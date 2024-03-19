package gov.pcc.pwc.pwb.rest;

import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.domain.AdmImportRecord;
import gov.pcc.pwc.repository.AdmImportRecordRepository;
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
 * REST controller for managing {@link AdmImportRecord}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class AdmImportRecordResource {

    private final Logger log = LoggerFactory.getLogger(AdmImportRecordResource.class);

    private static final String ENTITY_NAME = "admImportRecord";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AdmImportRecordRepository admImportRecordRepository;

    public AdmImportRecordResource(AdmImportRecordRepository admImportRecordRepository) {
        this.admImportRecordRepository = admImportRecordRepository;
    }

    /**
     * {@code POST  /adm-import-records} : Create a new admImportRecord.
     *
     * @param admImportRecord the admImportRecord to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new admImportRecord, or with status {@code 400 (Bad Request)} if the admImportRecord has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/adm-import-records")
    public ResponseEntity<AdmImportRecord> createAdmImportRecord(@Valid @RequestBody AdmImportRecord admImportRecord)
        throws URISyntaxException {
        log.debug("REST request to save AdmImportRecord : {}", admImportRecord);
        if (admImportRecordRepository.existsById(admImportRecord.getId())) {
            throw new BadRequestAlertException("A new admImportRecord cannot have the existed ID", ENTITY_NAME, "idinvalid");
        }
        AdmImportRecord result = admImportRecordRepository.save(admImportRecord);
        return ResponseEntity
            .created(new URI("/api/adm-import-records/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /adm-import-records/:id} : Updates an existing admImportRecord.
     *
     * @param id the id of the admImportRecord to save.
     * @param admImportRecord the admImportRecord to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated admImportRecord,
     * or with status {@code 400 (Bad Request)} if the admImportRecord is not valid,
     * or with status {@code 500 (Internal Server Error)} if the admImportRecord couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/adm-import-records/{id}")
    public ResponseEntity<AdmImportRecord> updateAdmImportRecord(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody AdmImportRecord admImportRecord
    ) throws URISyntaxException {
        log.debug("REST request to update AdmImportRecord : {}, {}", id, admImportRecord);
        if (admImportRecord.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, admImportRecord.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!admImportRecordRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        AdmImportRecord result = admImportRecordRepository.save(admImportRecord);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, admImportRecord.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /adm-import-records/:id} : get the "id" admImportRecord.
     *
     * @param id the id of the admImportRecord to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the admImportRecord, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/adm-import-records/{id}")
    public ResponseEntity<AdmImportRecord> getAdmImportRecord(@PathVariable Long id) {
        log.debug("REST request to get AdmImportRecord : {}", id);
        Optional<AdmImportRecord> admImportRecord = admImportRecordRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(admImportRecord);
    }
}
