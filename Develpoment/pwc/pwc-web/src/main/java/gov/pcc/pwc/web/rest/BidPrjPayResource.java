package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.MessageCodes;
import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.common.errors.BidValidationFailException;
import gov.pcc.pwc.common.web.ReportMediaType;
import gov.pcc.pwc.domain.BidCcindPrimaryKey;
import gov.pcc.pwc.domain.BidPrjPayPrimaryKey;
import gov.pcc.pwc.report.bid.bidAae009.BidAae009A;
import gov.pcc.pwc.report.bid.bidAae009.BidAae009B;
import gov.pcc.pwc.report.bid.bidAae009.BidAae009C;
import gov.pcc.pwc.repository.BidPrjPayRepository;
import gov.pcc.pwc.service.BidCcindService;
import gov.pcc.pwc.service.BidPrjPayService;
import gov.pcc.pwc.service.BidProjectService;
import gov.pcc.pwc.service.ReportService;
import gov.pcc.pwc.service.criteria.BidPrjPayCriteria;
import gov.pcc.pwc.service.dto.BidCcindDTO;
import gov.pcc.pwc.service.dto.BidPrjPayDTO;
import gov.pcc.pwc.service.dto.BidProjectInfoDTO;
import gov.pcc.pwc.utils.PwcHeadUtil;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
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
 * REST controller for managing {@link gov.pcc.pwc.domain.BidPrjPay}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class BidPrjPayResource {

    private final Logger log = LoggerFactory.getLogger(BidPrjPayResource.class);

    private static final String ENTITY_NAME = "bidPrjPay";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BidPrjPayService bidPrjPayService;

    private final BidPrjPayRepository bidPrjPayRepository;

    private final BidProjectService bidProjectService;

    private final BidCcindService bidCcindService;

    private final ReportService reportService;

    public BidPrjPayResource(BidPrjPayService bidPrjPayService, BidPrjPayRepository bidPrjPayRepository, BidProjectService bidProjectService, BidCcindService bidCcindService, ReportService reportService) {
        this.bidPrjPayService = bidPrjPayService;
        this.bidPrjPayRepository = bidPrjPayRepository;
        this.bidProjectService = bidProjectService;
        this.bidCcindService = bidCcindService;
        this.reportService = reportService;
    }

    /**
     * {@code POST  /bid-prj-pays} : Create a new bidPrjPay.
     *
     * @param bidPrjPayDTO the bidPrjPayDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bidPrjPayDTO, or with status {@code 400 (Bad Request)} if the bidPrjPay has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bid-prj-pays")
    public ResponseEntity<BidPrjPayDTO> createBidPrjPay(@Valid @RequestBody BidPrjPayDTO bidPrjPayDTO) throws URISyntaxException {
        log.debug("REST request to save BidPrjPay : {}", bidPrjPayDTO);
        List<String> validResult = bidPrjPayService.validData(bidPrjPayDTO);
        if(validResult.size() > 0){
            throw new BidValidationFailException("bidValidError","資料檢核錯誤" ,validResult.toArray(new String[0]));
        }
        BidPrjPayDTO result = bidPrjPayService.save(bidPrjPayDTO);

        return ResponseEntity
            .created(new URI("/api/bid-prj-pays/" + result.getWkut() + "/"+result.getPrjno()+"/"+result.getMonNo()))
            .headers(PwcHeadUtil.createAlert(MessageCodes.PWC_COMMON_0001_S()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bid-prj-pays/:id} : Updates an existing bidPrjPay.
     *
     * @param bidPrjPayDTO the bidPrjPayDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidPrjPayDTO,
     * or with status {@code 400 (Bad Request)} if the bidPrjPayDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bidPrjPayDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bid-prj-pays")
    public ResponseEntity<BidPrjPayDTO> updateBidPrjPay(
        @Valid @RequestBody BidPrjPayDTO bidPrjPayDTO
    ) throws URISyntaxException {
        BidPrjPayPrimaryKey bidPrjPayPK = new BidPrjPayPrimaryKey(bidPrjPayDTO.getWkut(), bidPrjPayDTO.getPrjno(), bidPrjPayDTO.getMonNo());
        log.debug("REST request to update BidPrjPay : {}", bidPrjPayDTO);
        if (bidPrjPayDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(bidPrjPayPK, bidPrjPayDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }
        if (!bidPrjPayRepository.existsById(bidPrjPayPK)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        List<String> validResult = bidPrjPayService.validData(bidPrjPayDTO);
        if (validResult.size() > 0) {
            throw new BidValidationFailException("bidValidError","資料檢核錯誤" ,validResult.toArray(new String[0]));
        }
        BidPrjPayDTO result = bidPrjPayService.save(bidPrjPayDTO);

        return ResponseEntity
            .ok()
            .headers(PwcHeadUtil.createAlert(MessageCodes.PWC_COMMON_0001_S()))
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidPrjPayDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /bid-prj-pays/:id} : Partial updates given fields of an existing bidPrjPay, field will ignore if it is null
     *
     * @param bidPrjPayDTO the bidPrjPayDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidPrjPayDTO,
     * or with status {@code 400 (Bad Request)} if the bidPrjPayDTO is not valid,
     * or with status {@code 404 (Not Found)} if the bidPrjPayDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the bidPrjPayDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/bid-prj-pays", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<BidPrjPayDTO> partialUpdateBidPrjPay(
        @NotNull @RequestBody BidPrjPayDTO bidPrjPayDTO
    ) throws URISyntaxException {
        BidPrjPayPrimaryKey bidPrjPayPK = new BidPrjPayPrimaryKey(bidPrjPayDTO.getWkut(), bidPrjPayDTO.getPrjno(), bidPrjPayDTO.getMonNo());
        log.debug("REST request to partial update BidPrjPay partially : {}", bidPrjPayPK);
        if (bidPrjPayDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(bidPrjPayPK, bidPrjPayDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }
        if (!bidPrjPayRepository.existsById(bidPrjPayPK)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        Optional<BidPrjPayDTO> result = bidPrjPayService.partialUpdate(bidPrjPayDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidPrjPayDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /bid-prj-pays} : get all the bidPrjPays.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bidPrjPays in body.
     */
    @GetMapping("/bid-prj-pays")
    public List<BidPrjPayDTO> getAllBidPrjPays() {
        log.debug("REST request to get all BidPrjPays");
        return bidPrjPayService.findAll();
    }

    /**
     * {@code GET  /bid-prj-pays/getOne} : get the "id" bidPrjPay.
     *
     * @param bidPrjPayPrimaryKey the id of the bidPrjPayDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the BidPrjPayDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/bid-prj-pays/getOne")
    public ResponseEntity<BidPrjPayDTO> getOne(@RequestBody BidPrjPayPrimaryKey bidPrjPayPrimaryKey) {
        log.debug("REST request to get BidPrjPay : {}", bidPrjPayPrimaryKey);
        Optional<BidPrjPayDTO> bidPrjPayDTO = bidPrjPayService.findOne(bidPrjPayPrimaryKey);
        return ResponseUtil.wrapOrNotFound(bidPrjPayDTO);
    }

    @PostMapping("/bid-prj-pays/getPages")
    public Page<BidPrjPayDTO> getPages(@RequestBody BidPrjPayCriteria bidPrjPayCriteria) {
        log.debug("REST request to get BidPrjPay : {}", bidPrjPayCriteria);
        return bidPrjPayService.findPages(bidPrjPayCriteria);
    }

    @PostMapping("/bid-prj-pays/getAll")
    public List<BidPrjPayDTO> getAll(@RequestBody BidPrjPayCriteria bidPrjPayCriteria) {
        log.debug("REST request to get BidPrjPay : {}", bidPrjPayCriteria);
        return bidPrjPayService.findAll(bidPrjPayCriteria);
    }

    @PostMapping("/bid-prj-pays/getMaxMonNo")
    public int getMaxMonNo(@RequestBody BidPrjPayCriteria bidPrjPayCriteria) {
        log.debug("REST request to get BidPrjPay : {}", bidPrjPayCriteria);
        return bidPrjPayService.findMaxMonNo(bidPrjPayCriteria);
    }

    /**
     * {@code DELETE  /bid-prj-pays/:id} : delete the "id" bidPrjPay.
     *
     * @param bidPrjPayPrimaryKey the id of the bidPrjPayDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bid-prj-pays/{id}")
    public ResponseEntity<Void> deleteBidPrjPay(@RequestBody BidPrjPayPrimaryKey bidPrjPayPrimaryKey) {
        log.debug("REST request to delete BidPrjPay : {}", bidPrjPayPrimaryKey);
        bidPrjPayService.delete(bidPrjPayPrimaryKey);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, bidPrjPayPrimaryKey.toString()))
            .build();
    }

    @PostMapping(path = "/bid-prj-pay/bidAae009A", produces = ReportMediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> bidAae009A(@RequestBody BidPrjPayCriteria bidPrjPayCriteria) throws IOException {
        log.debug("REST request to get pdf : {}", bidPrjPayCriteria);
        BidProjectInfoDTO bidProjectDTO = bidProjectService.findBidProjectInfoByWkutAndPrjno(bidPrjPayCriteria.getWkut(), bidPrjPayCriteria.getPrjno()).get();
        List<BidPrjPayDTO> bidPrjPayDTOs = bidPrjPayService.findAll(bidPrjPayCriteria);

        BidAae009A bidAae009A = new BidAae009A();
        bidAae009A.setBidProjectDTO(bidProjectDTO);
        bidAae009A.setBidPrjPayDTOs(bidPrjPayDTOs);

        byte[] pdfbyte = reportService.printReport(bidAae009A);
        return ResponseEntity.ok(pdfbyte);
    }

    @PostMapping(path = "/bid-prj-pay/bidAae009B", produces = ReportMediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> bidAae009B(@RequestBody BidPrjPayPrimaryKey bidPrjPayPrimaryKey) throws IOException {
        log.debug("REST request to get pdf : {}", bidPrjPayPrimaryKey);
        BidProjectInfoDTO bidProjectDTO = bidProjectService.findBidProjectInfoByWkutAndPrjno(bidPrjPayPrimaryKey.getWkut(), bidPrjPayPrimaryKey.getPrjno()).get();
        BidPrjPayDTO bidPrjPayDTO = bidPrjPayService.findOne(bidPrjPayPrimaryKey).get();

        BidAae009B bidAae009B = new BidAae009B();
        bidAae009B.setBidProjectDTO(bidProjectDTO);
        bidAae009B.setBidPrjPayDTO(bidPrjPayDTO);

        byte[] pdfbyte = reportService.printReport(bidAae009B);
        return ResponseEntity.ok(pdfbyte);
    }

    @PostMapping(path = "/bid-prj-pay/bidAae009C", produces = ReportMediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> bidAae009C(@RequestBody BidPrjPayPrimaryKey bidPrjPayPrimaryKey) throws IOException {
        log.debug("REST request to get pdf : {}", bidPrjPayPrimaryKey);
        BidProjectInfoDTO bidProjectDTO = bidProjectService.findBidProjectInfoByWkutAndPrjno(bidPrjPayPrimaryKey.getWkut(), bidPrjPayPrimaryKey.getPrjno()).get();
        BidPrjPayDTO bidPrjPayDTO = bidPrjPayService.findOne(bidPrjPayPrimaryKey).get();
        BigDecimal bvalInd1 = BigDecimal.valueOf(100);
        LocalDate payDate = bidPrjPayDTO.getPayDate();
        if (payDate != null) {
            String year = String.format("%03d", payDate.getYear() - 1911);
            String month = String.format("%02d", payDate.getMonthValue());
            BidCcindPrimaryKey bidCcindPrimaryKey = new BidCcindPrimaryKey(year, month);
            BidCcindDTO bidCcindDTO = bidCcindService.findOne(bidCcindPrimaryKey).orElse(null);
            if (bidCcindDTO != null && bidCcindDTO.getInd1() != null) {
                bvalInd1 = bidCcindDTO.getInd1();
            }
        }
        BigDecimal cvalInd1 = BigDecimal.valueOf(100);
        LocalDate actDate = bidProjectDTO.getActDate();
        if (actDate != null) {
            String year = String.format("%03d", actDate.getYear() - 1911);
            String month = String.format("%02d", actDate.getMonthValue());
            BidCcindPrimaryKey bidCcindPrimaryKey = new BidCcindPrimaryKey(year, month);
            BidCcindDTO bidCcindDTO = bidCcindService.findOne(bidCcindPrimaryKey).orElse(null);
            if (bidCcindDTO != null && bidCcindDTO.getInd1() != null) {
                cvalInd1 = bidCcindDTO.getInd1();
            }
        }

        BidAae009C bidAae009C = new BidAae009C();
        bidAae009C.setBidProjectDTO(bidProjectDTO);
        bidAae009C.setBidPrjPayDTO(bidPrjPayDTO);
        bidAae009C.setBvalInd1(bvalInd1);
        bidAae009C.setCvalInd1(cvalInd1);

        byte[] pdfbyte = reportService.printReport(bidAae009C);
        return ResponseEntity.ok(pdfbyte);
    }
}
