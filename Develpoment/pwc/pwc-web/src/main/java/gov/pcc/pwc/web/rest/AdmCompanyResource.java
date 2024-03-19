package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.repository.AdmCompanyRepository;
import gov.pcc.pwc.service.AdmAccountService;
import gov.pcc.pwc.service.AdmCompanyService;
import gov.pcc.pwc.service.dto.AdmCompanyApplyDTO;
import gov.pcc.pwc.service.dto.AdmCompanyDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * REST controller for managing {@link gov.pcc.pwc.domain.AdmCompany}.
 */
@RestController
@RequestMapping("/api")
public class AdmCompanyResource {

    private final Logger log = LoggerFactory.getLogger(AdmCompanyResource.class);

    private static final String ENTITY_NAME = "admCompany";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AdmCompanyService admCompanyService;

    private final AdmCompanyRepository admCompanyRepository;

    private final AdmAccountService admAccountService;

    public AdmCompanyResource(AdmCompanyService admCompanyService, AdmCompanyRepository admCompanyRepository, AdmAccountService admAccountService) {
        this.admCompanyService = admCompanyService;
        this.admCompanyRepository = admCompanyRepository;
        this.admAccountService = admAccountService;
    }

    /**
     * {@code POST  /adm-companies} : Create a new admCompany.
     *
     * @param admCompanyDTO the admCompanyDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new admCompanyDTO, or with status {@code 400 (Bad Request)} if the admCompany has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/adm-companies")
    public ResponseEntity<AdmCompanyDTO> createAdmCompany(@Valid @RequestBody AdmCompanyDTO admCompanyDTO) throws URISyntaxException {
        log.debug("REST request to save AdmCompany : {}", admCompanyDTO);
        if (admCompanyDTO.getCompIdno() != null) {
            throw new BadRequestAlertException("A new admCompany cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AdmCompanyDTO result = admCompanyService.save(admCompanyDTO);
        return ResponseEntity
            .created(new URI("/api/adm-companies/" + result.getCompIdno()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getCompIdno()))
            .body(result);
    }

    /**
     * {@code PUT  /adm-companies/:ban} : Updates an existing admCompany.
     *
     * @param ban the id of the admCompanyDTO to save.
     * @param admCompanyDTO the admCompanyDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated admCompanyDTO,
     * or with status {@code 400 (Bad Request)} if the admCompanyDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the admCompanyDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/adm-companies/{ban}")
    public ResponseEntity<AdmCompanyDTO> updateAdmCompany(
        @PathVariable(value = "ban", required = false) final String ban,
        @Valid @RequestBody AdmCompanyDTO admCompanyDTO
    ) throws URISyntaxException {
        log.debug("REST request to update AdmCompany : {}, {}", ban, admCompanyDTO);
        if (admCompanyDTO.getCompIdno() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(ban, admCompanyDTO.getCompIdno())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!admCompanyRepository.existsById(ban)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        AdmCompanyDTO result = admCompanyService.save(admCompanyDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, admCompanyDTO.getCompIdno()))
            .body(result);
    }

    /**
     * {@code PATCH  /adm-companies/:ban} : Partial updates given fields of an existing admCompany, field will ignore if it is null
     *
     * @param ban the id of the admCompanyDTO to save.
     * @param admCompanyDTO the admCompanyDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated admCompanyDTO,
     * or with status {@code 400 (Bad Request)} if the admCompanyDTO is not valid,
     * or with status {@code 404 (Not Found)} if the admCompanyDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the admCompanyDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/adm-companies/{ban}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<AdmCompanyDTO> partialUpdateAdmCompany(
        @PathVariable(value = "ban", required = false) final String ban,
        @NotNull @RequestBody AdmCompanyDTO admCompanyDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update AdmCompany partially : {}, {}", ban, admCompanyDTO);
        if (admCompanyDTO.getCompIdno() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(ban, admCompanyDTO.getCompIdno())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!admCompanyRepository.existsById(ban)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<AdmCompanyDTO> result = admCompanyService.partialUpdate(admCompanyDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, admCompanyDTO.getCompIdno())
        );
    }

    /**
     * {@code GET  /adm-companies} : get all the admCompanies.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of admCompanies in body.
     */
    @GetMapping("/adm-companies")
    public List<AdmCompanyDTO> getAllAdmCompanies() {
        log.debug("REST request to get all AdmCompanies");
        return admCompanyService.findAll();

    }

    /**
     * {@code POST  /adm-companies} : get all the admCompanies.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of admCompanies in body.
     */
    @PostMapping("/adm-companies-unit")
    public List<AdmCompanyDTO> getAllAdmCompaniesForUnit(@RequestBody Map<String, String> keyWord) {
        log.debug("REST request to get all AdmCompanies");
        List<AdmCompanyDTO> admCompany = admCompanyService.getAllAdmCompaniesForUnit(keyWord);
        return admCompany;
    }

    /**
     * {@code GET  /adm-companies/:id} : get the "id" admCompany.
     *
     * @param id the id of the admCompanyDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the admCompanyDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/adm-companies/{id}")
    public ResponseEntity<AdmCompanyDTO> getAdmCompany(@PathVariable String id) {
        log.debug("REST request to get AdmCompany : {}", id);
        Optional<AdmCompanyDTO> admCompanyDTO = admCompanyService.findOne(id);
        return ResponseUtil.wrapOrNotFound(admCompanyDTO);
    }

    /**
     * {@code DELETE  /adm-companies/:id} : delete the "id" admCompany.
     *
     * @param id the id of the admCompanyDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/adm-companies/{id}")
    public ResponseEntity<Void> deleteAdmCompany(@PathVariable String id) {
        log.debug("REST request to delete AdmCompany : {}", id);
        admCompanyService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }

    /**
     * {@code GET  /service/get-adm-company-info/{compIdno} : get the admCompanyApplyDTO
     *
     * @param admCompanyApplyDTO the id of the AdmCompanyApplyDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the AdmCompanyApplyDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/service/get-adm-company-info/{compIdno}")
    public AdmCompanyApplyDTO getCompanyInfo(@PathVariable String compIdno) {
        log.debug("REST request to get AdmCompanyApplyDTO : {}", compIdno);
        return admCompanyService.findOneCompany(compIdno);
    }

    /**
     * {@code POST  /service/adm-company-contact/save} : Create a new AdmAccount.
     *
     * @param admCompanyApplyDTO the AdmCompanyApplyDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new admCompanyApplyDTO, or with status {@code 400 (Bad Request)} if the AdmAccount has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/service/adm-company-contact/save")
    public ResponseEntity<AdmCompanyApplyDTO> saveAdmAccountContact(@Valid @RequestBody AdmCompanyApplyDTO admCompanyApplyDTO, HttpServletRequest request)
        throws URISyntaxException {
        log.debug("REST request to save AdmCompanyApplyDTO : {}", admCompanyApplyDTO);
        AdmCompanyApplyDTO result = admAccountService.saveCompanyInfo(admCompanyApplyDTO, request);
        return ResponseEntity
            .created(new URI("/api/service/adm-company-contact/save/" + result.getUserId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getUserId()))
            .body(result);
    }
}
