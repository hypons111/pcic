package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.ProOsPersonList;
import gov.pcc.pwc.repository.ProOsPersonListRepository;
import gov.pcc.pwc.service.dto.ProOsPersonListDTO;
import gov.pcc.pwc.service.mapper.ProOsPersonListMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ProOsPersonList}.
 */
@Service
@Transactional
public class ProOsPersonListService {

    private final Logger log = LoggerFactory.getLogger(ProOsPersonListService.class);

    private final ProOsPersonListRepository proOsPersonListRepository;

    private final ProOsPersonListMapper proOsPersonListMapper;

    public ProOsPersonListService(ProOsPersonListRepository proOsPersonListRepository, ProOsPersonListMapper proOsPersonListMapper) {
        this.proOsPersonListRepository = proOsPersonListRepository;
        this.proOsPersonListMapper = proOsPersonListMapper;
    }

    /**
     * Save a proOsPersonList.
     *
     * @param proOsPersonListDTO the entity to save.
     * @return the persisted entity.
     */
    public ProOsPersonListDTO save(ProOsPersonListDTO proOsPersonListDTO) {
        log.debug("Request to save ProOsPersonList : {}", proOsPersonListDTO);
        ProOsPersonList proOsPersonList = proOsPersonListMapper.toEntity(proOsPersonListDTO);
        proOsPersonList = proOsPersonListRepository.save(proOsPersonList);
        return proOsPersonListMapper.toDto(proOsPersonList);
    }

    /**
     * Partially update a proOsPersonList.
     *
     * @param proOsPersonListDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ProOsPersonListDTO> partialUpdate(ProOsPersonListDTO proOsPersonListDTO) {
        log.debug("Request to partially update ProOsPersonList : {}", proOsPersonListDTO);

        return proOsPersonListRepository
            .findById(proOsPersonListDTO.getProOsPersonListId())
            .map(existingProOsPersonList -> {
                proOsPersonListMapper.partialUpdate(existingProOsPersonList, proOsPersonListDTO);

                return existingProOsPersonList;
            })
            .map(proOsPersonListRepository::save)
            .map(proOsPersonListMapper::toDto);
    }

    /**
     * Get all the proOsPersonLists.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ProOsPersonListDTO> findAll() {
        log.debug("Request to get all ProOsPersonLists");
        return proOsPersonListRepository
            .findAll()
            .stream()
            .map(proOsPersonListMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one proOsPersonList by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ProOsPersonListDTO> findOne(Long id) {
        log.debug("Request to get ProOsPersonList : {}", id);
        return proOsPersonListRepository.findById(id).map(proOsPersonListMapper::toDto);
    }

    /**
     * Delete the proOsPersonList by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ProOsPersonList : {}", id);
        proOsPersonListRepository.deleteById(id);
    }
}
