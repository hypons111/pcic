package gov.pcc.pwc.service;

import gov.pcc.pwc.repository.AdmBatchRecordRepository;
import gov.pcc.pwc.service.criteria.AdmBatchRecordCriteria;
import gov.pcc.pwc.service.dto.AdmBatchRecordDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdmBatchRecordService {

    private final Logger log = LoggerFactory.getLogger(AdmBatchRecordService.class);

    private final AdmBatchRecordRepository admBatchRecordRepository;

    public AdmBatchRecordService(AdmBatchRecordRepository admBatchRecordRepository) {
        this.admBatchRecordRepository = admBatchRecordRepository;
    }

    /**
     * Find the list of AdmBatchRecordDTO by criteria.
     *
     * @param criteria the criteria.
     * @return the list of AdmBatchRecordDTO.
     */
    @Transactional(readOnly = true)
    public Page<AdmBatchRecordDTO> findByCriteria(AdmBatchRecordCriteria criteria) {
        if (criteria.getSortBy() != null && criteria.getSortBy().length == 0) {
            criteria.setSortBy(new String[] { "id" });
        }
        final Page<AdmBatchRecordDTO> admBatchRecordDTOs = admBatchRecordRepository.findAllByCriteria(criteria);
        return admBatchRecordDTOs;
    }

}
