package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.common.errors.BidValidationFailException;
import gov.pcc.pwc.domain.BidPrjSur2PrimaryKey;
import gov.pcc.pwc.repository.BidPrjSur2Repository;
import gov.pcc.pwc.service.BidPrjSur2Service;
import gov.pcc.pwc.service.criteria.BidPrjSur2Criteria;
import gov.pcc.pwc.service.dto.BidPrjSur2DTO;
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
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link gov.pcc.pwc.domain.BidPrjSur2}.
 */
@RestController
@RequestMapping("/api")
public class BidPrjSur2Resource {

    private final Logger log = LoggerFactory.getLogger(BidPrjSur2Resource.class);

    private static final String ENTITY_NAME = "bidPrjSur2";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BidPrjSur2Service bidPrjSur2Service;

    private final BidPrjSur2Repository bidPrjSur2Repository;

    public BidPrjSur2Resource(BidPrjSur2Service bidPrjSur2Service, BidPrjSur2Repository bidPrjSur2Repository) {
        this.bidPrjSur2Service = bidPrjSur2Service;
        this.bidPrjSur2Repository = bidPrjSur2Repository;
    }

    /**
     * {@code POST  /bid-prj-sur-2-s} : Create a new bidPrjSur2.
     *
     * @param bidPrjSur2DTO the bidPrjSur2DTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bidPrjSur2DTO, or with status {@code 400 (Bad Request)} if the bidPrjSur2 has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bid-prj-sur-2-s")
    public ResponseEntity<BidPrjSur2DTO> createBidPrjSur2(@Valid @RequestBody BidPrjSur2DTO bidPrjSur2DTO) throws URISyntaxException {
        log.debug("REST request to save BidPrjSur2 : {}", bidPrjSur2DTO);

        String formStatus = "create";
        //        後端驗證
        List<String> validResult = bidPrjSur2Service.validData(bidPrjSur2DTO , formStatus);
        if(validResult.size()>0){
            throw new BidValidationFailException("bidValidError","資料檢核錯誤" ,validResult.toArray(new String[0]));
        }

        BidPrjSur2DTO result = bidPrjSur2Service.save(bidPrjSur2DTO);
        return ResponseEntity
            .created(new URI("/api/bid-prj-sur-2-s/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bid-prj-sur-2-s/:id} : Updates an existing bidPrjSur2.
     *
     * @param bidPrjSur2DTO the bidPrjSur2DTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidPrjSur2DTO,
     * or with status {@code 400 (Bad Request)} if the bidPrjSur2DTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bidPrjSur2DTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bid-prj-sur-2-s/updateOne")
    public ResponseEntity<BidPrjSur2DTO> updateBidPrjSur2(
        @Valid @RequestBody BidPrjSur2DTO bidPrjSur2DTO
    ) throws URISyntaxException {
        log.debug("REST request to update BidPrjSur2 : {}", bidPrjSur2DTO);
        BidPrjSur2PrimaryKey bidPrjSur2PrimaryKey = new BidPrjSur2PrimaryKey(bidPrjSur2DTO.getWkut(),bidPrjSur2DTO.getPrjno(),bidPrjSur2DTO.getDisNo());
        if (bidPrjSur2DTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(bidPrjSur2PrimaryKey, bidPrjSur2DTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidPrjSur2Repository.existsById(bidPrjSur2PrimaryKey)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        String formStatus = "modify";
        //        後端驗證
        List<String> validResult = bidPrjSur2Service.validData(bidPrjSur2DTO , formStatus);
        if(validResult.size()>0){
            throw new BidValidationFailException("bidValidError","資料檢核錯誤" ,validResult.toArray(new String[0]));
        }

        BidPrjSur2DTO result = bidPrjSur2Service.save(bidPrjSur2DTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidPrjSur2DTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /bid-prj-sur-2-s/:id} : Partial updates given fields of an existing bidPrjSur2, field will ignore if it is null
     *
     * @param bidPrjSur2DTO the bidPrjSur2DTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidPrjSur2DTO,
     * or with status {@code 400 (Bad Request)} if the bidPrjSur2DTO is not valid,
     * or with status {@code 404 (Not Found)} if the bidPrjSur2DTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the bidPrjSur2DTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/bid-prj-sur-2-s/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<BidPrjSur2DTO> partialUpdateBidPrjSur2(
        @NotNull @RequestBody BidPrjSur2DTO bidPrjSur2DTO
    ) throws URISyntaxException {
        BidPrjSur2PrimaryKey bidPrjSur2PrimaryKey = new BidPrjSur2PrimaryKey(bidPrjSur2DTO.getWkut(),bidPrjSur2DTO.getPrjno(),bidPrjSur2DTO.getDisNo());
        log.debug("REST request to partial update BidPrjSur2 partially : {}", bidPrjSur2DTO);
        if (bidPrjSur2DTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(bidPrjSur2PrimaryKey, bidPrjSur2DTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidPrjSur2Repository.existsById(bidPrjSur2PrimaryKey)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BidPrjSur2DTO> result = bidPrjSur2Service.partialUpdate(bidPrjSur2DTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidPrjSur2DTO.getId().toString())
        );
    }

    /**
     * {@code GET  /bid-prj-sur-2-s} : get all the bidPrjSur2s.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bidPrjSur2s in body.
     */
    @GetMapping("/bid-prj-sur-2-s")
    public List<BidPrjSur2DTO> getAllBidPrjSur2s() {
        log.debug("REST request to get all BidPrjSur2s");
        return bidPrjSur2Service.findAll();
    }

    /**
     * {@code GET  /bid-prj-sur-2-s/:id} : get the "id" bidPrjSur2.
     *
     * @param id the id of the bidPrjSur2DTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bidPrjSur2DTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/bid-prj-sur-2-s/getOne")
    public ResponseEntity<BidPrjSur2DTO> getBidPrjSur2(@RequestBody BidPrjSur2PrimaryKey id) {
        log.debug("REST request to get BidPrjSur2 : {}", id);
        Optional<BidPrjSur2DTO> bidPrjSur2DTO = bidPrjSur2Service.findOne(id);
        return ResponseUtil.wrapOrNotFound(bidPrjSur2DTO);
    }

    /**
     * {@code DELETE  /bid-prj-sur-2-s/:id} : delete the "id" bidPrjSur2.
     *
     * @param id the id of the bidPrjSur2DTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bid-prj-sur-2-s/{id}")
    public ResponseEntity<Void> deleteBidPrjSur2(@PathVariable BidPrjSur2PrimaryKey id) {
        log.debug("REST request to delete BidPrjSur2 : {}", id);
        bidPrjSur2Service.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * find bidPrjSur2DTO by criteria
     * */
    @PostMapping("/bid-prj-sur-2-s/criteria")
    public Page<BidPrjSur2DTO> getBidPrjSur2ByCriteria(@Valid @RequestBody BidPrjSur2Criteria bidPrjSur2Criteria) throws InterruptedException{
        log.debug("REST request to get RecTyphoon : {}",bidPrjSur2Criteria);
        return bidPrjSur2Service.findAllByCriteria(bidPrjSur2Criteria);
    }
}
