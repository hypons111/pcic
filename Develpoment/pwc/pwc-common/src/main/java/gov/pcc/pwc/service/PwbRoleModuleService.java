package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.PwbRoleModule;
import gov.pcc.pwc.domain.PwcInformationModule;
import gov.pcc.pwc.repository.PwbRoleModuleRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import gov.pcc.pwc.service.criteria.PwbRoleModuleCriteria;
import gov.pcc.pwc.service.dto.PwbRoleModuleDTO;
import gov.pcc.pwc.service.dto.PwcInformationModuleDTO;
import gov.pcc.pwc.service.dto.PwcSharedDTO;
import gov.pcc.pwc.service.mapper.PwbRoleModuleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * Service Implementation for managing {@link PwbRoleModule}.
 */
@Service
@Transactional
public class PwbRoleModuleService {

    private final Logger log = LoggerFactory.getLogger(PwbRoleModuleService.class);

    private final PwbRoleModuleRepository pwbRoleModuleRepository;

    private final PwbRoleModuleMapper pwbRoleModuleMapper;

    public PwbRoleModuleService(PwbRoleModuleRepository pwbRoleModuleRepository, PwbRoleModuleMapper pwbRoleModuleMapper) {
        this.pwbRoleModuleRepository = pwbRoleModuleRepository;
        this.pwbRoleModuleMapper = pwbRoleModuleMapper;
    }

    /**
     * Save a pwbRoleModule.
     *
     * @param pwbRoleModuleDTO the entity to save.
     * @return the persisted entity.
     */
    public PwbRoleModuleDTO save(PwbRoleModuleDTO pwbRoleModuleDTO) {
        log.debug("Request to save PwbRoleModule : {}", pwbRoleModuleDTO);
        PwbRoleModule pwbRoleModule = pwbRoleModuleMapper.toEntity(pwbRoleModuleDTO);
        pwbRoleModule = pwbRoleModuleRepository.save(pwbRoleModule);
        return pwbRoleModuleMapper.toDto(pwbRoleModule);
    }

    /**
     * Partially update a pwbRoleModule.
     *
     * @param pwbRoleModuleDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<PwbRoleModuleDTO> partialUpdate(PwbRoleModuleDTO pwbRoleModuleDTO) {
        log.debug("Request to partially update PwbRoleModule : {}", pwbRoleModuleDTO);

        return pwbRoleModuleRepository
            .findById(pwbRoleModuleDTO.getId())
            .map(existingPwbRoleModule -> {
                pwbRoleModuleMapper.partialUpdate(existingPwbRoleModule, pwbRoleModuleDTO);

                return existingPwbRoleModule;
            })
            .map(pwbRoleModuleRepository::save)
            .map(pwbRoleModuleMapper::toDto);
    }

    /**
     * Get all the pwbRoleModules.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<PwbRoleModuleDTO> findAll() {
        log.debug("Request to get all PwbRoleModules");
        return pwbRoleModuleRepository.findAll().stream().map(pwbRoleModuleMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one pwbRoleModule by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PwbRoleModuleDTO> findOne(Long id) {
        log.debug("Request to get PwbRoleModule : {}", id);
        return pwbRoleModuleRepository.findById(id).map(pwbRoleModuleMapper::toDto);
    }

    /**
     * Delete the pwbRoleModule by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete PwbRoleModule : {}", id);
        pwbRoleModuleRepository.deleteById(id);
    }

    public List<String> findAllBusinessType() {
        log.debug("Request to get BusinessType ");
        return pwbRoleModuleRepository.findAllBusinessType();
    }

    public List<PwbRoleModuleDTO> findRoleId() {
        log.debug("Request to get RoleId ");
        return pwbRoleModuleRepository.findRoleId();
    }

    public Page<PwbRoleModuleDTO> findSearchList(PwbRoleModuleCriteria pwbRoleModuleCriteria){
        log.debug("Request to get searchList ");
        return pwbRoleModuleRepository.findSearchList(pwbRoleModuleCriteria);
    }

    public List<PwbRoleModuleDTO> findRoleIdByModule(PwbRoleModuleCriteria pwbRoleModuleCriteria) {
        log.debug("Request to get RoleIdByModule ");
        return pwbRoleModuleRepository.findRoleIdByModule(pwbRoleModuleCriteria);
    }

    public void deleteModule(PwbRoleModuleDTO pwbRoleModuleDTO){
        log.debug("REST request to delete module ");
        pwbRoleModuleRepository.deleteModule(pwbRoleModuleDTO);
    }

    public List<PwbRoleModuleDTO> findAllRoleId(PwbRoleModuleCriteria pwbRoleModuleCriteria) {
        log.debug("Request to get AllRoleId ");
        return pwbRoleModuleRepository.findAllRoleId(pwbRoleModuleCriteria);
    }

    public void saveRoleId(PwbRoleModuleDTO pwbRoleModuleDTO){
        log.debug("REST request to save module ");

        for(PwbRoleModuleDTO e : pwbRoleModuleDTO.getTable()){
            PwbRoleModule pwbRoleModule = new PwbRoleModule();
            BeanUtils.copyProperties(pwbRoleModuleDTO, pwbRoleModule);
            pwbRoleModule.setRoleId(e.getRoleId());
            pwbRoleModuleRepository.save(pwbRoleModule);
        }


    }
}
