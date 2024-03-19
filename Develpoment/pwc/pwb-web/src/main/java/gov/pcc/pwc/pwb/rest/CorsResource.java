package gov.pcc.pwc.pwb.rest;

import gov.pcc.pwc.repository.CorsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


/**
 * REST controller for openData API.
 * Cors拿外部openAPI範例
 */
@RestController
@RequestMapping("/api")
@Transactional
public class CorsResource {

    private final Logger log = LoggerFactory.getLogger(CorsResource.class);

    private static final String ENTITY_NAME = "corsResource";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CorsRepository corsRepository;

    public CorsResource(CorsRepository corsRepository) {
        this.corsRepository = corsRepository;
    }

    /**
     * {@code GET  cors/getOpenData/parking} : get all the openData of parking space.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of admUnits in body.
     */
    @GetMapping("/cors/getOpenData/parking")
    public String getOpenDataParkingSpace() {
        log.debug("REST request to get all the openData of parking space.");
        return corsRepository.findAllParkingSpace();
    }

}
