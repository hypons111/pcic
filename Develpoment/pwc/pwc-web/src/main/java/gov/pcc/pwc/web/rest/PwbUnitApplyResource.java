package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.repository.PwbUnitApplyRepository;
import gov.pcc.pwc.service.PwbUnitApplyService;
import gov.pcc.pwc.service.criteria.PwbRoleApproveForUnitCriteria;
import gov.pcc.pwc.service.criteria.ProOrgSuggestionCriteria;
import gov.pcc.pwc.service.criteria.PwbUnitApplyCriteria;
import gov.pcc.pwc.service.dto.ComStatusSearchDTO;
import gov.pcc.pwc.service.dto.PwbRoleApplyDTO;
import gov.pcc.pwc.service.dto.PwbRoleDTO;
import gov.pcc.pwc.service.dto.PwbUnitApplyDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
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
 * REST controller for managing {@link gov.pcc.pwc.domain.PwbUnitApply}.
 */
@RestController
@RequestMapping("/api")
public class PwbUnitApplyResource {

    private final Logger log = LoggerFactory.getLogger(PwbUnitApplyResource.class);

    private static final String ENTITY_NAME = "pwbUnitApply";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PwbUnitApplyService pwbUnitApplyService;

    private final PwbUnitApplyRepository pwbUnitApplyRepository;

    public PwbUnitApplyResource(PwbUnitApplyService pwbUnitApplyService, PwbUnitApplyRepository pwbUnitApplyRepository) {
        this.pwbUnitApplyService = pwbUnitApplyService;
        this.pwbUnitApplyRepository = pwbUnitApplyRepository;
    }

    /**
     * {@code POST  /pwb-unit-applies} : Create a new pwbUnitApply.
     *
     * @param pwbUnitApplyDTO the pwbUnitApplyDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pwbUnitApplyDTO, or with status {@code 400 (Bad Request)} if the pwbUnitApply has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pwb-unit-applies")
    public ResponseEntity<PwbUnitApplyDTO> createPwbUnitApply(@Valid @RequestBody PwbUnitApplyDTO pwbUnitApplyDTO)
        throws URISyntaxException {
        log.debug("REST request to save PwbUnitApply : {}", pwbUnitApplyDTO);
        if (pwbUnitApplyDTO.getId() != null) {
            throw new BadRequestAlertException("A new pwbUnitApply cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PwbUnitApplyDTO result = pwbUnitApplyService.save(pwbUnitApplyDTO);
        return ResponseEntity
            .created(new URI("/api/pwb-unit-applies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pwb-unit-applies/:id} : Updates an existing pwbUnitApply.
     *
     * @param id the id of the pwbUnitApplyDTO to save.
     * @param pwbUnitApplyDTO the pwbUnitApplyDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pwbUnitApplyDTO,
     * or with status {@code 400 (Bad Request)} if the pwbUnitApplyDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pwbUnitApplyDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pwb-unit-applies/{id}")
    public ResponseEntity<PwbUnitApplyDTO> updatePwbUnitApply(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody PwbUnitApplyDTO pwbUnitApplyDTO
    ) throws URISyntaxException {
        log.debug("REST request to update PwbUnitApply : {}, {}", id, pwbUnitApplyDTO);
        if (pwbUnitApplyDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, pwbUnitApplyDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!pwbUnitApplyRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        PwbUnitApplyDTO result = pwbUnitApplyService.save(pwbUnitApplyDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pwbUnitApplyDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /pwb-unit-applies/:id} : Partial updates given fields of an existing pwbUnitApply, field will ignore if it is null
     *
     * @param id the id of the pwbUnitApplyDTO to save.
     * @param pwbUnitApplyDTO the pwbUnitApplyDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pwbUnitApplyDTO,
     * or with status {@code 400 (Bad Request)} if the pwbUnitApplyDTO is not valid,
     * or with status {@code 404 (Not Found)} if the pwbUnitApplyDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the pwbUnitApplyDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/pwb-unit-applies/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<PwbUnitApplyDTO> partialUpdatePwbUnitApply(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody PwbUnitApplyDTO pwbUnitApplyDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update PwbUnitApply partially : {}, {}", id, pwbUnitApplyDTO);
        if (pwbUnitApplyDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, pwbUnitApplyDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!pwbUnitApplyRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PwbUnitApplyDTO> result = pwbUnitApplyService.partialUpdate(pwbUnitApplyDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pwbUnitApplyDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /pwb-unit-applies} : get all the pwbUnitApplies.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pwbUnitApplies in body.
     */
    @GetMapping("/pwb-unit-applies")
    public List<PwbUnitApplyDTO> getAllPwbUnitApplies() {
        log.debug("REST request to get all PwbUnitApplies");
        return pwbUnitApplyService.findAll();
    }

    /**
     * {@code GET  /pwb-unit-applies/:id} : get the "id" pwbUnitApply.
     *
     * @param id the id of the pwbUnitApplyDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pwbUnitApplyDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pwb-unit-applies/{id}")
    public ResponseEntity<PwbUnitApplyDTO> getPwbUnitApply(@PathVariable Long id) {
        log.debug("REST request to get PwbUnitApply : {}", id);
        Optional<PwbUnitApplyDTO> pwbUnitApplyDTO = pwbUnitApplyService.findOne(id);
        return ResponseUtil.wrapOrNotFound(pwbUnitApplyDTO);
    }

    /**
     * {@code DELETE  /pwb-unit-applies/:id} : delete the "id" pwbUnitApply.
     *
     * @param id the id of the pwbUnitApplyDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pwb-unit-applies/{id}")
    public ResponseEntity<Void> deletePwbUnitApply(@PathVariable Long id) {
        log.debug("REST request to delete PwbUnitApply : {}", id);
        pwbUnitApplyService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code POST  /pwb-unit-applies} : Create a new pwbRoleApply.
     *
     * @param pwbUnitApplyDTO the pwbRoleApplyDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pwbRoleApplyDTO, or with status {@code 400 (Bad Request)} if the pwbRoleApply has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pwb-unit-apply/query-module")
    public Page<PwbUnitApplyDTO> queryUnitApplyModule(@Valid @RequestBody PwbUnitApplyDTO pwbUnitApplyDTO)
            throws URISyntaxException {
        log.debug(" Query PwbRoleApply  : {}", pwbUnitApplyDTO);
        return pwbUnitApplyService.findUnitApplyByCriteria(pwbUnitApplyDTO);
    }

    /**
     * {@code PUT  /pwb-role-applies/:id} : Updates an existing pwbRoleApply.
     *
     * @param id the id of the pwbUnitApplyDTO to save.
     * @param pwbUnitApplyDTO the pwbUnitApplyDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pwbUnitApplyDTO,
     * or with status {@code 400 (Bad Request)} if the pwbUnitApplyDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pwbUnitApplyDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pwb-unit-applies-update/{id}")
    public ResponseEntity<PwbUnitApplyDTO> updatePwbUnitApplies(
            @PathVariable(value = "id", required = false) final Long id,
            @Valid @RequestBody PwbUnitApplyDTO pwbUnitApplyDTO
    ) throws URISyntaxException {
        log.debug("REST request to update PwbRoleApply : {}, {}", id, pwbUnitApplyDTO);
        if (pwbUnitApplyDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, pwbUnitApplyDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!pwbUnitApplyRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        PwbUnitApplyDTO result = pwbUnitApplyService.saveByApplyAndMail(pwbUnitApplyDTO);
        return ResponseEntity
                .ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pwbUnitApplyDTO.getId().toString()))
                .body(result);
    }

    /**
     * {@code POST  /pwb-unit-applies} : Create a new pwbUnitApply.
     *
     * @param pwbUnitApplyDTO the pwbUnitApplyDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pwbUnitApplyDTO, or with status {@code 400 (Bad Request)} if the pwbUnitApply has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pwb-unit-applies-save")
    public ResponseEntity<PwbUnitApplyDTO> savePwbUnitApply(@Valid @RequestBody PwbUnitApplyDTO pwbUnitApplyDTO)
            throws URISyntaxException {
        log.debug("REST request to save PwbRoleApply : {}", pwbUnitApplyDTO);
        if (pwbUnitApplyDTO.getId() != null) {
            throw new BadRequestAlertException("A new pwbRoleApply cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PwbUnitApplyDTO result = pwbUnitApplyService.saveByApplyAndMail(pwbUnitApplyDTO);
        return ResponseEntity
                .created(new URI("/pwb-unit-applies-save/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * {@code POST  /search-unit-list} : get pwbRoleDTO by criteria.
     *
     * @param criteria the criteria of the PwbUnitDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the PwbUnitDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/search-unit-list")
    public Page<PwbUnitApplyDTO> getSearchUnitList(@Valid @RequestBody PwbRoleApproveForUnitCriteria criteria) throws InterruptedException {
        log.debug("REST request to get PwbRoleApprove : {}", criteria);
        return pwbUnitApplyService.findByApproveCriteria(criteria);
    }

    /**
     * {@code POST  /approve-unit-apply/:approveId} : Create a new pwbRoleApply.
     *
     * @param PwbUnitApplyDTO the pwbUnitApplyDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pwbRoleApplyDTO, or with status {@code 400 (Bad Request)} if the pwbUnitApply has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/approve-unit-apply/{approveId}")
    public ResponseEntity<PwbUnitApplyDTO> approveUnitApply(
            @PathVariable(value = "approveId", required = false) final String approveId,
            @Valid @RequestBody PwbUnitApplyDTO PwbUnitApplyDTO)
            throws URISyntaxException {
        log.debug("REST request to save PwbUnitApply : {}", PwbUnitApplyDTO);

        if (PwbUnitApplyDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        //更新pwb_unit_apply的資料
        PwbUnitApplyDTO result = pwbUnitApplyService.saveUnitApplyResult(PwbUnitApplyDTO, approveId);

        return ResponseEntity
                .created(new URI("/api/approve-unit-apply/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * {@code Delete  /cancel-apply/:unitId} : Updates an existing pwbRoleApply & delete the "userId" admAccountRole
     *
     * @param unitId the userId of the pwbRoleApplyDTO to save.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/cancel-unit-apply/{unitId}")
    public ResponseEntity<Void> cancelUnitApply(@PathVariable String unitId) {

        // 刪除adm_Unit_Role的資料
        pwbUnitApplyService.deleteUnitRole(unitId);
        // 刪除adm_Account_Role的資料
        pwbUnitApplyService.deleteAccountRole(unitId);

        return ResponseEntity
                .noContent()
                .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, unitId.toString()))
                .build();
    }

    /**
     * {@code PUT  /disapprove-unit-apply/:id} : Updates an existing pwbUnitApply.
     *
     * @param id the id of the pwbRoleApplyDTO to save.
     * @param pwbUnitApplyDTO the pwbUnitApplyDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pwbUnitApplyDTO,
     * or with status {@code 400 (Bad Request)} if the pwbRoleApplyDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pwbUnitApplyDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/disapprove-unit-apply/{id}")
    public ResponseEntity<PwbUnitApplyDTO> disapproveUnitApply(
            @PathVariable(value = "id", required = false) final Long id,
            @Valid @RequestBody PwbUnitApplyDTO pwbUnitApplyDTO
    ) throws URISyntaxException {
        log.debug("REST request to update PwbUnitApply : {}, {}", id, pwbUnitApplyDTO);
        if (pwbUnitApplyDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, pwbUnitApplyDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!pwbUnitApplyRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        //更新pwb_role_apply的資料
        PwbUnitApplyDTO result = pwbUnitApplyService.saveUnitApplyResult(pwbUnitApplyDTO);

        return ResponseEntity
                .ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pwbUnitApplyDTO.getId().toString()))
                .body(result);
    }

    /**
     * {@code POST /pwb-unit-apply/getApplyList} : get pwbUnitApply by criteria.
     *
     * @param criteria the criteria of the PwbUnitApplyCriteria to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the PwbUnitApplyDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/pwb-unit-apply/getApplyList")
    public Page<PwbUnitApplyDTO> getApplyList(@Valid @RequestBody PwbUnitApplyCriteria criteria) throws InterruptedException {
        log.debug("REST request to get PwbUnitApplyCriteria : {}", criteria);
        return pwbUnitApplyService.findApplyList(criteria);
    }

    @PutMapping("/pwb-unit-apply/disapprove/{id}")
    public ResponseEntity<PwbUnitApplyDTO> disapproveApply(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody PwbUnitApplyDTO pwbUnitApplyDTO
    ) throws URISyntaxException {
        log.debug("REST request to update PwbUnitApply : ", id, pwbUnitApplyDTO);
        if (pwbUnitApplyDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, pwbUnitApplyDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }
        if (!pwbUnitApplyRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        //更新PWB_UNIT_APPLY的資料
        PwbUnitApplyDTO result = pwbUnitApplyService.saveUnitApply(pwbUnitApplyDTO);

        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pwbUnitApplyDTO.getId().toString()))
            .body(result);
    }

    @PostMapping("/pwb-unit-apply/approve/{loginUserId}")
    public ResponseEntity<PwbUnitApplyDTO> approveApply(
        @PathVariable(value = "loginUserId", required = false) final String loginUserId,
        @Valid @RequestBody PwbUnitApplyDTO pwbUnitApplyDTO)
        throws URISyntaxException {
        log.debug("REST request to save PwbUnitApply : ", pwbUnitApplyDTO);

        if (pwbUnitApplyDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        //更新PWB_UNIT_APPLY的資料
        PwbUnitApplyDTO result = pwbUnitApplyService.saveUnitApply(pwbUnitApplyDTO);

        //新增一筆資料到PWB_UNIT_ROLE
        pwbUnitApplyService.addUnitRole(loginUserId, pwbUnitApplyDTO);

        return ResponseEntity
            .created(new URI("/api/pwb-unit-apply/approve-apply/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @DeleteMapping("/pwb-unit-apply/cancel/{unitId}")
    public ResponseEntity<Void> cancelApply(@PathVariable String unitId) {

        // 刪除PWB_UNIT_ROLE的資料
        pwbUnitApplyService.cancelUnitRole(unitId);

        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, unitId.toString()))
            .build();
    }


}
