package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.domain.BidPrjQcPrimaryKey;
import gov.pcc.pwc.repository.BidPrjQcRepository;
import gov.pcc.pwc.service.BidPrjQcService;
import gov.pcc.pwc.service.dto.BidPrjQcDTO;
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
 * REST controller for managing {@link gov.pcc.pwc.domain.BidPrjQc}.
 */
@RestController
@RequestMapping("/api")
public class BidPrjQcResource {

    private final Logger log = LoggerFactory.getLogger(BidPrjQcResource.class);

    private static final String ENTITY_NAME = "bidPrjQc";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BidPrjQcService bidPrjQcService;

    private final BidPrjQcRepository bidPrjQcRepository;

    public BidPrjQcResource(BidPrjQcService bidPrjQcService, BidPrjQcRepository bidPrjQcRepository) {
        this.bidPrjQcService = bidPrjQcService;
        this.bidPrjQcRepository = bidPrjQcRepository;
    }

    /**
     * {@code POST  /bid-prj-qcs} : Create a new bidPrjQc.
     *
     * @param bidPrjQcDTO the bidPrjQcDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bidPrjQcDTO, or with status {@code 400 (Bad Request)} if the bidPrjQc has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bid-prj-qcs")
    public ResponseEntity<BidPrjQcDTO> createBidPrjQc(@Valid @RequestBody BidPrjQcDTO bidPrjQcDTO) throws URISyntaxException {
        log.debug("REST request to save BidPrjQc : {}", bidPrjQcDTO);
        if (bidPrjQcDTO.getPrjQcKey() != null) {
            throw new BadRequestAlertException("A new bidPrjQc cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BidPrjQcDTO result = bidPrjQcService.save(bidPrjQcDTO);
        return ResponseEntity
            .created(new URI("/api/bid-prj-qcs/" + result.getPrjQcKey()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /bid-prj-qcs/:id} : Updates an existing bidPrjQc.
     *
     * @param bidPrjQcDTO the bidPrjQcDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidPrjQcDTO,
     * or with status {@code 400 (Bad Request)} if the bidPrjQcDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bidPrjQcDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bid-prj-qcs/update")
    public ResponseEntity<BidPrjQcDTO> updateBidPrjQc(
        @Valid @RequestBody BidPrjQcDTO bidPrjQcDTO
    ) throws URISyntaxException {
        log.debug("REST request to update BidPrjQc : {}", bidPrjQcDTO);
        BidPrjQcPrimaryKey bidPrjQcPrimaryKey = new BidPrjQcPrimaryKey(bidPrjQcDTO.getWkut(),bidPrjQcDTO.getPrjno(),bidPrjQcDTO.getId(),bidPrjQcDTO.getStartDate());
        if (bidPrjQcDTO.getPrjQcKey() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(bidPrjQcPrimaryKey, bidPrjQcDTO.getPrjQcKey())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidPrjQcRepository.existsById(bidPrjQcPrimaryKey)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        BidPrjQcDTO result = bidPrjQcService.save(bidPrjQcDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidPrjQcDTO.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /bid-prj-qcs/:id} : Partial updates given fields of an existing bidPrjQc, field will ignore if it is null
     *
     * @param bidPrjQcDTO the bidPrjQcDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidPrjQcDTO,
     * or with status {@code 400 (Bad Request)} if the bidPrjQcDTO is not valid,
     * or with status {@code 404 (Not Found)} if the bidPrjQcDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the bidPrjQcDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/bid-prj-qcs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<BidPrjQcDTO> partialUpdateBidPrjQc(
        @NotNull @RequestBody BidPrjQcDTO bidPrjQcDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update BidPrjQc partially : {}", bidPrjQcDTO);
        BidPrjQcPrimaryKey bidPrjQcPrimaryKey = new BidPrjQcPrimaryKey(bidPrjQcDTO.getWkut(),bidPrjQcDTO.getPrjno(),bidPrjQcDTO.getId(),bidPrjQcDTO.getStartDate());

        if (bidPrjQcDTO.getPrjQcKey() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(bidPrjQcPrimaryKey, bidPrjQcDTO.getPrjQcKey())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidPrjQcRepository.existsById(bidPrjQcPrimaryKey)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BidPrjQcDTO> result = bidPrjQcService.partialUpdate(bidPrjQcDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidPrjQcDTO.getId())
        );
    }

    /**
     * {@code GET  /bid-prj-qcs} : get all the bidPrjQcs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bidPrjQcs in body.
     */
    @GetMapping("/bid-prj-qcs")
    public List<BidPrjQcDTO> getAllBidPrjQcs() {
        log.debug("REST request to get all BidPrjQcs");
        return bidPrjQcService.findAll();
    }

    /**
     * {@code GET  /bid-prj-qcs/:id} : get the "id" bidPrjQc.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bidPrjQcDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/bid-prj-qcs/getOne")
    public ResponseEntity<BidPrjQcDTO> getBidPrjQc(@RequestBody BidPrjQcPrimaryKey id) {
        log.debug("REST request to get BidPrjQc : {}", id);
        Optional<BidPrjQcDTO> bidPrjQcDTO = bidPrjQcService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bidPrjQcDTO);
    }

    /**
     * {@code DELETE  /bid-prj-qcs/:id} : delete the "id" bidPrjQc.
     *
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bid-prj-qcs/delete")
    public ResponseEntity<Void> deleteBidPrjQc(@RequestBody BidPrjQcPrimaryKey id) {
        log.debug("REST request to delete BidPrjQc : {}", id);
        bidPrjQcService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * get bidPrjQc by wkut and prjno
     *
     * **/
    @PostMapping("/bid-prj-qcs/getListByCretiria")
    public List<BidPrjQcDTO> getBidPrjQcList(@Valid@RequestBody BidPrjQcDTO bidPrjQcDTO){
        log.debug("REST request to get RecTyphoon : {}",bidPrjQcDTO);
        return bidPrjQcService.findPrjQcList(bidPrjQcDTO);

    }
}
