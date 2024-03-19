package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.domain.AdmAccount;
import gov.pcc.pwc.repository.AdmAccountRepository;
import gov.pcc.pwc.service.AdmAccountService;
import gov.pcc.pwc.service.criteria.UserManagementCriteria;
import gov.pcc.pwc.service.dto.AdmAccountContactDTO;
import gov.pcc.pwc.service.dto.AdmCompanyDTO;
import gov.pcc.pwc.service.dto.ComStatusSearchDTO;
import gov.pcc.pwc.service.dto.UserManagementDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link AdmAccount}.
 */
@RestController
@RequestMapping("/api/service")
public class AdmAccountResource {

    private final Logger log = LoggerFactory.getLogger(AdmAccountResource.class);

    private static final String ENTITY_NAME = "AdmAccount";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AdmAccountService admAccountService;

    private final AdmAccountRepository admAccountRepository;

    public AdmAccountResource(AdmAccountService admAccountService, AdmAccountRepository admAccountRepository) {
        this.admAccountService = admAccountService;
        this.admAccountRepository = admAccountRepository;
    }

    /**
     * {@code GET  /adm-account/:userId} : get the "id" userAccount.
     *
     * @param userId the id of the userAccountDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userAccountDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/adm-account/{userId}")
    public AdmAccountContactDTO getUserAccount(@PathVariable String userId) {
        log.debug("REST request to get admAccountContractDTO : {}", userId);
        return admAccountService.findOne(userId);
    }

    /**
     * {@code GET  /adm-account/:userId} : get the "id" userAccount.
     *
     * @param  admAccountContactDTO the id of the AdmAccountContactDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userAccountDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/adm-user-account")
    public AdmAccountContactDTO getUserAccountByDto(@Valid @RequestBody AdmAccountContactDTO admAccountContactDTO) {
        log.debug("REST request to get admAccountContractDTO : {}", admAccountContactDTO.getUserId());
        return admAccountService.findByUserId(admAccountContactDTO);
    }

    /**
     * {@code POST  /adm-account-save} : Create a new AdmAccount.
     *
     * @param admAccountContactDTO the AdmAccountContactDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new admAccountContactDTO, or with status {@code 400 (Bad Request)} if the AdmAccount has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/adm-account-save")
    public ResponseEntity<AdmAccountContactDTO> createAdmAccount(@Valid @RequestBody AdmAccountContactDTO admAccountContactDTO, HttpServletRequest request)
            throws URISyntaxException {
        log.debug("REST request to save AdmAccountContact : {}", admAccountContactDTO);
        AdmAccountContactDTO result = admAccountService.saveForAccountContact(admAccountContactDTO, request);
        return ResponseEntity
                .created(new URI("/api/service/adm-account-save/" + result.getUserId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getUserId()))
                .body(result);
    }

    /**
     * {@code POST  /adm-account-save} : Create a new AdmAccount.
     *
     * @param admAccountContactDTO the AdmAccountContactDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new admAccountContactDTO, or with status {@code 400 (Bad Request)} if the AdmAccount has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/adm-change-password")
    public ResponseEntity<AdmAccount> changeAdmPassword(@Valid @RequestBody AdmAccountContactDTO admAccountContactDTO)
            throws URISyntaxException {
        log.debug("REST request to save AdmAccountContact : {}", admAccountContactDTO);
        AdmAccount result = admAccountService.savePassWord(admAccountContactDTO);
        return ResponseEntity
                .created(new URI("/api/service/adm-change-password/" + result.getUserId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getUserId()))
                .body(result);
    }

    /**
     * {@code POST  /adm-account-contact/save} : Create a new AdmAccount.
     *
     * @param admAccountContactDTO the AdmAccountContactDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new admAccountContactDTO, or with status {@code 400 (Bad Request)} if the AdmAccount has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/adm-account-contact/save")
    public ResponseEntity<AdmAccountContactDTO> saveAdmAccountContact(@Valid @RequestBody AdmAccountContactDTO admAccountContactDTO, HttpServletRequest request)
            throws URISyntaxException {
        log.debug("REST request to save AdmAccountContact : {}", admAccountContactDTO);
        AdmAccountContactDTO result = admAccountService.saveForAgentInfo(admAccountContactDTO, request);
        return ResponseEntity
                .created(new URI("/api/service/adm-account-contact/save/" + result.getUserId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getUserId()))
                .body(result);
    }

    /**
     * {@code GET  /adm-account/getUserId} : get the "userId" AdmAccount.
     *
     * @param  admAccountContactDTO the id of the AdmAccountContactDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userAccountDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/adm-account/getUserId")
    public List<String> getAccountUserId(@Valid @RequestBody AdmAccountContactDTO admAccountContactDTO) {
        log.debug("REST request to get admAccountContractDTO : {}", admAccountContactDTO.getLoginUserId());
        return admAccountService.findAccountUserId(admAccountContactDTO);
    }

    /**
     * {@code GET  /adm-account/getAccountInfo} : get the "userId" AdmAccount.
     *
     * @param  criteria the id of the AdmAccountContactDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userAccountDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/adm-account/getAccountInfo")
    public Page<UserManagementDTO> getAccountInfo(@Valid @RequestBody UserManagementCriteria criteria) {
        log.debug("REST request to get admAccountContractDTO : {}", criteria.getUserId());
        return admAccountService.findAccountInfo(criteria);
    }

 
}
