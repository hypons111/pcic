package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.CmsComp;
import gov.pcc.pwc.domain.GeoQuestion;
import gov.pcc.pwc.repository.GeoQuestionRepository;
import gov.pcc.pwc.service.criteria.GeoQuestionCriteria;
import gov.pcc.pwc.service.dto.AdmSysCodeDTO;
import gov.pcc.pwc.service.dto.GeoMemberDTO;
import gov.pcc.pwc.service.dto.GeoQuestionDTO;
import gov.pcc.pwc.service.mapper.GeoQuestionMapper;
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
 * Service Implementation for managing {@link GeoQuestion}.
 */
@Service
@Transactional
public class GeoQuestionService {

    private final Logger log = LoggerFactory.getLogger(GeoQuestionService.class);

    private final GeoQuestionRepository geoQuestionRepository;

    private final GeoQuestionMapper geoQuestionMapper;

    public GeoQuestionService(GeoQuestionRepository geoQuestionRepository, GeoQuestionMapper geoQuestionMapper) {
        this.geoQuestionRepository = geoQuestionRepository;
        this.geoQuestionMapper = geoQuestionMapper;
    }

    /**
     * Save a geoQuestion.
     *
     * @param geoQuestionDTO the entity to save.
     * @return the persisted entity.
     */
    public GeoQuestionDTO save(GeoQuestionDTO geoQuestionDTO) {
        log.debug("Request to save GeoQuestion : {}", geoQuestionDTO);
        GeoQuestion geoQuestion = geoQuestionMapper.toEntity(geoQuestionDTO);
        geoQuestion = geoQuestionRepository.save(geoQuestion);
        return geoQuestionMapper.toDto(geoQuestion);
    }
//impl方法
    public GeoQuestionDTO insert(GeoQuestionDTO geoQuestionDTO) {
        log.debug("Request to save GeoQuestion : {}", geoQuestionDTO);
        geoQuestionRepository.insert(geoQuestionDTO);
        return geoQuestionDTO;
    }

    /**
     * Partially update a geoQuestion.
     *
     * @param geoQuestionDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<GeoQuestionDTO> partialUpdate(GeoQuestionDTO geoQuestionDTO) {
        log.debug("Request to partially update GeoQuestion : {}", geoQuestionDTO);

        return geoQuestionRepository
            .findById(geoQuestionDTO.getId())
            .map(existingGeoQuestion -> {
                geoQuestionMapper.partialUpdate(existingGeoQuestion, geoQuestionDTO);

                return existingGeoQuestion;
            })
            .map(geoQuestionRepository::save)
            .map(geoQuestionMapper::toDto);
    }

    /**
     * Get all the geoQuestions.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<GeoQuestionDTO> findAll() {
        log.debug("Request to get all GeoQuestions");
        return geoQuestionRepository.findAll().stream().map(geoQuestionMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one geoQuestion by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<GeoQuestionDTO> findOne(Long id) {
        log.debug("Request to get GeoQuestion : {}", id);
        return geoQuestionRepository.findById(id).map(geoQuestionMapper::toDto);
    }

    /**
     * Find the list of CmsCompDTO by criteria.
     *
     * @param criteria the criteria.
     * @return the list of CmsCompDTO.
     */
    @Transactional(readOnly = true)
    public Page<GeoQuestionDTO> findByCriteriaImpl(GeoQuestionCriteria criteria) {
        log.debug("Request to get all GeoQuestions by criteria");
        if (criteria.getSortBy() != null && criteria.getSortBy().length == 0) {
            criteria.setSortBy(new String[]{"id"});
        }
        return geoQuestionRepository.findAllByCriteriaImpl(criteria);
    }

    @Transactional(readOnly = true)
    public Page<GeoQuestionDTO> findByCriteria(GeoQuestionCriteria criteria) {
        if (criteria.getSortBy() != null && criteria.getSortBy().length == 0) {
            criteria.setSortBy(new String[] { "id" });
        }
        Page<GeoQuestion> geoQuestions = geoQuestionRepository.findAll(criteria, criteria.toPageable());
        return geoQuestions.map(geoQuestionMapper::toDto);
    }

    /**
     * Delete the geoQuestion by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete GeoQuestion : {}", id);
        geoQuestionRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<AdmSysCodeDTO> findAdmSysCodeByCriteriaImpl(GeoQuestionCriteria criteria) {
        return geoQuestionRepository.findAdmSysCodeByCriteriaImpl(criteria);
    }
    @Transactional(readOnly = true)
    public void updateAdmSysCodeByCriteriaImpl(GeoQuestionCriteria criteria) {
        geoQuestionRepository.updateAdmSysCodeByCriteriaImpl(criteria);
    }
    @Transactional(readOnly = true)
    public List<GeoMemberDTO> findGeoMemberIdByCriteriaImpl(GeoQuestionCriteria criteria) {
        return geoQuestionRepository.findGeoMemberIdByCriteriaImpl(criteria);
    }
}
