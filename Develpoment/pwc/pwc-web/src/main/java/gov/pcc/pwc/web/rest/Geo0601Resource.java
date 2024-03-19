package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.repository.Geo0601Repository;
import gov.pcc.pwc.service.Geo0601Service;
import gov.pcc.pwc.service.dto.Geo0601DTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * REST controller for managing {@link gov.pcc.pwc.domain.GeoJob}.
 */
@RestController
@RequestMapping("/api")
public class Geo0601Resource {

    private final Logger log = LoggerFactory.getLogger(Geo0601Resource.class);

    private static final String ENTITY_NAME = "geo0601";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final Geo0601Service geo0601Service;

    private final Geo0601Repository geo0601Repository;

    public Geo0601Resource(Geo0601Service geo0601Service, Geo0601Repository geo0601Repository) {
        this.geo0601Service = geo0601Service;
        this.geo0601Repository = geo0601Repository;
    }

    /**
     * {@code POST  /geo-0601} : get geo0601.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the geo0601DTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/geo-0601")
    public Geo0601DTO getAllCount() throws InterruptedException {
        log.debug("REST request to get Geo0601 : {}");
        return geo0601Service.getAllCount();
    }
}
