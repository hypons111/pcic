package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.domain.BidPrjGdfkPrimaryKey;
import gov.pcc.pwc.domain.BidPrjWksorcPrimaryKey;
import gov.pcc.pwc.repository.BidPrjGdfkRepository;
import gov.pcc.pwc.service.BidPrjGdfkService;
import gov.pcc.pwc.service.criteria.BidPrjGdfkCriteria;
import gov.pcc.pwc.service.criteria.BidPrjWksorcCriteria;
import gov.pcc.pwc.service.dto.BidPrjGdfkDTO;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import gov.pcc.pwc.service.dto.BidPrjWksorcDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link gov.pcc.pwc.domain.BidPrjGdfk}.
 */
@RestController
@RequestMapping("/api")
public class BidPrjGdfkResource {

    private final Logger log = LoggerFactory.getLogger(BidPrjGdfkResource.class);

    private static final String ENTITY_NAME = "bidPrjGdfk";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BidPrjGdfkService bidPrjGdfkService;

    private final BidPrjGdfkRepository bidPrjGdfkRepository;

    public BidPrjGdfkResource(BidPrjGdfkService bidPrjGdfkService, BidPrjGdfkRepository bidPrjGdfkRepository) {
        this.bidPrjGdfkService = bidPrjGdfkService;
        this.bidPrjGdfkRepository = bidPrjGdfkRepository;
    }

    /**
     * {@code POST  /bid-prj-gdfks} : Create a new bidPrjGdfk.
     *
     * @param bidPrjGdfkDTO the bidPrjGdfkDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bidPrjGdfkDTO, or with status {@code 400 (Bad Request)} if the bidPrjGdfk has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bid-prj-gdfks")
    public ResponseEntity<BidPrjGdfkDTO> createBidPrjGdfk(@Valid @RequestBody BidPrjGdfkDTO bidPrjGdfkDTO) throws URISyntaxException {
        log.debug("REST request to save BidPrjGdfk : {}", bidPrjGdfkDTO);
//        if (bidPrjGdfkDTO.getId() != null) {
//            throw new BadRequestAlertException("A new bidPrjGdfk cannot already have an ID", ENTITY_NAME, "idexists");
//        }
        BidPrjGdfkDTO result = bidPrjGdfkService.save(bidPrjGdfkDTO);
        return ResponseEntity
//            .created(new URI("/api/bid-prj-gdfks/" + result.getId()))
//            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
//            .body(result);
            .created(new URI("/api/bid-prj-safes/" +result.getId().getWkut()+"/"+ result.getId().getPrjno()+"/"+ result.getId().getUseType()+"/"+ result.getId().getStartDate()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bid-prj-gdfks/:id} : Updates an existing bidPrjGdfk.
     *
     * @param bidPrjGdfkDTO the id of the bidPrjGdfkDTO to save.
     * @param bidPrjGdfkDTO the bidPrjGdfkDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidPrjGdfkDTO,
     * or with status {@code 400 (Bad Request)} if the bidPrjGdfkDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bidPrjGdfkDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bid-prj-gdfks")
    public ResponseEntity<BidPrjGdfkDTO> updateBidPrjGdfk(
//        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody BidPrjGdfkDTO bidPrjGdfkDTO
    ) throws URISyntaxException {
        BidPrjGdfkPrimaryKey bidPrjGdfkPrimaryKey = new BidPrjGdfkPrimaryKey(bidPrjGdfkDTO.getWkut(), bidPrjGdfkDTO.getPrjno(), bidPrjGdfkDTO.getUseType(), bidPrjGdfkDTO.getStartDate());
        log.debug("REST request to update BidPrjGdfk : {}, {}", bidPrjGdfkPrimaryKey, bidPrjGdfkDTO);
        if (bidPrjGdfkDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(bidPrjGdfkPrimaryKey, bidPrjGdfkDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidPrjGdfkRepository.existsById(bidPrjGdfkPrimaryKey)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        BidPrjGdfkDTO result = bidPrjGdfkService.save(bidPrjGdfkDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidPrjGdfkDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /bid-prj-gdfks/:id} : Partial updates given fields of an existing bidPrjGdfk, field will ignore if it is null
     *
     * @param bidPrjGdfkDTO the id of the bidPrjGdfkDTO to save.
     * @param bidPrjGdfkDTO the bidPrjGdfkDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidPrjGdfkDTO,
     * or with status {@code 400 (Bad Request)} if the bidPrjGdfkDTO is not valid,
     * or with status {@code 404 (Not Found)} if the bidPrjGdfkDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the bidPrjGdfkDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/bid-prj-gdfks", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<BidPrjGdfkDTO> partialUpdateBidPrjGdfk(
//        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody BidPrjGdfkDTO bidPrjGdfkDTO
    ) throws URISyntaxException {
        BidPrjGdfkPrimaryKey bidPrjGdfkPrimaryKey = new BidPrjGdfkPrimaryKey(bidPrjGdfkDTO.getWkut(), bidPrjGdfkDTO.getPrjno(), bidPrjGdfkDTO.getUseType(), bidPrjGdfkDTO.getStartDate());
        log.debug("REST request to partial update BidPrjGdfk partially : {}, {}", bidPrjGdfkPrimaryKey, bidPrjGdfkDTO);
        if (bidPrjGdfkDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(bidPrjGdfkPrimaryKey, bidPrjGdfkDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidPrjGdfkRepository.existsById(bidPrjGdfkPrimaryKey)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BidPrjGdfkDTO> result = bidPrjGdfkService.partialUpdate(bidPrjGdfkDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidPrjGdfkDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /bid-prj-gdfks} : get all the bidPrjGdfks.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bidPrjGdfks in body.
     */
    @GetMapping("/bid-prj-gdfks")
    public List<BidPrjGdfkDTO> getAllBidPrjGdfks() {
        log.debug("REST request to get all BidPrjGdfks");
        return bidPrjGdfkService.findAll();
    }

    /**
     * {@code GET  /bid-prj-gdfks/:id} : get the "id" bidPrjGdfk.
     *
     * @param bidPrjGdfkPrimaryKey the id of the bidPrjGdfkDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bidPrjGdfkDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/bid-prj-gdfks/getOne")
    public ResponseEntity<BidPrjGdfkDTO> getBidPrjGdfk(@PathVariable BidPrjGdfkPrimaryKey bidPrjGdfkPrimaryKey) {
        log.debug("REST request to get BidPrjGdfk : {}", bidPrjGdfkPrimaryKey);
        Optional<BidPrjGdfkDTO> bidPrjGdfkDTO = bidPrjGdfkService.findOne(bidPrjGdfkPrimaryKey);
        return ResponseUtil.wrapOrNotFound(bidPrjGdfkDTO);
    }

    /**
     * {@code DELETE  /bid-prj-gdfks/:id} : delete the "id" bidPrjGdfk.
     *
     * @param bidPrjGdfkPrimaryKey the id of the bidPrjGdfkDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @PostMapping("/bid-prj-gdfks/delete")
    public ResponseEntity<Void> deleteBidPrjGdfk(@RequestBody BidPrjGdfkPrimaryKey bidPrjGdfkPrimaryKey) {
        log.debug("REST request to delete BidPrjGdfk : {}", bidPrjGdfkPrimaryKey);
        bidPrjGdfkService.delete(bidPrjGdfkPrimaryKey);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, bidPrjGdfkPrimaryKey.toString()))
            .build();
    }

    //    分頁用criteria
    /**
     * {@code POST  /bid-prj-add/criteria-jpa} : get admSysCode by criteria.
     *
     * @param criteria the criteria of the BidPrjEnviCriteria to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the BidPrjEnviDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/bid-prj-gdfk/criteria-jpa")
    public Page<BidPrjGdfkDTO> getBidPrjGdfkByCriteria(@Valid @RequestBody BidPrjGdfkCriteria criteria) throws InterruptedException {
        log.debug("REST request to get BidPrjGdfkDTO : {}", criteria);
        return bidPrjGdfkService.findByCriteria(criteria);
    }
}
