package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.repository.PwbUnitRoleRepository;
import gov.pcc.pwc.service.PwbUnitRoleService;
import gov.pcc.pwc.service.dto.AdmRoleDTO;
import gov.pcc.pwc.service.dto.PwbUnitRoleDTO;
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
 * REST controller for managing {@link gov.pcc.pwc.domain.PwbUnitRole}.
 */
@RestController
@RequestMapping("/api")
public class PwbUnitRoleResource {

    private final Logger log = LoggerFactory.getLogger(PwbUnitRoleResource.class);

    private static final String ENTITY_NAME = "pwbUnitRole";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PwbUnitRoleService pwbUnitRoleService;

    private final PwbUnitRoleRepository pwbUnitRoleRepository;

    public PwbUnitRoleResource(PwbUnitRoleService pwbUnitRoleService, PwbUnitRoleRepository pwbUnitRoleRepository) {
        this.pwbUnitRoleService = pwbUnitRoleService;
        this.pwbUnitRoleRepository = pwbUnitRoleRepository;
    }

    /**
     * {@code POST  /pwb-unit-roles} : Create a new pwbUnitRole.
     *
     * @param pwbUnitRoleDTO the pwbUnitRoleDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pwbUnitRoleDTO, or with status {@code 400 (Bad Request)} if the pwbUnitRole has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pwb-unit-roles")
    public ResponseEntity<PwbUnitRoleDTO> createPwbUnitRole(@Valid @RequestBody PwbUnitRoleDTO pwbUnitRoleDTO) throws URISyntaxException {
        log.debug("REST request to save PwbUnitRole : {}", pwbUnitRoleDTO);
        if (pwbUnitRoleDTO.getId() != null) {
            throw new BadRequestAlertException("A new pwbUnitRole cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PwbUnitRoleDTO result = pwbUnitRoleService.save(pwbUnitRoleDTO);
        return ResponseEntity
            .created(new URI("/api/pwb-unit-roles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pwb-unit-roles/:id} : Updates an existing pwbUnitRole.
     *
     * @param id the id of the pwbUnitRoleDTO to save.
     * @param pwbUnitRoleDTO the pwbUnitRoleDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pwbUnitRoleDTO,
     * or with status {@code 400 (Bad Request)} if the pwbUnitRoleDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pwbUnitRoleDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pwb-unit-roles/{id}")
    public ResponseEntity<PwbUnitRoleDTO> updatePwbUnitRole(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody PwbUnitRoleDTO pwbUnitRoleDTO
    ) throws URISyntaxException {
        log.debug("REST request to update PwbUnitRole : {}, {}", id, pwbUnitRoleDTO);
        if (pwbUnitRoleDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, pwbUnitRoleDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!pwbUnitRoleRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        PwbUnitRoleDTO result = pwbUnitRoleService.save(pwbUnitRoleDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pwbUnitRoleDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /pwb-unit-roles/:id} : Partial updates given fields of an existing pwbUnitRole, field will ignore if it is null
     *
     * @param id the id of the pwbUnitRoleDTO to save.
     * @param pwbUnitRoleDTO the pwbUnitRoleDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pwbUnitRoleDTO,
     * or with status {@code 400 (Bad Request)} if the pwbUnitRoleDTO is not valid,
     * or with status {@code 404 (Not Found)} if the pwbUnitRoleDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the pwbUnitRoleDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/pwb-unit-roles/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<PwbUnitRoleDTO> partialUpdatePwbUnitRole(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody PwbUnitRoleDTO pwbUnitRoleDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update PwbUnitRole partially : {}, {}", id, pwbUnitRoleDTO);
        if (pwbUnitRoleDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, pwbUnitRoleDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!pwbUnitRoleRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PwbUnitRoleDTO> result = pwbUnitRoleService.partialUpdate(pwbUnitRoleDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pwbUnitRoleDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /pwb-unit-roles} : get all the pwbUnitRoles.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pwbUnitRoles in body.
     */
    @GetMapping("/pwb-unit-roles")
    public List<PwbUnitRoleDTO> getAllPwbUnitRoles() {
        log.debug("REST request to get all PwbUnitRoles");
        return pwbUnitRoleService.findAll();
    }

    /**
     * {@code GET  /pwb-unit-roles/:id} : get the "id" pwbUnitRole.
     *
     * @param id the id of the pwbUnitRoleDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pwbUnitRoleDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pwb-unit-roles/{id}")
    public ResponseEntity<PwbUnitRoleDTO> getPwbUnitRole(@PathVariable Long id) {
        log.debug("REST request to get PwbUnitRole : {}", id);
        Optional<PwbUnitRoleDTO> pwbUnitRoleDTO = pwbUnitRoleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(pwbUnitRoleDTO);
    }

    /**
     * {@code DELETE  /pwb-unit-roles/:id} : delete the "id" pwbUnitRole.
     *
     * @param id the id of the pwbUnitRoleDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pwb-unit-roles/{id}")
    public ResponseEntity<Void> deletePwbUnitRole(@PathVariable Long id) {
        log.debug("REST request to delete PwbUnitRole : {}", id);
        pwbUnitRoleService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    @GetMapping("/pwb-unit-role/get-role-opts/{unitId}")
    public List<AdmRoleDTO> getServiceRoleOpts(@PathVariable String unitId) {
        log.debug("REST request to get all PwbUnitRoleDTO for front-end roleOpts", unitId);
        return pwbUnitRoleService.findAllRoleOpts(unitId);
    }
}
