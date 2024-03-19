package gov.pcc.pwc.service;

import gov.pcc.pwc.repository.AdmLoginHisRepository;
import gov.pcc.pwc.service.criteria.AdmAgentCriteria;
import gov.pcc.pwc.service.criteria.AdmLoginHisCriteria;
import gov.pcc.pwc.service.dto.AdmAgentDTO;
import gov.pcc.pwc.service.dto.AdmLoginHisDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;

@Service
@Transactional
public class AdmLoginHisService {

    private final Logger log = LoggerFactory.getLogger(AdmLoginHisService.class);

    private final AdmLoginHisRepository admLoginHisRepository;

    public AdmLoginHisService(AdmLoginHisRepository admLoginHisRepository) {
        this.admLoginHisRepository = admLoginHisRepository;
    }


    /**
     * Find the list of AdmLoginHisDTO by criteria.
     *
     * @param criteria the criteria.
     * @return the list of AdmLoginHisDTO.
     */
    @Transactional(readOnly = true)
    public Page<AdmLoginHisDTO> findByCriteria(AdmLoginHisCriteria criteria) {
        if (criteria.getSortBy() != null && criteria.getSortBy().length == 0) {
            criteria.setSortBy(new String[] { "createTime" });
        }
        if (criteria.getDateStart() == null || criteria.getDateStart().equals("")) {
            criteria.setDateStart(Instant.MIN);
        }
        if (criteria.getDateEnd() == null || criteria.getDateEnd().equals("")) {
            criteria.setDateEnd(Instant.MAX);
        }
        final Page<AdmLoginHisDTO> admLoginHisDTOs = admLoginHisRepository.findAllByCriteria(criteria);
        return admLoginHisDTOs;
    }

    @Transactional(readOnly = true)
    public AdmLoginHisDTO findByPk(Long id) {
        return admLoginHisRepository.findByPk(id);
    }

}
