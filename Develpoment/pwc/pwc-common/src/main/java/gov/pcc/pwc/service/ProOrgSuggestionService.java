package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.ProOrgSuggestion;
import gov.pcc.pwc.repository.ProOrgSuggestionRepository;
import gov.pcc.pwc.service.criteria.ProOrgSuggestionHistoryCriteria;
import gov.pcc.pwc.service.criteria.ProOrgSuggestionCriteria;
import gov.pcc.pwc.service.dto.ComStatusSearchDTO;
import gov.pcc.pwc.service.dto.ProOrgSuggestionDTO;
import gov.pcc.pwc.service.dto.ProOrgSuggestionHistoryDTO;
import gov.pcc.pwc.service.mapper.ProOrgSuggestionMapper;

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
 * Service Implementation for managing {@link ProOrgSuggestion}.
 */
@Service
@Transactional
public class ProOrgSuggestionService {

    private final Logger log = LoggerFactory.getLogger(ProOrgSuggestionService.class);

    private final ProOrgSuggestionRepository proOrgSuggestionRepository;

    private final ProOrgSuggestionMapper proOrgSuggestionMapper;

    public ProOrgSuggestionService(ProOrgSuggestionRepository proOrgSuggestionRepository, ProOrgSuggestionMapper proOrgSuggestionMapper) {
        this.proOrgSuggestionRepository = proOrgSuggestionRepository;
        this.proOrgSuggestionMapper = proOrgSuggestionMapper;
    }

    /**
     * Save a proOrgSuggestion.
     *
     * @param proOrgSuggestionDTO the entity to save.
     * @return the persisted entity.
     */
    public ProOrgSuggestionDTO save(ProOrgSuggestionDTO proOrgSuggestionDTO) {
        log.debug("Request to save ProOrgSuggestion : {}", proOrgSuggestionDTO);
        ProOrgSuggestion proOrgSuggestion = proOrgSuggestionMapper.toEntity(proOrgSuggestionDTO);
        proOrgSuggestion = proOrgSuggestionRepository.save(proOrgSuggestion);
        return proOrgSuggestionMapper.toDto(proOrgSuggestion);
    }

    /**
     * Partially update a proOrgSuggestion.
     *
     * @param proOrgSuggestionDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ProOrgSuggestionDTO> partialUpdate(ProOrgSuggestionDTO proOrgSuggestionDTO) {
        log.debug("Request to partially update ProOrgSuggestion : {}", proOrgSuggestionDTO);

        return proOrgSuggestionRepository
                .findById(proOrgSuggestionDTO.getProOrgSuggestionId())
                .map(existingProOrgSuggestion -> {
                    proOrgSuggestionMapper.partialUpdate(existingProOrgSuggestion, proOrgSuggestionDTO);

                    return existingProOrgSuggestion;
                })
                .map(proOrgSuggestionRepository::save)
                .map(proOrgSuggestionMapper::toDto);
    }

    /**
     * Get all the proOrgSuggestions.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ProOrgSuggestionDTO> findAll() {
        log.debug("Request to get all ProOrgSuggestions");
        return proOrgSuggestionRepository
                .findAll()
                .stream()
                .map(proOrgSuggestionMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one proOrgSuggestion by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ProOrgSuggestionDTO> findOne(Long id) {
        log.debug("Request to get ProOrgSuggestion : {}", id);
        return proOrgSuggestionRepository.findById(id).map(proOrgSuggestionMapper::toDto);
    }

    /**
     * Delete the proOrgSuggestion by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ProOrgSuggestion : {}", id);
        proOrgSuggestionRepository.deleteById(id);
    }

    /**
     * Find the list of ProOrgSuggestionHistory by criteria.
     *
     * @param criteria the criteria.
     * @return the list of ProOrgSuggestionHistoryDTO.
     */
    // proSuggestionHistoryQuery by DTO
    @Transactional(readOnly = true)
    public List<ProOrgSuggestionHistoryDTO> findByCriteria(ProOrgSuggestionHistoryDTO criteria) {
        return proOrgSuggestionRepository.findAllByCriteria(criteria);
    }

    // proSuggestionHistoryQuery by criteria
    @Transactional(readOnly = true)
    public Page<ProOrgSuggestionHistoryDTO> findByCriteria(ProOrgSuggestionHistoryCriteria criteria) {
        // sort by 原本沒有賦值
        if (criteria.getSortBy() != null && criteria.getSortBy().length == 0) {
            criteria.setSortBy(new String[]{"innovateName"});
        }
        final Page<ProOrgSuggestionHistoryDTO> proOrgSuggestionHistoryDTOs = proOrgSuggestionRepository.findAllByCriteria(criteria);
        return proOrgSuggestionHistoryDTOs;
    }

    /**
     * Find the list of ComStatusSearchDTO by criteria.
     *
     * @param criteria the criteria.
     * @return the list of ComStatusSearchDTO.
     */
    @Transactional(readOnly = true)
    public List<ComStatusSearchDTO> findAllByCriteria(ProOrgSuggestionCriteria criteria) {
        return proOrgSuggestionRepository.findAllByCriteria(criteria);
    }

    /**
     * Find the list of ComStatusSearchDTO by criteria.
     *
     * @param criteria the criteria.
     * @return the list of ComStatusSearchDTO.
     */
    @Transactional(readOnly = true)
    public Page<ComStatusSearchDTO> findPageByCriteria(ProOrgSuggestionCriteria criteria) {
        criteria.setSortBy(new String[]{"innoStatus"});
        return proOrgSuggestionRepository.findPageByCriteria(criteria);
    }
}
