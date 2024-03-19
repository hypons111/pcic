package gov.pcc.pwc.service.mapper;

import org.mapstruct.Mapper;

import gov.pcc.pwc.domain.PwcInformation;
import gov.pcc.pwc.service.dto.PwcInformationDTO;

/**
 * Mapper for the entity {@link PwcInformation} and its DTO {@link PwcInformationDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PwcInformationMapper extends EntityMapper<PwcInformationDTO, PwcInformation> {}
