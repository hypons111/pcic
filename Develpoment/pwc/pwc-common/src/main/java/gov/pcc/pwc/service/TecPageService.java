package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.TecPage;
import gov.pcc.pwc.repository.TecPageRepository;
import gov.pcc.pwc.service.criteria.TecPageCriteria;
import gov.pcc.pwc.service.dto.TecPageContentDTO;
import gov.pcc.pwc.service.dto.TecInfoContentDTO;
import gov.pcc.pwc.service.dto.TecPageDTO;
import gov.pcc.pwc.service.mapper.TecPageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link TecPage}.
 */
@Service
@Transactional
public class TecPageService {

    private final Logger log = LoggerFactory.getLogger(TecPageService.class);

    private final TecPageRepository tecPageRepository;

    private final TecPageMapper tecPageMapper;

    public TecPageService(TecPageRepository tecPageRepository, TecPageMapper tecPageMapper) {
        this.tecPageRepository = tecPageRepository;
        this.tecPageMapper = tecPageMapper;
    }

    /**
     * Save a tecPage.
     *
     * @param tecPageDTO the entity to save.
     * @return the persisted entity.
     */
    public TecPageDTO save(TecPageDTO tecPageDTO) {
        log.debug("Request to save TecPage : {}", tecPageDTO);
        TecPage tecPage = tecPageMapper.toEntity(tecPageDTO);
        tecPage = tecPageRepository.save(tecPage);
        return tecPageMapper.toDto(tecPage);
    }

    /**
     * Partially update a tecPage.
     *
     * @param tecPageDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<TecPageDTO> partialUpdate(TecPageDTO tecPageDTO) {
        log.debug("Request to partially update TecPage : {}", tecPageDTO);

        return tecPageRepository
            .findById(tecPageDTO.getPageId())
            .map(existingTecPage -> {
                tecPageMapper.partialUpdate(existingTecPage, tecPageDTO);

                return existingTecPage;
            })
            .map(tecPageRepository::save)
            .map(tecPageMapper::toDto);
    }

    /**
     * Get all the tecPages.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<TecPageDTO> findAll() {
        log.debug("Request to get all TecPages");
        return tecPageRepository.findAll().stream().map(tecPageMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Find the list of tecPage by criteria.
     *
     * @param criteria the criteria.
     * @return the list of tecPageDTO.
     * 最新消息Service
     */
    @Transactional(readOnly = true)
    public Page<TecPageDTO> findByCriteria(TecPageCriteria criteria) {
        if(criteria.getSortBy() != null && criteria.getSortBy().length == 0 ){
            criteria.setSortBy(new String[] { "id" });
        }
        //傳入參數
        final Page<TecPage> tecPages = tecPageRepository.findAll(criteria, criteria.toPageable());
        return tecPages.map(tecPageMapper::toDto);
    }

    //最新消息不帶參數
    @Transactional(readOnly = true)
    public List<TecPageContentDTO> findByTop(){
        log.debug("findByTop:" + tecPageRepository.findTopQuery().toString());
        return tecPageRepository.findTopQuery();
    }

    //技術資料庫簡介
    @Transactional(readOnly = true)
    public TecInfoContentDTO findTecIntroByQuery(){
        log.debug("findTecIntroByQuery:" + tecPageRepository);
        return tecPageRepository.findTecInfoQuery();
    };

    //法規參考
    @Transactional(readOnly = true)
    public TecInfoContentDTO findLawRefByQuery() {
        log.debug("findLawRefByQuery:" );
        return tecPageRepository.findLawRefQuery();
    }


    /**
     * Get one tecPage by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TecPageDTO> findOne(Long id) {
        log.debug("Request to get TecPage : {}", id);
        return tecPageRepository.findById(id).map(tecPageMapper::toDto);
    }

    /**
     * Delete the tecPage by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete TecPage : {}", id);
        tecPageRepository.deleteById(id);
    }
}
