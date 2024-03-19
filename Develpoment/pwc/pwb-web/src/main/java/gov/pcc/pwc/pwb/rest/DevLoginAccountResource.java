package gov.pcc.pwc.pwb.rest;

import gov.pcc.pwc.service.InMemoryUserService;
import gov.pcc.pwc.service.dto.UserDTO;
import gov.pcc.pwc.service.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing the current user's account.
 */
@RestController
@RequestMapping("/api")
//@Profile("dev && !test")
public class DevLoginAccountResource {

    private final Logger log = LoggerFactory.getLogger(DevLoginAccountResource.class);

    private final InMemoryUserService userService;

    private final UserMapper userMapper;

    public DevLoginAccountResource(InMemoryUserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping("/account")
    public ResponseEntity<UserDTO> createAccount(@RequestBody UserDTO userDTO) {
        log.info("DEVELOPMENT ONLY!! Account creation request received: {}", userDTO);
        userService.addUser(userMapper.userDTOToUser(userDTO));
        return ResponseEntity.ok(userDTO);
    }
}
