package gov.pcc.pwc.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gov.pcc.pwc.domain.CmsComp;
import gov.pcc.pwc.repository.CmsCompRepository;
import gov.pcc.pwc.service.criteria.CmsCompCriteria;
import gov.pcc.pwc.service.dto.CmsCompDTO;
import gov.pcc.pwc.service.mapper.CmsCompMapper;

/**
 * Service Implementation for managing {@link CmsComp}.
 */
@Service
@Transactional
public class CmsCompService {

    private final Logger log = LoggerFactory.getLogger(CmsCompService.class);

    private final CmsCompRepository cmsCompRepository;

    private final CmsCompMapper cmsCompMapper;

    public CmsCompService(CmsCompRepository cmsCompRepository, CmsCompMapper cmsCompMapper) {
        this.cmsCompRepository = cmsCompRepository;
        this.cmsCompMapper = cmsCompMapper;
    }

    /**
     * Save a cmsComp.
     *
     * @param cmsCompDTO the entity to save.
     * @return the persisted entity.
     */
    public CmsCompDTO save(CmsCompDTO cmsCompDTO) {
        log.debug("Request to save CmsComp : {}", cmsCompDTO);
        CmsComp cmsComp = cmsCompMapper.toEntity(cmsCompDTO);
        cmsComp = cmsCompRepository.save(cmsComp);
        return cmsCompMapper.toDto(cmsComp);
    }

    /**
     * Partially update a cmsComp.
     *
     * @param cmsCompDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<CmsCompDTO> partialUpdate(CmsCompDTO cmsCompDTO) {
        log.debug("Request to partially update CmsComp : {}", cmsCompDTO);

        return cmsCompRepository
            .findById(cmsCompDTO.getId())
            .map(
                existingCmsComp -> {
                    cmsCompMapper.partialUpdate(existingCmsComp, cmsCompDTO);
                    return existingCmsComp;
                }
            )
            .map(cmsCompRepository::save)
            .map(cmsCompMapper::toDto);
    }

    /**
     * Get all the cmsComps.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<CmsCompDTO> findAll() {
        log.debug("Request to get all CmsComps");
        return cmsCompRepository.findAll().stream().map(cmsCompMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one cmsComp by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CmsCompDTO> findOne(String id) {
        log.debug("Request to get CmsComp : {}", id);
        return cmsCompRepository.findById(id).map(cmsCompMapper::toDto);
    }

    /**
     * Find the list of CmsCompDTO by criteria.
     *
     * @param criteria the criteria.
     * @return the list of CmsCompDTO.
     */
    @Transactional(readOnly = true)
    public Page<CmsCompDTO> findByCriteria(CmsCompCriteria criteria) {
        if (criteria.getSortBy() != null && criteria.getSortBy().length == 0) {
            criteria.setSortBy(new String[] { "id" });
        }
        final Page<CmsComp> cmsComps = cmsCompRepository.findAll(criteria, criteria.toPageable());
        return cmsComps.map(cmsCompMapper::toDto);
    }

    @Transactional(readOnly = true)
    public List<CmsCompDTO> findByCriteria(CmsCompDTO criteria) {
        return cmsCompRepository.findAllByCriteria(criteria);
    }

    /**
     * Delete the cmsComp by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete CmsComp : {}", id);
        cmsCompRepository.deleteById(id);
    }
}
