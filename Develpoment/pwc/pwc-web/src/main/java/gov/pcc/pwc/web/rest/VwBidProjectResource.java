package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.domain.BidProjectPrimaryKey;
import gov.pcc.pwc.repository.VwBidProjectRepository;
import gov.pcc.pwc.service.VwBidProjectService;
import gov.pcc.pwc.service.criteria.VwBidProjectCriteria;
import gov.pcc.pwc.service.dto.VwBidProjectDTO;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import gov.pcc.pwc.service.dto.VwBidProjectWkutDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.ResponseUtil;

import javax.validation.Valid;

/**
 * REST controller for managing {@link gov.pcc.pwc.domain.VwBidProject}.
 */
@RestController
@RequestMapping("/api")
public class VwBidProjectResource {

    private final Logger log = LoggerFactory.getLogger(VwBidProjectResource.class);

    private static final String ENTITY_NAME = "vwBidProject";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final VwBidProjectService vwBidProjectService;

    private final VwBidProjectRepository vwBidProjectRepository;

    public VwBidProjectResource(VwBidProjectService vwBidProjectService, VwBidProjectRepository vwBidProjectRepository) {
        this.vwBidProjectService = vwBidProjectService;
        this.vwBidProjectRepository = vwBidProjectRepository;
    }


    /**
     * {@code GET  /vw-bid-projects} : get all the vwBidProjects.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of vwBidProjects in body.
     */
    @GetMapping("/vw-bid-projects")
    public List<VwBidProjectDTO> getAllVwBidProjects() {
        log.debug("REST request to get all VwBidProjects");
        return vwBidProjectService.findAll();
    }

    @PostMapping("/vw-bid-projects/criteria")
    public Page<VwBidProjectDTO> getVwBidProjectByCriteria(@Valid  @RequestBody VwBidProjectCriteria criteria){
        log.debug("REST request to get VwBidProject by criteria : {}", criteria);
        if("specificUnit".equals(criteria.getQueryType())){
            return vwBidProjectService.findProjectByDesingOrSupervisionUnitName(criteria);
        }
        return vwBidProjectService.findByCriteria(criteria);
    }

    @PostMapping("/vw-bid-projects/add92")
    public List<VwBidProjectWkutDTO> getVwBidProjectWkutByAdd92(@RequestBody Map<String, String> add92){
        log.debug("REST request to get VwBidProject WKUT by add92 : {}", add92.get("add92"));
        return vwBidProjectService.findWkutNameAndCodeByAdd92(add92.get("add92"));
    }
}
