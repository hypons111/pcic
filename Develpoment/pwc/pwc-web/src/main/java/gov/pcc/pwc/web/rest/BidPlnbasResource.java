package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.domain.BidPlnbasPrimaryKey;
import gov.pcc.pwc.repository.BidPlnbasRepository;
import gov.pcc.pwc.service.BidPlnbasService;
import gov.pcc.pwc.service.criteria.BidPlnbasCriteria;
import gov.pcc.pwc.service.dto.BidPlnbasDTO;
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
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link gov.pcc.pwc.domain.BidPlnbas}.
 */
@RestController
@RequestMapping("/api")
public class BidPlnbasResource {

    private final Logger log = LoggerFactory.getLogger(BidPlnbasResource.class);

    private static final String ENTITY_NAME = "bidPlnbas";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BidPlnbasService bidPlnbasService;

    private final BidPlnbasRepository bidPlnbasRepository;

    public BidPlnbasResource(BidPlnbasService bidPlnbasService, BidPlnbasRepository bidPlnbasRepository) {
        this.bidPlnbasService = bidPlnbasService;
        this.bidPlnbasRepository = bidPlnbasRepository;
    }

    /**
     * {@code POST  /bid-plnbas} : Create a new bidPlnbas.
     *
     * @param bidPlnbasDTO the bidPlnbasDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bidPlnbasDTO, or with status {@code 400 (Bad Request)} if the bidPlnbas has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bid-plnbas")
    public ResponseEntity<BidPlnbasDTO> createBidPlnbas(@Valid @RequestBody BidPlnbasDTO bidPlnbasDTO) throws URISyntaxException {
        log.debug("REST request to save BidPlnbas : {}", bidPlnbasDTO);
        if (bidPlnbasDTO.getId() != null) {
            throw new BadRequestAlertException("A new bidPlnbas cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BidPlnbasDTO result = bidPlnbasService.save(bidPlnbasDTO);
        return ResponseEntity
            .created(new URI("/api/bid-plnbas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bid-plnbas/:id} : Updates an existing bidPlnbas.
     *
     * @param id the id of the bidPlnbasDTO to save.
     * @param bidPlnbasDTO the bidPlnbasDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidPlnbasDTO,
     * or with status {@code 400 (Bad Request)} if the bidPlnbasDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bidPlnbasDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bid-plnbas/{id}")
    public ResponseEntity<BidPlnbasDTO> updateBidPlnbas(
        @PathVariable(value = "id", required = false) final BidPlnbasPrimaryKey id,
        @Valid @RequestBody BidPlnbasDTO bidPlnbasDTO
    ) throws URISyntaxException {
        log.debug("REST request to update BidPlnbas : {}, {}", id, bidPlnbasDTO);
        if (bidPlnbasDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, bidPlnbasDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidPlnbasRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        BidPlnbasDTO result = bidPlnbasService.save(bidPlnbasDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidPlnbasDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /bid-plnbas/:id} : Partial updates given fields of an existing bidPlnbas, field will ignore if it is null
     *
     * @param id the id of the bidPlnbasDTO to save.
     * @param bidPlnbasDTO the bidPlnbasDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidPlnbasDTO,
     * or with status {@code 400 (Bad Request)} if the bidPlnbasDTO is not valid,
     * or with status {@code 404 (Not Found)} if the bidPlnbasDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the bidPlnbasDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/bid-plnbas/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<BidPlnbasDTO> partialUpdateBidPlnbas(
        @PathVariable(value = "id", required = false) final BidPlnbasPrimaryKey id,
        @NotNull @RequestBody BidPlnbasDTO bidPlnbasDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update BidPlnbas partially : {}, {}", id, bidPlnbasDTO);
        if (bidPlnbasDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, bidPlnbasDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidPlnbasRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BidPlnbasDTO> result = bidPlnbasService.partialUpdate(bidPlnbasDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidPlnbasDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /bid-plnbas} : get all the bidPlnbas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bidPlnbas in body.
     */
    @GetMapping("/bid-plnbas")
    public List<BidPlnbasDTO> getAllBidPlnbas() {
        log.debug("REST request to get all BidPlnbas");
        return bidPlnbasService.findAll();
    }

    /**
     * {@code GET  /bid-plnbas/:id} : get the "id" bidPlnbas.
     *
     * @param id the id of the bidPlnbasDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bidPlnbasDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bid-plnbas/{id}")
    public ResponseEntity<BidPlnbasDTO> getBidPlnbas(@PathVariable BidPlnbasPrimaryKey id) {
        log.debug("REST request to get BidPlnbas : {}", id);
        Optional<BidPlnbasDTO> bidPlnbasDTO = bidPlnbasService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bidPlnbasDTO);
    }

    /**
     * {@code DELETE  /bid-plnbas/:id} : delete the "id" bidPlnbas.
     *
     * @param id the id of the bidPlnbasDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bid-plnbas/{id}")
    public ResponseEntity<Void> deleteBidPlnbas(@PathVariable BidPlnbasPrimaryKey id) {
        log.debug("REST request to delete BidPlnbas : {}", id);
        bidPlnbasService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    @PostMapping("/bid-plnbas/getPages")
    public Page<BidPlnbasDTO> getPages(@RequestBody BidPlnbasCriteria criteria) {
        log.debug("REST request to get BidPlnbas : {}", criteria);
        return bidPlnbasService.findAllByCriteria(criteria);
    }
}
