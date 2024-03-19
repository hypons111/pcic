package gov.pcc.pwc.service.mapper;

import org.mapstruct.Mapper;

import gov.pcc.pwc.domain.PwcInformationModule;
import gov.pcc.pwc.service.dto.PwcInformationModuleDTO;

/**
 * Mapper for the entity {@link PwcInformationModule} and its DTO {@link PwcInformationModuleDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PwcInformationModuleMapper extends EntityMapper<PwcInformationModuleDTO, PwcInformationModule> {}
