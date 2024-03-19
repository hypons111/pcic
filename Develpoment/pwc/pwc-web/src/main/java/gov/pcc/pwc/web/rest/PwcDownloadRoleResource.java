package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.repository.PwcDownloadRoleRepository;
import gov.pcc.pwc.service.PwcDownloadRoleService;
import gov.pcc.pwc.service.dto.PwcDownloadRoleDTO;
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
 * REST controller for managing {@link gov.pcc.pwc.domain.PwcDownloadRole}.
 */
@RestController
@RequestMapping("/api")
public class PwcDownloadRoleResource {

    private final Logger log = LoggerFactory.getLogger(PwcDownloadRoleResource.class);

    private static final String ENTITY_NAME = "pwcDownloadRole";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PwcDownloadRoleService pwcDownloadRoleService;

    private final PwcDownloadRoleRepository pwcDownloadRoleRepository;

    public PwcDownloadRoleResource(PwcDownloadRoleService pwcDownloadRoleService, PwcDownloadRoleRepository pwcDownloadRoleRepository) {
        this.pwcDownloadRoleService = pwcDownloadRoleService;
        this.pwcDownloadRoleRepository = pwcDownloadRoleRepository;
    }

    /**
     * {@code POST  /pwc-download-roles} : Create a new pwcDownloadRole.
     *
     * @param pwcDownloadRoleDTO the pwcDownloadRoleDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pwcDownloadRoleDTO, or with status {@code 400 (Bad Request)} if the pwcDownloadRole has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pwc-download-roles")
    public ResponseEntity<PwcDownloadRoleDTO> createPwcDownloadRole(@Valid @RequestBody PwcDownloadRoleDTO pwcDownloadRoleDTO)
        throws URISyntaxException {
        log.debug("REST request to save PwcDownloadRole : {}", pwcDownloadRoleDTO);
        if (pwcDownloadRoleDTO.getProDownloadRoleId() != null) {
            throw new BadRequestAlertException("A new pwcDownloadRole cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PwcDownloadRoleDTO result = pwcDownloadRoleService.save(pwcDownloadRoleDTO);
        return ResponseEntity
            .created(new URI("/api/pwc-download-roles/" + result.getProDownloadRoleId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getProDownloadRoleId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pwc-download-roles/:proDownloadRoleId} : Updates an existing pwcDownloadRole.
     *
     * @param proDownloadRoleId the id of the pwcDownloadRoleDTO to save.
     * @param pwcDownloadRoleDTO the pwcDownloadRoleDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pwcDownloadRoleDTO,
     * or with status {@code 400 (Bad Request)} if the pwcDownloadRoleDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pwcDownloadRoleDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pwc-download-roles/{proDownloadRoleId}")
    public ResponseEntity<PwcDownloadRoleDTO> updatePwcDownloadRole(
        @PathVariable(value = "proDownloadRoleId", required = false) final Long proDownloadRoleId,
        @Valid @RequestBody PwcDownloadRoleDTO pwcDownloadRoleDTO
    ) throws URISyntaxException {
        log.debug("REST request to update PwcDownloadRole : {}, {}", proDownloadRoleId, pwcDownloadRoleDTO);
        if (pwcDownloadRoleDTO.getProDownloadRoleId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(proDownloadRoleId, pwcDownloadRoleDTO.getProDownloadRoleId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!pwcDownloadRoleRepository.existsById(proDownloadRoleId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        PwcDownloadRoleDTO result = pwcDownloadRoleService.save(pwcDownloadRoleDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pwcDownloadRoleDTO.getProDownloadRoleId().toString())
            )
            .body(result);
    }

    /**
     * {@code PATCH  /pwc-download-roles/:proDownloadRoleId} : Partial updates given fields of an existing pwcDownloadRole, field will ignore if it is null
     *
     * @param proDownloadRoleId the id of the pwcDownloadRoleDTO to save.
     * @param pwcDownloadRoleDTO the pwcDownloadRoleDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pwcDownloadRoleDTO,
     * or with status {@code 400 (Bad Request)} if the pwcDownloadRoleDTO is not valid,
     * or with status {@code 404 (Not Found)} if the pwcDownloadRoleDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the pwcDownloadRoleDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/pwc-download-roles/{proDownloadRoleId}", consumes = "application/merge-patch+json")
    public ResponseEntity<PwcDownloadRoleDTO> partialUpdatePwcDownloadRole(
        @PathVariable(value = "proDownloadRoleId", required = false) final Long proDownloadRoleId,
        @NotNull @RequestBody PwcDownloadRoleDTO pwcDownloadRoleDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update PwcDownloadRole partially : {}, {}", proDownloadRoleId, pwcDownloadRoleDTO);
        if (pwcDownloadRoleDTO.getProDownloadRoleId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(proDownloadRoleId, pwcDownloadRoleDTO.getProDownloadRoleId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!pwcDownloadRoleRepository.existsById(proDownloadRoleId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PwcDownloadRoleDTO> result = pwcDownloadRoleService.partialUpdate(pwcDownloadRoleDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pwcDownloadRoleDTO.getProDownloadRoleId().toString())
        );
    }

    /**
     * {@code GET  /pwc-download-roles} : get all the pwcDownloadRoles.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pwcDownloadRoles in body.
     */
    @GetMapping("/pwc-download-roles")
    public List<PwcDownloadRoleDTO> getAllPwcDownloadRoles() {
        log.debug("REST request to get all PwcDownloadRoles");
        return pwcDownloadRoleService.findAll();
    }

    /**
     * {@code GET  /pwc-download-roles/:id} : get the "id" pwcDownloadRole.
     *
     * @param id the id of the pwcDownloadRoleDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pwcDownloadRoleDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pwc-download-roles/{id}")
    public ResponseEntity<PwcDownloadRoleDTO> getPwcDownloadRole(@PathVariable Long id) {
        log.debug("REST request to get PwcDownloadRole : {}", id);
        Optional<PwcDownloadRoleDTO> pwcDownloadRoleDTO = pwcDownloadRoleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(pwcDownloadRoleDTO);
    }

    /**
     * {@code DELETE  /pwc-download-roles/:id} : delete the "id" pwcDownloadRole.
     *
     * @param id the id of the pwcDownloadRoleDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pwc-download-roles/{id}")
    public ResponseEntity<Void> deletePwcDownloadRole(@PathVariable Long id) {
        log.debug("REST request to delete PwcDownloadRole : {}", id);
        pwcDownloadRoleService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
