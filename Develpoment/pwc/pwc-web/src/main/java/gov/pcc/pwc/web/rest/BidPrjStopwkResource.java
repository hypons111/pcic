package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.MessageCodes;
import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.common.errors.BidValidationFailException;
import gov.pcc.pwc.common.web.ReportMediaType;
import gov.pcc.pwc.domain.BidPrjStopwkPrimaryKey;
import gov.pcc.pwc.domain.BidProjectPrimaryKey;
import gov.pcc.pwc.report.bid.bidAaa004.BidAaa004Recovery;
import gov.pcc.pwc.report.bid.bidAaa004.BidAaa004Stop;
import gov.pcc.pwc.repository.BidPrjStopwkRepository;
import gov.pcc.pwc.service.AdmSysCodeService;
import gov.pcc.pwc.service.BidPrjStopwkService;
import gov.pcc.pwc.service.BidProjectService;
import gov.pcc.pwc.service.ReportService;
import gov.pcc.pwc.service.criteria.AdmSysCodeCriteria;
import gov.pcc.pwc.service.criteria.BidPrjStopwkCriteria;
import gov.pcc.pwc.service.dto.*;

import java.io.IOException;
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
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link gov.pcc.pwc.domain.BidPrjStopwk}.
 */
@RestController
@RequestMapping("/api")
public class BidPrjStopwkResource {

    private final Logger log = LoggerFactory.getLogger(BidPrjStopwkResource.class);

    private static final String ENTITY_NAME = "bidPrjStopwk";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BidPrjStopwkService bidPrjStopwkService;

    private final BidPrjStopwkRepository bidPrjStopwkRepository;

    private final BidProjectService bidProjectService;

    private final ReportService reportService;

    private final AdmSysCodeService admSysCodeService;

    public BidPrjStopwkResource(BidPrjStopwkService bidPrjStopwkService, BidPrjStopwkRepository bidPrjStopwkRepository, BidProjectService bidProjectService, ReportService reportService, AdmSysCodeService admSysCodeService) {
        this.bidPrjStopwkService = bidPrjStopwkService;
        this.bidPrjStopwkRepository = bidPrjStopwkRepository;
        this.bidProjectService = bidProjectService;
        this.reportService = reportService;
        this.admSysCodeService = admSysCodeService;
    }

    /**
     * {@code POST  /bid-prj-stopwks} : Create a new bidPrjStopwk.
     *
     * @param bidPrjStopwkDTO the bidPrjStopwkDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bidPrjStopwkDTO, or with status {@code 400 (Bad Request)} if the bidPrjStopwk has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bid-prj-stopwks")
    public ResponseEntity<BidPrjStopwkDTO> createBidPrjStopwk(@Valid @RequestBody BidPrjStopwkDTO bidPrjStopwkDTO, Authentication authentication)
        throws URISyntaxException {
        log.debug("REST request to save BidPrjStopwk : {}", bidPrjStopwkDTO);
        //log.debug("REST check user Name : {}", authentication.getName());
        if (bidPrjStopwkRepository.existsById(bidPrjStopwkDTO.getId())) {
            throw new BadRequestAlertException("A new bidPrjStopwk ID already exist", ENTITY_NAME, "idAlreadyExists");
        }
        bidPrjStopwkDTO.setCreateUser(authentication.getName());
        bidPrjStopwkDTO.setCreateDate(Instant.now());

        List<String> validResult = bidPrjStopwkService.validData(bidPrjStopwkDTO,"new");

        if(validResult.size()>0){
            throw new BidValidationFailException("bidValidError","資料檢核錯誤" ,validResult.toArray(new String[0]));
        }


        BidPrjStopwkDTO result = bidPrjStopwkService.save(bidPrjStopwkDTO);
        return ResponseEntity
            .created(new URI("/api/bid-prj-stopwks/" + result.getIdString()))
            .headers(PwcHeadUtil.createAlert(MessageCodes.PWC_COMMON_0001_S()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getIdString()))
            .body(result);
    }

    /**
     * {@code PUT  /bid-prj-stopwks/:id} : Updates an existing bidPrjStopwk.
     *
     * @param bidPrjStopwkDTO the bidPrjStopwkDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidPrjStopwkDTO,
     * or with status {@code 400 (Bad Request)} if the bidPrjStopwkDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bidPrjStopwkDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bid-prj-stopwks")
    public ResponseEntity<BidPrjStopwkDTO> updateBidPrjStopwk(
        @Valid @RequestBody BidPrjStopwkDTO bidPrjStopwkDTO,Authentication authentication
    ) throws URISyntaxException {
        BidPrjStopwkPrimaryKey id = new BidPrjStopwkPrimaryKey(bidPrjStopwkDTO.getWkut(),bidPrjStopwkDTO.getPrjno(),bidPrjStopwkDTO.getStartDate());
        log.debug("REST request to update BidPrjStopwk : {}", bidPrjStopwkDTO);

        if (!Objects.equals(id, bidPrjStopwkDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidPrjStopwkRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        List<String> validResult = bidPrjStopwkService.validData(bidPrjStopwkDTO,"update");

        if(validResult.size()>0){
            throw new BidValidationFailException("bidValidError","資料檢核錯誤" ,validResult.toArray(new String[0]));
        }

        bidPrjStopwkDTO.setUpdateUser(authentication.getName());
        bidPrjStopwkDTO.setUpdateDate(Instant.now());

        BidPrjStopwkDTO result = bidPrjStopwkService.save(bidPrjStopwkDTO);
        return ResponseEntity
            .ok()
            .headers(PwcHeadUtil.createAlert(MessageCodes.PWC_COMMON_0002_S()))
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidPrjStopwkDTO.getIdString()))
            .body(result);
    }

    /**
     * {@code PATCH  /bid-prj-stopwks/:id} : Partial updates given fields of an existing bidPrjStopwk, field will ignore if it is null
     * @param bidPrjStopwkDTO the bidPrjStopwkDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidPrjStopwkDTO,
     * or with status {@code 400 (Bad Request)} if the bidPrjStopwkDTO is not valid,
     * or with status {@code 404 (Not Found)} if the bidPrjStopwkDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the bidPrjStopwkDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/bid-prj-stopwks", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<BidPrjStopwkDTO> partialUpdateBidPrjStopwk(
        @NotNull @RequestBody BidPrjStopwkDTO bidPrjStopwkDTO
    ) throws URISyntaxException {
        BidPrjStopwkPrimaryKey id = new BidPrjStopwkPrimaryKey(bidPrjStopwkDTO.getWkut(),bidPrjStopwkDTO.getPrjno(),bidPrjStopwkDTO.getStartDate());
        log.debug("REST request to partial update BidPrjStopwk partially : {}", bidPrjStopwkDTO);
        if (bidPrjStopwkDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, bidPrjStopwkDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidPrjStopwkRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BidPrjStopwkDTO> result = bidPrjStopwkService.partialUpdate(bidPrjStopwkDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidPrjStopwkDTO.getIdString())
        );
    }

    /**
     * {@code GET  /bid-prj-stopwks} : get all the bidPrjStopwks.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bidPrjStopwks in body.
     */
    @GetMapping("/bid-prj-stopwks")
    public List<BidPrjStopwkDTO> getAllBidPrjStopwks() {
        log.debug("REST request to get all BidPrjStopwks");
        return bidPrjStopwkService.findAll();
    }

    /**
     * {@code Post  /bid-prj-stopwks/getOne} : get the "id" bidPrjStopwk.
     *
     * @param id the id of the BidPrjStopwkPrimaryKey.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bidPrjStopwkDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/bid-prj-stopwks/getOne")
    public ResponseEntity<BidPrjStopwkDTO> getBidPrjStopwk(@RequestBody BidPrjStopwkPrimaryKey id) {
        log.debug("REST request to get BidPrjStopwk : {}", id);
        Optional<BidPrjStopwkDTO> bidPrjStopwkDTO = bidPrjStopwkService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bidPrjStopwkDTO);
    }

    /**
     * {@code post  /bid-prj-stopwks/deleteOne} : delete the "id" bidPrjStopwk.
     *
     * @param id the id of the bidPrjStopwkDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @PostMapping("/bid-prj-stopwks/deleteOne")
    public ResponseEntity<Void> deleteBidPrjStopwk(@RequestBody BidPrjStopwkPrimaryKey id) {
        log.debug("REST request to delete BidPrjStopwk : {}", id);
        bidPrjStopwkService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.getPrjno()))
            .build();
    }


    @PostMapping("/bid-prj-stopwks/getProjectAllStopwk")
    public Page<BidPrjStopwkDTO> getProjectAllStopwk(@NotNull @RequestBody BidPrjStopwkCriteria criteria) {
        criteria.setSortBy(new String[]{"startDate"});
        log.debug("REST request to get project All BidPrjStopwk by criteria : {}", criteria);
        return bidPrjStopwkService.findProjectAllStopwkBycriteria(criteria);

    }


    @PostMapping(path = "/bid-prj-stopwks/prjStopwkReport", produces = ReportMediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> bidAaa004(@RequestBody BidPrjStopwkPrimaryKey id) throws IOException {
        log.debug("REST request to get pdf : {}", id);
        BidProjectPrimaryKey projectPK = new BidProjectPrimaryKey(id.getWkut(), id.getPrjno());
        BidProjectInfoDTO bidProjectInfoDTO = bidProjectService.findBidProjectInfoByWkutAndPrjno(id.getWkut(), id.getPrjno()).orElse(null);
        BidProjectDTO bidProjectDTO = bidProjectService.findOne(projectPK).orElse(null);
        BidPrjStopwkDTO bidPrjStopwkDto = bidPrjStopwkService.findOne(id).orElse(null);

        List<BidUnit10DTO> ccuts = bidProjectService.findProjectCCUT(projectPK);
        List<BidUnit10DTO> scuts = bidProjectService.findProjectSCUT(projectPK);
        BidUnit10DTO scut = scuts.size() > 0 ? scuts.get(0) : null;
        BidUnit10DTO ccut = ccuts.size() > 0 ? ccuts.get(0) : null;

        //處理sysCode  協調層級
        AdmSysCodeCriteria modifySysCodeCriteria = new AdmSysCodeCriteria();
        modifySysCodeCriteria.setPerPage(100);
        modifySysCodeCriteria.setModuleType("BID");
        modifySysCodeCriteria.setDataType("BID_012");
        List<AdmSysCodeDTO> sysCode = admSysCodeService.findByCriteria(modifySysCodeCriteria).getContent();


        BidAaa004Stop bidAaa004Stop = new BidAaa004Stop();
        bidAaa004Stop.setBidProjectInfoDTO(bidProjectInfoDTO);
        bidAaa004Stop.setAdmSysCodeDTOs(sysCode);
        bidAaa004Stop.setBidProjectDTO(bidProjectDTO);
        bidAaa004Stop.setBidPrjStopwkDTO(bidPrjStopwkDto);
        bidAaa004Stop.setCcut(ccut);
        bidAaa004Stop.setScut(scut);


        return ResponseEntity.ok(reportService.printReport(bidAaa004Stop));
    }

    @PostMapping(path = "/bid-prj-stopwks/prjStopwkRecoveryReport", produces = ReportMediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> bidAaa004Recovery(@RequestBody BidPrjStopwkPrimaryKey id) throws IOException {
        log.debug("REST request to get pdf : {}", id);
        BidProjectPrimaryKey projectPK = new BidProjectPrimaryKey(id.getWkut(), id.getPrjno());
        BidProjectInfoDTO bidProjectInfoDTO = bidProjectService.findBidProjectInfoByWkutAndPrjno(id.getWkut(), id.getPrjno()).orElse(null);
        BidProjectDTO bidProjectDTO = bidProjectService.findOne(projectPK).orElse(null);
        BidPrjStopwkDTO bidPrjStopwkDto = bidPrjStopwkService.findOne(id).orElse(null);

        List<BidUnit10DTO> ccuts = bidProjectService.findProjectCCUT(projectPK);
        List<BidUnit10DTO> scuts = bidProjectService.findProjectSCUT(projectPK);
        BidUnit10DTO scut = scuts.size() > 0 ? scuts.get(0) : null;
        BidUnit10DTO ccut = ccuts.size() > 0 ? ccuts.get(0) : null;


        BidAaa004Recovery bidAaa004Recovery = new BidAaa004Recovery();
        bidAaa004Recovery.setBidProjectInfoDTO(bidProjectInfoDTO);
        bidAaa004Recovery.setBidProjectDTO(bidProjectDTO);
        bidAaa004Recovery.setBidPrjStopwkDTO(bidPrjStopwkDto);
        bidAaa004Recovery.setCcut(ccut);
        bidAaa004Recovery.setScut(scut);


        return ResponseEntity.ok(reportService.printReport(bidAaa004Recovery));
    }
}
