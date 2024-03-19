package gov.pcc.pwc.security;

import org.springframework.security.core.AuthenticationException;

/**
 * This exception is thrown in case of a not activated user trying to authenticate.
 */
public class UserNotActivatedException extends AuthenticationException {

    private static final long serialVersionUID = 1L;

    public UserNotActivatedException(String message) {
        super(message);
    }

    public UserNotActivatedException(String message, Throwable t) {
        super(message, t);
    }

    /**
     * Constants for Spring Security authorities.
     */
    public static final class AuthoritiesConstants {

        public static final String ADMIN = "ROLE_ADMIN";

        public static final String USER = "ROLE_USER";

        public static final String ANONYMOUS = "ROLE_ANONYMOUS";

        private AuthoritiesConstants() {}
    }
}
