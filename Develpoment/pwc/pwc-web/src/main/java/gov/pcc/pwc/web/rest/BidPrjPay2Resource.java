package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.common.errors.BidValidationFailException;
import gov.pcc.pwc.domain.*;
import gov.pcc.pwc.report.bid.bidAae023.BidAae023;
import gov.pcc.pwc.repository.BidPrjPay2Repository;
import gov.pcc.pwc.service.*;
import gov.pcc.pwc.service.criteria.*;
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
 * REST controller for managing {@link gov.pcc.pwc.domain.BidPrjPay2}.
 */
@RestController
@RequestMapping("/api")
public class BidPrjPay2Resource {

    private final Logger log = LoggerFactory.getLogger(BidPrjPay2Resource.class);

    private static final String ENTITY_NAME = "bidPrjPay2";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BidPrjPay2Service bidPrjPay2Service;

    private final BidPrjPay2Repository bidPrjPay2Repository;

    private final BidProjectService bidProjectService;

    private final BidUnit10Service bidUnit10Service;

    private final BidProjectUnitService bidProjectUnitService;

    private final BidPrjSur2Service bidPrjSur2Service;

    private final BidMdlbasService bidMdlbasService;

    private final BidPrjMonthService bidPrjMonthService;



    // 產生報表檔
    private final ReportService reportService;

    public BidPrjPay2Resource(BidPrjPay2Service bidPrjPay2Service, BidPrjPay2Repository bidPrjPay2Repository,
                              BidProjectService bidProjectService, ReportService reportService,BidUnit10Service bidUnit10Service
    ,BidProjectUnitService bidProjectUnitService,BidPrjSur2Service bidPrjSur2Service, BidMdlbasService bidMdlbasService,BidPrjMonthService bidPrjMonthService) {
        this.bidPrjPay2Service = bidPrjPay2Service;
        this.bidPrjPay2Repository = bidPrjPay2Repository;
        this.bidProjectService = bidProjectService;
        this.reportService = reportService;
        this.bidUnit10Service = bidUnit10Service;
        this.bidProjectUnitService = bidProjectUnitService;
        this.bidPrjSur2Service = bidPrjSur2Service;
        this.bidMdlbasService = bidMdlbasService;
        this.bidPrjMonthService = bidPrjMonthService;



    }

    /**
     * {@code POST  /bid-prj-pay-2-s} : Create a new bidPrjPay2.
     *
     * @param bidPrjPay2DTO the bidPrjPay2DTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bidPrjPay2DTO, or with status {@code 400 (Bad Request)} if the bidPrjPay2 has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bid-prj-pay-2-s")
    public ResponseEntity<BidPrjPay2DTO> createBidPrjPay2(@Valid @RequestBody BidPrjPay2DTO bidPrjPay2DTO) throws URISyntaxException {
        log.debug("REST request to save BidPrjPay2 : {}", bidPrjPay2DTO);

        String formStatus = "create";
//        後端驗證
        List<String> validResult = bidPrjPay2Service.validData(bidPrjPay2DTO , formStatus);
        if(validResult.size()>0){
            throw new BidValidationFailException("bidValidError","資料檢核錯誤" ,validResult.toArray(new String[0]));
        }

        BidPrjPay2DTO result = bidPrjPay2Service.save(bidPrjPay2DTO);
        return ResponseEntity
            .created(new URI("/api/bid-prj-pay-2-s/" +result.getWkut()+result.getPrjno()+result.getYear()+result.getMonth()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bid-prj-pay-2-s/:id} : Updates an existing bidPrjPay2.
     *
     * @param bidPrjPay2DTO the bidPrjPay2DTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidPrjPay2DTO,
     * or with status {@code 400 (Bad Request)} if the bidPrjPay2DTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bidPrjPay2DTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bid-prj-pay-2-s/updateOne")
    public ResponseEntity<BidPrjPay2DTO> updateBidPrjPay2(
        @Valid @RequestBody BidPrjPay2DTO bidPrjPay2DTO
    ) throws URISyntaxException {
        log.debug("REST request to update BidPrjPay2 : {}", bidPrjPay2DTO);
        BidPrjPay2PrimaryKey bidPrjPay2PrimaryKey = new BidPrjPay2PrimaryKey(bidPrjPay2DTO.getWkut(),bidPrjPay2DTO.getPrjno(),bidPrjPay2DTO.getYear(),bidPrjPay2DTO.getMonth());
        if (bidPrjPay2DTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(bidPrjPay2PrimaryKey, bidPrjPay2DTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidPrjPay2Repository.existsById(bidPrjPay2PrimaryKey) ){
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        String formStatus = "modify";
//        後端驗證
        List<String> validResult = bidPrjPay2Service.validData(bidPrjPay2DTO , formStatus);
        if(validResult.size()>0){
            throw new BidValidationFailException("bidValidError","資料檢核錯誤" ,validResult.toArray(new String[0]));
        }

        BidPrjPay2DTO result = bidPrjPay2Service.save(bidPrjPay2DTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidPrjPay2DTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /bid-prj-pay-2-s/:id} : Partial updates given fields of an existing bidPrjPay2, field will ignore if it is null
     *
     * @param bidPrjPay2DTO the bidPrjPay2DTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidPrjPay2DTO,
     * or with status {@code 400 (Bad Request)} if the bidPrjPay2DTO is not valid,
     * or with status {@code 404 (Not Found)} if the bidPrjPay2DTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the bidPrjPay2DTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/bid-prj-pay-2-s/partialUpdate", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<BidPrjPay2DTO> partialUpdateBidPrjPay2(
        @NotNull @RequestBody BidPrjPay2DTO bidPrjPay2DTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update BidPrjPay2 partially : {}", bidPrjPay2DTO);
        BidPrjPay2PrimaryKey bidPrjPay2PrimaryKey = new BidPrjPay2PrimaryKey(bidPrjPay2DTO.getWkut(),bidPrjPay2DTO.getPrjno(),bidPrjPay2DTO.getYear(),bidPrjPay2DTO.getMonth());

        if (bidPrjPay2DTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(bidPrjPay2PrimaryKey, bidPrjPay2DTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidPrjPay2Repository.existsById(bidPrjPay2PrimaryKey)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BidPrjPay2DTO> result = bidPrjPay2Service.partialUpdate(bidPrjPay2DTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidPrjPay2DTO.getId().toString())
        );
    }

    /**
     * {@code GET  /bid-prj-pay-2-s} : get all the bidPrjPay2s.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bidPrjPay2s in body.
     */
    @GetMapping("/bid-prj-pay-2-s")
    public List<BidPrjPay2DTO> getAllBidPrjPay2s() {
        log.debug("REST request to get all BidPrjPay2s");
        return bidPrjPay2Service.findAll();
    }

    /**
     * {@code POST  /bid-prj-pay-2-s/getOne} : get the "id" bidPrjPay2.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bidPrjPay2DTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/bid-prj-pay-2-s/getOne")
    public ResponseEntity<BidPrjPay2DTO> getBidPrjPay2(@RequestBody BidPrjPay2PrimaryKey id) {
        log.debug("REST request to get BidPrjPay2 : {}", id);
        Optional<BidPrjPay2DTO> bidPrjPay2DTO = bidPrjPay2Service.findOne(id);
        return ResponseUtil.wrapOrNotFound(bidPrjPay2DTO);
    }

    /**
     * {@code DELETE  /bid-prj-pay-2-s/:id} : delete the "id" bidPrjPay2.
     *
     * @param id the id of the bidPrjPay2DTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bid-prj-pay-2-s/{id}")
    public ResponseEntity<Void> deleteBidPrjPay2(@PathVariable BidPrjPay2PrimaryKey id) {
        log.debug("REST request to delete BidPrjPay2 : {}", id);
        bidPrjPay2Service.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * GET bidPrjPay2DTO by criteria
     *
     * */
    @PostMapping("/bid-prj-pay-2-s/criteria")
    public Page<BidPrjPay2DTO> getBidPrjPay2ByCriteria(@Valid @RequestBody BidPrjPay2Criteria criteria){
        log.debug("REST request to get BidPrjPay2 : {}",criteria);
        return bidPrjPay2Service.findAllBidPrjPay2ByCriteria(criteria);
    }


    // 產生復建工程發包、請撥款及執行狀況表
    @PostMapping("/bid-prj-pay-2-s/BidAae023")
    public  ResponseEntity<byte[]> bidAae006(@RequestBody BidPrjPay2Criteria criteria) throws IOException {
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

        BidProjectUnitCriteria dsctAndScctCriteria =new BidProjectUnitCriteria();
        dsctAndScctCriteria.setWkut(criteria.getWkut());
        dsctAndScctCriteria.setPrjno(criteria.getPrjno());
        dsctAndScctCriteria.setSortBy(new String[]{"wkut"});

        // dsct
        Page<BidProjectUnitDTO> dsctPage = bidProjectUnitService.getBidProjectUnitDTOPageByCriteria(dsctAndScctCriteria);
        List<BidProjectUnitDTO> dsctLsit = dsctPage.getContent();

        // scct
        Page<BidProjectUnitDTO> scctPage = bidProjectUnitService.getBidProjectUnitDTOPageByCriteria(dsctAndScctCriteria);
        List<BidProjectUnitDTO> scctList = scctPage.getContent();

        // 行政院核定復建項目 SUR2
        BidPrjSur2Criteria sur2Criteria = new BidPrjSur2Criteria();
        sur2Criteria.setWkut(criteria.getWkut());
        sur2Criteria.setPrjno(criteria.getPrjno());
        sur2Criteria.setSortBy(new String[]{"wkut"});
        Page<BidPrjSur2DTO> sur2DTOPage = bidPrjSur2Service.findAllByCriteria(sur2Criteria);
        List<BidPrjSur2DTO> sur2DTOList = sur2DTOPage.getContent();

        // 歷次撥款狀況 PAY2

        Page<BidPrjPay2DTO> pay2DTOPage = bidPrjPay2Service.findAllBidPrjPay2ByCriteria(criteria);
        List<BidPrjPay2DTO> pay2DTOList = pay2DTOPage.getContent();

        // 各位份累積執行狀況
        BidPrjMonthCriteria bidPrjMonthCriteria = new BidPrjMonthCriteria();
        bidPrjMonthCriteria.setWkut(criteria.getWkut());
        bidPrjMonthCriteria.setPrjno(criteria.getPrjno());
        bidPrjMonthCriteria.setSortBy(new String[]{"syr"});
        Page<BidPrjMonthDTO> monthDTOPage = bidPrjMonthService.findAllBidPrjMonthByCriteria(bidPrjMonthCriteria);
        List<BidPrjMonthDTO> monthDTOList = monthDTOPage.getContent();

        // 提供產生報表相關資料
        BidAae023 bidAae023 = new BidAae023();
        bidAae023.setBidProjectDTO(bidProjectDTO);
        bidAae023.setWkutDTO(wkutDTO);
        bidAae023.setBosCodeDTO(bosCodeDTO);
        bidAae023.setDepCodeDTO(depCodeDTO);
        bidAae023.setDsctLsit(dsctLsit);
        bidAae023.setScctList(scctList);
        bidAae023.setSur2DTOList(sur2DTOList);
        bidAae023.setPay2DTOList(pay2DTOList);
        bidAae023.setMonthDTOList(monthDTOList);

        // 產生報表
        byte[] pdfbyte = reportService.printReport(bidAae023);
        return ResponseEntity.ok(pdfbyte);

    }

}
