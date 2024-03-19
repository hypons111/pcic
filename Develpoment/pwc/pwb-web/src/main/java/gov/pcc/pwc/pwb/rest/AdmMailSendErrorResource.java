package gov.pcc.pwc.pwb.rest;

import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.domain.AdmMailSendError;
import gov.pcc.pwc.repository.AdmMailSendErrorRepository;
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
 * REST controller for managing {@link AdmMailSendError}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class AdmMailSendErrorResource {

    private final Logger log = LoggerFactory.getLogger(AdmMailSendErrorResource.class);

    private static final String ENTITY_NAME = "admMailSendError";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AdmMailSendErrorRepository admMailSendErrorRepository;

    public AdmMailSendErrorResource(AdmMailSendErrorRepository admMailSendErrorRepository) {
        this.admMailSendErrorRepository = admMailSendErrorRepository;
    }

    /**
     * {@code POST  /adm-mail-send-errors} : Create a new admMailSendError.
     *
     * @param admMailSendError the admMailSendError to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new admMailSendError, or with status {@code 400 (Bad Request)} if the admMailSendError has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/adm-mail-send-errors")
    public ResponseEntity<AdmMailSendError> createAdmMailSendError(@Valid @RequestBody AdmMailSendError admMailSendError)
        throws URISyntaxException {
        log.debug("REST request to save AdmMailSendError : {}", admMailSendError);
        if (admMailSendError.getId() != null) {
            throw new BadRequestAlertException("A new admMailSendError cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AdmMailSendError result = admMailSendErrorRepository.save(admMailSendError);
        return ResponseEntity
            .created(new URI("/api/adm-mail-send-errors/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /adm-mail-send-errors/:id} : Updates an existing admMailSendError.
     *
     * @param id the id of the admMailSendError to save.
     * @param admMailSendError the admMailSendError to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated admMailSendError,
     * or with status {@code 400 (Bad Request)} if the admMailSendError is not valid,
     * or with status {@code 500 (Internal Server Error)} if the admMailSendError couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/adm-mail-send-errors/{id}")
    public ResponseEntity<AdmMailSendError> updateAdmMailSendError(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody AdmMailSendError admMailSendError
    ) throws URISyntaxException {
        log.debug("REST request to update AdmMailSendError : {}, {}", id, admMailSendError);
        if (admMailSendError.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, admMailSendError.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!admMailSendErrorRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        AdmMailSendError result = admMailSendErrorRepository.save(admMailSendError);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, admMailSendError.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /adm-mail-send-errors/:id} : Partial updates given fields of an existing admMailSendError, field will ignore if it is null
     *
     * @param id the id of the admMailSendError to save.
     * @param admMailSendError the admMailSendError to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated admMailSendError,
     * or with status {@code 400 (Bad Request)} if the admMailSendError is not valid,
     * or with status {@code 404 (Not Found)} if the admMailSendError is not found,
     * or with status {@code 500 (Internal Server Error)} if the admMailSendError couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/adm-mail-send-errors/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<AdmMailSendError> partialUpdateAdmMailSendError(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody AdmMailSendError admMailSendError
    ) throws URISyntaxException {
        log.debug("REST request to partial update AdmMailSendError partially : {}, {}", id, admMailSendError);
        if (admMailSendError.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, admMailSendError.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!admMailSendErrorRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<AdmMailSendError> result = admMailSendErrorRepository
            .findById(admMailSendError.getId())
            .map(
                existingAdmMailSendError -> {
                    if (admMailSendError.getRealSendTime() != null) {
                        existingAdmMailSendError.setRealSendTime(admMailSendError.getRealSendTime());
                    }
                    if (admMailSendError.getSender() != null) {
                        existingAdmMailSendError.setSender(admMailSendError.getSender());
                    }
                    if (admMailSendError.getReceiver() != null) {
                        existingAdmMailSendError.setReceiver(admMailSendError.getReceiver());
                    }
                    if (admMailSendError.getSubject() != null) {
                        existingAdmMailSendError.setSubject(admMailSendError.getSubject());
                    }
                    if (admMailSendError.getContent() != null) {
                        existingAdmMailSendError.setContent(admMailSendError.getContent());
                    }
                    if (admMailSendError.getErrorMessage() != null) {
                        existingAdmMailSendError.setErrorMessage(admMailSendError.getErrorMessage());
                    }
                    if (admMailSendError.getCreateUser() != null) {
                        existingAdmMailSendError.setCreateUser(admMailSendError.getCreateUser());
                    }
                    if (admMailSendError.getCreateTime() != null) {
                        existingAdmMailSendError.setCreateTime(admMailSendError.getCreateTime());
                    }
                    if (admMailSendError.getUpdateUser() != null) {
                        existingAdmMailSendError.setUpdateUser(admMailSendError.getUpdateUser());
                    }
                    if (admMailSendError.getUpdateTime() != null) {
                        existingAdmMailSendError.setUpdateTime(admMailSendError.getUpdateTime());
                    }

                    return existingAdmMailSendError;
                }
            )
            .map(admMailSendErrorRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, admMailSendError.getId().toString())
        );
    }

    /**
     * {@code GET  /adm-mail-send-errors} : get all the admMailSendErrors.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of admMailSendErrors in body.
     */
    @GetMapping("/adm-mail-send-errors")
    public List<AdmMailSendError> getAllAdmMailSendErrors() {
        log.debug("REST request to get all AdmMailSendErrors");
        return admMailSendErrorRepository.findAll();
    }

    /**
     * {@code GET  /adm-mail-send-errors/:id} : get the "id" admMailSendError.
     *
     * @param id the id of the admMailSendError to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the admMailSendError, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/adm-mail-send-errors/{id}")
    public ResponseEntity<AdmMailSendError> getAdmMailSendError(@PathVariable Long id) {
        log.debug("REST request to get AdmMailSendError : {}", id);
        Optional<AdmMailSendError> admMailSendError = admMailSendErrorRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(admMailSendError);
    }

    /**
     * {@code DELETE  /adm-mail-send-errors/:id} : delete the "id" admMailSendError.
     *
     * @param id the id of the admMailSendError to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/adm-mail-send-errors/{id}")
    public ResponseEntity<Void> deleteAdmMailSendError(@PathVariable Long id) {
        log.debug("REST request to delete AdmMailSendError : {}", id);
        admMailSendErrorRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
