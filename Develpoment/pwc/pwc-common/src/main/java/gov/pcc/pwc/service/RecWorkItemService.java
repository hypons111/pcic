package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.RecWorkItem;
import gov.pcc.pwc.repository.RecWorkItemRepository;
import gov.pcc.pwc.service.criteria.RecWorkItemCriteria;
import gov.pcc.pwc.service.dto.RecWorkItemDTO;
import gov.pcc.pwc.service.genSerialNumber.errors.TypeVariableNotMatchException;
import gov.pcc.pwc.service.mapper.RecWorkItemMapper;
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
 * Service Implementation for managing {@link RecWorkItem}.
 */
@Service
@Transactional
public class RecWorkItemService {

    private final Logger log = LoggerFactory.getLogger(RecWorkItemService.class);

    private final RecWorkItemRepository recWorkItemRepository;

    private final RecWorkItemMapper recWorkItemMapper;

    public RecWorkItemService(RecWorkItemRepository recWorkItemRepository, RecWorkItemMapper recWorkItemMapper) {
        this.recWorkItemRepository = recWorkItemRepository;
        this.recWorkItemMapper = recWorkItemMapper;
    }

    /**
     * Save a recWorkItem.
     *
     * @param recWorkItemDTO the entity to save.
     * @return the persisted entity.
     */
    public RecWorkItemDTO save(RecWorkItemDTO recWorkItemDTO) {
        log.debug("Request to save RecWorkItem : {}", recWorkItemDTO);
        RecWorkItem recWorkItem = recWorkItemMapper.toEntity(recWorkItemDTO);
        recWorkItem = recWorkItemRepository.save(recWorkItem);
        return recWorkItemMapper.toDto(recWorkItem);
    }


    public RecWorkItemDTO beforeInsertPackage(RecWorkItemDTO recWorkItemDTO) throws TypeVariableNotMatchException {
        recWorkItemRepository.packPkAndLogColumn(recWorkItemDTO);
        return  recWorkItemDTO;
    }

    /**
     * Partially update a recWorkItem.
     *
     * @param recWorkItemDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<RecWorkItemDTO> partialUpdate(RecWorkItemDTO recWorkItemDTO) {
        log.debug("Request to partially update recWorkItem : {}", recWorkItemDTO);

        return recWorkItemRepository
                .findById(recWorkItemDTO.getWorkItemNo())
                .map(existingRecWorkItem -> {
                    recWorkItemMapper.partialUpdate(existingRecWorkItem, recWorkItemDTO);

                    return existingRecWorkItem;
                })
                .map(recWorkItemRepository::save)
                .map(recWorkItemMapper::toDto);
    }

    /**
     * Get all the recProjects.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<RecWorkItemDTO> findAll() {
        log.debug("Request to get all recWorkItems");
        return recWorkItemRepository.findAll().stream().map(recWorkItemMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one recProject by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<RecWorkItemDTO> findOne(String id) {
        log.debug("Request to get RecWorkItem : {}", id);
        return recWorkItemRepository.findById(id).map(recWorkItemMapper::toDto);
    }

    //災損地區工程項目條件查詢
    @Transactional(readOnly = true)
    public Page<RecWorkItemDTO> findByCriteria(RecWorkItemCriteria criteria){
        // criteria呼叫getSortBy時如果非空值且getSortBy()陣列長度不為0
        if ( criteria.getSortBy() != null && criteria.getSortBy().length == 0 ){
            criteria.setSortBy(new String[] { "WORK_ITEM_NO" });
        }
        final Page<RecWorkItemDTO> recWorkItemDTOs = recWorkItemRepository.findAllByCriteria(criteria);
        return recWorkItemDTOs;

    }


    /**
     * Delete the recWorkItem by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete recWorkItem : {}", id);
        recWorkItemRepository.deleteById(id);
    }
}
