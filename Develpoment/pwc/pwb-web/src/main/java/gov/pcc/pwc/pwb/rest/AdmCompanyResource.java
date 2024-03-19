package gov.pcc.pwc.pwb.rest;

import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.domain.AdmCompany;
import gov.pcc.pwc.repository.AdmCompanyRepository;
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
 * REST controller for managing {@link AdmCompany}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class AdmCompanyResource {

    private final Logger log = LoggerFactory.getLogger(AdmCompanyResource.class);

    private static final String ENTITY_NAME = "admCompany";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AdmCompanyRepository admCompanyRepository;

    public AdmCompanyResource(AdmCompanyRepository admCompanyRepository) {
        this.admCompanyRepository = admCompanyRepository;
    }

    /**
     * {@code POST  /adm-companies} : Create a new admCompany.
     *
     * @param admCompany the admCompany to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new admCompany, or with status {@code 400 (Bad Request)} if the admCompany has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/adm-companies")
    public ResponseEntity<AdmCompany> createAdmCompany(@Valid @RequestBody AdmCompany admCompany) throws URISyntaxException {
        log.debug("REST request to save AdmCompany : {}", admCompany);
        if (admCompany.getCompIdno() != null) {
            throw new BadRequestAlertException("A new admCompany cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AdmCompany result = admCompanyRepository.save(admCompany);
        return ResponseEntity
            .created(new URI("/api/adm-companies/" + result.getCompIdno()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getCompIdno()))
            .body(result);
    }

    /**
     * {@code PUT  /adm-companies/:compIdno} : Updates an existing admCompany.
     *
     * @param compIdno the id of the admCompany to save.
     * @param admCompany the admCompany to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated admCompany,
     * or with status {@code 400 (Bad Request)} if the admCompany is not valid,
     * or with status {@code 500 (Internal Server Error)} if the admCompany couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/adm-companies/{compIdno}")
    public ResponseEntity<AdmCompany> updateAdmCompany(
        @PathVariable(value = "compIdno", required = false) final String compIdno,
        @Valid @RequestBody AdmCompany admCompany
    ) throws URISyntaxException {
        log.debug("REST request to update AdmCompany : {}, {}", compIdno, admCompany);
        if (admCompany.getCompIdno() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(compIdno, admCompany.getCompIdno())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!admCompanyRepository.existsById(compIdno)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        AdmCompany result = admCompanyRepository.save(admCompany);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, admCompany.getCompIdno()))
            .body(result);
    }

    /**
     * {@code PATCH  /adm-companies/:compIdno} : Partial updates given fields of an existing admCompany, field will ignore if it is null
     *
     * @param compIdno the id of the admCompany to save.
     * @param admCompany the admCompany to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated admCompany,
     * or with status {@code 400 (Bad Request)} if the admCompany is not valid,
     * or with status {@code 404 (Not Found)} if the admCompany is not found,
     * or with status {@code 500 (Internal Server Error)} if the admCompany couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/adm-companies/{compIdno}", consumes = "application/merge-patch+json")
    public ResponseEntity<AdmCompany> partialUpdateAdmCompany(
        @PathVariable(value = "compIdno", required = false) final String compIdno,
        @NotNull @RequestBody AdmCompany admCompany
    ) throws URISyntaxException {
        log.debug("REST request to partial update AdmCompany partially : {}, {}", compIdno, admCompany);
        if (admCompany.getCompIdno() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(compIdno, admCompany.getCompIdno())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!admCompanyRepository.existsById(compIdno)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<AdmCompany> result = admCompanyRepository
            .findById(admCompany.getCompIdno())
            .map(
                existingAdmCompany -> {
                    if (admCompany.getName() != null) {
                        existingAdmCompany.setName(admCompany.getName());
                    }
                    if (admCompany.getAddressCity() != null) {
                        existingAdmCompany.setAddressCity(admCompany.getAddressCity());
                    }
                    if (admCompany.getAddressTown() != null) {
                        existingAdmCompany.setAddressTown(admCompany.getAddressTown());
                    }
                    if (admCompany.getAddressVillage() != null) {
                        existingAdmCompany.setAddressVillage(admCompany.getAddressVillage());
                    }
                    if (admCompany.getAddressLin() != null) {
                        existingAdmCompany.setAddressLin(admCompany.getAddressLin());
                    }
                    if (admCompany.getAddressRoad() != null) {
                        existingAdmCompany.setAddressRoad(admCompany.getAddressRoad());
                    }
                    if (admCompany.getAmount() != null) {
                        existingAdmCompany.setAmount(admCompany.getAmount());
                    }
                    if (admCompany.getConDate() != null) {
                        existingAdmCompany.setConDate(admCompany.getConDate());
                    }
                    if (admCompany.getUpdateUser() != null) {
                        existingAdmCompany.setUpdateUser(admCompany.getUpdateUser());
                    }
                    if (admCompany.getUpdateTime() != null) {
                        existingAdmCompany.setUpdateTime(admCompany.getUpdateTime());
                    }

                    return existingAdmCompany;
                }
            )
            .map(admCompanyRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, admCompany.getCompIdno())
        );
    }

    /**
     * {@code GET  /adm-companies} : get all the admCompanies.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of admCompanies in body.
     */
    @GetMapping("/adm-companies")
    public List<AdmCompany> getAllAdmCompanies() {
        log.debug("REST request to get all AdmCompanies");
        return admCompanyRepository.findAll();
    }

    /**
     * {@code GET  /adm-companies/:id} : get the "id" admCompany.
     *
     * @param id the id of the admCompany to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the admCompany, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/adm-companies/{id}")
    public ResponseEntity<AdmCompany> getAdmCompany(@PathVariable String id) {
        log.debug("REST request to get AdmCompany : {}", id);
        Optional<AdmCompany> admCompany = admCompanyRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(admCompany);
    }

    /**
     * {@code DELETE  /adm-companies/:id} : delete the "id" admCompany.
     *
     * @param id the id of the admCompany to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/adm-companies/{id}")
    public ResponseEntity<Void> deleteAdmCompany(@PathVariable String id) {
        log.debug("REST request to delete AdmCompany : {}", id);
        admCompanyRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }


}
