package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.MessageCodes;
import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.common.errors.BidValidationFailException;
import gov.pcc.pwc.common.web.ReportMediaType;
import gov.pcc.pwc.domain.*;
import gov.pcc.pwc.report.bid.bidAae003.BidAae003;
import gov.pcc.pwc.repository.BidPrjScequipRepository;
import gov.pcc.pwc.repository.BidPrjWksorcRepository;
import gov.pcc.pwc.service.*;
import gov.pcc.pwc.service.dto.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import gov.pcc.pwc.utils.PwcHeadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link gov.pcc.pwc.domain.BidPrjScequip}.
 */
@RestController
@RequestMapping("/api")
public class BidPrjScequipResource {

    private final Logger log = LoggerFactory.getLogger(BidPrjScequipResource.class);

    private static final String ENTITY_NAME = "bidPrjScequip";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BidPrjScequipService bidPrjScequipService;

    private final BidPrjScequipRepository bidPrjScequipRepository;

    private final BidPrjScequipService sidPrjScequipService;

    private final BidPrjWksorcService bidPrjWksorcService;

    private final BidPrjWksorcRepository bidPrjWksorcRepository;


    private final VwBidProjectService vwBidProjectService;

    private final BidProjectService bidProjectService;

    private final BidProjectUnitService bidProjectUnitService;

    private final BidUnit10Service bidUnit10Service;

    private final BidPlaceService bidPlaceService;

    private final AdmSysCodeService admSysCodeService;

    private final BidPrjSubcoService bidPrjSubcoService;


    // 產生報表檔
    private final ReportService reportService;

    public BidPrjScequipResource(BidPrjScequipService bidPrjScequipService, BidPrjScequipRepository bidPrjScequipRepository, BidPrjScequipService sidPrjScequipService, BidPrjWksorcService bidPrjWksorcService, BidPrjWksorcRepository bidPrjWksorcRepository, VwBidProjectService vwBidProjectService, BidProjectService bidProjectService, BidProjectUnitService bidProjectUnitService, BidUnit10Service bidUnit10Service, BidPlaceService bidPlaceService, AdmSysCodeService admSysCodeService, BidPrjSubcoService bidPrjSubcoService, ReportService reportService) {
        this.bidPrjScequipService = bidPrjScequipService;
        this.bidPrjScequipRepository = bidPrjScequipRepository;
        this.sidPrjScequipService = sidPrjScequipService;
        this.bidPrjWksorcService = bidPrjWksorcService;
        this.bidPrjWksorcRepository = bidPrjWksorcRepository;
        this.vwBidProjectService = vwBidProjectService;
        this.bidProjectService = bidProjectService;
        this.bidProjectUnitService = bidProjectUnitService;
        this.bidUnit10Service = bidUnit10Service;
        this.bidPlaceService = bidPlaceService;
        this.admSysCodeService = admSysCodeService;
        this.bidPrjSubcoService = bidPrjSubcoService;
        this.reportService = reportService;
    }

    /**
     * {@code POST  /bid-prj-scequip} : Create a new bidPrjScequip.
     *
     * @param bidPrjScequipDTO the bidPrjScequipDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bidPrjScequipDTO, or with status {@code 400 (Bad Request)} if the bidPrjScequip has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bid-prj-scequip")
    public ResponseEntity<BidPrjScequipDTO> createBidPrjScequip(@Valid @RequestBody BidPrjScequipDTO bidPrjScequipDTO, Authentication authentication)
        throws URISyntaxException {
        log.debug("REST request to save BidPrjScequip : {}", bidPrjScequipDTO);

        BidPrjScequipDTO upData = new BidPrjScequipDTO();

        upData.setWkut(bidPrjScequipDTO.getWkut());
        upData.setPrjno(bidPrjScequipDTO.getPrjno());
        upData.setSubCcut(bidPrjScequipDTO.getSubCcut());
        upData.seteCode(bidPrjScequipDTO.geteCode());
        upData.seteCnt(bidPrjScequipDTO.geteCnt());
        upData.seteRemark(bidPrjScequipDTO.geteRemark());
        upData.setCreateDate(Instant.now());
        upData.setCreateUser(authentication.getName());
        upData.setUpdateDate(Instant.now());
        upData.setUpdateUser(authentication.getName());
        upData.setConfA(bidPrjScequipDTO.getConfA());
        upData.setConfB(bidPrjScequipDTO.getConfB());
        upData.setuIp(bidPrjScequipDTO.getuIp());

//        List<String> validResult = sidPrjScequipService.validData(BidPrjScequipDTO);
//        if(validResult.size()>0){
//            throw new BidValidationFailException("bidValidError","資料檢核錯誤" ,validResult.toArray(new String[0]));
//        }


        BidPrjScequipDTO result = bidPrjScequipService.save(upData);


        return ResponseEntity
            .created(new URI("/api/bid-prj-scequip/" + result.getWkut() + "/" + result.getPrjno() + "/" + result.getSubCcut() + "/" + result.geteCode()))
            .headers(PwcHeadUtil.createAlert(MessageCodes.PWC_COMMON_0001_S()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getSubCcut()))
            .body(result);
    }

    /**
     * {@code PUT  /bid-prj-scequip/:id} : Updates an existing bidPrjScequip.
     *
     * @param bidPrjScequipDTO the bidPrjScequipDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidPrjScequipDTO,
     * or with status {@code 400 (Bad Request)} if the bidPrjScequipDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bidPrjScequipDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bid-prj-scequip")
    public ResponseEntity<BidPrjScequipDTO> updateBidPrjScequip(
        @Valid @RequestBody BidPrjScequipDTO bidPrjScequipDTO, Authentication authentication
    ) throws URISyntaxException {
        BidPrjScequipPrimaryKey bidPrjScequipPK = new BidPrjScequipPrimaryKey(bidPrjScequipDTO.getWkut(), bidPrjScequipDTO.getPrjno(), bidPrjScequipDTO.getSubCcut(), bidPrjScequipDTO.geteCode());
        log.debug("REST request to update BidPrjScequip : {}", bidPrjScequipDTO);
        log.debug("REST request to update bidPrjScequipPK : {}", bidPrjScequipPK);
        if (bidPrjScequipDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(bidPrjScequipPK, bidPrjScequipDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }


//        List<String> validResult = bidPrjScequipService.validData(bidPrjScequipPK);
//
//        if (validResult.size() > 0) {
//            throw new BidValidationFailException("bidValidError", "資料檢核錯誤", validResult.toArray(new String[0]));
//        }

        bidPrjScequipDTO.setUpdateDate(Instant.now());
        bidPrjScequipDTO.setUpdateUser(authentication.getName());

        BidPrjScequipDTO result = bidPrjScequipService.save(bidPrjScequipDTO);


        return ResponseEntity
            .ok()
            .headers(PwcHeadUtil.createAlert(MessageCodes.PWC_COMMON_0001_S()))
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidPrjScequipDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /bid-prj-scequip/:id} : Partial updates given fields of an existing bidPrjScequip, field will ignore if it is null
     *
     * @param bidPrjScequipDTO the bidPrjScequipDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidPrjScequipDTO,
     * or with status {@code 400 (Bad Request)} if the bidPrjScequipDTO is not valid,
     * or with status {@code 404 (Not Found)} if the bidPrjScequipDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the bidPrjScequipDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/bid-prj-scequip/{id}", consumes = {"application/json", "application/merge-patch+json"})
    public ResponseEntity<BidPrjScequipDTO> partialUpdateBidPrjScequip(
        @NotNull @RequestBody BidPrjScequipDTO bidPrjScequipDTO, Authentication authentication
    ) throws URISyntaxException {

        BidPrjScequipPrimaryKey bidPrjScequipPK = new BidPrjScequipPrimaryKey(bidPrjScequipDTO.getWkut(), bidPrjScequipDTO.getPrjno(), bidPrjScequipDTO.getSubCcut(), bidPrjScequipDTO.geteCode());

        log.debug("REST request to partial update BidPrjScequip partially :, {}", bidPrjScequipDTO);
        if (bidPrjScequipDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(bidPrjScequipPK, bidPrjScequipDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidPrjScequipRepository.existsById(bidPrjScequipPK)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        bidPrjScequipDTO.setUpdateDate(Instant.now());
        bidPrjScequipDTO.setUpdateUser(authentication.getName());
        Optional<BidPrjScequipDTO> result = bidPrjScequipService.partialUpdate(bidPrjScequipDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidPrjScequipDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /bid-prj-scequip} : get all the bidPrjScequips.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bidPrjScequips in body.
     */
    @GetMapping("/bid-prj-scequip")
    public List<BidPrjScequipDTO> getAllBidPrjScequips() {
        log.debug("REST request to get all BidPrjScequips");
        return bidPrjScequipService.findAll();
    }

    /**
     * {@code GET  /bid-prj-scequip/:id} : get the "id" bidPrjScequip.
     *
     * @param bidPrjScequipPrimaryKey the id of the bidPrjScequipDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bidPrjScequipDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bid-prj-scequip/{id}")
    public ResponseEntity<BidPrjScequipDTO> getBidPrjScequip(@PathVariable BidPrjScequipPrimaryKey bidPrjScequipPrimaryKey) {
        log.debug("REST request to get BidPrjScequip : {}", bidPrjScequipPrimaryKey);
        Optional<BidPrjScequipDTO> bidPrjScequipDTO = bidPrjScequipService.findOne(bidPrjScequipPrimaryKey);
        return ResponseUtil.wrapOrNotFound(bidPrjScequipDTO);
    }

    /**
     * {@code DELETE  /bid-prj-scequip/:id} : delete the "id" bidPrjScequip.
     *
     * @param bidPrjScequipPrimaryKey the id of the bidPrjScequipDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bid-prj-scequip/{id}")
    public ResponseEntity<Void> deleteBidPrjScequip(@PathVariable BidPrjScequipPrimaryKey bidPrjScequipPrimaryKey) {
        log.debug("REST request to delete BidPrjScequip : {}", bidPrjScequipPrimaryKey);
        bidPrjScequipService.delete(bidPrjScequipPrimaryKey);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, bidPrjScequipPrimaryKey.toString()))
            .build();
    }


    // 人力需求表 bid-aae-030B

    @PostMapping(path = "/bid-prj-scequip/bidAae003", produces = ReportMediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> bidAae003(@RequestBody BidPrjSubcoPrimaryKey criteria) throws IOException {
        log.debug("REST request to get pdf : {}", criteria);


        BidProjectPrimaryKey bidProjectPrimaryKey = new BidProjectPrimaryKey(criteria.getWkut(), criteria.getPrjno());


        // find VwBidProjectDTO
        VwBidProjectDTO vwBidProjectDTO = vwBidProjectService.findOne(bidProjectPrimaryKey).get();

        // find BidProjectDTO
        BidProjectDTO bidProjectDTO = bidProjectService.findOne(bidProjectPrimaryKey).get();

        // find BidUnit10DTO
        BidUnit10DTO bidUnit10DTO = bidUnit10Service.findOne(criteria.getWkut()).get();

        // find BidPrjSubco
        List<BidPrjSubco> bidPrjSubco = bidPrjSubcoService.findByWkutAndPrjnoAndSubcoNo(criteria.getWkut(), criteria.getPrjno(),criteria.getSubcoNo());


        // find BidPrjScequipDTO
        List<BidPrjScequip> bidPrjScequip = sidPrjScequipService.findByWkutAndPrjnoAndSubCcut(criteria.getWkut(), criteria.getPrjno(),bidPrjSubco.get(0).getSubCcut());

        // find bidPrjSubcoDTO
        List<BidPrjSubcoDTO> bidPrjSubcoDTO = bidPrjSubcoService.gatAllMet(bidProjectPrimaryKey);

        // 縣市鄉鎮
        String place = "";
        String placeNum = vwBidProjectDTO.getPlaceCode();
        if (placeNum != null && placeNum.length() > 0) {
            List<BidPlaceDTO> bidPlaceDTO = bidPlaceService.findMapInfoByPlaceCode(placeNum);
            for (BidPlaceDTO item : bidPlaceDTO) {
                place = item.getPlaceName();
            }
        } else {
            place = "(未填)";
        }

        BidAae003 bidAae003 = new BidAae003();
        bidAae003.setBidPrjSubco(bidPrjSubco);
        bidAae003.setVwBidProjectDTO(vwBidProjectDTO);
        bidAae003.setBidProjectDTO(bidProjectDTO);
        bidAae003.setBidUnit10DTO(bidUnit10DTO);
        bidAae003.setBidPrjScequip(bidPrjScequip);
        bidAae003.setPlace(place);

        byte[] pdfbyte = reportService.printReport(bidAae003);
        return ResponseEntity.ok(pdfbyte);

    }

    @PostMapping("/bid-prj-scequip/getScequipList")
    public List<BidPrjScequip> getBidPrjSubcosList(@RequestBody BidPrjScequipPrimaryKey bidPrjScequipPrimaryKey) {

        List<BidPrjScequip> bidPrjScequip = bidPrjScequipService.findByWkutAndPrjnoAndSubCcut(bidPrjScequipPrimaryKey.getWkut(), bidPrjScequipPrimaryKey.getPrjno(), bidPrjScequipPrimaryKey.getSubCcut());

        System.out.println("bidPrjScequip>>>>>>>>>>>>>>>" + bidPrjScequip);

        return bidPrjScequip;
    }

}
