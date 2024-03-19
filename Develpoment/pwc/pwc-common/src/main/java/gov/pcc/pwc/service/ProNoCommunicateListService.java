package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.ProNoCommunicateList;
import gov.pcc.pwc.repository.ProNoCommunicateListRepository;
import gov.pcc.pwc.service.dto.ProNoCommunicateListDTO;
import gov.pcc.pwc.service.mapper.ProNoCommunicateListMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ProNoCommunicateList}.
 */
@Service
@Transactional
public class ProNoCommunicateListService {

    private final Logger log = LoggerFactory.getLogger(ProNoCommunicateListService.class);

    private final ProNoCommunicateListRepository proNoCommunicateListRepository;

    private final ProNoCommunicateListMapper proNoCommunicateListMapper;

    public ProNoCommunicateListService(
        ProNoCommunicateListRepository proNoCommunicateListRepository,
        ProNoCommunicateListMapper proNoCommunicateListMapper
    ) {
        this.proNoCommunicateListRepository = proNoCommunicateListRepository;
        this.proNoCommunicateListMapper = proNoCommunicateListMapper;
    }

    /**
     * Save a proNoCommunicateList.
     *
     * @param proNoCommunicateListDTO the entity to save.
     * @return the persisted entity.
     */
    public ProNoCommunicateListDTO save(ProNoCommunicateListDTO proNoCommunicateListDTO) {
        log.debug("Request to save ProNoCommunicateList : {}", proNoCommunicateListDTO);
        ProNoCommunicateList proNoCommunicateList = proNoCommunicateListMapper.toEntity(proNoCommunicateListDTO);
        proNoCommunicateList = proNoCommunicateListRepository.save(proNoCommunicateList);
        return proNoCommunicateListMapper.toDto(proNoCommunicateList);
    }

    /**
     * Partially update a proNoCommunicateList.
     *
     * @param proNoCommunicateListDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ProNoCommunicateListDTO> partialUpdate(ProNoCommunicateListDTO proNoCommunicateListDTO) {
        log.debug("Request to partially update ProNoCommunicateList : {}", proNoCommunicateListDTO);

        return proNoCommunicateListRepository
            .findById(proNoCommunicateListDTO.getProNoCommunicateListId())
            .map(existingProNoCommunicateList -> {
                proNoCommunicateListMapper.partialUpdate(existingProNoCommunicateList, proNoCommunicateListDTO);

                return existingProNoCommunicateList;
            })
            .map(proNoCommunicateListRepository::save)
            .map(proNoCommunicateListMapper::toDto);
    }

    /**
     * Get all the proNoCommunicateLists.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ProNoCommunicateListDTO> findAll() {
        log.debug("Request to get all ProNoCommunicateLists");
        return proNoCommunicateListRepository
            .findAll()
            .stream()
            .map(proNoCommunicateListMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one proNoCommunicateList by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ProNoCommunicateListDTO> findOne(Long id) {
        log.debug("Request to get ProNoCommunicateList : {}", id);
        return proNoCommunicateListRepository.findById(id).map(proNoCommunicateListMapper::toDto);
    }

    /**
     * Delete the proNoCommunicateList by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ProNoCommunicateList : {}", id);
        proNoCommunicateListRepository.deleteById(id);
    }
}
