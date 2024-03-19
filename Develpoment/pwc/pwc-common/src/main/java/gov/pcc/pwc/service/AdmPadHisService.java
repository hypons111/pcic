package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.AdmPadHis;
import gov.pcc.pwc.repository.AdmPadHisRepository;
import gov.pcc.pwc.service.dto.AdmPadHisDTO;
import gov.pcc.pwc.service.mapper.AdmPadHisMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link AdmPadHis}.
 */
@Service
@Transactional
public class AdmPadHisService {

    private final Logger log = LoggerFactory.getLogger(AdmPadHisService.class);

    private final AdmPadHisRepository admPadHisRepository;

    private final AdmPadHisMapper admPadHisMapper;

    public AdmPadHisService(AdmPadHisRepository admPadHisRepository, AdmPadHisMapper admPadHisMapper) {
        this.admPadHisRepository = admPadHisRepository;
        this.admPadHisMapper = admPadHisMapper;
    }

    /**
     * Save a admPadHis.
     *
     * @param admPadHisDTO the entity to save.
     * @return the persisted entity.
     */
    public AdmPadHisDTO save(AdmPadHisDTO admPadHisDTO) {
        log.debug("Request to save AdmPadHis : {}", admPadHisDTO);
        AdmPadHis admPadHis = admPadHisMapper.toEntity(admPadHisDTO);
        admPadHis = admPadHisRepository.save(admPadHis);
        return admPadHisMapper.toDto(admPadHis);
    }

    /**
     * Partially update a admPadHis.
     *
     * @param admPadHisDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<AdmPadHisDTO> partialUpdate(AdmPadHisDTO admPadHisDTO) {
        log.debug("Request to partially update AdmPadHis : {}", admPadHisDTO);

        return admPadHisRepository
            .findById(admPadHisDTO.getId())
            .map(existingAdmPadHis -> {
                admPadHisMapper.partialUpdate(existingAdmPadHis, admPadHisDTO);

                return existingAdmPadHis;
            })
            .map(admPadHisRepository::save)
            .map(admPadHisMapper::toDto);
    }

    /**
     * Get all the admPadHis.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<AdmPadHisDTO> findAll() {
        log.debug("Request to get all AdmPadHis");
        return admPadHisRepository.findAll().stream().map(admPadHisMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one admPadHis by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AdmPadHisDTO> findOne(Long id) {
        log.debug("Request to get AdmPadHis : {}", id);
        return admPadHisRepository.findById(id).map(admPadHisMapper::toDto);
    }

    /**
     * Delete the admPadHis by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete AdmPadHis : {}", id);
        admPadHisRepository.deleteById(id);
    }
}
