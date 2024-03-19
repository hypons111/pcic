package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.repository.AdmAccountRoleRepository;
import gov.pcc.pwc.service.AdmAccountRoleService;
import gov.pcc.pwc.service.dto.AdmAccountContactDTO;
import gov.pcc.pwc.service.dto.AdmAccountRoleDTO;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link gov.pcc.pwc.domain.AdmAccountRole}.
 */
@RestController
@RequestMapping("/api")
public class AdmAccountRoleResource {

    private final Logger log = LoggerFactory.getLogger(AdmAccountRoleResource.class);

    private static final String ENTITY_NAME = "admAccountRole";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AdmAccountRoleService admAccountRoleService;

    private final AdmAccountRoleRepository admAccountRoleRepository;

    public AdmAccountRoleResource(AdmAccountRoleService admAccountRoleService, AdmAccountRoleRepository admAccountRoleRepository) {
        this.admAccountRoleService = admAccountRoleService;
        this.admAccountRoleRepository = admAccountRoleRepository;
    }

    /**
     * {@code POST  /adm-account-roles} : Create a new admAccountRole.
     *
     * @param admAccountRoleDTO the admAccountRoleDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new admAccountRoleDTO, or with status {@code 400 (Bad Request)} if the admAccountRole has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/adm-account-roles")
    public ResponseEntity<AdmAccountRoleDTO> createAdmAccountRole(@Valid @RequestBody AdmAccountRoleDTO admAccountRoleDTO)
        throws URISyntaxException {
        log.debug("REST request to save AdmAccountRole : {}", admAccountRoleDTO);
        if (admAccountRoleDTO.getId() != null) {
            throw new BadRequestAlertException("A new admAccountRole cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AdmAccountRoleDTO result = admAccountRoleService.save(admAccountRoleDTO);
        return ResponseEntity
            .created(new URI("/api/adm-account-roles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /adm-account-roles/:id} : Updates an existing admAccountRole.
     *
     * @param id the id of the admAccountRoleDTO to save.
     * @param admAccountRoleDTO the admAccountRoleDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated admAccountRoleDTO,
     * or with status {@code 400 (Bad Request)} if the admAccountRoleDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the admAccountRoleDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/adm-account-roles/{id}")
    public ResponseEntity<AdmAccountRoleDTO> updateAdmAccountRole(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody AdmAccountRoleDTO admAccountRoleDTO
    ) throws URISyntaxException {
        log.debug("REST request to update AdmAccountRole : {}, {}", id, admAccountRoleDTO);
        if (admAccountRoleDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, admAccountRoleDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!admAccountRoleRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        AdmAccountRoleDTO result = admAccountRoleService.save(admAccountRoleDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, admAccountRoleDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /adm-account-roles/:id} : Partial updates given fields of an existing admAccountRole, field will ignore if it is null
     *
     * @param id the id of the admAccountRoleDTO to save.
     * @param admAccountRoleDTO the admAccountRoleDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated admAccountRoleDTO,
     * or with status {@code 400 (Bad Request)} if the admAccountRoleDTO is not valid,
     * or with status {@code 404 (Not Found)} if the admAccountRoleDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the admAccountRoleDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/adm-account-roles/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<AdmAccountRoleDTO> partialUpdateAdmAccountRole(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody AdmAccountRoleDTO admAccountRoleDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update AdmAccountRole partially : {}, {}", id, admAccountRoleDTO);
        if (admAccountRoleDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, admAccountRoleDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!admAccountRoleRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<AdmAccountRoleDTO> result = admAccountRoleService.partialUpdate(admAccountRoleDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, admAccountRoleDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /adm-account-roles} : get all the admAccountRoles.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of admAccountRoles in body.
     */
    @GetMapping("/adm-account-roles")
    public List<AdmAccountRoleDTO> getAllAdmAccountRoles() {
        log.debug("REST request to get all AdmAccountRoles");
        return admAccountRoleService.findAll();
    }

    /**
     * {@code GET  /adm-account-roles/:id} : get the "id" admAccountRole.
     *
     * @param id the id of the admAccountRoleDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the admAccountRoleDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/adm-account-roles/{id}")
    public ResponseEntity<AdmAccountRoleDTO> getAdmAccountRole(@PathVariable Long id) {
        log.debug("REST request to get AdmAccountRole : {}", id);
        Optional<AdmAccountRoleDTO> admAccountRoleDTO = admAccountRoleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(admAccountRoleDTO);
    }

    /**
     * {@code DELETE  /adm-account-roles/:id} : delete the "id" admAccountRole.
     *
     * @param id the id of the admAccountRoleDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/adm-account-roles/{id}")
    public ResponseEntity<Void> deleteAdmAccountRole(@PathVariable Long id) {
        log.debug("REST request to delete AdmAccountRole : {}", id);
        admAccountRoleService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code GET  /adm-account-role/getRoleId} : get the "userId" admAccountRole.
     *
     * @param  admAccountContactDTO the id of the admAccountContactDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the admAccountContactDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/adm-account-role/getRoleId")
    public List<String> getAccountRoleId(@Valid @RequestBody AdmAccountContactDTO admAccountContactDTO) {
        log.debug("REST request to get admAccountContractDTO : {}", admAccountContactDTO.getLoginUserId());
        return admAccountRoleService.findAccountRoleId(admAccountContactDTO);
    }
}
