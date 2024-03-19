package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.common.web.ReportMediaType;
import gov.pcc.pwc.common.web.io.DownloadableResource;
import gov.pcc.pwc.repository.PwcInformationAppendixRepository;
import gov.pcc.pwc.service.PwcInformationAppendixService;
import gov.pcc.pwc.service.dto.PwcInformationAppendixDTO;
import gov.pcc.pwc.service.dto.PwcInformationDTO;
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
 * REST controller for managing {@link gov.pcc.pwc.domain.PwcInformationAppendix}.
 */
@RestController
@RequestMapping("/api/service")
public class PwcInformationAppendixResource {

    private final Logger log = LoggerFactory.getLogger(PwcInformationAppendixResource.class);

    private static final String ENTITY_NAME = "pwcInformationAppendix";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PwcInformationAppendixService pwcInformationAppendixService;

    private final PwcInformationAppendixRepository pwcInformationAppendixRepository;

    public PwcInformationAppendixResource(
        PwcInformationAppendixService pwcInformationAppendixService,
        PwcInformationAppendixRepository pwcInformationAppendixRepository
    ) {
        this.pwcInformationAppendixService = pwcInformationAppendixService;
        this.pwcInformationAppendixRepository = pwcInformationAppendixRepository;
    }

    /**
     * {@code POST  /pwc-information-appendices} : Create a new pwcInformationAppendix.
     *
     * @param pwcInformationAppendixDTO the pwcInformationAppendixDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pwcInformationAppendixDTO, or with status {@code 400 (Bad Request)} if the pwcInformationAppendix has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pwc-information-appendices")
    public ResponseEntity<PwcInformationAppendixDTO> createPwcInformationAppendix(
        @Valid @RequestBody PwcInformationAppendixDTO pwcInformationAppendixDTO
    ) throws URISyntaxException {
        log.debug("REST request to save PwcInformationAppendix : {}", pwcInformationAppendixDTO);
        if (pwcInformationAppendixDTO.getProInformationAppendixId() != null) {
            throw new BadRequestAlertException("A new pwcInformationAppendix cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PwcInformationAppendixDTO result = pwcInformationAppendixService.save(pwcInformationAppendixDTO);
        return ResponseEntity
            .created(new URI("/api/pwc-information-appendices/" + result.getProInformationAppendixId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getProInformationAppendixId().toString())
            )
            .body(result);
    }

    /**
     * {@code PUT  /pwc-information-appendices/:proInformationAppendixId} : Updates an existing pwcInformationAppendix.
     *
     * @param proInformationAppendixId the id of the pwcInformationAppendixDTO to save.
     * @param pwcInformationAppendixDTO the pwcInformationAppendixDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pwcInformationAppendixDTO,
     * or with status {@code 400 (Bad Request)} if the pwcInformationAppendixDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pwcInformationAppendixDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pwc-information-appendices/{proInformationAppendixId}")
    public ResponseEntity<PwcInformationAppendixDTO> updatePwcInformationAppendix(
        @PathVariable(value = "proInformationAppendixId", required = false) final Long proInformationAppendixId,
        @Valid @RequestBody PwcInformationAppendixDTO pwcInformationAppendixDTO
    ) throws URISyntaxException {
        log.debug("REST request to update PwcInformationAppendix : {}, {}", proInformationAppendixId, pwcInformationAppendixDTO);
        if (pwcInformationAppendixDTO.getProInformationAppendixId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(proInformationAppendixId, pwcInformationAppendixDTO.getProInformationAppendixId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!pwcInformationAppendixRepository.existsById(proInformationAppendixId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        PwcInformationAppendixDTO result = pwcInformationAppendixService.save(pwcInformationAppendixDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    pwcInformationAppendixDTO.getProInformationAppendixId().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PATCH  /pwc-information-appendices/:proInformationAppendixId} : Partial updates given fields of an existing pwcInformationAppendix, field will ignore if it is null
     *
     * @param proInformationAppendixId the id of the pwcInformationAppendixDTO to save.
     * @param pwcInformationAppendixDTO the pwcInformationAppendixDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pwcInformationAppendixDTO,
     * or with status {@code 400 (Bad Request)} if the pwcInformationAppendixDTO is not valid,
     * or with status {@code 404 (Not Found)} if the pwcInformationAppendixDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the pwcInformationAppendixDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/pwc-information-appendices/{proInformationAppendixId}", consumes = "application/merge-patch+json")
    public ResponseEntity<PwcInformationAppendixDTO> partialUpdatePwcInformationAppendix(
        @PathVariable(value = "proInformationAppendixId", required = false) final Long proInformationAppendixId,
        @NotNull @RequestBody PwcInformationAppendixDTO pwcInformationAppendixDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update PwcInformationAppendix partially : {}, {}",
            proInformationAppendixId,
            pwcInformationAppendixDTO
        );
        if (pwcInformationAppendixDTO.getProInformationAppendixId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(proInformationAppendixId, pwcInformationAppendixDTO.getProInformationAppendixId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!pwcInformationAppendixRepository.existsById(proInformationAppendixId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PwcInformationAppendixDTO> result = pwcInformationAppendixService.partialUpdate(pwcInformationAppendixDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                true,
                ENTITY_NAME,
                pwcInformationAppendixDTO.getProInformationAppendixId().toString()
            )
        );
    }

    /**
     * {@code GET  /pwc-information-appendices} : get all the pwcInformationAppendices.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pwcInformationAppendices in body.
     */
    @GetMapping("/pwc-information-appendices")
    public List<PwcInformationAppendixDTO> getAllPwcInformationAppendices() {
        log.debug("REST request to get all PwcInformationAppendices");
        return pwcInformationAppendixService.findAll();
    }

    /**
     * {@code GET  /pwc-information-appendices/:id} : get the "id" pwcInformationAppendix.
     *
     * @param id the id of the pwcInformationAppendixDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pwcInformationAppendixDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pwc-information-appendices/{id}")
    public ResponseEntity<PwcInformationAppendixDTO> getPwcInformationAppendix(@PathVariable Long id) {
        log.debug("REST request to get PwcInformationAppendix : {}", id);
        Optional<PwcInformationAppendixDTO> pwcInformationAppendixDTO = pwcInformationAppendixService.findOne(id);
        return ResponseUtil.wrapOrNotFound(pwcInformationAppendixDTO);
    }

    /**
     * {@code DELETE  /pwc-information-appendices/:id} : delete the "id" pwcInformationAppendix.
     *
     * @param id the id of the pwcInformationAppendixDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pwc-information-appendices/{id}")
    public ResponseEntity<Void> deletePwcInformationAppendix(@PathVariable Long id) {
        log.debug("REST request to delete PwcInformationAppendix : {}", id);
        pwcInformationAppendixService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code POST  /info-appendix/link} : get PwcInformationDTO by criteria.
     *
     * @param criteria the criteria of the PwcInformationDTO to retrieve.
     * @return the {@link PwcInformationAppendixDTO} with status {@code 200 (OK)} and with body the PwcInformationAppendixDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/info-appendix/link")
    public List<PwcInformationAppendixDTO> getAppendixLinkByCriteria(@RequestBody PwcInformationDTO criteria) {
        log.debug("REST request to get PwcInformation : {}", criteria);
        return pwcInformationAppendixService.findLinkByCriteria(criteria);
    }

    /**
     * {@code POST  /info-appendix/attachment} : get PwcInformationDTO by criteria.
     *
     * @param criteria the criteria of the PwcInformationDTO to retrieve.
     * @return the {@link PwcInformationAppendixDTO} with status {@code 200 (OK)} and with body the PwcInformationAppendixDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/info-appendix/attachment")
    public List<PwcInformationAppendixDTO> getAppendixAttachmentByCriteria(@RequestBody PwcInformationDTO criteria) {
        log.debug("REST request to get PwcInformation : {}", criteria);
        return pwcInformationAppendixService.findAttachmentByCriteria(criteria);
    }

    /**
     * * {@code POST  /info-download} : get fileCode.
     *
     * @param fileCode the String of the retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ResponseEntity, or with status {@code 404 (Not Found)}.
     */
    @PostMapping(path = "/info-download", produces = ReportMediaType.APPLICATION_ODS_VALUE)
    public ResponseEntity<DownloadableResource> downloadFileByInfo(@RequestBody String fileCode) throws Exception {
        log.debug("REST request to get PwcInformation : {}", fileCode);
        DownloadableResource infoDownload = pwcInformationAppendixService.downFileByInfo(fileCode);
        return ResponseEntity.ok(infoDownload);
    }
}
