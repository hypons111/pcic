package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.domain.AdmSmsSendError;
import gov.pcc.pwc.repository.AdmSmsSendErrorRepository;
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
 * REST controller for managing {@link AdmSmsSendError}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class AdmSmsSendErrorResource {

    private final Logger log = LoggerFactory.getLogger(AdmSmsSendErrorResource.class);

    private static final String ENTITY_NAME = "admSmsSendError";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AdmSmsSendErrorRepository admSmsSendErrorRepository;

    public AdmSmsSendErrorResource(AdmSmsSendErrorRepository admSmsSendErrorRepository) {
        this.admSmsSendErrorRepository = admSmsSendErrorRepository;
    }

    /**
     * {@code POST  /adm-sms-send-errors} : Create a new admSmsSendError.
     *
     * @param admSmsSendError the admSmsSendError to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new admSmsSendError, or with status {@code 400 (Bad Request)} if the admSmsSendError has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/adm-sms-send-errors")
    public ResponseEntity<AdmSmsSendError> createAdmSmsSendError(@Valid @RequestBody AdmSmsSendError admSmsSendError)
        throws URISyntaxException {
        log.debug("REST request to save AdmSmsSendError : {}", admSmsSendError);
        if (admSmsSendError.getId() != null) {
            throw new BadRequestAlertException("A new admSmsSendError cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AdmSmsSendError result = admSmsSendErrorRepository.save(admSmsSendError);
        return ResponseEntity
            .created(new URI("/api/adm-sms-send-errors/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /adm-sms-send-errors/:id} : Updates an existing admSmsSendError.
     *
     * @param id the id of the admSmsSendError to save.
     * @param admSmsSendError the admSmsSendError to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated admSmsSendError,
     * or with status {@code 400 (Bad Request)} if the admSmsSendError is not valid,
     * or with status {@code 500 (Internal Server Error)} if the admSmsSendError couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/adm-sms-send-errors/{id}")
    public ResponseEntity<AdmSmsSendError> updateAdmSmsSendError(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody AdmSmsSendError admSmsSendError
    ) throws URISyntaxException {
        log.debug("REST request to update AdmSmsSendError : {}, {}", id, admSmsSendError);
        if (admSmsSendError.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, admSmsSendError.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!admSmsSendErrorRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        AdmSmsSendError result = admSmsSendErrorRepository.save(admSmsSendError);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, admSmsSendError.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /adm-sms-send-errors/:id} : Partial updates given fields of an existing admSmsSendError, field will ignore if it is null
     *
     * @param id the id of the admSmsSendError to save.
     * @param admSmsSendError the admSmsSendError to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated admSmsSendError,
     * or with status {@code 400 (Bad Request)} if the admSmsSendError is not valid,
     * or with status {@code 404 (Not Found)} if the admSmsSendError is not found,
     * or with status {@code 500 (Internal Server Error)} if the admSmsSendError couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/adm-sms-send-errors/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<AdmSmsSendError> partialUpdateAdmSmsSendError(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody AdmSmsSendError admSmsSendError
    ) throws URISyntaxException {
        log.debug("REST request to partial update AdmSmsSendError partially : {}, {}", id, admSmsSendError);
        if (admSmsSendError.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, admSmsSendError.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!admSmsSendErrorRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<AdmSmsSendError> result = admSmsSendErrorRepository
            .findById(admSmsSendError.getId())
            .map(
                existingAdmSmsSendError -> {
                    if (admSmsSendError.getRealSendTime() != null) {
                        existingAdmSmsSendError.setRealSendTime(admSmsSendError.getRealSendTime());
                    }
                    if (admSmsSendError.getSender() != null) {
                        existingAdmSmsSendError.setSender(admSmsSendError.getSender());
                    }
                    if (admSmsSendError.getReceiver() != null) {
                        existingAdmSmsSendError.setReceiver(admSmsSendError.getReceiver());
                    }
                    if (admSmsSendError.getMessage() != null) {
                        existingAdmSmsSendError.setMessage(admSmsSendError.getMessage());
                    }
                    if (admSmsSendError.getErrorMessage() != null) {
                        existingAdmSmsSendError.setErrorMessage(admSmsSendError.getErrorMessage());
                    }
                    if (admSmsSendError.getCreateUser() != null) {
                        existingAdmSmsSendError.setCreateUser(admSmsSendError.getCreateUser());
                    }
                    if (admSmsSendError.getCreateTime() != null) {
                        existingAdmSmsSendError.setCreateTime(admSmsSendError.getCreateTime());
                    }
                    if (admSmsSendError.getUpdateUser() != null) {
                        existingAdmSmsSendError.setUpdateUser(admSmsSendError.getUpdateUser());
                    }
                    if (admSmsSendError.getUpdateTime() != null) {
                        existingAdmSmsSendError.setUpdateTime(admSmsSendError.getUpdateTime());
                    }

                    return existingAdmSmsSendError;
                }
            )
            .map(admSmsSendErrorRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, admSmsSendError.getId().toString())
        );
    }

    /**
     * {@code GET  /adm-sms-send-errors} : get all the admSmsSendErrors.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of admSmsSendErrors in body.
     */
    @GetMapping("/adm-sms-send-errors")
    public List<AdmSmsSendError> getAllAdmSmsSendErrors() {
        log.debug("REST request to get all AdmSmsSendErrors");
        return admSmsSendErrorRepository.findAll();
    }

    /**
     * {@code GET  /adm-sms-send-errors/:id} : get the "id" admSmsSendError.
     *
     * @param id the id of the admSmsSendError to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the admSmsSendError, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/adm-sms-send-errors/{id}")
    public ResponseEntity<AdmSmsSendError> getAdmSmsSendError(@PathVariable Long id) {
        log.debug("REST request to get AdmSmsSendError : {}", id);
        Optional<AdmSmsSendError> admSmsSendError = admSmsSendErrorRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(admSmsSendError);
    }

    /**
     * {@code DELETE  /adm-sms-send-errors/:id} : delete the "id" admSmsSendError.
     *
     * @param id the id of the admSmsSendError to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/adm-sms-send-errors/{id}")
    public ResponseEntity<Void> deleteAdmSmsSendError(@PathVariable Long id) {
        log.debug("REST request to delete AdmSmsSendError : {}", id);
        admSmsSendErrorRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
