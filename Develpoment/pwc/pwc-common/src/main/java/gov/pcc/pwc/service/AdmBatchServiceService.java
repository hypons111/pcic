package gov.pcc.pwc.service;

import gov.pcc.pwc.repository.AdmBatchServiceRepository;
import gov.pcc.pwc.service.criteria.AdmBatchServiceCriteria;
import gov.pcc.pwc.service.dto.AdmBatchServiceDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdmBatchServiceService {

    private final Logger log = LoggerFactory.getLogger(AdmBatchServiceService.class);

    private final AdmBatchServiceRepository admBatchServiceRepository;

    public AdmBatchServiceService(AdmBatchServiceRepository admBatchServiceRepository) {
        this.admBatchServiceRepository = admBatchServiceRepository;
    }

    /**
     * Find the list of AdmBatchServiceDTO by criteria.
     *
     * @param criteria the criteria.
     * @return the list of AdmBatchServiceDTO.
     */
    @Transactional(readOnly = true)
    public Page<AdmBatchServiceDTO> findByCriteria(AdmBatchServiceCriteria criteria) {
        if (criteria.getSortBy() != null && criteria.getSortBy().length == 0) {
            criteria.setSortBy(new String[] { "batchServiceNo" });
        }
        final Page<AdmBatchServiceDTO> admBatchServiceDTOs = admBatchServiceRepository.findAllByCriteria(criteria);
        return admBatchServiceDTOs;
    }

}
