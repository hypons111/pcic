package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.repository.RecProjectRepository;
import gov.pcc.pwc.service.RecProjectService;
import gov.pcc.pwc.service.criteria.RecProjectCriteria;
import gov.pcc.pwc.service.criteria.RecProjectQueryCriteria;
import gov.pcc.pwc.service.dto.*;
import gov.pcc.pwc.common.errors.BadRequestAlertException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import gov.pcc.pwc.service.genSerialNumber.errors.TypeVariableNotMatchException;
import gov.pcc.pwc.service.odsExporter.DownloadService;
import gov.pcc.pwc.service.odsExporter.OdsExportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link gov.pcc.pwc.domain.RecProject}.
 */
@RestController
@RequestMapping("/api")
public class RecProjectResource {

    private final Logger log = LoggerFactory.getLogger(RecProjectResource.class);

    private static final String ENTITY_NAME = "recProject";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RecProjectService recProjectService;

    private final RecProjectRepository recProjectRepository;

    private final DownloadService downloadService;

    public RecProjectResource(RecProjectService recProjectService, RecProjectRepository recProjectRepository,DownloadService downloadService) {
        this.recProjectService = recProjectService;
        this.recProjectRepository = recProjectRepository;
        this.downloadService = downloadService;
    }

    @GetMapping("/service/rec-projects/get-all-org")
    public List<RecSelectOptionsDTO> getAllOrg() {
        log.debug("REST request to findAllOrg");
        return recProjectService.findAllOrg();
    }

    @GetMapping("/service/rec-projects/get-all-typhoon")
    public List<RecSelectOptionsDTO> getAllTyphoon() {
        log.debug("REST request to findAllTyphoon");
        return recProjectService.findAllTyphoon();
    }

    @GetMapping("/service/rec-projects/get-all-prj")
    public List<RecSelectOptionsDTO> getAllPrjCode() {
        log.debug("REST request to findAllPrjCode");
        return recProjectService.findAllPrjCode();
    }

    @PostMapping("/service/rec-projects/criteria-jpa")
    public Page<RecProjectQueryDTO> getRecProjectByCriteria(@Valid @RequestBody RecProjectQueryCriteria criteria) {
        log.debug("REST request to get RecProjectQuery : {}",criteria.getSortBy());
        return recProjectService.findByCriteria(criteria);
    }

    //復建工程查詢
    @PostMapping("/rec-projects/criteria-jpa")
    public Page<RecProject0303DTO> getRecProjectByCriteria(@Valid @RequestBody RecProjectCriteria criteria) throws InterruptedException {
        log.debug("REST request to get RecProject : {}",criteria);
        return recProjectService.findByCriteria(criteria);
    }

    //取已存在颱風的颱風選項
    @GetMapping("/rec-projects/get-project-typhoon/{orgId}")
    public List<RecProjectOptionsDTO> getProjectTyphoon(@PathVariable String orgId) {
        log.debug("REST request to getProjectTyphoon");
        return recProjectService.findProjectTyphoon(orgId);
    }

    //取已存在復建工程的工程類別
    @GetMapping("/rec-projects/get-project-code/{typhoonNo}/{orgId}")
    public List<RecProjectOptionsDTO> getProjectCode(@PathVariable String typhoonNo, @PathVariable String orgId) {
        log.debug("REST request to getTyphoonNo : {}", typhoonNo);
        return recProjectService.findProjectCode(typhoonNo, orgId);
    }

    //取已存在復建工程的機關名稱
    @GetMapping("/rec-projects/get-project-org-role/{typhoonNo}/{orgId}")
    public List<RecProjectOptionsDTO> getProjectOrgRole(@PathVariable String typhoonNo, @PathVariable String orgId) {
        log.debug("REST request to getTyphoonNo : {}", typhoonNo);
        return recProjectService.findProjectOrgRole(typhoonNo, orgId);
    }

    //取已存在復建工程的鄉(鎮市)
    @GetMapping("/rec-projects/get-project-city/{typhoonNo}/{orgId}")
    public List<RecProjectOptionsDTO> getProjectCity(@PathVariable String typhoonNo, @PathVariable String orgId) {
        log.debug("REST request to getTyphoonNo : {}", typhoonNo);
        return recProjectService.findProjectCity(typhoonNo, orgId);
    }

    //取已存在復建工程的申請狀況
    @GetMapping("/rec-projects/get-project-status/{typhoonNo}/{orgId}")
    public List<RecProjectOptionsDTO> getProjectStatus(@PathVariable String typhoonNo, @PathVariable String orgId) {
        log.debug("REST request to getTyphoonNo : {}", typhoonNo);
        return recProjectService.findProjectStatus(typhoonNo, orgId);
    }

    //取構造物主檔的構造物名稱
    @GetMapping("/rec-projects/get-project-const-item")
    public List<RecProjectOptionsDTO> getProjectConstItem() {
        log.debug("REST request to getProjectConstItem");
        return recProjectService.findProjectConstItem();
    }

    //取構造物形式主檔的構造物形式名稱
    @GetMapping("/rec-projects/get-project-const-model/{constItemName}")
    public List<RecProjectOptionsDTO> getProjectConstModel(@PathVariable String constItemName) {
        log.debug("REST request to getConstItemName : {}", constItemName);
        return recProjectService.findProjectConstModel(constItemName);
    }

    @PostMapping("/service/rec-projects/export-ods")
    public void exportOdsByCriteria(HttpServletResponse response, @Valid @RequestBody RecProjectQueryCriteria criteria) throws IOException {
        log.debug("REST request to exportByCriteria");
        String downloadFilePath = "D:\\downloadTemp\\";  //toDo: 路徑須改為檔案伺服器或確認的本地資料夾
        String fileName = criteria.getFileName();
        recProjectService.exportOdsByCriteriaAndFile(criteria,downloadFilePath,fileName);
        downloadService.downloadByResponse(response,downloadFilePath,fileName+".ods");
    }

    @PostMapping("/service/rec-projects/export-kml")
    public void exportKmlByCriteria(HttpServletResponse response, @Valid @RequestBody RecProjectQueryCriteria criteria) throws IOException {
        log.debug("REST request to exportKmlByCriteria");
        String downloadFilePath = "D:\\downloadTemp\\";  //toDo: 路徑須改為檔案伺服器或確認的本地資料夾
        String fileName = criteria.getFileName();
        recProjectService.exportKmlByCriteriaAndFile(criteria,downloadFilePath,fileName);
        downloadService.downloadByResponse(response,downloadFilePath,fileName+".kml");
    }

    @PostMapping("/service/rec-projects/typhoon-chart-data")
    public Map<String,List<RecProjectChartDTO>> getTyphoonCountAndAmountByCriteria( @Valid @RequestBody RecProjectQueryCriteria criteria){
        return recProjectService.findTyphoonCountAndAmountByCriteria(criteria);
    }

    @PostMapping("/service/rec-projects/projectCode-chart-data")
    public Map<String,List<RecProjectChartDTO>> getProjectCodeCountAndAmountByCriteria( @Valid @RequestBody RecProjectQueryCriteria criteria){
        return recProjectService.findProjectCodeCountAndAmountByCriteria(criteria);
    }

    @PostMapping("/service/rec-projects/org-chart-data")
    public Map<String,List<RecProjectChartDTO>> getOrgCountAndAmountByCriteria( @Valid @RequestBody RecProjectQueryCriteria criteria){
        return recProjectService.findOrgCountAndAmountByCriteria(criteria);
    }

    @PostMapping("/service/rec-projects/org-chart-data-by-typhoon")
    public Map<String,List<RecProjectChartDTO>> getOrgCountAndAmountByTyphoonCriteria( @Valid @RequestBody RecProjectQueryCriteria criteria){
        return recProjectService.findOrgCountAndAmountByTyphoonCriteria(criteria);
    }

    @GetMapping("/service/rec-projects/all-typhoon-chart-data")
    public Map<String,List<RecProjectChartDTO>> getAllTyphoonCountAndAmountNoParam(){
        return recProjectService.findAllTyphoonCountAndAmountNoParam();
    }

    @GetMapping("/service/rec-projects/all-typhoon-chart-data-year")
    public Map<String,List<RecProjectChartDTO>> getAllYearTyphoonCountAndAmountNoParam(){
        return recProjectService.findAllYearTyphoonCountAndAmountNoParam();
    }

    @PostMapping("/service/rec-projects/org-chart-data-year")
    public Map<String,List<RecProjectMultiChartDTO>> getOrgCountAndAmountByYearAndCriteria(@Valid @RequestBody RecProjectQueryCriteria criteria){
        return recProjectService.findOrgCountAndAmountByYearAndCriteria(criteria);
    }

    @PostMapping("/rec-projects/chart-data-by-indigenous")
    public Map<String,List<RecProjectChartDTO>> getCountAndAmountByIndigenousAndCriteria( @Valid @RequestBody RecProjectQueryCriteria criteria){
        return recProjectService.findCountAndAmountByIndigenousAndCriteria(criteria);
    }

    @PostMapping("/service/rec-projects/expired")
    public Page<RecProjectQueryDTO> getExpiredByCriteria(@Valid @RequestBody RecProjectQueryCriteria criteria) {
        log.debug("REST request to get RecProjectQuery : {}",criteria.getSortBy());
        return recProjectService.findExpiredByCriteria(criteria);
    }

    @PostMapping("/service/rec-projects/export-ods-expired")
    public void exportOdsByCriteriaExpired(HttpServletResponse response, @Valid @RequestBody RecProjectQueryCriteria criteria) throws IOException {
        log.debug("REST request to exportByCriteriaExpired");
        String downloadFilePath = "D:\\downloadTemp\\";  //toDo: 路徑須改為檔案伺服器或確認的本地資料夾
        String fileName = criteria.getFileName();
        recProjectService.exportOdsByCriteriaAndFileExpired(criteria,downloadFilePath,fileName);
        downloadService.downloadByResponse(response,downloadFilePath,fileName+".ods");
    }

    @PostMapping("/service/rec-projects/expired-finished")
    public Page<RecProjectQueryDTO> getExpiredAndFinishedByCriteria(@Valid @RequestBody RecProjectQueryCriteria criteria) {
        log.debug("REST request to get RecProjectQuery : {}",criteria.getSortBy());
        return recProjectService.findExpiredAndFinishedByCriteria(criteria);
    }

    @PostMapping("/service/rec-projects/export-ods-expired-finished")
    public void exportOdsByCriteriaExpiredAndFinished(HttpServletResponse response, @Valid @RequestBody RecProjectQueryCriteria criteria) throws IOException {
        log.debug("REST request to exportByCriteriaExpired");
        String downloadFilePath = "D:\\downloadTemp\\";  //toDo: 路徑須改為檔案伺服器或確認的本地資料夾
        String fileName = criteria.getFileName();
        recProjectService.exportOdsByCriteriaAndFileExpiredAndFinished(criteria,downloadFilePath,fileName);
        downloadService.downloadByResponse(response,downloadFilePath,fileName+".ods");
    }

    @PostMapping("/service/rec-projects/research")
    public Page<RecProjectQueryDTO> getResearchByCriteria(@Valid @RequestBody RecProjectQueryCriteria criteria) {
        log.debug("REST request to getResearchByCriteria : {}",criteria.getSortBy());
        return recProjectService.findResearchAndFinishedByCriteria(criteria);
    }

    @PostMapping("/service/rec-projects/export-ods-research")
    public void exportOdsByCriteriaResearch(HttpServletResponse response, @Valid @RequestBody RecProjectQueryCriteria criteria) throws IOException {
        log.debug("REST request to exportOdsByCriteriaResearch");
        String downloadFilePath = "D:\\downloadTemp\\";  //toDo: 路徑須改為檔案伺服器或確認的本地資料夾
        String fileName = criteria.getFileName();
        recProjectService.exportOdsByCriteriaAndFileResearch(criteria,downloadFilePath,fileName);
        downloadService.downloadByResponse(response,downloadFilePath,fileName+".ods");
    }

    @PostMapping("/service/rec-projects/project-criteria")
    public Page<RecProjectQueryDTO> getProjectByCriteria(@Valid @RequestBody RecProjectQueryCriteria criteria) {
        log.debug("REST request to get getProjectByCriteria : {}",criteria.getSortBy());
        return recProjectService.findProjectByCriteria(criteria);
    }

    @PostMapping("/service/rec-projects/export-project-kml")
    public void exportProjectKmlByCriteria(HttpServletResponse response, @Valid @RequestBody RecProjectQueryCriteria criteria) throws IOException {
        log.debug("REST request to exportKmlByCriteria");
        String downloadFilePath = "D:\\downloadTemp\\";  //toDo: 路徑須改為檔案伺服器或確認的本地資料夾
        String fileName = criteria.getFileName();
        recProjectService.exportProjectKmlByCriteriaAndFile(criteria,downloadFilePath,fileName);
        downloadService.downloadByResponse(response,downloadFilePath,fileName+".kml");
    }


    /**
     * {@code POST  /rec-projects} : Create a new recProject.
     *
     * @param recProjectDTO the recProjectDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new recProjectDTO, or with status {@code 400 (Bad Request)} if the recProject has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/rec-projects")
    public ResponseEntity<RecProjectDTO> createRecProject(@Valid @RequestBody RecProjectDTO recProjectDTO) throws URISyntaxException, TypeVariableNotMatchException {
        log.debug("REST request to save RecProject : {}", recProjectDTO);
        //新增主鍵
        recProjectService.beforeInsertPackage(recProjectDTO);
        //主鍵已給會牴觸
//        if (recProjectDTO.getProjectNo() != null) {
//            throw new BadRequestAlertException("A new recProject cannot already have an ID", ENTITY_NAME, "idexists");
//        }
        log.debug("REST request to save RecProject : {}", recProjectDTO);
        RecProjectDTO result = recProjectService.save(recProjectDTO);
        return ResponseEntity
            .created(new URI("/api/rec-projects/" + result.getProjectNo()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getProjectNo()))
            .body(result);
    }

    /**
     * {@code PUT  /rec-projects/:projectNo} : Updates an existing recProject.
     *
     * @param projectNo the id of the recProjectDTO to save.
     * @param recProjectDTO the recProjectDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated recProjectDTO,
     * or with status {@code 400 (Bad Request)} if the recProjectDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the recProjectDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/rec-projects/{projectNo}")
    public ResponseEntity<RecProjectDTO> updateRecProject(
        @PathVariable(value = "projectNo", required = false) final String projectNo,
        @Valid @RequestBody RecProjectDTO recProjectDTO
    ) throws URISyntaxException {
        log.debug("REST request to update RecProject : {}, {}", projectNo, recProjectDTO);
        if (recProjectDTO.getProjectNo() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(projectNo, recProjectDTO.getProjectNo())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!recProjectRepository.existsById(projectNo)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        RecProjectDTO result = recProjectService.save(recProjectDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, recProjectDTO.getProjectNo()))
            .body(result);
    }

    /**
     * {@code PATCH  /rec-projects/:projectNo} : Partial updates given fields of an existing recProject, field will ignore if it is null
     *
     * @param projectNo the id of the recProjectDTO to save.
     * @param recProjectDTO the recProjectDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated recProjectDTO,
     * or with status {@code 400 (Bad Request)} if the recProjectDTO is not valid,
     * or with status {@code 404 (Not Found)} if the recProjectDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the recProjectDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/rec-projects/{projectNo}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<RecProjectDTO> partialUpdateRecProject(
        @PathVariable(value = "projectNo", required = false) final String projectNo,
        @NotNull @RequestBody RecProjectDTO recProjectDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update RecProject partially : {}, {}", projectNo, recProjectDTO);
        if (recProjectDTO.getProjectNo() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(projectNo, recProjectDTO.getProjectNo())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!recProjectRepository.existsById(projectNo)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RecProjectDTO> result = recProjectService.partialUpdate(recProjectDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, recProjectDTO.getProjectNo())
        );
    }

    /**
     * {@code GET  /rec-projects} : get all the recProjects.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of recProjects in body.
     */
    @GetMapping("/rec-projects")
    public List<RecProjectDTO> getAllRecProjects() {
        log.debug("REST request to get all RecProjects");
        return recProjectService.findAll();
    }

    /**
     * {@code GET  /rec-projects/:id} : get the "id" recProject.
     *
     * @param id the id of the recProjectDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the recProjectDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/rec-projects/{id}")
    public ResponseEntity<RecProjectDTO> getRecProject(@PathVariable String id) {
        log.debug("REST request to get RecProject : {}", id);
        Optional<RecProjectDTO> recProjectDTO = recProjectService.findOne(id);
        return ResponseUtil.wrapOrNotFound(recProjectDTO);
    }

    /**
     * {@code DELETE  /rec-projects/:id} : delete the "id" recProject.
     *
     * @param id the id of the recProjectDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/rec-projects/{id}")
    public ResponseEntity<Void> deleteRecProject(@PathVariable String id) {
        log.debug("REST request to delete RecProject : {}", id);
        recProjectService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
