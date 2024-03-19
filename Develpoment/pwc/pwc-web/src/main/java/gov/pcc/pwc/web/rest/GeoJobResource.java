package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.common.web.io.DownloadableResource;
import gov.pcc.pwc.domain.AdmMailSend;
import gov.pcc.pwc.repository.AdmMailSendRepository;
import gov.pcc.pwc.repository.GeoJobRepository;
import gov.pcc.pwc.service.GeoJobService;
import gov.pcc.pwc.service.criteria.GeoJobCriteria;
import gov.pcc.pwc.service.criteria.RecProjectQueryCriteria;
import gov.pcc.pwc.service.dto.Geo0607DTO;
import gov.pcc.pwc.service.dto.GeoJobDTO;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;


import gov.pcc.pwc.service.odsExporter.DownloadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link gov.pcc.pwc.domain.GeoJob}.
 */
@RestController
@RequestMapping("/api")
public class GeoJobResource {

    private final Logger log = LoggerFactory.getLogger(GeoJobResource.class);

    private static final String ENTITY_NAME = "geoJob";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GeoJobService geoJobService;

    private final GeoJobRepository geoJobRepository;

    private final AdmMailSendRepository admMailSendRepository;
    private final DownloadService downloadService;

    public GeoJobResource(GeoJobService geoJobService, GeoJobRepository geoJobRepository, AdmMailSendRepository admMailSendRepository,DownloadService downloadService) {
        this.geoJobService = geoJobService;
        this.geoJobRepository = geoJobRepository;
        this.admMailSendRepository = admMailSendRepository;
        this.downloadService = downloadService;
    }

    /**
     * {@code POST  /geo-jobs} : Create a new geoJob.
     *
     * @param geoJobDTO the geoJobDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new geoJobDTO, or with status {@code 400 (Bad Request)} if the geoJob has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/geo-jobs")
    public ResponseEntity<GeoJobDTO> createGeoJob(@Valid @RequestBody GeoJobDTO geoJobDTO, Authentication authentication) throws URISyntaxException {
        log.debug("REST request to save GeoJob : {}", geoJobDTO);
        if (geoJobDTO.getId() != null) {
            throw new BadRequestAlertException("A new geoJob cannot already have an ID", ENTITY_NAME, "idexists");
        }
        geoJobDTO.setCreateUser(authentication.getName());
        geoJobDTO.setCreateTime(Instant.now());
        geoJobDTO.setUpdateUser(authentication.getName());
        geoJobDTO.setUpdateTime(Instant.now());
        GeoJobDTO result = geoJobService.save(geoJobDTO);
        return ResponseEntity
            .created(new URI("/api/geo-jobs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /geo-jobs/:id} : Updates an existing geoJob.
     *
     * @param id        the id of the geoJobDTO to save.
     * @param geoJobDTO the geoJobDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated geoJobDTO,
     * or with status {@code 400 (Bad Request)} if the geoJobDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the geoJobDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/geo-jobs/{id}/{action}")
    public ResponseEntity<GeoJobDTO> updateGeoJob(
        @PathVariable(value = "id", required = false) final Long id,
        @PathVariable(value = "action", required = false) final String action,
        @Valid @RequestBody GeoJobDTO geoJobDTO, Authentication authentication
    ) throws URISyntaxException {
        log.debug("REST request to update GeoJob : {}, {}", id, geoJobDTO);
        if (geoJobDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, geoJobDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }
        if (!geoJobRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        GeoJobDTO result = geoJobService.save(geoJobDTO);

        //寄信
        String receiver1 = geoJobDTO.getEmail();
        String receiver2 = "peexam@mail.pcc.gov.tw";
        if (Objects.equals(action, "GEO0502")) {
            String subject1 = "[技師分階段考試實務經驗及專業研習登錄管理資訊系統] 機構工作職缺修改通知";
            String subject2 = "[寄件備份]" + subject1;
            String content1 = geoJobDTO.getCompanyName() + "於系統上送出「工作職缺修改」之申請\n" +
                "請前往審核此筆工作職缺修改內容！\n";
            String content2 = geoJobDTO.getContactPerson() + "於系統上送出「工作職缺修改」之申請\n" +
                "請前往審核此筆工作職缺修改內容！\n";
            geoJobService.saveMailSend(geoJobDTO, authentication, receiver1, subject1, content1, action);
            //附件
            geoJobService.saveMailSend(geoJobDTO, authentication, receiver2, subject2, content2, action);
        }
        if(Objects.equals(action, "GEO0603")) {
            String subject1 = "[技師分階段考試實務經驗及專業研習登錄管理資訊系統] 工作職缺審核申請結果通知";
            String content1 = geoJobDTO.getContactPerson()+ " 您好!\n您於系統上送出「工作職缺審核」之申請案已獲受理\n" +
                "審核結果通過!\n";
            String content2 = geoJobDTO.getContactPerson()+ "於「技師分階段考試實務經驗及專業研習登錄管理資訊系統」申請「工作職缺審核申請」\n" +
                "審核結果通過!\n";
            geoJobService.saveMailSend(geoJobDTO, authentication, receiver1, subject1, content1, action);
            //附件
            geoJobService.saveMailSend(geoJobDTO, authentication, receiver2, subject1, content2, action);
        }
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, geoJobDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /geo-jobs/:id} : Partial updates given fields of an existing geoJob, field will ignore if it is null
     *
     * @param id        the id of the geoJobDTO to save.
     * @param geoJobDTO the geoJobDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated geoJobDTO,
     * or with status {@code 400 (Bad Request)} if the geoJobDTO is not valid,
     * or with status {@code 404 (Not Found)} if the geoJobDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the geoJobDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/geo-jobs/{id}", consumes = {"application/json", "application/merge-patch+json"})
    public ResponseEntity<GeoJobDTO> partialUpdateGeoJob(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody GeoJobDTO geoJobDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update GeoJob partially : {}, {}", id, geoJobDTO);
        if (geoJobDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, geoJobDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!geoJobRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<GeoJobDTO> result = geoJobService.partialUpdate(geoJobDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, geoJobDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /geo-jobs} : get all the geoJobs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of geoJobs in body.
     */
    @GetMapping("/geo-jobs")
    public List<GeoJobDTO> getAllGeoJobs() {
        log.debug("REST request to get all GeoJobs");
        return geoJobService.findAll();
    }

    /**
     * {@code GET  /geo-jobs/:id} : get the "id" geoJob.
     *
     * @param id the id of the geoJobDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the geoJobDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/geo-jobs/{id}")
    public ResponseEntity<GeoJobDTO> getGeoJob(@PathVariable Long id) {
        log.debug("REST request to get GeoJob : {}", id);
        Optional<GeoJobDTO> geoJobDTO = geoJobService.findOne(id);
        return ResponseUtil.wrapOrNotFound(geoJobDTO);
    }

    /**
     * {@code POST  /geo-jobs/criteria-jpa} : get geoJobs by criteria.
     *
     * @param criteria the criteria of the cmsCompDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the geoJobDTO, or with status {@code 404 (Not Found)}.
     */
//    @PostMapping("/geo-jobs/criteria-jpa")
//    public Page<GeoJobDTO> getGeoJobByCriteria(@Valid @RequestBody GeoJobCriteria criteria) throws InterruptedException {
//        log.debug("REST request to get GeoJob : {}", criteria);
//        return geoJobService.findByCriteria(criteria);
//    }
    @PostMapping("/geo-jobs/criteria")
    public Page<GeoJobDTO> getGeoJobByCriteria(@Valid @RequestBody GeoJobCriteria criteria) throws InterruptedException {
        log.debug("REST request to get GeoJob : {}", criteria);
        return geoJobService.findByCriteriaImpl(criteria);
    }

    /**
     * {@code DELETE  /geo-jobs/:id} : delete the "id" geoJob.
     *
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @PostMapping("/geo-jobs/delete")
    public ResponseEntity<Void> deleteGeoJob(@Valid @RequestBody GeoJobDTO geoJobDTO, Authentication authentication) {
        log.debug("REST request to delete GeoJob : {}", geoJobDTO);

        String subject = "[技師分階段考試實務經驗及專業研習登錄管理資訊系統] 機構工作職缺刪除通知";
        String content = geoJobDTO.getContactPerson() + "於系統上送出「工作職缺修改」執行刪除\n" +
            "將無法審核此筆工作職缺內容！\n";
        String receiver2 = "peexam@mail.pcc.gov.tw";

        geoJobService.saveMailSend(geoJobDTO, authentication, receiver2, subject, content, "GEO0502");
        //附件
        geoJobService.saveMailSend(geoJobDTO, authentication, receiver2, subject, content, "GEO0502");

        geoJobService.delete(geoJobDTO.getId());
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, geoJobDTO.getId().toString()))
            .build();
    }

    /**
     * {@code POST  /geo-jobs/workPlace} : get geoJobs by workPlaceList.
     *
     * @param criteria the criteria of the cmsCompDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the geoJobDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/geo-jobs/workPlace")
    public Page<GeoJobDTO> findByWorkPlace(@Valid @RequestBody GeoJobCriteria criteria) throws InterruptedException {
        log.debug("REST request to get GeoJob : {}", criteria);
        return geoJobService.findByWorkPlace(criteria);
    }
    /**
     * {@code POST  /geo-jobs/counts} : get JobCounts.
     *
     * @param criteria the criteria of the cmsCompDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the geo0607DTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/geo-jobs/counts")
    public Page<Geo0607DTO> findJobCounts(@Valid @RequestBody GeoJobCriteria criteria) throws InterruptedException {
        log.debug("REST request to get JobCounts : {}", criteria);
        return geoJobService.findJobCounts(criteria);
    }

    @PostMapping("/service/geo-jobs/export-xls")
    public DownloadableResource exportJobCounts(HttpServletResponse response, @Valid @RequestBody GeoJobCriteria criteria) throws IOException {
        log.debug("REST request to exportJobCounts");
        String downloadFilePath = "D:\\downloadTemp\\";  //toDo: 路徑須改為檔案伺服器或確認的本地資料夾
        String fileName = criteria.getFileName();
        geoJobService.exportJobCounts(criteria,downloadFilePath,fileName);
//        downloadService.downloadByResponse(response,downloadFilePath,fileName+".xls");
        return new DownloadableResource(new ByteArrayResource(Files.readAllBytes(Path.of(downloadFilePath + fileName))), fileName);
    }
}
