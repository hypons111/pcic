package gov.pcc.pwc.pwb.rest;

import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.domain.AdmFile;
import gov.pcc.pwc.repository.AdmFileRepository;
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
 * REST controller for managing {@link AdmFile}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class AdmFileResource {

    private final Logger log = LoggerFactory.getLogger(AdmFileResource.class);

    private static final String ENTITY_NAME = "admFile";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AdmFileRepository admFileRepository;

    public AdmFileResource(AdmFileRepository admFileRepository) {
        this.admFileRepository = admFileRepository;
    }

    /**
     * {@code POST  /adm-files} : Create a new admFile.
     *
     * @param admFile the admFile to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new admFile, or with status {@code 400 (Bad Request)} if the admFile has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/adm-files")
    public ResponseEntity<AdmFile> createAdmFile(@Valid @RequestBody AdmFile admFile) throws URISyntaxException {
        log.debug("REST request to save AdmFile : {}", admFile);
        if (admFile.getId() != null) {
            throw new BadRequestAlertException("A new admFile cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AdmFile result = admFileRepository.save(admFile);
        return ResponseEntity
            .created(new URI("/api/adm-files/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /adm-files/:id} : Updates an existing admFile.
     *
     * @param id the id of the admFile to save.
     * @param admFile the admFile to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated admFile,
     * or with status {@code 400 (Bad Request)} if the admFile is not valid,
     * or with status {@code 500 (Internal Server Error)} if the admFile couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/adm-files/{id}")
    public ResponseEntity<AdmFile> updateAdmFile(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody AdmFile admFile
    ) throws URISyntaxException {
        log.debug("REST request to update AdmFile : {}, {}", id, admFile);
        if (admFile.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, admFile.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!admFileRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        AdmFile result = admFileRepository.save(admFile);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, admFile.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /adm-files/:id} : Partial updates given fields of an existing admFile, field will ignore if it is null
     *
     * @param id the id of the admFile to save.
     * @param admFile the admFile to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated admFile,
     * or with status {@code 400 (Bad Request)} if the admFile is not valid,
     * or with status {@code 404 (Not Found)} if the admFile is not found,
     * or with status {@code 500 (Internal Server Error)} if the admFile couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/adm-files/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<AdmFile> partialUpdateAdmFile(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody AdmFile admFile
    ) throws URISyntaxException {
        log.debug("REST request to partial update AdmFile partially : {}, {}", id, admFile);
        if (admFile.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, admFile.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!admFileRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<AdmFile> result = admFileRepository
            .findById(admFile.getId())
            .map(
                existingAdmFile -> {
                    if (admFile.getModuleType() != null) {
                        existingAdmFile.setModuleType(admFile.getModuleType());
                    }
                    if (admFile.getBusinessType() != null) {
                        existingAdmFile.setBusinessType(admFile.getBusinessType());
                    }
                    if (admFile.getSourceId() != null) {
                        existingAdmFile.setSourceId(admFile.getSourceId());
                    }
                    if (admFile.getItemNo() != null) {
                        existingAdmFile.setItemNo(admFile.getItemNo());
                    }
                    if (admFile.getFileType() != null) {
                        existingAdmFile.setFileType(admFile.getFileType());
                    }
                    if (admFile.getFileNameOri() != null) {
                        existingAdmFile.setFileNameOri(admFile.getFileNameOri());
                    }
                    if (admFile.getFilePathName() != null) {
                        existingAdmFile.setFilePathName(admFile.getFilePathName());
                    }
                    if (admFile.getFileVirusStatus() != null) {
                        existingAdmFile.setFileVirusStatus(admFile.getFileVirusStatus());
                    }
                    if (admFile.getFileRemark() != null) {
                        existingAdmFile.setFileRemark(admFile.getFileRemark());
                    }
                    if (admFile.getS3Url() != null) {
                        existingAdmFile.setS3Url(admFile.getS3Url());
                    }
                    if (admFile.getCreateUser() != null) {
                        existingAdmFile.setCreateUser(admFile.getCreateUser());
                    }
                    if (admFile.getCreateTime() != null) {
                        existingAdmFile.setCreateTime(admFile.getCreateTime());
                    }
                    if (admFile.getUpdateUser() != null) {
                        existingAdmFile.setUpdateUser(admFile.getUpdateUser());
                    }
                    if (admFile.getUpdateTime() != null) {
                        existingAdmFile.setUpdateTime(admFile.getUpdateTime());
                    }

                    return existingAdmFile;
                }
            )
            .map(admFileRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, admFile.getId().toString())
        );
    }

    /**
     * {@code GET  /adm-files} : get all the admFiles.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of admFiles in body.
     */
    @GetMapping("/adm-files")
    public List<AdmFile> getAllAdmFiles() {
        log.debug("REST request to get all AdmFiles");
        return admFileRepository.findAll();
    }

    /**
     * {@code GET  /adm-files/:id} : get the "id" admFile.
     *
     * @param id the id of the admFile to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the admFile, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/adm-files/{id}")
    public ResponseEntity<AdmFile> getAdmFile(@PathVariable Long id) {
        log.debug("REST request to get AdmFile : {}", id);
        Optional<AdmFile> admFile = admFileRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(admFile);
    }

    /**
     * {@code DELETE  /adm-files/:id} : delete the "id" admFile.
     *
     * @param id the id of the admFile to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/adm-files/{id}")
    public ResponseEntity<Void> deleteAdmFile(@PathVariable Long id) {
        log.debug("REST request to delete AdmFile : {}", id);
        admFileRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
