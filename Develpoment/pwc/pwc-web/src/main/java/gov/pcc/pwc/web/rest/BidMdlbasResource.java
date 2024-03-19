package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.domain.BidMdlbasPrimaryKey;
import gov.pcc.pwc.repository.BidMdlbasRepository;
import gov.pcc.pwc.service.BidMdlbasService;
import gov.pcc.pwc.service.criteria.BidMdlbasCriteria;
import gov.pcc.pwc.service.dto.BidMdlbasDTO;
import gov.pcc.pwc.common.errors.BadRequestAlertException;
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
 * REST controller for managing {@link gov.pcc.pwc.domain.BidMdlbas}.
 */
@RestController
@RequestMapping("/api")
public class BidMdlbasResource {

    private final Logger log = LoggerFactory.getLogger(BidMdlbasResource.class);

    private static final String ENTITY_NAME = "bidMdlbas";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BidMdlbasService bidMdlbasService;

    private final BidMdlbasRepository bidMdlbasRepository;

    public BidMdlbasResource(BidMdlbasService bidMdlbasService, BidMdlbasRepository bidMdlbasRepository) {
        this.bidMdlbasService = bidMdlbasService;
        this.bidMdlbasRepository = bidMdlbasRepository;
    }

    /**
     * {@code POST  /bid-mdlbas} : Create a new bidMdlbas.
     *
     * @param bidMdlbasDTO the bidMdlbasDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bidMdlbasDTO, or with status {@code 400 (Bad Request)} if the bidMdlbas has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bid-mdlbas")
    public ResponseEntity<BidMdlbasDTO> createBidMdlbas(@Valid @RequestBody BidMdlbasDTO bidMdlbasDTO) throws URISyntaxException {
        log.debug("REST request to save BidMdlbas : {}", bidMdlbasDTO);
        if (bidMdlbasDTO.getId() != null) {
            throw new BadRequestAlertException("A new bidMdlbas cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BidMdlbasDTO result = bidMdlbasService.save(bidMdlbasDTO);
        return ResponseEntity
            .created(new URI("/api/bid-mdlbas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bid-mdlbas/:id} : Updates an existing bidMdlbas.
     *
     * @param bidMdlbasDTO the bidMdlbasDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidMdlbasDTO,
     * or with status {@code 400 (Bad Request)} if the bidMdlbasDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bidMdlbasDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bid-mdlbas/{id}")
    public ResponseEntity<BidMdlbasDTO> updateBidMdlbas(
        @Valid @RequestBody BidMdlbasDTO bidMdlbasDTO
    ) throws URISyntaxException {
        BidMdlbasPrimaryKey bidMdlbasPrimaryKey = new BidMdlbasPrimaryKey(bidMdlbasDTO.getWkut(),bidMdlbasDTO.getDisNo());
        log.debug("REST request to update BidMdlbas : {}", bidMdlbasDTO);
        if (bidMdlbasDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(bidMdlbasPrimaryKey, bidMdlbasDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidMdlbasRepository.existsById(bidMdlbasPrimaryKey)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        BidMdlbasDTO result = bidMdlbasService.save(bidMdlbasDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidMdlbasDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /bid-mdlbas/:id} : Partial updates given fields of an existing bidMdlbas, field will ignore if it is null
     *
     * @param bidMdlbasDTO the bidMdlbasDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidMdlbasDTO,
     * or with status {@code 400 (Bad Request)} if the bidMdlbasDTO is not valid,
     * or with status {@code 404 (Not Found)} if the bidMdlbasDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the bidMdlbasDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/bid-mdlbas/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<BidMdlbasDTO> partialUpdateBidMdlbas(
        @NotNull @RequestBody BidMdlbasDTO bidMdlbasDTO
    ) throws URISyntaxException {
        BidMdlbasPrimaryKey bidMdlbasPrimaryKey = new BidMdlbasPrimaryKey(bidMdlbasDTO.getWkut(),bidMdlbasDTO.getDisNo());
        log.debug("REST request to partial update BidMdlbas partially : {}",bidMdlbasDTO);
        if (bidMdlbasDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(bidMdlbasPrimaryKey, bidMdlbasDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidMdlbasRepository.existsById(bidMdlbasPrimaryKey)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BidMdlbasDTO> result = bidMdlbasService.partialUpdate(bidMdlbasDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidMdlbasDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /bid-mdlbas} : get all the bidMdlbas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bidMdlbas in body.
     */
    @GetMapping("/bid-mdlbas")
    public List<BidMdlbasDTO> getAllBidMdlbas() {
        log.debug("REST request to get all BidMdlbas");
        return bidMdlbasService.findAll();
    }

    /**
     * {@code GET  /bid-mdlbas/:id} : get the "id" bidMdlbas.
     *
     * @param id the id of the bidMdlbasDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bidMdlbasDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bid-mdlbas/{id}")
    public ResponseEntity<BidMdlbasDTO> getBidMdlbas(@PathVariable BidMdlbasPrimaryKey id) {
        log.debug("REST request to get BidMdlbas : {}", id);
        Optional<BidMdlbasDTO> bidMdlbasDTO = bidMdlbasService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bidMdlbasDTO);
    }

    /**
     * {@code DELETE  /bid-mdlbas/:id} : delete the "id" bidMdlbas.
     *
     * @param id the id of the bidMdlbasDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bid-mdlbas/{id}")
    public ResponseEntity<Void> deleteBidMdlbas(@PathVariable BidMdlbasPrimaryKey id) {
        log.debug("REST request to delete BidMdlbas : {}", id);
        bidMdlbasService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * get budg by criteria
     * */
    @PostMapping("/bid-mdlbas/criteria")
    public List<BidMdlbasDTO> getDisNoNByCriteria(@Valid@RequestBody BidMdlbasCriteria criteria){
        return bidMdlbasService.findDisNoByCriteria(criteria);
    }
}
