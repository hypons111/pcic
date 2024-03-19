package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.MessageCodes;
import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.common.web.ReportMediaType;
import gov.pcc.pwc.domain.BidKpifinPrimaryKey;
import gov.pcc.pwc.domain.BidProjectPrimaryKey;
import gov.pcc.pwc.report.bid.bidAad008.BidAad008;
import gov.pcc.pwc.repository.BidKpifinRepository;
import gov.pcc.pwc.service.*;
import gov.pcc.pwc.service.criteria.*;
import gov.pcc.pwc.service.dto.BidKpifinDTO;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import gov.pcc.pwc.service.dto.BidProjectDTO;
import gov.pcc.pwc.service.dto.BidProjectInfoDTO;
import gov.pcc.pwc.service.dto.BidUnit10DTO;
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
 * REST controller for managing {@link gov.pcc.pwc.domain.BidKpifin}.
 */
@RestController
@RequestMapping("/api")
public class BidKpifinResource {

    private final Logger log = LoggerFactory.getLogger(BidKpifinResource.class);

    private static final String ENTITY_NAME = "bidKpifin";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BidKpifinService bidKpifinService;

    private final ReportService reportService;

    private final BidProjectService bidProjectService;

    private final BidKpifinRepository bidKpifinRepository;

    private final BidPrjMonthService bidPrjMonthService;

    private final BidChkaptService bidChkaptService;

    private final BidPrjPriceService bidPrjPriceService;

    private final BidPrjSafeService bidPrjSafeService;

    private final BidPrjEnviService bidPrjEnviService;

    private final BidOverseeService bidOverseeService;


    public BidKpifinResource(BidKpifinService bidKpifinService, ReportService reportService, BidProjectService bidProjectService, BidKpifinRepository bidKpifinRepository, BidPrjMonthService bidPrjMonthService, BidChkaptService bidChkaptService, BidPrjPriceService bidPrjPriceService, BidPrjSafeService bidPrjSafeService, BidPrjEnviService bidPrjEnviService, BidOverseeService bidOverseeService) {

        this.bidKpifinService = bidKpifinService;
        this.reportService = reportService;
        this.bidProjectService = bidProjectService;
        this.bidKpifinRepository = bidKpifinRepository;
        this.bidPrjMonthService = bidPrjMonthService;
        this.bidChkaptService = bidChkaptService;
        this.bidPrjPriceService = bidPrjPriceService;
        this.bidPrjSafeService = bidPrjSafeService;
        this.bidPrjEnviService = bidPrjEnviService;
        this.bidOverseeService = bidOverseeService;
    }

    /**
     * {@code POST  /bid-kpifins} : Create a new bidKpifin.
     *
     * @param bidKpifinDTO the bidKpifinDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bidKpifinDTO, or with status {@code 400 (Bad Request)} if the bidKpifin has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bid-kpifins")
    public ResponseEntity<BidKpifinDTO> createBidKpifin(@Valid @RequestBody BidKpifinDTO bidKpifinDTO , Authentication auth) throws URISyntaxException {
        log.debug("REST request to save BidKpifin : {}", bidKpifinDTO);

        //check sequence
        List<BidKpifinDTO> kpiList = bidKpifinService.getListByCriteriaExceptSeq(bidKpifinDTO);
        if(kpiList!=null && kpiList.size()>0){
            //此情況為用 wkut prjno 和 idate 且flag N有找到東西
            //表示seq要自訂
            bidKpifinDTO.setSeq(kpiList.get(0).getSeq()+1);
        }else{
            bidKpifinDTO.setSeq(1);
        }
        bidKpifinDTO.setFlag("Y");
        bidKpifinDTO.setCreateUser(auth.getName());
        bidKpifinDTO.setCreateDate(Instant.now());
        bidKpifinDTO.setUpdateUser(auth.getName());
        bidKpifinDTO.setUpdateDate(Instant.now());

        BidKpifinDTO result = bidKpifinService.save(bidKpifinDTO);
        return ResponseEntity
            .created(new URI("/api/bid-kpifins/" + result.getIdString()))
            .headers(PwcHeadUtil.createAlert(MessageCodes.PWC_COMMON_0001_S()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getIdString()))
            .body(result);
    }

    /**
     * {@code PUT  /bid-kpifins/:id} : Updates an existing bidKpifin.
     *
     * @param bidKpifinDTO the bidKpifinDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidKpifinDTO,
     * or with status {@code 400 (Bad Request)} if the bidKpifinDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bidKpifinDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bid-kpifins")
    public ResponseEntity<BidKpifinDTO> updateBidKpifin(
        @Valid @RequestBody BidKpifinDTO bidKpifinDTO,Authentication auth
    ) throws URISyntaxException {
        log.debug("REST request to update BidKpifin : {}", bidKpifinDTO);
        BidKpifinPrimaryKey id = new BidKpifinPrimaryKey(bidKpifinDTO.getWkut(),bidKpifinDTO.getPrjno(),bidKpifinDTO.getIdate(),bidKpifinDTO.getSeq());
        if (bidKpifinDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, bidKpifinDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidKpifinRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        bidKpifinDTO.setUpdateUser(auth.getName());
        bidKpifinDTO.setUpdateDate(Instant.now());

        BidKpifinDTO result = bidKpifinService.save(bidKpifinDTO);
        return ResponseEntity
            .ok()
            .headers(PwcHeadUtil.createAlert(MessageCodes.PWC_COMMON_0002_S()))
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidKpifinDTO.getIdString()))
            .body(result);
    }

    /**
     * {@code PATCH  /bid-kpifins/:id} : Partial updates given fields of an existing bidKpifin, field will ignore if it is null
     *
     * @param bidKpifinDTO the bidKpifinDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidKpifinDTO,
     * or with status {@code 400 (Bad Request)} if the bidKpifinDTO is not valid,
     * or with status {@code 404 (Not Found)} if the bidKpifinDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the bidKpifinDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/bid-kpifins/", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<BidKpifinDTO> partialUpdateBidKpifin(
        @NotNull @RequestBody BidKpifinDTO bidKpifinDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update BidKpifin partially :  {}", bidKpifinDTO);
        BidKpifinPrimaryKey id = new BidKpifinPrimaryKey(bidKpifinDTO.getWkut(),bidKpifinDTO.getPrjno(),bidKpifinDTO.getIdate(),bidKpifinDTO.getSeq());
        if (bidKpifinDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, bidKpifinDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidKpifinRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BidKpifinDTO> result = bidKpifinService.partialUpdate(bidKpifinDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidKpifinDTO.getIdString())
        );
    }

    /**
     * {@code GET  /bid-kpifins/:id} : get the "id" bidKpifin.
     *
     * @param id the id of the bidKpifinDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bidKpifinDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/bid-kpifins/getOne")
    public ResponseEntity<BidKpifinDTO> getBidKpifin(@RequestBody BidKpifinPrimaryKey id) {
        log.debug("REST request to get BidKpifin : {}", id);
        Optional<BidKpifinDTO> bidKpifinDTO = bidKpifinService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bidKpifinDTO);
    }

    /**
     * {@code DELETE  /bid-kpifins/:id} : delete the "id" bidKpifin.
     *
     * @param id the id of the bidKpifinDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @PostMapping("/bid-kpifins/deleteOne")
    public ResponseEntity<Void> deleteBidKpifin(@RequestBody BidKpifinPrimaryKey id) {
        log.debug("REST request to delete BidKpifin : {}", id);
        //不要刪掉 把flag改成N
        Optional<BidKpifinDTO> bidKpifinDTO = bidKpifinService.findOne(id);

        if(bidKpifinDTO.isPresent()){
            bidKpifinDTO.get().setFlag("N");
            bidKpifinService.save(bidKpifinDTO.get());
        }

//        bidKpifinService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    @PostMapping("/bid-kpifins/getPageBycriteria")
    public Page<BidKpifinDTO> getPageBycriteria(@RequestBody BidKpifinCriteria criteria){
        return bidKpifinService.findPageByCriteria(criteria);
    }

    @PostMapping("/bid-kpifins/list-by-criteria")
    public List<BidKpifinDTO> getListBycriteria(@RequestBody BidKpifinCriteria criteria){
        return bidKpifinService.findListByCriteria(criteria);
    }

    @PostMapping(path = "/bid-kpifins/report", produces = ReportMediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> BidAad008(@RequestBody BidKpifinPrimaryKey id) throws IOException {
        BidAad008 bidAad008 = new BidAad008();
        BidProjectPrimaryKey projectPK = new BidProjectPrimaryKey(id.getWkut(), id.getPrjno());
        BidProjectInfoDTO bidProjectInfoDTO = bidProjectService.findBidProjectInfoByWkutAndPrjno(id.getWkut(), id.getPrjno()).orElse(null);
        BidProjectDTO bidProjectDTO = bidProjectService.findOne(projectPK).orElse(null);
        BidKpifinDTO bidKpifinDTO = bidKpifinService.findOne(id).orElse(null);
        List<BidUnit10DTO> ccuts = bidProjectService.findProjectCCUT(projectPK);


        bidAad008.setBidProjectInfoDTO(bidProjectInfoDTO);
        bidAad008.setBidProjectDTO(bidProjectDTO);
        bidAad008.setBidKpifinDTO(bidKpifinDTO);
        bidAad008.setCcuts(ccuts);
        bidAad008.setBidPrjMonthDTOs(bidPrjMonthService.findAllBidPrjMonth(id.getWkut(),id.getPrjno()));
        bidAad008.setBidChkaptDTO(bidChkaptService.findLastestOne(new BidChkaptCriteria(id.getWkut(),id.getPrjno())).orElse(null));
        bidAad008.setBidPrjPriceDTOs(bidPrjPriceService.getBidPrjPriceDTOListByCriteria(new BidPrjPriceCriteria(id.getWkut(),id.getPrjno())));
        bidAad008.setBidPrjSafeDTOS(bidPrjSafeService.findPrjSafeDTOListByCriteria(new BidPrjSafeCriteria(id.getWkut(),id.getPrjno())));
        bidAad008.setBidPrjEnviDTOS(bidPrjEnviService.findDTOListByCriteria(new BidPrjEnviCriteria(id.getWkut(),id.getPrjno())));
        bidAad008.setBidOversees(bidOverseeService.findListByCriteria(new BidOverseeCriteria(id.getWkut(),id.getPrjno())));



        return ResponseEntity.ok(reportService.printReport(bidAad008));
    }
}
