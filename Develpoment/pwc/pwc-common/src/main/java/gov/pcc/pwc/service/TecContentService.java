package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.TecContent;
import gov.pcc.pwc.repository.TecContentRepository;
import gov.pcc.pwc.service.dto.TecContentDTO;
import gov.pcc.pwc.service.mapper.TecContentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link TecContent}.
 */
@Service
@Transactional
public class TecContentService {

    private final Logger log = LoggerFactory.getLogger(TecContentService.class);

    private final TecContentRepository tecContentRepository;

    private final TecContentMapper tecContentMapper;

    public TecContentService(TecContentRepository tecContentRepository, TecContentMapper tecContentMapper) {
        this.tecContentRepository = tecContentRepository;
        this.tecContentMapper = tecContentMapper;
    }

    /**
     * Save a tecContent.
     *
     * @param tecContentDTO the entity to save.
     * @return the persisted entity.
     */
    public TecContentDTO save(TecContentDTO tecContentDTO) {
        log.debug("Request to save TecContent : {}", tecContentDTO);
        TecContent tecContent = tecContentMapper.toEntity(tecContentDTO);
        tecContent = tecContentRepository.save(tecContent);
        return tecContentMapper.toDto(tecContent);
    }

    /**
     * Partially update a tecContent.
     *
     * @param tecContentDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<TecContentDTO> partialUpdate(TecContentDTO tecContentDTO) {
        log.debug("Request to partially update TecContent : {}", tecContentDTO);

        return tecContentRepository
            .findById(tecContentDTO.getContentId())
            .map(existingTecContent -> {
                tecContentMapper.partialUpdate(existingTecContent, tecContentDTO);

                return existingTecContent;
            })
            .map(tecContentRepository::save)
            .map(tecContentMapper::toDto);
    }

    /**
     * Get all the tecContents.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<TecContentDTO> findAll() {
        log.debug("Request to get all TecContents");
        return tecContentRepository.findAll().stream().map(tecContentMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one tecContent by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TecContentDTO> findOne(Long id) {
        log.debug("Request to get TecContent : {}", id);
        return tecContentRepository.findById(id).map(tecContentMapper::toDto);
    }

    /**
     * Delete the tecContent by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete TecContent : {}", id);
        tecContentRepository.deleteById(id);
    }
}
