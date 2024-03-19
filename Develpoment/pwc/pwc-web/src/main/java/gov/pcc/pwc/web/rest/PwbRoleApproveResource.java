package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.repository.PwbRoleApproveRepository;
import gov.pcc.pwc.service.PwbRoleApproveService;
import gov.pcc.pwc.service.criteria.PwbRoleApproveCriteria;
import gov.pcc.pwc.service.dto.PwbRoleApproveDTO;
import gov.pcc.pwc.service.dto.PwbRoleDTO;
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
 * REST controller for managing {@link gov.pcc.pwc.domain.PwbRoleApprove}.
 */
@RestController
@RequestMapping("/api")
public class PwbRoleApproveResource {

    private final Logger log = LoggerFactory.getLogger(PwbRoleApproveResource.class);

    private static final String ENTITY_NAME = "pwbRoleApprove";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PwbRoleApproveService pwbRoleApproveService;

    private final PwbRoleApproveRepository pwbRoleApproveRepository;

    public PwbRoleApproveResource(PwbRoleApproveService pwbRoleApproveService, PwbRoleApproveRepository pwbRoleApproveRepository) {
        this.pwbRoleApproveService = pwbRoleApproveService;
        this.pwbRoleApproveRepository = pwbRoleApproveRepository;
    }

    /**
     * {@code POST  /pwb-role-approves} : Create a new pwbRoleApprove.
     *
     * @param pwbRoleApproveDTO the pwbRoleApproveDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pwbRoleApproveDTO, or with status {@code 400 (Bad Request)} if the pwbRoleApprove has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pwb-role-approves")
    public ResponseEntity<PwbRoleApproveDTO> createPwbRoleApprove(@Valid @RequestBody PwbRoleApproveDTO pwbRoleApproveDTO)
        throws URISyntaxException {
        log.debug("REST request to save PwbRoleApprove : {}", pwbRoleApproveDTO);
        if (pwbRoleApproveDTO.getId() != null) {
            throw new BadRequestAlertException("A new pwbRoleApprove cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PwbRoleApproveDTO result = pwbRoleApproveService.save(pwbRoleApproveDTO);
        return ResponseEntity
            .created(new URI("/api/pwb-role-approves/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pwb-role-approves/:id} : Updates an existing pwbRoleApprove.
     *
     * @param id the id of the pwbRoleApproveDTO to save.
     * @param pwbRoleApproveDTO the pwbRoleApproveDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pwbRoleApproveDTO,
     * or with status {@code 400 (Bad Request)} if the pwbRoleApproveDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pwbRoleApproveDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pwb-role-approves/{id}")
    public ResponseEntity<PwbRoleApproveDTO> updatePwbRoleApprove(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody PwbRoleApproveDTO pwbRoleApproveDTO
    ) throws URISyntaxException {
        log.debug("REST request to update PwbRoleApprove : {}, {}", id, pwbRoleApproveDTO);
        if (pwbRoleApproveDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, pwbRoleApproveDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!pwbRoleApproveRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        PwbRoleApproveDTO result = pwbRoleApproveService.save(pwbRoleApproveDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pwbRoleApproveDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /pwb-role-approves/:id} : Partial updates given fields of an existing pwbRoleApprove, field will ignore if it is null
     *
     * @param id the id of the pwbRoleApproveDTO to save.
     * @param pwbRoleApproveDTO the pwbRoleApproveDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pwbRoleApproveDTO,
     * or with status {@code 400 (Bad Request)} if the pwbRoleApproveDTO is not valid,
     * or with status {@code 404 (Not Found)} if the pwbRoleApproveDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the pwbRoleApproveDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/pwb-role-approves/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<PwbRoleApproveDTO> partialUpdatePwbRoleApprove(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody PwbRoleApproveDTO pwbRoleApproveDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update PwbRoleApprove partially : {}, {}", id, pwbRoleApproveDTO);
        if (pwbRoleApproveDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, pwbRoleApproveDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!pwbRoleApproveRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PwbRoleApproveDTO> result = pwbRoleApproveService.partialUpdate(pwbRoleApproveDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pwbRoleApproveDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /pwb-role-approves} : get all the pwbRoleApproves.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pwbRoleApproves in body.
     */
    @GetMapping("/pwb-role-approves")
    public List<PwbRoleApproveDTO> getAllPwbRoleApproves() {
        log.debug("REST request to get all PwbRoleApproves");
        return pwbRoleApproveService.findAll();
    }

    /**
     * {@code GET  /pwb-role-approves/:id} : get the "id" pwbRoleApprove.
     *
     * @param id the id of the pwbRoleApproveDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pwbRoleApproveDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pwb-role-approves/{id}")
    public ResponseEntity<PwbRoleApproveDTO> getPwbRoleApprove(@PathVariable Long id) {
        log.debug("REST request to get PwbRoleApprove : {}", id);
        Optional<PwbRoleApproveDTO> pwbRoleApproveDTO = pwbRoleApproveService.findOne(id);
        return ResponseUtil.wrapOrNotFound(pwbRoleApproveDTO);
    }

    /**
     * {@code DELETE  /pwb-role-approves/:id} : delete the "id" pwbRoleApprove.
     *
     * @param id the id of the pwbRoleApproveDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pwb-role-approves/{id}")
    public ResponseEntity<Void> deletePwbRoleApprove(@PathVariable Long id) {
        log.debug("REST request to delete PwbRoleApprove : {}", id);
        pwbRoleApproveService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }


    /**
     * {@code POST  /pwb-role-approve/getModuleType/:userId} : get the "moduleType" pwbRoleApprove.
     *
     * @param userId the userId of the pwbRoleDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the moduleType, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/pwb-role-approve/getModuleType/{userId}")
    public List<String> getModuleType(@PathVariable String userId) {
        log.debug("REST request to get moduleType : {}", userId);
        return pwbRoleApproveService.findByUserId(userId);
    }


    /**
     * {@code POST  /pwb-role-approve/getSearchList} : get pwbRoleDTO by criteria.
     *
     * @param criteria the criteria of the pwbRoleDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pwbRoleDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/pwb-role-approve/getSearchList")
    public Page<PwbRoleDTO> getSearchList(@Valid @RequestBody PwbRoleApproveCriteria criteria) throws InterruptedException {
        log.debug("REST request to get PwbRoleApprove : {}", criteria);
        return pwbRoleApproveService.findByCriteria(criteria);
    }

    /**
     * {@code POST  /getModuleType/:userId} : get the "moduleType" pwbRoleApprove.
     *
     * @param userId the userId of the pwbRoleDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the moduleType, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/module-type-agency/{userId}")
    public List<String> getModuleTypeForAgency(@PathVariable String userId) {
        log.debug("REST request to get moduleType : {}", userId);
        return pwbRoleApproveService.findByUserIdForAgency(userId);
    }

    @PostMapping("/pwb-role-approve/getApproveList")
    public Page<PwbRoleDTO> getApproveList(@Valid @RequestBody PwbRoleApproveCriteria criteria) {
        log.debug("REST request to get PwbRoleApproveDTO : {}", criteria);
        return pwbRoleApproveService.findApproveList(criteria);
    }

    @PostMapping("/pwb-role-approve/getApproveModuleType")
    public List<String> getApproveModuleType() {
        log.debug("REST request to get moduleType : {}");
        return pwbRoleApproveService.findApproveModuleType();
    }

    @PostMapping("/pwb-role-approve/getApproveUserId")
    public List<PwbRoleDTO> getApproveUserId() {
        log.debug("REST request to get userId : {}");
        return pwbRoleApproveService.findApproveUserId();
    }

}
