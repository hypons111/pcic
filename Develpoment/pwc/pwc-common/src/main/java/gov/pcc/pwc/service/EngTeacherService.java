package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.EngTeacher;
import gov.pcc.pwc.repository.EngTeacherRepository;
import gov.pcc.pwc.service.dto.EngTeacherDTO;
import gov.pcc.pwc.service.genSerialNumber.SerialNumGenerator;
import gov.pcc.pwc.service.genSerialNumber.enums.EngGenNumberVariableEnum;
import gov.pcc.pwc.service.genSerialNumber.enums.ModuleEnum;
import gov.pcc.pwc.service.genSerialNumber.errors.TypeVariableNotMatchException;
import gov.pcc.pwc.service.genSerialNumber.parameter.SerialNumVariableParam;
import gov.pcc.pwc.service.mapper.EngTeacherMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link EngTeacher}.
 */
@Service
@Transactional
public class EngTeacherService {

    private final Logger log = LoggerFactory.getLogger(EngTeacherService.class);

    private final EngTeacherRepository engTeacherRepository;

    private final EngTeacherMapper engTeacherMapper;

    private final SerialNumGenerator serialNumGenerator;

    public EngTeacherService(EngTeacherRepository engTeacherRepository, EngTeacherMapper engTeacherMapper, SerialNumGenerator serialNumGenerator) {
        this.engTeacherRepository = engTeacherRepository;
        this.engTeacherMapper = engTeacherMapper;
        this.serialNumGenerator = serialNumGenerator;
    }

    /**
     * Save a engTeacher.
     *
     * @param engTeacherDTO the entity to save.
     * @return the persisted entity.
     */
    public EngTeacherDTO save(EngTeacherDTO engTeacherDTO) {
        log.debug("Request to save EngTeacher : {}", engTeacherDTO);
        EngTeacher engTeacher = engTeacherMapper.toEntity(engTeacherDTO);
        engTeacher = engTeacherRepository.save(engTeacher);
        return engTeacherMapper.toDto(engTeacher);
    }

    /**
     * Partially update a engTeacher.
     *
     * @param engTeacherDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<EngTeacherDTO> partialUpdate(EngTeacherDTO engTeacherDTO) {
        log.debug("Request to partially update EngTeacher : {}", engTeacherDTO);

        return engTeacherRepository
            .findById(engTeacherDTO.getEngTeacherId())
            .map(existingEngTeacher -> {
                engTeacherMapper.partialUpdate(existingEngTeacher, engTeacherDTO);

                return existingEngTeacher;
            })
            .map(engTeacherRepository::save)
            .map(engTeacherMapper::toDto);
    }

    /**
     * Get all the engTeachers.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<EngTeacherDTO> findAll() {
        log.debug("Request to get all EngTeachers");
        return engTeacherRepository.findAll().stream().map(engTeacherMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one engTeacher by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EngTeacherDTO> findOne(String id) {
        log.debug("Request to get EngTeacher : {}", id);
        return engTeacherRepository.findById(id).map(engTeacherMapper::toDto);
    }

    /**
     * Delete the engTeacher by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete EngTeacher : {}", id);
        engTeacherRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<EngTeacherDTO> findByTeacherNameLike(String teacherName) {
        log.debug("Request to get EngTeachers by teacherName : {}", teacherName);
        return engTeacherRepository.findByTeacherNameContaining(teacherName)
                .stream()
                .map(engTeacherMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    public Map<String, String> getNewEngTeacherId() throws TypeVariableNotMatchException {
        return Map.of("engTeacherId", serialNumGenerator
                .setModule(ModuleEnum.ENG)
                .setVariable(new SerialNumVariableParam(EngGenNumberVariableEnum.TEACHER.getVariable()))
                .getGeneratedNumber());
    }
}
