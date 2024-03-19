package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.AdmFunction;
import gov.pcc.pwc.domain.PwbUsualfunction;
import gov.pcc.pwc.repository.PwbUsualfunctionRepository;
import gov.pcc.pwc.service.dto.PwbUsualfunctionDTO;
import gov.pcc.pwc.service.dto.PwcPengingViewDTO;
import gov.pcc.pwc.service.dto.PwcSharedDTO;
import gov.pcc.pwc.service.mapper.PwbUsualfunctionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link PwbUsualfunction}.
 */
@Service
@Transactional
public class PwbUsualfunctionService {

    private final Logger log = LoggerFactory.getLogger(PwbUsualfunctionService.class);

    private final PwbUsualfunctionRepository pwbUsualfunctionRepository;

    private final PwbUsualfunctionMapper pwbUsualfunctionMapper;

    public PwbUsualfunctionService(PwbUsualfunctionRepository pwbUsualfunctionRepository, PwbUsualfunctionMapper pwbUsualfunctionMapper) {
        this.pwbUsualfunctionRepository = pwbUsualfunctionRepository;
        this.pwbUsualfunctionMapper = pwbUsualfunctionMapper;
    }

    /**
     * Save a pwbUsualfunction.
     *
     * @param pwbUsualfunctionDTO the entity to save.
     * @return the persisted entity.
     */
    public PwbUsualfunctionDTO save(PwbUsualfunctionDTO pwbUsualfunctionDTO) {
        log.debug("Request to save PwbUsualfunction : {}", pwbUsualfunctionDTO);
        PwbUsualfunction pwbUsualfunction = pwbUsualfunctionMapper.toEntity(pwbUsualfunctionDTO);
        pwbUsualfunction = pwbUsualfunctionRepository.save(pwbUsualfunction);
        return pwbUsualfunctionMapper.toDto(pwbUsualfunction);
    }

    /**
     * Partially update a pwbUsualfunction.
     *
     * @param pwbUsualfunctionDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<PwbUsualfunctionDTO> partialUpdate(PwbUsualfunctionDTO pwbUsualfunctionDTO) {
        log.debug("Request to partially update PwbUsualfunction : {}", pwbUsualfunctionDTO);

        return pwbUsualfunctionRepository
            .findById(pwbUsualfunctionDTO.getId())
            .map(existingPwbUsualfunction -> {
                pwbUsualfunctionMapper.partialUpdate(existingPwbUsualfunction, pwbUsualfunctionDTO);

                return existingPwbUsualfunction;
            })
            .map(pwbUsualfunctionRepository::save)
            .map(pwbUsualfunctionMapper::toDto);
    }

    /**
     * Get all the pwbUsualfunctions.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<PwbUsualfunctionDTO> findAll() {
        log.debug("Request to get all PwbUsualfunctions");
        return pwbUsualfunctionRepository
            .findAll()
            .stream()
            .map(pwbUsualfunctionMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one pwbUsualfunction by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PwbUsualfunctionDTO> findOne(Long id) {
        log.debug("Request to get PwbUsualfunction : {}", id);
        return pwbUsualfunctionRepository.findById(id).map(pwbUsualfunctionMapper::toDto);
    }

    /**
     * Delete the pwbUsualfunction by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete PwbUsualfunction : {}", id);
        pwbUsualfunctionRepository.deleteById(id);
    }

    /**
     * Get all the pwbUsualfunctions.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<AdmFunction> getUsualfunctionFunctionName(String id) {
        return pwbUsualfunctionRepository.getUsualfunctionFunctionName(id);
    }

    /**
     * Get all the pwbUsualfunctions.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<AdmFunction> getUsualfunctionFunctionNameDefault(String id) {
        return pwbUsualfunctionRepository.getUsualfunctionFunctionNameDefault(id);
    }

    /**
     * Save a pwbUsualfunction.
     *
     * @param pwbUsualfunctionDTO the entity to save.
     * @return the persisted entity.
     */
    public void addPwbUsualfunction(PwbUsualfunctionDTO pwbUsualfunctionDTO) {
        log.debug("Request to save PwbUsualfunction : {}", pwbUsualfunctionDTO);
        List<PwcSharedDTO> SharedDTO = pwbUsualfunctionDTO.getUsualFunctions();

        if (SharedDTO.size() > 0) {
            // 查詢PWB_USUALFUNCTION是否有資料
            List<PwbUsualfunction> usualfunctionList = pwbUsualfunctionRepository.findByUserId(pwbUsualfunctionDTO.getUserId());
            // 刪除舊有的PWB_USUALFUNCTION資料
            if (usualfunctionList.size() > 0) {
                usualfunctionList.forEach(item -> {
                    pwbUsualfunctionRepository.deleteById(item.getId());
                });
            }
        }
        //寫入 pwbUsualfunction 資料
        PwbUsualfunction pwbUsualfunction = null;
        for(PwcSharedDTO UsualFunction : SharedDTO){
            pwbUsualfunction = pwbUsualfunctionMapper.toEntity(pwbUsualfunctionDTO);
            pwbUsualfunction.setFunctionId(UsualFunction.getCode());
            pwbUsualfunction = pwbUsualfunctionRepository.save(pwbUsualfunction);
        }
    }

    /**
     * Get all the pwbUsualfunctions.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<PwcPengingViewDTO> getPengingViewForUserid(String id) {
        return pwbUsualfunctionRepository.getPengingViewForUserid(id);
    }

}
