package gov.pcc.pwc.pwb.rest;

import gov.pcc.pwc.MessageCodes;
import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.domain.AdmSysParameter;
import gov.pcc.pwc.repository.AdmSysParameterRepository;
import gov.pcc.pwc.service.AdmSysParameterService;
import gov.pcc.pwc.service.criteria.AdmSysParameterCriteria;
import gov.pcc.pwc.service.dto.AdmFunctionExtendDTO;
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

/**
 * REST controller for managing {@link AdmSysParameter}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class AdmSysParameterResource {

    private final Logger log = LoggerFactory.getLogger(AdmSysParameterResource.class);

    private static final String ENTITY_NAME = "admSysParameter";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AdmSysParameterService admSysParameterService;

    private final AdmSysParameterRepository admSysParameterRepository;

    public AdmSysParameterResource(AdmSysParameterService admSysParameterService, AdmSysParameterRepository admSysParameterRepository) {
        this.admSysParameterService = admSysParameterService;
        this.admSysParameterRepository = admSysParameterRepository;
    }

    /**
     * {@code POST  /adm-sys-parameters} : Create a new admSysParameter.
     *
     * @param admSysParameter the admSysParameter to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new admSysParameter, or with status {@code 400 (Bad Request)} if the admSysParameter has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/adm-sys-parameters")
    public ResponseEntity<AdmSysParameter> createAdmSysParameter(@Valid @RequestBody AdmSysParameter admSysParameter)
        throws URISyntaxException {
        log.debug("REST request to save AdmSysParameter : {}", admSysParameter);

        if (admSysParameterRepository.findByPk(admSysParameter.getSysName(), admSysParameter.getSysType()) != null) {
            throw new BadRequestAlertException("A new admSysParameter cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AdmSysParameter result = admSysParameterRepository.save(admSysParameter);
        return ResponseEntity
            .created(new URI("/api/adm-sys-parameters/" + result.getSysName() + "/" + result.getSysType()))
            .headers(PwcHeadUtil.createAlert(MessageCodes.PWB_AMD11_0001_S(admSysParameter.getSysName(), admSysParameter.getSysType())))
            .body(result);
    }

    /**
     * {@code GET  /adm-sys-parameters} : get all the admSysParameters.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of admSysParameters in body.
     */
    @GetMapping("/adm-sys-parameters")
    public List<AdmSysParameter> getAllAdmSysParameters() {
        log.debug("REST request to get all AdmSysParameters");
        return admSysParameterRepository.findAll();
    }

    /**
     * {@code GET  /adm-sys-parameters/:sysName/:sysType} : get the "id" admSysParameter.
     *
     * @param sysName,sysType the id of the admSysParameter to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the admSysParameter, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/adm-sys-parameters/{sysName}/{sysType}")
    public AdmSysParameterDTO getAdmSysParameter(@PathVariable String sysName, @PathVariable String sysType) {
        log.debug("REST request to get AdmSysParameter : {}", sysName, sysType);
        return admSysParameterService.findByPk(sysName, sysType);
    }

    /**
     * {@code PUT  /adm-sys-parameters/:sysName/:sysType} : Updates an existing admSysParameter.
     *
     * @param sysName,sysType the unique key of the admSysParameterDTO to save.
     * @param admSysParameterDTO the AdmSysParameter to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated admSysParameterDTO,
     * or with status {@code 400 (Bad Request)} if the admSysParameterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the admSysParameterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/adm-sys-parameters/{sysName}/{sysType}")
    public ResponseEntity<AdmSysParameterDTO> updateSysParameter(
        @PathVariable(value = "sysName") final String sysName,
        @PathVariable(value = "sysType") final String sysType,
        @Valid @RequestBody AdmSysParameterDTO admSysParameterDTO
    ) throws URISyntaxException {
        log.debug("REST request to update admSysParameter : {}, {} ,{}", sysName, sysType, admSysParameterDTO);
        if (admSysParameterDTO.getSysName() == null || admSysParameterDTO.getSysType() == null) {
            throw new BadRequestAlertException("Invalid functionId", ENTITY_NAME, "unique key null");
        }
        if (admSysParameterRepository.findByPk(sysName, sysType) == null) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "unique key not found");
        }

        AdmSysParameterDTO result = admSysParameterService.update(admSysParameterDTO);
        return ResponseEntity
            .ok()
            .headers(
                PwcHeadUtil.createAlert(MessageCodes.PWB_AMD11_0002_S(admSysParameterDTO.getSysName(), admSysParameterDTO.getSysType()))
            )
            .body(result);
    }

    /**
     * {@code DELETE  /adm-sys-parameters/:sysName/:sysType} : delete the "ID" admSysParameter.
     *
     * @param sysName,sysType the id of the admSysParameter to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/adm-sys-parameters/{sysName}/{sysType}")
    public ResponseEntity<Void> deleteAdmSysParameter(@PathVariable String sysName, @PathVariable String sysType) {
        log.debug("REST request to delete AdmSysParameter : {}, {}", sysName, sysType);
        admSysParameterRepository.deleteBySysNameAndSysType(sysName, sysType);
        return ResponseEntity.noContent().headers(PwcHeadUtil.createAlert(MessageCodes.PWB_AMD11_0003_S(sysName, sysType))).build();
    }

    /**
     * {@code POST  /adm-sys-parameters/criteria-jpa} : get admSysParameter by criteria.
     *
     * @param criteria the criteria of the AdmSysParameterCriteria to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the AdmSysParameterDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/adm-sys-parameters/criteria-jpa")
    public Page<AdmSysParameterDTO> getSysParameterByCriteria(@Valid @RequestBody AdmSysParameterCriteria criteria) {
        log.debug("REST request to get AdmSysParameterDTO : {}", criteria);
        return admSysParameterService.findByCriteria(criteria);
    }

    /**
     * {@code GET  /adm-sys-parameters/get-sys-type-opts} : get all sysType of admSysParameters for front-end sysTypeOpts.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the admSysParameterDTOs, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/adm-sys-parameters/get-sys-type-opts")
    public List<AdmSysParameterDTO> getSysTypeOpts() {
        log.debug("REST request to get all admSysParemeterDTO for front-end sysTypeOpts");
        return admSysParameterService.findAllSysTypeOpts();
    }
}
