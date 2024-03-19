package gov.pcc.pwc.pwb.rest;

import gov.pcc.pwc.MessageCodes;
import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.domain.AdmHoliday;
import gov.pcc.pwc.repository.AdmHolidayRepository;
import gov.pcc.pwc.service.AdmHolidayService;
import gov.pcc.pwc.service.criteria.AdmHolidayCriteria;
import gov.pcc.pwc.service.dto.AdmHolidayDTO;
import gov.pcc.pwc.utils.PwcHeadUtil;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link AdmHoliday}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class AdmHolidayResource {

    private final Logger log = LoggerFactory.getLogger(AdmHolidayResource.class);

    private static final String ENTITY_NAME = "admHoliday";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AdmHolidayService admHolidayService;
    private final AdmHolidayRepository admHolidayRepository;

    public AdmHolidayResource(AdmHolidayService admHolidayService, AdmHolidayRepository admHolidayRepository) {
        this.admHolidayService = admHolidayService;
        this.admHolidayRepository = admHolidayRepository;
    }

    /**
     * {@code POST  /adm-holidays} : Create a new admHoliday.
     *
     * @param admHolidayDTO the admHoliday to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new admHoliday, or with status {@code 400 (Bad Request)} if the admHoliday has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/adm-holidays")
    public ResponseEntity<AdmHolidayDTO> createAdmHoliday(@Valid @RequestBody AdmHolidayDTO admHolidayDTO) throws URISyntaxException {
        log.debug("REST request to save AdmHolidayDTO : {}", admHolidayDTO);
        if (admHolidayDTO.getId() != null) {
            throw new BadRequestAlertException("A new admHoliday cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AdmHolidayDTO result = admHolidayService.save(admHolidayDTO);
        return ResponseEntity
            .created(new URI("/api/adm-holidays/" + result.getId()))
            .headers(PwcHeadUtil.createAlert(MessageCodes.PWB_AMD14_0001_S(admHolidayDTO.getHolidayName())))
            .body(result);
    }

    /**
     * {@code PUT  /adm-holidays/:id} : Updates an existing admHoliday.
     *
     * @param id the id of the admHoliday to save.
     * @param admHolidayDTO the admHoliday to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated admHoliday,
     * or with status {@code 400 (Bad Request)} if the admHoliday is not valid,
     * or with status {@code 500 (Internal Server Error)} if the admHoliday couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/adm-holidays/{id}")
    public ResponseEntity<AdmHolidayDTO> updateAdmHoliday(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody AdmHolidayDTO admHolidayDTO
    ) throws URISyntaxException {
        log.debug("REST request to update AdmHoliday : {}, {}", id, admHolidayDTO);
        if (admHolidayDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, Long.parseLong(admHolidayDTO.getId()))) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!admHolidayRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        AdmHolidayDTO result = admHolidayService.save(admHolidayDTO);
        return ResponseEntity
            .ok()
            .headers(PwcHeadUtil.createAlert(MessageCodes.PWB_AMD14_0002_S(admHolidayDTO.getHolidayName())))
            .body(result);
    }


    /**
     * {@code GET  /adm-holidays/:id} : get the "id" admHoliday.
     *
     * @param id the id of the admHoliday to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the admHoliday, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/adm-holidays/{id}")
    public ResponseEntity<AdmHoliday> getAdmHoliday(@PathVariable Long id) {
        log.debug("REST request to get AdmHoliday : {}", id);
        Optional<AdmHoliday> admHoliday = admHolidayRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(admHoliday);
    }

    /**
     * {@code DELETE  /adm-holidays/:id} : delete the "id" admHoliday.
     *
     * @param id the id of the admHoliday to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/adm-holidays/{id}")
    public ResponseEntity<Void> deleteAdmHoliday(@PathVariable Long id) {
        log.debug("REST request to delete AdmHoliday : {}", id);
        Optional<AdmHoliday> result = admHolidayRepository.findById(id);
        admHolidayRepository.deleteById(id);
        return ResponseEntity.noContent().headers(PwcHeadUtil.createAlert(MessageCodes.PWB_AMD14_0003_S(result.get().getHolidayName()))).build();
    }

    /**
     * {@code POST  /adm-holidays/criteria-jpa} : get admHoliday by criteria.
     *
     * @param criteria the criteria of the AdmHolidayCriteria to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the AdmHolidayDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/adm-holidays/criteria-jpa")
    public Page<AdmHolidayDTO> getAdmHolidayByCriteria(@Valid @RequestBody AdmHolidayCriteria criteria) {
        log.debug("REST request to get AdmHolidayDTO : {}", criteria);
        return admHolidayService.findByCriteria(criteria);
    }
}
