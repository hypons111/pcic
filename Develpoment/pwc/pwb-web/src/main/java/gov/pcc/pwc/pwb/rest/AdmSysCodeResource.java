package gov.pcc.pwc.pwb.rest;

import gov.pcc.pwc.MessageCodes;
import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.domain.AdmSysCode;
import gov.pcc.pwc.repository.AdmSysCodeRepository;
import gov.pcc.pwc.service.AdmSysCodeService;
import gov.pcc.pwc.service.criteria.AdmSysCodeCriteria;
import gov.pcc.pwc.service.dto.AdmSysCodeDTO;
import gov.pcc.pwc.service.dto.AdmSysParameterDTO;
import gov.pcc.pwc.utils.PwcHeadUtil;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;

/**
 * REST controller for managing {@link AdmSysCode}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class AdmSysCodeResource {

    private final Logger log = LoggerFactory.getLogger(AdmSysCodeResource.class);

    private static final String ENTITY_NAME = "admSysCode";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AdmSysCodeService admSysCodeService;

    private final AdmSysCodeRepository admSysCodeRepository;

    public AdmSysCodeResource(AdmSysCodeService admSysCodeService, AdmSysCodeRepository admSysCodeRepository) {
        this.admSysCodeService = admSysCodeService;
        this.admSysCodeRepository = admSysCodeRepository;
    }

    /**
     * {@code POST  /adm-sys-codes} : Create a new admSysCode.
     *
     * @param admSysCode the admSysCode to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new admSysCode, or with status {@code 400 (Bad Request)} if the admSysCode has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/adm-sys-codes")
    public ResponseEntity<AdmSysCode> createAdmSysCode(@Valid @RequestBody AdmSysCode admSysCode) throws URISyntaxException {
        log.debug("REST request to save AdmSysCode : {}", admSysCode);
        if (admSysCodeRepository.findByPk(admSysCode.getModuleType(), admSysCode.getDataType(), admSysCode.getDataKey()) != null) {
            throw new BadRequestAlertException("A new admSysCode cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AdmSysCode result = admSysCodeRepository.save(admSysCode);
        return ResponseEntity
            .created(new URI("/api/adm-sys-codes/" + result.getModuleType() + "/" + result.getDataType() + "/" + result.getDataKey()))
            .headers(
                PwcHeadUtil.createAlert(
                    MessageCodes.PWB_AMD12_0001_S(admSysCode.getModuleType(), admSysCode.getDataType(), admSysCode.getDataKey())
                )
            )
            .body(result);
    }

    /**
     * {@code GET  /adm-sys-codes/:moduleType/:dataType/:dataKey} : get the "id" admSysCode.
     *
     * @param moduleType,dataType,dataKey the id of the admSysCode to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the admSysCode, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/adm-sys-codes/{moduleType}/{dataType}/{dataKey}")
    public AdmSysCodeDTO getAdmSysCode(@PathVariable String moduleType, @PathVariable String dataType, @PathVariable String dataKey) {
        log.debug("REST request to get AdmSysCode : {},{},{}", moduleType, dataType, dataKey);
        return admSysCodeService.findByPk(moduleType, dataType, dataKey);
    }

    /**
     * {@code PUT  /adm-sys-codes/:moduleType/:dataType/:dataKey} : Updates an existing admSysCode.
     *
     * @param moduleType,dataType,dataKey the unique key of the admSysCodeDTO to save.
     * @param admSysCodeDTO the AdmSysCode to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated admSysCodeDTO,
     * or with status {@code 400 (Bad Request)} if the admSysCodeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the admSysCodeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/adm-sys-codes/{moduleType}/{dataType}/{dataKey}")
    public ResponseEntity<AdmSysCodeDTO> updateSysCode(
        @PathVariable(value = "moduleType") final String moduleType,
        @PathVariable(value = "dataType") final String dataType,
        @PathVariable(value = "dataKey") final String dataKey,
        @Valid @RequestBody AdmSysCodeDTO admSysCodeDTO
    ) throws URISyntaxException {
        log.debug("REST request to update admSysCode : {}, {} ,{}, {}", moduleType, dataType, dataKey, admSysCodeDTO);
        if (admSysCodeDTO.getModuleType() == null || admSysCodeDTO.getDataType() == null || admSysCodeDTO.getDataKey() == null) {
            throw new BadRequestAlertException("Invalid functionId", ENTITY_NAME, "unique key null");
        }
        if (admSysCodeRepository.findByPk(moduleType, dataType, dataKey) == null) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "unique key not found");
        }

        AdmSysCodeDTO result = admSysCodeService.update(admSysCodeDTO);
        return ResponseEntity
            .ok()
            .headers(
                PwcHeadUtil.createAlert(
                    MessageCodes.PWB_AMD12_0002_S(admSysCodeDTO.getModuleType(), admSysCodeDTO.getDataType(), admSysCodeDTO.getDataKey())
                )
            )
            .body(result);
    }

    /**
     * {@code DELETE  /adm-sys-codes/:moduleType/:dataType/:dataKey} : delete the "ID" admSysCode.
     *
     * @param moduleType,dataType,dataKey the id of the admSysCode to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/adm-sys-codes/{moduleType}/{dataType}/{dataKey}")
    public ResponseEntity<Void> deleteAdmSysCode(
        @PathVariable String moduleType,
        @PathVariable String dataType,
        @PathVariable String dataKey
    ) {
        log.debug("REST request to delete AdmSysCode : {}, {}, {}", moduleType, dataType, dataKey);
        admSysCodeRepository.deleteByModuleTypeAndDataTypeAndDataKey(moduleType, dataType, dataKey);
        return ResponseEntity
            .noContent()
            .headers(PwcHeadUtil.createAlert(MessageCodes.PWB_AMD12_0003_S(moduleType, dataType, dataKey)))
            .build();
    }

    /**
     * {@code POST  /adm-sys-codes/criteria-jpa} : get admSysCode by criteria.
     *
     * @param criteria the criteria of the AdmSysCodeCriteria to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the AdmSysCodeDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/adm-sys-codes/criteria-jpa")
    public Page<AdmSysCodeDTO> getSysCodeByCriteria(@Valid @RequestBody AdmSysCodeCriteria criteria) {
        log.debug("REST request to get AdmSysCodeDTO : {}", criteria);
        return admSysCodeService.findByCriteria(criteria);
    }

    /**
     * {@code GET  /adm-sys-codes/get-data-type-opts} : get all dataType of admSysCodes for front-end dataTypeOpts.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the admSysCodeDTOs, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/adm-sys-codes/get-data-type-opts")
    public List<AdmSysCodeDTO> getDataTypeOpts() {
        log.debug("REST request to get all admSysCodeDTO for front-end dataTypeOpts");
        return admSysCodeService.findAllDataTypeOpts();
    }
}
