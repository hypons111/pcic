package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.domain.BidPrjRescisPrimaryKey;
import gov.pcc.pwc.repository.BidPrjRescisRepository;
import gov.pcc.pwc.service.BidPrjRescisService;
import gov.pcc.pwc.service.criteria.BidPrjRescisCriteria;
import gov.pcc.pwc.service.dto.BidPrjRescisDTO;
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
 * REST controller for managing {@link gov.pcc.pwc.domain.BidPrjRescis}.
 */
@RestController
@RequestMapping("/api")
public class BidPrjRescisResource {

    private final Logger log = LoggerFactory.getLogger(BidPrjRescisResource.class);

    private static final String ENTITY_NAME = "bidPrjRescis";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BidPrjRescisService bidPrjRescisService;

    private final BidPrjRescisRepository bidPrjRescisRepository;

    public BidPrjRescisResource(BidPrjRescisService bidPrjRescisService, BidPrjRescisRepository bidPrjRescisRepository) {
        this.bidPrjRescisService = bidPrjRescisService;
        this.bidPrjRescisRepository = bidPrjRescisRepository;
    }

    /**
     * {@code POST  /bid-prj-rescis} : Create a new bidPrjRescis.
     *
     * @param bidPrjRescisDTO the bidPrjRescisDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bidPrjRescisDTO, or with status {@code 400 (Bad Request)} if the bidPrjRescis has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bid-prj-rescis")
    public ResponseEntity<BidPrjRescisDTO> createBidPrjRescis(@Valid @RequestBody BidPrjRescisDTO bidPrjRescisDTO)
        throws URISyntaxException {
        log.debug("REST request to save BidPrjRescis : {}", bidPrjRescisDTO);
        if (bidPrjRescisDTO.getId() != null) {
            throw new BadRequestAlertException("A new bidPrjRescis cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BidPrjRescisDTO result = bidPrjRescisService.save(bidPrjRescisDTO);
        return ResponseEntity
            .created(new URI("/api/bid-prj-rescis/" + result.getIdString()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bid-prj-rescis/:id} : Updates an existing bidPrjRescis.
     *
     * @param bidPrjRescisDTO the bidPrjRescisDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidPrjRescisDTO,
     * or with status {@code 400 (Bad Request)} if the bidPrjRescisDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bidPrjRescisDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bid-prj-rescis")
    public ResponseEntity<BidPrjRescisDTO> updateBidPrjRescis(
        @Valid @RequestBody BidPrjRescisDTO bidPrjRescisDTO
    ) throws URISyntaxException {
        BidPrjRescisPrimaryKey id = new BidPrjRescisPrimaryKey(bidPrjRescisDTO.getWkut(), bidPrjRescisDTO.getPrjno(), bidPrjRescisDTO.getStopDate());
        log.debug("REST request to update BidPrjRescis : {}, {}", id, bidPrjRescisDTO);
        if (bidPrjRescisDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, bidPrjRescisDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidPrjRescisRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        BidPrjRescisDTO result = bidPrjRescisService.save(bidPrjRescisDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidPrjRescisDTO.getIdString()))
            .body(result);
    }

    /**
     * {@code PATCH  /bid-prj-rescis/:id} : Partial updates given fields of an existing bidPrjRescis, field will ignore if it is null
     *
     * @param bidPrjRescisDTO the bidPrjRescisDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidPrjRescisDTO,
     * or with status {@code 400 (Bad Request)} if the bidPrjRescisDTO is not valid,
     * or with status {@code 404 (Not Found)} if the bidPrjRescisDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the bidPrjRescisDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/bid-prj-rescis/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<BidPrjRescisDTO> partialUpdateBidPrjRescis(
        @NotNull @RequestBody BidPrjRescisDTO bidPrjRescisDTO
    ) throws URISyntaxException {
        BidPrjRescisPrimaryKey id = new BidPrjRescisPrimaryKey(bidPrjRescisDTO.getWkut(), bidPrjRescisDTO.getPrjno(), bidPrjRescisDTO.getStopDate());
        log.debug("REST request to partial update BidPrjRescis partially : {}, {}", id, bidPrjRescisDTO);
        if (bidPrjRescisDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, bidPrjRescisDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidPrjRescisRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BidPrjRescisDTO> result = bidPrjRescisService.partialUpdate(bidPrjRescisDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidPrjRescisDTO.getIdString())
        );
    }

    /**
     * {@code GET  /bid-prj-rescis} : get all the bidPrjRescis.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bidPrjRescis in body.
     */
    @GetMapping("/service/bid-prj-rescis")
    public List<BidPrjRescisDTO> getAllBidPrjRescis() {
        log.debug("REST request to get all BidPrjRescis");
        return bidPrjRescisService.findAll();
    }

    /**
     * {@code GET  /bid-prj-rescis/:id} : get the "id" bidPrjRescis.
     *
     * @param id the id of the bidPrjRescisDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bidPrjRescisDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/bid-prj-rescis/getOne")
    public ResponseEntity<BidPrjRescisDTO> getBidPrjRescis(@RequestBody BidPrjRescisPrimaryKey id) {
        log.debug("REST request to get BidPrjRescis : {}", id);
        Optional<BidPrjRescisDTO> bidPrjRescisDTO = bidPrjRescisService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bidPrjRescisDTO);
    }

    /**
     * {@code DELETE  /bid-prj-rescis/:id} : delete the "id" bidPrjRescis.
     *
     * @param id the id of the bidPrjRescisDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @PostMapping("/bid-prj-rescis/deleteOne")
    public ResponseEntity<Void> deleteBidPrjRescis(@RequestBody BidPrjRescisPrimaryKey id) {
        log.debug("REST request to delete BidPrjRescis : {}", id);
        bidPrjRescisService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.getPrjno()))
            .build();
    }

    /**
     *
     * @param criteria prjno 和 wkut
     * @return DTO or Null
     */
    @PostMapping("/bid-prj-rescis/find-latest")
    public BidPrjRescisDTO getLatestRescis(@RequestBody BidPrjRescisCriteria criteria){
        return bidPrjRescisService.getLatestBidPrjRescis(criteria).orElse(null);
    }
}
