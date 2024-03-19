package gov.pcc.pwc.repository.custom;

import gov.pcc.pwc.service.dto.AdmCompanyApplyDTO;
import gov.pcc.pwc.service.dto.AdmCompanyDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdmCompanyRepositoryCustom {
    AdmCompanyApplyDTO findByUserId(String userId);
    Page<AdmCompanyDTO> findByAdmCompanyAll(String keywordName, Pageable pageable);
}
