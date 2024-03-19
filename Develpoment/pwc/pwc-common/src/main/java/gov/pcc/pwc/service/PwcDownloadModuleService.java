package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.PwcDownloadModule;
import gov.pcc.pwc.repository.PwcDownloadModuleRepository;
import gov.pcc.pwc.service.dto.PwcDownloadModuleDTO;
import gov.pcc.pwc.service.mapper.PwcDownloadModuleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link PwcDownloadModule}.
 */
@Service
@Transactional
public class PwcDownloadModuleService {

    private final Logger log = LoggerFactory.getLogger(PwcDownloadModuleService.class);

    private final PwcDownloadModuleRepository pwcDownloadModuleRepository;

    private final PwcDownloadModuleMapper pwcDownloadModuleMapper;

    public PwcDownloadModuleService(
        PwcDownloadModuleRepository pwcDownloadModuleRepository,
        PwcDownloadModuleMapper pwcDownloadModuleMapper
    ) {
        this.pwcDownloadModuleRepository = pwcDownloadModuleRepository;
        this.pwcDownloadModuleMapper = pwcDownloadModuleMapper;
    }

    /**
     * Save a pwcDownloadModule.
     *
     * @param pwcDownloadModuleDTO the entity to save.
     * @return the persisted entity.
     */
    public PwcDownloadModuleDTO save(PwcDownloadModuleDTO pwcDownloadModuleDTO) {
        log.debug("Request to save PwcDownloadModule : {}", pwcDownloadModuleDTO);
        PwcDownloadModule pwcDownloadModule = pwcDownloadModuleMapper.toEntity(pwcDownloadModuleDTO);
        pwcDownloadModule = pwcDownloadModuleRepository.save(pwcDownloadModule);
        return pwcDownloadModuleMapper.toDto(pwcDownloadModule);
    }

    /**
     * Partially update a pwcDownloadModule.
     *
     * @param pwcDownloadModuleDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<PwcDownloadModuleDTO> partialUpdate(PwcDownloadModuleDTO pwcDownloadModuleDTO) {
        log.debug("Request to partially update PwcDownloadModule : {}", pwcDownloadModuleDTO);

        return pwcDownloadModuleRepository
            .findById(pwcDownloadModuleDTO.getProDownloadModuleId())
            .map(
                existingPwcDownloadModule -> {
                    pwcDownloadModuleMapper.partialUpdate(existingPwcDownloadModule, pwcDownloadModuleDTO);
                    return existingPwcDownloadModule;
                }
            )
            .map(pwcDownloadModuleRepository::save)
            .map(pwcDownloadModuleMapper::toDto);
    }

    /**
     * Get all the pwcDownloadModules.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<PwcDownloadModuleDTO> findAll() {
        log.debug("Request to get all PwcDownloadModules");
        return pwcDownloadModuleRepository
            .findAll()
            .stream()
            .map(pwcDownloadModuleMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one pwcDownloadModule by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PwcDownloadModuleDTO> findOne(Long id) {
        log.debug("Request to get PwcDownloadModule : {}", id);
        return pwcDownloadModuleRepository.findById(id).map(pwcDownloadModuleMapper::toDto);
    }

    /**
     * Delete the pwcDownloadModule by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete PwcDownloadModule : {}", id);
        pwcDownloadModuleRepository.deleteById(id);
    }
}
