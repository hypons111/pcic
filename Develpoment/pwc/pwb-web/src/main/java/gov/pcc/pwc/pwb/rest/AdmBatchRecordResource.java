package gov.pcc.pwc.pwb.rest;

import gov.pcc.pwc.domain.AdmBatchRecord;
import gov.pcc.pwc.service.AdmBatchRecordService;
import gov.pcc.pwc.service.criteria.AdmBatchRecordCriteria;
import gov.pcc.pwc.service.dto.AdmBatchRecordDTO;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing {@link AdmBatchRecord}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class AdmBatchRecordResource {

    private final Logger log = LoggerFactory.getLogger(AdmBatchRecordResource.class);

    private static final String ENTITY_NAME = "admBatchRecord";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AdmBatchRecordService admBatchRecordService;

    public AdmBatchRecordResource(AdmBatchRecordService admBatchRecordService) {
        this.admBatchRecordService = admBatchRecordService;
    }

    /**
     * {@code POST  /adm-batch-records/criteria-jpa} : get admBatchRecord by criteria.
     *
     * @param criteria the criteria of the AdmBatchRecordCriteria to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the AdmBatchRecordDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/adm-batch-records/criteria-jpa")
    public Page<AdmBatchRecordDTO> getAdmBatchRecordsByCriteria(@Valid @RequestBody AdmBatchRecordCriteria criteria) {
        log.debug("REST request to get AdmBatchRecordDTO : {}", criteria);
        return admBatchRecordService.findByCriteria(criteria);
    }
    //    /**
    //     * {@code DELETE  /adm-batch-records/:id} : delete the "id" admBatchRecord.
    //     *
    //     * @param id the id of the admBatchRecord to delete.
    //     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
    //     */
    //    @DeleteMapping("/adm-batch-records/{id}")
    //    public ResponseEntity<Void> deleteAdmBatchRecord(@PathVariable Long id) {
    //        log.debug("REST request to delete AdmBatchRecord : {}", id);
    //        admBatchRecordRepository.deleteById(id);
    //        return ResponseEntity
    //            .noContent()
    //            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
    //            .build();
    //    }
}
