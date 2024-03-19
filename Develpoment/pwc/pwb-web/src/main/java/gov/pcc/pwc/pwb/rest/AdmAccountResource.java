package gov.pcc.pwc.pwb.rest;

import gov.pcc.pwc.MessageCodes;
import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.domain.AdmAccount;
import gov.pcc.pwc.repository.AdmAccountRepository;
import gov.pcc.pwc.service.AdmAccountService;
import gov.pcc.pwc.service.criteria.AdmAccountContactCriteria;
import gov.pcc.pwc.service.dto.AdmAccountContactDTO;
import gov.pcc.pwc.utils.PwcHeadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

/**
 * REST controller for managing {@link AdmAccount}.
 */
@RestController
@RequestMapping("/api")
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
     * {@code POST  /adm-account} : Create a new admAccount and admContract.
     *
     * @param admAccountContractDTO the admAccountContractDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new admAccountContractDTO, or with status {@code 400 (Bad Request)} if the admAccount has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/adm-account")
    public ResponseEntity<AdmAccountContactDTO> createUserAccount(@Valid @RequestBody AdmAccountContactDTO admAccountContractDTO)
        throws URISyntaxException {
        log.debug("REST request to save AdmAccount and AdmContact : {}", admAccountContractDTO);
        admAccountContractDTO.setNo(admAccountRepository.getBiggestNo());
        admAccountContractDTO.setUserId(admAccountContractDTO.getUserId() + admAccountContractDTO.getNo());
        admAccountContractDTO.setUserPadHash(admAccountContractDTO.getUserId() + "@ABC" + Math.round(Math.random() * 100000));
        if (admAccountRepository.existsById(admAccountContractDTO.getUserId())) {
            throw new BadRequestAlertException("the userId had already used", ENTITY_NAME, "idexists");
        }
        AdmAccountContactDTO result = admAccountService.save(admAccountContractDTO);
        return ResponseEntity
            .created(new URI("/api/adm-account/" + result.getUserId()))
            .headers(PwcHeadUtil.createAlert(MessageCodes.PWB_AMD04_0001_S(result.getUserId())))
            .body(result);
    }

    /**
     * {@code POST  /adm-account/application} : Apply a new admAccount and admContract.
     *
     * @param admAccountContractDTO the admAccountContractDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new admAccountContractDTO, or with status {@code 400 (Bad Request)} if the admAccount has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/service/adm-account/application")
    public ResponseEntity<AdmAccountContactDTO> serviceApplyUserAccount(@Valid @RequestBody AdmAccountContactDTO admAccountContractDTO)
        throws URISyntaxException {
        log.debug("REST request to save AdmAccount and AdmContact : {}", admAccountContractDTO);
        admAccountContractDTO.setNo(admAccountRepository.getBiggestNo());
        admAccountContractDTO.setUserId(admAccountContractDTO.getUserId() + admAccountContractDTO.getNo());
        admAccountContractDTO.setUserPadHash(admAccountContractDTO.getUserId() + "@ABC" + Math.round(Math.random() * 100000));
        if (admAccountRepository.existsById(admAccountContractDTO.getUserId())) {
            throw new BadRequestAlertException("the userId had already used", ENTITY_NAME, "idexists");
        }
        AdmAccountContactDTO result = admAccountService.save(admAccountContractDTO);
        return ResponseEntity
            .created(new URI("/api/adm-account/application" + result.getUserId()))
            .headers(PwcHeadUtil.createAlert(MessageCodes.PWB_AMD02_0001_S(result.getUserId())))
            .body(result);
    }

    /**
     * {@code PUT  /adm-account/audit/:id} : audit an applying userAccount.
     *
     * @param userId the id of the admAccountContractDTO to save.
     * @param admAccountContractDTO the admAccountContractDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated admAccountContractDTO,
     * or with status {@code 400 (Bad Request)} if the admAccountContractDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the admAccountContractDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/adm-account/audit/{userId}")
    public ResponseEntity<AdmAccountContactDTO> auditUserAccount(
        @PathVariable(value = "userId", required = false) final String userId,
        @Valid @RequestBody AdmAccountContactDTO admAccountContractDTO
    ) throws URISyntaxException {
        log.debug("REST request to audit admAccountContract : {}, {}", userId, admAccountContractDTO);
        if (admAccountContractDTO.getUserId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(userId, admAccountContractDTO.getUserId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!admAccountRepository.existsById(userId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        AdmAccountContactDTO result = admAccountService.save(admAccountContractDTO);
        return ResponseEntity
            .ok()
            .headers(PwcHeadUtil.createAlert(MessageCodes.PWB_AMD03_0001_S(admAccountContractDTO.getUserId())))
            .body(result);
    }

    /**
     * {@code PUT  /adm-account/:id} : Updates an existing userAccount.
     *
     * @param userId the id of the admAccountContractDTO to save.
     * @param admAccountContractDTO the admAccountContractDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated admAccountContractDTO,
     * or with status {@code 400 (Bad Request)} if the admAccountContractDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the admAccountContractDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/adm-account/{userId}")
    public ResponseEntity<AdmAccountContactDTO> updateUserAccount(
        @PathVariable(value = "userId", required = false) final String userId,
        @Valid @RequestBody AdmAccountContactDTO admAccountContractDTO
    ) throws URISyntaxException {
        log.debug("REST request to update admAccountContract : {}, {}", userId, admAccountContractDTO);
        if (admAccountContractDTO.getUserId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(userId, admAccountContractDTO.getUserId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!admAccountRepository.existsById(userId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        AdmAccountContactDTO result = admAccountService.save(admAccountContractDTO);
        return ResponseEntity
            .ok()
            .headers(PwcHeadUtil.createAlert(MessageCodes.PWB_AMD04_0002_S(admAccountContractDTO.getUserId())))
            .body(result);
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
     * {@code POST  /adm-account/criteria} : get admAccountContracts by criteria.
     *
     * @param criteria the criteria of the admAccountContractDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the admAccountContractDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/adm-account/criteria")
    public List<AdmAccountContactDTO> getUserAccountByCriteria(@RequestBody AdmAccountContactDTO criteria) {
        log.debug("REST request to get admAccountContractDTOs : {}", criteria);
        return admAccountService.findByCriteria(criteria);
    }

    /**
     * {@code POST  /adm-account/criteria-jpa} : get admAccountContracts by criteria.
     *
     * @param criteria the criteria of the AdmAccountContactDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the AdmAccountContactDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/adm-account/criteria-jpa")
    public Page<AdmAccountContactDTO> getUserAccountByCriteria(@Valid @RequestBody AdmAccountContactCriteria criteria) {
        log.debug("REST request to get AdmAccountContactDTO : {}", criteria);
        return admAccountService.findByCriteria(criteria);
    }

    /**
     * {@code DELETE  /adm-account/:id} : delete the "id" admAccount.
     *
     * @param id the id of the admAccount to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/adm-account/{id}")
    public ResponseEntity<Void> deleteAdmAccount(@PathVariable String id) {
        log.debug("REST request to delete AdmAccount : {}", id);
        admAccountService.deleteById(id);
        return ResponseEntity.ok().headers(PwcHeadUtil.createAlert(MessageCodes.PWB_AMD03_0002_S(id))).body(null);
    }

    /**
     * {@code DELETE  /adm-account/:id} : delete the "id" admAccount.
     *
     * @param id the id of the admAccount to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
//    @DeleteMapping("/adm-account/{id}")
//    public ResponseEntity<Void> addAdmAccount(@PathVariable String id) {
//        log.debug("REST request to delete AdmAccount : {}", id);
//        admAccountService.deleteById(id);
//        return ResponseEntity.ok().headers(PwcHeadUtil.createAlert(MessageCodes.PWB_AMD03_0002_S(id))).body(null);
//    }


}
