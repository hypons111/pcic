package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.ProEnginList;
import gov.pcc.pwc.repository.ProEnginListRepository;
import gov.pcc.pwc.service.dto.ProEnginListDTO;
import gov.pcc.pwc.service.mapper.ProEnginListMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ProEnginList}.
 */
@Service
@Transactional
public class ProEnginListService {

    private final Logger log = LoggerFactory.getLogger(ProEnginListService.class);

    private final ProEnginListRepository proEnginListRepository;

    private final ProEnginListMapper proEnginListMapper;

    public ProEnginListService(ProEnginListRepository proEnginListRepository, ProEnginListMapper proEnginListMapper) {
        this.proEnginListRepository = proEnginListRepository;
        this.proEnginListMapper = proEnginListMapper;
    }

    /**
     * Save a proEnginList.
     *
     * @param proEnginListDTO the entity to save.
     * @return the persisted entity.
     */
    public ProEnginListDTO save(ProEnginListDTO proEnginListDTO) {
        log.debug("Request to save ProEnginList : {}", proEnginListDTO);
        ProEnginList proEnginList = proEnginListMapper.toEntity(proEnginListDTO);
        proEnginList = proEnginListRepository.save(proEnginList);
        return proEnginListMapper.toDto(proEnginList);
    }

    /**
     * Partially update a proEnginList.
     *
     * @param proEnginListDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ProEnginListDTO> partialUpdate(ProEnginListDTO proEnginListDTO) {
        log.debug("Request to partially update ProEnginList : {}", proEnginListDTO);

        return proEnginListRepository
            .findById(proEnginListDTO.getProEnginListId())
            .map(existingProEnginList -> {
                proEnginListMapper.partialUpdate(existingProEnginList, proEnginListDTO);

                return existingProEnginList;
            })
            .map(proEnginListRepository::save)
            .map(proEnginListMapper::toDto);
    }

    /**
     * Get all the proEnginLists.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ProEnginListDTO> findAll() {
        log.debug("Request to get all ProEnginLists");
        return proEnginListRepository.findAll().stream().map(proEnginListMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one proEnginList by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ProEnginListDTO> findOne(Long id) {
        log.debug("Request to get ProEnginList : {}", id);
        return proEnginListRepository.findById(id).map(proEnginListMapper::toDto);
    }

    /**
     * Delete the proEnginList by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ProEnginList : {}", id);
        proEnginListRepository.deleteById(id);
    }
}
