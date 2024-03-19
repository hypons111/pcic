package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.repository.EctCheckRepository;
import gov.pcc.pwc.service.EctCheckService;
import gov.pcc.pwc.service.dto.EctCheckDTO;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import gov.pcc.pwc.service.odsExporter.DownloadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link gov.pcc.pwc.domain.EctCheck}.
 */
@RestController
@RequestMapping("/api")
public class EctCheckResource {

    private final Logger log = LoggerFactory.getLogger(EctCheckResource.class);

    private static final String ENTITY_NAME = "ectCheck";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EctCheckService ectCheckService;

    private final EctCheckRepository ectCheckRepository;

	private final DownloadService downloadService;

    public EctCheckResource(EctCheckService ectCheckService, EctCheckRepository ectCheckRepository, DownloadService downloadService) {
        this.ectCheckService = ectCheckService;
        this.ectCheckRepository = ectCheckRepository;
		this.downloadService = downloadService;
    }

    @PostMapping("/service/upload-cert-pdf")
    public Map<String, Object> uploadCertPdf(MultipartHttpServletRequest request) {
        log.debug("Rest uploadCertPdf");
        return ectCheckService.uploadCertPdf(request);
    }

    /**
     * {@code POST  /ect-checks} : Create a new ectCheck.
     *
     * @param ectCheckDTO the ectCheckDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ectCheckDTO, or with status {@code 400 (Bad Request)} if the ectCheck has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ect-checks")
    public ResponseEntity<EctCheckDTO> createEctCheck(@Valid @RequestBody EctCheckDTO ectCheckDTO) throws URISyntaxException {
        log.debug("REST request to save EctCheck : {}", ectCheckDTO);
        EctCheckDTO result = ectCheckService.save(ectCheckDTO);
        return ResponseEntity
            .created(new URI("/api/ect-checks/" + result.getEctCheckId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getEctCheckId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ect-checks/:ectCheckId} : Updates an existing ectCheck.
     *
     * @param ectCheckId the id of the ectCheckDTO to save.
     * @param ectCheckDTO the ectCheckDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ectCheckDTO,
     * or with status {@code 400 (Bad Request)} if the ectCheckDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ectCheckDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ect-checks/{ectCheckId}")
    public ResponseEntity<EctCheckDTO> updateEctCheck(
        @PathVariable(value = "ectCheckId", required = false) final Long ectCheckId,
        @Valid @RequestBody EctCheckDTO ectCheckDTO
    ) throws URISyntaxException {
        log.debug("REST request to update EctCheck : {}, {}", ectCheckId, ectCheckDTO);

        EctCheckDTO result = ectCheckService.save(ectCheckDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ectCheckDTO.getEctCheckId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /ect-checks/:ectCheckId} : Partial updates given fields of an existing ectCheck, field will ignore if it is null
     *
     * @param ectCheckId the id of the ectCheckDTO to save.
     * @param ectCheckDTO the ectCheckDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ectCheckDTO,
     * or with status {@code 400 (Bad Request)} if the ectCheckDTO is not valid,
     * or with status {@code 404 (Not Found)} if the ectCheckDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the ectCheckDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/ect-checks/{ectCheckId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<EctCheckDTO> partialUpdateEctCheck(
        @PathVariable(value = "ectCheckId", required = false) final Long ectCheckId,
        @NotNull @RequestBody EctCheckDTO ectCheckDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update EctCheck partially : {}, {}", ectCheckId, ectCheckDTO);

        Optional<EctCheckDTO> result = ectCheckService.partialUpdate(ectCheckDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ectCheckDTO.getEctCheckId().toString())
        );
    }

    /**
     * {@code GET  /ect-checks} : get all the ectChecks.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ectChecks in body.
     */
    @GetMapping("/ect-checks")
    public List<EctCheckDTO> getAllEctChecks() {
        log.debug("REST request to get all EctChecks");
        return ectCheckService.findAll();
    }

    /**
     * {@code GET  /ect-checks/:id} : get the "id" ectCheck.
     *
     * @param id the id of the ectCheckDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ectCheckDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ect-checks/{id}")
    public ResponseEntity<EctCheckDTO> getEctCheck(@PathVariable Long id) {
        log.debug("REST request to get EctCheck : {}", id);
        Optional<EctCheckDTO> ectCheckDTO = ectCheckService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ectCheckDTO);
    }

    /**
     * {@code DELETE  /ect-checks/:id} : delete the "id" ectCheck.
     *
     * @param id the id of the ectCheckDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ect-checks/{id}")
    public ResponseEntity<Void> deleteEctCheck(@PathVariable Long id) {
        log.debug("REST request to delete EctCheck : {}", id);
        ectCheckService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
