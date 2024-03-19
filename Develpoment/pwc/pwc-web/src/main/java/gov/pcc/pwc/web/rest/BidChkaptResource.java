package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.domain.BidChkaptPrimaryKey;
import gov.pcc.pwc.repository.BidChkaptRepository;
import gov.pcc.pwc.service.BidChkaptService;
import gov.pcc.pwc.service.criteria.BidChkaptCriteria;
import gov.pcc.pwc.service.dto.BidChkaptDTO;
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
 * REST controller for managing {@link gov.pcc.pwc.domain.BidChkapt}.
 */
@RestController
@RequestMapping("/api")
public class BidChkaptResource {

    private final Logger log = LoggerFactory.getLogger(BidChkaptResource.class);

    private static final String ENTITY_NAME = "bidChkapt";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BidChkaptService bidChkaptService;

    private final BidChkaptRepository bidChkaptRepository;

    public BidChkaptResource(BidChkaptService bidChkaptService, BidChkaptRepository bidChkaptRepository) {
        this.bidChkaptService = bidChkaptService;
        this.bidChkaptRepository = bidChkaptRepository;
    }

    /**
     * {@code POST  /bid-chkapts} : Create a new bidChkapt.
     *
     * @param bidChkaptDTO the bidChkaptDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bidChkaptDTO, or with status {@code 400 (Bad Request)} if the bidChkapt has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bid-chkapts")
    public ResponseEntity<BidChkaptDTO> createBidChkapt(@Valid @RequestBody BidChkaptDTO bidChkaptDTO) throws URISyntaxException {
        log.debug("REST request to save BidChkapt : {}", bidChkaptDTO);
        BidChkaptDTO result = bidChkaptService.save(bidChkaptDTO);
        return ResponseEntity
            .created(new URI("/api/bid-chkapts/" + result.getIdString()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getIdString()))
            .body(result);
    }

    /**
     * {@code PUT  /bid-chkapts/:id} : Updates an existing bidChkapt.
     *
     * @param bidChkaptDTO the bidChkaptDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidChkaptDTO,
     * or with status {@code 400 (Bad Request)} if the bidChkaptDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bidChkaptDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bid-chkapts")
    public ResponseEntity<BidChkaptDTO> updateBidChkapt(
        @Valid @RequestBody BidChkaptDTO bidChkaptDTO
    ) throws URISyntaxException {
        BidChkaptPrimaryKey id = new BidChkaptPrimaryKey(bidChkaptDTO.getWkut(), bidChkaptDTO.getPrjno(), bidChkaptDTO.getIdate());
        log.debug("REST request to update BidChkapt : {}, {}", id, bidChkaptDTO);

        if (!Objects.equals(id, bidChkaptDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidChkaptRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        BidChkaptDTO result = bidChkaptService.save(bidChkaptDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidChkaptDTO.getIdString()))
            .body(result);
    }

    /**
     * {@code PATCH  /bid-chkapts/:id} : Partial updates given fields of an existing bidChkapt, field will ignore if it is null
     *
     * @param bidChkaptDTO the bidChkaptDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidChkaptDTO,
     * or with status {@code 400 (Bad Request)} if the bidChkaptDTO is not valid,
     * or with status {@code 404 (Not Found)} if the bidChkaptDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the bidChkaptDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/bid-chkapts", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<BidChkaptDTO> partialUpdateBidChkapt(
        @NotNull @RequestBody BidChkaptDTO bidChkaptDTO
    ) throws URISyntaxException {
        BidChkaptPrimaryKey id = new BidChkaptPrimaryKey(bidChkaptDTO.getWkut(), bidChkaptDTO.getPrjno(), bidChkaptDTO.getIdate());
        log.debug("REST request to partial update BidChkapt partially : {}, {}", id, bidChkaptDTO);

        if (!Objects.equals(id, bidChkaptDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidChkaptRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BidChkaptDTO> result = bidChkaptService.partialUpdate(bidChkaptDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidChkaptDTO.getIdString())
        );
    }

    /**
     * {@code GET  /bid-chkapts} : get all the bidChkapts.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bidChkapts in body.
     */
    @GetMapping("/bid-chkapts")
    public List<BidChkaptDTO> getAllBidChkapts() {
        log.debug("REST request to get all BidChkapts");
        return bidChkaptService.findAll();
    }

    /**
     * {@code GET  /bid-chkapts/:id} : get the "id" bidChkapt.
     *
     * @param id the id of the bidChkaptDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bidChkaptDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/bid-chkapts/getOne")
    public ResponseEntity<BidChkaptDTO> getBidChkapt(@RequestBody BidChkaptPrimaryKey id) {
        log.debug("REST request to get BidChkapt : {}", id);
        Optional<BidChkaptDTO> bidChkaptDTO = bidChkaptService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bidChkaptDTO);
    }

    /**
     * {@code DELETE  /bid-chkapts/:id} : delete the "id" bidChkapt.
     *
     * @param id the id of the bidChkaptDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @PostMapping("/bid-chkapts/deleteOne")
    public ResponseEntity<Void> deleteBidChkapt(@RequestBody BidChkaptPrimaryKey id) {
        log.debug("REST request to delete BidChkapt : {}", id);
        bidChkaptService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     *
     * @param criteria
     * @return DTO
     */
    @PostMapping("/bid-chkapts/latest-one-by-criteria")
    public BidChkaptDTO findLatestOneByCriteira(@RequestBody BidChkaptCriteria criteria) {
        log.debug("REST request to find latest one BidChkapt by criteria : {}", criteria);
        return  bidChkaptService.findLastestOne(criteria).orElse(null);

    }
}
