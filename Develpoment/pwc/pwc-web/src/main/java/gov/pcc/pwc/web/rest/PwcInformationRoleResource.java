package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.repository.PwcInformationRoleRepository;
import gov.pcc.pwc.service.PwcInformationRoleService;
import gov.pcc.pwc.service.dto.PwcInformationRoleDTO;
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
 * REST controller for managing {@link gov.pcc.pwc.domain.PwcInformationRole}.
 */
@RestController
@RequestMapping("/api")
public class PwcInformationRoleResource {

    private final Logger log = LoggerFactory.getLogger(PwcInformationRoleResource.class);

    private static final String ENTITY_NAME = "pwcInformationRole";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PwcInformationRoleService pwcInformationRoleService;

    private final PwcInformationRoleRepository pwcInformationRoleRepository;

    public PwcInformationRoleResource(
        PwcInformationRoleService pwcInformationRoleService,
        PwcInformationRoleRepository pwcInformationRoleRepository
    ) {
        this.pwcInformationRoleService = pwcInformationRoleService;
        this.pwcInformationRoleRepository = pwcInformationRoleRepository;
    }

    /**
     * {@code POST  /pwc-information-roles} : Create a new pwcInformationRole.
     *
     * @param pwcInformationRoleDTO the pwcInformationRoleDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pwcInformationRoleDTO, or with status {@code 400 (Bad Request)} if the pwcInformationRole has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pwc-information-roles")
    public ResponseEntity<PwcInformationRoleDTO> createPwcInformationRole(@Valid @RequestBody PwcInformationRoleDTO pwcInformationRoleDTO)
        throws URISyntaxException {
        log.debug("REST request to save PwcInformationRole : {}", pwcInformationRoleDTO);
        if (pwcInformationRoleDTO.getProInformationRoleId() != null) {
            throw new BadRequestAlertException("A new pwcInformationRole cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PwcInformationRoleDTO result = pwcInformationRoleService.save(pwcInformationRoleDTO);
        return ResponseEntity
            .created(new URI("/api/pwc-information-roles/" + result.getProInformationRoleId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getProInformationRoleId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pwc-information-roles/:proInformationRoleId} : Updates an existing pwcInformationRole.
     *
     * @param proInformationRoleId the id of the pwcInformationRoleDTO to save.
     * @param pwcInformationRoleDTO the pwcInformationRoleDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pwcInformationRoleDTO,
     * or with status {@code 400 (Bad Request)} if the pwcInformationRoleDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pwcInformationRoleDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pwc-information-roles/{proInformationRoleId}")
    public ResponseEntity<PwcInformationRoleDTO> updatePwcInformationRole(
        @PathVariable(value = "proInformationRoleId", required = false) final Long proInformationRoleId,
        @Valid @RequestBody PwcInformationRoleDTO pwcInformationRoleDTO
    ) throws URISyntaxException {
        log.debug("REST request to update PwcInformationRole : {}, {}", proInformationRoleId, pwcInformationRoleDTO);
        if (pwcInformationRoleDTO.getProInformationRoleId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(proInformationRoleId, pwcInformationRoleDTO.getProInformationRoleId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!pwcInformationRoleRepository.existsById(proInformationRoleId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        PwcInformationRoleDTO result = pwcInformationRoleService.save(pwcInformationRoleDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    pwcInformationRoleDTO.getProInformationRoleId().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PATCH  /pwc-information-roles/:proInformationRoleId} : Partial updates given fields of an existing pwcInformationRole, field will ignore if it is null
     *
     * @param proInformationRoleId the id of the pwcInformationRoleDTO to save.
     * @param pwcInformationRoleDTO the pwcInformationRoleDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pwcInformationRoleDTO,
     * or with status {@code 400 (Bad Request)} if the pwcInformationRoleDTO is not valid,
     * or with status {@code 404 (Not Found)} if the pwcInformationRoleDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the pwcInformationRoleDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/pwc-information-roles/{proInformationRoleId}", consumes = "application/merge-patch+json")
    public ResponseEntity<PwcInformationRoleDTO> partialUpdatePwcInformationRole(
        @PathVariable(value = "proInformationRoleId", required = false) final Long proInformationRoleId,
        @NotNull @RequestBody PwcInformationRoleDTO pwcInformationRoleDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update PwcInformationRole partially : {}, {}", proInformationRoleId, pwcInformationRoleDTO);
        if (pwcInformationRoleDTO.getProInformationRoleId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(proInformationRoleId, pwcInformationRoleDTO.getProInformationRoleId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!pwcInformationRoleRepository.existsById(proInformationRoleId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PwcInformationRoleDTO> result = pwcInformationRoleService.partialUpdate(pwcInformationRoleDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                true,
                ENTITY_NAME,
                pwcInformationRoleDTO.getProInformationRoleId().toString()
            )
        );
    }

    /**
     * {@code GET  /pwc-information-roles} : get all the pwcInformationRoles.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pwcInformationRoles in body.
     */
    @GetMapping("/pwc-information-roles")
    public List<PwcInformationRoleDTO> getAllPwcInformationRoles() {
        log.debug("REST request to get all PwcInformationRoles");
        return pwcInformationRoleService.findAll();
    }

    /**
     * {@code GET  /pwc-information-roles/:id} : get the "id" pwcInformationRole.
     *
     * @param id the id of the pwcInformationRoleDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pwcInformationRoleDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pwc-information-roles/{id}")
    public ResponseEntity<PwcInformationRoleDTO> getPwcInformationRole(@PathVariable Long id) {
        log.debug("REST request to get PwcInformationRole : {}", id);
        Optional<PwcInformationRoleDTO> pwcInformationRoleDTO = pwcInformationRoleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(pwcInformationRoleDTO);
    }

    /**
     * {@code DELETE  /pwc-information-roles/:id} : delete the "id" pwcInformationRole.
     *
     * @param id the id of the pwcInformationRoleDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pwc-information-roles/{id}")
    public ResponseEntity<Void> deletePwcInformationRole(@PathVariable Long id) {
        log.debug("REST request to delete PwcInformationRole : {}", id);
        pwcInformationRoleService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
