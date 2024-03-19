package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.common.web.ReportMediaType;
import gov.pcc.pwc.domain.BidPrjFrwkrPrimaryKey;
import gov.pcc.pwc.domain.BidProjectPrimaryKey;
import gov.pcc.pwc.report.bid.bidaae021.BidAae021;
import gov.pcc.pwc.repository.BidPrjFrwkrRepository;
import gov.pcc.pwc.service.*;
import gov.pcc.pwc.service.criteria.BidPrjEnviCriteria;
import gov.pcc.pwc.service.criteria.BidPrjFrwkrCriteria;
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
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link gov.pcc.pwc.domain.BidPrjFrwkr}.
 */
@RestController
@RequestMapping("/api")
public class BidPrjFrwkrResource {

    private final Logger log = LoggerFactory.getLogger(BidPrjFrwkrResource.class);

    private static final String ENTITY_NAME = "bidPrjFrwkr";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BidPrjFrwkrService bidPrjFrwkrService;

    private final BidPrjFrwkrRepository bidPrjFrwkrRepository;

    private final VwBidProjectService vwBidProjectService;

    private final BidProjectService bidProjectService;

    private final BidProjectUnitService bidProjectUnitService;

    private final BidUnit10Service bidUnit10Service;

    private final BidPlaceService bidPlaceService;

    private final AdmSysCodeService admSysCodeService;

    // 產生報表檔
    private final ReportService reportService;


    public BidPrjFrwkrResource(BidPrjFrwkrService bidPrjFrwkrService, BidPrjFrwkrRepository bidPrjFrwkrRepository, ReportService reportService, VwBidProjectService vwBidProjectService, BidProjectService bidProjectService, BidProjectUnitService bidProjectUnitService, BidUnit10Service bidUnit10Service, BidPlaceService bidPlaceService, AdmSysCodeService admSysCodeService) {
        this.bidPrjFrwkrService = bidPrjFrwkrService;
        this.bidPrjFrwkrRepository = bidPrjFrwkrRepository;
        this.reportService = reportService;
        this.vwBidProjectService = vwBidProjectService;
        this.bidProjectService = bidProjectService;
        this.bidProjectUnitService = bidProjectUnitService;
        this.bidUnit10Service = bidUnit10Service;
        this.bidPlaceService = bidPlaceService; //地址
        this.admSysCodeService = admSysCodeService;
    }

    /**
     * {@code POST  /bid-prj-frwkrs} : Create a new bidPrjFrwkr.
     *
     * @param bidPrjFrwkrDTO the bidPrjFrwkrDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bidPrjFrwkrDTO, or with status {@code 400 (Bad Request)} if the bidPrjFrwkr has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bid-prj-frwkrs")
    public ResponseEntity<BidPrjFrwkrDTO> createBidPrjFrwkr(@Valid @RequestBody BidPrjFrwkrDTO bidPrjFrwkrDTO) throws URISyntaxException {
        log.debug("REST request to save BidPrjFrwkr : {}", bidPrjFrwkrDTO);
//        if (bidPrjFrwkrDTO.getId() != null) {
//            throw new BadRequestAlertException("A new bidPrjFrwkr cannot already have an ID", ENTITY_NAME, "idexists");
//        }
        BidPrjFrwkrDTO result = bidPrjFrwkrService.save(bidPrjFrwkrDTO);
        return ResponseEntity
//            .created(new URI("/api/bid-prj-frwkrs/" + result.getId()))
//            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
//            .body(result);
            .created(new URI("/api/bid-prj-safes/" +result.getId().getWkut()+"/"+ result.getId().getPrjno()+"/"+ result.getId().getSdate()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bid-prj-frwkrs/:id} : Updates an existing bidPrjFrwkr.
     *
     * @param bidPrjFrwkrDTO the id of the bidPrjFrwkrDTO to save.
     * @param bidPrjFrwkrDTO the bidPrjFrwkrDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidPrjFrwkrDTO,
     * or with status {@code 400 (Bad Request)} if the bidPrjFrwkrDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bidPrjFrwkrDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bid-prj-frwkrs/")
    public ResponseEntity<BidPrjFrwkrDTO> updateBidPrjFrwkr(
//        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody BidPrjFrwkrDTO bidPrjFrwkrDTO
    ) throws URISyntaxException {
        BidPrjFrwkrPrimaryKey bidPrjFrwkrPrimaryKey = new BidPrjFrwkrPrimaryKey(bidPrjFrwkrDTO.getWkut(), bidPrjFrwkrDTO.getPrjno(), bidPrjFrwkrDTO.getSdate());
        log.debug("REST request to update BidPrjFrwkr : {}", bidPrjFrwkrDTO);
        if (bidPrjFrwkrDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(bidPrjFrwkrPrimaryKey, bidPrjFrwkrDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidPrjFrwkrRepository.existsById(bidPrjFrwkrPrimaryKey)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        BidPrjFrwkrDTO result = bidPrjFrwkrService.save(bidPrjFrwkrDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidPrjFrwkrDTO.getId().toString()))
            .body(result);

    }

    /**
     * {@code PATCH  /bid-prj-frwkrs/:id} : Partial updates given fields of an existing bidPrjFrwkr, field will ignore if it is null
     *
     * @param bidPrjFrwkrDTO the id of the bidPrjFrwkrDTO to save.
     * @param bidPrjFrwkrDTO the bidPrjFrwkrDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidPrjFrwkrDTO,
     * or with status {@code 400 (Bad Request)} if the bidPrjFrwkrDTO is not valid,
     * or with status {@code 404 (Not Found)} if the bidPrjFrwkrDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the bidPrjFrwkrDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/bid-prj-frwkrs", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<BidPrjFrwkrDTO> partialUpdateBidPrjFrwkr(
//        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody BidPrjFrwkrDTO bidPrjFrwkrDTO
    ) throws URISyntaxException {
        BidPrjFrwkrPrimaryKey bidPrjFrwkrPrimaryKey = new BidPrjFrwkrPrimaryKey(bidPrjFrwkrDTO.getWkut(), bidPrjFrwkrDTO.getPrjno(), bidPrjFrwkrDTO.getSdate());
        log.debug("REST request to partial update BidPrjFrwkr partially : {}", bidPrjFrwkrDTO);
        if (bidPrjFrwkrDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(bidPrjFrwkrPrimaryKey, bidPrjFrwkrDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidPrjFrwkrRepository.existsById(bidPrjFrwkrPrimaryKey)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BidPrjFrwkrDTO> result = bidPrjFrwkrService.partialUpdate(bidPrjFrwkrDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidPrjFrwkrDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /bid-prj-frwkrs} : get all the bidPrjFrwkrs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bidPrjFrwkrs in body.
     */
    @GetMapping("/bid-prj-frwkrs")
    public List<BidPrjFrwkrDTO> getAllBidPrjFrwkrs() {
        log.debug("REST request to get all BidPrjFrwkrs");
        return bidPrjFrwkrService.findAll();
    }

    /**
     * {@code GET  /bid-prj-frwkrs/:id} : get the "id" bidPrjFrwkr.
     *
     * @param bidPrjFrwkrPrimaryKey the id of the bidPrjFrwkrDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bidPrjFrwkrDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/bid-prj-frwkrs/getOne")
    public ResponseEntity<BidPrjFrwkrDTO> getBidPrjFrwkr(@PathVariable BidPrjFrwkrPrimaryKey bidPrjFrwkrPrimaryKey) {
        log.debug("REST request to get BidPrjFrwkr : {}", bidPrjFrwkrPrimaryKey);
        Optional<BidPrjFrwkrDTO> bidPrjFrwkrDTO = bidPrjFrwkrService.findOne(bidPrjFrwkrPrimaryKey);
        return ResponseUtil.wrapOrNotFound(bidPrjFrwkrDTO);
    }

    /**
     * {@code DELETE  /bid-prj-frwkrs/:id} : delete the "id" bidPrjFrwkr.
     *
     * @param bidPrjFrwkrPrimaryKey the id of the bidPrjFrwkrDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bid-prj-frwkrs")
    public ResponseEntity<Void> deleteBidPrjFrwkr(@RequestBody BidPrjFrwkrPrimaryKey bidPrjFrwkrPrimaryKey) {
        log.debug("REST request to delete BidPrjFrwkr : {}", bidPrjFrwkrPrimaryKey);
        bidPrjFrwkrService.delete(bidPrjFrwkrPrimaryKey);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, bidPrjFrwkrPrimaryKey.toString()))
            .build();
    }

    /**
     * {@code POST  /vid-prj-frwkr/criteria-jpa} : get BidPrjFrwkrCriteria by criteria.
     *
     * @param criteria the criteria of the bidUnit10DTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bidUnit10DTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/bid-prj-frwkr/criteria-jpa")
    public Page<BidPrjFrwkrDTO> getBidPrjFrwkrByCriteria(@Valid @RequestBody BidPrjFrwkrCriteria criteria) throws InterruptedException {
        log.debug("REST request to get BidPrjFrwkrDTO : {}", criteria);
        return bidPrjFrwkrService.findByCriteria(criteria);
    }



    // 產生外籍勞工表 bid-aae-021
    @PostMapping(path="/bid-prj-frwkr/bidAae021",produces = ReportMediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> bidAae021(@RequestBody BidPrjEnviCriteria criteria) throws IOException {
        log.debug("REST request to get pdf : {}", criteria);


        BidProjectPrimaryKey bidProjectPrimaryKey = new BidProjectPrimaryKey(criteria.getWkut(),criteria.getPrjno());
        // find VwBidProjectDTO
        VwBidProjectDTO vwBidProjectDTO = vwBidProjectService.findOne(bidProjectPrimaryKey).get();

        BidPrjFrwkrPrimaryKey bidPrjFrwkrPrimaryKey = new BidPrjFrwkrPrimaryKey(criteria.getWkut(),criteria.getPrjno(),criteria.getSdate());
        // find BidPrjFrwkrDTO
        BidPrjFrwkrDTO bidPrjFrwkrDTO = bidPrjFrwkrService.findOne(bidPrjFrwkrPrimaryKey).get();

        // find BidProjectDTO
        BidProjectDTO bidProjectDTO = bidProjectService.findOne(bidProjectPrimaryKey).get();

        // find BidUnit10DTO
        BidUnit10DTO bidUnit10DTO = bidUnit10Service.findOne(criteria.getWkut()).get();

        // find BidPlaceDTO
//        BidPlaceDTO bidPlaceDTO = BidPlaceService.findOne(criteria.getWkut()).get();


        // wkutName
        BidUnit10DTO wkutDTO = bidUnit10Service.findByCode(bidProjectDTO.getWkut()).orElse(null);

        String ccutNum ="";
        String scutNum ="";

        // 找承包商和監造單位
        List<BidProjectUnitDTO> bidProjectUnitCriteria = bidProjectUnitService.findBidCcutByKeyWorld(criteria.getWkut(),criteria.getPrjno());
        for(BidProjectUnitDTO item :bidProjectUnitCriteria){
            if(item.getType().equals("3") ){
                ccutNum = item.getCcut();
            }else if(item.getType().equals("2")){
                scutNum = item.getScut();

            }
        }
        String ccut = "";
        String scut = "";
        // 按照號碼找名稱
        if(ccutNum != null && ccutNum.length() > 0){
            BidUnit10DTO bidUnit10DTO1 = bidUnit10Service.findByCode(ccutNum).get();
            ccut = bidUnit10DTO1.getName();

        }
        if(scutNum != null && scutNum.length() > 0) {
            BidUnit10DTO bidUnit10DTO2 = bidUnit10Service.findByCode(scutNum).get();
            scut = bidUnit10DTO2.getName();
        }


//        // 工程類別
//        String typeCode = "";
//        String typeCodeNum = vwBidProjectDTO.getTypeCode();
//        if(typeCodeNum != null && typeCodeNum.length() > 0) {
//            AdmSysCodeDTO admSysCodeDTO = admSysCodeService.findByPk("BID", "BID_003", typeCodeNum);
//            typeCode = admSysCodeDTO.getValue();
//        }

        // 縣市鄉鎮
        String place ="";
        String placeNum = vwBidProjectDTO.getPlaceCode();
        if(placeNum != null && placeNum.length() > 0) {
            List<BidPlaceDTO> bidPlaceDTO = bidPlaceService.findMapInfoByPlaceCode(placeNum);
            for(BidPlaceDTO item: bidPlaceDTO){
                place = item.getPlaceName();
            }
        }else {
            place = "(未填)";
        }

//        // 管轄單位
//        String ctUnit ="";
//        String ctUnitNum = vwBidProjectDTO.getTypeCode();
//        if(ctUnitNum != null && ctUnitNum.length() > 0 ) {
//            AdmSysCodeDTO admSysCodeDTO2 = admSysCodeService.findByPk("BID", "BID_017", ctUnitNum);
//            ctUnit = admSysCodeDTO2.getValue();
//        }

        // 計畫別
        String frKnd1 ="";
        String frKnd1Num = bidPrjFrwkrDTO.getFrKnd1();
        if(frKnd1Num != null && frKnd1Num.length() > 0 ) {
            AdmSysCodeDTO admSysCodeDTO3 = admSysCodeService.findByPk("BID", "BID_039",frKnd1Num);
            frKnd1 = admSysCodeDTO3.getValue();
        }


            // 特殊性
        String frKnd2 ="";
        String frKnd2Num = bidPrjFrwkrDTO.getFrKnd2();
        if(frKnd2Num != null && frKnd2Num.length() > 0 ) {
            AdmSysCodeDTO admSysCodeDTO4 = admSysCodeService.findByPk("BID", "BID_040", frKnd2Num);
            frKnd2 = admSysCodeDTO4.getValue();
        }

        // 特殊性
        String frKnd3 ="";
        String frKnd3Num = bidPrjFrwkrDTO.getFrKnd3();
            if(frKnd3Num != null && frKnd3Num.length() > 0 ) {
                AdmSysCodeDTO admSysCodeDTO5 = admSysCodeService.findByPk("BID", "BID_041", frKnd3Num);
                frKnd3 = admSysCodeDTO5.getValue();
            }





        BidAae021 bidAae021 = new BidAae021();
        bidAae021.setVwBidProjectDTO(vwBidProjectDTO);
        bidAae021.setBidProjectDTO(bidProjectDTO);
        bidAae021.setBidPrjFrwkrDTO(bidPrjFrwkrDTO);
        bidAae021.setBidUnit10DTO(bidUnit10DTO);
        bidAae021.setCcut(ccut);
        bidAae021.setScut(scut);
        bidAae021.setFrKnd1(frKnd1);
        bidAae021.setFrKnd2(frKnd2);
        bidAae021.setFrKnd3(frKnd3);
        bidAae021.setPlace(place);
//        bidAae021.setBidUnit10DTO(bidUnit10DTO);


        byte[] pdfbyte = reportService.printReport(bidAae021);
        return ResponseEntity.ok(pdfbyte);


    }
}
