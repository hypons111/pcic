package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.repository.BidPlnunitbRepository;
import gov.pcc.pwc.service.BidPlnunitbService;
import gov.pcc.pwc.service.criteria.BidPlnunitbCriteria;
import gov.pcc.pwc.service.dto.BidPlnunitbDTO;
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
 * REST controller for managing {@link gov.pcc.pwc.domain.BidPlnunitb}.
 */
@RestController
@RequestMapping("/api")
public class BidPlnunitbResource {

    private final Logger log = LoggerFactory.getLogger(BidPlnunitbResource.class);

    private static final String ENTITY_NAME = "bidPlnunitb";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BidPlnunitbService bidPlnunitbService;

    private final BidPlnunitbRepository bidPlnunitbRepository;

    public BidPlnunitbResource(BidPlnunitbService bidPlnunitbService, BidPlnunitbRepository bidPlnunitbRepository) {
        this.bidPlnunitbService = bidPlnunitbService;
        this.bidPlnunitbRepository = bidPlnunitbRepository;
    }

    /**
     * {@code POST  /bid-plnunitbs} : Create a new bidPlnunitb.
     *
     * @param bidPlnunitbDTO the bidPlnunitbDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bidPlnunitbDTO, or with status {@code 400 (Bad Request)} if the bidPlnunitb has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bid-plnunitbs")
    public ResponseEntity<BidPlnunitbDTO> createBidPlnunitb(@Valid @RequestBody BidPlnunitbDTO bidPlnunitbDTO) throws URISyntaxException {
        log.debug("REST request to save BidPlnunitb : {}", bidPlnunitbDTO);
        if (bidPlnunitbDTO.getId() != null) {
            throw new BadRequestAlertException("A new bidPlnunitb cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BidPlnunitbDTO result = bidPlnunitbService.save(bidPlnunitbDTO);
        return ResponseEntity
            .created(new URI("/api/bid-plnunitbs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bid-plnunitbs/:id} : Updates an existing bidPlnunitb.
     *
     * @param id the id of the bidPlnunitbDTO to save.
     * @param bidPlnunitbDTO the bidPlnunitbDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidPlnunitbDTO,
     * or with status {@code 400 (Bad Request)} if the bidPlnunitbDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bidPlnunitbDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bid-plnunitbs/{id}")
    public ResponseEntity<BidPlnunitbDTO> updateBidPlnunitb(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody BidPlnunitbDTO bidPlnunitbDTO
    ) throws URISyntaxException {
        log.debug("REST request to update BidPlnunitb : {}, {}", id, bidPlnunitbDTO);
        if (bidPlnunitbDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, bidPlnunitbDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidPlnunitbRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        BidPlnunitbDTO result = bidPlnunitbService.save(bidPlnunitbDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidPlnunitbDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /bid-plnunitbs/:id} : Partial updates given fields of an existing bidPlnunitb, field will ignore if it is null
     *
     * @param id the id of the bidPlnunitbDTO to save.
     * @param bidPlnunitbDTO the bidPlnunitbDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidPlnunitbDTO,
     * or with status {@code 400 (Bad Request)} if the bidPlnunitbDTO is not valid,
     * or with status {@code 404 (Not Found)} if the bidPlnunitbDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the bidPlnunitbDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/bid-plnunitbs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<BidPlnunitbDTO> partialUpdateBidPlnunitb(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody BidPlnunitbDTO bidPlnunitbDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update BidPlnunitb partially : {}, {}", id, bidPlnunitbDTO);
        if (bidPlnunitbDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, bidPlnunitbDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidPlnunitbRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BidPlnunitbDTO> result = bidPlnunitbService.partialUpdate(bidPlnunitbDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidPlnunitbDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /bid-plnunitbs} : get all the bidPlnunitbs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bidPlnunitbs in body.
     */
    @GetMapping("/bid-plnunitbs")
    public List<BidPlnunitbDTO> getAllBidPlnunitbs() {
        log.debug("REST request to get all BidPlnunitbs");
        return bidPlnunitbService.findAll();
    }

    /**
     * {@code GET  /bid-plnunitbs/:id} : get the "id" bidPlnunitb.
     *
     * @param id the id of the bidPlnunitbDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bidPlnunitbDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bid-plnunitbs/{id}")
    public ResponseEntity<BidPlnunitbDTO> getBidPlnunitb(@PathVariable Long id) {
        log.debug("REST request to get BidPlnunitb : {}", id);
        Optional<BidPlnunitbDTO> bidPlnunitbDTO = bidPlnunitbService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bidPlnunitbDTO);
    }

    /**
     * {@code DELETE  /bid-plnunitbs/:id} : delete the "id" bidPlnunitb.
     *
     * @param id the id of the bidPlnunitbDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bid-plnunitbs/{id}")
    public ResponseEntity<Void> deleteBidPlnunitb(@PathVariable Long id) {
        log.debug("REST request to delete BidPlnunitb : {}", id);
        bidPlnunitbService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    @PostMapping("/bid-plnunitbs/getAll")
    public List<BidPlnunitbDTO> getAll(@RequestBody BidPlnunitbCriteria criteria) {
        log.debug("REST request to get BidPlnunitb : {}", criteria);
        return bidPlnunitbService.findAllByCriteria(criteria);
    }
}
