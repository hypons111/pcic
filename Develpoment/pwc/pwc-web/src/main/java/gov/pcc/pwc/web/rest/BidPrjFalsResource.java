package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.common.errors.BidValidationFailException;
import gov.pcc.pwc.domain.BidPrjFalsPrimaryKey;
import gov.pcc.pwc.domain.BidProjectPrimaryKey;
import gov.pcc.pwc.repository.BidPrjFalsRepository;
import gov.pcc.pwc.repository.BidProjectRepository;
import gov.pcc.pwc.service.BidPrjFalsService;
import gov.pcc.pwc.service.BidProjectService;
import gov.pcc.pwc.service.criteria.BidGovPrecheckCriteria;
import gov.pcc.pwc.service.criteria.BidPrjFalsCriteria;
import gov.pcc.pwc.service.dto.BidGovPrecheckDTO;
import gov.pcc.pwc.service.dto.BidPrjFalsDTO;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import gov.pcc.pwc.service.dto.BidProjectDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link gov.pcc.pwc.domain.BidPrjFals}.
 */
@RestController
@RequestMapping("/api")
public class BidPrjFalsResource {

    private final Logger log = LoggerFactory.getLogger(BidPrjFalsResource.class);

    private static final String ENTITY_NAME = "bidPrjFals";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BidPrjFalsService bidPrjFalsService;

    private final BidPrjFalsRepository bidPrjFalsRepository;
    private final BidProjectService bidProjectService;

    public BidPrjFalsResource(BidPrjFalsService bidPrjFalsService, BidPrjFalsRepository bidPrjFalsRepository, BidProjectService bidProjectService) {
        this.bidPrjFalsService = bidPrjFalsService;
        this.bidPrjFalsRepository = bidPrjFalsRepository;
        this.bidProjectService = bidProjectService;
    }

    /**
     * {@code POST  /bid-prj-falss} : Create a new bidPrjFals.
     *
     * @param bidPrjFalsDTO the bidPrjFalsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bidPrjFalsDTO, or with status {@code 400 (Bad Request)} if the bidPrjFals has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bid-prj-falss")
    public ResponseEntity<BidPrjFalsDTO> createBidPrjFals(@Valid @RequestBody BidPrjFalsDTO bidPrjFalsDTO, Authentication authentication) throws URISyntaxException {
        log.debug("REST request to save BidPrjFals : {}", bidPrjFalsDTO);

        bidPrjFalsDTO.setCreateUser(authentication.getName());
        bidPrjFalsDTO.setUpdateUser(authentication.getName());
        bidPrjFalsDTO.setCreateDate(Instant.now());
        bidPrjFalsDTO.setUpdateDate(Instant.now());

        String formStatus = "create";
//        後端驗證
        List<String> validResult = bidPrjFalsService.validData(bidPrjFalsDTO, formStatus);
        if (validResult.size() > 0) {
            throw new BidValidationFailException("bidValidError", "資料檢核錯誤", validResult.toArray(new String[0]));
        }

        BidPrjFalsDTO result = bidPrjFalsService.save(bidPrjFalsDTO);
        return ResponseEntity
            .created(new URI("/api/bid-prj-falss/" + bidPrjFalsDTO.getWkut()+bidPrjFalsDTO.getPrjno()+bidPrjFalsDTO.getSyr()+bidPrjFalsDTO.getMonth()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME,bidPrjFalsDTO.getWkut()+bidPrjFalsDTO.getPrjno()+bidPrjFalsDTO.getSyr()+bidPrjFalsDTO.getMonth()))
            .body(result);
    }

    /**
     * {@code PUT  /bid-prj-falss/:id} : Updates an existing bidPrjFals.
     *
     * @param bidPrjFalsDTO the bidPrjFalsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidPrjFalsDTO,
     * or with status {@code 400 (Bad Request)} if the bidPrjFalsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bidPrjFalsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bid-prj-falss/updateOne")
    public ResponseEntity<BidPrjFalsDTO> updateBidPrjFals(
        @Valid @RequestBody BidPrjFalsDTO bidPrjFalsDTO,
        Authentication authentication
    ) throws URISyntaxException {
        log.debug("REST request to update BidPrjFals : {}", bidPrjFalsDTO);
        BidPrjFalsPrimaryKey bidPrjFalsPrimaryKey = new BidPrjFalsPrimaryKey(bidPrjFalsDTO.getWkut(), bidPrjFalsDTO.getPrjno(), bidPrjFalsDTO.getYear(), bidPrjFalsDTO.getMonth());
        if (bidPrjFalsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(bidPrjFalsPrimaryKey, bidPrjFalsDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidPrjFalsRepository.existsById(bidPrjFalsPrimaryKey)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        bidPrjFalsDTO.setCreateUser(authentication.getName());
        bidPrjFalsDTO.setUpdateUser(authentication.getName());
        bidPrjFalsDTO.setCreateDate(Instant.now());
        bidPrjFalsDTO.setUpdateDate(Instant.now());

        String formStatus = "update";
//        後端驗證
        List<String> validResult = bidPrjFalsService.validData(bidPrjFalsDTO, formStatus);
        if (validResult.size() > 0) {
            throw new BidValidationFailException("bidValidError", "資料檢核錯誤", validResult.toArray(new String[0]));
        }

        // newDate 不為空需要修正csDate
        if(bidPrjFalsDTO.getNewDate() != null) {
            BidProjectPrimaryKey bidProjectPrimaryKey = new BidProjectPrimaryKey(bidPrjFalsDTO.getWkut(), bidPrjFalsDTO.getPrjno());
            BidProjectDTO bidProjectDTO = bidProjectService.findOne(bidProjectPrimaryKey).get();
            bidProjectDTO.setCsDate(bidPrjFalsDTO.getNewDate());
            bidProjectService.save(bidProjectDTO);
        }

        BidPrjFalsDTO result = bidPrjFalsService.save(bidPrjFalsDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidPrjFalsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /bid-prj-falss/:id} : Partial updates given fields of an existing bidPrjFals, field will ignore if it is null
     *
     * @param bidPrjFalsDTO the bidPrjFalsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidPrjFalsDTO,
     * or with status {@code 400 (Bad Request)} if the bidPrjFalsDTO is not valid,
     * or with status {@code 404 (Not Found)} if the bidPrjFalsDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the bidPrjFalsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/bid-prj-falss/partialUpdate", consumes = {"application/json", "application/merge-patch+json"})
    public ResponseEntity<BidPrjFalsDTO> partialUpdateBidPrjFals(
        @NotNull @RequestBody BidPrjFalsDTO bidPrjFalsDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update BidPrjFals partially : {}", bidPrjFalsDTO);
        BidPrjFalsPrimaryKey bidPrjFalsPrimaryKey = new BidPrjFalsPrimaryKey(bidPrjFalsDTO.getWkut(), bidPrjFalsDTO.getPrjno(), bidPrjFalsDTO.getYear(), bidPrjFalsDTO.getMonth());

        if (bidPrjFalsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(bidPrjFalsPrimaryKey, bidPrjFalsDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidPrjFalsRepository.existsById(bidPrjFalsPrimaryKey)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BidPrjFalsDTO> result = bidPrjFalsService.partialUpdate(bidPrjFalsDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidPrjFalsDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /bid-prj-falss} : get all the bidPrjFals.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bidPrjFals in body.
     */
    @GetMapping("/bid-prj-falss")
    public List<BidPrjFalsDTO> getAllBidPrjFals() {
        log.debug("REST request to get all BidPrjFals");
        return bidPrjFalsService.findAll();
    }

    /**
     * {@code GET  /bid-prj-falss/:id} : get the "id" bidPrjFals.
     *
     * @param id the id of the bidPrjFalsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bidPrjFalsDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/bid-prj-falss/getOne")
    public ResponseEntity<BidPrjFalsDTO> getBidPrjFals(@RequestBody BidPrjFalsPrimaryKey id) {
        log.debug("REST request to get BidPrjFals : {}", id);
        Optional<BidPrjFalsDTO> bidPrjFalsDTO = bidPrjFalsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bidPrjFalsDTO);
    }

    /**
     * {@code DELETE  /bid-prj-falss/:id} : delete the "id" bidPrjFals.
     *
     * @param id the id of the bidPrjFalsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bid-prj-falss/{id}")
    public ResponseEntity<Void> deleteBidPrjFals(@PathVariable BidPrjFalsPrimaryKey id) {
        log.debug("REST request to delete BidPrjFals : {}", id);
        bidPrjFalsService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    @PostMapping("/bid-prj-falss/criteria")
    public Page<BidPrjFalsDTO> findPageByCriteria(@RequestBody BidPrjFalsCriteria criteria) {
        log.debug("REST request to get BidPrjFalsDTO Page by criteria : {}", criteria);
        return bidPrjFalsService.findOneByCriteria(criteria);
    }
}
