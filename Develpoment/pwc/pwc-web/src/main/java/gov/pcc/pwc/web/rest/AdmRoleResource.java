package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.repository.AdmRoleRepository;
import gov.pcc.pwc.service.AdmRoleService;
import gov.pcc.pwc.service.dto.AdmRoleDTO;
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
 * REST controller for managing {@link gov.pcc.pwc.domain.AdmRole}.
 */
@RestController
@RequestMapping("/api")
public class AdmRoleResource {

    private final Logger log = LoggerFactory.getLogger(AdmRoleResource.class);

    private static final String ENTITY_NAME = "admRole";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AdmRoleService admRoleService;

    private final AdmRoleRepository admRoleRepository;

    public AdmRoleResource(AdmRoleService admRoleService, AdmRoleRepository admRoleRepository) {
        this.admRoleService = admRoleService;
        this.admRoleRepository = admRoleRepository;
    }

    /**
     * {@code POST  /adm-roles} : Create a new admRole.
     *
     * @param admRoleDTO the admRoleDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new admRoleDTO, or with status {@code 400 (Bad Request)} if the admRole has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/adm-roles")
    public ResponseEntity<AdmRoleDTO> createAdmRole(@Valid @RequestBody AdmRoleDTO admRoleDTO) throws URISyntaxException {
        log.debug("REST request to save AdmRole : {}", admRoleDTO);
        if (admRoleDTO.getRoleId() != null) {
            throw new BadRequestAlertException("A new admRole cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AdmRoleDTO result = admRoleService.save(admRoleDTO);
        return ResponseEntity
            .created(new URI("/api/adm-roles/" + result.getRoleId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getRoleId()))
            .body(result);
    }

    /**
     * {@code PUT  /adm-roles/:roleId} : Updates an existing admRole.
     *
     * @param roleId the id of the admRoleDTO to save.
     * @param admRoleDTO the admRoleDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated admRoleDTO,
     * or with status {@code 400 (Bad Request)} if the admRoleDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the admRoleDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/adm-roles/{roleId}")
    public ResponseEntity<AdmRoleDTO> updateAdmRole(
        @PathVariable(value = "roleId", required = false) final String roleId,
        @Valid @RequestBody AdmRoleDTO admRoleDTO
    ) throws URISyntaxException {
        log.debug("REST request to update AdmRole : {}, {}", roleId, admRoleDTO);
        if (admRoleDTO.getRoleId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(roleId, admRoleDTO.getRoleId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!admRoleRepository.existsById(roleId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        AdmRoleDTO result = admRoleService.save(admRoleDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, admRoleDTO.getRoleId()))
            .body(result);
    }

    /**
     * {@code PATCH  /adm-roles/:roleId} : Partial updates given fields of an existing admRole, field will ignore if it is null
     *
     * @param roleId the id of the admRoleDTO to save.
     * @param admRoleDTO the admRoleDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated admRoleDTO,
     * or with status {@code 400 (Bad Request)} if the admRoleDTO is not valid,
     * or with status {@code 404 (Not Found)} if the admRoleDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the admRoleDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/adm-roles/{roleId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<AdmRoleDTO> partialUpdateAdmRole(
        @PathVariable(value = "roleId", required = false) final String roleId,
        @NotNull @RequestBody AdmRoleDTO admRoleDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update AdmRole partially : {}, {}", roleId, admRoleDTO);
        if (admRoleDTO.getRoleId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(roleId, admRoleDTO.getRoleId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!admRoleRepository.existsById(roleId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<AdmRoleDTO> result = admRoleService.partialUpdate(admRoleDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, admRoleDTO.getRoleId())
        );
    }

    /**
     * {@code GET  /adm-roles} : get all the admRoles.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of admRoles in body.
     */
    @GetMapping("/adm-roles")
    public List<AdmRoleDTO> getAllAdmRoles() {
        log.debug("REST request to get all AdmRoles");
        return admRoleService.findAll();
    }

    /**
     * {@code GET  /adm-roles/:id} : get the "id" admRole.
     *
     * @param id the id of the admRoleDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the admRoleDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/adm-roles/{id}")
    public ResponseEntity<AdmRoleDTO> getAdmRole(@PathVariable String id) {
        log.debug("REST request to get AdmRole : {}", id);
        Optional<AdmRoleDTO> admRoleDTO = admRoleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(admRoleDTO);
    }

    /**
     * {@code DELETE  /adm-roles/:id} : delete the "id" admRole.
     *
     * @param id the id of the admRoleDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/adm-roles/{id}")
    public ResponseEntity<Void> deleteAdmRole(@PathVariable String id) {
        log.debug("REST request to delete AdmRole : {}", id);
        admRoleService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
