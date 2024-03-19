package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.repository.EngEbillingCheckDtlRepository;
import gov.pcc.pwc.service.EngEbillingCheckDtlService;
import gov.pcc.pwc.service.dto.EngEbillingCheckDtlDTO;
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
 * REST controller for managing {@link gov.pcc.pwc.domain.EngEbillingCheckDtl}.
 */
@RestController
@RequestMapping("/api")
public class EngEbillingCheckDtlResource {

    private final Logger log = LoggerFactory.getLogger(EngEbillingCheckDtlResource.class);

    private static final String ENTITY_NAME = "engEbillingCheckDtl";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EngEbillingCheckDtlService engEbillingCheckDtlService;

    private final EngEbillingCheckDtlRepository engEbillingCheckDtlRepository;

    public EngEbillingCheckDtlResource(
        EngEbillingCheckDtlService engEbillingCheckDtlService,
        EngEbillingCheckDtlRepository engEbillingCheckDtlRepository
    ) {
        this.engEbillingCheckDtlService = engEbillingCheckDtlService;
        this.engEbillingCheckDtlRepository = engEbillingCheckDtlRepository;
    }

    /**
     * {@code POST  /eng-ebilling-check-dtls} : Create a new engEbillingCheckDtl.
     *
     * @param engEbillingCheckDtlDTO the engEbillingCheckDtlDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new engEbillingCheckDtlDTO, or with status {@code 400 (Bad Request)} if the engEbillingCheckDtl has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/eng-ebilling-check-dtls")
    public ResponseEntity<EngEbillingCheckDtlDTO> createEngEbillingCheckDtl(
        @Valid @RequestBody EngEbillingCheckDtlDTO engEbillingCheckDtlDTO
    ) throws URISyntaxException {
        log.debug("REST request to save EngEbillingCheckDtl : {}", engEbillingCheckDtlDTO);
        if (engEbillingCheckDtlDTO.getEngEbillingCheckId() != null) {
            throw new BadRequestAlertException("A new engEbillingCheckDtl cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EngEbillingCheckDtlDTO result = engEbillingCheckDtlService.save(engEbillingCheckDtlDTO);
        return ResponseEntity
            .created(new URI("/api/eng-ebilling-check-dtls/" + result.getEngEbillingCheckId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getEngEbillingCheckId()))
            .body(result);
    }

    /**
     * {@code PUT  /eng-ebilling-check-dtls/:engEbillingCheckId} : Updates an existing engEbillingCheckDtl.
     *
     * @param engEbillingCheckId the id of the engEbillingCheckDtlDTO to save.
     * @param engEbillingCheckDtlDTO the engEbillingCheckDtlDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated engEbillingCheckDtlDTO,
     * or with status {@code 400 (Bad Request)} if the engEbillingCheckDtlDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the engEbillingCheckDtlDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/eng-ebilling-check-dtls/{engEbillingCheckId}")
    public ResponseEntity<EngEbillingCheckDtlDTO> updateEngEbillingCheckDtl(
        @PathVariable(value = "engEbillingCheckId", required = false) final String engEbillingCheckId,
        @Valid @RequestBody EngEbillingCheckDtlDTO engEbillingCheckDtlDTO
    ) throws URISyntaxException {
        log.debug("REST request to update EngEbillingCheckDtl : {}, {}", engEbillingCheckId, engEbillingCheckDtlDTO);
        if (engEbillingCheckDtlDTO.getEngEbillingCheckId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(engEbillingCheckId, engEbillingCheckDtlDTO.getEngEbillingCheckId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!engEbillingCheckDtlRepository.existsById(engEbillingCheckId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        EngEbillingCheckDtlDTO result = engEbillingCheckDtlService.save(engEbillingCheckDtlDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, engEbillingCheckDtlDTO.getEngEbillingCheckId()))
            .body(result);
    }

    /**
     * {@code PATCH  /eng-ebilling-check-dtls/:engEbillingCheckId} : Partial updates given fields of an existing engEbillingCheckDtl, field will ignore if it is null
     *
     * @param engEbillingCheckId the id of the engEbillingCheckDtlDTO to save.
     * @param engEbillingCheckDtlDTO the engEbillingCheckDtlDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated engEbillingCheckDtlDTO,
     * or with status {@code 400 (Bad Request)} if the engEbillingCheckDtlDTO is not valid,
     * or with status {@code 404 (Not Found)} if the engEbillingCheckDtlDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the engEbillingCheckDtlDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/eng-ebilling-check-dtls/{engEbillingCheckId}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public ResponseEntity<EngEbillingCheckDtlDTO> partialUpdateEngEbillingCheckDtl(
        @PathVariable(value = "engEbillingCheckId", required = false) final String engEbillingCheckId,
        @NotNull @RequestBody EngEbillingCheckDtlDTO engEbillingCheckDtlDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update EngEbillingCheckDtl partially : {}, {}", engEbillingCheckId, engEbillingCheckDtlDTO);
        if (engEbillingCheckDtlDTO.getEngEbillingCheckId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(engEbillingCheckId, engEbillingCheckDtlDTO.getEngEbillingCheckId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!engEbillingCheckDtlRepository.existsById(engEbillingCheckId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<EngEbillingCheckDtlDTO> result = engEbillingCheckDtlService.partialUpdate(engEbillingCheckDtlDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, engEbillingCheckDtlDTO.getEngEbillingCheckId())
        );
    }

    /**
     * {@code GET  /eng-ebilling-check-dtls} : get all the engEbillingCheckDtls.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of engEbillingCheckDtls in body.
     */
    @GetMapping("/eng-ebilling-check-dtls")
    public List<EngEbillingCheckDtlDTO> getAllEngEbillingCheckDtls() {
        log.debug("REST request to get all EngEbillingCheckDtls");
        return engEbillingCheckDtlService.findAll();
    }

    /**
     * {@code GET  /eng-ebilling-check-dtls/:id} : get the "id" engEbillingCheckDtl.
     *
     * @param id the id of the engEbillingCheckDtlDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the engEbillingCheckDtlDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/eng-ebilling-check-dtls/{id}")
    public ResponseEntity<EngEbillingCheckDtlDTO> getEngEbillingCheckDtl(@PathVariable String id) {
        log.debug("REST request to get EngEbillingCheckDtl : {}", id);
        Optional<EngEbillingCheckDtlDTO> engEbillingCheckDtlDTO = engEbillingCheckDtlService.findOne(id);
        return ResponseUtil.wrapOrNotFound(engEbillingCheckDtlDTO);
    }

    /**
     * {@code DELETE  /eng-ebilling-check-dtls/:id} : delete the "id" engEbillingCheckDtl.
     *
     * @param id the id of the engEbillingCheckDtlDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/eng-ebilling-check-dtls/{id}")
    public ResponseEntity<Void> deleteEngEbillingCheckDtl(@PathVariable String id) {
        log.debug("REST request to delete EngEbillingCheckDtl : {}", id);
        engEbillingCheckDtlService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
