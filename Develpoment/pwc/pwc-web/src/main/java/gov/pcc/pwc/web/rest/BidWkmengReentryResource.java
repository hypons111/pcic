package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.repository.BidWkmengReentryRepository;
import gov.pcc.pwc.service.BidWkmengReentryService;
import gov.pcc.pwc.service.dto.BidWkmengReentryDTO;
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
 * REST controller for managing {@link gov.pcc.pwc.domain.BidWkmengReentry}.
 */
@RestController
@RequestMapping("/api")
public class BidWkmengReentryResource {

    private final Logger log = LoggerFactory.getLogger(BidWkmengReentryResource.class);

    private static final String ENTITY_NAME = "bidWkmengReentry";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BidWkmengReentryService bidWkmengReentryService;

    private final BidWkmengReentryRepository bidWkmengReentryRepository;

    public BidWkmengReentryResource(
        BidWkmengReentryService bidWkmengReentryService,
        BidWkmengReentryRepository bidWkmengReentryRepository
    ) {
        this.bidWkmengReentryService = bidWkmengReentryService;
        this.bidWkmengReentryRepository = bidWkmengReentryRepository;
    }

    /**
     * {@code POST  /bid-wkmeng-reentries} : Create a new bidWkmengReentry.
     *
     * @param bidWkmengReentryDTO the bidWkmengReentryDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bidWkmengReentryDTO, or with status {@code 400 (Bad Request)} if the bidWkmengReentry has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bid-wkmeng-reentries")
    public ResponseEntity<BidWkmengReentryDTO> createBidWkmengReentry(@Valid @RequestBody BidWkmengReentryDTO bidWkmengReentryDTO)
        throws URISyntaxException {
        log.debug("REST request to save BidWkmengReentry : {}", bidWkmengReentryDTO);
        if (bidWkmengReentryDTO.getId() != null) {
            throw new BadRequestAlertException("A new bidWkmengReentry cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BidWkmengReentryDTO result = bidWkmengReentryService.save(bidWkmengReentryDTO);
        return ResponseEntity
            .created(new URI("/api/bid-wkmeng-reentries/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /bid-wkmeng-reentries/:id} : Updates an existing bidWkmengReentry.
     *
     * @param id the id of the bidWkmengReentryDTO to save.
     * @param bidWkmengReentryDTO the bidWkmengReentryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidWkmengReentryDTO,
     * or with status {@code 400 (Bad Request)} if the bidWkmengReentryDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bidWkmengReentryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bid-wkmeng-reentries/{id}")
    public ResponseEntity<BidWkmengReentryDTO> updateBidWkmengReentry(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody BidWkmengReentryDTO bidWkmengReentryDTO
    ) throws URISyntaxException {
        log.debug("REST request to update BidWkmengReentry : {}, {}", id, bidWkmengReentryDTO);
        if (bidWkmengReentryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, bidWkmengReentryDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidWkmengReentryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        BidWkmengReentryDTO result = bidWkmengReentryService.save(bidWkmengReentryDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidWkmengReentryDTO.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /bid-wkmeng-reentries/:id} : Partial updates given fields of an existing bidWkmengReentry, field will ignore if it is null
     *
     * @param id the id of the bidWkmengReentryDTO to save.
     * @param bidWkmengReentryDTO the bidWkmengReentryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidWkmengReentryDTO,
     * or with status {@code 400 (Bad Request)} if the bidWkmengReentryDTO is not valid,
     * or with status {@code 404 (Not Found)} if the bidWkmengReentryDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the bidWkmengReentryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/bid-wkmeng-reentries/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<BidWkmengReentryDTO> partialUpdateBidWkmengReentry(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody BidWkmengReentryDTO bidWkmengReentryDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update BidWkmengReentry partially : {}, {}", id, bidWkmengReentryDTO);
        if (bidWkmengReentryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, bidWkmengReentryDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidWkmengReentryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BidWkmengReentryDTO> result = bidWkmengReentryService.partialUpdate(bidWkmengReentryDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidWkmengReentryDTO.getId())
        );
    }

    /**
     * {@code GET  /bid-wkmeng-reentries} : get all the bidWkmengReentries.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bidWkmengReentries in body.
     */
    @GetMapping("/bid-wkmeng-reentries")
    public List<BidWkmengReentryDTO> getAllBidWkmengReentries() {
        log.debug("REST request to get all BidWkmengReentries");
        return bidWkmengReentryService.findAll();
    }

    /**
     * {@code GET  /bid-wkmeng-reentries/:id} : get the "id" bidWkmengReentry.
     *
     * @param id the id of the bidWkmengReentryDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bidWkmengReentryDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bid-wkmeng-reentries/{id}")
    public ResponseEntity<BidWkmengReentryDTO> getBidWkmengReentry(@PathVariable String id) {
        log.debug("REST request to get BidWkmengReentry : {}", id);
        Optional<BidWkmengReentryDTO> bidWkmengReentryDTO = bidWkmengReentryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bidWkmengReentryDTO);
    }

    /**
     * {@code DELETE  /bid-wkmeng-reentries/:id} : delete the "id" bidWkmengReentry.
     *
     * @param id the id of the bidWkmengReentryDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bid-wkmeng-reentries/{id}")
    public ResponseEntity<Void> deleteBidWkmengReentry(@PathVariable String id) {
        log.debug("REST request to delete BidWkmengReentry : {}", id);
        bidWkmengReentryService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
