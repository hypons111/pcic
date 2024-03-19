package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.AdmAccount;
import gov.pcc.pwc.domain.AdmCompany;
import gov.pcc.pwc.domain.AdmContact;
import gov.pcc.pwc.domain.AdmMailSend;
import gov.pcc.pwc.repository.*;
import gov.pcc.pwc.service.criteria.AdmAccountContactCriteria;
import gov.pcc.pwc.service.criteria.UserManagementCriteria;
import gov.pcc.pwc.service.dto.AdmAccountContactDTO;
import gov.pcc.pwc.service.dto.AdmAgentDTO;
import gov.pcc.pwc.service.dto.AdmCompanyApplyDTO;
import gov.pcc.pwc.service.dto.UserManagementDTO;
import gov.pcc.pwc.service.mapper.AdmAgentMapper;
import gov.pcc.pwc.utils.RandomPassword;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service Implementation for managing {@link AdmAccount}.
 */
@Service
@Transactional
public class AdmAccountService {

    private final Logger log = LoggerFactory.getLogger(AdmAccountService.class);

    private final AdmAccountRepository admAccountRepository;

    private final AdmContactRepository admContactRepository;

    private final AdmAccountRoleRepository admAccountRoleRepository;

    private final AdmMailSendRepository admMailSendRepository;

    private final AdmAgentRepository admAgentRepository;

    private final AdmAgentMapper admAgentMapper;

    private final AdmCompanyRepository admCompanyRepository;

    public AdmAccountService(
            AdmAccountRepository admAccountRepository,
            AdmContactRepository admContactRepository,
            AdmAccountRoleRepository admAccountRoleRepository,
            AdmMailSendRepository admMailSendRepository,
            AdmAgentRepository admAgentRepository,
            AdmAgentMapper admAgentMapper,
            AdmCompanyRepository admCompanyRepository) {
        this.admAccountRepository = admAccountRepository;
        this.admContactRepository = admContactRepository;
        this.admAccountRoleRepository = admAccountRoleRepository;
        this.admMailSendRepository = admMailSendRepository;
        this.admAgentRepository = admAgentRepository;
        this.admAgentMapper = admAgentMapper;
        this.admCompanyRepository = admCompanyRepository;
    }
    private String admMailSendContent = "" ;
    private String subject = "";

    /**
     * Save a userAccount.
     *
     * @param admAccountContactDTO the entity to save.
     * @return the persisted entity.
     */
    public AdmAccountContactDTO save(AdmAccountContactDTO admAccountContactDTO) {
        log.debug("Request to save AdmAccount & AdmContact : {}", admAccountContactDTO);
        AdmAccount admAccount = new AdmAccount();
        admAccount.setUserId(admAccountContactDTO.getUserId());
        admAccount.setUserPadHash(admAccountContactDTO.getUserPadHash());
        admAccount.setStatus(admAccountContactDTO.getStatus());
        admAccount.setAuditStatus(admAccountContactDTO.getAuditStatus());
        admAccount.setUserType(admAccountContactDTO.getUserType());
        if (admAccountContactDTO.getUserType().equals("A")) {
            admAccount.setPccOrgId(admAccountContactDTO.getPccOrgId());
            admAccount.setNo(admAccountContactDTO.getNo());
        } else if (admAccountContactDTO.getUserType().equals("C")) {
            admAccount.setCompIdno(admAccountContactDTO.getCompIdno());
        }
        admAccount.setUpdateUser(admAccountContactDTO.getUpdateUser());
        admAccount.setPadUpdateTime(admAccountContactDTO.getPadUpdateTime());
        admAccount.setUpdateTime(admAccountContactDTO.getUpdateTime());

        AdmContact admContact = new AdmContact();
        admContact.setUserId(admAccountContactDTO.getUserId());
        admContact.setChName(admAccountContactDTO.getChName());
        admContact.setChNameRome(admAccountContactDTO.getChNameRome());
        admContact.setTitle(admAccountContactDTO.getTitle());
        admContact.setBirthday(admAccountContactDTO.getBirthday());
        admContact.setIdno(admAccountContactDTO.getIdno());
        admContact.setSex(admAccountContactDTO.getSex());
        admContact.setWorkTel(admAccountContactDTO.getWorkTel());
        admContact.setMobileTel(admAccountContactDTO.getMobileTel());
        admContact.setEmail(admAccountContactDTO.getEmail());
        admContact.setHomeAddrZip(admAccountContactDTO.getHomeAddrZip());
        admContact.setHomeAddrCity(admAccountContactDTO.getHomeAddrCity());
        admContact.setHomeAddrTown(admAccountContactDTO.getHomeAddrTown());
        admContact.setHomeAddrOther(admAccountContactDTO.getHomeAddrOther());
        admContact.setWorkAddrZip(admAccountContactDTO.getWorkAddrZip());
        admContact.setWorkAddrCity(admAccountContactDTO.getWorkAddrCity());
        admContact.setWorkAddrTown(admAccountContactDTO.getWorkAddrTown());
        admContact.setWorkAddrOther(admAccountContactDTO.getWorkAddrOther());
        admContact.setUpdateUser(admAccountContactDTO.getUpdateUser());
        admContact.setUpdateTime(admAccountContactDTO.getUpdateTime());

        admAccountRepository.save(admAccount);
        admContactRepository.save(admContact);
        admAccountRoleRepository.saveAdmAccountRoleList(admAccountContactDTO);

        return admAccountContactDTO;
    }

    /**
     * Get one userAccount by id.
     *
     * @param userId the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public AdmAccountContactDTO findOne(String userId) {
        log.debug("Request to get AdmAccount & AdmContact : {}", userId);
        AdmAccountContactDTO criteria = new AdmAccountContactDTO();
        criteria.setUserId(userId);
        List<AdmAccountContactDTO> results = admAccountRepository.findAllByCriteria(criteria);
        if(!results.isEmpty()) {
            AdmAccountContactDTO result = results.get(0);
            // 由帳號擷取出UnitId (for工程會帳號)
            result.setUnitId(result.getUserId().substring(5, result.getUserId().indexOf("-")));
            return result;
        }
        return null;
    }

    /**
     * Find the list of AdmAccountContactDTO by criteria.
     *
     * @param criteria the criteria.
     * @return the list of AdmAccountContactDTO.
     */
    @Transactional(readOnly = true)
    public Page<AdmAccountContactDTO> findByCriteria(AdmAccountContactCriteria criteria) {
        if (criteria.getSortBy() != null && criteria.getSortBy().length == 0) {
            criteria.setSortBy(new String[] { "userId" });
        }
        final Page<AdmAccountContactDTO> AdmAccountContactDTOs = admAccountRepository.findAllByCriteria(criteria);
        // 由帳號擷取出UnitId (for工程會帳號)
        AdmAccountContactDTOs.forEach(admAccountContactDTO -> {
            admAccountContactDTO.setUnitId(admAccountContactDTO.getUserId().substring(5, admAccountContactDTO.getUserId().indexOf("-")));
        });
        return AdmAccountContactDTOs;
    }

    @Transactional(readOnly = true)
    public List<AdmAccountContactDTO> findByCriteria(AdmAccountContactDTO criteria) {
        final List<AdmAccountContactDTO> AdmAccountContactDTOs = admAccountRepository.findAllByCriteria(criteria);
        AdmAccountContactDTOs.forEach(admAccountContactDTO -> {
            admAccountContactDTO.setUnitId(admAccountContactDTO.getUserId().substring(5, admAccountContactDTO.getUserId().indexOf("-")));
        });
        return AdmAccountContactDTOs;
    }

    public AdmAccountContactDTO deleteById(String userId) {
        AdmAccountContactDTO admAccountContactDTO = findOne(userId);
        admAccountRepository.deleteById(userId);
        admContactRepository.deleteById(userId);
        admAccountRoleRepository.deleteByUserId(userId);
        return admAccountContactDTO;
    }

    /**
     * Save a userAccount.
     *
     * @param admAccountContactDTO the entity to save.
     * @return the persisted entity.
     */
    public AdmAccountContactDTO saveForAccountContact(AdmAccountContactDTO admAccountContactDTO, HttpServletRequest request) {
        log.debug("Request to save AdmAccount & AdmContact : {}", admAccountContactDTO);
        //帳號檔
        AdmAccount admAccount = new AdmAccount();
        admAccount.setUserId(admAccountContactDTO.getUserId());
        String randomPwd = RandomPassword.getRandomPwd(8);
        admAccount.setUserPadHash(randomPwd);
        admAccount.setStatus("Y");
        admAccount.setAuditStatus("Y");
        admAccount.setUserType("I");
        admAccount.setUpdateUser(admAccountContactDTO.getUpdateUser());
        admAccount.setPadUpdateTime(admAccountContactDTO.getPadUpdateTime());
        admAccount.setUpdateTime(admAccountContactDTO.getUpdateTime());

        //聯絡資料檔
        AdmContact admContact = new AdmContact();
        admContact.setUserId(admAccountContactDTO.getUserId());
        admContact.setChName(admAccountContactDTO.getChName());
        admContact.setChNameRome(admAccountContactDTO.getChNameRome());
        admContact.setTitle(admAccountContactDTO.getTitle());
        admContact.setBirthday(admAccountContactDTO.getBirthday());
        admContact.setIdno(admAccountContactDTO.getIdno());
        admContact.setSex(admAccountContactDTO.getSex());
        admContact.setWorkTel(admAccountContactDTO.getWorkTel());
        admContact.setMobileTel(admAccountContactDTO.getMobileTel());
        admContact.setEmail(admAccountContactDTO.getEmail());
        admContact.setHomeAddrZip(admAccountContactDTO.getHomeAddrZip());
        admContact.setHomeAddrCity(admAccountContactDTO.getHomeAddrCity());
        admContact.setHomeAddrTown(admAccountContactDTO.getHomeAddrTown());
        admContact.setHomeAddrOther(admAccountContactDTO.getHomeAddrOther());
        admContact.setWorkAddrZip(admAccountContactDTO.getWorkAddrZip());
        admContact.setWorkAddrCity(admAccountContactDTO.getWorkAddrCity());
        admContact.setWorkAddrTown(admAccountContactDTO.getWorkAddrTown());
        admContact.setWorkAddrOther(admAccountContactDTO.getWorkAddrOther());
        admContact.setUpdateUser(admAccountContactDTO.getUpdateUser());
        admContact.setUpdateTime(admAccountContactDTO.getUpdateTime());

        //郵件發送主檔
        AdmMailSend admMailSend = new AdmMailSend();
        admMailSend.setMailType("PWC0303");
        admMailSend.setSourceId(admAccountContactDTO.getUserId());
        admMailSend.setReceiver(admAccountContactDTO.getEmail());
        admMailSend.setSubject(subject);
        admMailSend.setContent(formatContent(admAccountContactDTO, randomPwd, request));
        admMailSend.setExpectSendTime(Instant.now());
        admMailSend.setStatus("W");
        admMailSend.setCreateUser(admAccountContactDTO.getUpdateUser());
        admMailSend.setCreateTime(admAccountContactDTO.getUpdateTime());
        admMailSend.setUpdateUser(admAccountContactDTO.getUpdateUser());
        admMailSend.setUpdateTime(admAccountContactDTO.getUpdateTime());

        admAccountRepository.save(admAccount);
        admContactRepository.save(admContact);
        admMailSendRepository.save(admMailSend);

        return admAccountContactDTO;
    }

    /**
     * Get one userAccount by id.
     *
     * @param admAccountContactDTO the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public AdmAccountContactDTO findByUserId(AdmAccountContactDTO admAccountContactDTO) {
        log.debug("Request to get AdmAccount & AdmContact : {}", admAccountContactDTO.getUserId());

        AdmAccountContactDTO result = admAccountRepository.findByUserId(admAccountContactDTO);

        return result;
    }

    public String formatContent(AdmAccountContactDTO admAccountContactDTO, String randomPwd, HttpServletRequest request) {
        String url = urlContent(request);
        admMailSendContent = admAccountContactDTO.getUserId() + "先生/女士\n"+
                "您已完成公共工程雲端系統帳號申請\n"+
                "**帳號：" + admAccountContactDTO.getUserId() + "\n"+
                " **預設密碼：" + randomPwd + "\n"+
                "請於14天內連結下列網址進行密碼變更：\n" + url + "\n"+
                "謝謝\n" +
                "公共工程雲端系統客服 敬上";

        return admMailSendContent;
    }

    public String urlContent(HttpServletRequest request) {

        //取UUID
        String secretKey= UUID.randomUUID().toString();
        //14天後時間
        Timestamp outDate = new Timestamp(System.currentTimeMillis()+ 4320 * 60 * 1000);
        long date = outDate.getTime()/1000 * 1000 ;
        String key = "$" +date+ "$" +secretKey;
        String path = request.getContextPath();
        String basePath = request.getScheme()+"://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
        String resetPassHref = basePath + "service/pwc0304?sid=" + key;

        return resetPassHref;
    }

    /**
     * Save a AdmAccount.
     *
     * @param admAccountContactDTO the entity to save.
     * @return the persisted entity.
     */
    public AdmAccount savePassWord(AdmAccountContactDTO admAccountContactDTO) {
        log.debug("Request to save AdmAccount : {}", admAccountContactDTO);
        AdmAccount admAccount = new AdmAccount();
        BeanUtils.copyProperties(admAccountContactDTO, admAccount);
        admAccount = admAccountRepository.save(admAccount);
        return admAccount;
    }

    /**
     * Get one admAccountContactDTO
     *
     * @param admAccountContactDTO the id of the entity.
     * @return the AdmMailSend.
     */
    @Transactional(readOnly = true)
    public AdmMailSend saveMailSend(AdmAccountContactDTO admAccountContactDTO, String mailType, String content, String subject) {
        log.debug("Request to get AdmAccount & AdmContact : {}", admAccountContactDTO.getUserId());

        //郵件發送主檔
        AdmMailSend admMailSend = new AdmMailSend();
        admMailSend.setMailType(mailType);
        admMailSend.setSourceId(admAccountContactDTO.getUserId());
        admMailSend.setReceiver(admAccountContactDTO.getEmail());
        admMailSend.setSubject(subject);
        admMailSend.setContent(content);
        admMailSend.setExpectSendTime(Instant.now());
        admMailSend.setStatus("W");
        admMailSend.setCreateUser(admAccountContactDTO.getUpdateUser());
        admMailSend.setCreateTime(admAccountContactDTO.getUpdateTime());
        admMailSend.setUpdateUser(admAccountContactDTO.getUpdateUser());
        admMailSend.setUpdateTime(admAccountContactDTO.getUpdateTime());

        return admMailSend;
    }

    /**
     * Get one admAccountContactDTO
     *
     * @param admAccountContactDTO the id of the entity.
     * @return the AdmContact.
     */
    @Transactional(readOnly = true)
    public AdmContact saveAdmContact(AdmAccountContactDTO admAccountContactDTO) {
        log.debug("Request to get AdmAccount & AdmContact : {}", admAccountContactDTO.getUserId());
        //聯絡資料檔
        AdmContact admContact = new AdmContact();
        BeanUtils.copyProperties(admAccountContactDTO, admContact);

        return admContact;
    }

    /**
     * Get one admCompanyApplyDTO
     *
     * @param admCompanyApplyDTO the id of the entity.
     * @return the AdmContact.
     */
    @Transactional(readOnly = true)
    public AdmContact saveAdmContact(AdmCompanyApplyDTO admCompanyApplyDTO) {
        log.debug("Request to get AdmContact : {}", admCompanyApplyDTO.getUserId());
        //聯絡資料檔
        AdmContact admContact = new AdmContact();
        BeanUtils.copyProperties(admCompanyApplyDTO, admContact);

        return admContact;
    }

    /**
     * Get one admCompanyApplyDTO
     *
     * @param admCompanyApplyDTO the id of the entity.
     * @return the AdmCompany.
     */
    @Transactional(readOnly = true)
    public AdmCompany saveAdmCompany(AdmCompanyApplyDTO admCompanyApplyDTO) {
        log.debug("Request to get AdmCompany : {}", admCompanyApplyDTO.getUserId());
        //公司法人資料檔
        AdmCompany admCompany = new AdmCompany();
        BeanUtils.copyProperties(admCompanyApplyDTO, admCompany);

        return admCompany;
    }

    /**
     * Save a userAccount.
     *
     * @param admAccountContactDTO the entity to save.
     * @return the persisted entity.
     */
    public AdmAccountContactDTO saveForAgentInfo(AdmAccountContactDTO admAccountContactDTO, HttpServletRequest request) {
        log.debug("Request to save AdmAccount & AdmContact : {}", admAccountContactDTO);
//        Optional<AdmAgentDTO> admAgentDTO = admAgentRepository.findById(admAccountContactDTO.getWkutNormal()).map(admAgentMapper::toDto);

        //帳號檔 AdmAccount
//        String userId = admAgentDTO.get().getPccOrgId()+"-0";
        String userId = admAccountContactDTO.getPccOrgId()+"-0";
        AdmAccount admAccount = new AdmAccount();
        admAccount.setUserId(userId);
        String randomPwd = RandomPassword.getRandomPwd(8);
        admAccount.setUserPadHash(randomPwd);
        //是否使用機關卡驗證申請
        if("Y".equals(admAccountContactDTO.getAgencyCardType())){
            admAccount.setStatus("Y");
            admAccount.setAuditStatus("Y");
            //郵件發送主檔 MailSend
            admAccountContactDTO.setUserId(userId);
            String content = formatContent(admAccountContactDTO, randomPwd, request);
            subject = "公共工程雲端系統帳號申請通知";
            AdmMailSend admMailSend = saveMailSend(admAccountContactDTO, "PWC0301", content, subject);
            admMailSendRepository.save(admMailSend);
        }else{
            admAccount.setStatus("N");
            admAccount.setAuditStatus("S");
        }
        admAccount.setUserType("A");
        admAccount.setUpdateUser(admAccountContactDTO.getUpdateUser());
        admAccount.setUpdateTime(admAccountContactDTO.getUpdateTime());

        //聯絡資料檔 AdmContact
        AdmContact admContact = saveAdmContact(admAccountContactDTO);

        admAccountRepository.save(admAccount);
        admContactRepository.save(admContact);


        return admAccountContactDTO;
    }

    /**
     * Save a userAccount.
     *
     * @param admCompanyApplyDTO the entity to save.
     * @return the persisted entity.
     */
    public AdmCompanyApplyDTO saveCompanyInfo(AdmCompanyApplyDTO admCompanyApplyDTO, HttpServletRequest request) {
        log.debug("Request to save AdmAccount & AdmContact : {}", admCompanyApplyDTO);

        // 帳號檔 AdmAcount
        AdmAccount admAccount = new AdmAccount();
        String userId = admCompanyApplyDTO.getCompIdno()+"-0";
        admAccount.setUserId(userId);
        admAccount.setCompIdno(admCompanyApplyDTO.getCompIdno());
        String randomPwd = RandomPassword.getRandomPwd(8);
        admAccount.setUserPadHash(randomPwd);
        //是否使用機關卡驗證申請
        if("Y".equals(admCompanyApplyDTO.getAgencyCardType())){
            admAccount.setStatus("Y");
            admAccount.setAuditStatus("Y");
            //郵件發送主檔 MailSend
            admCompanyApplyDTO.setUserId(userId);
            AdmAccountContactDTO admAccountContactDTO = new AdmAccountContactDTO();
            BeanUtils.copyProperties(admCompanyApplyDTO, admAccountContactDTO);
            String content = formatContent(admAccountContactDTO, randomPwd, request);
            subject = "公共工程雲端系統帳號申請通知";
            AdmMailSend admMailSend = saveMailSend(admAccountContactDTO, "PWC0302", content, subject);
            admMailSendRepository.save(admMailSend);
        }else{
            admAccount.setStatus("N");
            admAccount.setAuditStatus("S");
        }
        admAccount.setUserType("C");
        admAccount.setUpdateUser(admCompanyApplyDTO.getUpdateUser());
        admAccount.setUpdateTime(admCompanyApplyDTO.getUpdateTime());

        //聯絡資料檔 AdmContact
        AdmContact admContact = saveAdmContact(admCompanyApplyDTO);

        //公司法人資料檔 AdmCompany
        AdmCompany admCompany = saveAdmCompany(admCompanyApplyDTO);

        admAccountRepository.save(admAccount);
        admContactRepository.save(admContact);
        admCompanyRepository.save(admCompany);

        return admCompanyApplyDTO;
    }
    /**
     * Get few admAccountContactDTO
     *
     * @param admAccountContactDTO the id of the entity.
     * @return the userIds.
     */
    @Transactional(readOnly = true)
    public List<String> findAccountUserId(AdmAccountContactDTO admAccountContactDTO) {
        log.debug("Request to get AdmAccount : {}", admAccountContactDTO.getUserId());
        return admAccountRepository.findAccountUserId(admAccountContactDTO);
    }

    /**
     * Get few userManagementDTO
     *
     * @param criteria the id of the entity.
     * @return the userIds.
     */
    @Transactional(readOnly = true)
    public Page<UserManagementDTO> findAccountInfo(UserManagementCriteria criteria) {
        log.debug("Request to get AdmAccount : {}", criteria.getUserId());
        criteria.setSortBy(new String[]{"userId"});
        return admAccountRepository.findAccountInfo(criteria);
    }

}
