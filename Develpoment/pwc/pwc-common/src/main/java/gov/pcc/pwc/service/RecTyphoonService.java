package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.RecTyphoon;
import gov.pcc.pwc.repository.RecTyphoonRepository;
import gov.pcc.pwc.service.criteria.RecTyphoonCriteria;
import gov.pcc.pwc.service.dto.RecTyphoonDTO;
import gov.pcc.pwc.service.dto.RecTyphoonQueryDTO;
import gov.pcc.pwc.service.genSerialNumber.errors.TypeVariableNotMatchException;
import gov.pcc.pwc.service.mapper.RecTyphoonMapper;
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
 * Service Implementation for managing {@link RecTyphoon}.
 */
@Service
@Transactional
public class RecTyphoonService {

    private final Logger log = LoggerFactory.getLogger(RecTyphoonService.class);

    private final RecTyphoonRepository recTyphoonRepository;

    private final RecTyphoonMapper recTyphoonMapper;

    public RecTyphoonService(RecTyphoonRepository recTyphoonRepository, RecTyphoonMapper recTyphoonMapper) {
        this.recTyphoonRepository = recTyphoonRepository;
        this.recTyphoonMapper = recTyphoonMapper;
    }

    /**
     * Save a recTyphoon.
     *
     * @param recTyphoonDTO the entity to save.
     * @return the persisted entity.
     */
    public RecTyphoonDTO save(RecTyphoonDTO recTyphoonDTO) {
        log.debug("Request to save RecTyphoon : {}", recTyphoonDTO);
        RecTyphoon recTyphoon = recTyphoonMapper.toEntity(recTyphoonDTO);
        recTyphoon = recTyphoonRepository.save(recTyphoon);
        return recTyphoonMapper.toDto(recTyphoon);
    }


    public RecTyphoonDTO beforeInsertPackage(RecTyphoonDTO recTyphoonDTO) throws TypeVariableNotMatchException {
        recTyphoonRepository.packPkAndLogColumn(recTyphoonDTO);
        return  recTyphoonDTO;
    }

    /**
     * Partially update a recTyphoon.
     *
     * @param recTyphoonDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<RecTyphoonDTO> partialUpdate(RecTyphoonDTO recTyphoonDTO) {
        log.debug("Request to partially update RecTyphoon : {}", recTyphoonDTO);

        return recTyphoonRepository
            .findById(recTyphoonDTO.getTyphoonNo())
            .map(existingRecTyphoon -> {
                recTyphoonMapper.partialUpdate(existingRecTyphoon, recTyphoonDTO);

                return existingRecTyphoon;
            })
            .map(recTyphoonRepository::save)
            .map(recTyphoonMapper::toDto);
    }

    /**
     * Get all the recTyphoons.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<RecTyphoonDTO> findAll() {
        log.debug("Request to get all RecTyphoons");
        return recTyphoonRepository.findAll().stream().map(recTyphoonMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one recTyphoon by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<RecTyphoonDTO> findOne(String id) {
        log.debug("Request to get RecTyphoon : {}", id);
        return recTyphoonRepository.findById(id).map(recTyphoonMapper::toDto);
    }

    //風災條件查詢
    @Transactional(readOnly = true)
    public Page<RecTyphoonQueryDTO> findByCriteria(RecTyphoonCriteria criteria){
        // criteria呼叫getSortBy時如果非空值且getSortBy()陣列長度不為0
        if ( criteria.getSortBy() != null && criteria.getSortBy().length == 0 ){
            criteria.setSortBy(new String[] { "TYPHOON_NAME" });
        }
        final Page<RecTyphoonQueryDTO> recTyphoonQueryDTOs = recTyphoonRepository.findAllByCriteria(criteria);
        return recTyphoonQueryDTOs;

    }


    /**
     * Delete the recTyphoon by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete RecTyphoon : {}", id);
        recTyphoonRepository.deleteById(id);
    }
}
