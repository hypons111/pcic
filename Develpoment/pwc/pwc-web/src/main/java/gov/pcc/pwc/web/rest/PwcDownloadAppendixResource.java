package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.common.web.ReportMediaType;
import gov.pcc.pwc.common.web.io.DownloadableResource;
import gov.pcc.pwc.repository.PwcDownloadAppendixRepository;
import gov.pcc.pwc.service.PwcDownloadAppendixService;
import gov.pcc.pwc.service.dto.PwcDownloadAppendixDTO;
import gov.pcc.pwc.service.dto.PwcDownloadDTO;
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
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link gov.pcc.pwc.domain.PwcDownloadAppendix}.
 */
@RestController
@RequestMapping("/api/service")
public class PwcDownloadAppendixResource {

    private final Logger log = LoggerFactory.getLogger(PwcDownloadAppendixResource.class);

    private static final String ENTITY_NAME = "pwcDownloadAppendix";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PwcDownloadAppendixService pwcDownloadAppendixService;

    private final PwcDownloadAppendixRepository pwcDownloadAppendixRepository;

    public PwcDownloadAppendixResource(
        PwcDownloadAppendixService pwcDownloadAppendixService,
        PwcDownloadAppendixRepository pwcDownloadAppendixRepository
    ) {
        this.pwcDownloadAppendixService = pwcDownloadAppendixService;
        this.pwcDownloadAppendixRepository = pwcDownloadAppendixRepository;
    }

    /**
     * {@code POST  /pwc-download-appendices} : Create a new pwcDownloadAppendix.
     *
     * @param pwcDownloadAppendixDTO the pwcDownloadAppendixDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pwcDownloadAppendixDTO, or with status {@code 400 (Bad Request)} if the pwcDownloadAppendix has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pwc-download-appendices")
    public ResponseEntity<PwcDownloadAppendixDTO> createPwcDownloadAppendix(
        @Valid @RequestBody PwcDownloadAppendixDTO pwcDownloadAppendixDTO
    ) throws URISyntaxException {
        log.debug("REST request to save PwcDownloadAppendix : {}", pwcDownloadAppendixDTO);
        if (pwcDownloadAppendixDTO.getProDownloadAppendixId() != null) {
            throw new BadRequestAlertException("A new pwcDownloadAppendix cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PwcDownloadAppendixDTO result = pwcDownloadAppendixService.save(pwcDownloadAppendixDTO);
        return ResponseEntity
            .created(new URI("/api/pwc-download-appendices/" + result.getProDownloadAppendixId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getProDownloadAppendixId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pwc-download-appendices/:proDownloadAppendixId} : Updates an existing pwcDownloadAppendix.
     *
     * @param proDownloadAppendixId the id of the pwcDownloadAppendixDTO to save.
     * @param pwcDownloadAppendixDTO the pwcDownloadAppendixDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pwcDownloadAppendixDTO,
     * or with status {@code 400 (Bad Request)} if the pwcDownloadAppendixDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pwcDownloadAppendixDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pwc-download-appendices/{proDownloadAppendixId}")
    public ResponseEntity<PwcDownloadAppendixDTO> updatePwcDownloadAppendix(
        @PathVariable(value = "proDownloadAppendixId", required = false) final Long proDownloadAppendixId,
        @Valid @RequestBody PwcDownloadAppendixDTO pwcDownloadAppendixDTO
    ) throws URISyntaxException {
        log.debug("REST request to update PwcDownloadAppendix : {}, {}", proDownloadAppendixId, pwcDownloadAppendixDTO);
        if (pwcDownloadAppendixDTO.getProDownloadAppendixId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(proDownloadAppendixId, pwcDownloadAppendixDTO.getProDownloadAppendixId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!pwcDownloadAppendixRepository.existsById(proDownloadAppendixId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        PwcDownloadAppendixDTO result = pwcDownloadAppendixService.save(pwcDownloadAppendixDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    pwcDownloadAppendixDTO.getProDownloadAppendixId().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PATCH  /pwc-download-appendices/:proDownloadAppendixId} : Partial updates given fields of an existing pwcDownloadAppendix, field will ignore if it is null
     *
     * @param proDownloadAppendixId the id of the pwcDownloadAppendixDTO to save.
     * @param pwcDownloadAppendixDTO the pwcDownloadAppendixDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pwcDownloadAppendixDTO,
     * or with status {@code 400 (Bad Request)} if the pwcDownloadAppendixDTO is not valid,
     * or with status {@code 404 (Not Found)} if the pwcDownloadAppendixDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the pwcDownloadAppendixDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/pwc-download-appendices/{proDownloadAppendixId}", consumes = "application/merge-patch+json")
    public ResponseEntity<PwcDownloadAppendixDTO> partialUpdatePwcDownloadAppendix(
        @PathVariable(value = "proDownloadAppendixId", required = false) final Long proDownloadAppendixId,
        @NotNull @RequestBody PwcDownloadAppendixDTO pwcDownloadAppendixDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update PwcDownloadAppendix partially : {}, {}", proDownloadAppendixId, pwcDownloadAppendixDTO);
        if (pwcDownloadAppendixDTO.getProDownloadAppendixId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(proDownloadAppendixId, pwcDownloadAppendixDTO.getProDownloadAppendixId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!pwcDownloadAppendixRepository.existsById(proDownloadAppendixId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PwcDownloadAppendixDTO> result = pwcDownloadAppendixService.partialUpdate(pwcDownloadAppendixDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                true,
                ENTITY_NAME,
                pwcDownloadAppendixDTO.getProDownloadAppendixId().toString()
            )
        );
    }

    /**
     * {@code GET  /pwc-download-appendices} : get all the pwcDownloadAppendices.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pwcDownloadAppendices in body.
     */
    @GetMapping("/pwc-download-appendices")
    public List<PwcDownloadAppendixDTO> getAllPwcDownloadAppendices() {
        log.debug("REST request to get all PwcDownloadAppendices");
        return pwcDownloadAppendixService.findAll();
    }

    /**
     * {@code GET  /pwc-download-appendices/:id} : get the "id" pwcDownloadAppendix.
     *
     * @param id the id of the pwcDownloadAppendixDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pwcDownloadAppendixDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pwc-download-appendices/{id}")
    public ResponseEntity<PwcDownloadAppendixDTO> getPwcDownloadAppendix(@PathVariable Long id) {
        log.debug("REST request to get PwcDownloadAppendix : {}", id);
        Optional<PwcDownloadAppendixDTO> pwcDownloadAppendixDTO = pwcDownloadAppendixService.findOne(id);
        return ResponseUtil.wrapOrNotFound(pwcDownloadAppendixDTO);
    }

    /**
     * {@code DELETE  /pwc-download-appendices/:id} : delete the "id" pwcDownloadAppendix.
     *
     * @param id the id of the pwcDownloadAppendixDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pwc-download-appendices/{id}")
    public ResponseEntity<Void> deletePwcDownloadAppendix(@PathVariable Long id) {
        log.debug("REST request to delete PwcDownloadAppendix : {}", id);
        pwcDownloadAppendixService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code POST  /down-appendix/link} : get PwcDownloadDTO by criteria.
     *
     * @param criteria the criteria of the PwcDownloadDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the PwcDownloadAppendixDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/down-appendix/link")
    public List<PwcDownloadAppendixDTO> getAppendixLinkByCriteria(@RequestBody PwcDownloadDTO criteria) {
        log.debug("REST request to get PwcDownload : {}", criteria);
        return pwcDownloadAppendixService.findLinkByCriteria(criteria);
    }

    /**
     * {@code POST  /down-appendix/attachment"} : get PwcDownloadDTO by criteria.
     *
     * @param criteria the criteria of the PwcDownloadDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the PwcDownloadAppendixDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/down-appendix/attachment")
    public List<PwcDownloadAppendixDTO> getAppendixAttachmentByCriteria(@RequestBody PwcDownloadDTO criteria) {
        log.debug("REST request to get PwcDownload : {}", criteria);
        return pwcDownloadAppendixService.findAttachmentByCriteria(criteria);
    }

    /**
     *
     * * {@code POST  /file-download} : get fileCode.
     *
     * @param fileCode the String of the retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ResponseEntity, or with status {@code 404 (Not Found)}.
     */
    @PostMapping(path = "/file-download", produces = ReportMediaType.APPLICATION_ODS_VALUE)
    public ResponseEntity<DownloadableResource> downloadFileByInfo(@RequestBody String fileCode) throws Exception {
        log.debug("REST request to get PwcInformation : {}", fileCode);
        DownloadableResource fileDownload = pwcDownloadAppendixService.downFileByDown(fileCode);
        return ResponseEntity.ok(fileDownload);
    }
}
