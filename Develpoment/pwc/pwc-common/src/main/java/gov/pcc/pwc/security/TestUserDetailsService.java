package gov.pcc.pwc.security;

import org.springframework.context.annotation.Profile;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import gov.pcc.pwc.config.TestLoginConfigurationProperties;
import gov.pcc.pwc.security.user.UserDetailsWrapper;
import gov.pcc.pwc.service.UserService;

@Profile("test")
@Component("userDetailsService")
public class TestUserDetailsService implements UserDetailsService {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    private final TestLoginConfigurationProperties testLoginConfigurationProperties;

    public TestUserDetailsService(
        UserService userService,
        PasswordEncoder passwordEncoder,
        TestLoginConfigurationProperties testLoginConfigurationProperties
    ) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.testLoginConfigurationProperties = testLoginConfigurationProperties;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetailsWrapper userDetails = userService
            .getUserWithAuthoritiesById(username)
            .map(UserDetailsWrapper::new)
            .orElseThrow(() -> new UsernameNotFoundException(username));

        String password = testLoginConfigurationProperties
            .getUsers()
            .stream()
            .filter(user -> user.getId().equals(username))
            .findFirst()
            .map(TestLoginConfigurationProperties.User::getPassword)
            .orElseThrow(() -> new UsernameNotFoundException(username));

        userDetails.setPassword(passwordEncoder.encode(password));
        return userDetails;
    }

    /**
     * This exception is thrown in case of a not activated user trying to authenticate.
     */
    public static class UserNotActivatedException extends AuthenticationException {

        private static final long serialVersionUID = 1L;

        public UserNotActivatedException(String message) {
            super(message);
        }

        public UserNotActivatedException(String message, Throwable t) {
            super(message, t);
        }
    }
}
