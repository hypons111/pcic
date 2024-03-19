package gov.pcc.pwc.service;

import java.util.List;

import gov.pcc.pwc.repository.AdmRoleFunctionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gov.pcc.pwc.domain.AdmFunction;
import gov.pcc.pwc.repository.AdmFunctionRepository;
import gov.pcc.pwc.service.dto.AdmFunctionDTO;

/**
 * Service Implementation for managing {@link AdmFunction}.
 */
@Service
@Transactional
public class AdmFunctionService {

    private final Logger log = LoggerFactory.getLogger(AdmFunctionService.class);

    private final AdmFunctionRepository admFunctionRepository;

    private final AdmRoleFunctionRepository admRoleFunctionRepository;

    public AdmFunctionService(AdmFunctionRepository admFunctionRepository, AdmRoleFunctionRepository admRoleFunctionRepository) {
        this.admFunctionRepository = admFunctionRepository;
        this.admRoleFunctionRepository = admRoleFunctionRepository;
    }

    /**
     * Save a admFunction.
     *
     * @param admFunctionDTO the entity to save.
     * @return the persisted entity.
     */
    @Transactional
    public AdmFunctionDTO save(AdmFunctionDTO admFunctionDTO) {
        log.debug("Request to save AdmFunction: {}", admFunctionDTO);
        AdmFunction admFunction = new AdmFunction();
        admFunction.setFunctionId(admFunctionDTO.getFunctionId());
        admFunction.setFunctionName(admFunctionDTO.getFunctionName());
        admFunction.setFunctionDescript(admFunctionDTO.getFunctionDescript());
        admFunction.setFunctionPath(admFunctionDTO.getFunctionPath());
        admFunction.setFunctionCategory(admFunctionDTO.getFunctionCategory());
        admFunction.setSysCategory(admFunctionDTO.getSysCategory());
        admFunction.setSortNo(admFunctionDTO.getSortNo());
        admFunction.setMasterFunctionId(admFunctionDTO.getMasterFunctionId());
        admFunction.setStatus(admFunctionDTO.getStatus());
        admFunction.setUpdateTime(admFunctionDTO.getUpdateTime());
        admFunction.setUpdateUser(admFunctionDTO.getUpdateUser());
        admFunctionRepository.save(admFunction);
        return admFunctionDTO;
    }

    /**
     * Update a admFunction.
     *
     * @param admFunctionDTO the entity to update.
     * @return the persisted entity.
     */
    @Transactional
    public AdmFunctionDTO update(AdmFunctionDTO admFunctionDTO) {
        log.debug("Request to update AdmFunction : {}", admFunctionDTO);
        admFunctionRepository.update(admFunctionDTO);
        return admFunctionDTO;
    }

    /**
     * Get one admFunction by functionId.
     *
     * @param functionId the unique key of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public AdmFunctionDTO findOne(String functionId) {
        log.debug("Request to get AdmFunction : {}", functionId);
        AdmFunctionDTO criteria = new AdmFunctionDTO();
        criteria.setFunctionId(functionId);

        return admFunctionRepository.findAllByCriteria(criteria).get(0);
    }

    /**
     * Find the list of admFunctionDTO by criteria.
     *
     * @param criteria the criteria.
     * @return the list of admFunctionDTO.
     */

    @Transactional(readOnly = true)
    public List<AdmFunctionDTO> findByCriteria(AdmFunctionDTO criteria) {
        return admFunctionRepository.findAllByCriteria(criteria);
    }

    /**
     * Find all function for front-end functionOpts
     *
     * @return the list of AdmFunctionDTO.
     */

    @Transactional(readOnly = true)
    public List<AdmFunctionDTO> findAllFunctionOpts() {
        return admFunctionRepository.findAllFunctionOpts();
    }

    /**
     * Delete the "functionId" AdmFunction.
     *
     * @param functionId the unique key of the entity.
     */

    @Transactional
    public String delete(String functionId) {
        log.debug("Request to delete AdmFunction : {}", functionId);
        if (admRoleFunctionRepository.findByFunctionId(functionId).size() > 0) {
            return "N";
        } else {
            admFunctionRepository.deleteByFunctionId(functionId);
            return "Y";
        }
    }
}
