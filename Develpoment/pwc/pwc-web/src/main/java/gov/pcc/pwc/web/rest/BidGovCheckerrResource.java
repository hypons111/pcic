package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.domain.BidGovCheckerrPrimaryKey;
import gov.pcc.pwc.repository.BidGovCheckerrRepository;
import gov.pcc.pwc.service.BidGovCheckerrService;
import gov.pcc.pwc.service.criteria.BidGovCheckerrCriteria;
import gov.pcc.pwc.service.dto.BidGovCheckerrDTO;
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
 * REST controller for managing {@link gov.pcc.pwc.domain.BidGovCheckerr}.
 */
@RestController
@RequestMapping("/api")
public class BidGovCheckerrResource {

    private final Logger log = LoggerFactory.getLogger(BidGovCheckerrResource.class);

    private static final String ENTITY_NAME = "bidGovCheckerr";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BidGovCheckerrService bidGovCheckerrService;

    private final BidGovCheckerrRepository bidGovCheckerrRepository;

    public BidGovCheckerrResource(BidGovCheckerrService bidGovCheckerrService, BidGovCheckerrRepository bidGovCheckerrRepository) {
        this.bidGovCheckerrService = bidGovCheckerrService;
        this.bidGovCheckerrRepository = bidGovCheckerrRepository;
    }

    /**
     * {@code POST  /bid-gov-checkerrs} : Create a new bidGovCheckerr.
     *
     * @param bidGovCheckerrDTO the bidGovCheckerrDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bidGovCheckerrDTO, or with status {@code 400 (Bad Request)} if the bidGovCheckerr has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bid-gov-checkerrs")
    public ResponseEntity<BidGovCheckerrDTO> createBidGovCheckerr(@Valid @RequestBody BidGovCheckerrDTO bidGovCheckerrDTO)
        throws URISyntaxException {
        log.debug("REST request to save BidGovCheckerr : {}", bidGovCheckerrDTO);
        if (bidGovCheckerrDTO.getId() != null) {
            throw new BadRequestAlertException("A new bidGovCheckerr cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BidGovCheckerrDTO result = bidGovCheckerrService.save(bidGovCheckerrDTO);
        return ResponseEntity
            .created(new URI("/api/bid-gov-checkerrs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bid-gov-checkerrs/:id} : Updates an existing bidGovCheckerr.
     *
     * @param id the id of the bidGovCheckerrDTO to save.
     * @param bidGovCheckerrDTO the bidGovCheckerrDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidGovCheckerrDTO,
     * or with status {@code 400 (Bad Request)} if the bidGovCheckerrDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bidGovCheckerrDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bid-gov-checkerrs/{id}")
    public ResponseEntity<BidGovCheckerrDTO> updateBidGovCheckerr(
        @PathVariable(value = "id", required = false) final BidGovCheckerrPrimaryKey id,
        @Valid @RequestBody BidGovCheckerrDTO bidGovCheckerrDTO
    ) throws URISyntaxException {
        log.debug("REST request to update BidGovCheckerr : {}, {}", id, bidGovCheckerrDTO);
        if (bidGovCheckerrDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, bidGovCheckerrDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidGovCheckerrRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        BidGovCheckerrDTO result = bidGovCheckerrService.save(bidGovCheckerrDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidGovCheckerrDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /bid-gov-checkerrs/:id} : Partial updates given fields of an existing bidGovCheckerr, field will ignore if it is null
     *
     * @param id the id of the bidGovCheckerrDTO to save.
     * @param bidGovCheckerrDTO the bidGovCheckerrDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidGovCheckerrDTO,
     * or with status {@code 400 (Bad Request)} if the bidGovCheckerrDTO is not valid,
     * or with status {@code 404 (Not Found)} if the bidGovCheckerrDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the bidGovCheckerrDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/bid-gov-checkerrs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<BidGovCheckerrDTO> partialUpdateBidGovCheckerr(
        @PathVariable(value = "id", required = false) final BidGovCheckerrPrimaryKey id,
        @NotNull @RequestBody BidGovCheckerrDTO bidGovCheckerrDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update BidGovCheckerr partially : {}, {}", id, bidGovCheckerrDTO);
        if (bidGovCheckerrDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, bidGovCheckerrDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidGovCheckerrRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BidGovCheckerrDTO> result = bidGovCheckerrService.partialUpdate(bidGovCheckerrDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidGovCheckerrDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /bid-gov-checkerrs} : get all the bidGovCheckerrs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bidGovCheckerrs in body.
     */
    @GetMapping("/bid-gov-checkerrs")
    public List<BidGovCheckerrDTO> getAllBidGovCheckerrs() {
        log.debug("REST request to get all BidGovCheckerrs");
        return bidGovCheckerrService.findAll();
    }

    @PostMapping("/bid-gov-checkerrs/findAllByCriteria")
    public List<BidGovCheckerrDTO> findAllByCriteria(@RequestBody BidGovCheckerrCriteria criteria) {
        log.debug("REST request to get BidGovCheckerr List by criteria : {}", criteria);
        return bidGovCheckerrService.findAllByCriteria(criteria);
    }

    /**
     * {@code GET  /bid-gov-checkerrs/:id} : get the "id" bidGovCheckerr.
     *
     * @param id the id of the bidGovCheckerrDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bidGovCheckerrDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bid-gov-checkerrs/{id}")
    public ResponseEntity<BidGovCheckerrDTO> getBidGovCheckerr(@PathVariable BidGovCheckerrPrimaryKey id) {
        log.debug("REST request to get BidGovCheckerr : {}", id);
        Optional<BidGovCheckerrDTO> bidGovCheckerrDTO = bidGovCheckerrService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bidGovCheckerrDTO);
    }

    /**
     * {@code DELETE  /bid-gov-checkerrs/:id} : delete the "id" bidGovCheckerr.
     *
     * @param id the id of the bidGovCheckerrDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bid-gov-checkerrs/{id}")
    public ResponseEntity<Void> deleteBidGovCheckerr(@PathVariable BidGovCheckerrPrimaryKey id) {
        log.debug("REST request to delete BidGovCheckerr : {}", id);
        bidGovCheckerrService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
