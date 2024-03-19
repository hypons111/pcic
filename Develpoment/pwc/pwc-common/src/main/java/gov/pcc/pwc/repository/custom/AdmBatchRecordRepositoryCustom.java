package gov.pcc.pwc.repository.custom;

import gov.pcc.pwc.service.criteria.AdmBatchRecordCriteria;
import gov.pcc.pwc.service.dto.AdmBatchRecordDTO;
import org.springframework.data.domain.Page;

public interface AdmBatchRecordRepositoryCustom {
    Page<AdmBatchRecordDTO> findAllByCriteria(AdmBatchRecordCriteria criteria);
}
