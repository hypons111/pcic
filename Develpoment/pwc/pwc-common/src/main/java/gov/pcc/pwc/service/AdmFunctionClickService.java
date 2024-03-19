package gov.pcc.pwc.service;

import gov.pcc.pwc.repository.AdmFunctionClickRepository;
import gov.pcc.pwc.service.criteria.AdmFunctionClickCriteria;
import gov.pcc.pwc.service.criteria.AdmFunctionUsedRecordCriteria;
import gov.pcc.pwc.service.dto.AdmFunctionClickDTO;
import gov.pcc.pwc.service.dto.AdmFunctionUsedRecordDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class AdmFunctionClickService {

    private final Logger log = LoggerFactory.getLogger(AdmFunctionClickService.class);

    private final AdmFunctionClickRepository admFunctionClickRepository;

    public AdmFunctionClickService(AdmFunctionClickRepository admFunctionClickRepository) {
        this.admFunctionClickRepository = admFunctionClickRepository;
    }


    /**
     * Find the list of AdmFunctionClickDTO by criteria.
     *
     * @param criteria the criteria.
     * @return the list of AdmFunctionClickDTO.
     */
    @Transactional(readOnly = true)
    public Page<AdmFunctionClickDTO> findByCriteria(AdmFunctionClickCriteria criteria) {
        if (criteria.getSortBy() != null && criteria.getSortBy().length == 0) {
            criteria.setSortBy(new String[] { "createTime" });
        }
        if (criteria.getDateStart() == null || criteria.getDateStart().equals("")) {
            criteria.setDateStart(Instant.MIN);
        }
        if (criteria.getDateEnd() == null || criteria.getDateEnd().equals("")) {
            criteria.setDateEnd(Instant.MAX);
        }
        final Page<AdmFunctionClickDTO> admFunctionClickDTOs = admFunctionClickRepository.findAllByCriteria(criteria);
        return admFunctionClickDTOs;
    }

    /**
     * Find the list of AdmFunctionClickDTO statistics by criteria.
     *
     * @param criteria the criteria.
     * @return the list of AdmFunctionClickDTO statistics.
     */
    @Transactional(readOnly = true)
    public Page<AdmFunctionClickDTO> findStatisticsByCriteria(AdmFunctionClickCriteria criteria) {
        if (criteria.getSortBy() != null && criteria.getSortBy().length == 0) {
            criteria.setSortBy(new String[] { "functionId" });
        }
        if (criteria.getDateStart() == null || criteria.getDateStart().equals("")) {
            criteria.setDateStart(Instant.MIN);
        }
        if (criteria.getDateEnd() == null || criteria.getDateEnd().equals("")) {
            criteria.setDateEnd(Instant.MAX);
        }
        final Page<AdmFunctionClickDTO> AdmFunctionClickDTOs = admFunctionClickRepository.findAllStatisticsByCriteria(criteria);
        return AdmFunctionClickDTOs;
    }

    /**
     * Find the list of AdmFunctionClickDTO statistics info by criteria.
     *
     * @param criteria the criteria.
     * @return the list of AdmFunctionClickDTO statistics info.
     */
    @Transactional(readOnly = true)
    public List<AdmFunctionClickDTO> findStatisticsInfoByCriteria(AdmFunctionClickCriteria criteria) {
        if (criteria.getSortBy() != null && criteria.getSortBy().length == 0) {
            criteria.setSortBy(new String[] { "functionExtendId" });
        }
        if (criteria.getDateStart() == null || criteria.getDateStart().equals("")) {
            criteria.setDateStart(Instant.MIN);
        }
        if (criteria.getDateEnd() == null || criteria.getDateEnd().equals("")) {
            criteria.setDateEnd(Instant.MAX);
        }
        final List<AdmFunctionClickDTO> admFunctionClickDTOs = admFunctionClickRepository.findAllStatisticsInfoByCriteria(criteria);
        return admFunctionClickDTOs;
    }

}
