package gov.pcc.pwc.pwb.rest;

import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.domain.AdmFunctionUsedRecord;
import gov.pcc.pwc.repository.AdmFunctionUsedRecordRepository;
import gov.pcc.pwc.service.AdmFunctionUsedRecordService;
import gov.pcc.pwc.service.criteria.AdmFunctionUsedRecordCriteria;
import gov.pcc.pwc.service.dto.AdmFunctionUsedRecordDTO;
import gov.pcc.pwc.service.dto.AdmLoginHisDTO;
import java.net.URI;
import java.net.URISyntaxException;
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
 * REST controller for managing {@link AdmFunctionUsedRecord}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class AdmFunctionUsedRecordResource {

    private final Logger log = LoggerFactory.getLogger(AdmFunctionUsedRecordResource.class);

    private static final String ENTITY_NAME = "admFunctionUsedRecord";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AdmFunctionUsedRecordService admFunctionUsedRecordService;

    private final AdmFunctionUsedRecordRepository admFunctionUsedRecordRepository;

    public AdmFunctionUsedRecordResource(
        AdmFunctionUsedRecordService admFunctionUsedRecordService,
        AdmFunctionUsedRecordRepository admFunctionUsedRecordRepository
    ) {
        this.admFunctionUsedRecordService = admFunctionUsedRecordService;
        this.admFunctionUsedRecordRepository = admFunctionUsedRecordRepository;
    }

    /**
     * {@code POST  /adm-function-used-records} : Create a new admFunctionUsedRecord.
     *
     * @param admFunctionUsedRecord the admFunctionUsedRecord to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new admFunctionUsedRecord, or with status {@code 400 (Bad Request)} if the admFunctionUsedRecord has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/adm-function-used-records")
    public ResponseEntity<AdmFunctionUsedRecord> createAdmFunctionUsedRecord(
        @Valid @RequestBody AdmFunctionUsedRecord admFunctionUsedRecord
    ) throws URISyntaxException {
        log.debug("REST request to save AdmFunctionUsedRecord : {}", admFunctionUsedRecord);
        if (admFunctionUsedRecord.getId() != null) {
            throw new BadRequestAlertException("A new admFunctionUsedRecord cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AdmFunctionUsedRecord result = admFunctionUsedRecordRepository.save(admFunctionUsedRecord);
        return ResponseEntity
            .created(new URI("/api/adm-function-used-records/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /adm-function-used-records/:id} : Updates an existing admFunctionUsedRecord.
     *
     * @param id the id of the admFunctionUsedRecord to save.
     * @param admFunctionUsedRecord the admFunctionUsedRecord to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated admFunctionUsedRecord,
     * or with status {@code 400 (Bad Request)} if the admFunctionUsedRecord is not valid,
     * or with status {@code 500 (Internal Server Error)} if the admFunctionUsedRecord couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/adm-function-used-records/{id}")
    public ResponseEntity<AdmFunctionUsedRecord> updateAdmFunctionUsedRecord(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody AdmFunctionUsedRecord admFunctionUsedRecord
    ) throws URISyntaxException {
        log.debug("REST request to update AdmFunctionUsedRecord : {}, {}", id, admFunctionUsedRecord);
        if (admFunctionUsedRecord.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, admFunctionUsedRecord.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!admFunctionUsedRecordRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        AdmFunctionUsedRecord result = admFunctionUsedRecordRepository.save(admFunctionUsedRecord);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, admFunctionUsedRecord.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /adm-function-used-records/:id} : Partial updates given fields of an existing admFunctionUsedRecord, field will ignore if it is null
     *
     * @param id the id of the admFunctionUsedRecord to save.
     * @param admFunctionUsedRecord the admFunctionUsedRecord to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated admFunctionUsedRecord,
     * or with status {@code 400 (Bad Request)} if the admFunctionUsedRecord is not valid,
     * or with status {@code 404 (Not Found)} if the admFunctionUsedRecord is not found,
     * or with status {@code 500 (Internal Server Error)} if the admFunctionUsedRecord couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/adm-function-used-records/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<AdmFunctionUsedRecord> partialUpdateAdmFunctionUsedRecord(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody AdmFunctionUsedRecord admFunctionUsedRecord
    ) throws URISyntaxException {
        log.debug("REST request to partial update AdmFunctionUsedRecord partially : {}, {}", id, admFunctionUsedRecord);
        if (admFunctionUsedRecord.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, admFunctionUsedRecord.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!admFunctionUsedRecordRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<AdmFunctionUsedRecord> result = admFunctionUsedRecordRepository
            .findById(admFunctionUsedRecord.getId())
            .map(
                existingAdmFunctionUsedRecord -> {
                    if (admFunctionUsedRecord.getSysCategory() != null) {
                        existingAdmFunctionUsedRecord.setSysCategory(admFunctionUsedRecord.getSysCategory());
                    }
                    if (admFunctionUsedRecord.getFunctionId() != null) {
                        existingAdmFunctionUsedRecord.setFunctionId(admFunctionUsedRecord.getFunctionId());
                    }
                    if (admFunctionUsedRecord.getFunctionExtendId() != null) {
                        existingAdmFunctionUsedRecord.setFunctionExtendId(admFunctionUsedRecord.getFunctionExtendId());
                    }
                    if (admFunctionUsedRecord.getRecordContent() != null) {
                        existingAdmFunctionUsedRecord.setRecordContent(admFunctionUsedRecord.getRecordContent());
                    }
                    if (admFunctionUsedRecord.getCreateTime() != null) {
                        existingAdmFunctionUsedRecord.setCreateTime(admFunctionUsedRecord.getCreateTime());
                    }
                    if (admFunctionUsedRecord.getCreateUserSys() != null) {
                        existingAdmFunctionUsedRecord.setCreateUserSys(admFunctionUsedRecord.getCreateUserSys());
                    }

                    return existingAdmFunctionUsedRecord;
                }
            )
            .map(admFunctionUsedRecordRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, admFunctionUsedRecord.getId().toString())
        );
    }

    /**
     * {@code GET  /adm-function-used-records} : get all the admFunctionUsedRecords.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of admFunctionUsedRecords in body.
     */
    @GetMapping("/adm-function-used-records")
    public List<AdmFunctionUsedRecord> getAllAdmFunctionUsedRecords() {
        log.debug("REST request to get all AdmFunctionUsedRecords");
        return admFunctionUsedRecordRepository.findAll();
    }

    /**
     * {@code GET  /adm-function-used-records/:id} : get the "id" admFunctionUsedRecord.
     *
     * @param id the id of the admFunctionUsedRecord to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the admFunctionUsedRecord, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/adm-function-used-records/{id}")
    public AdmFunctionUsedRecordDTO getAdmFunctionUsedRecord(@PathVariable Long id) {
        log.debug("REST request to get AdmFunctionUsedRecordDTO : {}", id);
        return admFunctionUsedRecordService.findByPk(id);
    }

    /**
     * {@code DELETE  /adm-function-used-records/:id} : delete the "id" admFunctionUsedRecord.
     *
     * @param id the id of the admFunctionUsedRecord to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/adm-function-used-records/{id}")
    public ResponseEntity<Void> deleteAdmFunctionUsedRecord(@PathVariable Long id) {
        log.debug("REST request to delete AdmFunctionUsedRecord : {}", id);
        admFunctionUsedRecordRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code POST  /adm-function-used-records/criteria-jpa} : get admFunctionUsedRecords by criteria.
     *
     * @param criteria the criteria of the AdmFunctionUsedRecordCriteria to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the AdmFunctionUsedRecordsDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/adm-function-used-records/criteria-jpa")
    public Page<AdmFunctionUsedRecordDTO> getAdmFunctionUsedRecordByCriteria(@Valid @RequestBody AdmFunctionUsedRecordCriteria criteria) {
        log.debug("REST request to get AdmFunctionUsedRecordDTO : {}", criteria);
        return admFunctionUsedRecordService.findByCriteria(criteria);
    }

    /**
     * {@code POST  /adm-function-used-records/statistics} : get admFunctionUsedRecords statistics by criteria.
     *
     * @param criteria the criteria of the AdmFunctionUsedRecordCriteria to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the AdmFunctionUsedRecordsDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/adm-function-used-records/statistics")
    public Page<AdmFunctionUsedRecordDTO> getAdmFunctionUsedRecordStatisticsByCriteria(@Valid @RequestBody AdmFunctionUsedRecordCriteria criteria) {
        log.debug("REST request to get AdmFunctionUsedRecordDTO of statistics : {}", criteria);
        return admFunctionUsedRecordService.findStatisticsByCriteria(criteria);
    }

    /**
     * {@code POST  /adm-function-used-records/statistics/info} : get admFunctionUsedRecords statistics info by criteria.
     *
     * @param functionId the functionId of the admFunctionUsedRecords to retrieve.
     * @param criteria the criteria of the AdmFunctionUsedRecordCriteria to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the AdmFunctionUsedRecordsDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/adm-function-used-records/statistics/info/{functionId}")
    public List<AdmFunctionUsedRecordDTO> getAdmFunctionUsedRecordStatisticsInfoByCriteria(@Valid @RequestBody AdmFunctionUsedRecordCriteria criteria, @PathVariable String functionId) {
        log.debug("REST request to get AdmFunctionUsedRecordDTO of statistics info : {}", criteria);
        criteria.setFunctionId(functionId);
        return admFunctionUsedRecordService.findStatisticsInfoByCriteria(criteria);
    }
}
