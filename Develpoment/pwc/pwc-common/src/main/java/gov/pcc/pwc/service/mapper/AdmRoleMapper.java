package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.AdmRole;
import gov.pcc.pwc.service.dto.AdmRoleDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link AdmRole} and its DTO {@link AdmRoleDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AdmRoleMapper extends EntityMapper<AdmRoleDTO, AdmRole> {}
