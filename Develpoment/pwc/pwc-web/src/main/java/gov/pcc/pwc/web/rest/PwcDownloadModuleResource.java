package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.repository.PwcDownloadModuleRepository;
import gov.pcc.pwc.service.PwcDownloadModuleService;
import gov.pcc.pwc.service.dto.PwcDownloadModuleDTO;
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
 * REST controller for managing {@link gov.pcc.pwc.domain.PwcDownloadModule}.
 */
@RestController
@RequestMapping("/api")
public class PwcDownloadModuleResource {

    private final Logger log = LoggerFactory.getLogger(PwcDownloadModuleResource.class);

    private static final String ENTITY_NAME = "pwcDownloadModule";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PwcDownloadModuleService pwcDownloadModuleService;

    private final PwcDownloadModuleRepository pwcDownloadModuleRepository;

    public PwcDownloadModuleResource(
        PwcDownloadModuleService pwcDownloadModuleService,
        PwcDownloadModuleRepository pwcDownloadModuleRepository
    ) {
        this.pwcDownloadModuleService = pwcDownloadModuleService;
        this.pwcDownloadModuleRepository = pwcDownloadModuleRepository;
    }

    /**
     * {@code POST  /pwc-download-modules} : Create a new pwcDownloadModule.
     *
     * @param pwcDownloadModuleDTO the pwcDownloadModuleDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pwcDownloadModuleDTO, or with status {@code 400 (Bad Request)} if the pwcDownloadModule has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pwc-download-modules")
    public ResponseEntity<PwcDownloadModuleDTO> createPwcDownloadModule(@Valid @RequestBody PwcDownloadModuleDTO pwcDownloadModuleDTO)
        throws URISyntaxException {
        log.debug("REST request to save PwcDownloadModule : {}", pwcDownloadModuleDTO);
        if (pwcDownloadModuleDTO.getProDownloadModuleId() != null) {
            throw new BadRequestAlertException("A new pwcDownloadModule cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PwcDownloadModuleDTO result = pwcDownloadModuleService.save(pwcDownloadModuleDTO);
        return ResponseEntity
            .created(new URI("/api/pwc-download-modules/" + result.getProDownloadModuleId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getProDownloadModuleId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pwc-download-modules/:proDownloadModuleId} : Updates an existing pwcDownloadModule.
     *
     * @param proDownloadModuleId the id of the pwcDownloadModuleDTO to save.
     * @param pwcDownloadModuleDTO the pwcDownloadModuleDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pwcDownloadModuleDTO,
     * or with status {@code 400 (Bad Request)} if the pwcDownloadModuleDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pwcDownloadModuleDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pwc-download-modules/{proDownloadModuleId}")
    public ResponseEntity<PwcDownloadModuleDTO> updatePwcDownloadModule(
        @PathVariable(value = "proDownloadModuleId", required = false) final Long proDownloadModuleId,
        @Valid @RequestBody PwcDownloadModuleDTO pwcDownloadModuleDTO
    ) throws URISyntaxException {
        log.debug("REST request to update PwcDownloadModule : {}, {}", proDownloadModuleId, pwcDownloadModuleDTO);
        if (pwcDownloadModuleDTO.getProDownloadModuleId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(proDownloadModuleId, pwcDownloadModuleDTO.getProDownloadModuleId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!pwcDownloadModuleRepository.existsById(proDownloadModuleId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        PwcDownloadModuleDTO result = pwcDownloadModuleService.save(pwcDownloadModuleDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    pwcDownloadModuleDTO.getProDownloadModuleId().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PATCH  /pwc-download-modules/:proDownloadModuleId} : Partial updates given fields of an existing pwcDownloadModule, field will ignore if it is null
     *
     * @param proDownloadModuleId the id of the pwcDownloadModuleDTO to save.
     * @param pwcDownloadModuleDTO the pwcDownloadModuleDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pwcDownloadModuleDTO,
     * or with status {@code 400 (Bad Request)} if the pwcDownloadModuleDTO is not valid,
     * or with status {@code 404 (Not Found)} if the pwcDownloadModuleDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the pwcDownloadModuleDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/pwc-download-modules/{proDownloadModuleId}", consumes = "application/merge-patch+json")
    public ResponseEntity<PwcDownloadModuleDTO> partialUpdatePwcDownloadModule(
        @PathVariable(value = "proDownloadModuleId", required = false) final Long proDownloadModuleId,
        @NotNull @RequestBody PwcDownloadModuleDTO pwcDownloadModuleDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update PwcDownloadModule partially : {}, {}", proDownloadModuleId, pwcDownloadModuleDTO);
        if (pwcDownloadModuleDTO.getProDownloadModuleId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(proDownloadModuleId, pwcDownloadModuleDTO.getProDownloadModuleId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!pwcDownloadModuleRepository.existsById(proDownloadModuleId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PwcDownloadModuleDTO> result = pwcDownloadModuleService.partialUpdate(pwcDownloadModuleDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pwcDownloadModuleDTO.getProDownloadModuleId().toString())
        );
    }

    /**
     * {@code GET  /pwc-download-modules} : get all the pwcDownloadModules.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pwcDownloadModules in body.
     */
    @GetMapping("/pwc-download-modules")
    public List<PwcDownloadModuleDTO> getAllPwcDownloadModules() {
        log.debug("REST request to get all PwcDownloadModules");
        return pwcDownloadModuleService.findAll();
    }

    /**
     * {@code GET  /pwc-download-modules/:id} : get the "id" pwcDownloadModule.
     *
     * @param id the id of the pwcDownloadModuleDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pwcDownloadModuleDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pwc-download-modules/{id}")
    public ResponseEntity<PwcDownloadModuleDTO> getPwcDownloadModule(@PathVariable Long id) {
        log.debug("REST request to get PwcDownloadModule : {}", id);
        Optional<PwcDownloadModuleDTO> pwcDownloadModuleDTO = pwcDownloadModuleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(pwcDownloadModuleDTO);
    }

    /**
     * {@code DELETE  /pwc-download-modules/:id} : delete the "id" pwcDownloadModule.
     *
     * @param id the id of the pwcDownloadModuleDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pwc-download-modules/{id}")
    public ResponseEntity<Void> deletePwcDownloadModule(@PathVariable Long id) {
        log.debug("REST request to delete PwcDownloadModule : {}", id);
        pwcDownloadModuleService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
