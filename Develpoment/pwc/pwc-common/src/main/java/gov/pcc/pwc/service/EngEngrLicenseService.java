package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.EngEngrLicense;
import gov.pcc.pwc.domain.EngMember;
import gov.pcc.pwc.repository.EngEngrLicenseRepository;
import gov.pcc.pwc.service.criteria.EngEngrLicenseCriteria;
import gov.pcc.pwc.service.criteria.EngMemberCriteria;
import gov.pcc.pwc.service.dto.EngEngrLicenseDTO;
import gov.pcc.pwc.service.dto.EngMemberDTO;
import gov.pcc.pwc.service.mapper.EngEngrLicenseMapper;
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
 * Service Implementation for managing {@link EngEngrLicense}.
 */
@Service
@Transactional
public class EngEngrLicenseService {

    private final Logger log = LoggerFactory.getLogger(EngEngrLicenseService.class);

    private final EngEngrLicenseRepository engEngrLicenseRepository;

    private final EngEngrLicenseMapper engEngrLicenseMapper;

    public EngEngrLicenseService(EngEngrLicenseRepository engEngrLicenseRepository, EngEngrLicenseMapper engEngrLicenseMapper) {
        this.engEngrLicenseRepository = engEngrLicenseRepository;
        this.engEngrLicenseMapper = engEngrLicenseMapper;
    }

    /**
     * Save a engEngrLicense.
     *
     * @param engEngrLicenseDTO the entity to save.
     * @return the persisted entity.
     */
    public EngEngrLicenseDTO save(EngEngrLicenseDTO engEngrLicenseDTO) {
        log.debug("Request to save EngEngrLicense : {}", engEngrLicenseDTO);
        EngEngrLicense engEngrLicense = engEngrLicenseMapper.toEntity(engEngrLicenseDTO);
        engEngrLicense = engEngrLicenseRepository.save(engEngrLicense);
        return engEngrLicenseMapper.toDto(engEngrLicense);
    }

    /**
     * Partially update a engEngrLicense.
     *
     * @param engEngrLicenseDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<EngEngrLicenseDTO> partialUpdate(EngEngrLicenseDTO engEngrLicenseDTO) {
        log.debug("Request to partially update EngEngrLicense : {}", engEngrLicenseDTO);

        return engEngrLicenseRepository
            .findById(engEngrLicenseDTO.getEngEngrLicenseId())
            .map(existingEngEngrLicense -> {
                engEngrLicenseMapper.partialUpdate(existingEngEngrLicense, engEngrLicenseDTO);

                return existingEngEngrLicense;
            })
            .map(engEngrLicenseRepository::save)
            .map(engEngrLicenseMapper::toDto);
    }

    /**
     * Get all the engEngrLicenses.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<EngEngrLicenseDTO> findAll() {
        log.debug("Request to get all EngEngrLicenses");
        return engEngrLicenseRepository
            .findAll()
            .stream()
            .map(engEngrLicenseMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one engEngrLicense by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EngEngrLicenseDTO> findOne(String id) {
        log.debug("Request to get EngEngrLicense : {}", id);
        return engEngrLicenseRepository.findById(id).map(engEngrLicenseMapper::toDto);
    }

    /**
     * Delete the engEngrLicense by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete EngEngrLicense : {}", id);
        engEngrLicenseRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<EngEngrLicenseDTO> findByCriteriaImpl(EngEngrLicenseCriteria criteria){
        return engEngrLicenseRepository.findByCriteriaImpl(criteria);
    }

    @Transactional(readOnly = true)
    public Page<EngEngrLicenseDTO> findByCriteriaImpl2(@NotNull EngEngrLicenseCriteria criteria){
        if (criteria.getSortBy() != null && criteria.getSortBy().length == 0) {
            criteria.setSortBy(new String[] { "ENG_ENGR_LICENSE_NO" });
            criteria.setSortDirection(Sort.Direction.ASC);
        }
        return engEngrLicenseRepository.findByCriteriaImpl2(criteria);
    }

//    @Transactional(readOnly = true)
//    public Page<EngEngrLicenseDTO> findByCriteria(EngEngrLicenseCriteria criteria) {
//        if (criteria.getSortBy() != null && criteria.getSortBy().length == 0) {
//            criteria.setSortBy(new String[] { "engEngrLicenseNo" });
//        }
//
//        final Page<EngEngrLicense> engEngrLicense = engEngrLicenseRepository.findAll(criteria, criteria.toPageable());
//        return engEngrLicense.map(engEngrLicenseMapper::toDto);
//    }
}
