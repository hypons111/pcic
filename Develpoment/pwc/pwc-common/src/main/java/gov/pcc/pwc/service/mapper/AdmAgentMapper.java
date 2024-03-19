package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.AdmAgent;
import gov.pcc.pwc.service.dto.AdmAgentDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link AdmAgent} and its DTO {@link AdmAgentDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AdmAgentMapper extends EntityMapper<AdmAgentDTO, AdmAgent> {}
