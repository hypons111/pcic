package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.domain.AdmMailSend;
import gov.pcc.pwc.repository.AdmMailSendRepository;
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
 * REST controller for managing {@link AdmMailSend}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class AdmMailSendResource {

    private final Logger log = LoggerFactory.getLogger(AdmMailSendResource.class);

    private static final String ENTITY_NAME = "admMailSend";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AdmMailSendRepository admMailSendRepository;

    public AdmMailSendResource(AdmMailSendRepository admMailSendRepository) {
        this.admMailSendRepository = admMailSendRepository;
    }

    /**
     * {@code POST  /adm-mail-sends} : Create a new admMailSend.
     *
     * @param admMailSend the admMailSend to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new admMailSend, or with status {@code 400 (Bad Request)} if the admMailSend has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/adm-mail-sends")
    public ResponseEntity<AdmMailSend> createAdmMailSend(@Valid @RequestBody AdmMailSend admMailSend) throws URISyntaxException {
        log.debug("REST request to save AdmMailSend : {}", admMailSend);
        if (admMailSend.getId() != null) {
            throw new BadRequestAlertException("A new admMailSend cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AdmMailSend result = admMailSendRepository.save(admMailSend);
        return ResponseEntity
            .created(new URI("/api/adm-mail-sends/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /adm-mail-sends/:id} : Updates an existing admMailSend.
     *
     * @param id the id of the admMailSend to save.
     * @param admMailSend the admMailSend to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated admMailSend,
     * or with status {@code 400 (Bad Request)} if the admMailSend is not valid,
     * or with status {@code 500 (Internal Server Error)} if the admMailSend couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/adm-mail-sends/{id}")
    public ResponseEntity<AdmMailSend> updateAdmMailSend(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody AdmMailSend admMailSend
    ) throws URISyntaxException {
        log.debug("REST request to update AdmMailSend : {}, {}", id, admMailSend);
        if (admMailSend.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, admMailSend.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!admMailSendRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        AdmMailSend result = admMailSendRepository.save(admMailSend);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, admMailSend.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /adm-mail-sends/:id} : Partial updates given fields of an existing admMailSend, field will ignore if it is null
     *
     * @param id the id of the admMailSend to save.
     * @param admMailSend the admMailSend to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated admMailSend,
     * or with status {@code 400 (Bad Request)} if the admMailSend is not valid,
     * or with status {@code 404 (Not Found)} if the admMailSend is not found,
     * or with status {@code 500 (Internal Server Error)} if the admMailSend couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/adm-mail-sends/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<AdmMailSend> partialUpdateAdmMailSend(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody AdmMailSend admMailSend
    ) throws URISyntaxException {
        log.debug("REST request to partial update AdmMailSend partially : {}, {}", id, admMailSend);
        if (admMailSend.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, admMailSend.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!admMailSendRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<AdmMailSend> result = admMailSendRepository
            .findById(admMailSend.getId())
            .map(
                existingAdmMailSend -> {
                    if (admMailSend.getMailType() != null) {
                        existingAdmMailSend.setMailType(admMailSend.getMailType());
                    }
                    if (admMailSend.getSourceId() != null) {
                        existingAdmMailSend.setSourceId(admMailSend.getSourceId());
                    }
                    if (admMailSend.getSender() != null) {
                        existingAdmMailSend.setSender(admMailSend.getSender());
                    }
                    if (admMailSend.getReceiver() != null) {
                        existingAdmMailSend.setReceiver(admMailSend.getReceiver());
                    }
                    if (admMailSend.getSubject() != null) {
                        existingAdmMailSend.setSubject(admMailSend.getSubject());
                    }
                    if (admMailSend.getContent() != null) {
                        existingAdmMailSend.setContent(admMailSend.getContent());
                    }
                    if (admMailSend.getExpectSendTime() != null) {
                        existingAdmMailSend.setExpectSendTime(admMailSend.getExpectSendTime());
                    }
                    if (admMailSend.getRealSendTime() != null) {
                        existingAdmMailSend.setRealSendTime(admMailSend.getRealSendTime());
                    }
                    if (admMailSend.getStatus() != null) {
                        existingAdmMailSend.setStatus(admMailSend.getStatus());
                    }
                    if (admMailSend.getProcessMessage() != null) {
                        existingAdmMailSend.setProcessMessage(admMailSend.getProcessMessage());
                    }
                    if (admMailSend.getCreateUser() != null) {
                        existingAdmMailSend.setCreateUser(admMailSend.getCreateUser());
                    }
                    if (admMailSend.getCreateTime() != null) {
                        existingAdmMailSend.setCreateTime(admMailSend.getCreateTime());
                    }
                    if (admMailSend.getUpdateUser() != null) {
                        existingAdmMailSend.setUpdateUser(admMailSend.getUpdateUser());
                    }
                    if (admMailSend.getUpdateTime() != null) {
                        existingAdmMailSend.setUpdateTime(admMailSend.getUpdateTime());
                    }

                    return existingAdmMailSend;
                }
            )
            .map(admMailSendRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, admMailSend.getId().toString())
        );
    }

    /**
     * {@code GET  /adm-mail-sends} : get all the admMailSends.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of admMailSends in body.
     */
    @GetMapping("/adm-mail-sends")
    public List<AdmMailSend> getAllAdmMailSends() {
        log.debug("REST request to get all AdmMailSends");
        return admMailSendRepository.findAll();
    }

    /**
     * {@code GET  /adm-mail-sends/:id} : get the "id" admMailSend.
     *
     * @param id the id of the admMailSend to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the admMailSend, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/adm-mail-sends/{id}")
    public ResponseEntity<AdmMailSend> getAdmMailSend(@PathVariable Long id) {
        log.debug("REST request to get AdmMailSend : {}", id);
        Optional<AdmMailSend> admMailSend = admMailSendRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(admMailSend);
    }

    /**
     * {@code DELETE  /adm-mail-sends/:id} : delete the "id" admMailSend.
     *
     * @param id the id of the admMailSend to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/adm-mail-sends/{id}")
    public ResponseEntity<Void> deleteAdmMailSend(@PathVariable Long id) {
        log.debug("REST request to delete AdmMailSend : {}", id);
        admMailSendRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
