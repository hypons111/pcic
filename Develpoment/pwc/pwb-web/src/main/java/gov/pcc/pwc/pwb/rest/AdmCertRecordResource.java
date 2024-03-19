package gov.pcc.pwc.pwb.rest;

import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.domain.AdmCertRecord;
import gov.pcc.pwc.repository.AdmCertRecordRepository;
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
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link AdmCertRecord}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class AdmCertRecordResource {

    private final Logger log = LoggerFactory.getLogger(AdmCertRecordResource.class);

    private static final String ENTITY_NAME = "admCertRecord";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AdmCertRecordRepository admCertRecordRepository;

    public AdmCertRecordResource(AdmCertRecordRepository admCertRecordRepository) {
        this.admCertRecordRepository = admCertRecordRepository;
    }

    /**
     * {@code POST  /adm-cert-records} : Create a new admCertRecord.
     *
     * @param admCertRecord the admCertRecord to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new admCertRecord, or with status {@code 400 (Bad Request)} if the admCertRecord has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/adm-cert-records")
    public ResponseEntity<AdmCertRecord> createAdmCertRecord(@Valid @RequestBody AdmCertRecord admCertRecord) throws URISyntaxException {
        log.debug("REST request to save AdmCertRecord : {}", admCertRecord);
        if (admCertRecord.getId() != null) {
            throw new BadRequestAlertException("A new admCertRecord cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AdmCertRecord result = admCertRecordRepository.save(admCertRecord);
        return ResponseEntity
            .created(new URI("/api/adm-cert-records/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /adm-cert-records/:id} : Updates an existing admCertRecord.
     *
     * @param id the id of the admCertRecord to save.
     * @param admCertRecord the admCertRecord to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated admCertRecord,
     * or with status {@code 400 (Bad Request)} if the admCertRecord is not valid,
     * or with status {@code 500 (Internal Server Error)} if the admCertRecord couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/adm-cert-records/{id}")
    public ResponseEntity<AdmCertRecord> updateAdmCertRecord(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody AdmCertRecord admCertRecord
    ) throws URISyntaxException {
        log.debug("REST request to update AdmCertRecord : {}, {}", id, admCertRecord);
        if (admCertRecord.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, admCertRecord.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!admCertRecordRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        AdmCertRecord result = admCertRecordRepository.save(admCertRecord);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, admCertRecord.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /adm-cert-records/:id} : Partial updates given fields of an existing admCertRecord, field will ignore if it is null
     *
     * @param id the id of the admCertRecord to save.
     * @param admCertRecord the admCertRecord to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated admCertRecord,
     * or with status {@code 400 (Bad Request)} if the admCertRecord is not valid,
     * or with status {@code 404 (Not Found)} if the admCertRecord is not found,
     * or with status {@code 500 (Internal Server Error)} if the admCertRecord couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/adm-cert-records/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<AdmCertRecord> partialUpdateAdmCertRecord(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody AdmCertRecord admCertRecord
    ) throws URISyntaxException {
        log.debug("REST request to partial update AdmCertRecord partially : {}, {}", id, admCertRecord);
        if (admCertRecord.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, admCertRecord.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!admCertRecordRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<AdmCertRecord> result = admCertRecordRepository
            .findById(admCertRecord.getId())
            .map(
                existingAdmCertRecord -> {
                    if (admCertRecord.getFunctionId() != null) {
                        existingAdmCertRecord.setFunctionId(admCertRecord.getFunctionId());
                    }
                    if (admCertRecord.getCertCategory() != null) {
                        existingAdmCertRecord.setCertCategory(admCertRecord.getCertCategory());
                    }
                    if (admCertRecord.getCertSn() != null) {
                        existingAdmCertRecord.setCertSn(admCertRecord.getCertSn());
                    }
                    if (admCertRecord.getSignOps() != null) {
                        existingAdmCertRecord.setSignOps(admCertRecord.getSignOps());
                    }
                    if (admCertRecord.getEncOps() != null) {
                        existingAdmCertRecord.setEncOps(admCertRecord.getEncOps());
                    }
                    if (admCertRecord.getCreateTime() != null) {
                        existingAdmCertRecord.setCreateTime(admCertRecord.getCreateTime());
                    }
                    if (admCertRecord.getCreateUserSys() != null) {
                        existingAdmCertRecord.setCreateUserSys(admCertRecord.getCreateUserSys());
                    }

                    return existingAdmCertRecord;
                }
            )
            .map(admCertRecordRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, admCertRecord.getId().toString())
        );
    }

    /**
     * {@code GET  /adm-cert-records} : get all the admCertRecords.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of admCertRecords in body.
     */
    @GetMapping("/adm-cert-records")
    public List<AdmCertRecord> getAllAdmCertRecords() {
        log.debug("REST request to get all AdmCertRecords");
        return admCertRecordRepository.findAll();
    }

    /**
     * {@code GET  /adm-cert-records/:id} : get the "id" admCertRecord.
     *
     * @param id the id of the admCertRecord to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the admCertRecord, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/adm-cert-records/{id}")
    public ResponseEntity<AdmCertRecord> getAdmCertRecord(@PathVariable Long id) {
        log.debug("REST request to get AdmCertRecord : {}", id);
        Optional<AdmCertRecord> admCertRecord = admCertRecordRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(admCertRecord);
    }

    /**
     * {@code DELETE  /adm-cert-records/:id} : delete the "id" admCertRecord.
     *
     * @param id the id of the admCertRecord to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/adm-cert-records/{id}")
    public ResponseEntity<Void> deleteAdmCertRecord(@PathVariable Long id) {
        log.debug("REST request to delete AdmCertRecord : {}", id);
        admCertRecordRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
