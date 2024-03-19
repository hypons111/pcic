package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.EngCompContact;
import gov.pcc.pwc.repository.EngCompContactRepository;
import gov.pcc.pwc.repository.EngCompRepository;
import gov.pcc.pwc.service.criteria.Eng0822R1DTOCriteria;
import gov.pcc.pwc.service.dto.Eng0822R1DTO;
import gov.pcc.pwc.service.dto.EngCompDTO;
import gov.pcc.pwc.service.genSerialNumber.SerialNumGenerator;
import gov.pcc.pwc.service.genSerialNumber.enums.EngGenNumberVariableEnum;
import gov.pcc.pwc.service.genSerialNumber.enums.ModuleEnum;
import gov.pcc.pwc.service.genSerialNumber.errors.TypeVariableNotMatchException;
import gov.pcc.pwc.service.genSerialNumber.parameter.SerialNumVariableParam;
import gov.pcc.pwc.service.mapper.EngCompMapper;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import gov.pcc.pwc.domain.EngComp;

import javax.validation.constraints.NotNull;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link EngComp}.
 */
@Service
@Transactional
public class EngCompService {

    private final Logger log = LoggerFactory.getLogger(EngCompService.class);

    private final EngCompRepository engCompRepository;

    private final EngCompMapper engCompMapper;

    public final EngCompContactRepository engCompContactRepository;

    private final SerialNumGenerator serialNumGenerator;

    public EngCompService(EngCompRepository engCompRepository,
                          EngCompContactRepository engCompContactRepository,
                          SerialNumGenerator serialNumGenerator,
                          EngCompMapper engCompMapper) {
        this.engCompRepository = engCompRepository;
        this.engCompContactRepository = engCompContactRepository;
        this.serialNumGenerator = serialNumGenerator;
        this.engCompMapper = engCompMapper;
    }

    // 技師公會通訊錄
    @Transactional(readOnly = true)
    public Page<Eng0822R1DTO> findEng0821(@NotNull Eng0822R1DTOCriteria criteria) {
        if (criteria.getSortBy() != null && criteria.getSortBy().length == 0) {
            criteria.setSortBy(new String[] { "SUBJECT_LIST" });
            criteria.setSortDirection(Sort.Direction.ASC);
        }
        final Page<Eng0822R1DTO> eng0822R1DTOS = engCompRepository.findEng0821R1(criteria);
        log.debug("findEng0821R1DTO:" + eng0822R1DTOS.toString());
        return eng0822R1DTOS;
    }

    // 業管新增公會資料
    public Eng0822R1DTO createEng0502(@NotNull Eng0822R1DTO dto) throws TypeVariableNotMatchException {
        // 新增所以需要產生id
        String id = serialNumGenerator
                .setModule(ModuleEnum.ENG)
                .setVariable(new SerialNumVariableParam(EngGenNumberVariableEnum.COMP.getVariable()))
                .getGeneratedNumber();
        dto.setEngCompId(id);  // 把產生的id給dto
        log.debug("createEng0502" + dto);
        Eng0822R1DTO eng0822R1DTO = engCompRepository.createEng0502(dto);
        return eng0822R1DTO;
    }

    // 業管編輯公會資料
    public Eng0822R1DTO updateEng0502(@NotNull Eng0822R1DTO dto)
            throws TypeVariableNotMatchException, IOException {
        log.debug("serviceUpdateEng0502" + dto);
        Eng0822R1DTO eng0822R1DTO = engCompRepository.updateEng0502(dto);
        return eng0822R1DTO;
    }

    // 用engCompId檢視公會資料
    @Transactional(readOnly = true)
    public Eng0822R1DTO viewEng0502(@NotNull String engCompId) {
        Eng0822R1DTO dto = engCompRepository.viewEng0502(engCompId);
        log.debug("serViewEng0502:" + dto);
        return dto;
    }

    // 查詢公會資料
    @Transactional(readOnly = true)
    public Page<Eng0822R1DTO> findEng0502(@NotNull Eng0822R1DTOCriteria criteria) {
        if (criteria.getSortBy() != null && criteria.getSortBy().length == 0) {
            criteria.setSortBy(new String[] { "TYPE" });
            criteria.setSortDirection(Sort.Direction.ASC); // 依類型由小到大
        }
        final Page<Eng0822R1DTO> page = engCompRepository.findEng0502(criteria);
        log.debug("service-findEng0502" + criteria.toString());
        return page;
    }

    // 編輯自己公會資料
    public Eng0822R1DTO updateEng0506(@NotNull Eng0822R1DTO dto)
            throws TypeVariableNotMatchException, IOException {
        log.debug("serviceUpdateEng0506" + dto);
        Eng0822R1DTO eng0822R1DTO = engCompRepository.updateEng0506(dto);
        return eng0822R1DTO;
    }

    // 檢視自己公會資料
    @Transactional(readOnly = true)
    public Eng0822R1DTO viewEng0506(@NotNull String id) {
        Eng0822R1DTO dto = engCompRepository.viewEng0506(id);
        log.debug("serViewEng0506:" + dto);
        return dto;
    }

    // 查詢合併主管機關
    @Transactional(readOnly = true)
    public Page<Eng0822R1DTO> findEng0703(@NotNull Eng0822R1DTOCriteria criteria) {
        if (criteria.getSortBy() != null && criteria.getSortBy().length == 0) {
            criteria.setSortBy(new String[] { "ENG_COMP_ID" });
            criteria.setSortDirection(Sort.Direction.DESC);
        }
        final Page<Eng0822R1DTO> page = engCompRepository.findEng0703(criteria);
        log.debug("findEng0703:" + page.toString());
        return page;
    }

    // 檢視該主管機關資料
    @Transactional(readOnly = true)
    public Eng0822R1DTO viewEng0703(@NotNull String id) {
        Eng0822R1DTO eng0822R1DTO = engCompRepository.viewEng0703(id);
        log.debug("viewEng0703:" + eng0822R1DTO);
        return eng0822R1DTO;
    }

    // 編輯該主管機關資料
    public Eng0822R1DTO updateEng0703(@NotNull Eng0822R1DTO dto)
            throws TypeVariableNotMatchException, IOException {
        log.debug("serviceUpdateEng0703" + dto);
        Eng0822R1DTO eng0822R1DTO = engCompRepository.update0703BySql(dto);
        return eng0822R1DTO;
    }

    // 新增主管機關資料
    public Eng0822R1DTO createEng0703(@NotNull Eng0822R1DTO dto) throws TypeVariableNotMatchException {
        log.debug("createEng0703" + dto);
        // 新增所以需要產生id
        String id = serialNumGenerator
                .setModule(ModuleEnum.ENG)
                .setVariable(new SerialNumVariableParam(EngGenNumberVariableEnum.COMP.getVariable()))
                .getGeneratedNumber();
        dto.setEngCompId(id);  // 把產生的id給dto
        Eng0822R1DTO eng0822R1DTO = engCompRepository.createEng0703(dto);
        return eng0822R1DTO;
    }

    // 合併顧問公司資料後回傳前端
    @Transactional(readOnly = true)
    public Page<Eng0822R1DTO> findEng0822R1(@NotNull Eng0822R1DTOCriteria criteria) {
        if (criteria.getSortBy() != null && criteria.getSortBy().length == 0) {
            criteria.setSortBy(new String[] { "SUBJECT_LIST" });
            criteria.setSortDirection(Sort.Direction.ASC);
        }
        final Page<Eng0822R1DTO> eng0822R1DTOS = engCompRepository.findEng0822R1(criteria);
        log.debug("findEng0822R1DTO:" + eng0822R1DTOS.toString());
        return eng0822R1DTOS;
    }
    // jhipster建的
    /**
     * Save a engComp.
     *
     * @param engCompDTO the entity to save.
     * @return the persisted entity.
     */
    public EngCompDTO save(EngCompDTO engCompDTO) {
        log.debug("Request to save EngComp : {}", engCompDTO);
        EngComp engComp = engCompMapper.toEntity(engCompDTO);
        engComp = engCompRepository.save(engComp);
        return engCompMapper.toDto(engComp);
    }

    /**
     * Partially update a engComp.
     *
     * @param engCompDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<EngCompDTO> partialUpdate(EngCompDTO engCompDTO) {
        log.debug("Request to partially update EngComp : {}", engCompDTO);

        return engCompRepository
                .findById(engCompDTO.getEngCompId())
                .map(existingEngComp -> {
                    engCompMapper.partialUpdate(existingEngComp, engCompDTO);

                    return existingEngComp;
                })
                .map(engCompRepository::save)
                .map(engCompMapper::toDto);
    }

    /**
     * Get all the engComps.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<EngCompDTO> findAll() {
        log.debug("Request to get all EngComps");
        return engCompRepository.findAll().stream().map(engCompMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one engComp by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EngCompDTO> findOne(String id) {
        log.debug("Request to get EngComp : {}", id);
        return engCompRepository.findById(id).map(engCompMapper::toDto);
    }

    /**
     * Delete the engComp by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete EngComp : {}", id);
        engCompRepository.deleteById(id);
    }
}
