package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.PwbUnitRole;
import gov.pcc.pwc.service.dto.PwbUnitRoleDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PwbUnitRole} and its DTO {@link PwbUnitRoleDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PwbUnitRoleMapper extends EntityMapper<PwbUnitRoleDTO, PwbUnitRole> {}
