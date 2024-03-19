package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.repository.EngEbillingCheckRepository;
import gov.pcc.pwc.service.EngEbillingCheckService;
import gov.pcc.pwc.service.dto.EngEbillingCheckDTO;
import gov.pcc.pwc.common.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import gov.pcc.pwc.service.genSerialNumber.errors.TypeVariableNotMatchException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link gov.pcc.pwc.domain.EngEbillingCheck}.
 */
@RestController
@RequestMapping("/api")
public class EngEbillingCheckResource {

    private final Logger log = LoggerFactory.getLogger(EngEbillingCheckResource.class);

    private static final String ENTITY_NAME = "engEbillingCheck";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EngEbillingCheckService engEbillingCheckService;

    private final EngEbillingCheckRepository engEbillingCheckRepository;

    public EngEbillingCheckResource(
        EngEbillingCheckService engEbillingCheckService,
        EngEbillingCheckRepository engEbillingCheckRepository
    ) {
        this.engEbillingCheckService = engEbillingCheckService;
        this.engEbillingCheckRepository = engEbillingCheckRepository;
    }

    /**
     * {@code POST  /eng-ebilling-checks} : Create a new engEbillingCheck.
     *
     * @param engEbillingCheckDTO the engEbillingCheckDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new engEbillingCheckDTO, or with status {@code 400 (Bad Request)} if the engEbillingCheck has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/eng-ebilling-checks")
    public ResponseEntity<EngEbillingCheckDTO> createEngEbillingCheck(@Valid @RequestBody EngEbillingCheckDTO engEbillingCheckDTO)
        throws URISyntaxException, TypeVariableNotMatchException {
        log.debug("REST request to save EngEbillingCheck : {}", engEbillingCheckDTO);
        if (engEbillingCheckDTO.getEngEbillingCheckId() != null) {
            throw new BadRequestAlertException("A new engEbillingCheck cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EngEbillingCheckDTO result = engEbillingCheckService.save(engEbillingCheckDTO);
        return ResponseEntity
            .created(new URI("/api/eng-ebilling-checks/" + result.getEngEbillingCheckId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getEngEbillingCheckId()))
            .body(result);
    }

    /**
     * {@code PUT  /eng-ebilling-checks/:engEbillingCheckId} : Updates an existing engEbillingCheck.
     *
     * @param engEbillingCheckId the id of the engEbillingCheckDTO to save.
     * @param engEbillingCheckDTO the engEbillingCheckDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated engEbillingCheckDTO,
     * or with status {@code 400 (Bad Request)} if the engEbillingCheckDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the engEbillingCheckDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/eng-ebilling-checks/{engEbillingCheckId}")
    public ResponseEntity<EngEbillingCheckDTO> updateEngEbillingCheck(
        @PathVariable(value = "engEbillingCheckId", required = false) final String engEbillingCheckId,
        @Valid @RequestBody EngEbillingCheckDTO engEbillingCheckDTO
    ) throws URISyntaxException, TypeVariableNotMatchException {
        log.debug("REST request to update EngEbillingCheck : {}, {}", engEbillingCheckId, engEbillingCheckDTO);
        if (engEbillingCheckDTO.getEngEbillingCheckId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(engEbillingCheckId, engEbillingCheckDTO.getEngEbillingCheckId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!engEbillingCheckRepository.existsById(engEbillingCheckId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        EngEbillingCheckDTO result = engEbillingCheckService.save(engEbillingCheckDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, engEbillingCheckDTO.getEngEbillingCheckId()))
            .body(result);
    }

    /**
     * {@code PATCH  /eng-ebilling-checks/:engEbillingCheckId} : Partial updates given fields of an existing engEbillingCheck, field will ignore if it is null
     *
     * @param engEbillingCheckId the id of the engEbillingCheckDTO to save.
     * @param engEbillingCheckDTO the engEbillingCheckDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated engEbillingCheckDTO,
     * or with status {@code 400 (Bad Request)} if the engEbillingCheckDTO is not valid,
     * or with status {@code 404 (Not Found)} if the engEbillingCheckDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the engEbillingCheckDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/eng-ebilling-checks/{engEbillingCheckId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<EngEbillingCheckDTO> partialUpdateEngEbillingCheck(
        @PathVariable(value = "engEbillingCheckId", required = false) final String engEbillingCheckId,
        @NotNull @RequestBody EngEbillingCheckDTO engEbillingCheckDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update EngEbillingCheck partially : {}, {}", engEbillingCheckId, engEbillingCheckDTO);
        if (engEbillingCheckDTO.getEngEbillingCheckId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(engEbillingCheckId, engEbillingCheckDTO.getEngEbillingCheckId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!engEbillingCheckRepository.existsById(engEbillingCheckId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<EngEbillingCheckDTO> result = engEbillingCheckService.partialUpdate(engEbillingCheckDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, engEbillingCheckDTO.getEngEbillingCheckId())
        );
    }

    /**
     * {@code GET  /eng-ebilling-checks} : get all the engEbillingChecks.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of engEbillingChecks in body.
     */
    @GetMapping("/eng-ebilling-checks")
    public List<EngEbillingCheckDTO> getAllEngEbillingChecks() {
        log.debug("REST request to get all EngEbillingChecks");
        return engEbillingCheckService.findAll();
    }

    /**
     * {@code GET  /eng-ebilling-checks/:id} : get the "id" engEbillingCheck.
     *
     * @param id the id of the engEbillingCheckDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the engEbillingCheckDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/eng-ebilling-checks/{id}")
    public ResponseEntity<EngEbillingCheckDTO> getEngEbillingCheck(@PathVariable String id) {
        log.debug("REST request to get EngEbillingCheck : {}", id);
        Optional<EngEbillingCheckDTO> engEbillingCheckDTO = engEbillingCheckService.findOne(id);
        return ResponseUtil.wrapOrNotFound(engEbillingCheckDTO);
    }

    @GetMapping("/eng-ebilling-checks/{docType}/{docId}")
    public EngEbillingCheckDTO getEngEbillingCheck(@PathVariable String docType, @PathVariable String docId) {
        log.debug("REST request to get EngEbillingCheck, docType : {}, docId : {}", docType, docId);
        Optional<EngEbillingCheckDTO> engEbillingCheckDTO = engEbillingCheckService.findByDocTypeAndDocId(docType, docId);
        return engEbillingCheckDTO.orElse(null);
    }

    /**
     * {@code DELETE  /eng-ebilling-checks/:id} : delete the "id" engEbillingCheck.
     *
     * @param id the id of the engEbillingCheckDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/eng-ebilling-checks/{id}")
    public ResponseEntity<Void> deleteEngEbillingCheck(@PathVariable String id) {
        log.debug("REST request to delete EngEbillingCheck : {}", id);
        engEbillingCheckService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
