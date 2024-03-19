package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.domain.BidProjectPrimaryKey;
import gov.pcc.pwc.repository.BidPrjDeleteRepository;
import gov.pcc.pwc.service.BidPrjDeleteService;
import gov.pcc.pwc.service.dto.BidPrjDeleteDTO;

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
 * REST controller for managing {@link gov.pcc.pwc.domain.BidPrjDelete}.
 */
@RestController
@RequestMapping("/api")
public class BidPrjDeleteResource {

    private final Logger log = LoggerFactory.getLogger(BidPrjDeleteResource.class);

    private static final String ENTITY_NAME = "bidPrjDelete";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BidPrjDeleteService bidPrjDeleteService;

    private final BidPrjDeleteRepository bidPrjDeleteRepository;

    public BidPrjDeleteResource(BidPrjDeleteService bidPrjDeleteService, BidPrjDeleteRepository bidPrjDeleteRepository) {
        this.bidPrjDeleteService = bidPrjDeleteService;
        this.bidPrjDeleteRepository = bidPrjDeleteRepository;
    }

    /**
     * {@code POST  /bid-prj-deletes} : Create a new bidPrjDelete.
     *
     * @param bidPrjDeleteDTO the bidPrjDeleteDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bidPrjDeleteDTO, or with status {@code 400 (Bad Request)} if the bidPrjDelete has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bid-prj-deletes")
    public ResponseEntity<BidPrjDeleteDTO> createBidPrjDelete(@Valid @RequestBody BidPrjDeleteDTO bidPrjDeleteDTO)
        throws URISyntaxException {
        log.debug("REST request to save BidPrjDelete : {}", bidPrjDeleteDTO);

        BidPrjDeleteDTO result = bidPrjDeleteService.save(bidPrjDeleteDTO);

        return ResponseEntity
            .created(new URI("/api/bid-prj-deletes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getIdString()))
            .body(result);
    }

    /**
     * {@code PUT  /bid-prj-deletes} : Updates an existing bidPrjDelete.
     *
     * @param bidPrjDeleteDTO the bidPrjDeleteDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidPrjDeleteDTO,
     * or with status {@code 400 (Bad Request)} if the bidPrjDeleteDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bidPrjDeleteDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bid-prj-deletes")
    public ResponseEntity<BidPrjDeleteDTO> updateBidPrjDelete(
        @Valid @RequestBody BidPrjDeleteDTO bidPrjDeleteDTO
    ) throws URISyntaxException {
        log.debug("REST request to update BidPrjDelete : {}", bidPrjDeleteDTO);

        BidProjectPrimaryKey id = new BidProjectPrimaryKey(bidPrjDeleteDTO.getWkut(), bidPrjDeleteDTO.getPrjno());

        if (bidPrjDeleteDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, bidPrjDeleteDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidPrjDeleteRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        BidPrjDeleteDTO result = bidPrjDeleteService.save(bidPrjDeleteDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidPrjDeleteDTO.getIdString()))
            .body(result);
    }

    /**
     * {@code PATCH  /bid-prj-deletes/:id} : Partial updates given fields of an existing bidPrjDelete, field will ignore if it is null
     *
     * @param bidPrjDeleteDTO the bidPrjDeleteDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidPrjDeleteDTO,
     * or with status {@code 400 (Bad Request)} if the bidPrjDeleteDTO is not valid,
     * or with status {@code 404 (Not Found)} if the bidPrjDeleteDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the bidPrjDeleteDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/bid-prj-deletes", consumes = {"application/json", "application/merge-patch+json"})
    public ResponseEntity<BidPrjDeleteDTO> partialUpdateBidPrjDelete(

        @NotNull @RequestBody BidPrjDeleteDTO bidPrjDeleteDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update BidPrjDelete partially : {}", bidPrjDeleteDTO);

        BidProjectPrimaryKey id = new BidProjectPrimaryKey(bidPrjDeleteDTO.getWkut(), bidPrjDeleteDTO.getPrjno());

        if (bidPrjDeleteDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, bidPrjDeleteDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidPrjDeleteRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BidPrjDeleteDTO> result = bidPrjDeleteService.partialUpdate(bidPrjDeleteDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidPrjDeleteDTO.getIdString())
        );
    }

    /**
     * {@code GET  /bid-prj-deletes} : get all the bidPrjDeletes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bidPrjDeletes in body.
     */
    @GetMapping("/bid-prj-deletes")
    public List<BidPrjDeleteDTO> getAllBidPrjDeletes() {
        log.debug("REST request to get all BidPrjDeletes");
        return bidPrjDeleteService.findAll();
    }

    /**
     * {@code Post  /bid-prj-deletes/getOne} : get the "id" bidPrjDelete.
     *
     * @param id the id of the bidPrjDeleteDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bidPrjDeleteDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/bid-prj-deletes/getOne")
    public ResponseEntity<BidPrjDeleteDTO> getBidPrjDelete(BidProjectPrimaryKey id) {
        log.debug("REST request to get BidPrjDelete : {}", id);
        Optional<BidPrjDeleteDTO> bidPrjDeleteDTO = bidPrjDeleteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bidPrjDeleteDTO);
    }

    /**
     * {@code DELETE  /bid-prj-deletes/:id} : delete the "id" bidPrjDelete.
     *
     * @param id the id of the bidPrjDeleteDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @PostMapping("/bid-prj-deletes/deleteOne")
    public ResponseEntity<Void> deleteBidPrjDelete(BidProjectPrimaryKey id) {
        log.debug("REST request to delete BidPrjDelete : {}", id);
        bidPrjDeleteService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
