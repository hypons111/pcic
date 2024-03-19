package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.EngCstLicense;
import gov.pcc.pwc.repository.EngCstLicenseRepository;
import gov.pcc.pwc.service.dto.EngCstLicenseDTO;
import gov.pcc.pwc.service.mapper.EngCstLicenseMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link EngCstLicense}.
 */
@Service
@Transactional
public class EngCstLicenseService {

    private final Logger log = LoggerFactory.getLogger(EngCstLicenseService.class);

    private final EngCstLicenseRepository engCstLicenseRepository;

    private final EngCstLicenseMapper engCstLicenseMapper;

    public EngCstLicenseService(EngCstLicenseRepository engCstLicenseRepository, EngCstLicenseMapper engCstLicenseMapper) {
        this.engCstLicenseRepository = engCstLicenseRepository;
        this.engCstLicenseMapper = engCstLicenseMapper;
    }

    /**
     * Save a engCstLicense.
     *
     * @param engCstLicenseDTO the entity to save.
     * @return the persisted entity.
     */
    public EngCstLicenseDTO save(EngCstLicenseDTO engCstLicenseDTO) {
        log.debug("Request to save EngCstLicense : {}", engCstLicenseDTO);
        EngCstLicense engCstLicense = engCstLicenseMapper.toEntity(engCstLicenseDTO);
        engCstLicense = engCstLicenseRepository.save(engCstLicense);
        return engCstLicenseMapper.toDto(engCstLicense);
    }

    /**
     * Partially update a engCstLicense.
     *
     * @param engCstLicenseDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<EngCstLicenseDTO> partialUpdate(EngCstLicenseDTO engCstLicenseDTO) {
        log.debug("Request to partially update EngCstLicense : {}", engCstLicenseDTO);

        return engCstLicenseRepository
            .findById(engCstLicenseDTO.getEngCstLicenseId())
            .map(existingEngCstLicense -> {
                engCstLicenseMapper.partialUpdate(existingEngCstLicense, engCstLicenseDTO);

                return existingEngCstLicense;
            })
            .map(engCstLicenseRepository::save)
            .map(engCstLicenseMapper::toDto);
    }

    /**
     * Get all the engCstLicenses.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<EngCstLicenseDTO> findAll() {
        log.debug("Request to get all EngCstLicenses");
        return engCstLicenseRepository.findAll().stream().map(engCstLicenseMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one engCstLicense by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EngCstLicenseDTO> findOne(String id) {
        log.debug("Request to get EngCstLicense : {}", id);
        return engCstLicenseRepository.findById(id).map(engCstLicenseMapper::toDto);
    }

    /**
     * Delete the engCstLicense by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete EngCstLicense : {}", id);
        engCstLicenseRepository.deleteById(id);
    }
}
