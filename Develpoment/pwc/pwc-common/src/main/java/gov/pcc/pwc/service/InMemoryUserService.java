package gov.pcc.pwc.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import gov.pcc.pwc.security.user.SystemUser;
import gov.pcc.pwc.security.user.User;

@Service
//@Profile("!prod && !test")
public class InMemoryUserService implements UserService {

    private final Logger log = LoggerFactory.getLogger(InMemoryUserService.class);

    private final Map<String, SystemUser> users = new HashMap<>();

    public Optional<SystemUser> getUserWithAuthoritiesById(String id) {
        return Optional.ofNullable(users.get(id));
    }

    public void addUser(SystemUser user) {
        User previousUser = this.users.put(user.getId(), user);
        if (previousUser == null) {
            log.info("User has been added: {}", user);
        } else {
            log.info("User {} has been replaced by {}", previousUser, user);
        }
    }
}
