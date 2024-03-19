package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.repository.PwbRoleApplyRepository;
import gov.pcc.pwc.service.AdmAgentService;
import gov.pcc.pwc.service.PwbRoleApplyService;
import gov.pcc.pwc.service.dto.PwbRoleApplyDTO;
import gov.pcc.pwc.service.dto.PwbRoleDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * REST controller for managing {@link gov.pcc.pwc.domain.PwbRoleApply}.
 */
@RestController
@RequestMapping("/api")
public class PwbRoleApplyResource {

    private final Logger log = LoggerFactory.getLogger(PwbRoleApplyResource.class);

    private static final String ENTITY_NAME = "pwbRoleApply";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PwbRoleApplyService pwbRoleApplyService;

    private final PwbRoleApplyRepository pwbRoleApplyRepository;

    private final AdmAgentService admAgentService;

    public PwbRoleApplyResource(PwbRoleApplyService pwbRoleApplyService, PwbRoleApplyRepository pwbRoleApplyRepository, AdmAgentService admAgentService) {
        this.pwbRoleApplyService = pwbRoleApplyService;
        this.pwbRoleApplyRepository = pwbRoleApplyRepository;
        this.admAgentService = admAgentService;
    }

    /**
     * {@code POST  /pwb-role-applies} : Create a new pwbRoleApply.
     *
     * @param pwbRoleApplyDTO the pwbRoleApplyDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pwbRoleApplyDTO, or with status {@code 400 (Bad Request)} if the pwbRoleApply has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pwb-role-applies")
    public ResponseEntity<PwbRoleApplyDTO> createPwbRoleApply(@Valid @RequestBody PwbRoleApplyDTO pwbRoleApplyDTO)
        throws URISyntaxException {
        log.debug("REST request to save PwbRoleApply : {}", pwbRoleApplyDTO);
        if (pwbRoleApplyDTO.getId() != null) {
            throw new BadRequestAlertException("A new pwbRoleApply cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PwbRoleApplyDTO result = pwbRoleApplyService.saveByApplyAndMail(pwbRoleApplyDTO);
        return ResponseEntity
            .created(new URI("/api/pwb-role-applies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pwb-role-applies/:id} : Updates an existing pwbRoleApply.
     *
     * @param id the id of the pwbRoleApplyDTO to save.
     * @param pwbRoleApplyDTO the pwbRoleApplyDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pwbRoleApplyDTO,
     * or with status {@code 400 (Bad Request)} if the pwbRoleApplyDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pwbRoleApplyDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pwb-role-applies/{id}")
    public ResponseEntity<PwbRoleApplyDTO> updatePwbRoleApply(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody PwbRoleApplyDTO pwbRoleApplyDTO
    ) throws URISyntaxException {
        log.debug("REST request to update PwbRoleApply : {}, {}", id, pwbRoleApplyDTO);
        if (pwbRoleApplyDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, pwbRoleApplyDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!pwbRoleApplyRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        PwbRoleApplyDTO result = pwbRoleApplyService.saveByApplyAndMail(pwbRoleApplyDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pwbRoleApplyDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /pwb-role-applies/:id} : Partial updates given fields of an existing pwbRoleApply, field will ignore if it is null
     *
     * @param id the id of the pwbRoleApplyDTO to save.
     * @param pwbRoleApplyDTO the pwbRoleApplyDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pwbRoleApplyDTO,
     * or with status {@code 400 (Bad Request)} if the pwbRoleApplyDTO is not valid,
     * or with status {@code 404 (Not Found)} if the pwbRoleApplyDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the pwbRoleApplyDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/pwb-role-applies/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<PwbRoleApplyDTO> partialUpdatePwbRoleApply(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody PwbRoleApplyDTO pwbRoleApplyDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update PwbRoleApply partially : {}, {}", id, pwbRoleApplyDTO);
        if (pwbRoleApplyDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, pwbRoleApplyDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!pwbRoleApplyRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PwbRoleApplyDTO> result = pwbRoleApplyService.partialUpdate(pwbRoleApplyDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pwbRoleApplyDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /pwb-role-applies} : get all the pwbRoleApplies.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pwbRoleApplies in body.
     */
    @GetMapping("/pwb-role-applies")
    public List<PwbRoleApplyDTO> getAllPwbRoleApplies() {
        log.debug("REST request to get all PwbRoleApplies");
        return pwbRoleApplyService.findAll();
    }

    /**
     * {@code GET  /pwb-role-applies/:id} : get the "id" pwbRoleApply.
     *
     * @param id the id of the pwbRoleApplyDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pwbRoleApplyDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pwb-role-applies/{id}")
    public ResponseEntity<PwbRoleApplyDTO> getPwbRoleApply(@PathVariable Long id) {
        log.debug("REST request to get PwbRoleApply : {}", id);
        Optional<PwbRoleApplyDTO> pwbRoleApplyDTO = pwbRoleApplyService.findOne(id);
        return ResponseUtil.wrapOrNotFound(pwbRoleApplyDTO);
    }

    /**
     * {@code DELETE  /pwb-role-applies/:id} : delete the "id" pwbRoleApply.
     *
     * @param id the id of the pwbRoleApplyDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pwb-role-applies/{id}")
    public ResponseEntity<Void> deletePwbRoleApply(@PathVariable Long id) {
        log.debug("REST request to delete PwbRoleApply : {}", id);
        pwbRoleApplyService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code POST  /pwb-role-applies} : Create a new pwbRoleApply.
     *
     * @param pwbRoleApplyDTO the pwbRoleApplyDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pwbRoleApplyDTO, or with status {@code 400 (Bad Request)} if the pwbRoleApply has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pwb-role-applies/query-module")
    public List<PwbRoleApplyDTO> queryRoleApplyModule(@Valid @RequestBody PwbRoleApplyDTO pwbRoleApplyDTO)
            throws URISyntaxException {
        log.debug(" Query PwbRoleApply  : {}", pwbRoleApplyDTO);
        return pwbRoleApplyService.findRoleApplyByCriteria(pwbRoleApplyDTO);
    }

    /**
     * {@code PUT /pwb-role-apply/disapprove-apply/:id} : Updates an existing pwbRoleApply.
     *
     * @param id the id of the pwbRoleApplyDTO to save.
     * @param pwbRoleDTO the pwbRoleApplyDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pwbRoleApplyDTO,
     * or with status {@code 400 (Bad Request)} if the pwbRoleApplyDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pwbRoleApplyDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pwb-role-apply/disapprove-apply/{id}")
    public ResponseEntity<PwbRoleApplyDTO> disapproveApply(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody PwbRoleDTO pwbRoleDTO
    ) throws URISyntaxException {
        log.debug("REST request to update PwbRoleApply : {}, {}", id, pwbRoleDTO);
        if (pwbRoleDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, pwbRoleDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!pwbRoleApplyRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        //更新pwb_role_apply的資料
        PwbRoleApplyDTO result = pwbRoleApplyService.saveApplyResult(pwbRoleDTO);

        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pwbRoleDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code POST  /pwb-role-apply/approve-apply/:approveId} : Create a new pwbRoleApply.
     *
     * @param pwbRoleDTO the pwbRoleApplyDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pwbRoleApplyDTO, or with status {@code 400 (Bad Request)} if the pwbRoleApply has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pwb-role-apply/approve-apply/{approveId}")
    public ResponseEntity<PwbRoleApplyDTO> approveApply(
        @PathVariable(value = "approveId", required = false) final String approveId,
        @Valid @RequestBody PwbRoleDTO pwbRoleDTO)
        throws URISyntaxException {
        log.debug("REST request to save PwbRoleApply : {}", pwbRoleDTO);

        if (pwbRoleDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        //更新pwb_role_apply的資料
        PwbRoleApplyDTO result = pwbRoleApplyService.saveApplyResult(pwbRoleDTO);

        //新增一筆資料到adm_account_role
        pwbRoleApplyService.createAccountRole(approveId, pwbRoleDTO);

        return ResponseEntity
            .created(new URI("/api/pwb-role-apply/approve-apply/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code Delete /pwb-role-apply/cancel-apply/:userId} : Updates an existing pwbRoleApply & delete the "userId" admAccountRole
     *
     * @param userId the userId of the pwbRoleApplyDTO to save.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pwb-role-apply/cancel-apply/{userId}")
    public ResponseEntity<Void> cancelApply(@PathVariable String userId) {


        // 刪除adm_Account_Role的資料
        pwbRoleApplyService.deleteAccountRole(userId);

        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, userId.toString()))
            .build();
    }

}
