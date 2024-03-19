package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.EngCourseDtlTeacher;
import gov.pcc.pwc.domain.EngCourseDtlTeacherPK;
import gov.pcc.pwc.repository.EngCourseDtlTeacherRepository;
import gov.pcc.pwc.service.dto.EngCourseDtlTeacherDTO;
import gov.pcc.pwc.service.mapper.EngCourseDtlTeacherMapper;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link EngCourseDtlTeacher}.
 */
@Service
@Transactional
public class EngCourseDtlTeacherService {

    private final Logger log = LoggerFactory.getLogger(EngCourseDtlTeacherService.class);

    private final EngCourseDtlTeacherRepository engCourseDtlTeacherRepository;

    private final EngCourseDtlTeacherMapper engCourseDtlTeacherMapper;

    public EngCourseDtlTeacherService(
        EngCourseDtlTeacherRepository engCourseDtlTeacherRepository,
        EngCourseDtlTeacherMapper engCourseDtlTeacherMapper
    ) {
        this.engCourseDtlTeacherRepository = engCourseDtlTeacherRepository;
        this.engCourseDtlTeacherMapper = engCourseDtlTeacherMapper;
    }

    /**
     * Save a engCourseDtlTeacher.
     *
     * @param engCourseDtlTeacherDTO the entity to save.
     * @return the persisted entity.
     */
    public EngCourseDtlTeacherDTO save(EngCourseDtlTeacherDTO engCourseDtlTeacherDTO) {
        log.debug("Request to save EngCourseDtlTeacher : {}", engCourseDtlTeacherDTO);
        EngCourseDtlTeacher engCourseDtlTeacher = engCourseDtlTeacherMapper.toEntity(engCourseDtlTeacherDTO);
        engCourseDtlTeacher = engCourseDtlTeacherRepository.save(engCourseDtlTeacher);
        return engCourseDtlTeacherMapper.toDto(engCourseDtlTeacher);
    }

    /**
     * Partially update a engCourseDtlTeacher.
     *
     * @param engCourseDtlTeacherDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<EngCourseDtlTeacherDTO> partialUpdate(EngCourseDtlTeacherDTO engCourseDtlTeacherDTO) {
        log.debug("Request to partially update EngCourseDtlTeacher : {}", engCourseDtlTeacherDTO);

        return engCourseDtlTeacherRepository
            .findById(
                    new EngCourseDtlTeacherPK(
                            engCourseDtlTeacherDTO.getEngCourseId(),
                            engCourseDtlTeacherDTO.getEngCourseDetailId(),
                            engCourseDtlTeacherDTO.getEngCourseDetailTeacherId()
                    )
            )
            .map(existingEngCourseDtlTeacher -> {
                engCourseDtlTeacherMapper.partialUpdate(existingEngCourseDtlTeacher, engCourseDtlTeacherDTO);

                return existingEngCourseDtlTeacher;
            })
            .map(engCourseDtlTeacherRepository::save)
            .map(engCourseDtlTeacherMapper::toDto);
    }

    /**
     * Get all the engCourseDtlTeachers.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<EngCourseDtlTeacherDTO> findAll() {
        log.debug("Request to get all EngCourseDtlTeachers");
        return engCourseDtlTeacherRepository
            .findAll()
            .stream()
            .map(engCourseDtlTeacherMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one engCourseDtlTeacher by id.
     *
     * @param engCourseId the id of the entity.
     * @param engCourseDetailId the id of the entity.
     * @param engCourseDetailTeacherId the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EngCourseDtlTeacherDTO> findOne(String engCourseId, Long engCourseDetailId, Long engCourseDetailTeacherId) {
        EngCourseDtlTeacherPK pk = new EngCourseDtlTeacherPK(engCourseId, engCourseDetailId, engCourseDetailTeacherId);
        log.debug("Request to get EngCourseDtlTeacher : {}", pk);
        return engCourseDtlTeacherRepository.findById(pk).map(engCourseDtlTeacherMapper::toDto);
    }

    /**
     * Delete the engCourseDtlTeacher by id.
     *
     * @param engCourseId the id of the entity.
     * @param engCourseDetailId the id of the entity.
     * @param engCourseDetailTeacherId the id of the entity.
     */
    public void delete(String engCourseId, Long engCourseDetailId, Long engCourseDetailTeacherId) {
        EngCourseDtlTeacherPK pk = new EngCourseDtlTeacherPK(engCourseId, engCourseDetailId, engCourseDetailTeacherId);
        log.debug("Request to delete EngCourseDtlTeacher : {}", pk);
        engCourseDtlTeacherRepository.deleteById(pk);
    }
}
