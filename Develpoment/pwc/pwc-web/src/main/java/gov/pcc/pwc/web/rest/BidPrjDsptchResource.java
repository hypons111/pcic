package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.common.errors.BidValidationFailException;
import gov.pcc.pwc.domain.BidPrjDsptchPrimaryKey;
import gov.pcc.pwc.domain.BidProjectPrimaryKey;
import gov.pcc.pwc.domain.BidProjectUnit;
import gov.pcc.pwc.domain.BidProjectUnitPrimaryKey;
import gov.pcc.pwc.report.bid.bidAae020.BidAae020s01a;
import gov.pcc.pwc.report.bid.bidAae020.BidAae020s02;
import gov.pcc.pwc.repository.BidPrjDsptchRepository;
import gov.pcc.pwc.service.*;
import gov.pcc.pwc.service.criteria.BidPrjDsptchCriteria;
import gov.pcc.pwc.service.criteria.BidPrjModifyCriteria;
import gov.pcc.pwc.service.dto.*;
import gov.pcc.pwc.common.errors.BadRequestAlertException;

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
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link gov.pcc.pwc.domain.BidPrjDsptch}.
 */
@RestController
@RequestMapping("/api")
public class BidPrjDsptchResource {

    private final Logger log = LoggerFactory.getLogger(BidPrjDsptchResource.class);

    private static final String ENTITY_NAME = "bidPrjDsptch";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BidPrjDsptchService bidPrjDsptchService;

    private final BidPrjDsptchRepository bidPrjDsptchRepository;

    private final BidProjectService bidProjectService;

    private final BidUnit10Service bidUnit10Service;

    private final BidPrjModifyService bidPrjModifyService;

    private final BidProjectUnitService bidProjectUnitService;



    // 產生報表檔
    private final ReportService reportService;

    public BidPrjDsptchResource(BidPrjDsptchService bidPrjDsptchService, BidPrjDsptchRepository bidPrjDsptchRepository,ReportService reportService,
                                BidProjectService bidProjectService,BidUnit10Service bidUnit10Service,BidPrjModifyService bidPrjModifyService,BidProjectUnitService bidProjectUnitService) {
        this.bidPrjDsptchService = bidPrjDsptchService;
        this.bidPrjDsptchRepository = bidPrjDsptchRepository;
        this.reportService = reportService;
        this.bidProjectService = bidProjectService;
        this.bidUnit10Service = bidUnit10Service;
        this.bidPrjModifyService = bidPrjModifyService;
        this.bidProjectUnitService = bidProjectUnitService;

    }

    /**
     * {@code POST  /bid-prj-dsptches} : Create a new bidPrjDsptch.
     *
     * @param bidPrjDsptchDTO the bidPrjDsptchDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bidPrjDsptchDTO, or with status {@code 400 (Bad Request)} if the bidPrjDsptch has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bid-prj-dsptches")
    public ResponseEntity<BidPrjDsptchDTO> createBidPrjDsptch(@Valid @RequestBody BidPrjDsptchDTO bidPrjDsptchDTO)
        throws URISyntaxException {
        log.debug("REST request to save BidPrjDsptch : {}", bidPrjDsptchDTO);
        String fromStatus = "create";

        //       後端驗證
        List<String> validResult = bidPrjDsptchService.validData(bidPrjDsptchDTO, fromStatus);
        if (validResult.size() > 0) {
            throw new BidValidationFailException("bidValidError", "資料檢核錯誤", validResult.toArray(new String[0]));
        }

        BidPrjDsptchDTO result = bidPrjDsptchService.save(bidPrjDsptchDTO);
        return ResponseEntity
            .created(new URI("/api/bid-prj-dsptches/" + bidPrjDsptchDTO.getWkut() + bidPrjDsptchDTO.getPrjno() + bidPrjDsptchDTO.getDspNo()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, bidPrjDsptchDTO.getWkut() + bidPrjDsptchDTO.getPrjno() + bidPrjDsptchDTO.getDspNo()))
            .body(result);
    }

    /**
     * {@code PUT  /bid-prj-dsptches/updateOne} : Updates an existing bidPrjDsptch.
     *
     * @param bidPrjDsptchDTO the bidPrjDsptchDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidPrjDsptchDTO,
     * or with status {@code 400 (Bad Request)} if the bidPrjDsptchDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bidPrjDsptchDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bid-prj-dsptches/updateOne")
    public ResponseEntity<BidPrjDsptchDTO> updateBidPrjDsptch(
        @Valid @RequestBody BidPrjDsptchDTO bidPrjDsptchDTO
    ) throws URISyntaxException {
        log.debug("REST request to update BidPrjDsptch : {}", bidPrjDsptchDTO);
        BidPrjDsptchPrimaryKey bidPrjDsptchPrimaryKey = new BidPrjDsptchPrimaryKey(bidPrjDsptchDTO.getWkut(), bidPrjDsptchDTO.getPrjno(), bidPrjDsptchDTO.getDspNo());
        if (bidPrjDsptchDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(bidPrjDsptchPrimaryKey, bidPrjDsptchDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidPrjDsptchRepository.existsById(bidPrjDsptchPrimaryKey)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        String fromStatus = "modify";

        //       後端驗證
        List<String> validResult = bidPrjDsptchService.validData(bidPrjDsptchDTO, fromStatus);
        if (validResult.size() > 0) {
            throw new BidValidationFailException("bidValidError", "資料檢核錯誤", validResult.toArray(new String[0]));
        }

        BidPrjDsptchDTO result = bidPrjDsptchService.save(bidPrjDsptchDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidPrjDsptchDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /bid-prj-dsptches/:id} : Partial updates given fields of an existing bidPrjDsptch, field will ignore if it is null
     *
     * @param bidPrjDsptchDTO the bidPrjDsptchDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidPrjDsptchDTO,
     * or with status {@code 400 (Bad Request)} if the bidPrjDsptchDTO is not valid,
     * or with status {@code 404 (Not Found)} if the bidPrjDsptchDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the bidPrjDsptchDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/bid-prj-dsptches/partialUpdate", consumes = {"application/json", "application/merge-patch+json"})
    public ResponseEntity<BidPrjDsptchDTO> partialUpdateBidPrjDsptch(
        @NotNull @RequestBody BidPrjDsptchDTO bidPrjDsptchDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update BidPrjDsptch partially : {}", bidPrjDsptchDTO);
        BidPrjDsptchPrimaryKey bidPrjDsptchPrimaryKey = new BidPrjDsptchPrimaryKey(bidPrjDsptchDTO.getWkut(), bidPrjDsptchDTO.getPrjno(), bidPrjDsptchDTO.getDspNo());

        if (bidPrjDsptchDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(bidPrjDsptchPrimaryKey, bidPrjDsptchDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidPrjDsptchRepository.existsById(bidPrjDsptchPrimaryKey)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BidPrjDsptchDTO> result = bidPrjDsptchService.partialUpdate(bidPrjDsptchDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidPrjDsptchDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /bid-prj-dsptches} : get all the bidPrjDsptches.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bidPrjDsptches in body.
     */
    @GetMapping("/bid-prj-dsptches")
    public List<BidPrjDsptchDTO> getAllBidPrjDsptches() {
        log.debug("REST request to get all BidPrjDsptches");
        return bidPrjDsptchService.findAll();
    }

    /**
     * {@code POST  /bid-prj-dsptches/getOne} : get the "id" bidPrjDsptch.
     *
     * @param id the id of the bidPrjDsptchDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bidPrjDsptchDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/bid-prj-dsptches/getOne")
    public ResponseEntity<BidPrjDsptchDTO> getBidPrjDsptch(@RequestBody BidPrjDsptchPrimaryKey id) {
        log.debug("REST request to get BidPrjDsptch : {}", id);
        Optional<BidPrjDsptchDTO> bidPrjDsptchDTO = bidPrjDsptchService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bidPrjDsptchDTO);
    }

    /**
     * {@code DELETE  /bid-prj-dsptches/:id} : delete the "id" bidPrjDsptch.
     *
     * @param id the id of the bidPrjDsptchDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bid-prj-dsptches/{id}")
    public ResponseEntity<Void> deleteBidPrjDsptch(@PathVariable BidPrjDsptchPrimaryKey id) {
        log.debug("REST request to delete BidPrjDsptch : {}", id);
        bidPrjDsptchService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * find BidPrjDsptchDTOs By Criteria
     */
    @PostMapping("/bid-prj-dsptches/criteria")
    public Page<BidPrjDsptchDTO> getBidPrjStcByCriteria(@RequestBody BidPrjDsptchCriteria criteria) {
        log.debug("REST request to get BidPrjStcDTO Page by criteria : {}", criteria);
        return bidPrjDsptchService.findAllBidPrjDsptchByCriteria(criteria);
    }

    // 產生開口合約表
    @PostMapping("/bid-prj-dsptches/BidAae020s01")
    public ResponseEntity<byte[]> bidAae020s01(@RequestBody BidPrjDsptchCriteria criteria) throws IOException {
        log.debug("REST request to get pdf : {}", criteria);

        // 基本資料
        BidProjectPrimaryKey bidProjectPrimaryKey = new BidProjectPrimaryKey();
        bidProjectPrimaryKey.setWkut(criteria.getWkut());
        bidProjectPrimaryKey.setPrjno(criteria.getPrjno());

        BidProjectDTO bidProjectDTO = bidProjectService.findOne(bidProjectPrimaryKey).orElse(null);

        // wkut
        BidUnit10DTO wkutDTO = bidUnit10Service.findByCode(bidProjectDTO.getWkut()).orElse(null);
        // bosCode
        BidUnit10DTO bosCodeDTO = bidUnit10Service.findByCode(bidProjectDTO.getBosCode()).orElse(null);
        // depCode
        BidUnit10DTO depCodeDTO = bidUnit10Service.findByCode(bidProjectDTO.getDepCode()).orElse(null);
        // plnCode
        BidUnit10DTO plnCodeDTO = bidUnit10Service.findByCode(bidProjectDTO.getPlnCode()).orElse(null);

        // 變更設計
        BidPrjModifyCriteria bidPrjModifyCriteria = new BidPrjModifyCriteria();
        bidPrjModifyCriteria.setWkut(criteria.getWkut());
        bidPrjModifyCriteria.setPrjno(criteria.getPrjno());

        Page<BidPrjModifyDTO> bidPrjModifyDTOPage = bidPrjModifyService.findProjectAllModify(bidPrjModifyCriteria);
        List<BidPrjModifyDTO> bidPrjModifyDTOList = bidPrjModifyDTOPage.getContent();


        // 開口合約派工
        Page<BidPrjDsptchDTO> bidPrjDsptchDTOPage = bidPrjDsptchService.findAllBidPrjDsptchByCriteria(criteria);
        List<BidPrjDsptchDTO> bidPrjDsptchDTOList = bidPrjDsptchDTOPage.getContent();


        // 匯集資料
        BidAae020s01a bidAae020s01a = new BidAae020s01a();
        bidAae020s01a.setBidPrjModifyDTOList(bidPrjModifyDTOList);
        bidAae020s01a.setBidPrjDsptchDTOList(bidPrjDsptchDTOList);
        bidAae020s01a.setBidProjectDTO(bidProjectDTO);
        bidAae020s01a.setWkutDTO(wkutDTO);
        bidAae020s01a.setBosCodeDTO(bosCodeDTO);
        bidAae020s01a.setDepCodeDTO(depCodeDTO);
        bidAae020s01a.setPlnCodeDTO(plnCodeDTO);

        // 產生報表
        byte[] pdfbyte = reportService.printReport(bidAae020s01a);
        return ResponseEntity.ok(pdfbyte);
    }

    // 施工通知單
    @PostMapping("/bid-prj-dsptches/BidAae020s02")
    public ResponseEntity<byte[]> bidAae020s02(@RequestBody BidPrjDsptchCriteria criteria) throws IOException {
        log.debug("REST request to get pdf : {}", criteria);

        // 基本資料
        BidProjectPrimaryKey bidProjectPrimaryKey = new BidProjectPrimaryKey();
        bidProjectPrimaryKey.setWkut(criteria.getWkut());
        bidProjectPrimaryKey.setPrjno(criteria.getPrjno());

        BidProjectDTO bidProjectDTO = bidProjectService.findOne(bidProjectPrimaryKey).orElse(null);

        // 開口合約派工
        BidPrjDsptchPrimaryKey bidPrjDsptchPrimaryKey = new BidPrjDsptchPrimaryKey(criteria.getWkut(),criteria.getPrjno(),criteria.getDspNo());
        BidPrjDsptchDTO bidPrjDsptchDTO = bidPrjDsptchService.findOne(bidPrjDsptchPrimaryKey).orElse(null);

//        // 承攬廠商
        BidProjectUnitPrimaryKey bidProjectUnitPrimaryKey = new BidProjectUnitPrimaryKey(criteria.getWkut(),criteria.getPrjno(),"3","1");
        BidProjectUnitDTO ccutCodeDTO = bidProjectUnitService.findOne(bidProjectUnitPrimaryKey).orElse(null);
        BidUnit10DTO ccutNameDTO =  bidUnit10Service.findByCode(ccutCodeDTO.getCcut()).orElse(null);
//
//        // 監造單位
        BidProjectUnitPrimaryKey bidProjectUnitPrimaryKey2 = new BidProjectUnitPrimaryKey(criteria.getWkut(),criteria.getPrjno(),"2","1");
        BidProjectUnitDTO scutCodeDTO = bidProjectUnitService.findOne(bidProjectUnitPrimaryKey2).orElse(null);
        BidUnit10DTO scutNameDTO =  bidUnit10Service.findByCode(scutCodeDTO.getScut()).orElse(null);


        // 匯集資料
        BidAae020s02 bidAae020s02 = new BidAae020s02();
        bidAae020s02.setBidPrjDsptchDTO(bidPrjDsptchDTO);
        bidAae020s02.setBidProjectDTO(bidProjectDTO);
        bidAae020s02.setCcutNameDTO(ccutNameDTO);
        bidAae020s02.setScutNameDTO(scutNameDTO);

        // 產生報表
        byte[] pdfbyte = reportService.printReport(bidAae020s02);
        return ResponseEntity.ok(pdfbyte);
    }

}
