package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.EctPublic;
import gov.pcc.pwc.repository.EctPublicRepository;
import gov.pcc.pwc.service.dto.EctPublicDTO;
import gov.pcc.pwc.service.criteria.ECT0101R2Criteria;
import gov.pcc.pwc.service.criteria.ECT0101R2QueryCriteria;
import gov.pcc.pwc.service.mapper.EctPublicMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

/**
 * Service Implementation for managing {@link EctPublic}.
 */
@Service
@Transactional
public class EctPublicService {

    private final Logger log = LoggerFactory.getLogger(EctPublicService.class);

    private final EctPublicRepository ectPublicRepository;

    private final EctPublicMapper ectPublicMapper;

    public EctPublicService(EctPublicRepository ectPublicRepository, EctPublicMapper ectPublicMapper) {
        this.ectPublicRepository = ectPublicRepository;
        this.ectPublicMapper = ectPublicMapper;
    }

    @Transactional(readOnly = true)
	public Map<String, Object> getECT0101R2(@NotNull ECT0101R2QueryCriteria criteria) {
        final Map<String, Object> rs = ectPublicRepository.getECT0101R2(criteria);
        log.debug("service-ect0101r2" + rs.toString());
        return rs;
    }

    @Transactional(readOnly = true)
    public Map<String, Object> applyect(MultipartHttpServletRequest request) {
        final Map<String, Object> rs = ectPublicRepository.applyect(request);
        log.debug("service-applyect" + rs.toString());
        return rs;
    }

    @Transactional(readOnly = true)
    public Map<String, Object> queryect(MultipartHttpServletRequest request) {
        final Map<String, Object> rs = ectPublicRepository.queryect(request);
        log.debug("service-queryect" + rs.toString());
        return rs;
    }

    @Transactional(readOnly = true)
    public Map<String, Object> revokeect(MultipartHttpServletRequest request) {
        final Map<String, Object> rs = ectPublicRepository.revokeect(request);
        log.debug("service-revokeect" + rs.toString());
        return rs;
    }

    /**
     * Save a ectPublic.
     *
     * @param ectPublicDTO the entity to save.
     * @return the persisted entity.
     */
    public EctPublicDTO save(EctPublicDTO ectPublicDTO) {
        log.debug("Request to save EctPublic : {}", ectPublicDTO);
        EctPublic ectPublic = ectPublicMapper.toEntity(ectPublicDTO);
        ectPublic = ectPublicRepository.save(ectPublic);
        return ectPublicMapper.toDto(ectPublic);
    }

    /**
     * Partially update a ectPublic.
     *
     * @param ectPublicDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<EctPublicDTO> partialUpdate(EctPublicDTO ectPublicDTO) {
        log.debug("Request to partially update EctPublic : {}", ectPublicDTO);

        return ectPublicRepository
            .findById(ectPublicDTO.getEctPublishId())
            .map(existingEctPublic -> {
                ectPublicMapper.partialUpdate(existingEctPublic, ectPublicDTO);

                return existingEctPublic;
            })
            .map(ectPublicRepository::save)
            .map(ectPublicMapper::toDto);
    }

    /**
     * Get all the ectPublics.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<EctPublicDTO> findAll() {
        log.debug("Request to get all EctPublics");
        return ectPublicRepository.findAll().stream().map(ectPublicMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one ectPublic by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EctPublicDTO> findOne(Long id) {
        log.debug("Request to get EctPublic : {}", id);
        return ectPublicRepository.findById(id).map(ectPublicMapper::toDto);
    }

    /**
     * Delete the ectPublic by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete EctPublic : {}", id);
        ectPublicRepository.deleteById(id);
    }
}
