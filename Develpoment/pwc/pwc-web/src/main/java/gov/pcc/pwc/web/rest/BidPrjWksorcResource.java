package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.common.web.ReportMediaType;
import gov.pcc.pwc.domain.BidPrjWksorcPrimaryKey;
import gov.pcc.pwc.domain.BidProjectPrimaryKey;
import gov.pcc.pwc.report.bid.bidaae030.BidAae030A;
import gov.pcc.pwc.report.bid.bidaae030.BidAae030B;
import gov.pcc.pwc.repository.BidPrjWksorcRepository;
import gov.pcc.pwc.service.*;
import gov.pcc.pwc.service.criteria.BidPrjWksorcCriteria;
import gov.pcc.pwc.service.dto.*;
//import gov.pcc.pwc.web.rest.errors.BadRequestAlertException;
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
 * REST controller for managing {@link gov.pcc.pwc.domain.BidPrjWksorc}.
 */
@RestController
@RequestMapping("/api")
public class BidPrjWksorcResource {

    private final Logger log = LoggerFactory.getLogger(BidPrjWksorcResource.class);

    private static final String ENTITY_NAME = "bidPrjWksorc";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BidPrjWksorcService bidPrjWksorcService;

    private final BidPrjWksorcRepository bidPrjWksorcRepository;


    private final VwBidProjectService vwBidProjectService;

    private final BidProjectService bidProjectService;

    private final BidProjectUnitService bidProjectUnitService;

    private final BidUnit10Service bidUnit10Service;

    private final BidPlaceService bidPlaceService;

    private final AdmSysCodeService admSysCodeService;

    // 產生報表檔
    private final ReportService reportService;


    public BidPrjWksorcResource( BidPrjWksorcService bidPrjWksorcService, BidPrjWksorcRepository bidPrjWksorcRepository, VwBidProjectService vwBidProjectService, BidProjectService bidProjectService, BidProjectUnitService bidProjectUnitService, BidUnit10Service bidUnit10Service, BidPlaceService bidPlaceService, AdmSysCodeService admSysCodeService, ReportService reportService) {
        this.bidPrjWksorcService = bidPrjWksorcService;
        this.bidPrjWksorcRepository = bidPrjWksorcRepository;
        this.vwBidProjectService = vwBidProjectService;
        this.bidProjectService = bidProjectService;
        this.bidProjectUnitService = bidProjectUnitService;
        this.bidUnit10Service = bidUnit10Service;
        this.bidPlaceService = bidPlaceService;
        this.admSysCodeService = admSysCodeService;
        this.reportService = reportService;
    }

    /**
     * {@code POST  /bid-prj-wksorcs} : Create a new bidPrjWksorc.
     *
     * @param bidPrjWksorcDTO the bidPrjWksorcDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bidPrjWksorcDTO, or with status {@code 400 (Bad Request)} if the bidPrjWksorc has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bid-prj-wksorcs")
    public ResponseEntity<BidPrjWksorcDTO> createBidPrjWksorc(@Valid @RequestBody BidPrjWksorcDTO bidPrjWksorcDTO)
        throws URISyntaxException {
        log.debug("REST request to save BidPrjWksorc : {}", bidPrjWksorcDTO);
//        if (bidPrjWksorcDTO.getId() != null) {
//            throw new BadRequestAlertException("A new bidPrjWksorc cannot already have an ID", ENTITY_NAME, "idexists");
//        }
        BidPrjWksorcDTO result = bidPrjWksorcService.save(bidPrjWksorcDTO);
        bidPrjWksorcService.saveEmail(bidPrjWksorcDTO);
        return ResponseEntity
//            .created(new URI("/api/bid-prj-wksorcs/" + result.getId()))
//            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
//            .body(result);
            .created(new URI("/api/bid-prj-safes/" +result.getId().getWkut()+"/"+ result.getId().getPrjno()+"/"+ result.getId().getSyr()+"/"+ result.getId().getMonth()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bid-prj-wksorcs/:id} : Updates an existing bidPrjWksorc.
     *
     * @param bidPrjWksorcDTO the id of the bidPrjWksorcDTO to save.
     * @param bidPrjWksorcDTO the bidPrjWksorcDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidPrjWksorcDTO,
     * or with status {@code 400 (Bad Request)} if the bidPrjWksorcDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bidPrjWksorcDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bid-prj-wksorcs/")
    public ResponseEntity<BidPrjWksorcDTO> updateBidPrjWksorc(
//        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody BidPrjWksorcDTO bidPrjWksorcDTO
    ) throws URISyntaxException {
        BidPrjWksorcPrimaryKey bidPrjWksorcPrimaryKey = new BidPrjWksorcPrimaryKey(bidPrjWksorcDTO.getWkut(), bidPrjWksorcDTO.getPrjno(), bidPrjWksorcDTO.getSyr(), bidPrjWksorcDTO.getMonth());
        log.debug("REST request to update BidPrjWksorc : {}", bidPrjWksorcDTO);
        if (bidPrjWksorcDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(bidPrjWksorcPrimaryKey, bidPrjWksorcDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidPrjWksorcRepository.existsById(bidPrjWksorcPrimaryKey)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        BidPrjWksorcDTO result = bidPrjWksorcService.save(bidPrjWksorcDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidPrjWksorcDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /bid-prj-wksorcs/:id} : Partial updates given fields of an existing bidPrjWksorc, field will ignore if it is null
     *
     * @param bidPrjWksorcDTO the id of the bidPrjWksorcDTO to save.
     * @param bidPrjWksorcDTO the bidPrjWksorcDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidPrjWksorcDTO,
     * or with status {@code 400 (Bad Request)} if the bidPrjWksorcDTO is not valid,
     * or with status {@code 404 (Not Found)} if the bidPrjWksorcDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the bidPrjWksorcDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/bid-prj-wksorcs/", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<BidPrjWksorcDTO> partialUpdateBidPrjWksorc(
//        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody BidPrjWksorcDTO bidPrjWksorcDTO
    ) throws URISyntaxException {
        BidPrjWksorcPrimaryKey bidPrjWksorcPrimaryKey = new BidPrjWksorcPrimaryKey(bidPrjWksorcDTO.getWkut(), bidPrjWksorcDTO.getPrjno(), bidPrjWksorcDTO.getSyr(), bidPrjWksorcDTO.getMonth());
        log.debug("REST request to partial update BidPrjWksorc partially : {}, {}", bidPrjWksorcPrimaryKey, bidPrjWksorcDTO);
        if (bidPrjWksorcDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(bidPrjWksorcPrimaryKey, bidPrjWksorcDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidPrjWksorcRepository.existsById(bidPrjWksorcPrimaryKey)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BidPrjWksorcDTO> result = bidPrjWksorcService.partialUpdate(bidPrjWksorcDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidPrjWksorcDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /bid-prj-wksorcs} : get all the bidPrjWksorcs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bidPrjWksorcs in body.
     */
    @GetMapping("/bid-prj-wksorcs")
    public List<BidPrjWksorcDTO> getAllBidPrjWksorcs() {
        log.debug("REST request to get all BidPrjWksorcs");
        return bidPrjWksorcService.findAll();
    }

    /**
     * {@code GET  /bid-prj-wksorcs/:id} : get the "id" bidPrjWksorc.
     *
     * @param bidPrjWksorcPrimaryKey the id of the bidPrjWksorcDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bidPrjWksorcDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/bid-prj-wksorcs/getOne")
    public ResponseEntity<BidPrjWksorcDTO> getBidPrjWksorc(@PathVariable BidPrjWksorcPrimaryKey bidPrjWksorcPrimaryKey) {
        log.debug("REST request to get BidPrjWksorc : {}", bidPrjWksorcPrimaryKey);
        Optional<BidPrjWksorcDTO> bidPrjWksorcDTO = bidPrjWksorcService.findOne(bidPrjWksorcPrimaryKey);
        return ResponseUtil.wrapOrNotFound(bidPrjWksorcDTO);
    }

    /**
     * {@code DELETE  /bid-prj-wksorcs/:id} : delete the "id" bidPrjWksorc.
     *
     * @param bidPrjWksorcPrimaryKey the id of the bidPrjWksorcDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @PostMapping("/bid-prj-wksorcs-delete")
    public ResponseEntity<Void> deleteBidPrjWksorc(@RequestBody BidPrjWksorcPrimaryKey bidPrjWksorcPrimaryKey) {
        log.debug("REST request to delete BidPrjWksorc : {}", bidPrjWksorcPrimaryKey);
        bidPrjWksorcService.delete(bidPrjWksorcPrimaryKey);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, bidPrjWksorcPrimaryKey.toString()))
            .build();
    }

    //    分頁用criteria
    /**
     * {@code POST  /bid-prj-add/criteria-jpa} : get admSysCode by criteria.
     *
     * @param criteria the criteria of the BidPrjEnviCriteria to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the BidPrjEnviDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/bid-prj-wksorc/criteria-jpa")
    public Page<BidPrjWksorcDTO> getBidPrjWksorcByCriteria(@Valid @RequestBody BidPrjWksorcCriteria criteria) throws InterruptedException {
        log.debug("REST request to get BidPrjEnviDTO : {}", criteria);
        return bidPrjWksorcService.findByCriteria(criteria);
    }



    // 人力需求表 bid-aae-030A
    @PostMapping(path="/bid-prj-wksorc/bidAae030A",produces = ReportMediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> bidAae030A(@RequestBody BidPrjWksorcCriteria criteria) throws IOException {
        log.debug("REST request to get pdf : {}", criteria);


        BidProjectPrimaryKey bidProjectPrimaryKey = new BidProjectPrimaryKey(criteria.getWkut(),criteria.getPrjno());
        // find VwBidProjectDTO
        VwBidProjectDTO vwBidProjectDTO = vwBidProjectService.findOne(bidProjectPrimaryKey).get();

        // find BidPrjFrwkrDTO
        List<BidPrjWksorcDTO> bidPrjWksorcDTO2 = bidPrjWksorcService.findByWkutAndPrjno(criteria.getWkut(), criteria.getPrjno());


        // find BidProjectDTO
        BidProjectDTO bidProjectDTO = bidProjectService.findOne(bidProjectPrimaryKey).get();

        // find BidUnit10DTO
        BidUnit10DTO bidUnit10DTO = bidUnit10Service.findOne(criteria.getWkut()).get();


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

        BidAae030A bidAae030A = new BidAae030A();
        bidAae030A.setVwBidProjectDTO(vwBidProjectDTO);
        bidAae030A.setBidProjectDTO(bidProjectDTO);
        bidAae030A.setBidPrjWksorcDTO(bidPrjWksorcDTO2);
        bidAae030A.setBidUnit10DTO(bidUnit10DTO);
        bidAae030A.setCcut(ccut);
        bidAae030A.setScut(scut);
        bidAae030A.setPlace(place);
        byte[] pdfbyte = reportService.printReport(bidAae030A);
        return ResponseEntity.ok(pdfbyte);


    }


    // 人力需求表 bid-aae-030B

    @PostMapping(path="/bid-prj-wksorc/bidAae030B",produces = ReportMediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> bidAae030B(@RequestBody BidPrjWksorcCriteria criteria) throws IOException {
        log.debug("REST request to get pdf : {}", criteria);


        BidProjectPrimaryKey bidProjectPrimaryKey = new BidProjectPrimaryKey(criteria.getWkut(),criteria.getPrjno());
        // find VwBidProjectDTO
        VwBidProjectDTO vwBidProjectDTO = vwBidProjectService.findOne(bidProjectPrimaryKey).get();

        // find BidPrjFrwkrDTO
        List<BidPrjWksorcDTO> bidPrjWksorcDTO2 = bidPrjWksorcService.findByWkutAndPrjno(criteria.getWkut(), criteria.getPrjno());


        // find BidProjectDTO
        BidProjectDTO bidProjectDTO = bidProjectService.findOne(bidProjectPrimaryKey).get();

        // find BidUnit10DTO
        BidUnit10DTO bidUnit10DTO = bidUnit10Service.findOne(criteria.getWkut()).get();


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

        BidAae030B bidAae030B= new BidAae030B();
        bidAae030B.setVwBidProjectDTO(vwBidProjectDTO);
        bidAae030B.setBidProjectDTO(bidProjectDTO);
        bidAae030B.setBidPrjWksorcDTO(bidPrjWksorcDTO2);
        bidAae030B.setBidUnit10DTO(bidUnit10DTO);
        bidAae030B.setCcut(ccut);
        bidAae030B.setScut(scut);
        bidAae030B.setPlace(place);
        byte[] pdfbyte = reportService.printReport(bidAae030B);
        return ResponseEntity.ok(pdfbyte);


    }

}
