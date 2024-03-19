package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.repository.AdmContactRepository;
import gov.pcc.pwc.service.AdmContactService;
import gov.pcc.pwc.service.dto.AdmAccountContactDTO;
import gov.pcc.pwc.service.dto.AdmCompanyApplyDTO;
import gov.pcc.pwc.service.dto.AdmContactDTO;
import gov.pcc.pwc.common.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
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
 * REST controller for managing {@link gov.pcc.pwc.domain.AdmContact}.
 */
@RestController
@RequestMapping("/api")
public class AdmContactResource {

    private final Logger log = LoggerFactory.getLogger(AdmContactResource.class);

    private static final String ENTITY_NAME = "admContact";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AdmContactService admContactService;

    private final AdmContactRepository admContactRepository;

    public AdmContactResource(AdmContactService admContactService, AdmContactRepository admContactRepository) {
        this.admContactService = admContactService;
        this.admContactRepository = admContactRepository;
    }

    /**
     * {@code POST  /adm-contacts} : Create a new admContact.
     *
     * @param admContactDTO the admContactDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new admContactDTO, or with status {@code 400 (Bad Request)} if the admContact has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/adm-contacts")
    public ResponseEntity<AdmContactDTO> createAdmContact(@Valid @RequestBody AdmContactDTO admContactDTO) throws URISyntaxException {
        log.debug("REST request to save AdmContact : {}", admContactDTO);
        if (admContactDTO.getUserId() != null) {
            throw new BadRequestAlertException("A new admContact cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AdmContactDTO result = admContactService.save(admContactDTO);
        return ResponseEntity
            .created(new URI("/api/adm-contacts/" + result.getUserId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getUserId()))
            .body(result);
    }

    /**
     * {@code PUT  /adm-contacts/:userId} : Updates an existing admContact.
     *
     * @param userId the id of the admContactDTO to save.
     * @param admContactDTO the admContactDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated admContactDTO,
     * or with status {@code 400 (Bad Request)} if the admContactDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the admContactDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/adm-contacts/{userId}")
    public ResponseEntity<AdmContactDTO> updateAdmContact(
        @PathVariable(value = "userId", required = false) final String userId,
        @Valid @RequestBody AdmContactDTO admContactDTO
    ) throws URISyntaxException {
        log.debug("REST request to update AdmContact : {}, {}", userId, admContactDTO);
        if (admContactDTO.getUserId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(userId, admContactDTO.getUserId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!admContactRepository.existsById(userId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        AdmContactDTO result = admContactService.save(admContactDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, admContactDTO.getUserId()))
            .body(result);
    }

    /**
     * {@code PATCH  /adm-contacts/:userId} : Partial updates given fields of an existing admContact, field will ignore if it is null
     *
     * @param userId the id of the admContactDTO to save.
     * @param admContactDTO the admContactDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated admContactDTO,
     * or with status {@code 400 (Bad Request)} if the admContactDTO is not valid,
     * or with status {@code 404 (Not Found)} if the admContactDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the admContactDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/adm-contacts/{userId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<AdmContactDTO> partialUpdateAdmContact(
        @PathVariable(value = "userId", required = false) final String userId,
        @NotNull @RequestBody AdmContactDTO admContactDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update AdmContact partially : {}, {}", userId, admContactDTO);
        if (admContactDTO.getUserId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(userId, admContactDTO.getUserId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!admContactRepository.existsById(userId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<AdmContactDTO> result = admContactService.partialUpdate(admContactDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, admContactDTO.getUserId())
        );
    }

    /**
     * {@code GET  /adm-contacts} : get all the admContacts.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of admContacts in body.
     */
    @GetMapping("/adm-contacts")
    public List<AdmContactDTO> getAllAdmContacts() {
        log.debug("REST request to get all AdmContacts");
        return admContactService.findAll();
    }

    /**
     * {@code GET  /adm-contacts/:id} : get the "id" admContact.
     *
     * @param id the id of the admContactDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the admContactDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/adm-contacts/{id}")
    public ResponseEntity<AdmContactDTO> getAdmContact(@PathVariable String id) {
        log.debug("REST request to get AdmContact : {}", id);
        Optional<AdmContactDTO> admContactDTO = admContactService.findOne(id);
        return ResponseUtil.wrapOrNotFound(admContactDTO);
    }

    /**
     * {@code DELETE  /adm-contacts/:id} : delete the "id" admContact.
     *
     * @param id the id of the admContactDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/adm-contacts/{id}")
    public ResponseEntity<Void> deleteAdmContact(@PathVariable String id) {
        log.debug("REST request to delete AdmContact : {}", id);
        admContactService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }

    /**
     * {@code POST  /adm-account-contact/save } : Create or Save a admAccountContactDTO.
     *
     * @param admAccountContactDTO the AdmAccountContactDTO to create or save.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new admAccountContactDTO, or with status {@code 400 (Bad Request)} if the AdmAccount has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/adm-account-contact/createAndSave")
    public ResponseEntity<AdmAccountContactDTO> saveAdmAccountContact(@Valid @RequestBody AdmAccountContactDTO admAccountContactDTO, HttpServletRequest request)
        throws URISyntaxException {
        log.debug("REST request to create and save AdmAccountContactDTO : {}", admAccountContactDTO);
        admContactService.saveAdmAccountContact(admAccountContactDTO, request);
        return ResponseEntity
            .created(new URI("/api/adm-account-contact/save" + admAccountContactDTO.getUserId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, admAccountContactDTO.getUserId()))
            .body(admAccountContactDTO);
    }

}
