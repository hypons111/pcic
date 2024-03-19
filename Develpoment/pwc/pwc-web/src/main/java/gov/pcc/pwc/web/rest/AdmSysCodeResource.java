package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.MessageCodes;
import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.domain.AdmSysCode;
import gov.pcc.pwc.repository.AdmSysCodeRepository;
import gov.pcc.pwc.service.AdmSysCodeService;
import gov.pcc.pwc.service.criteria.AdmSysCodeCriteria;
import gov.pcc.pwc.service.dto.AdmSysCodeDTO;
import gov.pcc.pwc.utils.PwcHeadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * REST controller for managing {@link AdmSysCode}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class AdmSysCodeResource {

    private final Logger log = LoggerFactory.getLogger(AdmSysCodeResource.class);

    private static final String ENTITY_NAME = "admSysCode";

    public static final String FIND_ALL_ADM_SYS_CODE= "findAllAdmSysCode";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AdmSysCodeService admSysCodeService;

    private final AdmSysCodeRepository admSysCodeRepository;

    public AdmSysCodeResource(AdmSysCodeService admSysCodeService, AdmSysCodeRepository admSysCodeRepository) {
        this.admSysCodeService = admSysCodeService;
        this.admSysCodeRepository = admSysCodeRepository;
    }

    /**
     * {@code GET  /adm-sys-codes} : get all the admSysCodes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of admSysCodes in body.
     */
    @GetMapping("/service/adm-sys-codes")
    public List<AdmSysCodeDTO> getAllAdmSysCodes() {
        log.debug("REST request to get all AdmSysCodes");
        return admSysCodeService.findAll();
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
     * {@code POST  /adm-sys-codes/criteria-jpa} : get admSysCode by criteria.
     *
     * @param criteria the criteria of the AdmSysCodeCriteria to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the AdmSysCodeDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/adm-sys-codes/criteria-jpa")
    public Page<AdmSysCodeDTO> getSysCodeByCriteria(@Valid @RequestBody AdmSysCodeCriteria criteria) {
        log.debug("REST request to get AdmSysCodeDTO : {}", criteria);
        criteria.setPerPage(2000);
        return admSysCodeService.findByCriteria(criteria);
    }
}
