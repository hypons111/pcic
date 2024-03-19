package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.RecAnalysis;
import gov.pcc.pwc.repository.RecAnalysisRepository;
import gov.pcc.pwc.service.criteria.RecAnalysisCriteria;
import gov.pcc.pwc.service.dto.RecAnalysisDTO;
import gov.pcc.pwc.service.genSerialNumber.errors.TypeVariableNotMatchException;
import gov.pcc.pwc.service.mapper.RecAnalysisMapper;
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
 * Service Implementation for managing {@link RecAnalysis}.
 */
@Service
@Transactional
public class RecAnalysisService {

    private final Logger log = LoggerFactory.getLogger(RecAnalysisService.class);

    private final RecAnalysisRepository recAnalysisRepository;

    private final RecAnalysisMapper recAnalysisMapper;

    public RecAnalysisService(RecAnalysisRepository recAnalysisRepository, RecAnalysisMapper recAnalysisMapper) {
        this.recAnalysisRepository = recAnalysisRepository;
        this.recAnalysisMapper = recAnalysisMapper;
    }

    /**
     * Save a recAnalysis.
     *
     * @param recAnalysisDTO the entity to save.
     * @return the persisted entity.
     */
    public RecAnalysisDTO save(RecAnalysisDTO recAnalysisDTO) {
        log.debug("Request to save RecProject : {}", recAnalysisDTO);
        RecAnalysis recAnalysis = recAnalysisMapper.toEntity(recAnalysisDTO);
        recAnalysis = recAnalysisRepository.save(recAnalysis);
        return recAnalysisMapper.toDto(recAnalysis);
    }


    public RecAnalysisDTO beforeInsertPackage(RecAnalysisDTO recAnalysisDTO) throws TypeVariableNotMatchException {
        recAnalysisRepository.packPkAndLogColumn(recAnalysisDTO);
        return  recAnalysisDTO;
    }

    /**
     * Partially update a recAnalysis.
     *
     * @param recAnalysisDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<RecAnalysisDTO> partialUpdate(RecAnalysisDTO recAnalysisDTO) {
        log.debug("Request to partially update RecAnalysis : {}", recAnalysisDTO);

        return recAnalysisRepository
                .findById(recAnalysisDTO.getAnalysisNo())
                .map(existingRecAnalysis -> {
                    recAnalysisMapper.partialUpdate(existingRecAnalysis, recAnalysisDTO);

                    return existingRecAnalysis;
                })
                .map(recAnalysisRepository::save)
                .map(recAnalysisMapper::toDto);
    }

    /**
     * Get all the recProjects.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<RecAnalysisDTO> findAll() {
        log.debug("Request to get all RecAnalysiss");
        return recAnalysisRepository.findAll().stream().map(recAnalysisMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one recProject by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<RecAnalysisDTO> findOne(String id) {
        log.debug("Request to get RecAnalysis : {}", id);
        return recAnalysisRepository.findById(id).map(recAnalysisMapper::toDto);
    }

    //災損情形條件查詢
    @Transactional(readOnly = true)
    public Page<RecAnalysisDTO> findByCriteria(RecAnalysisCriteria criteria){
        // criteria呼叫getSortBy時如果非空值且getSortBy()陣列長度不為0
        if ( criteria.getSortBy() != null && criteria.getSortBy().length == 0 ){
            criteria.setSortBy(new String[] { "ANALYSIS_NO" });
        }
        final Page<RecAnalysisDTO> recAnalysisDTOs = recAnalysisRepository.findAllByCriteria(criteria);
        return recAnalysisDTOs;

    }


    /**
     * Delete the recAnalysis by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete RecAnalysis : {}", id);
        recAnalysisRepository.deleteById(id);
    }
}
