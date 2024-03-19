package gov.pcc.pwc.pwb.rest;

import gov.pcc.pwc.MessageCodes;
import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.domain.AdmCodeTable;
import gov.pcc.pwc.domain.AdmRole;
import gov.pcc.pwc.repository.AdmCodeTableRepository;
import gov.pcc.pwc.service.AdmCodeTableService;
import gov.pcc.pwc.service.criteria.AdmCodeTableCriteria;
import gov.pcc.pwc.service.dto.AdmCodeTableDTO;
import gov.pcc.pwc.utils.PwcHeadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

/**
 * REST controller for managing {@link AdmRole}.
 */
@RestController
@RequestMapping("/api")
public class AdmCodeTableResource {

    private final Logger log = LoggerFactory.getLogger(AdmCodeTableResource.class);

    private static final String ENTITY_NAME = "AdmCodeTable";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AdmCodeTableService admCodeTableService;

    private final AdmCodeTableRepository admCodeTableRepository;

    public AdmCodeTableResource(AdmCodeTableService admCodeTableService, AdmCodeTableRepository admCodeTableRepository) {
        this.admCodeTableService = admCodeTableService;
        this.admCodeTableRepository = admCodeTableRepository;
    }


    /**
     * {@code POST  /adm-code-table} : Create a new AdmCodeTable.
     *
     * @param admCodeTable the AdmCodeTable to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new roleDTO, or with status {@code 400 (Bad Request)} if the admRole has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/adm-code-table")
    public ResponseEntity<AdmCodeTable> createCodeTable(@Valid @RequestBody AdmCodeTable admCodeTable) throws URISyntaxException {
        log.debug("REST request to save AdmCodeTable : {}", admCodeTable);
        if (admCodeTableRepository.findByCategoryAndCode(admCodeTable.getCategory(), admCodeTable.getCode()) != null) {
            return ResponseEntity.ok().headers(PwcHeadUtil.createAlert(MessageCodes.PWB_AMD13_0004_S(admCodeTable.getCode()))).body(admCodeTable);
        }
        AdmCodeTable result = admCodeTableRepository.save(admCodeTable);
        return ResponseEntity
            .created(new URI("/api/adm-code-table/" + result.getId()))
            .headers(
                PwcHeadUtil.createAlert(
                    MessageCodes.PWB_AMD13_0001_S(admCodeTable.getCode())
                )
            )
            .body(result);
    }

    /**
     * {@code PUT  /adm-code-table/:id} : Updates an existing admCodeTable.
     *
     * @param id the id of the admCodeTable to save.
     * @param admCodeTable the AdmCodeTable to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated admCodeTable,
     * or with status {@code 400 (Bad Request)} if the admCodeTable is not valid,
     * or with status {@code 500 (Internal Server Error)} if the admCodeTable couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/adm-code-table/{id}")
    public ResponseEntity<AdmCodeTable> updateAdmCodeTable(
        @PathVariable Long id,
        @Valid @RequestBody AdmCodeTable admCodeTable
    ) throws URISyntaxException {
        log.debug("REST request to update admCodeTable : {}", admCodeTable);
        if (admCodeTable.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, admCodeTable.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }
        if (!admCodeTableRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        if (admCodeTableRepository.findByCategoryAndCode(admCodeTable.getCategory(), admCodeTable.getCode()) != null
            && (admCodeTable.getId()) != admCodeTableRepository.findByCategoryAndCode(admCodeTable.getCategory(), admCodeTable.getCode()).getId() ) {
            return ResponseEntity.ok().headers(PwcHeadUtil.createAlert(MessageCodes.PWB_AMD13_0005_S(admCodeTable.getCode()))).body(admCodeTable);
        }
        AdmCodeTable result = admCodeTableRepository.save(admCodeTable);
        return ResponseEntity.ok().headers(PwcHeadUtil.createAlert(MessageCodes.PWB_AMD13_0002_S(result.getCode()))).body(result);
    }

    /**
     * {@code GET  /adm-code-table/:id} : get the "id" admCodeTable.
     *
     * @param id the id of the admCodeTableDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the admCodeTableDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/adm-code-table/{id}")
    public AdmCodeTableDTO getAdmCodeTable(@PathVariable Long id) {
        log.debug("REST request to get AdmCodeTableDTO : {}", id);
        final AdmCodeTableDTO admCodeTableDTO = admCodeTableService.findByPk(id);
        return admCodeTableDTO;
    }


    /**
     * {@code POST  /adm-code-table/criteria} : get admCodeTableDTO by criteria.
     *
     * @param criteria the criteria of the admCodeTableDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the AdmCodeTableDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/adm-code-table/criteria")
    public Page<AdmCodeTableDTO> getRolesByCriteria(@RequestBody AdmCodeTableCriteria criteria) {
        log.debug("REST request to get AdmCodeTableDTOs : {}", criteria);
        return admCodeTableService.findByCriteria(criteria);
    }

    /**
     * {@code DELETE  /adm-code-table/:id} : delete data of the "id" admCodeTable.
     *
     * @param id the id of the admCodeTable to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/adm-code-table/{id}")
    public ResponseEntity<Void> deleteCodeTable(@PathVariable Long id) {
        log.debug("REST request to delete admCodeTable : {}", id);
        String code = admCodeTableRepository.findByPk(id).getCode();
        admCodeTableRepository.deleteById(id);
        return ResponseEntity.noContent().headers(PwcHeadUtil.createAlert(MessageCodes.PWB_AMD13_0003_S(code))).build();
    }
}
