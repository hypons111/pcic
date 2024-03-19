package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.AdmAccountRole;
import gov.pcc.pwc.domain.AdmMailSend;
import gov.pcc.pwc.domain.PwbRoleApply;
import gov.pcc.pwc.repository.*;
import gov.pcc.pwc.service.dto.*;
import gov.pcc.pwc.service.mapper.PwbRoleApplyMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link PwbRoleApply}.
 */
@Service
@Transactional
public class PwbRoleApplyService {

    private final Logger log = LoggerFactory.getLogger(PwbRoleApplyService.class);

    private final PwbRoleApplyRepository pwbRoleApplyRepository;

    private final PwbRoleApplyMapper pwbRoleApplyMapper;

    private final PwbRoleApproveRepository pwbRoleApproveRepository;

    private final AdmAccountRepository admAccountRepository;

    private final AdmMailSendRepository admMailSendRepository;

    private final AdmAccountRoleRepository admAccountRoleRepository;

    public PwbRoleApplyService(PwbRoleApplyRepository pwbRoleApplyRepository, PwbRoleApplyMapper pwbRoleApplyMapper, PwbRoleApproveRepository pwbRoleApproveRepository, AdmAccountRepository admAccountRepository, AdmMailSendRepository admMailSendRepository, AdmAccountRoleRepository admAccountRoleRepository) {
        this.pwbRoleApplyRepository = pwbRoleApplyRepository;
        this.pwbRoleApplyMapper = pwbRoleApplyMapper;
        this.pwbRoleApproveRepository = pwbRoleApproveRepository;
        this.admAccountRepository = admAccountRepository;
        this.admMailSendRepository = admMailSendRepository;
        this.admAccountRoleRepository = admAccountRoleRepository;
    }

    /**
     * Save a pwbRoleApply.
     *
     * @param pwbRoleApplyDTO the entity to save.
     * @return the persisted entity.
     */
    public PwbRoleApplyDTO save(PwbRoleApplyDTO pwbRoleApplyDTO) {
        log.debug("Request to save PwbRoleApply : {}", pwbRoleApplyDTO);
        PwbRoleApply pwbRoleApply = pwbRoleApplyMapper.toEntity(pwbRoleApplyDTO);
        pwbRoleApply = pwbRoleApplyRepository.save(pwbRoleApply);
        return pwbRoleApplyMapper.toDto(pwbRoleApply);
    }

    /**
     * Save a pwbRoleApply And admMailSend.
     *
     * @param pwbRoleApplyDTO the entity to save.
     * @return the persisted entity.
     */
    public PwbRoleApplyDTO saveByApplyAndMail(PwbRoleApplyDTO pwbRoleApplyDTO) {
//    public PwbRoleApplyDTO saveRoleApplyAndMail(PwbRoleApplyDTO pwbRoleApplyDTO) {
        log.debug("Request to save PwbRoleApply : {}", pwbRoleApplyDTO);
        PwbRoleApply pwbRoleApply = pwbRoleApplyMapper.toEntity(pwbRoleApplyDTO);
        pwbRoleApply = pwbRoleApplyRepository.save(pwbRoleApply);

        //狀態為申請時發送Mail
        if("1".equals(pwbRoleApplyDTO.getStatus())){
            AdmAccountContactDTO admAccountContact = new AdmAccountContactDTO();
            //取得申請者資料AccountContact
            admAccountContact.setUserId(pwbRoleApply.getUserId());
            List<AdmAccountContactDTO> adminAccountContact = admAccountRepository.findUserIdByCriteria(admAccountContact);
            //取得審核者資料 RoleApprove
            List<PwbRoleApproveDTO> pwbRoleApproveDTOs = pwbRoleApproveRepository.findUserId(pwbRoleApplyDTO.getModuleType()) ;
            if(pwbRoleApproveDTOs.size()>0){
                AdmAccountContactDTO admAccountContactForApp = new AdmAccountContactDTO();
                pwbRoleApproveDTOs.forEach(pwbRoleApproveDTO -> {
                    //取得審核者資料AccountContact
                    admAccountContactForApp.setUserId(pwbRoleApproveDTO.getUserId());
                    List<AdmAccountContactDTO> admAccountContactDTOs = admAccountRepository.findUserIdByCriteria(admAccountContactForApp);
                    if(admAccountContactDTOs.size()>0) {
                        AdmAccountContactDTO admAccountContactDTO = admAccountContactDTOs.get(0);
                        //郵件發送主檔
                        AdmMailSend admMailSend = new AdmMailSend();
                        admMailSend.setMailType("PWB0203");
                        admMailSend.setSourceId(admAccountContactDTO.getUserId());
                        admMailSend.setReceiver(admAccountContactDTO.getEmail());
                        admMailSend.setSubject("雲端系統使用權限申請(個人帳號)：" + adminAccountContact.get(0).getUserId() + adminAccountContact.get(0).getChName());
                        admMailSend.setContent(formatContent(admAccountContactDTO, pwbRoleApproveDTO));
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
        return pwbRoleApplyMapper.toDto(pwbRoleApply);
    }

    public String formatContent(AdmAccountContactDTO admAccountContactDTO, PwbRoleApproveDTO pwbRoleApproveDTO) {
//        String url = urlContent(request);
        String admMailSendContent = "雲端系統使用" +
                admAccountContactDTO.getUserId() +
                admAccountContactDTO.getChName() + "申請" + "\n" +
                pwbRoleApproveDTO.getModuleType() + "\n" +
                "** 敬請審查 **";

        return admMailSendContent;
    }

    /**
     * Partially update a pwbRoleApply.
     *
     * @param pwbRoleApplyDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<PwbRoleApplyDTO> partialUpdate(PwbRoleApplyDTO pwbRoleApplyDTO) {
        log.debug("Request to partially update PwbRoleApply : {}", pwbRoleApplyDTO);

        return pwbRoleApplyRepository
            .findById(pwbRoleApplyDTO.getId())
            .map(existingPwbRoleApply -> {
                pwbRoleApplyMapper.partialUpdate(existingPwbRoleApply, pwbRoleApplyDTO);

                return existingPwbRoleApply;
            })
            .map(pwbRoleApplyRepository::save)
            .map(pwbRoleApplyMapper::toDto);
    }

    /**
     * Get all the pwbRoleApplies.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<PwbRoleApplyDTO> findAll() {
        log.debug("Request to get all PwbRoleApplies");
        return pwbRoleApplyRepository.findAll().stream().map(pwbRoleApplyMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one pwbRoleApply by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PwbRoleApplyDTO> findOne(Long id) {
        log.debug("Request to get PwbRoleApply : {}", id);
        return pwbRoleApplyRepository.findById(id).map(pwbRoleApplyMapper::toDto);
    }

    /**
     * Delete the pwbRoleApply by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete PwbRoleApply : {}", id);
        pwbRoleApplyRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<PwbRoleApplyDTO> findRoleApplyByCriteria(PwbRoleApplyDTO criteria) {
        return pwbRoleApplyRepository.findRoleApply(criteria);
    }

    /**
     * Update PWB_ROLE_APPLY
     *
     */
    public PwbRoleApplyDTO saveApplyResult(PwbRoleDTO pwbRoleDTO) {
        log.debug("Request to save PwbRoleApply : {}", pwbRoleDTO);
        PwbRoleApply pwbRoleApply = pwbRoleApplyMapper.toEntity(pwbRoleDTO);
        pwbRoleApply.setCreateUser(pwbRoleDTO.getCreateUser());
        pwbRoleApply.setCreateTime(pwbRoleDTO.getCreateTime());
        pwbRoleApply.setUpdateUser(pwbRoleDTO.getUpdateUser());
        pwbRoleApply.setUpdateTime(pwbRoleDTO.getUpdateTime());
        pwbRoleApply = pwbRoleApplyRepository.save(pwbRoleApply);

        return pwbRoleApplyMapper.toDto(pwbRoleApply);
    }

    /**
     * Create ADM_ACCOUNT_ROLE according to the user's module_type
     *
     */
    public void createAccountRole(String approveId, PwbRoleDTO pwbRoleDTO) {
        List<String> roleIds = admAccountRoleRepository.getRoleId(pwbRoleDTO.getModuleType());

        // 核准button，新增ADM_ACCOUNT_ROLE
        for(int i = 0; i < roleIds.size(); i++){
            AdmAccountRole admAccountRole = new AdmAccountRole();
            admAccountRole.setUserId(pwbRoleDTO.getUserId());
            admAccountRole.setRoleId(roleIds.get(i));
            admAccountRole.setUpdateUser(approveId);
            admAccountRole.setUpdateTime(Instant.now());
            admAccountRoleRepository.save(admAccountRole);
        }
    }

    /**
     * Delete ADM_ACCOUNT_ROLE according to userId
     *
     */
    public void deleteAccountRole(String userId) {
        // 取消button，刪除ADM_ACCOUNT_ROLE
        admAccountRoleRepository.deleteAccountRole(userId);
    }


}
