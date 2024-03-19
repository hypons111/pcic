package gov.pcc.pwc.service.mapper;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import gov.pcc.pwc.security.user.Authority;
import gov.pcc.pwc.security.user.Role;
import gov.pcc.pwc.security.user.SystemUser;
import gov.pcc.pwc.security.user.User;
import gov.pcc.pwc.service.dto.UserDTO;

/**
 * Mapper for the entity {@link User} and its DTO called {@link UserDTO}.
 * <p>
 * Normal mappers are generated using MapStruct, this one is hand-coded as MapStruct
 * support is still in beta, and requires a manual step with an IDE.
 */
@Service
public class UserMapper {

    public UserDTO userToUserDTO(SystemUser user) {
        if (user == null) {
            return null;
        } else {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setName(user.getName());
            userDTO.setUnit(user.getUnit());
            userDTO.setRoles(user.getRoles().stream().map(Role::getId).collect(Collectors.toSet()));
            userDTO.setAuthorities(user.getAuthorities().stream().map(Authority::getId).collect(Collectors.toSet()));
            userDTO.setIdn(user.getIdn());
            userDTO.setEmail(user.getEmail());
            userDTO.setUserType(user.getUserType());
            userDTO.setPccOrgId(user.getPccOrgId());
            userDTO.setCompIdno(user.getCompIdno());
            userDTO.setIp(user.getIp());
            return userDTO;
        }
    }

    public SystemUser userDTOToUser(UserDTO dto) {
        if (dto == null) {
            return null;
        } else {
            SystemUser.Builder builder = SystemUser.builder();
            builder.setId(dto.getId()).setName(dto.getName()).setUnit(dto.getUnit()).setIdn(dto.getIdn()).setEmail(dto.getEmail()).setUserType(dto.getUserType()).setPccOrgId(dto.getPccOrgId()).setCompIdno(dto.getCompIdno()).setIp(dto.getIp());

            dto.getRoles().forEach(role -> builder.addRole(role, role));
            dto.getAuthorities().forEach(authority -> builder.addAuthority(authority, authority));

            return builder.build();
        }
    }
}
