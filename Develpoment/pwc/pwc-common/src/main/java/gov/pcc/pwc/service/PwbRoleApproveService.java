package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.PwbRoleApprove;
import gov.pcc.pwc.repository.PwbRoleApproveRepository;
import gov.pcc.pwc.service.criteria.PwbRoleApproveCriteria;
import gov.pcc.pwc.service.dto.PwbRoleApproveDTO;
import gov.pcc.pwc.service.dto.PwbRoleDTO;
import gov.pcc.pwc.service.mapper.PwbRoleApproveMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link PwbRoleApprove}.
 */
@Service
@Transactional
public class PwbRoleApproveService {

    private final Logger log = LoggerFactory.getLogger(PwbRoleApproveService.class);

    private final PwbRoleApproveRepository pwbRoleApproveRepository;

    private final PwbRoleApproveMapper pwbRoleApproveMapper;

    public PwbRoleApproveService(PwbRoleApproveRepository pwbRoleApproveRepository, PwbRoleApproveMapper pwbRoleApproveMapper) {
        this.pwbRoleApproveRepository = pwbRoleApproveRepository;
        this.pwbRoleApproveMapper = pwbRoleApproveMapper;
    }

    /**
     * Save a pwbRoleApprove.
     *
     * @param pwbRoleApproveDTO the entity to save.
     * @return the persisted entity.
     */
    public PwbRoleApproveDTO save(PwbRoleApproveDTO pwbRoleApproveDTO) {
        log.debug("Request to save PwbRoleApprove : {}", pwbRoleApproveDTO);
        PwbRoleApprove pwbRoleApprove = pwbRoleApproveMapper.toEntity(pwbRoleApproveDTO);
        pwbRoleApprove = pwbRoleApproveRepository.save(pwbRoleApprove);
        return pwbRoleApproveMapper.toDto(pwbRoleApprove);
    }

    /**
     * Partially update a pwbRoleApprove.
     *
     * @param pwbRoleApproveDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<PwbRoleApproveDTO> partialUpdate(PwbRoleApproveDTO pwbRoleApproveDTO) {
        log.debug("Request to partially update PwbRoleApprove : {}", pwbRoleApproveDTO);

        return pwbRoleApproveRepository
            .findById(pwbRoleApproveDTO.getId())
            .map(existingPwbRoleApprove -> {
                pwbRoleApproveMapper.partialUpdate(existingPwbRoleApprove, pwbRoleApproveDTO);

                return existingPwbRoleApprove;
            })
            .map(pwbRoleApproveRepository::save)
            .map(pwbRoleApproveMapper::toDto);
    }

    /**
     * Get all the pwbRoleApproves.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<PwbRoleApproveDTO> findAll() {
        log.debug("Request to get all PwbRoleApproves");
        return pwbRoleApproveRepository
            .findAll()
            .stream()
            .map(pwbRoleApproveMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one pwbRoleApprove by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PwbRoleApproveDTO> findOne(Long id) {
        log.debug("Request to get PwbRoleApprove : {}", id);
        return pwbRoleApproveRepository.findById(id).map(pwbRoleApproveMapper::toDto);
    }

    /**
     * Delete the pwbRoleApprove by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete PwbRoleApprove : {}", id);
        pwbRoleApproveRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<String> findByUserId(String userId) {
        log.debug("Request to get moduleType : {}", userId);
        List<String> result = pwbRoleApproveRepository.findByUserId(userId);
        return result;
    }


    @Transactional(readOnly = true)
    public Page<PwbRoleDTO> findByCriteria(PwbRoleApproveCriteria criteria) {
        criteria.setSortBy(new String[]{"applyTime"});
        return pwbRoleApproveRepository.findByCriteria(criteria);
    }

    @Transactional(readOnly = true)
    public List<String> findByUserIdForAgency(String userId) {
        log.debug("Request to get moduleType : {}", userId);
        List<String> result = pwbRoleApproveRepository.findByUserIdForAgency(userId);
        return result;
    }

    @Transactional(readOnly = true)
    public Page<PwbRoleDTO> findApproveList(PwbRoleApproveCriteria criteria){
        return pwbRoleApproveRepository.findApproveList(criteria);
    }

    @Transactional(readOnly = true)
    public List<String> findApproveModuleType(){
        List<String> result = pwbRoleApproveRepository.findApproveModuleType();
        return result;
    }

    @Transactional(readOnly = true)
    public List<PwbRoleDTO> findApproveUserId(){
        List<PwbRoleDTO> result = pwbRoleApproveRepository.findApproveUserId();
        return result;
    }
}
