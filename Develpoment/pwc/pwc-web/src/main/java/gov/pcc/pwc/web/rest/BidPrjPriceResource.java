package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.domain.BidPrjPricePrimaryKey;
import gov.pcc.pwc.repository.BidPrjPriceRepository;
import gov.pcc.pwc.service.BidPrjPriceService;
import gov.pcc.pwc.service.criteria.BidGovCheckCriteria;
import gov.pcc.pwc.service.criteria.BidPrjPriceCriteria;
import gov.pcc.pwc.service.dto.BidGovCheckDTO;
import gov.pcc.pwc.service.dto.BidPrjPriceDTO;
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
 * REST controller for managing {@link gov.pcc.pwc.domain.BidPrjPrice}.
 */
@RestController
@RequestMapping("/api")
    public class BidPrjPriceResource {

    private final Logger log = LoggerFactory.getLogger(BidPrjPriceResource.class);

    private static final String ENTITY_NAME = "bidPrjPrice";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BidPrjPriceService bidPrjPriceService;

    private final BidPrjPriceRepository bidPrjPriceRepository;

    public BidPrjPriceResource(BidPrjPriceService bidPrjPriceService, BidPrjPriceRepository bidPrjPriceRepository) {
        this.bidPrjPriceService = bidPrjPriceService;
        this.bidPrjPriceRepository = bidPrjPriceRepository;
    }

    /**
     * {@code POST  /bid-prj-prices} : Create a new bidPrjPrice.
     *
     * @param bidPrjPriceDTO the bidPrjPriceDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bidPrjPriceDTO, or with status {@code 400 (Bad Request)} if the bidPrjPrice has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bid-prj-prices")
    public ResponseEntity<BidPrjPriceDTO> createBidPrjPrice(@Valid @RequestBody BidPrjPriceDTO bidPrjPriceDTO) throws URISyntaxException {
        log.debug("REST request to save BidPrjPrice : {}", bidPrjPriceDTO);

        BidPrjPriceDTO result = bidPrjPriceService.save(bidPrjPriceDTO);
        return ResponseEntity
            .created(new URI("/api/bid-prj-prices/" + result.getIdString()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getIdString()))
            .body(result);
    }

    /**
     * {@code PUT  /bid-prj-prices/:id} : Updates an existing bidPrjPrice.
     *
     * @param bidPrjPriceDTO the bidPrjPriceDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidPrjPriceDTO,
     * or with status {@code 400 (Bad Request)} if the bidPrjPriceDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bidPrjPriceDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bid-prj-prices")
    public ResponseEntity<BidPrjPriceDTO> updateBidPrjPrice(
        @Valid @RequestBody BidPrjPriceDTO bidPrjPriceDTO
    ) throws URISyntaxException {
        BidPrjPricePrimaryKey id = new BidPrjPricePrimaryKey(bidPrjPriceDTO.getWkut(), bidPrjPriceDTO.getPrjno(), bidPrjPriceDTO.getYear(), bidPrjPriceDTO.getpUnit(), bidPrjPriceDTO.getGrade());
        log.debug("REST request to update BidPrjPrice : {}, {}", id, bidPrjPriceDTO);

        if (!Objects.equals(id, bidPrjPriceDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidPrjPriceRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        BidPrjPriceDTO result = bidPrjPriceService.save(bidPrjPriceDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidPrjPriceDTO.getIdString()))
            .body(result);
    }

    /**
     * {@code PATCH  /bid-prj-prices/:id} : Partial updates given fields of an existing bidPrjPrice, field will ignore if it is null
     *
     * @param bidPrjPriceDTO the bidPrjPriceDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidPrjPriceDTO,
     * or with status {@code 400 (Bad Request)} if the bidPrjPriceDTO is not valid,
     * or with status {@code 404 (Not Found)} if the bidPrjPriceDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the bidPrjPriceDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/bid-prj-prices/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<BidPrjPriceDTO> partialUpdateBidPrjPrice(
        @NotNull @RequestBody BidPrjPriceDTO bidPrjPriceDTO
    ) throws URISyntaxException {
        BidPrjPricePrimaryKey id = new BidPrjPricePrimaryKey(bidPrjPriceDTO.getWkut(), bidPrjPriceDTO.getPrjno(), bidPrjPriceDTO.getYear(), bidPrjPriceDTO.getpUnit(), bidPrjPriceDTO.getGrade());

        log.debug("REST request to partial update BidPrjPrice partially : {}, {}", id, bidPrjPriceDTO);

        if (!Objects.equals(id, bidPrjPriceDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidPrjPriceRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BidPrjPriceDTO> result = bidPrjPriceService.partialUpdate(bidPrjPriceDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidPrjPriceDTO.getIdString())
        );
    }

    /**
     * {@code GET  /bid-prj-prices} : get all the bidPrjPrices.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bidPrjPrices in body.
     */
    @GetMapping("/bid-prj-prices")
    public List<BidPrjPriceDTO> getAllBidPrjPrices() {
        log.debug("REST request to get all BidPrjPrices");
        return bidPrjPriceService.findAll();
    }

    /**
     * {@code GET  /bid-prj-prices/:id} : get the "id" bidPrjPrice.
     *
     * @param id the id of the bidPrjPriceDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bidPrjPriceDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/bid-prj-prices/getOne")
    public ResponseEntity<BidPrjPriceDTO> getBidPrjPrice(@RequestBody BidPrjPricePrimaryKey id) {
        log.debug("REST request to get BidPrjPrice : {}", id);
        Optional<BidPrjPriceDTO> bidPrjPriceDTO = bidPrjPriceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bidPrjPriceDTO);
    }

    /**
     * {@code DELETE  /bid-prj-prices/:id} : delete the "id" bidPrjPrice.
     *
     * @param id the id of the bidPrjPriceDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @PostMapping("/bid-prj-prices/deleteOne")
    public ResponseEntity<Void> deleteBidPrjPrice(@PathVariable BidPrjPricePrimaryKey id) {
        log.debug("REST request to delete BidPrjPrice : {}", id);
        bidPrjPriceService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * @param criteria wkut ,prjno
     * @return List<BidPrjPriceDTO>
     */
    @PostMapping("/bid-prj-prices/getListByCriteria")
    public List<BidPrjPriceDTO> getPriceListByCriteria(@RequestBody BidPrjPriceCriteria criteria) {
        log.debug("REST request to BidGovCheckDTO list by criteria : {}", criteria);
        return bidPrjPriceService.getBidPrjPriceDTOListByCriteria(criteria);
    }

    /**
     * 公共工程金質獎
     * @param criteria wkut ,prjno
     * @return List<BidPrjPriceDTO>
     */
    @PostMapping("/bid-prj-prices/jin-zhi/getListByCriteria")
    public List<BidPrjPriceDTO> getJZPriceListByCriteria(@RequestBody BidPrjPriceCriteria criteria) {
        log.debug("REST request to BidGovCheckDTO list by criteria : {}", criteria);
        return bidPrjPriceService.getCertainBidPrjPriceDTOListByCriteria(criteria,"公共工程金質獎");
    }

    /**
     * 金安獎
     * @param criteria wkut ,prjno
     * @return List<BidPrjPriceDTO>
     */
    @PostMapping("/bid-prj-prices/jin-an/getListByCriteria")
    public List<BidPrjPriceDTO> getJNPriceListByCriteria(@RequestBody BidPrjPriceCriteria criteria) {
        log.debug("REST request to BidGovCheckDTO list by criteria : {}", criteria);
        return bidPrjPriceService.getCertainBidPrjPriceDTOListByCriteria(criteria,"金安獎");
    }
}
