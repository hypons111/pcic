package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.EngReviewLog;
import gov.pcc.pwc.repository.EngReviewLogRepository;
import gov.pcc.pwc.service.criteria.EngReviewLogCriteria;
import gov.pcc.pwc.service.dto.EngReviewLogDTO;
import gov.pcc.pwc.service.genSerialNumber.SerialNumGenerator;
import gov.pcc.pwc.service.genSerialNumber.enums.EngGenNumberVariableEnum;
import gov.pcc.pwc.service.genSerialNumber.enums.ModuleEnum;
import gov.pcc.pwc.service.genSerialNumber.errors.TypeVariableNotMatchException;
import gov.pcc.pwc.service.genSerialNumber.parameter.SerialNumVariableParam;
import gov.pcc.pwc.service.mapper.EngReviewLogMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link EngReviewLog}.
 */
@Service
@Transactional
public class EngReviewLogService {

    private final Logger log = LoggerFactory.getLogger(EngReviewLogService.class);

    private final EngReviewLogRepository engReviewLogRepository;

    private final EngReviewLogMapper engReviewLogMapper;

    private final SerialNumGenerator serialNumGenerator;


    public EngReviewLogService(EngReviewLogRepository engReviewLogRepository, EngReviewLogMapper engReviewLogMapper, SerialNumGenerator serialNumGenerator) {
        this.engReviewLogRepository = engReviewLogRepository;
        this.engReviewLogMapper = engReviewLogMapper;
        this.serialNumGenerator = serialNumGenerator;
    }

    /**
     * Save a engReviewLog.
     *
     * @param engReviewLogDTO the entity to save.
     * @return the persisted entity.
     */
    public EngReviewLogDTO save(EngReviewLogDTO engReviewLogDTO) {
        log.debug("Request to save EngReviewLog : {}", engReviewLogDTO);
        EngReviewLog engReviewLog = engReviewLogMapper.toEntity(engReviewLogDTO);
        engReviewLog = engReviewLogRepository.save(engReviewLog);
        return engReviewLogMapper.toDto(engReviewLog);
    }

    /**
     * Partially update a engReviewLog.
     *
     * @param engReviewLogDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<EngReviewLogDTO> partialUpdate(EngReviewLogDTO engReviewLogDTO) {
        log.debug("Request to partially update EngReviewLog : {}", engReviewLogDTO);

        return engReviewLogRepository
            .findById(engReviewLogDTO.getEngReviewLogId())
            .map(existingEngReviewLog -> {
                engReviewLogMapper.partialUpdate(existingEngReviewLog, engReviewLogDTO);

                return existingEngReviewLog;
            })
            .map(engReviewLogRepository::save)
            .map(engReviewLogMapper::toDto);
    }

    /**
     * Get all the engReviewLogs.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<EngReviewLogDTO> findAll() {
        log.debug("Request to get all EngReviewLogs");
        return engReviewLogRepository.findAll().stream().map(engReviewLogMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one engReviewLog by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EngReviewLogDTO> findOne(String id) {
        log.debug("Request to get EngReviewLog : {}", id);
        return engReviewLogRepository.findById(id).map(engReviewLogMapper::toDto);
    }

    /**
     * Delete the engReviewLog by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete EngReviewLog : {}", id);
        engReviewLogRepository.deleteById(id);
    }

    /**
     * Find the list of EngReviewLogDTO by criteria.
     *
     * @param criteria the EngReviewLogCriteria.
     * @return the list of EngEngrCertificateDTO.
     */
    @Transactional(readOnly = true)
    public List<EngReviewLogDTO> findByCriteria(@NotNull EngReviewLogCriteria criteria) {
        log.debug("Request to get EngReviewLogs : {}, {}, {}", criteria.getExternalId(), criteria.getExternalVersion(), criteria.getReviewCaseType());
//        Sort sort = Sort.by("itemNo");
//        sort.ascending();
        if(criteria.getSort() == null) {
            criteria.setSort(Sort.by("itemNo").ascending());
        }
        final List<EngReviewLog> engReviewLogs = engReviewLogRepository.findAll(criteria, criteria.getSort());
        return engReviewLogs.stream().map(engReviewLogMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    public EngReviewLogDTO insert(EngReviewLogDTO engReviewLogDTO) throws TypeVariableNotMatchException {
        log.debug("Request to insert EngReviewLog : {}", engReviewLogDTO);

        if(engReviewLogDTO == null) return null;

        String engReviewLogId = serialNumGenerator
                .setModule(ModuleEnum.ENG)
                .setVariable(new SerialNumVariableParam(EngGenNumberVariableEnum.REVIEW_LOG.getVariable()))
                .getGeneratedNumber();
        engReviewLogDTO.setEngReviewLogId(engReviewLogId);

        EngReviewLogCriteria engReviewLogCriteria = new EngReviewLogCriteria();
        engReviewLogCriteria.setExternalId(engReviewLogDTO.getExternalId());
        engReviewLogCriteria.setExternalVersion(engReviewLogDTO.getExternalVersion());
        engReviewLogCriteria.setReviewCaseType(engReviewLogDTO.getReviewCaseType());
        engReviewLogCriteria.setSort(Sort.by("itemNo").descending());
        List<EngReviewLogDTO> list = findByCriteria(engReviewLogCriteria);
        if(list.size() != 0){
            engReviewLogDTO.setItemNo(list.get(0).getItemNo() + 1);
        }else {
            engReviewLogDTO.setItemNo(1);
        }

        engReviewLogRepository.insert(engReviewLogDTO);

        return engReviewLogDTO;
    }
}
