package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.repository.AdmMenuRepository;
import gov.pcc.pwc.service.AdmMenuService;
import gov.pcc.pwc.service.dto.AdmMenuDTO;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing {@link gov.pcc.pwc.domain.AdmMenu}.
 */
@RestController
@RequestMapping("/api")
public class AdmMenuResource {

    private final Logger log = LoggerFactory.getLogger(AdmMenuResource.class);

    private static final String ENTITY_NAME = "admMenu";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AdmMenuService admMenuService;


    public AdmMenuResource(AdmMenuService admMenuService) {
        this.admMenuService = admMenuService;
    }

    @GetMapping("/service/adm-menus/open-menu")
    public List<AdmMenuDTO> getOpenMenu() {
        log.debug("REST request to get menu");
        return admMenuService.getMenu(true);
    }

    @GetMapping("/service/adm-menus/login-menu")
    public List<AdmMenuDTO> getLoginMenu() {
        log.debug("REST request to get menu");
        return admMenuService.getMenu(false);
    }
}
