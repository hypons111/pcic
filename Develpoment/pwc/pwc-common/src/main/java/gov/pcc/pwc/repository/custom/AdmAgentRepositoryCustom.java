package gov.pcc.pwc.repository.custom;

import gov.pcc.pwc.service.criteria.AdmAgentCriteria;
import gov.pcc.pwc.service.dto.AdmAgentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdmAgentRepositoryCustom {
    Page<AdmAgentDTO> findAllByCriteria(AdmAgentCriteria criteria);
    Page<AdmAgentDTO> findNameAndCodeByKeyWord(String keywordName, String keywordId, Pageable pageable);
    List<AdmAgentDTO> findBidExamineUnit(String wkutCode);
}
