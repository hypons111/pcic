package gov.pcc.pwc.pwb.rest;

import java.util.Optional;

import gov.pcc.pwc.security.SecurityUtils;
import gov.pcc.pwc.service.UserService;
import gov.pcc.pwc.service.dto.UserDTO;
import gov.pcc.pwc.service.mapper.UserMapper;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing the current user's account.
 */
@RestController
@RequestMapping("/api")
public class AccountResource {

    private final Environment env;

    @Value("${info.display-ribbon-on-profiles:}")
    private String displayRibbonOnProfiles;

    private static class AccountResourceException extends RuntimeException {

        private AccountResourceException(String message) {
            super(message);
        }
    }

    public static class Info {

        private String[] activeProfiles;

        private String displayRibbonOnProfiles;

        public String[] getActiveProfiles() {
            return activeProfiles;
        }

        public void setActiveProfiles(String[] activeProfiles) {
            this.activeProfiles = activeProfiles;
        }

        public String getDisplayRibbonOnProfiles() {
            return displayRibbonOnProfiles;
        }

        public void setDisplayRibbonOnProfiles(String displayRibbonOnProfiles) {
            this.displayRibbonOnProfiles = displayRibbonOnProfiles;
        }
    }

    private final Logger log = LoggerFactory.getLogger(AccountResource.class);

    private final UserService userService;

    private final UserMapper userMapper;

    public AccountResource(Environment env, UserService userService, UserMapper userMapper) {
        this.env = env;
        this.userService = userService;
        this.userMapper = userMapper;
    }

    /**
     * {@code GET  /authenticate} : check if the user is authenticated, and return its login.
     *
     * @param request the HTTP request.
     * @return the login if the user is authenticated.
     */
    @GetMapping("/authenticate")
    public String isAuthenticated(HttpServletRequest request) {
        log.debug("REST request to check if the current user is authenticated");
        return request.getRemoteUser();
    }

    /**
     * {@code GET  /account} : get the current user.
     *
     * @return the current user.
     * @throws RuntimeException {@code 500 (Internal Server Error)} if the user couldn't be returned.
     */
    @GetMapping("/account")
    public UserDTO getAccount() {
        Optional<String> userLogin = SecurityUtils.getCurrentUserLogin();
        return userLogin
            .map(login -> login.contains("/") ? login.substring(login.indexOf('/') + 1) : login)
            .flatMap(userService::getUserWithAuthoritiesById)
            .map(userMapper::userToUserDTO)
            .orElseThrow(() -> new AccountResourceException("User " + userLogin + " could not be found"));
    }

    @GetMapping("/sso")
    public ResponseEntity sso() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/profiles")
    public Info info() {
        Info info = new Info();
        info.setActiveProfiles(env.getActiveProfiles());
        info.setDisplayRibbonOnProfiles(displayRibbonOnProfiles);
        return info;
    }
}
