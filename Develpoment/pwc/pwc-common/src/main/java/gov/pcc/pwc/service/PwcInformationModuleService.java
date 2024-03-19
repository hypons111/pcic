package gov.pcc.pwc.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gov.pcc.pwc.domain.PwcInformationModule;
import gov.pcc.pwc.repository.PwcInformationModuleRepository;
import gov.pcc.pwc.service.dto.PwcInformationModuleDTO;
import gov.pcc.pwc.service.mapper.PwcInformationModuleMapper;

/**
 * Service Implementation for managing {@link PwcInformationModule}.
 */
@Service
@Transactional
public class PwcInformationModuleService {

    private final Logger log = LoggerFactory.getLogger(PwcInformationModuleService.class);

    private final PwcInformationModuleRepository pwcInformationModuleRepository;

    private final PwcInformationModuleMapper pwcInformationModuleMapper;

    public PwcInformationModuleService(
        PwcInformationModuleRepository pwcInformationModuleRepository,
        PwcInformationModuleMapper pwcInformationModuleMapper
    ) {
        this.pwcInformationModuleRepository = pwcInformationModuleRepository;
        this.pwcInformationModuleMapper = pwcInformationModuleMapper;
    }

    /**
     * Save a pwcInformationModule.
     *
     * @param pwcInformationModuleDTO the entity to save.
     * @return the persisted entity.
     */
    public PwcInformationModuleDTO save(PwcInformationModuleDTO pwcInformationModuleDTO) {
        log.debug("Request to save PwcInformationModule : {}", pwcInformationModuleDTO);
        PwcInformationModule pwcInformationModule = pwcInformationModuleMapper.toEntity(pwcInformationModuleDTO);
        pwcInformationModule = pwcInformationModuleRepository.save(pwcInformationModule);
        return pwcInformationModuleMapper.toDto(pwcInformationModule);
    }

    /**
     * Partially update a pwcInformationModule.
     *
     * @param pwcInformationModuleDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<PwcInformationModuleDTO> partialUpdate(PwcInformationModuleDTO pwcInformationModuleDTO) {
        log.debug("Request to partially update PwcInformationModule : {}", pwcInformationModuleDTO);

        return pwcInformationModuleRepository
            .findById(pwcInformationModuleDTO.getProInformationModuleId())
            .map(
                existingPwcInformationModule -> {
                    pwcInformationModuleMapper.partialUpdate(existingPwcInformationModule, pwcInformationModuleDTO);
                    return existingPwcInformationModule;
                }
            )
            .map(pwcInformationModuleRepository::save)
            .map(pwcInformationModuleMapper::toDto);
    }

    /**
     * Get all the pwcInformationModules.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<PwcInformationModuleDTO> findAll() {
        log.debug("Request to get all PwcInformationModules");
        return pwcInformationModuleRepository
            .findAll()
            .stream()
            .map(pwcInformationModuleMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one pwcInformationModule by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PwcInformationModuleDTO> findOne(Long id) {
        log.debug("Request to get PwcInformationModule : {}", id);
        return pwcInformationModuleRepository.findById(id).map(pwcInformationModuleMapper::toDto);
    }

    /**
     * Delete the pwcInformationModule by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete PwcInformationModule : {}", id);
        pwcInformationModuleRepository.deleteById(id);
    }
}
