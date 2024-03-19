package gov.pcc.pwc.web.rest;


import gov.pcc.pwc.common.errors.BidValidationFailException;
import gov.pcc.pwc.common.web.ReportMediaType;
import gov.pcc.pwc.domain.BidPrjSafePrimaryKey;
import gov.pcc.pwc.domain.BidProjectPrimaryKey;
import gov.pcc.pwc.report.bid.bidAae006.BidAae006;
import gov.pcc.pwc.repository.BidPrjSafeRepository;
import gov.pcc.pwc.service.*;
import gov.pcc.pwc.service.criteria.BidPrjSafeCriteria;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link gov.pcc.pwc.domain.BidPrjSafe}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class BidPrjSafeResource {

    private final Logger log = LoggerFactory.getLogger(BidPrjSafeResource.class);

    private static final String ENTITY_NAME = "bidPrjSafe";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BidPrjSafeService bidPrjSafeService;

    private final BidPrjSafeRepository bidPrjSafeRepository;

    private final BidProjectService bidProjectService;

    private final VwBidProjectService vwBidProjectService;

    private final BidProjectUnitService bidProjectUnitService;

    private final BidUnit10Service bidUnit10Service;

    private AdmSysCodeService admSysCodeService;

    private BidPlaceService bidPlaceService;

    // 產生報表檔
    private final ReportService reportService;

    public BidPrjSafeResource(BidPrjSafeService bidPrjSafeService, BidPrjSafeRepository bidPrjSafeRepository, BidProjectService bidProjectService, ReportService reportService,VwBidProjectService vwBidProjectService,BidProjectUnitService bidProjectUnitService,BidUnit10Service bidUnit10Service,AdmSysCodeService admSysCodeService,BidPlaceService bidPlaceService) {
        this.bidPrjSafeService = bidPrjSafeService;
        this.bidPrjSafeRepository = bidPrjSafeRepository;
        this.bidProjectService = bidProjectService;
        this.reportService = reportService;
        this.vwBidProjectService = vwBidProjectService;
        this.bidUnit10Service = bidUnit10Service;
        this.bidProjectUnitService = bidProjectUnitService;
        this.admSysCodeService = admSysCodeService;
        this.bidPlaceService = bidPlaceService;
    }


    /**
     * {@code POST  /bid-prj-safes} : Create a new bidPrjSafe.
     *
     * @param bidPrjSafeDTO the bidPrjSafeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bidPrjSafeDTO, or with status {@code 400 (Bad Request)} if the bidPrjSafe has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bid-prj-safes")
    public ResponseEntity<BidPrjSafeDTO> createBidPrjSafe(@Valid @RequestBody BidPrjSafeDTO bidPrjSafeDTO) throws URISyntaxException {
        log.debug("REST request to save BidPrjSafe : {}", bidPrjSafeDTO);
//        idClass 已經有給一個CLASS在ID內
//        if (bidPrjSafeDTO.getId() != null) {
//            throw new BadRequestAlertException("A new bidPrjSafe cannot already have an ID", ENTITY_NAME, "idexists");
//        }
        String fromStatus = "create";
//       後端驗證
        List<String> validResult = bidPrjSafeService.validData(bidPrjSafeDTO,fromStatus);
        if(validResult.size()>0){
            throw new BidValidationFailException("bidValidError","資料檢核錯誤" ,validResult.toArray(new String[0]));

        }

        BidPrjSafeDTO result = bidPrjSafeService.save(bidPrjSafeDTO);
        return ResponseEntity
            .created(new URI("/api/bid-prj-safes/" +result.getId().getWkut()+"/"+ result.getId().getPrjno()+"/"+ result.getId().getsdate()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bid-prj-safes/updateOne} : Updates an existing bidPrjSafe.
     *
     * @param bidPrjSafeDTO the bidPrjSafeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidPrjSafeDTO,
     * or with status {@code 400 (Bad Request)} if the bidPrjSafeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bidPrjSafeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bid-prj-safes/updateOne")
    public ResponseEntity<BidPrjSafeDTO> updateBidPrjSafe(
        @Valid @RequestBody BidPrjSafeDTO bidPrjSafeDTO
    ) throws URISyntaxException {
        log.debug("REST request to update BidPrjSafe : {}",  bidPrjSafeDTO);
        BidPrjSafePrimaryKey bidPrjSafePrimaryKey = new BidPrjSafePrimaryKey(bidPrjSafeDTO.getWkut(),bidPrjSafeDTO.getPrjno(),bidPrjSafeDTO.getSdate());
        if (bidPrjSafeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(bidPrjSafePrimaryKey, bidPrjSafeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidPrjSafeRepository.existsById(bidPrjSafePrimaryKey)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        String formStatus = "modify";
//        後端驗證
        List<String> validResult = bidPrjSafeService.validData(bidPrjSafeDTO , formStatus);
        if(validResult.size()>0){
            throw new BidValidationFailException("bidValidError","資料檢核錯誤" ,validResult.toArray(new String[0]));
        }
        BidPrjSafeDTO result = bidPrjSafeService.update(bidPrjSafeDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidPrjSafeDTO.getId().toString()))
            .body(result);
    }


    /**
     * {@code PATCH  /bid-prj-safes/:id} : Partial updates given fields of an existing bidPrjSafe, field will ignore if it is null
     *
     * @param bidPrjSafeDTO the bidPrjSafeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidPrjSafeDTO,
     * or with status {@code 400 (Bad Request)} if the bidPrjSafeDTO is not valid,
     * or with status {@code 404 (Not Found)} if the bidPrjSafeDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the bidPrjSafeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/bid-prj-safes/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<BidPrjSafeDTO> partialUpdateBidPrjSafe(
        @NotNull @RequestBody BidPrjSafeDTO bidPrjSafeDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update BidPrjSafe partially : {}", bidPrjSafeDTO);
        BidPrjSafePrimaryKey bidPrjSafePrimaryKey = new BidPrjSafePrimaryKey(bidPrjSafeDTO.getWkut(),bidPrjSafeDTO.getPrjno(),bidPrjSafeDTO.getSdate());
        if (bidPrjSafeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(bidPrjSafePrimaryKey, bidPrjSafeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidPrjSafeRepository.existsById(bidPrjSafePrimaryKey)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BidPrjSafeDTO> result = bidPrjSafeService.partialUpdate(bidPrjSafeDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidPrjSafeDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /bid-prj-safes} : get all the bidPrjSafes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bidPrjSafes in body.
     */
    @GetMapping("/bid-prj-safes")
    public List<BidPrjSafeDTO> getAllBidPrjSafes() {
        log.debug("REST request to get all BidPrjSafes");
        return bidPrjSafeService.findAll();
    }

    /**
     * {@code POST  /bid-prj-safes/:id} : get the "id" bidPrjSafe.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bidPrjSafeDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/bid-prj-safes/getOne")
    public ResponseEntity<BidPrjSafeDTO> getBidPrjSafe(@RequestBody BidPrjSafePrimaryKey id) {
        log.debug("REST request to get BidPrjSafe : {}", id);
        Optional<BidPrjSafeDTO> bidPrjSafeDTO = bidPrjSafeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bidPrjSafeDTO);
    }

    /**
     * {@code DELETE  /bid-prj-safes/deleteOne} : delete the "id" bidPrjSafe.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bid-prj-safes/deleteOne")
    public ResponseEntity<Void> deleteBidPrjSafe(@RequestBody BidPrjSafePrimaryKey id) {
        log.debug("REST request to delete BidPrjSafe : {}", id);
        bidPrjSafeService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }


    // 工安事件狀況列表 bid-prj-safe : bid-aad-002-info
    @PostMapping("/bid-prj-safes/criteria")
    public Page<BidPrjSafeDTO> getBidPRjSafesByCriteria(@Valid @RequestBody BidPrjSafeCriteria criteria) throws InterruptedException {
        log.debug("REST request to get RecTyphoon : {}",criteria);
        return bidPrjSafeService.findAllListByCriteria(criteria);
    }

    // 產生工安事件表 bid-aae-006
    @PostMapping(path="/bid-prj-safes/bidAae006",produces = ReportMediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> bidAae006(@RequestBody BidPrjSafeCriteria criteria) throws IOException {
        log.debug("REST request to get pdf : {}", criteria);

        BidProjectPrimaryKey bidProjectPrimaryKey = new BidProjectPrimaryKey(criteria.getWkut(),criteria.getPrjno());
        // find VwBidProjectDTO
        VwBidProjectDTO vwBidProjectDTO = vwBidProjectService.findOne(bidProjectPrimaryKey).get();

        // find BidProjectDTO
        BidProjectDTO bidProjectDTO = bidProjectService.findOne(bidProjectPrimaryKey).get();


        BidPrjSafePrimaryKey bidPrjSafePrimaryKey = new BidPrjSafePrimaryKey(criteria.getWkut(),criteria.getPrjno(),criteria.getSdate());
        // find BidPrjSafeDTO
        BidPrjSafeDTO bidPrjSafeDTO = bidPrjSafeService.findOne(bidPrjSafePrimaryKey).get();

        String ccutNum ="";
        String scutNum ="";

        // 找 承包商 和 監造單位
        List<BidProjectUnitDTO> bidProjectUnitDTO = bidProjectUnitService.findBidCcutByKeyWorld(criteria.getWkut(),criteria.getPrjno());
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
        String ctUnitNum = bidPrjSafeDTO.getCtUnit();
        if(ctUnitNum != null && ctUnitNum.length() > 0 ) {
            AdmSysCodeDTO admSysCodeDTO2 = admSysCodeService.findByPk("BID", "BID_017", ctUnitNum);
           ctUnit = admSysCodeDTO2.getValue();
        }

        // 災害類型
        String sType ="";
        String sTypeNum = bidPrjSafeDTO.getStype();
        if(sTypeNum != null && sTypeNum.length() > 0 ) {
            AdmSysCodeDTO admSysCodeDTO3 = admSysCodeService.findByPk("BID", "BID_018", sTypeNum);
            sType = admSysCodeDTO3.getValue();
        }



        BidAae006 bidAae006 = new BidAae006();
        bidAae006.setVwBidProjectDTO(vwBidProjectDTO);
        bidAae006.setBidProjectDTO(bidProjectDTO);
        bidAae006.setBidPrjSafeDTO(bidPrjSafeDTO);
        bidAae006.setCcut(ccut);
        bidAae006.setScut(scut);
        bidAae006.setPlaceCode(placeCode);
        bidAae006.setTypeCode(typeCode);
        bidAae006.setCtUnit(ctUnit);
        bidAae006.setsType(sType);


        byte[] pdfbyte = reportService.printReport(bidAae006);
        return ResponseEntity.ok(pdfbyte);


    }

    /**
     * @param criteria wkut和prjno
     * @return  List<BidPrjSafeDTO>
     */
    @PostMapping(path="/bid-prj-safes/list-by-criteria")
    public List<BidPrjSafeDTO> findPrjSafeDTOListByCriteria(@RequestBody BidPrjSafeCriteria criteria){
        return bidPrjSafeService.findPrjSafeDTOListByCriteria(criteria);
    }
}
