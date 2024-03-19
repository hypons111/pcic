package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.EngCourse;
import gov.pcc.pwc.repository.EngCourseDtlRepository;
import gov.pcc.pwc.repository.EngCourseDtlTeacherRepository;
import gov.pcc.pwc.repository.EngCourseRepository;
import gov.pcc.pwc.repository.EngTeacherRepository;
import gov.pcc.pwc.service.criteria.EngCourseCriteria;
import gov.pcc.pwc.service.dto.Eng0401DTO;
import gov.pcc.pwc.service.dto.EngCourseDTO;
import gov.pcc.pwc.service.dto.EngCourseDtlDTO;
import gov.pcc.pwc.service.dto.EngCourseDtlTeacherDTO;
import gov.pcc.pwc.service.mapper.EngCourseDtlMapper;
import gov.pcc.pwc.service.mapper.EngCourseDtlTeacherMapper;
import gov.pcc.pwc.service.mapper.EngCourseMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import gov.pcc.pwc.service.mapper.EngTeacherMapper;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link EngCourse}.
 */
@Service
@Transactional
public class EngCourseService {

    private final Logger log = LoggerFactory.getLogger(EngCourseService.class);

    private final EngCourseRepository engCourseRepository;
    private final EngCourseDtlRepository engCourseDtlRepository;
    private final EngCourseDtlTeacherRepository engCourseDtlTeacherRepository;
    private final EngTeacherRepository engTeacherRepository;

    private final EngCourseMapper engCourseMapper;
    private final EngCourseDtlMapper engCourseDtlMapper;
    private final EngCourseDtlTeacherMapper engCourseDtlTeacherMapper;
    private final EngTeacherMapper engTeacherMapper;

    public EngCourseService(
            EngCourseRepository engCourseRepository,
            EngCourseDtlRepository engCourseDtlRepository,
            EngCourseDtlTeacherRepository engCourseDtlTeacherRepository,
            EngTeacherRepository engTeacherRepository,
            EngCourseMapper engCourseMapper,
            EngCourseDtlMapper engCourseDtlMapper,
            EngCourseDtlTeacherMapper engCourseDtlTeacherMapper,
            EngTeacherMapper engTeacherMapper
    ) {
        this.engCourseRepository = engCourseRepository;
        this.engCourseDtlRepository = engCourseDtlRepository;
        this.engCourseDtlTeacherRepository = engCourseDtlTeacherRepository;
        this.engTeacherRepository = engTeacherRepository;
        this.engCourseMapper = engCourseMapper;
        this.engCourseDtlMapper = engCourseDtlMapper;
        this.engCourseDtlTeacherMapper = engCourseDtlTeacherMapper;
        this.engTeacherMapper = engTeacherMapper;
    }

    /**
     * Save a engCourse.
     *
     * @param engCourseDTO the entity to save.
     * @return the persisted entity.
     */
    public EngCourseDTO save(EngCourseDTO engCourseDTO) {
        log.debug("Request to save EngCourse : {}", engCourseDTO);
        EngCourse engCourse = engCourseMapper.toEntity(engCourseDTO);
        engCourse = engCourseRepository.save(engCourse);
        return engCourseMapper.toDto(engCourse);
    }

    /**
     * Partially update a engCourse.
     *
     * @param engCourseDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<EngCourseDTO> partialUpdate(EngCourseDTO engCourseDTO) {
        log.debug("Request to partially update EngCourse : {}", engCourseDTO);

        return engCourseRepository
            .findById(engCourseDTO.getEngCourseId())
            .map(existingEngCourse -> {
                engCourseMapper.partialUpdate(existingEngCourse, engCourseDTO);

                return existingEngCourse;
            })
            .map(engCourseRepository::save)
            .map(engCourseMapper::toDto);
    }

    /**
     * Get all the engCourses.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<EngCourseDTO> findAll() {
        log.debug("Request to get all EngCourses");
        return engCourseRepository.findAll().stream().map(engCourseMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one engCourse by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EngCourseDTO> findOne(String id) {
        log.debug("Request to get EngCourse : {}", id);
        return engCourseRepository.findById(id).map(engCourseMapper::toDto);
    }

    /**
     * Delete the engCourse by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete EngCourse : {}", id);
        engCourseRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Page<EngCourseDTO> findAll(@NotNull EngCourseCriteria criteria) {
        log.debug("Request to get EngCourses by criteria");
        if (criteria.getSortBy() != null && criteria.getSortBy().length == 0) {
            criteria.setSortBy(new String[] { "applyDate", "dateStart" });
            criteria.setSortDirection(Sort.Direction.DESC);
        }
        return engCourseRepository.findAllByCriteria(criteria);
    }

    @Transactional(readOnly = true)
    public Eng0401DTO findEng0401(String engCourseId) {
        log.debug("Request to get Eng0401 : {}", engCourseId);

        EngCourseDTO engCourseDTO = engCourseRepository.findDTOById(engCourseId);

//        List<EngCourseDtlDTO> engCourseDtlDTOList = engCourseDtlRepository
//                .findByEngCourseId(engCourseId);
        List<EngCourseDtlDTO> engCourseDtlDTOList = engCourseDtlRepository
                .findByEngCourseId(engCourseId)
                .stream()
                .map(engCourseDtlMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));

        List<EngCourseDtlTeacherDTO> engCourseDtlTeacherDTOList = engCourseDtlTeacherRepository
                .findByEngCourseId(engCourseId)
                .stream()
                .map(engCourseDtlTeacherMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));

        Eng0401DTO eng0401DTO = new Eng0401DTO();
        eng0401DTO.setEngCourseDTO(engCourseDTO);
        eng0401DTO.setEngCourseDtlDTOList(engCourseDtlDTOList);
        eng0401DTO.setEngCourseDtlTeacherDTOList(engCourseDtlTeacherDTOList);
        return eng0401DTO;
    }
}
