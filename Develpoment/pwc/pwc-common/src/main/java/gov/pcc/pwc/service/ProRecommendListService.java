package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.ProRecommendList;
import gov.pcc.pwc.repository.ProRecommendListRepository;
import gov.pcc.pwc.service.dto.ProRecommendListDTO;
import gov.pcc.pwc.service.mapper.ProRecommendListMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ProRecommendList}.
 */
@Service
@Transactional
public class ProRecommendListService {

    private final Logger log = LoggerFactory.getLogger(ProRecommendListService.class);

    private final ProRecommendListRepository proRecommendListRepository;

    private final ProRecommendListMapper proRecommendListMapper;

    public ProRecommendListService(ProRecommendListRepository proRecommendListRepository, ProRecommendListMapper proRecommendListMapper) {
        this.proRecommendListRepository = proRecommendListRepository;
        this.proRecommendListMapper = proRecommendListMapper;
    }

    /**
     * Save a proRecommendList.
     *
     * @param proRecommendListDTO the entity to save.
     * @return the persisted entity.
     */
    public ProRecommendListDTO save(ProRecommendListDTO proRecommendListDTO) {
        log.debug("Request to save ProRecommendList : {}", proRecommendListDTO);
        ProRecommendList proRecommendList = proRecommendListMapper.toEntity(proRecommendListDTO);
        proRecommendList = proRecommendListRepository.save(proRecommendList);
        return proRecommendListMapper.toDto(proRecommendList);
    }

    /**
     * Partially update a proRecommendList.
     *
     * @param proRecommendListDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ProRecommendListDTO> partialUpdate(ProRecommendListDTO proRecommendListDTO) {
        log.debug("Request to partially update ProRecommendList : {}", proRecommendListDTO);

        return proRecommendListRepository
            .findById(proRecommendListDTO.getProRecommendListId())
            .map(existingProRecommendList -> {
                proRecommendListMapper.partialUpdate(existingProRecommendList, proRecommendListDTO);

                return existingProRecommendList;
            })
            .map(proRecommendListRepository::save)
            .map(proRecommendListMapper::toDto);
    }

    /**
     * Get all the proRecommendLists.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ProRecommendListDTO> findAll() {
        log.debug("Request to get all ProRecommendLists");
        return proRecommendListRepository
            .findAll()
            .stream()
            .map(proRecommendListMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one proRecommendList by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ProRecommendListDTO> findOne(Long id) {
        log.debug("Request to get ProRecommendList : {}", id);
        return proRecommendListRepository.findById(id).map(proRecommendListMapper::toDto);
    }

    /**
     * Delete the proRecommendList by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ProRecommendList : {}", id);
        proRecommendListRepository.deleteById(id);
    }
}
