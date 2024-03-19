package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.EngEngrSubjectpoint;
import gov.pcc.pwc.repository.EngEngrSubjectpointRepository;
import gov.pcc.pwc.service.criteria.EngEngrSubjectpointCriteria;
import gov.pcc.pwc.service.dto.EngEngrSubjectpointDTO;
import gov.pcc.pwc.service.genSerialNumber.SerialNumGenerator;
import gov.pcc.pwc.service.genSerialNumber.enums.EngGenNumberVariableEnum;
import gov.pcc.pwc.service.genSerialNumber.enums.ModuleEnum;
import gov.pcc.pwc.service.genSerialNumber.errors.TypeVariableNotMatchException;
import gov.pcc.pwc.service.genSerialNumber.parameter.SerialNumVariableParam;
import gov.pcc.pwc.service.mapper.EngEngrSubjectpointMapper;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link EngEngrSubjectpoint}.
 */
@Service
@Transactional
public class EngEngrSubjectpointService {

    private final Logger log = LoggerFactory.getLogger(EngEngrSubjectpointService.class);

    private final EngEngrSubjectpointRepository engEngrSubjectpointRepository;

    private final EngEngrSubjectpointMapper engEngrSubjectpointMapper;

    private final SerialNumGenerator serialNumGenerator;

    public EngEngrSubjectpointService(
            EngEngrSubjectpointRepository engEngrSubjectpointRepository,
            EngEngrSubjectpointMapper engEngrSubjectpointMapper,
            SerialNumGenerator serialNumGenerator) {
        this.engEngrSubjectpointRepository = engEngrSubjectpointRepository;
        this.engEngrSubjectpointMapper = engEngrSubjectpointMapper;
        this.serialNumGenerator = serialNumGenerator;
    }

    /**
     * Save a engEngrSubjectpoint.
     *
     * @param engEngrSubjectpointDTO the entity to save.
     * @return the persisted entity.
     */
    public EngEngrSubjectpointDTO save(EngEngrSubjectpointDTO engEngrSubjectpointDTO) throws TypeVariableNotMatchException {
        log.debug("Request to save EngEngrSubjectpoint : {}", engEngrSubjectpointDTO);
        if(engEngrSubjectpointDTO.getEngEngrSubjectpointId() == null) {
            engEngrSubjectpointDTO.setEngEngrSubjectpointId(serialNumGenerator
                    .setModule(ModuleEnum.ENG)
                    .setVariable(new SerialNumVariableParam(EngGenNumberVariableEnum.ENGR_SUBJECTPOINT_ID.getVariable()))
                    .getGeneratedNumber());
        }
        EngEngrSubjectpoint engEngrSubjectpoint = engEngrSubjectpointMapper.toEntity(engEngrSubjectpointDTO);
//        EngEngrSubjectpoint engEngrSubjectpoint = new EngEngrSubjectpoint();
//        BeanUtils.copyProperties(engEngrSubjectpointDTO, engEngrSubjectpoint);
        engEngrSubjectpoint = engEngrSubjectpointRepository.save(engEngrSubjectpoint);
        BeanUtils.copyProperties(engEngrSubjectpoint, engEngrSubjectpointDTO);
        return engEngrSubjectpointDTO;
    }

    @Transactional(readOnly = true)
    public Page<EngEngrSubjectpointDTO> findAll(@NotNull EngEngrSubjectpointCriteria criteria) {
        log.debug("Request to get all EngEngrSubjectpoints by criteria");
        if (criteria.getSortBy() != null && criteria.getSortBy().length == 0) {
            criteria.setSortBy(new String[] { "applyDate" });
            criteria.setSortDirection(Sort.Direction.DESC);
        }
        return engEngrSubjectpointRepository.findAllByCriteria(criteria);
    }

    @Transactional(readOnly = true)
    public EngEngrSubjectpointDTO findDTOById(String id) {
        log.debug("Request to get EngEngrSubjectpoint : {}", id);
        return engEngrSubjectpointRepository.findDTOById(id);
    }
}
