package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.MessageCodes;
import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.common.errors.BidValidationFailException;
import gov.pcc.pwc.common.web.ReportMediaType;
import gov.pcc.pwc.domain.BidPrjModifyPrimaryKey;
import gov.pcc.pwc.report.bid.bidAaa003.BidAaa003;
import gov.pcc.pwc.repository.BidPrjModifyRepository;
import gov.pcc.pwc.service.AdmSysCodeService;
import gov.pcc.pwc.service.BidPrjModifyService;
import gov.pcc.pwc.service.BidProjectService;
import gov.pcc.pwc.service.ReportService;
import gov.pcc.pwc.service.criteria.AdmSysCodeCriteria;
import gov.pcc.pwc.service.criteria.BidPrjModifyCriteria;
import gov.pcc.pwc.service.dto.AdmSysCodeDTO;
import gov.pcc.pwc.service.dto.BidPrjModifyDTO;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import gov.pcc.pwc.service.dto.BidProjectInfoDTO;
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
 * REST controller for managing {@link gov.pcc.pwc.domain.BidPrjModify}.
 */
@RestController
@RequestMapping("/api")
public class BidPrjModifyResource {

    private final Logger log = LoggerFactory.getLogger(BidPrjModifyResource.class);

    private static final String ENTITY_NAME = "bidPrjModify";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BidPrjModifyService bidPrjModifyService;

    private final BidProjectService bidProjectService;

    private final BidPrjModifyRepository bidPrjModifyRepository;

    private final ReportService reportService;

    private final AdmSysCodeService admSysCodeService;

    public BidPrjModifyResource(BidPrjModifyService bidPrjModifyService, BidProjectService bidProjectService, BidPrjModifyRepository bidPrjModifyRepository, ReportService reportService, AdmSysCodeService admSysCodeService) {
        this.bidPrjModifyService = bidPrjModifyService;
        this.bidProjectService = bidProjectService;
        this.bidPrjModifyRepository = bidPrjModifyRepository;
        this.reportService = reportService;
        this.admSysCodeService = admSysCodeService;
    }

    /**
     * {@code POST  /bid-prj-modifies} : Create a new bidPrjModify.
     *
     * @param bidPrjModifyDTO the bidPrjModifyDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bidPrjModifyDTO, or with status {@code 400 (Bad Request)} if the bidPrjModify has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bid-prj-modifies")
    public ResponseEntity<BidPrjModifyDTO> createBidPrjModify(@Valid @RequestBody BidPrjModifyDTO bidPrjModifyDTO, Authentication authentication)
        throws URISyntaxException {

        List<String> validResult = bidPrjModifyService.validData(bidPrjModifyDTO);

        if(validResult.size()>0){
            throw new BidValidationFailException("bidValidError","資料檢核錯誤" ,validResult.toArray(new String[0]));
        }

        bidPrjModifyDTO.setCreateDate(Instant.now());
        bidPrjModifyDTO.setCreateUser(authentication.getName());
        bidPrjModifyDTO.setUpdateDate(Instant.now());
        bidPrjModifyDTO.setUpdateUser(authentication.getName());

        BidPrjModifyDTO result = bidPrjModifyService.save(bidPrjModifyDTO);
        return ResponseEntity
            .created(new URI("/api/bid-prj-modifies/" + result.getWkut() + "/"+result.getPrjno()+"/"+result.getChgDate().toString()))
            .headers(PwcHeadUtil.createAlert(MessageCodes.PWC_COMMON_0001_S()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME,result.getChgDate().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bid-prj-modifies/:id} : Updates an existing bidPrjModify.
     *
     * @param bidPrjModifyDTO the bidPrjModifyDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidPrjModifyDTO,
     * or with status {@code 400 (Bad Request)} if the bidPrjModifyDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bidPrjModifyDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bid-prj-modifies")
    public ResponseEntity<BidPrjModifyDTO> updateBidPrjModify(
        @Valid @RequestBody BidPrjModifyDTO bidPrjModifyDTO, Authentication authentication
    ) throws URISyntaxException {
        BidPrjModifyPrimaryKey bidPrjModifyPK = new BidPrjModifyPrimaryKey(bidPrjModifyDTO.getWkut(), bidPrjModifyDTO.getPrjno(), bidPrjModifyDTO.getChgDate());
        log.debug("REST request to update BidPrjModify : {}", bidPrjModifyDTO);
        if (bidPrjModifyDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(bidPrjModifyPK, bidPrjModifyDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidPrjModifyRepository.existsById(bidPrjModifyPK)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        List<String> validResult = bidPrjModifyService.validData(bidPrjModifyDTO);

        if(validResult.size()>0){
            throw new BidValidationFailException("bidValidError","資料檢核錯誤" ,validResult.toArray(new String[0]));
        }

        bidPrjModifyDTO.setUpdateDate(Instant.now());
        bidPrjModifyDTO.setUpdateUser(authentication.getName());

        BidPrjModifyDTO result = bidPrjModifyService.save(bidPrjModifyDTO);

        return ResponseEntity
            .ok()
            .headers(PwcHeadUtil.createAlert(MessageCodes.PWC_COMMON_0001_S()))
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidPrjModifyDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /bid-prj-modifies/:id} : Partial updates given fields of an existing bidPrjModify, field will ignore if it is null
     *
     * @param bidPrjModifyDTO the bidPrjModifyDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidPrjModifyDTO,
     * or with status {@code 400 (Bad Request)} if the bidPrjModifyDTO is not valid,
     * or with status {@code 404 (Not Found)} if the bidPrjModifyDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the bidPrjModifyDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/bid-prj-modifies", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<BidPrjModifyDTO> partialUpdateBidPrjModify(
        @NotNull @RequestBody BidPrjModifyDTO bidPrjModifyDTO,Authentication authentication
    ) throws URISyntaxException {
        BidPrjModifyPrimaryKey bidPrjModifyPK = new BidPrjModifyPrimaryKey(bidPrjModifyDTO.getWkut(), bidPrjModifyDTO.getPrjno(), bidPrjModifyDTO.getChgDate());
        log.debug("REST request to partial update BidPrjModify partially : {}", bidPrjModifyDTO);
        if (bidPrjModifyDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(bidPrjModifyPK, bidPrjModifyDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidPrjModifyRepository.existsById(bidPrjModifyPK)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        bidPrjModifyDTO.setUpdateDate(Instant.now());
        bidPrjModifyDTO.setUpdateUser(authentication.getName());

        Optional<BidPrjModifyDTO> result = bidPrjModifyService.partialUpdate(bidPrjModifyDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidPrjModifyDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /bid-prj-modifies} : get all the bidPrjModifies.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bidPrjModifies in body.
     */
    @GetMapping("/bid-prj-modifies")
    public List<BidPrjModifyDTO> getAllBidPrjModifies() {
        log.debug("REST request to get all BidPrjModifies");
        return bidPrjModifyService.findAll();
    }

    /**
     * {@code Post  /bid-prj-modifies/getOne} : get the "id" bidPrjModify.
     *
     * @param bidPrjModifyPrimaryKey  the id of the bidPrjModifyDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bidPrjModifyDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/bid-prj-modifies/getOne")
    public ResponseEntity<BidPrjModifyDTO> getBidPrjModify(@RequestBody BidPrjModifyPrimaryKey bidPrjModifyPrimaryKey) {
        log.debug("REST request to get BidPrjModify : {}", bidPrjModifyPrimaryKey);
        Optional<BidPrjModifyDTO> bidPrjModifyDTO = bidPrjModifyService.findOne(bidPrjModifyPrimaryKey);
        return ResponseUtil.wrapOrNotFound(bidPrjModifyDTO);
    }

    @PostMapping("/bid-prj-modifies/getProjectModifyPage")
    public Page<BidPrjModifyDTO> getProjectAllModify(@RequestBody BidPrjModifyCriteria bidPrjModifyCriteria) {
        bidPrjModifyCriteria.setSortBy(new String[]{"chgDate"});
        log.debug("REST request to get BidPrjModify : {}", bidPrjModifyCriteria);
        return bidPrjModifyService.findProjectAllModify(bidPrjModifyCriteria);
    }


    /**
     * {@code DELETE  /bid-prj-modifies/:id} : delete the "id" bidPrjModify.
     *
     * @param bidPrjModifyPrimaryKey the id of the bidPrjModifyDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bid-prj-modifies")
    public ResponseEntity<Void> deleteBidPrjModify(@RequestBody BidPrjModifyPrimaryKey bidPrjModifyPrimaryKey) {
        log.debug("REST request to delete BidPrjModify : {}", bidPrjModifyPrimaryKey);
        bidPrjModifyService.delete(bidPrjModifyPrimaryKey);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, bidPrjModifyPrimaryKey.toString()))
            .build();
    }

    @PostMapping("/bid-prj-modifies/mergeProject/{mergePrjno}")
    public ResponseEntity<BidPrjModifyDTO> createBidPrjModifyForProjectMerge(@Valid @RequestBody BidPrjModifyDTO bidPrjModifyDTO,
                                                                             @PathVariable(value = "mergePrjno") final String mergePrjno,
                                                                             Authentication authentication)
        throws Exception {

        List<String> validResult = bidPrjModifyService.validDataForMergeProject(bidPrjModifyDTO,mergePrjno);

        if(validResult.size()>0){
            throw new BidValidationFailException("bidValidError","資料檢核錯誤" ,validResult.toArray(new String[0]));
        }

        BidPrjModifyDTO result = bidPrjModifyService.mergeProjectAndSavePrjModify(bidPrjModifyDTO,mergePrjno,authentication.getName());

        return ResponseEntity
            .created(new URI("/api/bid-prj-modifies/" + result.getWkut() + "/"+result.getPrjno()+"/"+result.getChgDate().toString()))
            .headers(PwcHeadUtil.createAlert(MessageCodes.PWC_COMMON_0001_S()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME,result.getChgDate().toString()))
            .body(result);
    }



    @PostMapping(path = "/bid-prj-modifies/prjModifyReport", produces = ReportMediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> bidAaa003(@RequestBody BidPrjModifyCriteria bidPrjModifyCriteria) throws IOException {
        log.debug("REST request to get pdf : {}", bidPrjModifyCriteria);
        BidProjectInfoDTO bidProjectDTO = bidProjectService.findBidProjectInfoByWkutAndPrjno(bidPrjModifyCriteria.getWkut(), bidPrjModifyCriteria.getPrjno()).get();
        List<BidPrjModifyDTO> bidPrjModifys = bidPrjModifyService.findProjectAllModifyList(bidPrjModifyCriteria);
        AdmSysCodeCriteria modifySysCodeCriteria = new AdmSysCodeCriteria();
        modifySysCodeCriteria.setPerPage(100);
        modifySysCodeCriteria.setModuleType("BID");
        modifySysCodeCriteria.setDataType("BID_010");
        List<AdmSysCodeDTO> sysCode = admSysCodeService.findByCriteria(modifySysCodeCriteria).getContent();
        BidAaa003 bidAaa003 = new BidAaa003();
        bidAaa003.setBidProjectDTO(bidProjectDTO);
        bidAaa003.setBidPrjModifyDTO(bidPrjModifys);
        bidAaa003.setAdmSysCodeDTOs(sysCode);

        return ResponseEntity.ok(reportService.printReport(bidAaa003));
    }
}


