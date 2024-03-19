package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.repository.RecUserProjectCodeRepository;
import gov.pcc.pwc.service.RecUserProjectCodeService;
import gov.pcc.pwc.service.criteria.RecUserProjectCodeCriteria;
import gov.pcc.pwc.service.dto.*;
import gov.pcc.pwc.common.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link gov.pcc.pwc.domain.RecUserProjectCode}.
 */
@RestController
@RequestMapping("/api")
public class RecUserProjectCodeResource {

    private final Logger log = LoggerFactory.getLogger(RecUserProjectCodeResource.class);

    private static final String ENTITY_NAME = "recUserProjectCode";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RecUserProjectCodeService recUserProjectCodeService;

    private final RecUserProjectCodeRepository recUserProjectCodeRepository;

    public RecUserProjectCodeResource(
        RecUserProjectCodeService recUserProjectCodeService,
        RecUserProjectCodeRepository recUserProjectCodeRepository
    ) {
        this.recUserProjectCodeService = recUserProjectCodeService;
        this.recUserProjectCodeRepository = recUserProjectCodeRepository;
    }


    //取機關代碼跟名稱
    @GetMapping("/rec-user-project-codes/get-rec-org-role")
    public List<RecOrgRoleDTO> getRecOrgRole() {
        log.debug("REST request to getRecOrgRole");
        return recUserProjectCodeService.getAllRecOrg();
    }

    //取REC的角色
    @GetMapping("/rec-user-project-codes/get-rec-adm-role")
    public List<RecAdmRoleDTO> getRecAdmRole() {
        log.debug("REST request to getRecAdmRole");
        return recUserProjectCodeService.getAllRecRole();
    }

    //取REC的USER
    @PostMapping("/rec-user-project-codes/criteria-jpa")
    public Page<RecUserProjectCodeQueryDTO> getRecUserByCriteria(@Valid @RequestBody RecUserProjectCodeCriteria criteria) throws InterruptedException {
        log.debug("REST request to get RecUser : {}",criteria);
        return recUserProjectCodeService.findByCriteria(criteria);
    }

    //取可以用的ProjectCodes
    @GetMapping("/rec-user-project-codes/get-access-project-codes/{loginId}/{roleId}/{orgId}")
    public List<RecProjectCodesDTO> getAccessRecProjectCodesByQuery(@PathVariable String loginId,@PathVariable String roleId,@PathVariable String orgId){
        log.debug("REST request to getRecUserByQuery {} {} {}",loginId,roleId,orgId);
        return recUserProjectCodeService.getAccessProjectCodes(loginId,roleId,orgId);
    }

    //取原本被選取的ProjectCodes
    @GetMapping("/rec-user-project-codes/get-checked-project-codes/{userId}")
    public List<String> getSelectedProjectCodesByQuery(@PathVariable String userId){
        log.debug("REST request to getSelectedProjectCodesByQuery");
        return recUserProjectCodeService.getSelectedProjectCodes(userId);
    }

    //測試insert
    @PostMapping("/rec-user-project-codes/insert-project-codes")
    public void insertRecProjectCodes (@Valid @RequestBody RecUserProjectCodeDTO recUserProjectCodeDTO) {
        log.debug("REST request to insertProjectCodes: {}", recUserProjectCodeDTO);
        recUserProjectCodeService.insertProjectCodes(recUserProjectCodeDTO);
    }



    /**
     * {@code POST  /rec-user-project-codes} : Create a new recUserProjectCode.
     *
     * @param recUserProjectCodeDTO the recUserProjectCodeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new recUserProjectCodeDTO, or with status {@code 400 (Bad Request)} if the recUserProjectCode has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/rec-user-project-codes")
    public ResponseEntity<RecUserProjectCodeDTO> createRecUserProjectCode(@Valid @RequestBody RecUserProjectCodeDTO recUserProjectCodeDTO)
        throws URISyntaxException {
        log.debug("REST request to save RecUserProjectCode : {}", recUserProjectCodeDTO);
        if (recUserProjectCodeDTO.getUserId() != null) {
            throw new BadRequestAlertException("A new recUserProjectCode cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RecUserProjectCodeDTO result = recUserProjectCodeService.save(recUserProjectCodeDTO);
        return ResponseEntity
            .created(new URI("/api/rec-user-project-codes/" + result.getUserId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getUserId()))
            .body(result);
    }

    /**
     * {@code PUT  /rec-user-project-codes/:userId} : Updates an existing recUserProjectCode.
     *
     * @param userId the id of the recUserProjectCodeDTO to save.
     * @param recUserProjectCodeDTO the recUserProjectCodeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated recUserProjectCodeDTO,
     * or with status {@code 400 (Bad Request)} if the recUserProjectCodeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the recUserProjectCodeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/rec-user-project-codes/{userId}")
    public ResponseEntity<RecUserProjectCodeDTO> updateRecUserProjectCode(
        @PathVariable(value = "userId", required = false) final String userId,
        @Valid @RequestBody RecUserProjectCodeDTO recUserProjectCodeDTO
    ) throws URISyntaxException {
        log.debug("REST request to update RecUserProjectCode : {}, {}", userId, recUserProjectCodeDTO);
        if (recUserProjectCodeDTO.getUserId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(userId, recUserProjectCodeDTO.getUserId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!recUserProjectCodeRepository.existsById(userId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        RecUserProjectCodeDTO result = recUserProjectCodeService.save(recUserProjectCodeDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, recUserProjectCodeDTO.getUserId()))
            .body(result);
    }

    /**
     * {@code PATCH  /rec-user-project-codes/:userId} : Partial updates given fields of an existing recUserProjectCode, field will ignore if it is null
     *
     * @param userId the id of the recUserProjectCodeDTO to save.
     * @param recUserProjectCodeDTO the recUserProjectCodeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated recUserProjectCodeDTO,
     * or with status {@code 400 (Bad Request)} if the recUserProjectCodeDTO is not valid,
     * or with status {@code 404 (Not Found)} if the recUserProjectCodeDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the recUserProjectCodeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/rec-user-project-codes/{userId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<RecUserProjectCodeDTO> partialUpdateRecUserProjectCode(
        @PathVariable(value = "userId", required = false) final String userId,
        @NotNull @RequestBody RecUserProjectCodeDTO recUserProjectCodeDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update RecUserProjectCode partially : {}, {}", userId, recUserProjectCodeDTO);
        if (recUserProjectCodeDTO.getUserId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(userId, recUserProjectCodeDTO.getUserId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!recUserProjectCodeRepository.existsById(userId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RecUserProjectCodeDTO> result = recUserProjectCodeService.partialUpdate(recUserProjectCodeDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, recUserProjectCodeDTO.getUserId())
        );
    }

    /**
     * {@code GET  /rec-user-project-codes} : get all the recUserProjectCodes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of recUserProjectCodes in body.
     */
    @GetMapping("/rec-user-project-codes")
    public List<RecUserProjectCodeDTO> getAllRecUserProjectCodes() {
        log.debug("REST request to get all RecUserProjectCodes");
        return recUserProjectCodeService.findAll();
    }

    /**
     * {@code GET  /rec-user-project-codes/:id} : get the "id" recUserProjectCode.
     *
     * @param id the id of the recUserProjectCodeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the recUserProjectCodeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/rec-user-project-codes/{id}")
    public ResponseEntity<RecUserProjectCodeDTO> getRecUserProjectCode(@PathVariable String id) {
        log.debug("REST request to get RecUserProjectCode : {}", id);
        Optional<RecUserProjectCodeDTO> recUserProjectCodeDTO = recUserProjectCodeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(recUserProjectCodeDTO);
    }

    /**
     * {@code DELETE  /rec-user-project-codes/:id} : delete the "id" recUserProjectCode.
     *
     * @param id the id of the recUserProjectCodeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/rec-user-project-codes/{id}")
    public ResponseEntity<Void> deleteRecUserProjectCode(@PathVariable String id) {
        log.debug("REST request to delete RecUserProjectCode : {}", id);
        recUserProjectCodeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
