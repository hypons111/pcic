package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.AdmAccountRole;
import gov.pcc.pwc.domain.AdmMailSend;
import gov.pcc.pwc.domain.PwbUnitApply;
import gov.pcc.pwc.domain.PwbUnitRole;
import gov.pcc.pwc.repository.*;
import gov.pcc.pwc.service.criteria.PwbRoleApproveForUnitCriteria;
import gov.pcc.pwc.service.dto.AdmAccountContactDTO;
import gov.pcc.pwc.service.dto.PwbRoleApproveDTO;
import gov.pcc.pwc.service.dto.PwbUnitApplyDTO;
import gov.pcc.pwc.service.criteria.PwbUnitApplyCriteria;
import gov.pcc.pwc.service.mapper.PwbUnitApplyMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link PwbUnitApply}.
 */
@Service
@Transactional
public class PwbUnitApplyService {

    private final Logger log = LoggerFactory.getLogger(PwbUnitApplyService.class);

    private final PwbUnitApplyRepository pwbUnitApplyRepository;

    private final PwbUnitApplyMapper pwbUnitApplyMapper;

    private final AdmAccountRepository admAccountRepository;

    private final PwbRoleApproveRepository pwbRoleApproveRepository;

    private final AdmMailSendRepository admMailSendRepository;

    private final AdmAccountRoleRepository admAccountRoleRepository;

    private final PwbRoleApplyService pwbRoleApplyService;

    private final PwbUnitRoleRepository pwbUnitRoleRepository;

    public PwbUnitApplyService(PwbUnitApplyRepository pwbUnitApplyRepository, PwbUnitApplyMapper pwbUnitApplyMapper, AdmAccountRepository admAccountRepository, PwbRoleApproveRepository pwbRoleApproveRepository, AdmMailSendRepository admMailSendRepository, PwbUnitRoleRepository pwbUnitRoleRepository, AdmAccountRoleRepository admAccountRoleRepository, PwbRoleApplyService pwbRoleApplyService) {
        this.pwbUnitApplyRepository = pwbUnitApplyRepository;
        this.pwbUnitApplyMapper = pwbUnitApplyMapper;
        this.admAccountRepository = admAccountRepository;
        this.pwbRoleApproveRepository = pwbRoleApproveRepository;
        this.admMailSendRepository = admMailSendRepository;
        this.admAccountRoleRepository = admAccountRoleRepository;
        this.pwbUnitRoleRepository = pwbUnitRoleRepository;
        this.pwbRoleApplyService = pwbRoleApplyService;
    }

    /**
     * Save a pwbUnitApply.
     *
     * @param pwbUnitApplyDTO the entity to save.
     * @return the persisted entity.
     */
    public PwbUnitApplyDTO save(PwbUnitApplyDTO pwbUnitApplyDTO) {
        log.debug("Request to save PwbUnitApply : {}", pwbUnitApplyDTO);
        PwbUnitApply pwbUnitApply = pwbUnitApplyMapper.toEntity(pwbUnitApplyDTO);
        pwbUnitApply = pwbUnitApplyRepository.save(pwbUnitApply);
        return pwbUnitApplyMapper.toDto(pwbUnitApply);
    }

    /**
     * Partially update a pwbUnitApply.
     *
     * @param pwbUnitApplyDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<PwbUnitApplyDTO> partialUpdate(PwbUnitApplyDTO pwbUnitApplyDTO) {
        log.debug("Request to partially update PwbUnitApply : {}", pwbUnitApplyDTO);

        return pwbUnitApplyRepository
                .findById(pwbUnitApplyDTO.getId())
                .map(existingPwbUnitApply -> {
                    pwbUnitApplyMapper.partialUpdate(existingPwbUnitApply, pwbUnitApplyDTO);

                    return existingPwbUnitApply;
                })
                .map(pwbUnitApplyRepository::save)
                .map(pwbUnitApplyMapper::toDto);
    }

    /**
     * Get all the pwbUnitApplies.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<PwbUnitApplyDTO> findAll() {
        log.debug("Request to get all PwbUnitApplies");
        return pwbUnitApplyRepository.findAll().stream().map(pwbUnitApplyMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one pwbUnitApply by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PwbUnitApplyDTO> findOne(Long id) {
        log.debug("Request to get PwbUnitApply : {}", id);
        return pwbUnitApplyRepository.findById(id).map(pwbUnitApplyMapper::toDto);
    }

    /**
     * Delete the pwbUnitApply by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete PwbUnitApply : {}", id);
        pwbUnitApplyRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Page<PwbUnitApplyDTO> findUnitApplyByCriteria(PwbUnitApplyDTO criteria) {
        return pwbUnitApplyRepository.findUnitApply(criteria);
    }

    /**
     * Save a pwbRoleApply And admMailSend.
     *
     * @param pwbUnitApplyDTO the entity to save.
     * @return the persisted entity.
     */
    public PwbUnitApplyDTO saveByApplyAndMail(PwbUnitApplyDTO pwbUnitApplyDTO) {
        log.debug("Request to save PwbRoleApply : {}", pwbUnitApplyDTO);
        PwbUnitApply pwbUnitApply = pwbUnitApplyMapper.toEntity(pwbUnitApplyDTO);
        pwbUnitApply = pwbUnitApplyRepository.save(pwbUnitApply);
        String business = pwbUnitApply.getBusinessType();
        //狀態為申請時發送Mail
        if ("1".equals(pwbUnitApplyDTO.getStatus())) {
            AdmAccountContactDTO admAccountContact = new AdmAccountContactDTO();
            //取得申請者資料Account && Contact
            admAccountContact.setUserId(pwbUnitApply.getUnitId());
            List<AdmAccountContactDTO> adminAccountContact = admAccountRepository.findUserIdByCriteria(admAccountContact);
            //取得審核者資料 RoleApprove
            List<PwbRoleApproveDTO> pwbRoleApproveDTOs = pwbRoleApproveRepository.findBusinessUserId(pwbUnitApplyDTO.getModuleType(), pwbUnitApplyDTO.getBusinessType());
            if (pwbRoleApproveDTOs.size() > 0) {
                AdmAccountContactDTO admAccountContactForApp = new AdmAccountContactDTO();
                pwbRoleApproveDTOs.forEach(pwbRoleApproveDTO -> {
                    //取得審核者資料Account && Contact
                    admAccountContactForApp.setUserId(pwbRoleApproveDTO.getUserId());
                    List<AdmAccountContactDTO> admAccountContactDTOs = admAccountRepository.findUserIdByCriteria(admAccountContactForApp);
                    if (admAccountContactDTOs.size() > 0) {
                        AdmAccountContactDTO admAccountContactDTO = admAccountContactDTOs.get(0);
                        //郵件發送主檔
                        AdmMailSend admMailSend = new AdmMailSend();
                        admMailSend.setMailType("PWB0301");
                        admMailSend.setSourceId(admAccountContactDTO.getUserId());
                        admMailSend.setReceiver(admAccountContactDTO.getEmail());
                        admMailSend.setSubject("單位管理者權限申請：" + adminAccountContact.get(0).getChName());
                        admMailSend.setContent(formatContent(admAccountContactDTO, business));
                        admMailSend.setExpectSendTime(Instant.now());
                        admMailSend.setStatus("W");
                        admMailSend.setCreateUser(admAccountContactDTO.getUpdateUser());
                        admMailSend.setCreateTime(Instant.now());
                        admMailSend.setUpdateUser(admAccountContactDTO.getUpdateUser());
                        admMailSend.setUpdateTime(Instant.now());
                        admMailSendRepository.save(admMailSend);
                    }
                });
            }
        }
        return pwbUnitApplyMapper.toDto(pwbUnitApply);
    }

    public String formatContent(AdmAccountContactDTO admAccountContactDTO, String business) {
        String admMailSendContent = admAccountContactDTO.getUserId() + "先生/女士\n"+
                "雲端系統使用單位: "+ admAccountContactDTO.getChName() + "申請\n"+
                "模組中文名稱" + business + "\n"+
                "** 敬請審查 **";
        return admMailSendContent;
    }

    @Transactional(readOnly = true)
    public Page<PwbUnitApplyDTO> findByApproveCriteria(PwbRoleApproveForUnitCriteria criteria) {
        criteria.setSortBy(new String[]{"applyTime"});
        return pwbUnitApplyRepository.findByApproveCriteria(criteria);
    }

    /**
     * Delete ADM_ACCOUNT_ROLE according to userId
     *
     */
    public void deleteAccountRole(String userId) {
        // 取消button，刪除ADM_ACCOUNT_ROLE
        admAccountRoleRepository.deleteAccountRole(userId);
    }

    /**
     * Delete PWB_UNIT_ROLE according to unitId
     *
     */
    public void deleteUnitRole(String unitId) {
        // 取消button，刪除 PWB_UNIT_ROLE
        pwbUnitRoleRepository.deleteUnitRole(unitId);
    }

    /**
     * save PwbUnitApply according to userId
     *
     * @return
     */
    public PwbUnitApplyDTO saveUnitApplyResult(PwbUnitApplyDTO pwbUnitApplyDTO, String approveId) {
        PwbUnitApply PwbUnitApply = new PwbUnitApply();
        BeanUtils.copyProperties(pwbUnitApplyDTO, PwbUnitApply);
        PwbUnitApply.setUpdateTime(Instant.now());
        PwbUnitApply.setUpdateUser(approveId);
        PwbUnitApply = pwbUnitApplyRepository.save(PwbUnitApply);
        BeanUtils.copyProperties(PwbUnitApply, pwbUnitApplyDTO);
        //新增一筆資料到pwb_unit_role
        createUnitRole(approveId, pwbUnitApplyDTO);
        return pwbUnitApplyDTO;
    }

    /**
     * Create PWB_UNIT_ROLE according the user's module_type
     *
     */
    public void createUnitRole(String approveId, PwbUnitApplyDTO pwbUnitApplyDTO) {
        List<String> roleIds = admAccountRoleRepository.getUnitRoleId(pwbUnitApplyDTO.getModuleType(), pwbUnitApplyDTO.getBusinessType(), pwbUnitApplyDTO.getUserType());

        // 新增PWB_UNIT_ROLE
            for(String roleId : roleIds){
                PwbUnitRole pwbUnitRole = new PwbUnitRole();
                pwbUnitRole.setUnitId(pwbUnitApplyDTO.getUnitId());
                pwbUnitRole.setRoleId(roleId);
                pwbUnitRole.setUpdateUser(approveId);
                pwbUnitRole.setUpdateTime(Instant.now());
                pwbUnitRole.setCreateUser(approveId);
                pwbUnitRole.setCreateTime(Instant.now());
                pwbUnitRoleRepository.save(pwbUnitRole);
            }

    }

    /**
     * Create ADM_ACCOUNT_ROLE according the user's module_type
     *
     */
    public void createAccountRole(String approveId, PwbUnitApplyDTO pwbUnitApplyDTO) {
        List<String> roleIds = admAccountRoleRepository.getUnitRoleId(pwbUnitApplyDTO.getModuleType(), pwbUnitApplyDTO.getBusinessType(), pwbUnitApplyDTO.getUserType());

        // 新增ADM_ACCOUNT_ROLE
        for(String roleId : roleIds){
            AdmAccountRole admAccountRole = new AdmAccountRole();
            admAccountRole.setUserId(pwbUnitApplyDTO.getUnitId());
            admAccountRole.setRoleId(roleId);
            admAccountRole.setUpdateUser(approveId);
            admAccountRole.setUpdateTime(Instant.now());
            admAccountRoleRepository.save(admAccountRole);
        }
    }

    /**
     * Update PWB_UNIT_APPLY
     *
     */
    public PwbUnitApplyDTO saveUnitApplyResult(PwbUnitApplyDTO pwbUnitApplyDTO) {
        log.debug("Request to save PwbUnitApplyDTO : {}", pwbUnitApplyDTO);
        PwbUnitApply pwbUnitApply = new PwbUnitApply();
        BeanUtils.copyProperties(pwbUnitApplyDTO, pwbUnitApply);
        pwbUnitApply.setUpdateUser(pwbUnitApplyDTO.getUpdateUser());
        pwbUnitApply.setUpdateTime(Instant.now());
        pwbUnitApply = pwbUnitApplyRepository.save(pwbUnitApply);

        return pwbUnitApplyMapper.toDto(pwbUnitApply);
    }



    public Page<PwbUnitApplyDTO> findApplyList(PwbUnitApplyCriteria criteria){
        return pwbUnitApplyRepository.findApplyList(criteria);
    }

    public PwbUnitApplyDTO saveUnitApply(PwbUnitApplyDTO pwbUnitApplyDTO) {
        log.debug("Request to save PwbRoleApply : {}", pwbUnitApplyDTO);
        PwbUnitApply pwbUnitApply = pwbUnitApplyMapper.toEntity(pwbUnitApplyDTO);
        pwbUnitApply = pwbUnitApplyRepository.save(pwbUnitApply);

        return pwbUnitApplyMapper.toDto(pwbUnitApply);
    }

    public void addUnitRole(String loginUserId, PwbUnitApplyDTO pwbUnitApplyDTO) {
        String moduleType = pwbUnitApplyDTO.getModuleType();
        String businessType = pwbUnitApplyDTO.getBusinessType();
        List<String> roleIds = pwbUnitApplyRepository.getUnitApplyRoleId(moduleType, businessType);

        // 核准button，新增PWB_UNIT_ROLE
        for(int i = 0; i < roleIds.size(); i++){
            PwbUnitRole pwbUnitRole = new PwbUnitRole();
            pwbUnitRole.setUnitId(pwbUnitApplyDTO.getUnitId());
            pwbUnitRole.setRoleId(roleIds.get(i));
            pwbUnitRole.setUpdateUser(loginUserId);
            pwbUnitRole.setUpdateTime(Instant.now());
            pwbUnitRole.setCreateUser(loginUserId);
            pwbUnitRole.setCreateTime(Instant.now());
            pwbUnitRoleRepository.save(pwbUnitRole);
        }
    }

    public void cancelUnitRole(String unitId) {
        // 取消button，刪除PWB_UNIT_ROLE
        pwbUnitApplyRepository.cancelUnitRole(unitId);
    }

}
