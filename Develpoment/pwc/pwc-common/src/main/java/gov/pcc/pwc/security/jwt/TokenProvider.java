package gov.pcc.pwc.security.jwt;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import gov.pcc.pwc.security.user.ApiAccessToken;
import gov.pcc.pwc.security.user.SystemUser;
import gov.pcc.pwc.security.user.UserDetailsWrapper;
import gov.pcc.pwc.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import tech.jhipster.config.JHipsterProperties;

@Component
public class TokenProvider {

    private final Logger log = LoggerFactory.getLogger(TokenProvider.class);

    private static final String AUTHORITIES_KEY = "auth";

    private static final String CLIENT_ID_KEY = "client_id";

    private final Key key;

    private final JwtParser jwtParser;

    private final long tokenValidityInMilliseconds;

    private final UserService userService;

    public TokenProvider(JHipsterProperties jHipsterProperties, UserService userService) {
        byte[] keyBytes;
        String secret = jHipsterProperties.getSecurity().getAuthentication().getJwt().getSecret();
        if (!ObjectUtils.isEmpty(secret)) {
            log.warn(
                "Warning: the JWT key used is not Base64-encoded. " +
                "We recommend using the `jhipster.security.authentication.jwt.base64-secret` key for optimum security."
            );
            keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        } else {
            log.debug("Using a Base64-encoded JWT secret key");
            keyBytes = Decoders.BASE64.decode(jHipsterProperties.getSecurity().getAuthentication().getJwt().getBase64Secret());
        }
        key = Keys.hmacShaKeyFor(keyBytes);
        jwtParser = Jwts.parserBuilder().setSigningKey(key).build();
        this.tokenValidityInMilliseconds = 1000 * jHipsterProperties.getSecurity().getAuthentication().getJwt().getTokenValidityInSeconds();

        this.userService = userService;
    }

    public String createToken(Authentication authentication) {
        String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));

        long now = (new Date()).getTime();
        Date validity = new Date(now + this.tokenValidityInMilliseconds);

        return Jwts
            .builder()
            .setSubject(authentication.getName())
            .claim(AUTHORITIES_KEY, authorities)
            .signWith(key, SignatureAlgorithm.HS512)
            .setExpiration(validity)
            .compact();
    }

    public String createApiAccessToken(ApiAccessToken token, Duration duration) {
        String authorities = token
            .getAuthorities()
            .stream()
            .map(authority -> "AUTHORITY_" + authority.getId())
            .collect(Collectors.joining(","));

        long now = (new Date()).getTime();
        Date validity = new Date(now + duration.toMillis());

        return Jwts
            .builder()
            .setSubject(token.getId())
            .claim(CLIENT_ID_KEY, token.getId())
            .claim(AUTHORITIES_KEY, authorities)
            .signWith(key, SignatureAlgorithm.HS512)
            .setExpiration(validity)
            .compact();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = jwtParser.parseClaimsJws(token).getBody();

        Collection<? extends GrantedAuthority> authorities = Arrays
            .stream(claims.get(AUTHORITIES_KEY).toString().split(","))
            .filter(auth -> !auth.trim().isEmpty())
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());

        String subject = claims.getSubject();

        String id = subject.substring(subject.indexOf('/') + 1);

        UserDetailsWrapper principal;

        if (claims.containsKey(CLIENT_ID_KEY)) {
            // API Client
            ApiAccessToken.Builder builder = ApiAccessToken.builder().setId(id).setName(id);
            authorities
                .stream()
                .map(GrantedAuthority::getAuthority)
                .forEach(
                    auth -> {
                        if (auth.startsWith("ROLE_")) {
                            builder.addRole(auth.substring("ROLE_".length()), auth.substring("ROLE_".length()));
                        } else if (auth.startsWith("AUTHORITY_")) {
                            builder.addAuthority(auth.substring("AUTHORITY_".length()), auth.substring("AUTHORITY_".length()));
                        } else {
                            throw new IllegalArgumentException("Unknown authority: " + auth);
                        }
                    }
                );
            principal = new UserDetailsWrapper(builder.build());
        } else {
            // 一般使用者
            SystemUser.Builder builder = SystemUser.builder().setId(id);

            final SystemUser user = userService
                .getUserWithAuthoritiesById(subject.substring(subject.indexOf('/') + 1))
                .orElseThrow(() -> new IllegalStateException("User " + subject + " is not available."));

            builder.setEmail(user.getEmail()).setIdn(user.getIdn()).setUnit(user.getUnit()).setName(user.getName()).setUserType(user.getUserType()).setPccOrgId(user.getPccOrgId()).setCompIdno(user.getCompIdno()).setIp(user.getIp());
            authorities
                .stream()
                .map(GrantedAuthority::getAuthority)
                .forEach(
                    auth -> {
                        if (auth.startsWith("ROLE_")) {
                            builder.addRole(auth.substring("ROLE_".length()), auth.substring("ROLE_".length()));
                        } else if (auth.startsWith("AUTHORITY_")) {
                            builder.addAuthority(auth.substring("AUTHORITY_".length()), auth.substring("AUTHORITY_".length()));
                        } else {
                            throw new IllegalArgumentException("Unknown authority: " + auth);
                        }
                    }
                );

            // TODO check authorities equality
            principal = new UserDetailsWrapper(builder.build());
        }
        return new UserDetailsAuthoritiesLinkedAuthenticationToken(principal);
    }

    public boolean validateToken(String authToken) {
        try {
            jwtParser.parseClaimsJws(authToken);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            log.info("Invalid JWT token.");
            log.trace("Invalid JWT token trace.", e);
        }
        return false;
    }
}
