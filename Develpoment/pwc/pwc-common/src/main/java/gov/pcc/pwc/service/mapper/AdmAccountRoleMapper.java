package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.AdmAccountRole;
import gov.pcc.pwc.service.dto.AdmAccountRoleDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link AdmAccountRole} and its DTO {@link AdmAccountRoleDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AdmAccountRoleMapper extends EntityMapper<AdmAccountRoleDTO, AdmAccountRole> {}
