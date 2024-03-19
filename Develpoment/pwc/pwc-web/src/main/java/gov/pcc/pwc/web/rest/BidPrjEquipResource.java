package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.domain.BidPrjEquipPrimaryKey;
import gov.pcc.pwc.repository.BidPrjEquipRepository;
import gov.pcc.pwc.service.BidPrjEquipService;
import gov.pcc.pwc.service.dto.BidPrjEquipDTO;
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
 * REST controller for managing {@link gov.pcc.pwc.domain.BidPrjEquip}.
 */
@RestController
@RequestMapping("/api")
public class BidPrjEquipResource {

    private final Logger log = LoggerFactory.getLogger(BidPrjEquipResource.class);

    private static final String ENTITY_NAME = "bidPrjEquip";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BidPrjEquipService bidPrjEquipService;

    private final BidPrjEquipRepository bidPrjEquipRepository;

    public BidPrjEquipResource(BidPrjEquipService bidPrjEquipService, BidPrjEquipRepository bidPrjEquipRepository) {
        this.bidPrjEquipService = bidPrjEquipService;
        this.bidPrjEquipRepository = bidPrjEquipRepository;
    }

    /**
     * {@code POST  /bid-prj-equips} : Create a new bidPrjEquip.
     *
     * @param bidPrjEquipDTO the bidPrjEquipDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bidPrjEquipDTO, or with status {@code 400 (Bad Request)} if the bidPrjEquip has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bid-prj-equips")
    public ResponseEntity<BidPrjEquipDTO> createBidPrjEquip(@Valid @RequestBody BidPrjEquipDTO bidPrjEquipDTO) throws URISyntaxException {
        log.debug("REST request to save BidPrjEquip : {}", bidPrjEquipDTO);
        if (bidPrjEquipDTO.getId() != null) {
            throw new BadRequestAlertException("A new bidPrjEquip cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BidPrjEquipDTO result = bidPrjEquipService.save(bidPrjEquipDTO);
        return ResponseEntity
            .created(new URI("/api/bid-prj-equips/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bid-prj-equips/:id} : Updates an existing bidPrjEquip.
     *
     * @param bidPrjEquipDTO the bidPrjEquipDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidPrjEquipDTO,
     * or with status {@code 400 (Bad Request)} if the bidPrjEquipDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bidPrjEquipDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bid-prj-equips/")
    public ResponseEntity<BidPrjEquipDTO> updateBidPrjEquip(@Valid @RequestBody BidPrjEquipDTO bidPrjEquipDTO
    ) throws URISyntaxException {
        BidPrjEquipPrimaryKey bidPrjEquipPrimaryKey = new BidPrjEquipPrimaryKey(bidPrjEquipDTO.getWkut(), bidPrjEquipDTO.getWkut(), bidPrjEquipDTO.getYear(), bidPrjEquipDTO.getMonth(), bidPrjEquipDTO.geteCode());
        log.debug("REST request to update BidPrjEquip : {}", bidPrjEquipDTO);
        if (bidPrjEquipDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(bidPrjEquipPrimaryKey, bidPrjEquipDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidPrjEquipRepository.existsById(bidPrjEquipPrimaryKey)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        BidPrjEquipDTO result = bidPrjEquipService.save(bidPrjEquipDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidPrjEquipDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /bid-prj-equips/:id} : Partial updates given fields of an existing bidPrjEquip, field will ignore if it is null
     * @param bidPrjEquipDTO the bidPrjEquipDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidPrjEquipDTO,
     * or with status {@code 400 (Bad Request)} if the bidPrjEquipDTO is not valid,
     * or with status {@code 404 (Not Found)} if the bidPrjEquipDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the bidPrjEquipDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/bid-prj-equips", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<BidPrjEquipDTO> partialUpdateBidPrjEquip( @NotNull @RequestBody BidPrjEquipDTO bidPrjEquipDTO) throws URISyntaxException {
        BidPrjEquipPrimaryKey bidPrjEquipPrimaryKey = new BidPrjEquipPrimaryKey(bidPrjEquipDTO.getWkut(), bidPrjEquipDTO.getWkut(), bidPrjEquipDTO.getYear(), bidPrjEquipDTO.getMonth(), bidPrjEquipDTO.geteCode());
        log.debug("REST request to partial update BidPrjEquip partially : {},", bidPrjEquipDTO);
        if (bidPrjEquipDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(bidPrjEquipPrimaryKey, bidPrjEquipDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidPrjEquipRepository.existsById(bidPrjEquipPrimaryKey)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BidPrjEquipDTO> result = bidPrjEquipService.partialUpdate(bidPrjEquipDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidPrjEquipDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /bid-prj-equips} : get all the bidPrjEquips.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bidPrjEquips in body.
     */
    @GetMapping("/bid-prj-equips")
    public List<BidPrjEquipDTO> getAllBidPrjEquips() {
        log.debug("REST request to get all BidPrjEquips");
        return bidPrjEquipService.findAll();
    }

    /**
     * {@code GET  /bid-prj-equips/getOne} : get the "id" bidPrjEquip.
     *
     * @param bidPrjEquipPrimaryKey  the id of the bidPrjEquipDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bidPrjEquipDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/bid-prj-equips/getOne")
    public ResponseEntity<BidPrjEquipDTO> getBidPrjEquip(@RequestBody BidPrjEquipPrimaryKey bidPrjEquipPrimaryKey) {
        log.debug("REST request to get BidPrjEquip : {}", bidPrjEquipPrimaryKey);
        Optional<BidPrjEquipDTO> bidPrjEquipDTO = bidPrjEquipService.findOne(bidPrjEquipPrimaryKey);
        return ResponseUtil.wrapOrNotFound(bidPrjEquipDTO);
    }

    /**
     * {@code DELETE  /bid-prj-equips/:id} : delete the "id" bidPrjEquip.
     *
     * @param bidPrjEquipPrimaryKey  the id of the bidPrjEquipDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bid-prj-equips")
    public ResponseEntity<Void> deleteBidPrjEquip(@RequestBody BidPrjEquipPrimaryKey bidPrjEquipPrimaryKey) {
        log.debug("REST request to delete BidPrjEquip : {}", bidPrjEquipPrimaryKey);
        bidPrjEquipService.delete(bidPrjEquipPrimaryKey);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, bidPrjEquipPrimaryKey.toString()))
            .build();
    }

    @PostMapping("/bid-prj-equips/monthlyEquip")
    public List<BidPrjEquipDTO> getMonthlyEquip(@RequestBody BidPrjEquipPrimaryKey bidPrjEquipPrimaryKey) {
        log.debug("REST request to get BidPrjEquip : {}", bidPrjEquipPrimaryKey);
//        bidPrjEquipService.findOne(bidPrjEquipPrimaryKey);
        return bidPrjEquipService.findPrjMonthAllEquip(bidPrjEquipPrimaryKey.getWkut(),bidPrjEquipPrimaryKey.getPrjno(),bidPrjEquipPrimaryKey.getYear(),bidPrjEquipPrimaryKey.getMonth());
    }
}
