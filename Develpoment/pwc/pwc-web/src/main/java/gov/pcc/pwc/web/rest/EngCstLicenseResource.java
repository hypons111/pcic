package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.repository.EngCstLicenseRepository;
import gov.pcc.pwc.service.EngCstLicenseService;
import gov.pcc.pwc.service.dto.EngCstLicenseDTO;
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
 * REST controller for managing {@link gov.pcc.pwc.domain.EngCstLicense}.
 */
@RestController
@RequestMapping("/api")
public class EngCstLicenseResource {

    private final Logger log = LoggerFactory.getLogger(EngCstLicenseResource.class);

    private static final String ENTITY_NAME = "engCstLicense";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EngCstLicenseService engCstLicenseService;

    private final EngCstLicenseRepository engCstLicenseRepository;

    public EngCstLicenseResource(EngCstLicenseService engCstLicenseService, EngCstLicenseRepository engCstLicenseRepository) {
        this.engCstLicenseService = engCstLicenseService;
        this.engCstLicenseRepository = engCstLicenseRepository;
    }

    /**
     * {@code POST  /eng-cst-licenses} : Create a new engCstLicense.
     *
     * @param engCstLicenseDTO the engCstLicenseDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new engCstLicenseDTO, or with status {@code 400 (Bad Request)} if the engCstLicense has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/eng-cst-licenses")
    public ResponseEntity<EngCstLicenseDTO> createEngCstLicense(@Valid @RequestBody EngCstLicenseDTO engCstLicenseDTO)
        throws URISyntaxException {
        log.debug("REST request to save EngCstLicense : {}", engCstLicenseDTO);
        if (engCstLicenseDTO.getEngCstLicenseId() != null) {
            throw new BadRequestAlertException("A new engCstLicense cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EngCstLicenseDTO result = engCstLicenseService.save(engCstLicenseDTO);
        return ResponseEntity
            .created(new URI("/api/eng-cst-licenses/" + result.getEngCstLicenseId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getEngCstLicenseId()))
            .body(result);
    }

    /**
     * {@code PUT  /eng-cst-licenses/:engCstLicenseId} : Updates an existing engCstLicense.
     *
     * @param engCstLicenseId the id of the engCstLicenseDTO to save.
     * @param engCstLicenseDTO the engCstLicenseDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated engCstLicenseDTO,
     * or with status {@code 400 (Bad Request)} if the engCstLicenseDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the engCstLicenseDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/eng-cst-licenses/{engCstLicenseId}")
    public ResponseEntity<EngCstLicenseDTO> updateEngCstLicense(
        @PathVariable(value = "engCstLicenseId", required = false) final String engCstLicenseId,
        @Valid @RequestBody EngCstLicenseDTO engCstLicenseDTO
    ) throws URISyntaxException {
        log.debug("REST request to update EngCstLicense : {}, {}", engCstLicenseId, engCstLicenseDTO);
        if (engCstLicenseDTO.getEngCstLicenseId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(engCstLicenseId, engCstLicenseDTO.getEngCstLicenseId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!engCstLicenseRepository.existsById(engCstLicenseId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        EngCstLicenseDTO result = engCstLicenseService.save(engCstLicenseDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, engCstLicenseDTO.getEngCstLicenseId()))
            .body(result);
    }

    /**
     * {@code PATCH  /eng-cst-licenses/:engCstLicenseId} : Partial updates given fields of an existing engCstLicense, field will ignore if it is null
     *
     * @param engCstLicenseId the id of the engCstLicenseDTO to save.
     * @param engCstLicenseDTO the engCstLicenseDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated engCstLicenseDTO,
     * or with status {@code 400 (Bad Request)} if the engCstLicenseDTO is not valid,
     * or with status {@code 404 (Not Found)} if the engCstLicenseDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the engCstLicenseDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/eng-cst-licenses/{engCstLicenseId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<EngCstLicenseDTO> partialUpdateEngCstLicense(
        @PathVariable(value = "engCstLicenseId", required = false) final String engCstLicenseId,
        @NotNull @RequestBody EngCstLicenseDTO engCstLicenseDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update EngCstLicense partially : {}, {}", engCstLicenseId, engCstLicenseDTO);
        if (engCstLicenseDTO.getEngCstLicenseId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(engCstLicenseId, engCstLicenseDTO.getEngCstLicenseId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!engCstLicenseRepository.existsById(engCstLicenseId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<EngCstLicenseDTO> result = engCstLicenseService.partialUpdate(engCstLicenseDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, engCstLicenseDTO.getEngCstLicenseId())
        );
    }

    /**
     * {@code GET  /eng-cst-licenses} : get all the engCstLicenses.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of engCstLicenses in body.
     */
    @GetMapping("/eng-cst-licenses")
    public List<EngCstLicenseDTO> getAllEngCstLicenses() {
        log.debug("REST request to get all EngCstLicenses");
        return engCstLicenseService.findAll();
    }

    /**
     * {@code GET  /eng-cst-licenses/:id} : get the "id" engCstLicense.
     *
     * @param id the id of the engCstLicenseDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the engCstLicenseDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/eng-cst-licenses/{id}")
    public ResponseEntity<EngCstLicenseDTO> getEngCstLicense(@PathVariable String id) {
        log.debug("REST request to get EngCstLicense : {}", id);
        Optional<EngCstLicenseDTO> engCstLicenseDTO = engCstLicenseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(engCstLicenseDTO);
    }

    /**
     * {@code DELETE  /eng-cst-licenses/:id} : delete the "id" engCstLicense.
     *
     * @param id the id of the engCstLicenseDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/eng-cst-licenses/{id}")
    public ResponseEntity<Void> deleteEngCstLicense(@PathVariable String id) {
        log.debug("REST request to delete EngCstLicense : {}", id);
        engCstLicenseService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
