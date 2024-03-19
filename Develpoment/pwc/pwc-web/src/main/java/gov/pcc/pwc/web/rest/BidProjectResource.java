package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.MessageCodes;
import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.common.errors.BidValidationFailException;
import gov.pcc.pwc.common.web.ReportMediaType;
import gov.pcc.pwc.domain.BidProjectFilePrimaryKey;
import gov.pcc.pwc.domain.BidProjectPrimaryKey;
import gov.pcc.pwc.report.bid.bidAae001.BidAae001;
import gov.pcc.pwc.report.bid.bidAae002.BidAae002;
import gov.pcc.pwc.repository.BidProjectRepository;
import gov.pcc.pwc.service.*;
import gov.pcc.pwc.service.criteria.BidPrjPayCriteria;
import gov.pcc.pwc.service.criteria.BidProjectCriteria;
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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link gov.pcc.pwc.domain.BidProject}.
 */
@RestController
@RequestMapping("/api")
public class BidProjectResource {

    private final Logger log = LoggerFactory.getLogger(BidProjectResource.class);

    private static final String ENTITY_NAME = "bidProject";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BidProjectService bidProjectService;

    private final BidProjectRepository bidProjectRepository;

    private final BidProjectUnitService bidProjectUnitService;

    private final BidProjectFileService bidProjectFileService;

    private final ReportService reportService;

    public BidProjectResource(BidProjectService bidProjectService, BidProjectRepository bidProjectRepository, BidProjectUnitService bidProjectUnitService, BidProjectFileService bidProjectFileService, ReportService reportService) {
        this.bidProjectService = bidProjectService;
        this.bidProjectRepository = bidProjectRepository;
        this.bidProjectUnitService = bidProjectUnitService;
        this.bidProjectFileService = bidProjectFileService;
        this.reportService = reportService;
    }

    /**
     * {@code POST  /bid-projects} : Create a new bidProject.
     *
     * @param bidProjectDTO the bidProjectDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bidProjectDTO, or with status {@code 400 (Bad Request)} if the bidProject has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bid-projects")
    public ResponseEntity<BidProjectDTO> createBidProject(
        @RequestBody BidProjectDTO bidProjectDTO,
        Authentication authentication
    ) throws URISyntaxException {
        log.debug("REST request to save BidProject : {}", bidProjectDTO);
        if (bidProjectRepository.existsById(bidProjectDTO.getId())) {
            throw new BadRequestAlertException("A new bidProject ID already exist", ENTITY_NAME, "idAlreadyExists");
        }
        List<String> validResult = bidProjectService.validCreateData(bidProjectDTO);
        if(validResult.size() > 0){
            throw new BidValidationFailException("bidValidError","資料檢核錯誤" ,validResult.toArray(new String[0]));
        }
        bidProjectDTO.setCreateDate(Instant.now());
        bidProjectDTO.setCreateUser(authentication.getName());
        BidProjectDTO result = bidProjectService.save(bidProjectDTO);
        return ResponseEntity
            .created(new URI("/api/bid-projects/" + result.getPrjno()))
            .headers(PwcHeadUtil.createAlert(MessageCodes.PWC_COMMON_0001_S()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getPrjno()))
            .body(result);
    }

    /**
     * {@code PUT  /bid-projects/:prjno} : Updates an existing bidProject.
     *
     * @param bidProjectDTO the bidProjectDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidProjectDTO,
     * or with status {@code 400 (Bad Request)} if the bidProjectDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bidProjectDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping(path = "/bid-projects", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<BidProjectDTO> updateBidProject(
        @RequestPart("form") BidProjectDTO bidProjectDTO,
        @RequestPart(name = "ckpFile", required = false) MultipartFile ckpFile,
        @RequestPart(name = "ckaFile", required = false) MultipartFile ckaFile,
        @RequestPart(name = "ckdFile", required = false) MultipartFile ckdFile,
        Authentication authentication
    ) throws URISyntaxException {
        log.debug("REST request to partial update BidProject partially : {}", bidProjectDTO);
        if (bidProjectDTO.getPrjno() == null || bidProjectDTO.getWkut() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        if (!bidProjectRepository.existsById(new BidProjectPrimaryKey(bidProjectDTO.getWkut(),bidProjectDTO.getPrjno()))) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        bidProjectDTO.setUpdateDate(Instant.now());
        bidProjectDTO.setUpdateUser(authentication.getName());
        Optional<BidProjectDTO> result = bidProjectService.partialUpdate(bidProjectDTO);
        // BID_PROJECT_UNIT
        bidProjectUnitService.saveDsutUnit(bidProjectDTO);
        bidProjectUnitService.saveScutUnit(bidProjectDTO);
        bidProjectUnitService.saveCcutUnit(bidProjectDTO);
        // BID_PROJECT_FILE
        if (ckpFile != null) {
            BidProjectFilePrimaryKey ckpKey = new BidProjectFilePrimaryKey(bidProjectDTO.getWkut(), bidProjectDTO.getPrjno(), "1", "1");
            Optional<BidProjectFileDTO> ckpResult = bidProjectFileService.findOne(ckpKey);
            BidProjectFileDTO ckpFileDTO = new BidProjectFileDTO();
            if (ckpResult.isPresent()) {
                ckpFileDTO = ckpResult.get();
                ckpFileDTO.setUpdateDate(bidProjectDTO.getUpdateDate());
                ckpFileDTO.setUpdateUser(bidProjectDTO.getUpdateUser());
            } else {
                ckpFileDTO.setId(ckpKey);
                ckpFileDTO.setCreateDate(bidProjectDTO.getUpdateDate());
                ckpFileDTO.setCreateUser(bidProjectDTO.getUpdateUser());
            }
            bidProjectFileService.saveFile(ckpFileDTO, ckpFile);
        }
        if (ckaFile != null) {
            BidProjectFilePrimaryKey ckaKey = new BidProjectFilePrimaryKey(bidProjectDTO.getWkut(), bidProjectDTO.getPrjno(), "2", "1");
            Optional<BidProjectFileDTO> ckaResult = bidProjectFileService.findOne(ckaKey);
            BidProjectFileDTO ckaFileDTO = new BidProjectFileDTO();
            if (ckaResult.isPresent()) {
                ckaFileDTO = ckaResult.get();
                ckaFileDTO.setUpdateDate(bidProjectDTO.getUpdateDate());
                ckaFileDTO.setUpdateUser(bidProjectDTO.getUpdateUser());
            } else {
                ckaFileDTO.setId(ckaKey);
                ckaFileDTO.setCreateDate(bidProjectDTO.getUpdateDate());
                ckaFileDTO.setCreateUser(bidProjectDTO.getUpdateUser());
            }
            bidProjectFileService.saveFile(ckaFileDTO, ckaFile);
        }
        if (ckdFile != null) {
            BidProjectFilePrimaryKey ckdKey = new BidProjectFilePrimaryKey(bidProjectDTO.getWkut(), bidProjectDTO.getPrjno(), "3", "1");
            Optional<BidProjectFileDTO> ckdResult = bidProjectFileService.findOne(ckdKey);
            BidProjectFileDTO ckdFileDTO = new BidProjectFileDTO();
            if (ckdResult.isPresent()) {
                ckdFileDTO = ckdResult.get();
                ckdFileDTO.setUpdateDate(bidProjectDTO.getUpdateDate());
                ckdFileDTO.setUpdateUser(bidProjectDTO.getUpdateUser());
            } else {
                ckdFileDTO.setId(ckdKey);
                ckdFileDTO.setCreateDate(bidProjectDTO.getUpdateDate());
                ckdFileDTO.setCreateUser(bidProjectDTO.getUpdateUser());
            }
            bidProjectFileService.saveFile(ckdFileDTO, ckdFile);
        }
        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidProjectDTO.getPrjno())
        );
    }

    /**
     * update BidProject
     * */
    @PutMapping("/bid-projects/updateOne")
    public ResponseEntity<BidProjectDTO> updateBidProjectData(@RequestBody BidProjectDTO bidProjectDTO)throws URISyntaxException{
        log.debug("REST request to update BidProject : {}",  bidProjectDTO);
        BidProjectPrimaryKey bidProjectPrimaryKey = new BidProjectPrimaryKey(bidProjectDTO.getWkut(),bidProjectDTO.getPrjno());
        if (bidProjectDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        if (!Objects.equals(bidProjectPrimaryKey, bidProjectDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidProjectRepository.existsById(bidProjectPrimaryKey)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        BidProjectDTO result = bidProjectService.save(bidProjectDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidProjectDTO.getId().toString()))
            .body(result);
    }
    /**
     * {@code PATCH  /bid-projects/:prjno} : Partial updates given fields of an existing bidProject, field will ignore if it is null
     *
     * @param bidProjectDTO the bidProjectDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidProjectDTO,
     * or with status {@code 400 (Bad Request)} if the bidProjectDTO is not valid,
     * or with status {@code 404 (Not Found)} if the bidProjectDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the bidProjectDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/bid-projects", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<BidProjectDTO> partialUpdateBidProject(
        @NotNull @RequestBody BidProjectDTO bidProjectDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update BidProject partially : {}", bidProjectDTO);
        if (bidProjectDTO.getPrjno() == null || bidProjectDTO.getWkut() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!bidProjectRepository.existsById(new BidProjectPrimaryKey(bidProjectDTO.getWkut(),bidProjectDTO.getPrjno()))) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        List<String> validResult = bidProjectService.validCreateData(bidProjectDTO);
        if(validResult.size() > 0){
            throw new BidValidationFailException("bidValidError","資料檢核錯誤" ,validResult.toArray(new String[0]));
        }
        Optional<BidProjectDTO> result = bidProjectService.partialUpdate(bidProjectDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidProjectDTO.getPrjno())
        );
    }

    /**
     * {@code GET  /bid-projects} : get all the bidProjects.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bidProjects in body.
     */
    @GetMapping("/bid-projects")
    public List<BidProjectDTO> getAllBidProjects() {
        log.debug("REST request to get all BidProjects");
        return bidProjectService.findAll();
    }

    /**
     * {@code GET  /bid-projects/:id} : get the "id" bidProject.
     *
     * @param wkut,prjno the id of the bidProjectDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bidProjectDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bid-projects/{wkut}/{prjno}")
    public ResponseEntity<BidProjectDTO> getBidProject(@PathVariable final String wkut,@PathVariable final String prjno) {
        log.debug("REST request to get BidProject : {},{}", wkut+",",prjno);
        Optional<BidProjectDTO> bidProjectDTO = bidProjectService.findOne(new BidProjectPrimaryKey(wkut,prjno));
        return ResponseUtil.wrapOrNotFound(bidProjectDTO);
    }

    @GetMapping("/bid-projectInfo/{wkut}/{prjno}")
    public ResponseEntity<BidProjectInfoDTO> getBidProjectInfo(@PathVariable final String wkut, @PathVariable final String prjno) {
        log.debug("REST request to get BidProject : {},{}", wkut,prjno);
        Optional<BidProjectInfoDTO> bidProjectDTO = bidProjectService.findBidProjectInfoByWkutAndPrjno(wkut,prjno);
        return ResponseUtil.wrapOrNotFound(bidProjectDTO);
    }

    /**
     * {@code DELETE  /bid-projects/:id} : delete the "id" bidProject.
     *
     * @param wkut,prjno  the id of the bidProjectDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bid-projects/{id}")
    public ResponseEntity<Void> deleteBidProject(@PathVariable final String wkut,@PathVariable final String prjno) {
        log.debug("REST request to delete BidProject : {},{}", wkut,prjno);
        bidProjectService.delete(new BidProjectPrimaryKey(wkut,prjno));
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME,wkut+prjno)).build();
    }

    @PostMapping("/bid-projects/dust")
    public List<BidUnit10DTO> findProjectDSUT(@RequestBody BidProjectPrimaryKey id){
        return bidProjectService.findProjectDSUT(id);
    }

    @PostMapping("/bid-projects/scut")
    public List<BidUnit10DTO> findProjectSCUT(@RequestBody BidProjectPrimaryKey id){
        return bidProjectService.findProjectSCUT(id);
    }

    @PostMapping("/bid-projects/ccut")
    public List<BidUnit10DTO> findProjectCCUT(@RequestBody BidProjectPrimaryKey id){
        return bidProjectService.findProjectCCUT(id);
    }

    @PostMapping("/bid-projects/criteria/list")
    public List<BidProjectDTO> getBidProjectListByCriteria(@Valid  @RequestBody BidProjectCriteria criteria){
        criteria.setSortBy(new String[]{"prjno"});
        log.debug("REST request to get BidProject by criteria : {}", criteria);
        return bidProjectService.findProjectListByCriteria(criteria);
    }

    @PostMapping("/bid-projects/criteria")
    public Page<BidProjectDTO> getBidProjectByCriteria(@Valid  @RequestBody BidProjectCriteria criteria){
        criteria.setSortBy(new String[]{"prjno"});
        log.debug("REST request to get BidProject by criteria : {}", criteria);
        return bidProjectService.findProjectByCriteria(criteria);
    }

    @PostMapping(path = "/bid-projects/bidAae001", produces = ReportMediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> bidAae001(@RequestBody BidPrjPayCriteria bidPrjPayCriteria) throws IOException {
        log.debug("REST request to get pdf : {}", bidPrjPayCriteria);
        BidProjectBaseDTO bidProjectBaseDTO = bidProjectService.findBidProjectBaseByWkutAndPrjno(bidPrjPayCriteria.getWkut(), bidPrjPayCriteria.getPrjno());

        BidAae001 bidAae001 = new BidAae001();
        bidAae001.setBidProjectBaseDTO(bidProjectBaseDTO);

        byte[] pdfbyte = reportService.printReport(bidAae001);
        return ResponseEntity.ok(pdfbyte);
    }

    @PostMapping(path = "/bid-projects/bidAae002", produces = ReportMediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> bidAae002(@RequestBody BidPrjPayCriteria bidPrjPayCriteria) throws IOException {
        log.debug("REST request to get pdf : {}", bidPrjPayCriteria);
        BidProjectPrimaryKey bidProjectPK = new BidProjectPrimaryKey(bidPrjPayCriteria.getWkut(), bidPrjPayCriteria.getPrjno());
        BidProjectInfoDTO bidProjectInfoDTO = bidProjectService.findBidProjectInfoByWkutAndPrjno(bidPrjPayCriteria.getWkut(), bidPrjPayCriteria.getPrjno()).get();
        BidProjectDTO bidProjectDTO = bidProjectService.findOne(bidProjectPK).get();
        List<BidUnit10DTO> ccuts = bidProjectService.findProjectCCUT(bidProjectPK);
        List<BidUnit10DTO> sucts = bidProjectService.findProjectSCUT(bidProjectPK);

        BidAae002 bidAae002 = new BidAae002();
        bidAae002.setBidProjectInfoDTO(bidProjectInfoDTO);
        bidAae002.setBidProjectDTO(bidProjectDTO);
        bidAae002.setCcuts(ccuts);
        bidAae002.setScuts(sucts);

        byte[] pdfbyte = reportService.printReport(bidAae002);
        return ResponseEntity.ok(pdfbyte);
    }

}
