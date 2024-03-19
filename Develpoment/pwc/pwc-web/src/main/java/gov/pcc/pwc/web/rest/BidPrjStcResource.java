package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.common.errors.BidValidationFailException;
import gov.pcc.pwc.domain.AdmFile;
import gov.pcc.pwc.domain.BidPrjMilestonePrimaryKey;
import gov.pcc.pwc.domain.BidPrjStcPrimaryKey;
import gov.pcc.pwc.domain.BidProjectPrimaryKey;
import gov.pcc.pwc.report.bid.bidAae013.BidAae013;
import gov.pcc.pwc.report.bid.bidAae023.BidAae023;
import gov.pcc.pwc.repository.AdmFileRepository;
import gov.pcc.pwc.repository.BidPrjMilestoneRepository;
import gov.pcc.pwc.repository.BidPrjStcRepository;
import gov.pcc.pwc.service.AdmFileService;
import gov.pcc.pwc.service.BidPrjMilestoneService;
import gov.pcc.pwc.service.BidPrjStcService;
import gov.pcc.pwc.service.ReportService;
import gov.pcc.pwc.service.criteria.*;
import gov.pcc.pwc.service.dto.*;
import gov.pcc.pwc.common.errors.BadRequestAlertException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
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
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link gov.pcc.pwc.domain.BidPrjStc}.
 */
@RestController
@RequestMapping("/api")
public class BidPrjStcResource {

    private final Logger log = LoggerFactory.getLogger(BidPrjStcResource.class);

    private static final String ENTITY_NAME = "bidPrjStc";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BidPrjStcService bidPrjStcService;

    private final BidPrjStcRepository bidPrjStcRepository;

    private final BidPrjMilestoneService bidPrjMilestoneService;

    private final AdmFileRepository admFileRepository;
    // 上傳檔案
    private final AdmFileService admFileService;
    // 產生報表檔
    private final ReportService reportService;

    public BidPrjStcResource(BidPrjStcService bidPrjStcService, BidPrjStcRepository bidPrjStcRepository,BidPrjMilestoneService bidPrjMilestoneService,AdmFileRepository admFileRepository, AdmFileService admFileService,ReportService reportService) {
        this.bidPrjStcService = bidPrjStcService;
        this.bidPrjStcRepository = bidPrjStcRepository;
        this.bidPrjMilestoneService = bidPrjMilestoneService;
        this.admFileRepository = admFileRepository;
        this.admFileService = admFileService;
        this.reportService = reportService;

    }

    /**
     * {@code POST  /bid-prj-stcs} : Create a new bidPrjStc.
     *
     * @param bidPrjStcDTO the bidPrjStcDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bidPrjStcDTO, or with status {@code 400 (Bad Request)} if the bidPrjStc has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bid-prj-stcs")
    public ResponseEntity<BidPrjStcDTO> createBidPrjStc(@Valid @RequestPart("form") BidPrjStcDTO bidPrjStcDTO, @RequestPart(name = "file", required = false) MultipartFile file, Authentication authentication) throws URISyntaxException, IOException {
        log.debug("REST request to save BidPrjStc : {}", bidPrjStcDTO);
        // 設定圖片
        AdmFile admFile = new AdmFile();
        Long admFileId = bidPrjStcDTO.getAdmFileId();
        if (admFileId != null) {
            Optional<AdmFile> oldAdmFile = admFileRepository.findById(admFileId);
            if(oldAdmFile.isPresent()) {
                admFile = oldAdmFile.get();
                admFile.setUpdateTime(Instant.now());
                admFile.setUpdateUser(authentication.getName());
            } else {
                admFile = new AdmFile();
                admFile.setModuleType("BID");
                admFile.setBusinessType("AAB-006_STC");
                admFile.setSourceId(bidPrjStcDTO.getPrjno() + "@" + bidPrjStcDTO.getCheckDate());
                admFile.setFileVirusStatus("1");
                admFile.setCreateTime(Instant.now());
                admFile.setCreateUser(authentication.getName());
            }
        } else {
            admFile.setModuleType("BID");
            admFile.setBusinessType("AAB-006_STC");
            admFile.setSourceId(bidPrjStcDTO.getPrjno() + "@" + bidPrjStcDTO.getCheckDate());
            admFile.setFileVirusStatus("1");
            admFile.setCreateTime(Instant.now());
            admFile.setCreateUser(authentication.getName());
        }
        String functionPath = "BID/PRJ_STC/" + bidPrjStcDTO.getWkut() + "/" + bidPrjStcDTO.getPrjno() + "/" + bidPrjStcDTO.getCheckDate();
        admFile = admFileService.saveFile(admFile, file, functionPath);
        bidPrjStcDTO.setAdmFileId(admFile.getId());
        bidPrjStcDTO.setCreateDate(Instant.now());
        bidPrjStcDTO.setCreateUser(authentication.getName());


        String formStatus = "create";
//        後端驗證
        List<String> validResult = bidPrjStcService.validData(bidPrjStcDTO , formStatus);
        if(validResult.size()>0){
            throw new BidValidationFailException("bidValidError","資料檢核錯誤" ,validResult.toArray(new String[0]));
        }

        if(bidPrjStcDTO.getYndA01()!= null && bidPrjStcDTO.getYndA01().equals("N")){
            bidPrjMilestoneService.fromStcInsert(bidPrjStcDTO,"1. 環境影響評估(含環境影響差異分析)",bidPrjStcDTO.getDateA01(),bidPrjStcDTO.getResonA01());
        }
        if(bidPrjStcDTO.getYndA02()!= null && bidPrjStcDTO.getYndA02().equals("N")){
            bidPrjMilestoneService.fromStcInsert(bidPrjStcDTO,"2. 水土保持規劃書及水土保持計畫",bidPrjStcDTO.getDateA02(),bidPrjStcDTO.getResonA02());
        }
        if(bidPrjStcDTO.getYndA03()!= null && bidPrjStcDTO.getYndA03().equals("N")){
            bidPrjMilestoneService.fromStcInsert(bidPrjStcDTO,"3. 非都市土地使用變更編定",bidPrjStcDTO.getDateA03(),bidPrjStcDTO.getResonA03());
        }
        if(bidPrjStcDTO.getYndA04()!= null && bidPrjStcDTO.getYndA04().equals("N")){
            bidPrjMilestoneService.fromStcInsert(bidPrjStcDTO,"4. 都市計畫擬定或變更",bidPrjStcDTO.getDateA04(),bidPrjStcDTO.getResonA04());
        }
        if(bidPrjStcDTO.getYndA05()!= null && bidPrjStcDTO.getYndA05().equals("N")){
            bidPrjMilestoneService.fromStcInsert(bidPrjStcDTO,"5. 用地取得",bidPrjStcDTO.getDateA05(),bidPrjStcDTO.getResonA05());
        }
        if(bidPrjStcDTO.getYndA06()!= null && bidPrjStcDTO.getYndA06().equals("N")){
            bidPrjMilestoneService.fromStcInsert(bidPrjStcDTO,"6. 都市計畫審議",bidPrjStcDTO.getDateA06(),bidPrjStcDTO.getResonA06());
        }
        if(bidPrjStcDTO.getYndA07()!= null && bidPrjStcDTO.getYndA07().equals("N")){
            bidPrjMilestoneService.fromStcInsert(bidPrjStcDTO,"7. 古蹟、歷史建築、聚落、文化景觀及遺址保存與維護",bidPrjStcDTO.getDateA07(),bidPrjStcDTO.getResonA07());
        }
        if(bidPrjStcDTO.getYndA08()!= null && bidPrjStcDTO.getYndA08().equals("N")){
            bidPrjMilestoneService.fromStcInsert(bidPrjStcDTO,"8. 建築許可(建築執照或特種建築物許可等)",bidPrjStcDTO.getDateA08(),bidPrjStcDTO.getResonA08());
        }
        if(bidPrjStcDTO.getYndA09()!= null && bidPrjStcDTO.getYndA09().equals("N")){
            bidPrjMilestoneService.fromStcInsert(bidPrjStcDTO,"9. 河川區域使用許可",bidPrjStcDTO.getDateA09(),bidPrjStcDTO.getResonA09());
        }
        if(bidPrjStcDTO.getYndA10()!= null && bidPrjStcDTO.getYndA10().equals("N")){
            bidPrjMilestoneService.fromStcInsert(bidPrjStcDTO,"10. 鄰近、跨(穿)越鐵路、公路施工許可；捷運禁限建範圍施工許可；道路挖掘許可",bidPrjStcDTO.getDateA10(),bidPrjStcDTO.getResonA10());
        }
        if(bidPrjStcDTO.getYndA11()!= null && bidPrjStcDTO.getYndA11().equals("N")){
            bidPrjMilestoneService.fromStcInsert(bidPrjStcDTO,"11. 管線箱涵處理及公有管線佈設許可",bidPrjStcDTO.getDateA11(),bidPrjStcDTO.getResonA11());
        }
        if(bidPrjStcDTO.getYndA12()!= null && bidPrjStcDTO.getYndA12().equals("N")){
            bidPrjMilestoneService.fromStcInsert(bidPrjStcDTO,"12. 樹木保護計畫",bidPrjStcDTO.getDateA12(),bidPrjStcDTO.getResonA12());
        }
        if(bidPrjStcDTO.getYndA13()!= null && bidPrjStcDTO.getYndA13().equals("N")){
            bidPrjMilestoneService.fromStcInsert(bidPrjStcDTO,"13. 評估其他標案影響",bidPrjStcDTO.getDateA13(),bidPrjStcDTO.getResonA13());
        }
        if(bidPrjStcDTO.getYndA14()!= null && bidPrjStcDTO.getYndA14().equals("N")){
            bidPrjMilestoneService.fromStcInsert(bidPrjStcDTO,"14. 地上物處理",bidPrjStcDTO.getDateA14(),bidPrjStcDTO.getResonA14());
        }
        if(bidPrjStcDTO.getYndA15()!= null && bidPrjStcDTO.getYndA15().equals("N")){
            bidPrjMilestoneService.fromStcInsert(bidPrjStcDTO,"15. 工程預算可支用情形",bidPrjStcDTO.getDateA15(),bidPrjStcDTO.getResonA15());
        }

        BidPrjStcDTO result = bidPrjStcService.save(bidPrjStcDTO);
        return ResponseEntity
            .created(new URI("/api/bid-prj-stcs/" + bidPrjStcDTO.getWkut()+bidPrjStcDTO.getPrjno()+bidPrjStcDTO.getCheckDate()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME,bidPrjStcDTO.getWkut()+bidPrjStcDTO.getPrjno()+bidPrjStcDTO.getCheckDate()))
            .body(result);
    }

    /**
     * {@code PUT  /bid-prj-stcs/:id} : Updates an existing bidPrjStc.
     *
     * @param bidPrjStcDTO the bidPrjStcDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidPrjStcDTO,
     * or with status {@code 400 (Bad Request)} if the bidPrjStcDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bidPrjStcDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bid-prj-stcs/updateOne")
    public ResponseEntity<BidPrjStcDTO> updateBidPrjStc(
        @Valid @RequestPart("form") BidPrjStcDTO bidPrjStcDTO,
        @RequestPart(name = "file", required = false) MultipartFile file
        ,Authentication authentication
    ) throws URISyntaxException,IOException {
        BidPrjStcPrimaryKey bidPrjStcPrimaryKey = new BidPrjStcPrimaryKey(bidPrjStcDTO.getWkut(),bidPrjStcDTO.getPrjno(),bidPrjStcDTO.getCheckDate());
        log.debug("REST request to update BidPrjStc : {}", bidPrjStcDTO);
        if (bidPrjStcDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(bidPrjStcPrimaryKey, bidPrjStcDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidPrjStcRepository.existsById(bidPrjStcPrimaryKey)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        // 設定圖片
        AdmFile admFile = new AdmFile();
        Long admFileId = bidPrjStcDTO.getAdmFileId();
        if (admFileId != null) {
            Optional<AdmFile> oldAdmFile = admFileRepository.findById(admFileId);
            if(oldAdmFile.isPresent()) {
                admFile = oldAdmFile.get();
                admFile.setUpdateTime(Instant.now());
                admFile.setUpdateUser(authentication.getName());
            } else {
                admFile = new AdmFile();
                admFile.setModuleType("BID");
                admFile.setBusinessType("AAB-006_STC");
                admFile.setSourceId(bidPrjStcDTO.getPrjno() + "@" + bidPrjStcDTO.getCheckDate());
                admFile.setFileVirusStatus("1");
                admFile.setCreateTime(Instant.now());
                admFile.setCreateUser(authentication.getName());
            }
        } else {
            admFile.setModuleType("BID");
            admFile.setBusinessType("AAB-006_STC");
            admFile.setSourceId(bidPrjStcDTO.getPrjno() + "@" + bidPrjStcDTO.getCheckDate());
            admFile.setFileVirusStatus("1");
            admFile.setCreateTime(Instant.now());
            admFile.setCreateUser(authentication.getName());
        }
        String functionPath = "BID\\PRJ_STC\\" + bidPrjStcDTO.getWkut() + "\\" + bidPrjStcDTO.getPrjno() + "\\" + bidPrjStcDTO.getCheckDate();
        admFile = admFileService.saveFile(admFile, file, functionPath);
        bidPrjStcDTO.setAdmFileId(admFile.getId());
        bidPrjStcDTO.setCreateDate(Instant.now());
        bidPrjStcDTO.setCreateUser(authentication.getName());

        String formStatus = "modify";
//        後端驗證
        List<String> validResult = bidPrjStcService.validData(bidPrjStcDTO , formStatus);
        if(validResult.size()>0){
            throw new BidValidationFailException("bidValidError","資料檢核錯誤" ,validResult.toArray(new String[0]));
        }
        BidPrjMilestoneCriteria criteria = new BidPrjMilestoneCriteria();
        criteria.setWkut(bidPrjStcDTO.getWkut());
        criteria.setPrjno(bidPrjStcDTO.getPrjno());
        criteria.setApplyDate(bidPrjStcDTO.getCheckDate());



        if(bidPrjStcDTO.getYndA01()!= null ){
            if(!bidPrjStcDTO.getYndA01().equals("D")){
                bidPrjMilestoneService.fromStcUpdate(criteria,bidPrjStcDTO,"1. 環境影響評估(含環境影響差異分析)",bidPrjStcDTO.getDateA01(),bidPrjStcDTO.getResonA01(),bidPrjStcDTO.getYndA01());
            }
        }

        if(bidPrjStcDTO.getYndA02()!= null ){
            if(!bidPrjStcDTO.getYndA02().equals("D")){
                bidPrjMilestoneService.fromStcUpdate(criteria,bidPrjStcDTO,"2. 水土保持規劃書及水土保持計畫",bidPrjStcDTO.getDateA02(),bidPrjStcDTO.getResonA02(),bidPrjStcDTO.getYndA02());
            }
        }

        if(bidPrjStcDTO.getYndA03()!= null ){
            if(!bidPrjStcDTO.getYndA03().equals("D")){
                bidPrjMilestoneService.fromStcUpdate(criteria,bidPrjStcDTO,"3. 非都市土地使用變更編定",bidPrjStcDTO.getDateA03(),bidPrjStcDTO.getResonA03(),bidPrjStcDTO.getYndA03());
            }
        }

        if(bidPrjStcDTO.getYndA04()!= null){
            if(!bidPrjStcDTO.getYndA04().equals("D")){
                bidPrjMilestoneService.fromStcUpdate(criteria,bidPrjStcDTO,"4. 都市計畫擬定或變更",bidPrjStcDTO.getDateA04(),bidPrjStcDTO.getResonA04(),bidPrjStcDTO.getYndA04());
            }
        }
        if(bidPrjStcDTO.getYndA05()!= null){
            if(!bidPrjStcDTO.getYndA05().equals("D")){
                bidPrjMilestoneService.fromStcUpdate(criteria,bidPrjStcDTO,"5. 用地取得",bidPrjStcDTO.getDateA05(),bidPrjStcDTO.getResonA05(),bidPrjStcDTO.getYndA05());
            }
        }
        if(bidPrjStcDTO.getYndA06()!= null){
            if(!bidPrjStcDTO.getYndA06().equals("D")){
                bidPrjMilestoneService.fromStcUpdate(criteria,bidPrjStcDTO,"6. 都市計畫審議",bidPrjStcDTO.getDateA06(),bidPrjStcDTO.getResonA06(),bidPrjStcDTO.getYndA06());
            }
        }

        if(bidPrjStcDTO.getYndA07()!= null){
            if(!bidPrjStcDTO.getYndA07().equals("D")){
                bidPrjMilestoneService.fromStcUpdate(criteria,bidPrjStcDTO,"7. 古蹟、歷史建築、聚落、文化景觀及遺址保存與維護",bidPrjStcDTO.getDateA07(),bidPrjStcDTO.getResonA07(),bidPrjStcDTO.getYndA07());
            }
        }
        if(bidPrjStcDTO.getYndA08()!= null){
            if(!bidPrjStcDTO.getYndA08().equals("D")){
                bidPrjMilestoneService.fromStcUpdate(criteria,bidPrjStcDTO,"8. 建築許可(建築執照或特種建築物許可等)",bidPrjStcDTO.getDateA08(),bidPrjStcDTO.getResonA08(),bidPrjStcDTO.getYndA08());
            }
        }

        if(bidPrjStcDTO.getYndA09()!= null){
            if(!bidPrjStcDTO.getYndA09().equals("D")){
                bidPrjMilestoneService.fromStcUpdate(criteria,bidPrjStcDTO,"9. 河川區域使用許可",bidPrjStcDTO.getDateA09(),bidPrjStcDTO.getResonA09(),bidPrjStcDTO.getYndA09());
            }
        }
        if(bidPrjStcDTO.getYndA10()!= null){
            if(!bidPrjStcDTO.getYndA10().equals("D")){
                bidPrjMilestoneService.fromStcUpdate(criteria,bidPrjStcDTO,"10. 鄰近、跨(穿)越鐵路、公路施工許可；捷運禁限建範圍施工許可；道路挖掘許可",bidPrjStcDTO.getDateA10(),bidPrjStcDTO.getResonA10(),bidPrjStcDTO.getYndA10());
            }
        }
        if(bidPrjStcDTO.getYndA11()!= null){
            if(!bidPrjStcDTO.getYndA11().equals("D")){
                bidPrjMilestoneService.fromStcUpdate(criteria,bidPrjStcDTO,"11. 管線箱涵處理及公有管線佈設許可",bidPrjStcDTO.getDateA11(),bidPrjStcDTO.getResonA11(),bidPrjStcDTO.getYndA11());
            }
        }

        if(bidPrjStcDTO.getYndA12()!= null){
            if(!bidPrjStcDTO.getYndA12().equals("D")){
                bidPrjMilestoneService.fromStcUpdate(criteria,bidPrjStcDTO,"12. 樹木保護計畫",bidPrjStcDTO.getDateA12(),bidPrjStcDTO.getResonA12(),bidPrjStcDTO.getYndA12());
            }
        }
        if(bidPrjStcDTO.getYndA13()!= null && bidPrjStcDTO.getYndA13().equals("N")){
            if(!bidPrjStcDTO.getYndA13().equals("D")){
                bidPrjMilestoneService.fromStcUpdate(criteria,bidPrjStcDTO,"13. 評估其他標案影響",bidPrjStcDTO.getDateA13(),bidPrjStcDTO.getResonA13(),bidPrjStcDTO.getYndA13());
            }
        }
        if(bidPrjStcDTO.getYndA14()!= null){
            if(!bidPrjStcDTO.getYndA14().equals("D")){
                bidPrjMilestoneService.fromStcUpdate(criteria,bidPrjStcDTO,"14. 地上物處理",bidPrjStcDTO.getDateA14(),bidPrjStcDTO.getResonA14(),bidPrjStcDTO.getYndA14());
            }
        }
        if(bidPrjStcDTO.getYndA15()!= null){
            if(!bidPrjStcDTO.getYndA15().equals("D")){
                bidPrjMilestoneService.fromStcUpdate(criteria,bidPrjStcDTO,"15. 工程預算可支用情形",bidPrjStcDTO.getDateA15(),bidPrjStcDTO.getResonA15(),bidPrjStcDTO.getYndA15());
            }
        }

        BidPrjStcDTO result = bidPrjStcService.save(bidPrjStcDTO);

        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidPrjStcDTO.getWkut()+bidPrjStcDTO.getPrjno()+bidPrjStcDTO.getCheckDate()))
            .body(result);
    }

    /**
     * {@code PATCH  /bid-prj-stcs/:id} : Partial updates given fields of an existing bidPrjStc, field will ignore if it is null
     *
     * @param bidPrjStcDTO the bidPrjStcDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidPrjStcDTO,
     * or with status {@code 400 (Bad Request)} if the bidPrjStcDTO is not valid,
     * or with status {@code 404 (Not Found)} if the bidPrjStcDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the bidPrjStcDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/bid-prj-stcs/partialUpdate", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<BidPrjStcDTO> partialUpdateBidPrjStc(
        @NotNull @RequestBody BidPrjStcDTO bidPrjStcDTO
    ) throws URISyntaxException {
        BidPrjStcPrimaryKey bidPrjStcPrimaryKey = new BidPrjStcPrimaryKey(bidPrjStcDTO.getWkut(),bidPrjStcDTO.getPrjno(),bidPrjStcDTO.getCheckDate());
        log.debug("REST request to partial update BidPrjStc partially : {}", bidPrjStcDTO);
        if (bidPrjStcDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(bidPrjStcPrimaryKey, bidPrjStcDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidPrjStcRepository.existsById(bidPrjStcPrimaryKey)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BidPrjStcDTO> result = bidPrjStcService.partialUpdate(bidPrjStcDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidPrjStcDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /bid-prj-stcs} : get all the bidPrjStcs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bidPrjStcs in body.
     */
    @GetMapping("/bid-prj-stcs")
    public List<BidPrjStcDTO> getAllBidPrjStcs() {
        log.debug("REST request to get all BidPrjStcs");
        return bidPrjStcService.findAll();
    }

    /**
     * {@code GET  /bid-prj-stcs/:id} : get the "id" bidPrjStc.
     *
     * @param id the id of the bidPrjStcDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bidPrjStcDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/bid-prj-stcs/getOne")
    public ResponseEntity<BidPrjStcDTO> getBidPrjStc(@RequestBody BidPrjStcPrimaryKey id) {
        log.debug("REST request to get BidPrjStc : {}", id);
        Optional<BidPrjStcDTO> bidPrjStcDTO = bidPrjStcService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bidPrjStcDTO);
    }

    /**
     * {@code DELETE  /bid-prj-stcs/:id} : delete the "id" bidPrjStc.
     *
     * @param id the id of the bidPrjStcDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bid-prj-stcs/{id}")
    public ResponseEntity<Void> deleteBidPrjStc(@PathVariable BidPrjStcPrimaryKey id) {
        log.debug("REST request to delete BidPrjStc : {}", id);
        bidPrjStcService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * find BidPrjStcDTO By Criteria
     * */
    @PostMapping("/bid-prj-stcs/criteria")
    public Page<BidPrjStcDTO> getBidPrjStcByCriteria(@RequestBody BidPrjStcCriteria criteria){
        log.debug("REST request to get BidPrjStcDTO Page by criteria : {}", criteria);
       return bidPrjStcService.findBidPrjStcDTOByCriteria(criteria);
    }

    // 產生復建工程發包、請撥款及執行狀況表
    @PostMapping("/bid-prj-stcs/BidAae013")
    public  ResponseEntity<byte[]> bidAae006(@RequestBody BidPrjStcCriteria criteria) throws IOException {
        log.debug("REST request to get pdf : {}", criteria);
        List<BidPrjStcDTO> list = bidPrjStcService.findTableDTOByCriteria(criteria);
        BidPrjStcDTO bidPrjStcDTO = list.get(0);

        BidAae013 bidAae013 = new BidAae013();
        bidAae013.setBidPrjStcDTO(bidPrjStcDTO);
        // 產生報表
        byte[] pdfbyte = reportService.printReport(bidAae013);
        return ResponseEntity.ok(pdfbyte);

    }

}
