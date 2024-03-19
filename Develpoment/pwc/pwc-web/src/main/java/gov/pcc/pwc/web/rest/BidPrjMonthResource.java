package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.MessageCodes;
import gov.pcc.pwc.common.enums.bid.BidFormStatusEnum;
import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.common.errors.BidValidationFailException;
import gov.pcc.pwc.common.web.ReportMediaType;
import gov.pcc.pwc.domain.BidPrjMonthPrimaryKey;
import gov.pcc.pwc.domain.BidProjectPrimaryKey;
import gov.pcc.pwc.report.bid.bidAab001.BidAab001;
import gov.pcc.pwc.report.bid.bidAab001.BidAab001Test;
import gov.pcc.pwc.report.bid.bidAab001.BidAab001Test1;
import gov.pcc.pwc.repository.BidPrjMonthRepository;
import gov.pcc.pwc.service.BidPrjMonthService;
import gov.pcc.pwc.service.BidProjectService;
import gov.pcc.pwc.service.ReportService;
import gov.pcc.pwc.service.criteria.BidPrjMonthCriteria;
import gov.pcc.pwc.service.dto.BidPrjMonthDTO;

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
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link gov.pcc.pwc.domain.BidPrjMonth}.
 */
@RestController
@RequestMapping("/api")
public class BidPrjMonthResource {

    private final Logger log = LoggerFactory.getLogger(BidPrjMonthResource.class);

    private static final String ENTITY_NAME = "bidPrjMonth";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BidPrjMonthService bidPrjMonthService;

    private final BidProjectService bidProjectService;

    private final BidPrjMonthRepository bidPrjMonthRepository;

    private final ReportService reportService;

    public BidPrjMonthResource(BidPrjMonthService bidPrjMonthService, BidProjectService bidProjectService, BidPrjMonthRepository bidPrjMonthRepository, ReportService reportService) {
        this.bidPrjMonthService = bidPrjMonthService;
        this.bidProjectService = bidProjectService;
        this.bidPrjMonthRepository = bidPrjMonthRepository;
        this.reportService = reportService;
    }

    /**
     * {@code POST  /bid-prj-months} : Create a new bidPrjMonth.
     *
     * @param bidPrjMonthDTO the bidPrjMonthDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bidPrjMonthDTO, or with status {@code 400 (Bad Request)} if the bidPrjMonth has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bid-prj-months")
    public ResponseEntity<BidPrjMonthDTO> createBidPrjMonth(@Valid @RequestBody BidPrjMonthDTO bidPrjMonthDTO, Authentication authentication) throws URISyntaxException {
        log.debug("REST request to save BidPrjMonth : {}", bidPrjMonthDTO);

        List<String> validResult = bidPrjMonthService.validProjectMonthDistibuteData(bidPrjMonthDTO, BidFormStatusEnum.CREATE);

        if (validResult.size() > 0) {
            throw new BidValidationFailException("bidValidError", "資料檢核錯誤", validResult.toArray(new String[0]));
        }

        bidPrjMonthDTO.setCreateDate(Instant.now());
        bidPrjMonthDTO.setCreateUser(authentication.getName());
        bidPrjMonthDTO.setUpdateDate(Instant.now());
        bidPrjMonthDTO.setUpdateUser(authentication.getName());

        BidPrjMonthDTO result = bidPrjMonthService.save(bidPrjMonthDTO);
        return ResponseEntity
            .created(new URI("/api/bid-prj-months/" + result.getIdString()))
            .headers(PwcHeadUtil.createAlert(MessageCodes.PWC_COMMON_0001_S()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getIdString()))
            .body(result);
    }

    /**
     * {@code PUT  /bid-prj-months/:id} : Updates an existing bidPrjMonth.
     *
     * @param wkut,prjno,yr,mnth the id of the bidPrjMonthDTO to save.
     * @param bidPrjMonthDTO     the bidPrjMonthDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidPrjMonthDTO,
     * or with status {@code 400 (Bad Request)} if the bidPrjMonthDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bidPrjMonthDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bid-prj-months/{wkut}/{prjno}/{yr}/{mnth}")
    public ResponseEntity<BidPrjMonthDTO> updateBidPrjMonth(
        @PathVariable(value = "wkut", required = false) final String wkut,
        @PathVariable(value = "prjno", required = false) final String prjno,
        @PathVariable(value = "year", required = false) final String year,
        @PathVariable(value = "month", required = false) final String month,
        @Valid @RequestBody BidPrjMonthDTO bidPrjMonthDTO
    ) throws URISyntaxException {
        log.debug("REST request to update BidPrjMonth : {}, {},{},{},{}", wkut, prjno, year, month, bidPrjMonthDTO);
        if (bidPrjMonthDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(new BidPrjMonthPrimaryKey(wkut, prjno, year, month), bidPrjMonthDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidPrjMonthRepository.existsById(new BidPrjMonthPrimaryKey(wkut, prjno, year, month))) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        BidPrjMonthDTO result = bidPrjMonthService.save(bidPrjMonthDTO);
        return ResponseEntity
            .ok()
            .headers(PwcHeadUtil.createAlert(MessageCodes.PWC_COMMON_0002_S()))
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidPrjMonthDTO.getIdString()))
            .body(result);
    }

    /**
     * {@code PATCH  /bid-prj-months} : Partial updates given fields of an existing bidPrjMonth, field will ignore if it is null
     *
     * @param bidPrjMonthDTO the bidPrjMonthDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidPrjMonthDTO,
     * or with status {@code 400 (Bad Request)} if the bidPrjMonthDTO is not valid,
     * or with status {@code 404 (Not Found)} if the bidPrjMonthDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the bidPrjMonthDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/bid-prj-months", consumes = {"application/json", "application/merge-patch+json"})
    public ResponseEntity<BidPrjMonthDTO> partialUpdateBidPrjMonth(
        @NotNull @RequestBody BidPrjMonthDTO bidPrjMonthDTO, Authentication authentication
    ) throws URISyntaxException {
        BidPrjMonthPrimaryKey id = new BidPrjMonthPrimaryKey(bidPrjMonthDTO.getWkut(), bidPrjMonthDTO.getPrjno(), bidPrjMonthDTO.getSyr(), bidPrjMonthDTO.getMonth());
        log.debug("REST request to partial update BidPrjMonth partially : {}", bidPrjMonthDTO);
        if (bidPrjMonthDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, bidPrjMonthDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidPrjMonthRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        List<String> validResult = bidPrjMonthService.validProjectMonthDistibuteData(bidPrjMonthDTO, BidFormStatusEnum.UPDATE);

        if (validResult.size() > 0) {
            throw new BidValidationFailException("bidValidError", "資料檢核錯誤", validResult.toArray(new String[0]));
        }

        bidPrjMonthDTO.setUpdateDate(Instant.now());
        bidPrjMonthDTO.setUpdateUser(authentication.getName());

        Optional<BidPrjMonthDTO> result = bidPrjMonthService.partialUpdate(bidPrjMonthDTO);


        if (result.isPresent()) {
            return ResponseEntity
                .ok()
                .headers(PwcHeadUtil.createAlert(MessageCodes.PWC_COMMON_0002_S()))
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidPrjMonthDTO.getIdString()))
                .body(result.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }


    }

    /**
     * {@code GET  /bid-prj-months/:id} : get the "id" bidPrjMonth.
     *
     * @param wkut,prjno,yr,mnth the id of the bidPrjMonthDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bidPrjMonthDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bid-prj-months/{wkut}/{prjno}/{yr}/{mnth}")
    public ResponseEntity<BidPrjMonthDTO> getBidPrjMonth(
        @PathVariable(value = "wkut", required = false) final String wkut,
        @PathVariable(value = "prjno", required = false) final String prjno,
        @PathVariable(value = "yr", required = false) final String yr,
        @PathVariable(value = "mnth", required = false) final String mnth) {
        log.debug("REST request to get BidPrjMonth : {},{},{},{}", wkut, prjno, yr, mnth);
        Optional<BidPrjMonthDTO> bidPrjMonthDTO = bidPrjMonthService.findOne(new BidPrjMonthPrimaryKey(wkut, prjno, yr, mnth));
        return ResponseUtil.wrapOrNotFound(bidPrjMonthDTO);
    }

    /**
     * {@code DELETE  /bid-prj-months/:id} : delete the "id" bidPrjMonth.
     *
     * @param wkut,prjno,yr,mnth the id of the bidPrjMonthDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bid-prj-months/{wkut}/{prjno}/{yr}/{mnth}")
    public ResponseEntity<Void> deleteBidPrjMonth(
        @PathVariable(value = "wkut", required = false) final String wkut,
        @PathVariable(value = "prjno", required = false) final String prjno,
        @PathVariable(value = "yr", required = false) final String syr,
        @PathVariable(value = "mnth", required = false) final String mnth
    ) {
        log.debug("REST request to delete BidPrjMonth : {},{},{},{}", wkut, prjno, syr, mnth);
        bidPrjMonthService.delete(new BidPrjMonthPrimaryKey(wkut, prjno, syr, mnth));
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, new BidPrjMonthPrimaryKey(wkut, prjno, syr, mnth).toString()))
            .build();
    }

    @GetMapping("/bid-prj-months/latest/{wkut}/{prjno}")
    public ResponseEntity<BidPrjMonthDTO> getBidPrjMonthLatest(
        @PathVariable(value = "wkut") final String wkut,
        @PathVariable(value = "prjno") final String prjno) {
        log.debug("REST request to get latest BidPrjMonth : {},{}", wkut, prjno);
        Optional<BidPrjMonthDTO> bidPrjMonthDTO = bidPrjMonthService.findLatestPrjMnth(wkut, prjno);
        return ResponseUtil.wrapOrNotFound(bidPrjMonthDTO);
    }

    @GetMapping("/bid-prj-months/pureLatest/{wkut}/{prjno}")
    public BidPrjMonthDTO getBidPurePrjMonthLatest(
        @PathVariable(value = "wkut") final String wkut,
        @PathVariable(value = "prjno") final String prjno) {
        log.debug("REST request to get latest BidPrjMonth : {},{}", wkut, prjno);
        BidPrjMonthCriteria bidPrjMonthCriteria = new BidPrjMonthCriteria();
        bidPrjMonthCriteria.setPrjno(prjno);
        bidPrjMonthCriteria.setWkut(wkut);
        bidPrjMonthCriteria.setSortBy(new String[]{"yr", "month"});
        List<BidPrjMonthDTO> monthDTOs = bidPrjMonthService.findAllBidPrjMonthListByCriteria(bidPrjMonthCriteria);
        if(!monthDTOs.isEmpty()){
            return monthDTOs.get(0);
        }else{
            return null;
        }
    }


    @GetMapping("/bid-prj-months/all/{wkut}/{prjno}")
    public List<BidPrjMonthDTO> getAllBidProjects(
        @PathVariable(value = "wkut") final String wkut,
        @PathVariable(value = "prjno") final String prjno) {
        log.debug("REST request to get single prj {} {} all BidPrjMonthDTO", wkut, prjno);
        return bidPrjMonthService.findAllBidPrjMonth(wkut, prjno);
    }

    @PostMapping("/bid-prj-months/criteria")
    public Page<BidPrjMonthDTO> getAllBidProjects(@Valid @RequestBody BidPrjMonthCriteria criteria) {
        log.debug("REST request to get single prj's all BidPrjMonthDTO by {}", criteria);
        criteria.setSortBy(new String[]{"yr", "month"});
        criteria.setSortDirection(Sort.Direction.DESC);
        return bidPrjMonthService.findAllBidPrjMonthByCriteria(criteria);
    }

    @PostMapping("/bid-prj-months/list-by-criteria")
    public List<BidPrjMonthDTO> getAllBidProjectsList(@Valid @RequestBody BidPrjMonthCriteria criteria) {
        log.debug("REST request to get single prj's all BidPrjMonthDTO by {}", criteria);
        return bidPrjMonthService.findAllBidPrjMonthListByCriteria(criteria);
    }

    @PostMapping(path = "/bid-prj-months/printMonthPredictReport", produces = ReportMediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> printMonthPredictReport(@Valid @RequestBody BidProjectPrimaryKey projectPK) {
        log.debug("REST request to generate month predict report by pk {}", projectPK);

        List<BidPrjMonthDTO> monthDtos = bidPrjMonthService.findAllBidPrjMonthListByCriteria(new BidPrjMonthCriteria(projectPK.getWkut(),projectPK.getPrjno()));
        BidProjectInfoDTO projectInfoDto = bidProjectService.findBidProjectInfoByWkutAndPrjno(projectPK.getWkut(), projectPK.getPrjno()).orElse(null);

        BidAab001 bidAab001 = new BidAab001();
        bidAab001.setBidProjectDTO(projectInfoDto);
        bidAab001.setBidPrjMonthDTOs(monthDtos);

        return ResponseEntity.ok(reportService.printReport(bidAab001));
    }


    @PostMapping(path = "service/bid-prj-months/testSubreport", produces = ReportMediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> testSubreport(@Valid @RequestBody BidProjectPrimaryKey projectPK) {
        log.debug("REST request to generate month predict report by pk {}", projectPK);

        List<BidPrjMonthDTO> monthDtos = bidPrjMonthService.findAllBidPrjMonthListByCriteria(new BidPrjMonthCriteria(projectPK.getWkut(),projectPK.getPrjno()));
        BidProjectInfoDTO projectInfoDto = bidProjectService.findBidProjectInfoByWkutAndPrjno(projectPK.getWkut(), projectPK.getPrjno()).orElse(null);

        BidAab001Test bidAab001Test = new BidAab001Test();
        bidAab001Test.setBidProjectDTO(projectInfoDto);
        bidAab001Test.setBidPrjMonthDTOs(monthDtos);

        return ResponseEntity.ok(reportService.printReport(bidAab001Test));
    }


}
