package gov.pcc.pwc.security;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import gov.pcc.pwc.domain.User;
import gov.pcc.pwc.repository.UserRepository;

/**
 * Authenticate a user from the database.
 */
//@Profile("dev && !test")
//@Component("userDetailsService")
public class DomainUserDetailsService implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(DomainUserDetailsService.class);

    private final UserRepository userRepository;

    public DomainUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String login) {
        log.debug("Authenticating {}", login);

        if (new EmailValidator().isValid(login, null)) {
            return userRepository
                .findOneWithAuthoritiesByEmailIgnoreCase(login)
                .map(user -> createSpringSecurityUser(login, user))
                .orElseThrow(() -> new UsernameNotFoundException("User with email " + login + " was not found in the database"));
        }

        String lowercaseLogin = login.toLowerCase(Locale.ENGLISH);
        return userRepository
            .findOneWithAuthoritiesByLogin(lowercaseLogin)
            .map(user -> createSpringSecurityUser(lowercaseLogin, user))
            .orElseThrow(() -> new UsernameNotFoundException("User " + lowercaseLogin + " was not found in the database"));
    }

    private org.springframework.security.core.userdetails.User createSpringSecurityUser(String lowercaseLogin, User user) {
        if (!user.isActivated()) {
            throw new UserNotActivatedException("User " + lowercaseLogin + " was not activated");
        }
        List<GrantedAuthority> grantedAuthorities = user
            .getAuthorities()
            .stream()
            .map(authority -> new SimpleGrantedAuthority(authority.getName()))
            .collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), grantedAuthorities);
    }

    /**
     * Utility class for Spring Security.
     */
    public static final class SecurityUtils {

        private SecurityUtils() {}

        /**
         * Get the login of the current user.
         *
         * @return the login of the current user.
         */
        public static Optional<String> getCurrentUserLogin() {
            SecurityContext securityContext = SecurityContextHolder.getContext();
            return Optional.ofNullable(extractPrincipal(securityContext.getAuthentication()));
        }

        private static String extractPrincipal(Authentication authentication) {
            if (authentication == null) {
                return null;
            } else if (authentication.getPrincipal() instanceof UserDetails) {
                UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
                return springSecurityUser.getUsername();
            } else if (authentication.getPrincipal() instanceof String) {
                return (String) authentication.getPrincipal();
            }
            return null;
        }

        /**
         * Get the JWT of the current user.
         *
         * @return the JWT of the current user.
         */
        public static Optional<String> getCurrentUserJWT() {
            SecurityContext securityContext = SecurityContextHolder.getContext();
            return Optional
                .ofNullable(securityContext.getAuthentication())
                .filter(authentication -> authentication.getCredentials() instanceof String)
                .map(authentication -> (String) authentication.getCredentials());
        }

        /**
         * Check if a user is authenticated.
         *
         * @return true if the user is authenticated, false otherwise.
         */
        public static boolean isAuthenticated() {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            return authentication != null && getAuthorities(authentication).noneMatch(AuthoritiesConstants.ANONYMOUS::equals);
        }

        /**
         * Checks if the current user has a specific authority.
         *
         * @param authority the authority to check.
         * @return true if the current user has the authority, false otherwise.
         */
        public static boolean hasCurrentUserThisAuthority(String authority) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            return authentication != null && getAuthorities(authentication).anyMatch(authority::equals);
        }

        private static Stream<String> getAuthorities(Authentication authentication) {
            return authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority);
        }
    }
}
