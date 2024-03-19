package gov.pcc.pwc.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gov.pcc.pwc.domain.CstSubject;
import gov.pcc.pwc.repository.CstSubjectRepository;
import gov.pcc.pwc.service.dto.CstSubjectDTO;
import gov.pcc.pwc.service.mapper.CstSubjectMapper;

/**
 * Service Implementation for managing {@link CstSubject}.
 */
@Service
@Transactional
public class CstSubjectService {

    private final Logger log = LoggerFactory.getLogger(CstSubjectService.class);

    private final CstSubjectRepository cstSubjectRepository;

    private final CstSubjectMapper cstSubjectMapper;

    public CstSubjectService(CstSubjectRepository cstSubjectRepository, CstSubjectMapper cstSubjectMapper) {
        this.cstSubjectRepository = cstSubjectRepository;
        this.cstSubjectMapper = cstSubjectMapper;
    }

    /**
     * Save a cstSubject.
     *
     * @param cstSubjectDTO the entity to save.
     * @return the persisted entity.
     */
    public CstSubjectDTO save(CstSubjectDTO cstSubjectDTO) {
        log.debug("Request to save CstSubject : {}", cstSubjectDTO);
        CstSubject cstSubject = cstSubjectMapper.toEntity(cstSubjectDTO);
        cstSubject = cstSubjectRepository.save(cstSubject);
        return cstSubjectMapper.toDto(cstSubject);
    }

    /**
     * Partially update a cstSubject.
     *
     * @param cstSubjectDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<CstSubjectDTO> partialUpdate(CstSubjectDTO cstSubjectDTO) {
        log.debug("Request to partially update CstSubject : {}", cstSubjectDTO);

        return cstSubjectRepository
            .findById(cstSubjectDTO.getId())
            .map(
                existingCstSubject -> {
                    cstSubjectMapper.partialUpdate(existingCstSubject, cstSubjectDTO);
                    return existingCstSubject;
                }
            )
            .map(cstSubjectRepository::save)
            .map(cstSubjectMapper::toDto);
    }

    /**
     * Get all the cstSubjects.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<CstSubjectDTO> findAll() {
        log.debug("Request to get all CstSubjects");
        return cstSubjectRepository.findAll().stream().map(cstSubjectMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one cstSubject by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CstSubjectDTO> findOne(Long id) {
        log.debug("Request to get CstSubject : {}", id);
        return cstSubjectRepository.findById(id).map(cstSubjectMapper::toDto);
    }

    /**
     * Delete the cstSubject by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CstSubject : {}", id);
        cstSubjectRepository.deleteById(id);
    }
}
