package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.RecArea;
import gov.pcc.pwc.repository.RecAreaRepository;
import gov.pcc.pwc.service.criteria.RecAreaCriteria;
import gov.pcc.pwc.service.dto.RecAreaDTO;
import gov.pcc.pwc.service.genSerialNumber.errors.TypeVariableNotMatchException;
import gov.pcc.pwc.service.mapper.RecAreaMapper;
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
 * Service Implementation for managing {@link RecArea}.
 */
@Service
@Transactional
public class RecAreaService {

    private final Logger log = LoggerFactory.getLogger(RecAreaService.class);

    private final RecAreaRepository recAreaRepository;

    private final RecAreaMapper recAreaMapper;

    public RecAreaService(RecAreaRepository recAreaRepository, RecAreaMapper recAreaMapper) {
        this.recAreaRepository = recAreaRepository;
        this.recAreaMapper = recAreaMapper;
    }

    /**
     * Save a recArea.
     *
     * @param recAreaDTO the entity to save.
     * @return the persisted entity.
     */
    public RecAreaDTO save(RecAreaDTO recAreaDTO) {
        log.debug("Request to save RecArea : {}", recAreaDTO);
        RecArea recArea = recAreaMapper.toEntity(recAreaDTO);
        recArea = recAreaRepository.save(recArea);
        return recAreaMapper.toDto(recArea);
    }


    public RecAreaDTO beforeInsertPackage(RecAreaDTO recAreaDTO) throws TypeVariableNotMatchException {
        recAreaRepository.packPkAndLogColumn(recAreaDTO);
        return  recAreaDTO;
    }

    /**
     * Partially update a recArea.
     *
     * @param recAreaDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<RecAreaDTO> partialUpdate(RecAreaDTO recAreaDTO) {
        log.debug("Request to partially update RecArea : {}", recAreaDTO);

        return recAreaRepository
                .findById(recAreaDTO.getAreaNo())
                .map(existingRecArea -> {
                    recAreaMapper.partialUpdate(existingRecArea, recAreaDTO);

                    return existingRecArea;
                })
                .map(recAreaRepository::save)
                .map(recAreaMapper::toDto);
    }

    /**
     * Get all the recProjects.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<RecAreaDTO> findAll() {
        log.debug("Request to get all RecAreas");
        return recAreaRepository.findAll().stream().map(recAreaMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one recProject by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<RecAreaDTO> findOne(String id) {
        log.debug("Request to get RecArea : {}", id);
        return recAreaRepository.findById(id).map(recAreaMapper::toDto);
    }

    //災損情形條件查詢
    @Transactional(readOnly = true)
    public Page<RecAreaDTO> findByCriteria(RecAreaCriteria criteria){
        // criteria呼叫getSortBy時如果非空值且getSortBy()陣列長度不為0
        if ( criteria.getSortBy() != null && criteria.getSortBy().length == 0 ){
            criteria.setSortBy(new String[] { "AREA_NO" });
        }
        final Page<RecAreaDTO> recAreaDTOs = recAreaRepository.findAllByCriteria(criteria);
        return recAreaDTOs;

    }


    /**
     * Delete the recArea by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete RecArea : {}", id);
        recAreaRepository.deleteById(id);
    }
}
