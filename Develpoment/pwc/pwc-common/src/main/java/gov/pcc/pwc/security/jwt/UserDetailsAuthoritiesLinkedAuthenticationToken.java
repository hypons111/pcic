package gov.pcc.pwc.security.jwt;

import static java.util.Objects.requireNonNull;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import gov.pcc.pwc.security.user.UserDetailsWrapper;

/**
 * 跟 UserDetails 裡的 authorities 集合連動的 AuthenticationToken。
 */
public class UserDetailsAuthoritiesLinkedAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private static final long serialVersionUID = -5567317885584097082L;

    public UserDetailsAuthoritiesLinkedAuthenticationToken(UserDetailsWrapper principal) {
        super(requireNonNull(principal, "Parameter \"principal\" must not be null."), null, principal.getAuthorities());
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return ((UserDetailsWrapper) getPrincipal()).getAuthorities();
    }
}
