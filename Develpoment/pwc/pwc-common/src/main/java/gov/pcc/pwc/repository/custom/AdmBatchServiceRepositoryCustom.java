package gov.pcc.pwc.repository.custom;

import gov.pcc.pwc.service.criteria.AdmBatchServiceCriteria;
import gov.pcc.pwc.service.dto.AdmBatchServiceDTO;
import org.springframework.data.domain.Page;

public interface AdmBatchServiceRepositoryCustom {
    Page<AdmBatchServiceDTO> findAllByCriteria(AdmBatchServiceCriteria criteria);
}
