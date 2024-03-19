package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.domain.BidPrjEquipPrimaryKey;
import gov.pcc.pwc.domain.BidPrjFalPrimaryKey;
import gov.pcc.pwc.repository.BidPrjFalRepository;
import gov.pcc.pwc.service.BidPrjFalService;
import gov.pcc.pwc.service.dto.BidPrjFalDTO;
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
 * REST controller for managing {@link gov.pcc.pwc.domain.BidPrjFal}.
 */
@RestController
@RequestMapping("/api")
public class BidPrjFalResource {

    private final Logger log = LoggerFactory.getLogger(BidPrjFalResource.class);

    private static final String ENTITY_NAME = "bidPrjFal";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BidPrjFalService bidPrjFalService;

    private final BidPrjFalRepository bidPrjFalRepository;

    public BidPrjFalResource(BidPrjFalService bidPrjFalService, BidPrjFalRepository bidPrjFalRepository) {
        this.bidPrjFalService = bidPrjFalService;
        this.bidPrjFalRepository = bidPrjFalRepository;
    }

    /**
     * {@code POST  /bid-prj-fals} : Create a new bidPrjFal.
     *
     * @param bidPrjFalDTO the bidPrjFalDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bidPrjFalDTO, or with status {@code 400 (Bad Request)} if the bidPrjFal has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bid-prj-fals")
    public ResponseEntity<BidPrjFalDTO> createBidPrjFal(@Valid @RequestBody BidPrjFalDTO bidPrjFalDTO) throws URISyntaxException {
        log.debug("REST request to save BidPrjFal : {}", bidPrjFalDTO);
        if (bidPrjFalDTO.getId() != null) {
            throw new BadRequestAlertException("A new bidPrjFal cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BidPrjFalDTO result = bidPrjFalService.save(bidPrjFalDTO);
        return ResponseEntity
            .created(new URI("/api/bid-prj-fals/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bid-prj-fals/:pk} : Updates an existing bidPrjFal.
     * @param bidPrjFalDTO the bidPrjFalDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidPrjFalDTO,
     * or with status {@code 400 (Bad Request)} if the bidPrjFalDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bidPrjFalDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bid-prj-fals/{wkut}/{prjno}/{year}/{month}")
    public ResponseEntity<BidPrjFalDTO> updateBidPrjFal(@Valid @RequestBody BidPrjFalDTO bidPrjFalDTO) throws URISyntaxException {
        BidPrjFalPrimaryKey bidPrjFalPrimaryKey = new BidPrjFalPrimaryKey(bidPrjFalDTO.getWkut(), bidPrjFalDTO.getWkut(), bidPrjFalDTO.getYear(), bidPrjFalDTO.getMonth(), bidPrjFalDTO.getMft());
        log.debug("REST request to update BidPrjFal : {}", bidPrjFalDTO);
        if (bidPrjFalDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(bidPrjFalPrimaryKey, bidPrjFalDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidPrjFalRepository.existsById(bidPrjFalPrimaryKey)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        BidPrjFalDTO result = bidPrjFalService.save(bidPrjFalDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidPrjFalDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /bid-prj-fals/:pk} : Partial updates given fields of an existing bidPrjFal, field will ignore if it is null
     * @param bidPrjFalDTO the bidPrjFalDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidPrjFalDTO,
     * or with status {@code 400 (Bad Request)} if the bidPrjFalDTO is not valid,
     * or with status {@code 404 (Not Found)} if the bidPrjFalDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the bidPrjFalDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/bid-prj-fals/{wkut}/{prjno}/{year}/{month}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<BidPrjFalDTO> partialUpdateBidPrjFal(@NotNull @RequestBody BidPrjFalDTO bidPrjFalDTO) throws URISyntaxException {
        BidPrjFalPrimaryKey bidPrjFalPrimaryKey = new BidPrjFalPrimaryKey(bidPrjFalDTO.getWkut(), bidPrjFalDTO.getWkut(), bidPrjFalDTO.getYear(), bidPrjFalDTO.getMonth(), bidPrjFalDTO.getMft());
        log.debug("REST request to partial update BidPrjFal partially : {}", bidPrjFalDTO);
        if (bidPrjFalDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(bidPrjFalPrimaryKey, bidPrjFalDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidPrjFalRepository.existsById(bidPrjFalPrimaryKey)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BidPrjFalDTO> result = bidPrjFalService.partialUpdate(bidPrjFalDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidPrjFalDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /bid-prj-fals} : get all the bidPrjFals.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bidPrjFals in body.
     */
    @GetMapping("/bid-prj-fals")
    public List<BidPrjFalDTO> getAllBidPrjFals() {
        log.debug("REST request to get all BidPrjFals");
        return bidPrjFalService.findAll();
    }

    /**
     * {@code GET  /bid-prj-fals/:id} : get the "id" bidPrjFal.
     *
     * @param bidPrjFalPrimaryKey the id of the bidPrjFalDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bidPrjFalDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/bid-prj-fals/getOne")
    public ResponseEntity<BidPrjFalDTO> getBidPrjFal(@RequestBody BidPrjFalPrimaryKey bidPrjFalPrimaryKey) {
        log.debug("REST request to get BidPrjFal : {}", bidPrjFalPrimaryKey);
        Optional<BidPrjFalDTO> bidPrjFalDTO = bidPrjFalService.findOne(bidPrjFalPrimaryKey);
        return ResponseUtil.wrapOrNotFound(bidPrjFalDTO);
    }

    /**
     * {@code DELETE  /bid-prj-fals/:id} : delete the "id" bidPrjFal.
     *
     * @param bidPrjFalPrimaryKey the id of the bidPrjFalDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bid-prj-fals")
    public ResponseEntity<Void> deleteBidPrjFall(@RequestBody BidPrjFalPrimaryKey bidPrjFalPrimaryKey) {
        log.debug("REST request to delete BidPrjFal : {}", bidPrjFalPrimaryKey);
        bidPrjFalService.delete(bidPrjFalPrimaryKey);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, bidPrjFalPrimaryKey.toString()))
            .build();
    }

    @PostMapping("/bid-prj-fals/monthlyPrjFal")
    public List<BidPrjFalDTO> getPrjMonthAllFal(@RequestBody BidPrjFalPrimaryKey bidPrjFalPrimaryKey) {
        log.debug("REST request to get PrjMonth All Fal : {}", bidPrjFalPrimaryKey);
        return bidPrjFalService.findPrjMonthAllFal(bidPrjFalPrimaryKey.getWkut(),bidPrjFalPrimaryKey.getPrjno(),bidPrjFalPrimaryKey.getYear(),bidPrjFalPrimaryKey.getMonth());
    }
}
