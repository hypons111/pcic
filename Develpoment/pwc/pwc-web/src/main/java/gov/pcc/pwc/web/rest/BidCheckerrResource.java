package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.repository.BidCheckerrRepository;
import gov.pcc.pwc.service.BidCheckerrService;
import gov.pcc.pwc.service.dto.BidCheckerrDTO;
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
 * REST controller for managing {@link gov.pcc.pwc.domain.BidCheckerr}.
 */
@RestController
@RequestMapping("/api")
public class BidCheckerrResource {

    private final Logger log = LoggerFactory.getLogger(BidCheckerrResource.class);

    private static final String ENTITY_NAME = "bidCheckerr";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BidCheckerrService bidCheckerrService;

    private final BidCheckerrRepository bidCheckerrRepository;

    public BidCheckerrResource(BidCheckerrService bidCheckerrService, BidCheckerrRepository bidCheckerrRepository) {
        this.bidCheckerrService = bidCheckerrService;
        this.bidCheckerrRepository = bidCheckerrRepository;
    }

    /**
     * {@code POST  /bid-checkerrs} : Create a new bidCheckerr.
     *
     * @param bidCheckerrDTO the bidCheckerrDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bidCheckerrDTO, or with status {@code 400 (Bad Request)} if the bidCheckerr has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bid-checkerrs")
    public ResponseEntity<BidCheckerrDTO> createBidCheckerr(@Valid @RequestBody BidCheckerrDTO bidCheckerrDTO) throws URISyntaxException {
        log.debug("REST request to save BidCheckerr : {}", bidCheckerrDTO);
        if (bidCheckerrDTO.getCode() != null) {
            throw new BadRequestAlertException("A new bidCheckerr cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BidCheckerrDTO result = bidCheckerrService.save(bidCheckerrDTO);
        return ResponseEntity
            .created(new URI("/api/bid-checkerrs/" + result.getCode()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getCode()))
            .body(result);
    }

    /**
     * {@code PUT  /bid-checkerrs/:code} : Updates an existing bidCheckerr.
     *
     * @param code the id of the bidCheckerrDTO to save.
     * @param bidCheckerrDTO the bidCheckerrDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidCheckerrDTO,
     * or with status {@code 400 (Bad Request)} if the bidCheckerrDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bidCheckerrDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bid-checkerrs/{code}")
    public ResponseEntity<BidCheckerrDTO> updateBidCheckerr(
        @PathVariable(value = "code", required = false) final String code,
        @Valid @RequestBody BidCheckerrDTO bidCheckerrDTO
    ) throws URISyntaxException {
        log.debug("REST request to update BidCheckerr : {}, {}", code, bidCheckerrDTO);
        if (bidCheckerrDTO.getCode() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(code, bidCheckerrDTO.getCode())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidCheckerrRepository.existsById(code)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        BidCheckerrDTO result = bidCheckerrService.save(bidCheckerrDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidCheckerrDTO.getCode()))
            .body(result);
    }

    /**
     * {@code PATCH  /bid-checkerrs/:code} : Partial updates given fields of an existing bidCheckerr, field will ignore if it is null
     *
     * @param code the id of the bidCheckerrDTO to save.
     * @param bidCheckerrDTO the bidCheckerrDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidCheckerrDTO,
     * or with status {@code 400 (Bad Request)} if the bidCheckerrDTO is not valid,
     * or with status {@code 404 (Not Found)} if the bidCheckerrDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the bidCheckerrDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/bid-checkerrs/{code}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<BidCheckerrDTO> partialUpdateBidCheckerr(
        @PathVariable(value = "code", required = false) final String code,
        @NotNull @RequestBody BidCheckerrDTO bidCheckerrDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update BidCheckerr partially : {}, {}", code, bidCheckerrDTO);
        if (bidCheckerrDTO.getCode() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(code, bidCheckerrDTO.getCode())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidCheckerrRepository.existsById(code)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BidCheckerrDTO> result = bidCheckerrService.partialUpdate(bidCheckerrDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidCheckerrDTO.getCode())
        );
    }

    /**
     * {@code GET  /bid-checkerrs} : get all the bidCheckerrs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bidCheckerrs in body.
     */
    @GetMapping("/bid-checkerrs")
    public List<BidCheckerrDTO> getAllBidCheckerrs() {
        log.debug("REST request to get all BidCheckerrs");
        return bidCheckerrService.findAll();
    }

    /**
     * {@code GET  /bid-checkerrs/:id} : get the "id" bidCheckerr.
     *
     * @param id the id of the bidCheckerrDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bidCheckerrDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bid-checkerrs/{id}")
    public ResponseEntity<BidCheckerrDTO> getBidCheckerr(@PathVariable String id) {
        log.debug("REST request to get BidCheckerr : {}", id);
        Optional<BidCheckerrDTO> bidCheckerrDTO = bidCheckerrService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bidCheckerrDTO);
    }

    /**
     * {@code DELETE  /bid-checkerrs/:id} : delete the "id" bidCheckerr.
     *
     * @param id the id of the bidCheckerrDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bid-checkerrs/{id}")
    public ResponseEntity<Void> deleteBidCheckerr(@PathVariable String id) {
        log.debug("REST request to delete BidCheckerr : {}", id);
        bidCheckerrService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
