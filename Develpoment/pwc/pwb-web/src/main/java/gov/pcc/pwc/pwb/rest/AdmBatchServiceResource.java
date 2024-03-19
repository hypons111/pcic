package gov.pcc.pwc.pwb.rest;

import gov.pcc.pwc.MessageCodes;
import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.domain.AdmBatchService;
import gov.pcc.pwc.repository.AdmBatchServiceRepository;
import gov.pcc.pwc.service.AdmBatchServiceService;
import gov.pcc.pwc.service.criteria.AdmBatchServiceCriteria;
import gov.pcc.pwc.service.dto.AdmBatchServiceDTO;
import gov.pcc.pwc.utils.PwcHeadUtil;
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
 * REST controller for managing {@link AdmBatchService}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class AdmBatchServiceResource {

    private final Logger log = LoggerFactory.getLogger(AdmBatchServiceResource.class);

    private static final String ENTITY_NAME = "admBatchService";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AdmBatchServiceService admBatchServiceService;

    private final AdmBatchServiceRepository admBatchServiceRepository;

    public AdmBatchServiceResource(AdmBatchServiceService admBatchServiceService, AdmBatchServiceRepository admBatchServiceRepository) {
        this.admBatchServiceService = admBatchServiceService;
        this.admBatchServiceRepository = admBatchServiceRepository;
    }

    /**
     * {@code POST  /adm-batch-services} : Create a new admBatchService.
     *
     * @param admBatchService the admBatchService to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new admBatchService, or with status {@code 400 (Bad Request)} if the admBatchService has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/adm-batch-services")
    public ResponseEntity<AdmBatchService> createAdmBatchService(@Valid @RequestBody AdmBatchService admBatchService)
        throws URISyntaxException {
        log.debug("REST request to save AdmBatchService : {}", admBatchService);
        if (admBatchServiceRepository.existsById(admBatchService.getBatchServiceNo())) {
            throw new BadRequestAlertException("A new admBatchService cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AdmBatchService result = admBatchServiceRepository.save(admBatchService);
        return ResponseEntity
            .created(new URI("/api/adm-batch-services/" + result.getBatchServiceNo()))
            .headers(PwcHeadUtil.createAlert(MessageCodes.PWB_AMD09_0001_S(result.getBatchServiceNo())))
            .body(result);
    }

    /**
     * {@code PUT  /adm-batch-services/:batchServiceNo} : Updates an existing admBatchService.
     *
     * @param batchServiceNo the id of the admBatchService to save.
     * @param admBatchService the admBatchService to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated admBatchService,
     * or with status {@code 400 (Bad Request)} if the admBatchService is not valid,
     * or with status {@code 500 (Internal Server Error)} if the admBatchService couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/adm-batch-services/{batchServiceNo}")
    public ResponseEntity<AdmBatchService> updateAdmBatchService(
        @PathVariable(value = "batchServiceNo", required = false) final String batchServiceNo,
        @Valid @RequestBody AdmBatchService admBatchService
    ) throws URISyntaxException {
        log.debug("REST request to update AdmBatchService : {}, {}", batchServiceNo, admBatchService);
        if (admBatchService.getBatchServiceNo() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(batchServiceNo, admBatchService.getBatchServiceNo())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!admBatchServiceRepository.existsById(batchServiceNo)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        AdmBatchService result = admBatchServiceRepository.save(admBatchService);
        return ResponseEntity.ok().headers(PwcHeadUtil.createAlert(MessageCodes.PWB_AMD09_0002_S(result.getBatchServiceNo()))).body(result);
    }


    /**
     * {@code GET  /adm-batch-services} : get all the admBatchServices.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of admBatchServices in body.
     */
    @GetMapping("/adm-batch-services")
    public List<AdmBatchService> getAllAdmBatchServices() {
        log.debug("REST request to get all AdmBatchServices");
        return admBatchServiceRepository.findAll();
    }

    /**
     * {@code GET  /adm-batch-services/:id} : get the "id" admBatchService.
     *
     * @param id the id of the admBatchService to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the admBatchService, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/adm-batch-services/{id}")
    public ResponseEntity<AdmBatchService> getAdmBatchService(@PathVariable String id) {
        log.debug("REST request to get AdmBatchService : {}", id);
        Optional<AdmBatchService> admBatchService = admBatchServiceRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(admBatchService);
    }

    /**
     * {@code DELETE  /adm-batch-services/:id} : delete the "id" admBatchService.
     *
     * @param id the id of the admBatchService to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/adm-batch-services/{id}")
    public ResponseEntity<Void> deleteAdmBatchService(@PathVariable String id) {
        log.debug("REST request to delete AdmBatchService : {}", id);
        admBatchServiceRepository.deleteById(id);
        return ResponseEntity.noContent().headers(PwcHeadUtil.createAlert(MessageCodes.PWB_AMD09_0003_S(id))).build();
    }

    /**
     * {@code POST  /adm-batch-services/criteria-jpa} : get admBatchService by criteria.
     *
     * @param criteria the criteria of the AdmBatchServiceDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the AdmBatchServiceDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/adm-batch-services/criteria-jpa")
    public Page<AdmBatchServiceDTO> getBatchServiceByCriteria(@Valid @RequestBody AdmBatchServiceCriteria criteria) {
        log.debug("REST request to get AdmBatchServiceDTO : {}", criteria);
        return admBatchServiceService.findByCriteria(criteria);
    }
}
