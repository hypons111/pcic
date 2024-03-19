package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.domain.AdmPostAddr;
import gov.pcc.pwc.repository.AdmPostAddrRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link AdmPostAddr}.
 */
@RestController
@RequestMapping("/api/service")
@Transactional
public class AdmPostAddrResource {

    private final Logger log = LoggerFactory.getLogger(AdmPostAddrResource.class);

    private static final String ENTITY_NAME = "admPostAddr";

    public static final String FIND_ALL_ADM_POST_ADDR= "findAllAdmPostAddr";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AdmPostAddrRepository admPostAddrRepository;

    public AdmPostAddrResource(AdmPostAddrRepository admPostAddrRepository) {
        this.admPostAddrRepository = admPostAddrRepository;
    }

    /**
     * {@code GET  /adm-post-addrs} : get all the admPostAddrs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
     *         of admPostAddrs in body.
     */
    @Cacheable(FIND_ALL_ADM_POST_ADDR)
    @GetMapping("/adm-post-addrs")
    public List<AdmPostAddr> getAllAdmPostAddrs() {
        log.debug("REST request to get all AdmPostAddrs");
        return admPostAddrRepository.findAll();
    }

    @GetMapping("/adm-post-addrs/{areaCode}")
    public AdmPostAddr getAllAdmPostAddrs(@PathVariable String areaCode) {
        log.debug("REST request to get a AdmPostAddr by areaCode");
        Optional<AdmPostAddr> admPostAddr = admPostAddrRepository.findByAreaCode(areaCode);
        return admPostAddr.orElse(null);
    }
}
