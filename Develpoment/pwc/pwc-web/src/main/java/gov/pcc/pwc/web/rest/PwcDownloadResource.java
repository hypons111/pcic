package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.repository.PwcDownloadRepository;
import gov.pcc.pwc.service.PwcDownloadService;
import gov.pcc.pwc.service.dto.BulletinDownAndInfoDTO;
import gov.pcc.pwc.service.dto.PwcDownloadDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * REST controller for managing {@link gov.pcc.pwc.domain.PwcDownload}.
 */
@RestController
@RequestMapping("/api/service")
public class PwcDownloadResource {

    private final Logger log = LoggerFactory.getLogger(PwcDownloadResource.class);

    private static final String ENTITY_NAME = "pwcDownload";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PwcDownloadService pwcDownloadService;

    private final PwcDownloadRepository pwcDownloadRepository;

    public PwcDownloadResource(PwcDownloadService pwcDownloadService, PwcDownloadRepository pwcDownloadRepository) {
        this.pwcDownloadService = pwcDownloadService;
        this.pwcDownloadRepository = pwcDownloadRepository;
    }

    /**
     * {@code POST  /pwc-downloads} : Create a new pwcDownload.
     *
     * @param pwcDownloadDTO the pwcDownloadDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pwcDownloadDTO, or with status {@code 400 (Bad Request)} if the pwcDownload has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pwc-downloads")
    public ResponseEntity<PwcDownloadDTO> createPwcDownload(@Valid @RequestBody PwcDownloadDTO pwcDownloadDTO) throws URISyntaxException {
        log.debug("REST request to save PwcDownload : {}", pwcDownloadDTO);
        if (pwcDownloadDTO.getId() != null) {
            throw new BadRequestAlertException("A new pwcDownload cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PwcDownloadDTO result = pwcDownloadService.save(pwcDownloadDTO);
        return ResponseEntity
            .created(new URI("/api/pwc-downloads/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pwc-downloads/:proDownloadId} : Updates an existing pwcDownload.
     *
     * @param proDownloadId the id of the pwcDownloadDTO to save.
     * @param pwcDownloadDTO the pwcDownloadDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pwcDownloadDTO,
     * or with status {@code 400 (Bad Request)} if the pwcDownloadDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pwcDownloadDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pwc-downloads/{proDownloadId}")
    public ResponseEntity<PwcDownloadDTO> updatePwcDownload(
        @PathVariable(value = "proDownloadId", required = false) final Long proDownloadId,
        @Valid @RequestBody PwcDownloadDTO pwcDownloadDTO
    ) throws URISyntaxException {
        log.debug("REST request to update PwcDownload : {}, {}", proDownloadId, pwcDownloadDTO);
        if (pwcDownloadDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(proDownloadId, pwcDownloadDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!pwcDownloadRepository.existsById(proDownloadId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        PwcDownloadDTO result = pwcDownloadService.save(pwcDownloadDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pwcDownloadDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /pwc-downloads/:proDownloadId} : Partial updates given fields of an existing pwcDownload, field will ignore if it is null
     *
     * @param proDownloadId the id of the pwcDownloadDTO to save.
     * @param pwcDownloadDTO the pwcDownloadDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pwcDownloadDTO,
     * or with status {@code 400 (Bad Request)} if the pwcDownloadDTO is not valid,
     * or with status {@code 404 (Not Found)} if the pwcDownloadDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the pwcDownloadDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/pwc-downloads/{proDownloadId}", consumes = "application/merge-patch+json")
    public ResponseEntity<PwcDownloadDTO> partialUpdatePwcDownload(
        @PathVariable(value = "proDownloadId", required = false) final Long proDownloadId,
        @NotNull @RequestBody PwcDownloadDTO pwcDownloadDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update PwcDownload partially : {}, {}", proDownloadId, pwcDownloadDTO);
        if (pwcDownloadDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(proDownloadId, pwcDownloadDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!pwcDownloadRepository.existsById(proDownloadId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PwcDownloadDTO> result = pwcDownloadService.partialUpdate(pwcDownloadDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pwcDownloadDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /pwc-downloads} : get all the pwcDownloads.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pwcDownloads in body.
     */
    @GetMapping("/pwc-downloads")
    public List<PwcDownloadDTO> getAllPwcDownloads() {
        log.debug("REST request to get all PwcDownloads");
        return pwcDownloadService.findAll();
    }

    /**
     * {@code GET  /pwc-downloads/:id} : get the "id" pwcDownload.
     *
     * @param id the id of the pwcDownloadDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pwcDownloadDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pwc-downloads/{id}")
    public ResponseEntity<PwcDownloadDTO> getPwcDownload(@PathVariable Long id) {
        log.debug("REST request to get PwcDownload : {}", id);
        Optional<PwcDownloadDTO> pwcDownloadDTO = pwcDownloadService.findOne(id);
        return ResponseUtil.wrapOrNotFound(pwcDownloadDTO);
    }

    /**
     * {@code POST  /cms-comps/criteria} : get PwcDownloadDTO by criteria.
     *
     * @param criteria the criteria of the PwcDownloadDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the PwcDownloadDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/pwc-download/criteria")
    public List<PwcDownloadDTO> getDownloadByCriteria(@RequestBody PwcDownloadDTO criteria) {
        log.debug("REST request to get PwcDownload : {}", criteria);
        return pwcDownloadService.findDownloadByCriteria(criteria);
    }

    /**
     * {@code DELETE  /info-delete} : delete the "id" pwcInformation.
     *
     * @param criteria the id of the pwcInformationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/download-delete")
    public ResponseEntity<Integer> downloadDelete(@RequestBody PwcDownloadDTO criteria) {
        log.debug("REST request to delete PwcInformation : {}", criteria.getId());
        return ResponseEntity.ok(pwcDownloadService.deleteByID(criteria));
    }

    /**
     * {@code POST  /download-save} : Create a new pwcDownload....
     *
     * @param bulletinDownAndInfoDTO the bulletinDownAndInfoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bulletinDownAndInfoDTO, or with status {@code 400 (Bad Request)} if the pwcDownload.. has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping(path = "/download-save", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<PwcDownloadDTO> createDownload( @Valid @RequestPart("form") BulletinDownAndInfoDTO bulletinDownAndInfoDTO,
                                                          @RequestPart("uploadFile") List<MultipartFile> uploadFile)
            throws URISyntaxException, IOException {
        log.debug("REST request to save PwcDownload : {}", bulletinDownAndInfoDTO);
        if (bulletinDownAndInfoDTO.getId() != null) {
            throw new BadRequestAlertException("A new pwcDownload cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PwcDownloadDTO result = pwcDownloadService.saveAll(bulletinDownAndInfoDTO, uploadFile);
        return ResponseEntity
                .created(new URI("/api/download-save/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * {@code PUT  /download-updated : Updates an existing pwcDownload.
     *
     * @param bulletinDownAndInfoDTO the pwcDownloadDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pwcDownloadDTO,
     * or with status {@code 400 (Bad Request)} if the pwcDownloadDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pwcDownloadDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping(path = "/download-updated", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<PwcDownloadDTO> updateDownload(@Valid @RequestPart("form") BulletinDownAndInfoDTO bulletinDownAndInfoDTO,
                                                         @RequestPart("uploadFile") List<MultipartFile> uploadFile) throws URISyntaxException, IOException {
        log.debug("REST request to update PwcInformation : {}, {}", bulletinDownAndInfoDTO.getId(), bulletinDownAndInfoDTO);
        if (bulletinDownAndInfoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        if (!pwcDownloadRepository.existsById(bulletinDownAndInfoDTO.getId())) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        PwcDownloadDTO result = pwcDownloadService.saveAll(bulletinDownAndInfoDTO, uploadFile);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bulletinDownAndInfoDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code POST  /pwc-download/getDownByCriteria} : get PwcDownloadDTO by criteria.
     *
     * @param criteria the criteria of the PwcDownloadDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the PwcDownloadDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/pwc-download/getDownByCriteria")
    public List<PwcDownloadDTO> getDownByCriteria(@RequestBody PwcDownloadDTO criteria) {
        log.debug("REST request to get PwcDownload : {}", criteria);
        return pwcDownloadService.findDownByCriteria(criteria);
    }
}
