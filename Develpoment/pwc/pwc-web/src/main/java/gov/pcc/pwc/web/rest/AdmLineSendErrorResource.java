package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.domain.AdmLineSendError;
import gov.pcc.pwc.repository.AdmLineSendErrorRepository;
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
 * REST controller for managing {@link AdmLineSendError}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class AdmLineSendErrorResource {

    private final Logger log = LoggerFactory.getLogger(AdmLineSendErrorResource.class);

    private static final String ENTITY_NAME = "admLineSendError";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AdmLineSendErrorRepository admLineSendErrorRepository;

    public AdmLineSendErrorResource(AdmLineSendErrorRepository admLineSendErrorRepository) {
        this.admLineSendErrorRepository = admLineSendErrorRepository;
    }

    /**
     * {@code POST  /adm-line-send-errors} : Create a new admLineSendError.
     *
     * @param admLineSendError the admLineSendError to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new admLineSendError, or with status {@code 400 (Bad Request)} if the admLineSendError has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/adm-line-send-errors")
    public ResponseEntity<AdmLineSendError> createAdmLineSendError(@Valid @RequestBody AdmLineSendError admLineSendError)
        throws URISyntaxException {
        log.debug("REST request to save AdmLineSendError : {}", admLineSendError);
        if (admLineSendError.getId() != null) {
            throw new BadRequestAlertException("A new admLineSendError cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AdmLineSendError result = admLineSendErrorRepository.save(admLineSendError);
        return ResponseEntity
            .created(new URI("/api/adm-line-send-errors/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /adm-line-send-errors/:id} : Updates an existing admLineSendError.
     *
     * @param id the id of the admLineSendError to save.
     * @param admLineSendError the admLineSendError to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated admLineSendError,
     * or with status {@code 400 (Bad Request)} if the admLineSendError is not valid,
     * or with status {@code 500 (Internal Server Error)} if the admLineSendError couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/adm-line-send-errors/{id}")
    public ResponseEntity<AdmLineSendError> updateAdmLineSendError(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody AdmLineSendError admLineSendError
    ) throws URISyntaxException {
        log.debug("REST request to update AdmLineSendError : {}, {}", id, admLineSendError);
        if (admLineSendError.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, admLineSendError.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!admLineSendErrorRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        AdmLineSendError result = admLineSendErrorRepository.save(admLineSendError);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, admLineSendError.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /adm-line-send-errors/:id} : Partial updates given fields of an existing admLineSendError, field will ignore if it is null
     *
     * @param id the id of the admLineSendError to save.
     * @param admLineSendError the admLineSendError to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated admLineSendError,
     * or with status {@code 400 (Bad Request)} if the admLineSendError is not valid,
     * or with status {@code 404 (Not Found)} if the admLineSendError is not found,
     * or with status {@code 500 (Internal Server Error)} if the admLineSendError couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/adm-line-send-errors/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<AdmLineSendError> partialUpdateAdmLineSendError(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody AdmLineSendError admLineSendError
    ) throws URISyntaxException {
        log.debug("REST request to partial update AdmLineSendError partially : {}, {}", id, admLineSendError);
        if (admLineSendError.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, admLineSendError.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!admLineSendErrorRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<AdmLineSendError> result = admLineSendErrorRepository
            .findById(admLineSendError.getId())
            .map(
                existingAdmLineSendError -> {
                    if (admLineSendError.getRealSendTime() != null) {
                        existingAdmLineSendError.setRealSendTime(admLineSendError.getRealSendTime());
                    }
                    if (admLineSendError.getSender() != null) {
                        existingAdmLineSendError.setSender(admLineSendError.getSender());
                    }
                    if (admLineSendError.getReceiver() != null) {
                        existingAdmLineSendError.setReceiver(admLineSendError.getReceiver());
                    }
                    if (admLineSendError.getMessage() != null) {
                        existingAdmLineSendError.setMessage(admLineSendError.getMessage());
                    }
                    if (admLineSendError.getErrorMessage() != null) {
                        existingAdmLineSendError.setErrorMessage(admLineSendError.getErrorMessage());
                    }
                    if (admLineSendError.getCreateUser() != null) {
                        existingAdmLineSendError.setCreateUser(admLineSendError.getCreateUser());
                    }
                    if (admLineSendError.getCreateTime() != null) {
                        existingAdmLineSendError.setCreateTime(admLineSendError.getCreateTime());
                    }
                    if (admLineSendError.getUpdateUser() != null) {
                        existingAdmLineSendError.setUpdateUser(admLineSendError.getUpdateUser());
                    }
                    if (admLineSendError.getUpdateTime() != null) {
                        existingAdmLineSendError.setUpdateTime(admLineSendError.getUpdateTime());
                    }

                    return existingAdmLineSendError;
                }
            )
            .map(admLineSendErrorRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, admLineSendError.getId().toString())
        );
    }

    /**
     * {@code GET  /adm-line-send-errors} : get all the admLineSendErrors.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of admLineSendErrors in body.
     */
    @GetMapping("/adm-line-send-errors")
    public List<AdmLineSendError> getAllAdmLineSendErrors() {
        log.debug("REST request to get all AdmLineSendErrors");
        return admLineSendErrorRepository.findAll();
    }

    /**
     * {@code GET  /adm-line-send-errors/:id} : get the "id" admLineSendError.
     *
     * @param id the id of the admLineSendError to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the admLineSendError, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/adm-line-send-errors/{id}")
    public ResponseEntity<AdmLineSendError> getAdmLineSendError(@PathVariable Long id) {
        log.debug("REST request to get AdmLineSendError : {}", id);
        Optional<AdmLineSendError> admLineSendError = admLineSendErrorRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(admLineSendError);
    }

    /**
     * {@code DELETE  /adm-line-send-errors/:id} : delete the "id" admLineSendError.
     *
     * @param id the id of the admLineSendError to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/adm-line-send-errors/{id}")
    public ResponseEntity<Void> deleteAdmLineSendError(@PathVariable Long id) {
        log.debug("REST request to delete AdmLineSendError : {}", id);
        admLineSendErrorRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
