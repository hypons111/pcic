package gov.pcc.pwc.service.mapper;

import org.mapstruct.Mapper;

import gov.pcc.pwc.domain.PwcDownloadModule;
import gov.pcc.pwc.service.dto.PwcDownloadModuleDTO;

/**
 * Mapper for the entity {@link PwcDownloadModule} and its DTO {@link PwcDownloadModuleDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PwcDownloadModuleMapper extends EntityMapper<PwcDownloadModuleDTO, PwcDownloadModule> {}
