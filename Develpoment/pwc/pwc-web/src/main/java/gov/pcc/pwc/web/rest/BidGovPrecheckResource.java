package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.MessageCodes;
import gov.pcc.pwc.common.enums.bid.BidFormStatusEnum;
import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.common.errors.BidValidationFailException;
import gov.pcc.pwc.domain.BidGovPrecheckPrimaryKey;
import gov.pcc.pwc.repository.BidGovPrecheckRepository;
import gov.pcc.pwc.service.BidGovPrecheckService;
import gov.pcc.pwc.service.criteria.BidGovPrecheckCriteria;
import gov.pcc.pwc.service.dto.BidGovPrecheckDTO;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import gov.pcc.pwc.utils.PwcHeadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link gov.pcc.pwc.domain.BidGovPrecheck}.
 */
@RestController
@RequestMapping("/api")
public class BidGovPrecheckResource {

    private final Logger log = LoggerFactory.getLogger(BidGovPrecheckResource.class);

    private static final String ENTITY_NAME = "bidGovPrecheck";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BidGovPrecheckService bidGovPrecheckService;

    private final BidGovPrecheckRepository bidGovPrecheckRepository;

    public BidGovPrecheckResource(BidGovPrecheckService bidGovPrecheckService, BidGovPrecheckRepository bidGovPrecheckRepository) {
        this.bidGovPrecheckService = bidGovPrecheckService;
        this.bidGovPrecheckRepository = bidGovPrecheckRepository;
    }

    /**
     * {@code POST  /bid-gov-prechecks} : Create a new bidGovPrecheck.
     *
     * @param bidGovPrecheckDTO the bidGovPrecheckDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bidGovPrecheckDTO, or with status {@code 400 (Bad Request)} if the bidGovPrecheck has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bid-gov-prechecks")
    public ResponseEntity<BidGovPrecheckDTO> createBidGovPrecheck(@Valid @RequestBody BidGovPrecheckDTO bidGovPrecheckDTO, Authentication authentication)
        throws URISyntaxException {
        log.debug("REST request to save BidGovPrecheck : {}", bidGovPrecheckDTO);

        List<String> validResult = bidGovPrecheckService.validData(bidGovPrecheckDTO, BidFormStatusEnum.CREATE);

        if(validResult.size()>0){
            throw new BidValidationFailException("bidValidError","資料檢核錯誤" ,validResult.toArray(new String[0]));
        }

        bidGovPrecheckDTO.setCreateDate(Instant.now());
        bidGovPrecheckDTO.setCreateUser(authentication.getName());
        bidGovPrecheckDTO.setUpdateDate(Instant.now());
        bidGovPrecheckDTO.setUpdateUser(authentication.getName());



        BidGovPrecheckDTO result = bidGovPrecheckService.save(bidGovPrecheckDTO);
        return ResponseEntity
            .created(new URI("/api/bid-gov-prechecks/" + result.getIdString()))
            .headers(PwcHeadUtil.createAlert(MessageCodes.PWC_COMMON_0001_S()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getIdString()))
            .body(result);
    }

    /**
     * {@code PUT  /bid-gov-prechecks/:id} : Updates an existing bidGovPrecheck.
     *
     * @param bidGovPrecheckDTO the bidGovPrecheckDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidGovPrecheckDTO,
     * or with status {@code 400 (Bad Request)} if the bidGovPrecheckDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bidGovPrecheckDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bid-gov-prechecks")
    public ResponseEntity<BidGovPrecheckDTO> updateBidGovPrecheck(
        @Valid @RequestBody BidGovPrecheckDTO bidGovPrecheckDTO, Authentication authentication
    ) throws URISyntaxException {
        BidGovPrecheckPrimaryKey id = new BidGovPrecheckPrimaryKey(bidGovPrecheckDTO.getWkut(), bidGovPrecheckDTO.getPrjno(), bidGovPrecheckDTO.getCheckDate());
        log.debug("REST request to update BidGovPrecheck : {}", bidGovPrecheckDTO);
        if (bidGovPrecheckDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, bidGovPrecheckDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidGovPrecheckRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        List<String> validResult = bidGovPrecheckService.validData(bidGovPrecheckDTO, BidFormStatusEnum.UPDATE);

        if(validResult.size()>0){
            throw new BidValidationFailException("bidValidError","資料檢核錯誤" ,validResult.toArray(new String[0]));
        }

        bidGovPrecheckDTO.setUpdateDate(Instant.now());
        bidGovPrecheckDTO.setUpdateUser(authentication.getName());

        BidGovPrecheckDTO result = bidGovPrecheckService.save(bidGovPrecheckDTO);
        return ResponseEntity
            .ok()
            .headers(PwcHeadUtil.createAlert(MessageCodes.PWC_COMMON_0002_S()))
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidGovPrecheckDTO.getIdString()))
            .body(result);
    }

    /**
     * {@code PATCH  /bid-gov-prechecks/:id} : Partial updates given fields of an existing bidGovPrecheck, field will ignore if it is null
     *
     * @param bidGovPrecheckDTO the bidGovPrecheckDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidGovPrecheckDTO,
     * or with status {@code 400 (Bad Request)} if the bidGovPrecheckDTO is not valid,
     * or with status {@code 404 (Not Found)} if the bidGovPrecheckDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the bidGovPrecheckDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/bid-gov-prechecks", consumes = {"application/json", "application/merge-patch+json"})
    public ResponseEntity<BidGovPrecheckDTO> partialUpdateBidGovPrecheck(
        @NotNull @RequestBody BidGovPrecheckDTO bidGovPrecheckDTO
    ) throws URISyntaxException {
        BidGovPrecheckPrimaryKey id = new BidGovPrecheckPrimaryKey(bidGovPrecheckDTO.getWkut(), bidGovPrecheckDTO.getPrjno(), bidGovPrecheckDTO.getCheckDate());
        log.debug("REST request to partial update BidGovPrecheck partially : {}, {}", id, bidGovPrecheckDTO);
        if (bidGovPrecheckDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, bidGovPrecheckDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidGovPrecheckRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BidGovPrecheckDTO> result = bidGovPrecheckService.partialUpdate(bidGovPrecheckDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidGovPrecheckDTO.getIdString())
        );
    }

    /**
     * {@code GET  /bid-gov-prechecks} : get all the bidGovPrechecks.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bidGovPrechecks in body.
     */
    @GetMapping("/bid-gov-prechecks")
    public List<BidGovPrecheckDTO> getAllBidGovPrechecks() {
        log.debug("REST request to get all BidGovPrechecks");
        return bidGovPrecheckService.findAll();
    }

    /**
     * {@code GET  /bid-gov-prechecks/:id} : get the "id" bidGovPrecheck.
     *
     * @param id the id of the bidGovPrecheckDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bidGovPrecheckDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/bid-gov-prechecks/getOne")
    public ResponseEntity<BidGovPrecheckDTO> getBidGovPrecheck(@RequestBody BidGovPrecheckPrimaryKey id) {
        log.debug("REST request to get BidGovPrecheck : {}", id);
        Optional<BidGovPrecheckDTO> bidGovPrecheckDTO = bidGovPrecheckService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bidGovPrecheckDTO);
    }

    /**
     * {@code DELETE  /bid-gov-prechecks/:id} : delete the "id" bidGovPrecheck.
     *
     * @param id the id of the bidGovPrecheckDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @PostMapping("/bid-gov-prechecks/deleteOne")
    public ResponseEntity<Void> deleteBidGovPrecheck(@RequestBody BidGovPrecheckPrimaryKey id) {
        log.debug("REST request to delete BidGovPrecheck : {}", id);
        bidGovPrecheckService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    @PostMapping("/bid-gov-prechecks/findPageByCriteria")
    public Page<BidGovPrecheckDTO> findPageByCriteria(@RequestBody BidGovPrecheckCriteria criteria) {
        log.debug("REST request to get BidGovPrecheck Page by criteria : {}", criteria);
        return bidGovPrecheckService.findPageByCriteria(criteria);
    }


    @PostMapping("/bid-gov-prechecks/latest")
    public ResponseEntity<BidGovPrecheckDTO> getLatestBidGovPrechecks(@RequestBody BidGovPrecheckCriteria criteria) {
        log.debug("REST request to get latest BidGovPrecheck: {}", criteria);
        criteria.setSortBy(new String[]{"checkDate"});
        criteria.setSortDirection(Sort.Direction.DESC);
        log.debug("After posthandle request to get latest BidGovPrecheck: {}", criteria);
        List<BidGovPrecheckDTO> prechecks = bidGovPrecheckService.findPageByCriteria(criteria).getContent();
        if (prechecks.size() > 0) {
            return ResponseEntity
                .ok()
                .body(prechecks.get(0));
        } else {
            return ResponseEntity
                .ok()
                .body(null);
        }
    }
}
