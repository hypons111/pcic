package gov.pcc.pwc.service;

import java.util.List;

import gov.pcc.pwc.repository.AdmRoleFunctionExtendRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gov.pcc.pwc.domain.AdmFunctionExtend;
import gov.pcc.pwc.repository.AdmFunctionExtendRepository;
import gov.pcc.pwc.service.dto.AdmFunctionExtendDTO;

/**
 * Service Implementation for managing {@link AdmFunctionExtend}.
 */
@Service
@Transactional
public class AdmFunctionExtendService {

    private final Logger log = LoggerFactory.getLogger(AdmFunctionExtendService.class);

    private final AdmFunctionExtendRepository admFunctionExtendRepository;

    private final AdmRoleFunctionExtendRepository admRoleFunctionExtendRepository;

    public AdmFunctionExtendService(AdmFunctionExtendRepository admFunctionExtendRepository, AdmRoleFunctionExtendRepository admRoleFunctionExtendRepository) {
        this.admFunctionExtendRepository = admFunctionExtendRepository;
        this.admRoleFunctionExtendRepository = admRoleFunctionExtendRepository;
    }

    /**
     * Save a admFunctionExtend.
     *
     * @param admFunctionExtendDTO the entity to save.
     * @return the persisted entity.
     */
    @Transactional
    public AdmFunctionExtendDTO save(AdmFunctionExtendDTO admFunctionExtendDTO) {
        log.debug("Request to save AdmFunctionExtend: {}", admFunctionExtendDTO);
        AdmFunctionExtend admFunctionExtend = new AdmFunctionExtend();
        admFunctionExtend.setFunctionExtendId(admFunctionExtendDTO.getFunctionExtendId());
        admFunctionExtend.setFunctionExtendName(admFunctionExtendDTO.getFunctionExtendName());
        admFunctionExtend.setFunctionExtendDescript(admFunctionExtendDTO.getFunctionExtendDescript());
        admFunctionExtend.setUpdateTime(admFunctionExtendDTO.getUpdateTime());
        admFunctionExtend.setUpdateUser(admFunctionExtendDTO.getUpdateUser());
        admFunctionExtendRepository.save(admFunctionExtend);
        return admFunctionExtendDTO;
    }

    /**
     * Update a admFunctionExtend.
     *
     * @param admFunctionExtendDTO the entity to update.
     * @return the persisted entity.
     */
    @Transactional
    public AdmFunctionExtendDTO update(AdmFunctionExtendDTO admFunctionExtendDTO) {
        log.debug("Request to update AdmFunctionExtend : {}", admFunctionExtendDTO);
        admFunctionExtendRepository.update(admFunctionExtendDTO);
        return admFunctionExtendDTO;
    }

    /**
     * Get one admFunctionExtend by functionExtendId.
     *
     * @param functionExtendId the unique key of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public AdmFunctionExtendDTO findOne(String functionExtendId) {
        log.debug("Request to get AdmFunctionExtend : {}", functionExtendId);
        AdmFunctionExtendDTO criteria = new AdmFunctionExtendDTO();
        criteria.setFunctionExtendId(functionExtendId);

        return admFunctionExtendRepository.findAllByCriteria(criteria).get(0);
    }

    /**
     * Find the list of admFunctionExtendDTO by criteria.
     *
     * @param criteria the criteria.
     * @return the list of admFunctionExtendDTO.
     */

    @Transactional(readOnly = true)
    public List<AdmFunctionExtendDTO> findByCriteria(AdmFunctionExtendDTO criteria) {
        return admFunctionExtendRepository.findAllByCriteria(criteria);
    }

    /**
     * Find all function for front-end functionExtendOpts
     *
     * @return the list of AdmFunctionExtendDTO.
     */

    @Transactional(readOnly = true)
    public List<AdmFunctionExtendDTO> findAllFunctionExtendOpts() {
        return admFunctionExtendRepository.findAllFunctionExtendOpts();
    }

    /**
     * Delete the "functionExtendId" AdmFunctionExtend.
     *
     * @param functionExtendId the unique key of the entity.
     */

    @Transactional
    public String delete(String functionExtendId) {
        log.debug("Request to delete AdmFunctionExtend : {}", functionExtendId);
        admFunctionExtendRepository.deleteByFunctionExtendId(functionExtendId);
        return "Y";
    }
}
