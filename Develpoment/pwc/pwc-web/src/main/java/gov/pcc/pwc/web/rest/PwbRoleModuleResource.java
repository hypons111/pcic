package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.repository.PwbRoleModuleRepository;
import gov.pcc.pwc.service.PwbRoleModuleService;
import gov.pcc.pwc.service.criteria.PwbRoleModuleCriteria;
import gov.pcc.pwc.service.dto.PwbRoleModuleDTO;
import gov.pcc.pwc.common.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import gov.pcc.pwc.service.dto.PwcSharedDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link gov.pcc.pwc.domain.PwbRoleModule}.
 */
@RestController
@RequestMapping("/api")
public class PwbRoleModuleResource {

    private final Logger log = LoggerFactory.getLogger(PwbRoleModuleResource.class);

    private static final String ENTITY_NAME = "pwbRoleModule";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PwbRoleModuleService pwbRoleModuleService;

    private final PwbRoleModuleRepository pwbRoleModuleRepository;

    public PwbRoleModuleResource(PwbRoleModuleService pwbRoleModuleService, PwbRoleModuleRepository pwbRoleModuleRepository) {
        this.pwbRoleModuleService = pwbRoleModuleService;
        this.pwbRoleModuleRepository = pwbRoleModuleRepository;
    }

    /**
     * {@code POST  /pwb-role-modules} : Create a new pwbRoleModule.
     *
     * @param pwbRoleModuleDTO the pwbRoleModuleDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pwbRoleModuleDTO, or with status {@code 400 (Bad Request)} if the pwbRoleModule has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pwb-role-modules")
    public ResponseEntity<PwbRoleModuleDTO> createPwbRoleModule(@Valid @RequestBody PwbRoleModuleDTO pwbRoleModuleDTO)
        throws URISyntaxException {
        log.debug("REST request to save PwbRoleModule : {}", pwbRoleModuleDTO);
        if (pwbRoleModuleDTO.getId() != null) {
            throw new BadRequestAlertException("A new pwbRoleModule cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PwbRoleModuleDTO result = pwbRoleModuleService.save(pwbRoleModuleDTO);
        return ResponseEntity
            .created(new URI("/api/pwb-role-modules/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pwb-role-modules/:id} : Updates an existing pwbRoleModule.
     *
     * @param id the id of the pwbRoleModuleDTO to save.
     * @param pwbRoleModuleDTO the pwbRoleModuleDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pwbRoleModuleDTO,
     * or with status {@code 400 (Bad Request)} if the pwbRoleModuleDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pwbRoleModuleDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pwb-role-modules/{id}")
    public ResponseEntity<PwbRoleModuleDTO> updatePwbRoleModule(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody PwbRoleModuleDTO pwbRoleModuleDTO
    ) throws URISyntaxException {
        log.debug("REST request to update PwbRoleModule : {}, {}", id, pwbRoleModuleDTO);
        if (pwbRoleModuleDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, pwbRoleModuleDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!pwbRoleModuleRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        PwbRoleModuleDTO result = pwbRoleModuleService.save(pwbRoleModuleDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pwbRoleModuleDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /pwb-role-modules/:id} : Partial updates given fields of an existing pwbRoleModule, field will ignore if it is null
     *
     * @param id the id of the pwbRoleModuleDTO to save.
     * @param pwbRoleModuleDTO the pwbRoleModuleDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pwbRoleModuleDTO,
     * or with status {@code 400 (Bad Request)} if the pwbRoleModuleDTO is not valid,
     * or with status {@code 404 (Not Found)} if the pwbRoleModuleDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the pwbRoleModuleDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/pwb-role-modules/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<PwbRoleModuleDTO> partialUpdatePwbRoleModule(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody PwbRoleModuleDTO pwbRoleModuleDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update PwbRoleModule partially : {}, {}", id, pwbRoleModuleDTO);
        if (pwbRoleModuleDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, pwbRoleModuleDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!pwbRoleModuleRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PwbRoleModuleDTO> result = pwbRoleModuleService.partialUpdate(pwbRoleModuleDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pwbRoleModuleDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /pwb-role-modules} : get all the pwbRoleModules.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pwbRoleModules in body.
     */
    @GetMapping("/pwb-role-modules")
    public List<PwbRoleModuleDTO> getAllPwbRoleModules() {
        log.debug("REST request to get all PwbRoleModules");
        return pwbRoleModuleService.findAll();
    }

    /**
     * {@code GET  /pwb-role-modules/:id} : get the "id" pwbRoleModule.
     *
     * @param id the id of the pwbRoleModuleDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pwbRoleModuleDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pwb-role-modules/{id}")
    public ResponseEntity<PwbRoleModuleDTO> getPwbRoleModule(@PathVariable Long id) {
        log.debug("REST request to get PwbRoleModule : {}", id);
        Optional<PwbRoleModuleDTO> pwbRoleModuleDTO = pwbRoleModuleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(pwbRoleModuleDTO);
    }

    /**
     * {@code DELETE  /pwb-role-modules/:id} : delete the "id" pwbRoleModule.
     *
     * @param id the id of the pwbRoleModuleDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pwb-role-modules/{id}")
    public ResponseEntity<Void> deletePwbRoleModule(@PathVariable Long id) {
        log.debug("REST request to delete PwbRoleModule : {}", id);
        pwbRoleModuleService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    @GetMapping("/pwb-role-modules/getAllBusinessType")
    public List<String> getAllBusinessType() {
        log.debug("REST request to get BusinessType ");
         return pwbRoleModuleService.findAllBusinessType();
    }

    @GetMapping("/pwb-role-modules/getRoleId")
    public List<PwbRoleModuleDTO> getRoleId() {
        log.debug("REST request to get RoleId ");
        return pwbRoleModuleService.findRoleId();
    }

    @PostMapping("/pwb-role-modules/getSearchList")
    public Page<PwbRoleModuleDTO> getSearchList(@Valid @RequestBody PwbRoleModuleCriteria pwbRoleModuleCriteria)
        throws URISyntaxException {
        log.debug("REST request to save PwbRoleModule : {}", pwbRoleModuleCriteria);
        return pwbRoleModuleService.findSearchList(pwbRoleModuleCriteria);
    }

    @PostMapping("/pwb-role-modules/getRoleIdByModule")
    public List<PwbRoleModuleDTO> getRoleIdByModule(@Valid @RequestBody PwbRoleModuleCriteria pwbRoleModuleCriteria) {
        log.debug("REST request to get RoleIdByModule ");
        return pwbRoleModuleService.findRoleIdByModule(pwbRoleModuleCriteria);
    }

    @PostMapping("/pwb-role-modules/deleteModule")
    public void deleteModule(@Valid @RequestBody PwbRoleModuleDTO pwbRoleModuleDTO) {
        log.debug("REST request to delete module ");
        pwbRoleModuleService.deleteModule(pwbRoleModuleDTO);
    }

    @PostMapping("/pwb-role-modules/getAllRoleId")
    public List<PwbRoleModuleDTO> getAllRoleId(@Valid @RequestBody PwbRoleModuleCriteria pwbRoleModuleCriteria) {
        log.debug("REST request to get AllRoleId ");
        return pwbRoleModuleService.findAllRoleId(pwbRoleModuleCriteria);
    }

    @PostMapping("/pwb-role-modules/saveRoleId")
    public void saveRoleId(@Valid @RequestBody PwbRoleModuleDTO pwbRoleModuleDTO) {
        log.debug("REST request to save module ");

        List<PwbRoleModuleDTO> roleTable = pwbRoleModuleDTO.getTable();

        //確認是否有roleId
        if (roleTable.size() > 0) {
            //刪掉原有的PWB_ROLE_MODULE.ROLE_ID
            pwbRoleModuleService.deleteModule(pwbRoleModuleDTO);

            //新增update後的PWB_ROLE_MODULE.ROLE_ID
            pwbRoleModuleService.saveRoleId(pwbRoleModuleDTO);
        }
    }
}
