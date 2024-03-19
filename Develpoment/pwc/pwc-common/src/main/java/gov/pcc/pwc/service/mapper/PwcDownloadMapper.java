package gov.pcc.pwc.service.mapper;

import org.mapstruct.Mapper;

import gov.pcc.pwc.domain.PwcDownload;
import gov.pcc.pwc.service.dto.PwcDownloadDTO;

/**
 * Mapper for the entity {@link PwcDownload} and its DTO {@link PwcDownloadDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PwcDownloadMapper extends EntityMapper<PwcDownloadDTO, PwcDownload> {}
