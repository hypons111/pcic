package gov.pcc.pwc.repository.custom;

import gov.pcc.pwc.service.criteria.RecAnalysisCriteria;
import gov.pcc.pwc.service.dto.RecAnalysisDTO;
import gov.pcc.pwc.service.genSerialNumber.errors.TypeVariableNotMatchException;
import org.springframework.data.domain.Page;

public interface RecAnalysisRepositoryCustom {
    Page<RecAnalysisDTO> findAllByCriteria(RecAnalysisCriteria criteria);
    RecAnalysisDTO packPkAndLogColumn (RecAnalysisDTO recAnalysisDTO) throws TypeVariableNotMatchException;
}
