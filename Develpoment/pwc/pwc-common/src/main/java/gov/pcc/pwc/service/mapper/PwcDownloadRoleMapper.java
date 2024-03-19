package gov.pcc.pwc.service.mapper;

import org.mapstruct.Mapper;

import gov.pcc.pwc.domain.PwcDownloadRole;
import gov.pcc.pwc.service.dto.PwcDownloadRoleDTO;

/**
 * Mapper for the entity {@link PwcDownloadRole} and its DTO {@link PwcDownloadRoleDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PwcDownloadRoleMapper extends EntityMapper<PwcDownloadRoleDTO, PwcDownloadRole> {}
