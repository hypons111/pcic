package gov.pcc.pwc.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import gov.pcc.pwc.security.user.UserDetailsWrapper;
import gov.pcc.pwc.service.UserService;

//@Profile("!prod && !test")
@Component("userDetailsService")
public class DevUserDetailsService implements UserDetailsService {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    public DevUserDetailsService(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetailsWrapper userDetails = userService.getUserWithAuthoritiesById(username).map(UserDetailsWrapper::new).get();
        userDetails.setPassword(passwordEncoder.encode("REMOVE_ME"));
        return userDetails;
    }
}
