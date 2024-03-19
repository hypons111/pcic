package gov.pcc.pwc.service.mapper;

import org.mapstruct.Mapper;

import gov.pcc.pwc.domain.PwcInformationAppendix;
import gov.pcc.pwc.service.dto.PwcInformationAppendixDTO;

/**
 * Mapper for the entity {@link PwcInformationAppendix} and its DTO {@link PwcInformationAppendixDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PwcInformationAppendixMapper extends EntityMapper<PwcInformationAppendixDTO, PwcInformationAppendix> {}
