package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.AdmSysCode;
import gov.pcc.pwc.domain.EngCase;
import gov.pcc.pwc.repository.EngCaseRepository;
import gov.pcc.pwc.service.dto.AdmSysCodeDTO;
import gov.pcc.pwc.service.dto.EngCaseDTO;
import gov.pcc.pwc.service.mapper.EngCaseMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link EngCase}.
 */
@Service
@Transactional
public class EngCaseService {

    private final Logger log = LoggerFactory.getLogger(EngCaseService.class);

    @Autowired
    private EngCaseRepository engCaseRepository;

    @Autowired
    private EngCaseMapper engCaseMapper;

    public EngCaseService() {
    }

//    // 後端回傳工程種類選單
//    @Transactional
//    public List<AdmSysCodeDTO> findAdmSysCode() {
//        List<AdmSysCodeDTO> list = engCaseRepository.findAdmSysCode();
//        log.debug("EngCaseService-getProjectTypes" + list);
//        return list;
//    }

    /**
     * Save a engCase.
     *
     * @param engCaseDTO the entity to save.
     * @return the persisted entity.
     */
    public EngCaseDTO save(EngCaseDTO engCaseDTO) {
        log.debug("Request to save EngCase : {}", engCaseDTO);
        EngCase engCase = engCaseMapper.toEntity(engCaseDTO);
        engCase = engCaseRepository.save(engCase);
        return engCaseMapper.toDto(engCase);
    }

    /**
     * Partially update a engCase.
     *
     * @param engCaseDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<EngCaseDTO> partialUpdate(EngCaseDTO engCaseDTO) {
        log.debug("Request to partially update EngCase : {}", engCaseDTO);

        return engCaseRepository
            .findById(engCaseDTO.getEngCaseId())
            .map(existingEngCase -> {
                engCaseMapper.partialUpdate(existingEngCase, engCaseDTO);

                return existingEngCase;
            })
            .map(engCaseRepository::save)
            .map(engCaseMapper::toDto);
    }

    /**
     * Get all the engCases.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<EngCaseDTO> findAll() {
        log.debug("Request to get all EngCases");
        return engCaseRepository.findAll().stream().map(engCaseMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one engCase by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EngCaseDTO> findOne(String id) {
        log.debug("Request to get EngCase : {}", id);
        return engCaseRepository.findById(id).map(engCaseMapper::toDto);
    }

    /**
     * Delete the engCase by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete EngCase : {}", id);
        engCaseRepository.deleteById(id);
    }
}
