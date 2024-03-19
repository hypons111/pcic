package gov.pcc.pwc.service;

import com.google.gson.Gson;
import gov.pcc.pwc.domain.EngMember;
import gov.pcc.pwc.repository.EngMemberRepository;
import gov.pcc.pwc.service.criteria.EngMemberCriteria;
import gov.pcc.pwc.service.dto.AdmFunctionUsedRecordDTO;
import gov.pcc.pwc.service.dto.EngMemberDTO;
import gov.pcc.pwc.service.mapper.EngMemberMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link EngMember}.
 */
@Service
@Transactional
public class EngMemberService {

    private final Logger log = LoggerFactory.getLogger(EngMemberService.class);

    private final EngMemberRepository engMemberRepository;

    private final EngMemberMapper engMemberMapper;

    private final AdmFunctionUsedRecordService admFunctionUsedRecordService;

    public EngMemberService(EngMemberRepository engMemberRepository, EngMemberMapper engMemberMapper, AdmFunctionUsedRecordService admFunctionUsedRecordService) {
        this.engMemberRepository = engMemberRepository;
        this.engMemberMapper = engMemberMapper;
        this.admFunctionUsedRecordService = admFunctionUsedRecordService;
    }

    /**
     * Save a engMember.
     *
     * @param engMemberDTO the entity to save.
     * @return the persisted entity.
     */
    public EngMemberDTO save(EngMemberDTO engMemberDTO) {
        log.debug("Request to save EngMember : {}", engMemberDTO);
        EngMember engMember = engMemberMapper.toEntity(engMemberDTO);
        engMember = engMemberRepository.save(engMember);
        return engMemberMapper.toDto(engMember);
    }

    public EngMemberDTO save(EngMemberDTO engMemberDTO, AdmFunctionUsedRecordDTO admFunctionUsedRecordDTO) {
        log.debug("Request to save EngMember : {}", engMemberDTO);
        EngMember engMember = engMemberMapper.toEntity(engMemberDTO);
        engMember = engMemberRepository.save(engMember);

        // 新增AdmFunctionUsedRecordDTO異動紀錄
        admFunctionUsedRecordDTO.setSysCategory("pwc");
        admFunctionUsedRecordDTO.setTableName("ENG_MEMBER");
        admFunctionUsedRecordDTO.setTableKey(engMember.getEngMemberId());
        admFunctionUsedRecordDTO.setUpdateValue(new Gson().toJson(engMember));
        admFunctionUsedRecordService.save(admFunctionUsedRecordDTO);

        return engMemberMapper.toDto(engMember);
    }

    /**
     * Partially update a engMember.
     *
     * @param engMemberDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<EngMemberDTO> partialUpdate(EngMemberDTO engMemberDTO) {
        log.debug("Request to partially update EngMember : {}", engMemberDTO);

        return engMemberRepository
            .findById(engMemberDTO.getEngMemberId())
            .map(existingEngMember -> {
                engMemberMapper.partialUpdate(existingEngMember, engMemberDTO);

                return existingEngMember;
            })
            .map(engMemberRepository::save)
            .map(engMemberMapper::toDto);
    }

    /**
     * Get all the engMembers.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<EngMemberDTO> findAll() {
        log.debug("Request to get all EngMembers");
        return engMemberRepository.findAll().stream().map(engMemberMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one engMember by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EngMemberDTO> findOne(String id) {
        log.debug("Request to get EngMember : {}", id);
        return engMemberRepository.findById(id).map(engMemberMapper::toDto);
    }

    /**
     * Get one engMember by type and idno.
     *
     * @param type the type of the entity.
     * @param idno the idno of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public EngMemberDTO findOne(String type, String idno) {
        log.debug("Request to get EngMeneber : {}, {}", type, idno);
        return engMemberRepository.findByTypeAndIdno(type, idno);
    }

    /**
     * Find the list of engMemberDTO by criteria.
     *
     * @param criteria the criteria.
     * @return the list of EngMemberDTO.
     */
    @Transactional(readOnly = true)
    public Page<EngMemberDTO> findByCriteria(EngMemberCriteria criteria) {
        if (criteria.getSortBy() != null && criteria.getSortBy().length == 0) {
            criteria.setSortBy(new String[] { "engMemberId" });
        }
        final Page<EngMember> engMembers = engMemberRepository.findAll(criteria, criteria.toPageable());
        return engMembers.map(engMemberMapper::toDto);
    }

    /**
     * Delete the engMember by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete EngMember : {}", id);
        engMemberRepository.deleteById(id);
    }
}
