package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.repository.PwcInformationModuleRepository;
import gov.pcc.pwc.service.PwcInformationModuleService;
import gov.pcc.pwc.service.dto.PwcInformationModuleDTO;
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
 * REST controller for managing {@link gov.pcc.pwc.domain.PwcInformationModule}.
 */
@RestController
@RequestMapping("/api")
public class PwcInformationModuleResource {

    private final Logger log = LoggerFactory.getLogger(PwcInformationModuleResource.class);

    private static final String ENTITY_NAME = "pwcInformationModule";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PwcInformationModuleService pwcInformationModuleService;

    private final PwcInformationModuleRepository pwcInformationModuleRepository;

    public PwcInformationModuleResource(
        PwcInformationModuleService pwcInformationModuleService,
        PwcInformationModuleRepository pwcInformationModuleRepository
    ) {
        this.pwcInformationModuleService = pwcInformationModuleService;
        this.pwcInformationModuleRepository = pwcInformationModuleRepository;
    }

    /**
     * {@code POST  /pwc-information-modules} : Create a new pwcInformationModule.
     *
     * @param pwcInformationModuleDTO the pwcInformationModuleDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pwcInformationModuleDTO, or with status {@code 400 (Bad Request)} if the pwcInformationModule has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pwc-information-modules")
    public ResponseEntity<PwcInformationModuleDTO> createPwcInformationModule(
        @Valid @RequestBody PwcInformationModuleDTO pwcInformationModuleDTO
    ) throws URISyntaxException {
        log.debug("REST request to save PwcInformationModule : {}", pwcInformationModuleDTO);
        if (pwcInformationModuleDTO.getProInformationModuleId() != null) {
            throw new BadRequestAlertException("A new pwcInformationModule cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PwcInformationModuleDTO result = pwcInformationModuleService.save(pwcInformationModuleDTO);
        return ResponseEntity
            .created(new URI("/api/pwc-information-modules/" + result.getProInformationModuleId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getProInformationModuleId().toString())
            )
            .body(result);
    }

    /**
     * {@code PUT  /pwc-information-modules/:proInformationModuleId} : Updates an existing pwcInformationModule.
     *
     * @param proInformationModuleId the id of the pwcInformationModuleDTO to save.
     * @param pwcInformationModuleDTO the pwcInformationModuleDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pwcInformationModuleDTO,
     * or with status {@code 400 (Bad Request)} if the pwcInformationModuleDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pwcInformationModuleDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pwc-information-modules/{proInformationModuleId}")
    public ResponseEntity<PwcInformationModuleDTO> updatePwcInformationModule(
        @PathVariable(value = "proInformationModuleId", required = false) final Long proInformationModuleId,
        @Valid @RequestBody PwcInformationModuleDTO pwcInformationModuleDTO
    ) throws URISyntaxException {
        log.debug("REST request to update PwcInformationModule : {}, {}", proInformationModuleId, pwcInformationModuleDTO);
        if (pwcInformationModuleDTO.getProInformationModuleId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(proInformationModuleId, pwcInformationModuleDTO.getProInformationModuleId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!pwcInformationModuleRepository.existsById(proInformationModuleId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        PwcInformationModuleDTO result = pwcInformationModuleService.save(pwcInformationModuleDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    pwcInformationModuleDTO.getProInformationModuleId().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PATCH  /pwc-information-modules/:proInformationModuleId} : Partial updates given fields of an existing pwcInformationModule, field will ignore if it is null
     *
     * @param proInformationModuleId the id of the pwcInformationModuleDTO to save.
     * @param pwcInformationModuleDTO the pwcInformationModuleDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pwcInformationModuleDTO,
     * or with status {@code 400 (Bad Request)} if the pwcInformationModuleDTO is not valid,
     * or with status {@code 404 (Not Found)} if the pwcInformationModuleDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the pwcInformationModuleDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/pwc-information-modules/{proInformationModuleId}", consumes = "application/merge-patch+json")
    public ResponseEntity<PwcInformationModuleDTO> partialUpdatePwcInformationModule(
        @PathVariable(value = "proInformationModuleId", required = false) final Long proInformationModuleId,
        @NotNull @RequestBody PwcInformationModuleDTO pwcInformationModuleDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update PwcInformationModule partially : {}, {}",
            proInformationModuleId,
            pwcInformationModuleDTO
        );
        if (pwcInformationModuleDTO.getProInformationModuleId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(proInformationModuleId, pwcInformationModuleDTO.getProInformationModuleId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!pwcInformationModuleRepository.existsById(proInformationModuleId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PwcInformationModuleDTO> result = pwcInformationModuleService.partialUpdate(pwcInformationModuleDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                true,
                ENTITY_NAME,
                pwcInformationModuleDTO.getProInformationModuleId().toString()
            )
        );
    }

    /**
     * {@code GET  /pwc-information-modules} : get all the pwcInformationModules.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pwcInformationModules in body.
     */
    @GetMapping("/pwc-information-modules")
    public List<PwcInformationModuleDTO> getAllPwcInformationModules() {
        log.debug("REST request to get all PwcInformationModules");
        return pwcInformationModuleService.findAll();
    }

    /**
     * {@code GET  /pwc-information-modules/:id} : get the "id" pwcInformationModule.
     *
     * @param id the id of the pwcInformationModuleDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pwcInformationModuleDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pwc-information-modules/{id}")
    public ResponseEntity<PwcInformationModuleDTO> getPwcInformationModule(@PathVariable Long id) {
        log.debug("REST request to get PwcInformationModule : {}", id);
        Optional<PwcInformationModuleDTO> pwcInformationModuleDTO = pwcInformationModuleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(pwcInformationModuleDTO);
    }

    /**
     * {@code DELETE  /pwc-information-modules/:id} : delete the "id" pwcInformationModule.
     *
     * @param id the id of the pwcInformationModuleDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pwc-information-modules/{id}")
    public ResponseEntity<Void> deletePwcInformationModule(@PathVariable Long id) {
        log.debug("REST request to delete PwcInformationModule : {}", id);
        pwcInformationModuleService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
