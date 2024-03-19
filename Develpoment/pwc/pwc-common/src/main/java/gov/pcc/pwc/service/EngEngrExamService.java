package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.EngEngrExam;
import gov.pcc.pwc.repository.EngEngrExamRepository;
import gov.pcc.pwc.service.criteria.EngEngrExamCriteria;
import gov.pcc.pwc.service.dto.EngEngrExamDTO;
import gov.pcc.pwc.service.mapper.EngEngrExamMapper;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link EngEngrExam}.
 */
@Service
@Transactional
public class EngEngrExamService {

    private final Logger log = LoggerFactory.getLogger(EngEngrExamService.class);

    private final EngEngrExamRepository engEngrExamRepository;

    private final EngEngrExamMapper engEngrExamMapper;

    public EngEngrExamService(EngEngrExamRepository engEngrExamRepository, EngEngrExamMapper engEngrExamMapper) {
        this.engEngrExamRepository = engEngrExamRepository;
        this.engEngrExamMapper = engEngrExamMapper;
    }

    /**
     * Save a engEngrExam.
     *
     * @param engEngrExamDTO the entity to save.
     * @return the persisted entity.
     */
    public EngEngrExamDTO save(EngEngrExamDTO engEngrExamDTO) {
        log.debug("Request to save EngEngrExam : {}", engEngrExamDTO);
        EngEngrExam engEngrExam = engEngrExamMapper.toEntity(engEngrExamDTO);
        engEngrExam = engEngrExamRepository.save(engEngrExam);
        return engEngrExamMapper.toDto(engEngrExam);
    }

    /**
     * Partially update a engEngrExam.
     *
     * @param engEngrExamDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<EngEngrExamDTO> partialUpdate(EngEngrExamDTO engEngrExamDTO) {
        log.debug("Request to partially update EngEngrExam : {}", engEngrExamDTO);

        return engEngrExamRepository
            .findById(engEngrExamDTO.getEngEngrExamId())
            .map(existingEngEngrExam -> {
                engEngrExamMapper.partialUpdate(existingEngEngrExam, engEngrExamDTO);

                return existingEngEngrExam;
            })
            .map(engEngrExamRepository::save)
            .map(engEngrExamMapper::toDto);
    }

    /**
     * Get all the engEngrExams.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<EngEngrExamDTO> findAll() {
        log.debug("Request to get all EngEngrExams");
        return engEngrExamRepository.findAll().stream().map(engEngrExamMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one engEngrExam by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EngEngrExamDTO> findOne(String id) {
        log.debug("Request to get EngEngrExam : {}", id);
        return engEngrExamRepository.findById(id).map(engEngrExamMapper::toDto);
    }

    /**
     * Delete the engEngrExam by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete EngEngrExam : {}", id);
        engEngrExamRepository.deleteById(id);
    }

    /**
     * Get one engEngrExam by idno and subject.
     *
     * @param idno the idno of the entity.
     * @param subject the subject of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public EngEngrExamDTO findOne(String idno, String subject) {
        log.debug("Request to get EngEngrExam : {}, {}", idno, subject);
        return engEngrExamRepository.findByIdnoAndSubject(idno, subject);
    }

    @Transactional(readOnly = true)
    public Page<EngEngrExamDTO> findAll(@NotNull EngEngrExamCriteria criteria) {
        if (criteria.getSortBy() != null && criteria.getSortBy().length == 0) {
            criteria.setSortBy(new String[] { "importDate" });
            criteria.setSortDirection(Sort.Direction.ASC);
        }
//        if(criteria.getImportDate()!=null){
//            criteria.setImportDate(criteria.getImportDate());
//        }
        Page<EngEngrExam> engEngrExam = engEngrExamRepository.findAll(criteria,
                criteria.toPageable());

//        System.out.println(criteria.getImportDate()+"\tengEngrExam789");
//        System.out.println(criteria.getImportDate().getYear()-1911+"\tengEngrExam456");
//        System.out.println(engEngrExam.getContent().get(5).getImportDate().getYear()-1911+"\tengEngrExam123");

        return engEngrExam.map(engEngrExamMapper::toDto);
    }
}
