package gov.pcc.pwc.service.mapper;

import org.mapstruct.Mapper;

import gov.pcc.pwc.domain.PwcDownloadAppendix;
import gov.pcc.pwc.service.dto.PwcDownloadAppendixDTO;

/**
 * Mapper for the entity {@link PwcDownloadAppendix} and its DTO {@link PwcDownloadAppendixDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PwcDownloadAppendixMapper extends EntityMapper<PwcDownloadAppendixDTO, PwcDownloadAppendix> {}
