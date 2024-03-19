package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.EngCourseDtl;
import gov.pcc.pwc.domain.EngCourseDtlPK;
import gov.pcc.pwc.repository.EngCourseDtlRepository;
import gov.pcc.pwc.service.dto.EngCourseDtlDTO;
import gov.pcc.pwc.service.mapper.EngCourseDtlMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link EngCourseDtl}.
 */
@Service
@Transactional
public class EngCourseDtlService {

    private final Logger log = LoggerFactory.getLogger(EngCourseDtlService.class);

    private final EngCourseDtlRepository engCourseDtlRepository;

    private final EngCourseDtlMapper engCourseDtlMapper;

    public EngCourseDtlService(EngCourseDtlRepository engCourseDtlRepository, EngCourseDtlMapper engCourseDtlMapper) {
        this.engCourseDtlRepository = engCourseDtlRepository;
        this.engCourseDtlMapper = engCourseDtlMapper;
    }

    /**
     * Save a engCourseDtl.
     *
     * @param engCourseDtlDTO the entity to save.
     * @return the persisted entity.
     */
    public EngCourseDtlDTO save(EngCourseDtlDTO engCourseDtlDTO) {
        log.debug("Request to save EngCourseDtl : {}", engCourseDtlDTO);
        EngCourseDtl engCourseDtl = engCourseDtlMapper.toEntity(engCourseDtlDTO);
        engCourseDtl = engCourseDtlRepository.save(engCourseDtl);
        return engCourseDtlMapper.toDto(engCourseDtl);
    }

    /**
     * Partially update a engCourseDtl.
     *
     * @param engCourseDtlDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<EngCourseDtlDTO> partialUpdate(EngCourseDtlDTO engCourseDtlDTO) {
        log.debug("Request to partially update EngCourseDtl : {}", engCourseDtlDTO);

        return engCourseDtlRepository
            .findById(new EngCourseDtlPK(engCourseDtlDTO.getEngCourseId(), engCourseDtlDTO.getEngCourseDetailId()))
            .map(existingEngCourseDtl -> {
                engCourseDtlMapper.partialUpdate(existingEngCourseDtl, engCourseDtlDTO);

                return existingEngCourseDtl;
            })
            .map(engCourseDtlRepository::save)
            .map(engCourseDtlMapper::toDto);
    }

    /**
     * Get all the engCourseDtls.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<EngCourseDtlDTO> findAll() {
        log.debug("Request to get all EngCourseDtls");
        return engCourseDtlRepository.findAll().stream().map(engCourseDtlMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one engCourseDtl by id.
     *
     * @param engCourseId the id of the entity.
     * @param engCourseDetailId the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EngCourseDtlDTO> findOne(String engCourseId, Long engCourseDetailId) {
        EngCourseDtlPK pk = new EngCourseDtlPK(engCourseId, engCourseDetailId);
        log.debug("Request to get EngCourseDtl : {}", pk);
        return engCourseDtlRepository.findById(pk).map(engCourseDtlMapper::toDto);
    }

    /**
     * Delete the engCourseDtl by id.
     *
     * @param engCourseId the id of the entity.
     * @param engCourseDetailId the id of the entity.
     */
    public void delete(String engCourseId, Long engCourseDetailId) {
        EngCourseDtlPK pk = new EngCourseDtlPK(engCourseId, engCourseDetailId);
        log.debug("Request to delete EngCourseDtl : {}", pk);
        engCourseDtlRepository.deleteById(pk);
    }
}
