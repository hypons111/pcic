package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.*;
import gov.pcc.pwc.repository.AdmAccountRepository;
import gov.pcc.pwc.repository.AdmAccountRoleRepository;
import gov.pcc.pwc.repository.AdmContactRepository;
import gov.pcc.pwc.repository.AdmMailSendRepository;
import gov.pcc.pwc.service.dto.*;
import gov.pcc.pwc.service.mapper.AdmContactMapper;

import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import gov.pcc.pwc.utils.RandomPassword;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

/**
 * Service Implementation for managing {@link AdmContact}.
 */
@Service
@Transactional
public class AdmContactService {

    private final Logger log = LoggerFactory.getLogger(AdmContactService.class);

    private final AdmContactRepository admContactRepository;

    private final AdmContactMapper admContactMapper;

    private final AdmAccountService admAccountService;

    private final AdmAccountRepository admAccountRepository;

    private final AdmAccountRoleRepository admAccountRoleRepository;

    private final PwbRoleApplyService pwbRoleApplyService;

    private final AdmMailSendRepository admMailSendRepository;

    public AdmContactService(AdmContactRepository admContactRepository, AdmContactMapper admContactMapper, AdmAccountService admAccountService, AdmAccountRepository admAccountRepository, AdmAccountRoleRepository admAccountRoleRepository, PwbRoleApplyService pwbRoleApplyService, AdmMailSendRepository admMailSendRepository) {
        this.admContactRepository = admContactRepository;
        this.admContactMapper = admContactMapper;
        this.admAccountService = admAccountService;
        this.admAccountRepository = admAccountRepository;
        this.admAccountRoleRepository = admAccountRoleRepository;
        this.pwbRoleApplyService = pwbRoleApplyService;
        this.admMailSendRepository = admMailSendRepository;
    }

    /**
     * Save a admContact.
     *
     * @param admContactDTO the entity to save.
     * @return the persisted entity.
     */
    public AdmContactDTO save(AdmContactDTO admContactDTO) {
        log.debug("Request to save AdmContact : {}", admContactDTO);
        AdmContact admContact = admContactMapper.toEntity(admContactDTO);
        admContact = admContactRepository.save(admContact);
        return admContactMapper.toDto(admContact);
    }

    /**
     * Partially update a admContact.
     *
     * @param admContactDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<AdmContactDTO> partialUpdate(AdmContactDTO admContactDTO) {
        log.debug("Request to partially update AdmContact : {}", admContactDTO);

        return admContactRepository
            .findById(admContactDTO.getUserId())
            .map(existingAdmContact -> {
                admContactMapper.partialUpdate(existingAdmContact, admContactDTO);

                return existingAdmContact;
            })
            .map(admContactRepository::save)
            .map(admContactMapper::toDto);
    }

    /**
     * Get all the admContacts.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<AdmContactDTO> findAll() {
        log.debug("Request to get all AdmContacts");
        return admContactRepository.findAll().stream().map(admContactMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one admContact by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AdmContactDTO> findOne(String id) {
        log.debug("Request to get AdmContact : {}", id);
        return admContactRepository.findById(id).map(admContactMapper::toDto);
    }

    /**
     * Delete the admContact by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete AdmContact : {}", id);
        admContactRepository.deleteById(id);
    }

    /**
     * Create or save a AdmAccount & AdmContact.
     *
     * @param admAccountContactDTO the entity to save.
     * @return the persisted entity.
     */
    public void saveAdmAccountContact(AdmAccountContactDTO admAccountContactDTO, HttpServletRequest request) {
        log.debug("Request to save AdmAccount & AdmContact : {}", admAccountContactDTO);

        // 查詢此USER_ID在ADM_ACCOUNT的所有資料
        AdmAccountContactDTO user = admAccountService.findOne(admAccountContactDTO.getUserId());
        String randomPwd = RandomPassword.getRandomPwd(8);

        if(user == null) {
            // 新增ADM_ACCOUNT
            AdmAccount admAccount = new AdmAccount();
            BeanUtils.copyProperties(admAccountContactDTO, admAccount);
            admAccount.setPccOrgId(admAccountContactDTO.getPccOrgId());
            admAccount.setCompIdno(admAccountContactDTO.getCompIdno());
            admAccount.setUserPadHash(randomPwd);
            admAccount.setStatus("Y");
            admAccount.setAuditStatus("Y");
            if(admAccountContactDTO.getPccOrgId() == null){
                admAccount.setUserType("C");
            } else {
                admAccount.setUserType("A");
            }
            admAccount.setUpdateUser(admAccountContactDTO.getLoginUserId());
            admAccount.setUpdateTime(Instant.now());
            admAccountRepository.save(admAccount);
        } else {
            // 更新ADM_ACCOUNT
            AdmAccount admAccount = new AdmAccount();
            BeanUtils.copyProperties(user, admAccount);
            admAccount.setStatus(admAccountContactDTO.getStatus());
            admAccount.setUpdateUser(admAccountContactDTO.getUserId());
            admAccount.setUpdateTime(Instant.now());
            admAccountRepository.save(admAccount);
        }

        String userId = admAccountContactDTO.getUserId();
        Optional<AdmContactDTO> admContactDTO = admContactRepository.findById(userId).map(admContactMapper::toDto);


        if(!admContactDTO.isEmpty()){
             //刪除原有的ADM_ACCOUNT_ROLE
            pwbRoleApplyService.deleteAccountRole(admAccountContactDTO.getUserId());
        } else {
            String content = admAccountService.formatContent(admAccountContactDTO, randomPwd, request);
            String subject = "帳號啟用通知";
            admAccountContactDTO.setUpdateUser(admAccountContactDTO.getLoginUserId());
            admAccountContactDTO.setUpdateTime(Instant.now());
            AdmMailSend admMailSend = admAccountService.saveMailSend(admAccountContactDTO, "PWB0202", content, subject);
            admMailSendRepository.save(admMailSend);
        }

        // 新增ADM_ACCOUNT_ROLE
        List<PwcSharedDTO> jurisdictionRoles = admAccountContactDTO.getJurisdictionRoles();
        for(PwcSharedDTO jurisdiction : jurisdictionRoles){
            AdmAccountRole admAccountRole = new AdmAccountRole();
            admAccountRole.setUserId(admAccountContactDTO.getUserId());
            admAccountRole.setRoleId(jurisdiction.getCode());
            admAccountRole.setUpdateUser(admAccountContactDTO.getLoginUserId());
            admAccountRole.setUpdateTime(Instant.now());
            admAccountRoleRepository.save(admAccountRole);
        }

        // 新增 & 更新ADM_CONTACT
        AdmContact admContact = new AdmContact();
        BeanUtils.copyProperties(admAccountContactDTO, admContact);
        admContact.setTitle(null);
        admContact.setUpdateUser(admAccountContactDTO.getLoginUserId());
        admContact.setUpdateTime(Instant.now());
        admContactRepository.save(admContact);


    }

}
