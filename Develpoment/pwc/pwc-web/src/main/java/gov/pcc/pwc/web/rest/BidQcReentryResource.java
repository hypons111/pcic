package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.repository.BidQcReentryRepository;
import gov.pcc.pwc.service.BidQcReentryService;
import gov.pcc.pwc.service.dto.BidQcReentryDTO;
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
 * REST controller for managing {@link gov.pcc.pwc.domain.BidQcReentry}.
 */
@RestController
@RequestMapping("/api")
public class BidQcReentryResource {

    private final Logger log = LoggerFactory.getLogger(BidQcReentryResource.class);

    private static final String ENTITY_NAME = "bidQcReentry";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BidQcReentryService bidQcReentryService;

    private final BidQcReentryRepository bidQcReentryRepository;

    public BidQcReentryResource(BidQcReentryService bidQcReentryService, BidQcReentryRepository bidQcReentryRepository) {
        this.bidQcReentryService = bidQcReentryService;
        this.bidQcReentryRepository = bidQcReentryRepository;
    }

    /**
     * {@code POST  /bid-qc-reentries} : Create a new bidQcReentry.
     *
     * @param bidQcReentryDTO the bidQcReentryDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bidQcReentryDTO, or with status {@code 400 (Bad Request)} if the bidQcReentry has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bid-qc-reentries")
    public ResponseEntity<BidQcReentryDTO> createBidQcReentry(@Valid @RequestBody BidQcReentryDTO bidQcReentryDTO)
        throws URISyntaxException {
        log.debug("REST request to save BidQcReentry : {}", bidQcReentryDTO);
        if (bidQcReentryDTO.getId() != null) {
            throw new BadRequestAlertException("A new bidQcReentry cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BidQcReentryDTO result = bidQcReentryService.save(bidQcReentryDTO);
        return ResponseEntity
            .created(new URI("/api/bid-qc-reentries/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /bid-qc-reentries/:id} : Updates an existing bidQcReentry.
     *
     * @param id the id of the bidQcReentryDTO to save.
     * @param bidQcReentryDTO the bidQcReentryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidQcReentryDTO,
     * or with status {@code 400 (Bad Request)} if the bidQcReentryDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bidQcReentryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bid-qc-reentries/{id}")
    public ResponseEntity<BidQcReentryDTO> updateBidQcReentry(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody BidQcReentryDTO bidQcReentryDTO
    ) throws URISyntaxException {
        log.debug("REST request to update BidQcReentry : {}, {}", id, bidQcReentryDTO);
        if (bidQcReentryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, bidQcReentryDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidQcReentryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        BidQcReentryDTO result = bidQcReentryService.save(bidQcReentryDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidQcReentryDTO.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /bid-qc-reentries/:id} : Partial updates given fields of an existing bidQcReentry, field will ignore if it is null
     *
     * @param id the id of the bidQcReentryDTO to save.
     * @param bidQcReentryDTO the bidQcReentryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidQcReentryDTO,
     * or with status {@code 400 (Bad Request)} if the bidQcReentryDTO is not valid,
     * or with status {@code 404 (Not Found)} if the bidQcReentryDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the bidQcReentryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/bid-qc-reentries/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<BidQcReentryDTO> partialUpdateBidQcReentry(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody BidQcReentryDTO bidQcReentryDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update BidQcReentry partially : {}, {}", id, bidQcReentryDTO);
        if (bidQcReentryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, bidQcReentryDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidQcReentryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BidQcReentryDTO> result = bidQcReentryService.partialUpdate(bidQcReentryDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidQcReentryDTO.getId())
        );
    }

    /**
     * {@code GET  /bid-qc-reentries} : get all the bidQcReentries.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bidQcReentries in body.
     */
    @GetMapping("/bid-qc-reentries")
    public List<BidQcReentryDTO> getAllBidQcReentries() {
        log.debug("REST request to get all BidQcReentries");
        return bidQcReentryService.findAll();
    }

    /**
     * {@code GET  /bid-qc-reentries/:id} : get the "id" bidQcReentry.
     *
     * @param id the id of the bidQcReentryDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bidQcReentryDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bid-qc-reentries/{id}")
    public ResponseEntity<BidQcReentryDTO> getBidQcReentry(@PathVariable String id) {
        log.debug("REST request to get BidQcReentry : {}", id);
        Optional<BidQcReentryDTO> bidQcReentryDTO = bidQcReentryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bidQcReentryDTO);
    }

    /**
     * {@code DELETE  /bid-qc-reentries/:id} : delete the "id" bidQcReentry.
     *
     * @param id the id of the bidQcReentryDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bid-qc-reentries/{id}")
    public ResponseEntity<Void> deleteBidQcReentry(@PathVariable String id) {
        log.debug("REST request to delete BidQcReentry : {}", id);
        bidQcReentryService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
