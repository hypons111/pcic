package gov.pcc.pwc.service;

import java.util.Optional;

import gov.pcc.pwc.security.user.SystemUser;

/**
 * Service class for managing users.
 */
public interface UserService {
    String USERS_BY_ID = "usersById";

    Optional<SystemUser> getUserWithAuthoritiesById(String id);
}
