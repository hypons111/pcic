package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.RecUserProjectCode;
import gov.pcc.pwc.repository.RecUserProjectCodeRepository;
import gov.pcc.pwc.service.criteria.RecUserProjectCodeCriteria;
import gov.pcc.pwc.service.dto.*;
import gov.pcc.pwc.service.mapper.RecUserProjectCodeMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link RecUserProjectCode}.
 */
@Service
@Transactional
public class RecUserProjectCodeService {

    private final Logger log = LoggerFactory.getLogger(RecUserProjectCodeService.class);

    private final RecUserProjectCodeRepository recUserProjectCodeRepository;

    private final RecUserProjectCodeMapper recUserProjectCodeMapper;

    public RecUserProjectCodeService(
        RecUserProjectCodeRepository recUserProjectCodeRepository,
        RecUserProjectCodeMapper recUserProjectCodeMapper
    ) {
        this.recUserProjectCodeRepository = recUserProjectCodeRepository;
        this.recUserProjectCodeMapper = recUserProjectCodeMapper;
    }

    public List<RecOrgRoleDTO> getAllRecOrg(){
        log.debug("Request to getAllRecOrg");
        return recUserProjectCodeRepository.findAllRecOrgByQuery();
    };

    public List<RecAdmRoleDTO> getAllRecRole(){
        log.debug("Request to getAllRecRole");
        return recUserProjectCodeRepository.findAllRecRoleByQuery();
    }

    public List<RecProjectCodesDTO> getAccessProjectCodes(String loginId, String roleId, String orgId){
        log.debug("Request to getAccessProjectCodes");
        return recUserProjectCodeRepository.findAccessRecProjectCodesByQuery(loginId, roleId, orgId);
    }

    public List<String> getSelectedProjectCodes(String userId){
        log.debug("Request to getSelectedProjectCodes");
        return recUserProjectCodeRepository.findSelectedProjectCodesByQuery(userId);
    }

    public void insertProjectCodes(RecUserProjectCodeDTO recUserProjectCodeDTO) {
        log.debug("Request to insertProjectCodes");
        recUserProjectCodeRepository.insertUserProjectCodesByQuery(recUserProjectCodeDTO);
    }

    public Page<RecUserProjectCodeQueryDTO> findByCriteria(RecUserProjectCodeCriteria criteria){
        if(criteria.getSortBy() != null && criteria.getSortBy().length == 0){
            criteria.setSortBy(new String[]{"USER_ID"});
        }
        log.debug("Request to getAllRecUser");
        final Page<RecUserProjectCodeQueryDTO> recUserProjectCodeQueryDTOS = recUserProjectCodeRepository.findUserByCriteria(criteria);
        return recUserProjectCodeQueryDTOS;
    }

    /**
     * Save a recUserProjectCode.
     *
     * @param recUserProjectCodeDTO the entity to save.
     * @return the persisted entity.
     */
    public RecUserProjectCodeDTO save(RecUserProjectCodeDTO recUserProjectCodeDTO) {
        log.debug("Request to save RecUserProjectCode : {}", recUserProjectCodeDTO);
        RecUserProjectCode recUserProjectCode = recUserProjectCodeMapper.toEntity(recUserProjectCodeDTO);
        recUserProjectCode = recUserProjectCodeRepository.save(recUserProjectCode);
        return recUserProjectCodeMapper.toDto(recUserProjectCode);
    }

    /**
     * Partially update a recUserProjectCode.
     *
     * @param recUserProjectCodeDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<RecUserProjectCodeDTO> partialUpdate(RecUserProjectCodeDTO recUserProjectCodeDTO) {
        log.debug("Request to partially update RecUserProjectCode : {}", recUserProjectCodeDTO);

        return recUserProjectCodeRepository
            .findById(recUserProjectCodeDTO.getUserId())
            .map(existingRecUserProjectCode -> {
                recUserProjectCodeMapper.partialUpdate(existingRecUserProjectCode, recUserProjectCodeDTO);

                return existingRecUserProjectCode;
            })
            .map(recUserProjectCodeRepository::save)
            .map(recUserProjectCodeMapper::toDto);
    }

    /**
     * Get all the recUserProjectCodes.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<RecUserProjectCodeDTO> findAll() {
        log.debug("Request to get all RecUserProjectCodes");
        return recUserProjectCodeRepository
            .findAll()
            .stream()
            .map(recUserProjectCodeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one recUserProjectCode by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<RecUserProjectCodeDTO> findOne(String id) {
        log.debug("Request to get RecUserProjectCode : {}", id);
        return recUserProjectCodeRepository.findById(id).map(recUserProjectCodeMapper::toDto);
    }

    /**
     * Delete the recUserProjectCode by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete RecUserProjectCode : {}", id);
        recUserProjectCodeRepository.deleteById(id);
    }
}
