package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.common.web.ReportMediaType;
import gov.pcc.pwc.domain.BidGovCheckPrimaryKey;
import gov.pcc.pwc.report.bid.bidAae034.BidAae034D;
import gov.pcc.pwc.repository.BidGovCheckRepository;
import gov.pcc.pwc.service.BidGovCheckService;
import gov.pcc.pwc.service.BidGovCheckerrService;
import gov.pcc.pwc.service.BidProjectService;
import gov.pcc.pwc.service.ReportService;
import gov.pcc.pwc.service.criteria.BidGovCheckCriteria;
import gov.pcc.pwc.service.criteria.BidGovCheckerrCriteria;
import gov.pcc.pwc.service.dto.BidGovCheckDTO;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import gov.pcc.pwc.service.dto.BidGovCheckerrDTO;
import gov.pcc.pwc.service.dto.BidProjectInfoDTO;
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
 * REST controller for managing {@link gov.pcc.pwc.domain.BidGovCheck}.
 */
@RestController
@RequestMapping("/api")
public class BidGovCheckResource {

    private final Logger log = LoggerFactory.getLogger(BidGovCheckResource.class);

    private static final String ENTITY_NAME = "bidGovCheck";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BidGovCheckService bidGovCheckService;

    private final BidGovCheckRepository bidGovCheckRepository;

    private final BidProjectService bidProjectService;

    private final BidGovCheckerrService bidGovCheckerrService;

    private final ReportService reportService;

    public BidGovCheckResource(BidGovCheckService bidGovCheckService, BidGovCheckRepository bidGovCheckRepository, BidProjectService bidProjectService, BidGovCheckerrService bidGovCheckerrService, ReportService reportService) {
        this.bidGovCheckService = bidGovCheckService;
        this.bidGovCheckRepository = bidGovCheckRepository;
        this.bidProjectService = bidProjectService;
        this.bidGovCheckerrService = bidGovCheckerrService;
        this.reportService = reportService;
    }

    /**
     * {@code POST  /bid-gov-checks} : Create a new bidGovCheck.
     *
     * @param bidGovCheckDTO the bidGovCheckDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bidGovCheckDTO, or with status {@code 400 (Bad Request)} if the bidGovCheck has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bid-gov-checks")
    public ResponseEntity<BidGovCheckDTO> createBidGovCheck(
        @RequestBody BidGovCheckDTO bidGovCheckDTO,
        Authentication authentication
    ) throws URISyntaxException {
        log.debug("REST request to save BidGovCheck : {}", bidGovCheckDTO);
        BidGovCheckPrimaryKey bidGovCheckPK = new BidGovCheckPrimaryKey(bidGovCheckDTO.getWkut(), bidGovCheckDTO.getPrjno(), bidGovCheckDTO.getCheckDate());
        if (bidGovCheckRepository.existsById(bidGovCheckPK)) {
            throw new BadRequestAlertException("A new bidGovCheck cannot already have an ID", ENTITY_NAME, "idexists");
        }
        bidGovCheckDTO.setCreateUser(authentication.getName());
        bidGovCheckDTO.setCreateDate(Instant.now());
        BidGovCheckDTO result = bidGovCheckService.save(bidGovCheckDTO);
        return ResponseEntity
            .created(new URI("/api/bid-gov-checks/" + result.getWkut() + "/" + result.getPrjno() + "/" + result.getCheckDate()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bid-gov-checks} : Updates an existing bidGovCheck.
     *
     * @param bidGovCheckDTO the bidGovCheckDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidGovCheckDTO,
     * or with status {@code 400 (Bad Request)} if the bidGovCheckDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bidGovCheckDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bid-gov-checks")
    public ResponseEntity<BidGovCheckDTO> updateBidGovCheck(
        @Valid @RequestBody BidGovCheckDTO bidGovCheckDTO
    ) throws URISyntaxException {
        log.debug("REST request to update BidGovCheck : {}", bidGovCheckDTO);
        BidGovCheckPrimaryKey bidGovCheckPK = new BidGovCheckPrimaryKey(bidGovCheckDTO.getWkut(), bidGovCheckDTO.getPrjno(), bidGovCheckDTO.getCheckDate());
        if (bidGovCheckDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(bidGovCheckPK, bidGovCheckDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidGovCheckRepository.existsById(bidGovCheckPK)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        BidGovCheckDTO result = bidGovCheckService.save(bidGovCheckDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidGovCheckDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /bid-gov-checks} : Partial updates given fields of an existing bidGovCheck, field will ignore if it is null
     *
     * @param bidGovCheckDTO the bidGovCheckDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidGovCheckDTO,
     * or with status {@code 400 (Bad Request)} if the bidGovCheckDTO is not valid,
     * or with status {@code 404 (Not Found)} if the bidGovCheckDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the bidGovCheckDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/bid-gov-checks", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<BidGovCheckDTO> partialUpdateBidGovCheck(
        @NotNull @RequestBody BidGovCheckDTO bidGovCheckDTO,
        Authentication authentication
    ) throws URISyntaxException {
        log.debug("REST request to partial update BidGovCheck partially : {}", bidGovCheckDTO);
        BidGovCheckPrimaryKey bidGovCheckPK = new BidGovCheckPrimaryKey(bidGovCheckDTO.getWkut(), bidGovCheckDTO.getPrjno(), bidGovCheckDTO.getCheckDate());
        if (bidGovCheckDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(bidGovCheckPK, bidGovCheckDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }
        if (!bidGovCheckRepository.existsById(bidGovCheckPK)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        bidGovCheckDTO.setUpdateUser(authentication.getName());
        bidGovCheckDTO.setUpdateDate(Instant.now());
        Optional<BidGovCheckDTO> result = bidGovCheckService.partialUpdate(bidGovCheckDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidGovCheckDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /bid-gov-checks} : get all the bidGovChecks.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bidGovChecks in body.
     */
    @GetMapping("/bid-gov-checks")
    public List<BidGovCheckDTO> getAllBidGovChecks() {
        log.debug("REST request to get all BidGovChecks");
        return bidGovCheckService.findAll();
    }

    /**
     * {@code POST  BigDecimal} : get bidGovCheck by criteria.
     *
     * @param criteria the criteria of the BidGovCheckCriteria to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the BidGovCheckDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/bid-gov-checks/criteria-jpa")
    public Page<BidGovCheckDTO> getBidGovCheckByCriteria(@Valid @RequestBody BidGovCheckCriteria criteria) {
        log.debug("REST request to get BidGovCheckDTO : {}", criteria);
        return bidGovCheckService.findByCriteria(criteria);
    }

    /**
     * {@code POST  /bid-gov-checks/getByCriteria} : get bidGovCheck by criteria.
     *
     * @param criteria the criteria of the BidGovCheckCriteria to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the BidGovCheckDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/bid-gov-checks/getByCriteria")
    public Page<BidGovCheckDTO> getBidGovCheckListByCriteria(@Valid @RequestBody BidGovCheckCriteria criteria) {
        log.debug("REST request to get BidGovCheckDTO : {}", criteria);
        return bidGovCheckService.findListByCriteria(criteria);
    }

    @PostMapping("/bid-gov-checks/getPageByCriteria")
    public Page<BidGovCheckDTO> getBidGovCheckPageByCriteria(@RequestBody BidGovCheckCriteria criteria) {
        log.debug("REST request to get BidGovCheckDTO : {}", criteria);
        criteria.setSortBy(new String[]{"checkDate"});
        return bidGovCheckService.getBidGovCheckDTOPageByCriteria(criteria);
    }

    @PostMapping("/bid-gov-checks/getListByCriteria")
    public List<BidGovCheckDTO> getBidGovCheckDTOListByCriteria(@RequestBody BidGovCheckCriteria criteria) {
        log.debug("REST request to get BidGovCheckDTO : {}", criteria);
        return bidGovCheckService.getBidGovCheckDTOListByCriteria(criteria);
    }

    /**
     * {@code GET  /bid-gov-checks/:id} : get the "id" bidGovCheck.
     *
     * @param id the id of the bidGovCheckDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bidGovCheckDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bid-gov-checks/{id}")
    public ResponseEntity<BidGovCheckDTO> getBidGovCheck(@PathVariable BidGovCheckPrimaryKey id) {
        log.debug("REST request to get BidGovCheck : {}", id);
        Optional<BidGovCheckDTO> bidGovCheckDTO = bidGovCheckService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bidGovCheckDTO);
    }

    /**
     * {@code DELETE  /bid-gov-checks/:id} : delete the "id" bidGovCheck.
     *
     * @param id the id of the bidGovCheckDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bid-gov-checks/{id}")
    public ResponseEntity<Void> deleteBidGovCheck(@PathVariable BidGovCheckPrimaryKey id) {
        log.debug("REST request to delete BidGovCheck : {}", id);
        bidGovCheckService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    @PostMapping(path = "/bid-gov-checks/bidAae034D", produces = ReportMediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> bidAae034D(@RequestBody BidGovCheckPrimaryKey bidGovCheckPK) throws IOException {
        log.debug("REST request to get pdf : {}", bidGovCheckPK);
        BidProjectInfoDTO bidProjectDTO = bidProjectService.findBidProjectInfoByWkutAndPrjno(bidGovCheckPK.getWkut(), bidGovCheckPK.getPrjno()).get();
        BidGovCheckDTO bidGovCheckDTO = bidGovCheckService.findOne(bidGovCheckPK).get();
        BidGovCheckerrCriteria criteria = new BidGovCheckerrCriteria(bidGovCheckPK.getWkut(), bidGovCheckPK.getPrjno(), bidGovCheckPK.getCheckDate());
        List<BidGovCheckerrDTO> bidGovCheckerrDTOs = bidGovCheckerrService.findAllByCriteria(criteria);

        BidAae034D bidAae034D = new BidAae034D();
        bidAae034D.setBidProjectDTO(bidProjectDTO);
        bidAae034D.setBidGovCheckDTO(bidGovCheckDTO);
        bidAae034D.setBidGovCheckerrDTOs(bidGovCheckerrDTOs);

        byte[] pdfbyte = reportService.printReport(bidAae034D);
        return ResponseEntity.ok(pdfbyte);
    }
}
