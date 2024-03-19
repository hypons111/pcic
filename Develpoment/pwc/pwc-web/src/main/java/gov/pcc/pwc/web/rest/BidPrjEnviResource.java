package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.common.errors.BidValidationFailException;
import gov.pcc.pwc.common.web.ReportMediaType;
import gov.pcc.pwc.domain.BidPrjEnviPrimaryKey;
import gov.pcc.pwc.domain.BidPrjEquipPrimaryKey;
import gov.pcc.pwc.domain.BidPrjSafePrimaryKey;
import gov.pcc.pwc.domain.BidProjectPrimaryKey;
import gov.pcc.pwc.report.bid.bidAae006.BidAae006;
import gov.pcc.pwc.report.bid.bidaae007.BidAae007;
import gov.pcc.pwc.repository.BidPrjEnviRepository;
import gov.pcc.pwc.service.*;
import gov.pcc.pwc.service.criteria.AdmSysCodeCriteria;
import gov.pcc.pwc.service.criteria.BidPrjEnviCriteria;
import gov.pcc.pwc.service.criteria.BidPrjSafeCriteria;
import gov.pcc.pwc.service.criteria.BidProjectUnitCriteria;
import gov.pcc.pwc.service.dto.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.TimeZone;
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
 * REST controller for managing {@link gov.pcc.pwc.domain.BidPrjEnvi}.
 */
@RestController
@RequestMapping("/api")
public class BidPrjEnviResource {

    private final Logger log = LoggerFactory.getLogger(BidPrjEnviResource.class);

    private static final String ENTITY_NAME = "bidPrjEnvi";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BidPrjEnviService bidPrjEnviService;

    private final BidPrjEnviRepository bidPrjEnviRepository;

    private final BidProjectService bidProjectService;

    private final VwBidProjectService vwBidProjectService;

    private final BidProjectUnitService bidProjectUnitService;

    private final BidUnit10Service bidUnit10Service;

    private AdmSysCodeService admSysCodeService;

    private BidPlaceService bidPlaceService;

    // 產生報表檔
    private final ReportService reportService;

    public BidPrjEnviResource(BidPrjEnviService bidPrjEnviService, BidPrjEnviRepository bidPrjEnviRepository, BidProjectService bidProjectService, ReportService reportService,VwBidProjectService vwBidProjectService,BidProjectUnitService bidProjectUnitService,BidUnit10Service bidUnit10Service,AdmSysCodeService admSysCodeService,BidPlaceService bidPlaceService) {
        this.bidPrjEnviService = bidPrjEnviService;
        this.bidPrjEnviRepository = bidPrjEnviRepository;
        this.bidProjectService = bidProjectService;
        this.vwBidProjectService = vwBidProjectService;
        this.reportService = reportService;
        this.bidProjectUnitService = bidProjectUnitService;
        this.bidUnit10Service = bidUnit10Service;
        this.admSysCodeService = admSysCodeService; // 下拉式選單
        this.bidPlaceService = bidPlaceService; //地址
    }

    /**
     * {@code POST  /bid-prj-envis} : Create a new bidPrjEnvi.
     *
     * @param bidPrjEnviDTO the bidPrjEnviDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bidPrjEnviDTO, or with status {@code 400 (Bad Request)} if the bidPrjEnvi has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bid-prj-envis")
    public ResponseEntity<BidPrjEnviDTO> createBidPrjEnvi(@Valid @RequestBody BidPrjEnviDTO bidPrjEnviDTO) throws URISyntaxException {
        log.debug("REST request to save BidPrjEnvi : {}", bidPrjEnviDTO);
//        if (bidPrjEnviDTO.getPrjno() != null) {
//            throw new BadRequestAlertException("A new bidPrjEnvi cannot already have an ID", ENTITY_NAME, "idexists");
//        }

            List<String> validResult = bidPrjEnviService.validData(bidPrjEnviDTO);
            if (validResult.size() > 0) {
                throw new BidValidationFailException("bidValidError", "資料檢核錯誤", validResult.toArray(new String[0]));
            }


        BidPrjEnviDTO result = bidPrjEnviService.save(bidPrjEnviDTO);
        return ResponseEntity
            .created(new URI("/api/bid-prj-safes/" +result.getId().getWkut()+"/"+ result.getId().getPrjno()+"/"+ result.getId().getSdate()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
//            .created(new URI("/api/bid-prj-envis/" + result.getId()))
//            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
//            .body(result);
    }

    /**
     * {@code PUT  /bid-prj-envis/:id} : Updates an existing bidPrjEnvi.
     *
     * @param bidPrjEnviDTO the id of the bidPrjEnviDTO to save.
     * @param bidPrjEnviDTO the bidPrjEnviDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidPrjEnviDTO,
     * or with status {@code 400 (Bad Request)} if the bidPrjEnviDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bidPrjEnviDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bid-prj-envis/")
    public ResponseEntity<BidPrjEnviDTO> updateBidPrjEnvi(
//        @PathVariable(value = "wkut", required = false) final String wkut,
        @Valid @RequestBody BidPrjEnviDTO bidPrjEnviDTO
    ) throws URISyntaxException {
        BidPrjEnviPrimaryKey bidPrjEnviPrimaryKey = new BidPrjEnviPrimaryKey(bidPrjEnviDTO.getWkut(), bidPrjEnviDTO.getPrjno(), bidPrjEnviDTO.getSdate());
        log.debug("REST request to update BidPrjEnvi : {}", bidPrjEnviDTO);
        if (bidPrjEnviDTO.getWkut() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(bidPrjEnviPrimaryKey, bidPrjEnviDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidPrjEnviRepository.existsById(bidPrjEnviPrimaryKey)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        BidPrjEnviDTO result = bidPrjEnviService.save(bidPrjEnviDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidPrjEnviDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /bid-prj-envis/:id} : Partial updates given fields of an existing bidPrjEnvi, field will ignore if it is null
     *
     * @param bidPrjEnviDTO the id of the bidPrjEnviDTO to save.
     * @param bidPrjEnviDTO the bidPrjEnviDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidPrjEnviDTO,
     * or with status {@code 400 (Bad Request)} if the bidPrjEnviDTO is not valid,
     * or with status {@code 404 (Not Found)} if the bidPrjEnviDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the bidPrjEnviDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/bid-prj-envis", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<BidPrjEnviDTO> partialUpdateBidPrjEnvi(
//        @PathVariable(value = "wkut", required = false) final String wkut,
        @NotNull @RequestBody BidPrjEnviDTO bidPrjEnviDTO

    ) throws URISyntaxException {
        BidPrjEnviPrimaryKey bidPrjEnviPrimaryKey = new BidPrjEnviPrimaryKey(bidPrjEnviDTO.getWkut(), bidPrjEnviDTO.getPrjno(), bidPrjEnviDTO.getSdate());
        log.debug("REST request to partial update BidPrjEnvi partially : {}", bidPrjEnviDTO);
        if (bidPrjEnviDTO.getWkut() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(bidPrjEnviPrimaryKey, bidPrjEnviDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidPrjEnviRepository.existsById(bidPrjEnviPrimaryKey)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BidPrjEnviDTO> result = bidPrjEnviService.partialUpdate(bidPrjEnviDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidPrjEnviDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /bid-prj-envis} : get all the bidPrjEnvis.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bidPrjEnvis in body.
     */
    @GetMapping("/bid-prj-envis")
    public List<BidPrjEnviDTO> getAllBidPrjEnvis() {
        log.debug("REST request to get all BidPrjEnvis");
        return bidPrjEnviService.findAll();
    }

    /**
     * {@code GET  /bid-prj-envis/:id} : get the "id" bidPrjEnvi.
     *
     * @param bidPrjEnviPrimaryKey the id of the bidPrjEnviDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bidPrjEnviDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/bid-prj-envis/getOne")
    public ResponseEntity<BidPrjEnviDTO> getBidPrjEnvi(@PathVariable BidPrjEnviPrimaryKey bidPrjEnviPrimaryKey) {
        log.debug("REST request to get BidPrjEnvi : {}", bidPrjEnviPrimaryKey);
        Optional<BidPrjEnviDTO> bidPrjEnviDTO = bidPrjEnviService.findOne(bidPrjEnviPrimaryKey);
        return ResponseUtil.wrapOrNotFound(bidPrjEnviDTO);
    }

    /**
     * {@code DELETE  /bid-prj-envis/:id} : delete the "id" bidPrjEnvi.
     *
     * @param bidPrjEnviPrimaryKey the id of the bidPrjEnviDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @PostMapping("/bid-prj-envis-delete")
    public ResponseEntity<Void> deleteBidPrjEnvi(@RequestBody BidPrjEnviPrimaryKey bidPrjEnviPrimaryKey) {
        log.debug("REST request to delete BidPrjEnvi : {}", bidPrjEnviPrimaryKey);
        bidPrjEnviService.delete(bidPrjEnviPrimaryKey);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, bidPrjEnviPrimaryKey.toString())).build();
    }
    //沒有分頁
    @PostMapping("/bid-prj-add-find-by-pk")
    public List<BidPrjEnviDTO> getAll(@RequestBody BidPrjEnviPrimaryKey bidPrjEnviPrimaryKey) {
        log.debug("REST request to getAll BidPrjEnvi : {}", bidPrjEnviPrimaryKey);
        return bidPrjEnviService.findByPk(bidPrjEnviPrimaryKey.getWkut(),bidPrjEnviPrimaryKey.getPrjno());
    }



    //分頁用criteria
    /**
          * {@code POST  /bid-prj-add/criteria-jpa} : get admSysCode by criteria.
          *
          * @param criteria the criteria of the BidPrjEnviCriteria to retrieve.
          * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the BidPrjEnviDTO, or with status {@code 404 (Not Found)}.
          */
    @PostMapping("/bid-prj-add/criteria-jpa")
    public Page<BidPrjEnviDTO> getBidPrjEnviByCriteria(@Valid @RequestBody BidPrjEnviCriteria criteria) throws InterruptedException {
        log.debug("REST request to get BidPrjEnviDTO : {}", criteria);
        return bidPrjEnviService.findByCriteria(criteria);
    }

    @PostMapping("/bid-prj-find-ctunit")
    public List<BidCcutDTO> getctUnit(@RequestBody BidPrjEnviCriteria criteria) throws InterruptedException{
        log.debug("REST request to getAll BidPrjEnvi : {}", criteria);
        return bidPrjEnviService.findctUnitByPk(criteria);
    }



    /**
     * {@code POST  /bid-prj-envis} : Create a new bidPrjEnvi.
     *
     * @param bidPrjEnviDTO the bidPrjEnviDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bidPrjEnviDTO, or with status {@code 400 (Bad Request)} if the bidPrjEnvi has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bid-prj-envis-up")
    public ResponseEntity<BidPrjEnviDTO> createBidPrjEnvir(@Valid @RequestBody BidPrjEnviDTO bidPrjEnviDTO) throws URISyntaxException {
        log.debug("REST request to save BidPrjEnvi : {}", bidPrjEnviDTO);
//        if (bidPrjEnviDTO.getPrjno() != null) {
//            throw new BadRequestAlertException("A new bidPrjEnvi cannot already have an ID", ENTITY_NAME, "idexists");
//        }


        BidPrjEnviDTO result = bidPrjEnviService.save(bidPrjEnviDTO);
        return ResponseEntity
            .created(new URI("/api/bid-prj-safes/" +result.getId().getWkut()+"/"+ result.getId().getPrjno()+"/"+ result.getId().getSdate()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
//            .created(new URI("/api/bid-prj-envis/" + result.getId()))
//            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
//            .body(result);
    }



    // 產生環保裁罰表 bid-aae-007
    @PostMapping(path="/bid-prj-envis/bidAae007",produces = ReportMediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> bidAae007(@RequestBody BidPrjEnviCriteria criteria) throws IOException {
        log.debug("REST request to get pdf : {}", criteria);


        BidProjectPrimaryKey bidProjectPrimaryKey = new BidProjectPrimaryKey(criteria.getWkut(),criteria.getPrjno());
        // find VwBidProjectDTO
        VwBidProjectDTO vwBidProjectDTO = vwBidProjectService.findOne(bidProjectPrimaryKey).get();

        BidPrjEnviPrimaryKey bidPrjEnviPrimaryKey = new BidPrjEnviPrimaryKey(criteria.getWkut(),criteria.getPrjno(),criteria.getSdate());
        // find BidPrjEnviDTO
        BidPrjEnviDTO bidPrjEnviDTO = bidPrjEnviService.findOne(bidPrjEnviPrimaryKey).get();



        // find BidProjectDTO
        BidProjectDTO bidProjectDTO = bidProjectService.findOne(bidProjectPrimaryKey).get();


        // wkutName
        BidUnit10DTO wkutDTO = bidUnit10Service.findByCode(bidProjectDTO.getWkut()).orElse(null);

        String ccutNum ="";
        String scutNum ="";

        // 找承包商和監造單位
        List<BidProjectUnitDTO> bidProjectUnitDTO = bidProjectUnitService.findBidCcutByKeyWorld(criteria.getWkut(),criteria.getPrjno());
        System.out.println("!!!!!!!"+bidProjectUnitDTO);
        for(BidProjectUnitDTO item :bidProjectUnitDTO){
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


        // 工程類別
        String typeCode = "";
        String typeCodeNum = vwBidProjectDTO.getTypeCode();
        if(typeCodeNum != null && typeCodeNum.length() > 0) {
            AdmSysCodeDTO admSysCodeDTO = admSysCodeService.findByPk("BID", "BID_003", typeCodeNum);
            typeCode = admSysCodeDTO.getValue();
        }

        // 縣市鄉鎮
        String placeCode ="";
        String placeCodeNum = vwBidProjectDTO.getPlaceCode();
        if(placeCodeNum != null && placeCodeNum.length() > 0) {
            List<BidPlaceDTO> bidPlaceDTO = bidPlaceService.findMapInfoByPlaceCode(placeCodeNum);
            for(BidPlaceDTO item: bidPlaceDTO){
                placeCode = item.getPlaceName();
            }
        }else {
            placeCode = "(未填)";
        }

        // 管轄單位
        String ctUnit ="";
        String ctUnitNum = bidPrjEnviDTO.getCtUnit();
        if(ctUnitNum != null && ctUnitNum.length() > 0 ) {
            AdmSysCodeDTO admSysCodeDTO2 = admSysCodeService.findByPk("BID", "BID_017", ctUnitNum);
            ctUnit = admSysCodeDTO2.getValue();
        }

        // 裁處類型
        String sType ="";
        String sTypeNum = bidPrjEnviDTO.getStype();
        if(sTypeNum != null && sTypeNum.length() > 0 ) {
            AdmSysCodeDTO admSysCodeDTO3 = admSysCodeService.findByPk("BID", "BID_022", sTypeNum);
            sType = admSysCodeDTO3.getValue();
        }else {
            sType = "(未填)";
        }




        BidAae007 bidAae007 = new BidAae007();
        bidAae007.setVwBidProjectDTO(vwBidProjectDTO);
        bidAae007.setBidProjectDTO(bidProjectDTO);
        bidAae007.setBidPrjEnviDTO(bidPrjEnviDTO);
        bidAae007.setCcut(ccut);
        bidAae007.setScut(scut);
        bidAae007.setPlaceCode(placeCode);
        bidAae007.setTypeCode(typeCode);
        bidAae007.setCtUnit(ctUnit);
        bidAae007.setsType(sType);
        bidAae007.setWkutDTO(wkutDTO);


        byte[] pdfbyte = reportService.printReport(bidAae007);
        return ResponseEntity.ok(pdfbyte);


    }


    /**
     *
     * @param criteria wkut prjno
     * @return List<BidPrjEnviDTO>
     */
    @PostMapping(path="/bid-prj-envis/list-by-criteria")
    public List<BidPrjEnviDTO> getDTOListByCriteria(BidPrjEnviCriteria criteria){
        return bidPrjEnviService.findDTOListByCriteria(criteria);
    }

}
