package gov.pcc.pwc.pwb.rest;

import gov.pcc.pwc.domain.AdmPostAddr;
import gov.pcc.pwc.repository.AdmPostAddrRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing {@link AdmPostAddr}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class AdmPostAddrResource {

    private final Logger log = LoggerFactory.getLogger(AdmPostAddrResource.class);

    private static final String ENTITY_NAME = "admPostAddr";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AdmPostAddrRepository admPostAddrRepository;

    public AdmPostAddrResource(AdmPostAddrRepository admPostAddrRepository) {
        this.admPostAddrRepository = admPostAddrRepository;
    }

    /**
     * {@code GET  /adm-post-addrs} : get all the admPostAddrs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of admPostAddrs in body.
     */
    @GetMapping("/adm-post-addrs")
    public List<AdmPostAddr> getAllAdmPostAddrs() {
        log.debug("REST request to get all AdmPostAddrs");
        return admPostAddrRepository.findAll();
    }
}
