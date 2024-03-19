package gov.pcc.pwc.service.mapper;

import org.mapstruct.Mapper;

import gov.pcc.pwc.domain.PwcInformationRole;
import gov.pcc.pwc.service.dto.PwcInformationRoleDTO;

/**
 * Mapper for the entity {@link PwcInformationRole} and its DTO {@link PwcInformationRoleDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PwcInformationRoleMapper extends EntityMapper<PwcInformationRoleDTO, PwcInformationRole> {}
