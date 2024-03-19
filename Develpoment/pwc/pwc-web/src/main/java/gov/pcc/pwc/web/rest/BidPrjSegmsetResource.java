package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.common.web.ReportMediaType;
import gov.pcc.pwc.domain.BidPrjFrwkrPrimaryKey;
import gov.pcc.pwc.domain.BidPrjSegmsetPrimaryKey;
import gov.pcc.pwc.domain.BidProjectPrimaryKey;
import gov.pcc.pwc.report.bid.bidaae022.BidAae022;
import gov.pcc.pwc.repository.BidPrjSegmsetRepository;
import gov.pcc.pwc.service.AdmSysCodeService;
import gov.pcc.pwc.service.BidPrjSegmsetService;
import gov.pcc.pwc.service.BidProjectService;
import gov.pcc.pwc.service.ReportService;
import gov.pcc.pwc.service.criteria.BidPrjEnviCriteria;
import gov.pcc.pwc.service.criteria.BidPrjSegmsetCriteria;
import gov.pcc.pwc.service.criteria.BidProjectUnitCriteria;
import gov.pcc.pwc.service.dto.*;

import java.io.IOException;
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
import org.springframework.data.domain.Page;

/**
 * REST controller for managing {@link gov.pcc.pwc.domain.BidPrjSegmset}.
 */
@RestController
@RequestMapping("/api")
public class BidPrjSegmsetResource {

    private final Logger log = LoggerFactory.getLogger(BidPrjSegmsetResource.class);

    private static final String ENTITY_NAME = "bidPrjSegmset";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BidPrjSegmsetService bidPrjSegmsetService;

    private final BidPrjSegmsetRepository bidPrjSegmsetRepository;

    private final BidProjectService bidProjectService;

    private final AdmSysCodeService admSysCodeService;


    // 產生報表檔
    private final ReportService reportService;

    public BidPrjSegmsetResource(BidPrjSegmsetService bidPrjSegmsetService, BidPrjSegmsetRepository bidPrjSegmsetRepository, ReportService reportService,  BidProjectService bidProjectService, AdmSysCodeService admSysCodeService) {
        this.bidPrjSegmsetService = bidPrjSegmsetService;
        this.bidPrjSegmsetRepository = bidPrjSegmsetRepository;
        this.reportService = reportService;
        this.bidProjectService = bidProjectService;
        this.admSysCodeService = admSysCodeService;
    }

    /**
     * {@code POST  /bid-prj-segmsets} : Create a new bidPrjSegmset.
     *
     * @param bidPrjSegmsetDTO the bidPrjSegmsetDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bidPrjSegmsetDTO, or with status {@code 400 (Bad Request)} if the bidPrjSegmset has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bid-prj-segmsets")
    public ResponseEntity<BidPrjSegmsetDTO> createBidPrjSegmset(@Valid @RequestBody BidPrjSegmsetDTO bidPrjSegmsetDTO)
        throws URISyntaxException {
        log.debug("REST request to save BidPrjSegmset : {}", bidPrjSegmsetDTO);
//        if (bidPrjSegmsetDTO.getId() != null) {
//            throw new BadRequestAlertException("A new bidPrjSegmset cannot already have an ID", ENTITY_NAME, "idexists");
//        }
        BidPrjSegmsetDTO result = bidPrjSegmsetService.save(bidPrjSegmsetDTO);
        return ResponseEntity
//            .created(new URI("/api/bid-prj-segmsets/" + result.getId()))
//            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
//            .body(result);
             .created(new URI("/api/bid-prj-safes/" +result.getId().getWkut()+"/"+ result.getId().getPrjno()+"/"+ result.getId().getAgreeDate()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bid-prj-segmsets/:id} : Updates an existing bidPrjSegmset.
     *
     * @param bidPrjSegmsetDTO the id of the bidPrjSegmsetDTO to save.
     * @param bidPrjSegmsetDTO the bidPrjSegmsetDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidPrjSegmsetDTO,
     * or with status {@code 400 (Bad Request)} if the bidPrjSegmsetDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bidPrjSegmsetDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bid-prj-segmsets/")
    public ResponseEntity<BidPrjSegmsetDTO> updateBidPrjSegmset(
//        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody BidPrjSegmsetDTO bidPrjSegmsetDTO
    ) throws URISyntaxException {
        BidPrjSegmsetPrimaryKey bidPrjSegmsetPrimaryKey = new BidPrjSegmsetPrimaryKey(bidPrjSegmsetDTO.getWkut(), bidPrjSegmsetDTO.getPrjno(), bidPrjSegmsetDTO.getAgreeDate());
        log.debug("REST request to update BidPrjSegmset : {}", bidPrjSegmsetDTO);
        if (bidPrjSegmsetDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(bidPrjSegmsetPrimaryKey, bidPrjSegmsetDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidPrjSegmsetRepository.existsById(bidPrjSegmsetPrimaryKey)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        BidPrjSegmsetDTO result = bidPrjSegmsetService.save(bidPrjSegmsetDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidPrjSegmsetDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /bid-prj-segmsets/:id} : Partial updates given fields of an existing bidPrjSegmset, field will ignore if it is null
     *
     * @param bidPrjSegmsetDTO the id of the bidPrjSegmsetDTO to save.
     * @param bidPrjSegmsetDTO the bidPrjSegmsetDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidPrjSegmsetDTO,
     * or with status {@code 400 (Bad Request)} if the bidPrjSegmsetDTO is not valid,
     * or with status {@code 404 (Not Found)} if the bidPrjSegmsetDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the bidPrjSegmsetDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/bid-prj-segmsets", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<BidPrjSegmsetDTO> partialUpdateBidPrjSegmset(
//        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody BidPrjSegmsetDTO bidPrjSegmsetDTO
    ) throws URISyntaxException {
        BidPrjSegmsetPrimaryKey bidPrjSegmsetPrimaryKey = new BidPrjSegmsetPrimaryKey(bidPrjSegmsetDTO.getWkut(), bidPrjSegmsetDTO.getPrjno(), bidPrjSegmsetDTO.getAgreeDate());
        log.debug("REST request to partial update BidPrjSegmset partially : {}", bidPrjSegmsetDTO);
        if (bidPrjSegmsetDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(bidPrjSegmsetPrimaryKey, bidPrjSegmsetDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidPrjSegmsetRepository.existsById(bidPrjSegmsetPrimaryKey)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BidPrjSegmsetDTO> result = bidPrjSegmsetService.partialUpdate(bidPrjSegmsetDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidPrjSegmsetDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /bid-prj-segmsets} : get all the bidPrjSegmsets.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bidPrjSegmsets in body.
     */
    @GetMapping("/bid-prj-segmsets")
    public List<BidPrjSegmsetDTO> getAllBidPrjSegmsets() {
        log.debug("REST request to get all BidPrjSegmsets");
        return bidPrjSegmsetService.findAll();
    }

    /**
     * {@code GET  /bid-prj-segmsets/:id} : get the "id" bidPrjSegmset.
     *
     * @param bidPrjSegmsetPrimaryKey the id of the bidPrjSegmsetDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bidPrjSegmsetDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/bid-prj-segmsets/getOne")
    public ResponseEntity<BidPrjSegmsetDTO> getBidPrjSegmset(@PathVariable BidPrjSegmsetPrimaryKey bidPrjSegmsetPrimaryKey) {
        log.debug("REST request to get BidPrjSegmset : {}", bidPrjSegmsetPrimaryKey);
        Optional<BidPrjSegmsetDTO> bidPrjSegmsetDTO = bidPrjSegmsetService.findOne(bidPrjSegmsetPrimaryKey);
        return ResponseUtil.wrapOrNotFound(bidPrjSegmsetDTO);
    }

    /**
     * {@code DELETE  /bid-prj-segmsets/:id} : delete the "id" bidPrjSegmset.
     *
     * @param bidPrjEnviPrimaryKey the id of the bidPrjSegmsetDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @PostMapping("/bid-prj-segmsets-delete")
    public ResponseEntity<Void> deleteBidPrjSegmset(@RequestBody BidPrjSegmsetPrimaryKey bidPrjEnviPrimaryKey) {
        log.debug("REST request to delete BidPrjSegmset : {}", bidPrjEnviPrimaryKey);
        bidPrjSegmsetService.delete(bidPrjEnviPrimaryKey);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, bidPrjEnviPrimaryKey.toString()))
            .build();
    }

//    分頁用criteria
    /**
     * {@code POST  /bid-prj-add/criteria-jpa} : get admSysCode by criteria.
     *
     * @param criteria the criteria of the BidPrjEnviCriteria to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the BidPrjEnviDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/bid-prj-segmset/criteria-jpa")
    public Page<BidPrjSegmsetDTO> getBidPrjSegmsetByCriteria(@Valid @RequestBody BidPrjSegmsetCriteria criteria) throws InterruptedException {
        log.debug("REST request to get BidPrjEnviDTO : {}", criteria);
        return bidPrjSegmsetService.findByCriteria(criteria);
    }



    // 產生混凝土報表 bid-aae-022
    @PostMapping(path="/bid-prj-segmset/bidAae022",produces = ReportMediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> bidAae022(@RequestBody BidPrjSegmsetCriteria criteria) throws IOException {
        log.debug("REST request to get pdf : {}", criteria);


        BidPrjSegmsetPrimaryKey bidPrjSegmsetPrimaryKey = new BidPrjSegmsetPrimaryKey(criteria.getWkut(),criteria.getPrjno(), criteria.getAgreeDate());

        // find BidPrjSegmsetDTO
        BidPrjSegmsetDTO bidPrjSegmsetDTO = bidPrjSegmsetService.findOne(bidPrjSegmsetPrimaryKey).get();

        BidProjectPrimaryKey bidProjectPrimaryKey = new BidProjectPrimaryKey(criteria.getWkut(),criteria.getPrjno());
        // find BidProjectDTO
        BidProjectDTO bidProjectDTO = bidProjectService.findOne(bidProjectPrimaryKey).get();


        // 同意理由
        String agreeReson ="";
        String agreeResonNum = bidPrjSegmsetDTO.getAgreeReson();
        if(agreeResonNum != null && agreeResonNum.length() > 0 ) {
            AdmSysCodeDTO admSysCodeDTO3 = admSysCodeService.findByPk("BID", "BID_050", agreeResonNum);
            agreeReson = admSysCodeDTO3.getValue();
        }



        BidAae022 bidAae022 = new BidAae022();
        bidAae022.setBidProjectDTO(bidProjectDTO);
        bidAae022.setBidPrjSegmsetDTO(bidPrjSegmsetDTO);
        bidAae022.setAgreeReson(agreeReson);
        byte[] pdfbyte = reportService.printReport(bidAae022);
        return ResponseEntity.ok(pdfbyte);


    }
}
