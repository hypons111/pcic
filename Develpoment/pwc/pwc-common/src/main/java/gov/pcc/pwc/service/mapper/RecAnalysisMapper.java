package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.RecAnalysis;
import gov.pcc.pwc.service.dto.RecAnalysisDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link RecAnalysis} and its DTO {@link RecAnalysisDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RecAnalysisMapper extends EntityMapper<RecAnalysisDTO, RecAnalysis> {}
