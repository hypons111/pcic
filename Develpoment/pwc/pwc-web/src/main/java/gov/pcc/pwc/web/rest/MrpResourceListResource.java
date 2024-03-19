package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.repository.MrpResourceListRepository;
import gov.pcc.pwc.service.MrpResourceListService;
import gov.pcc.pwc.service.dto.MrpResourceListDTO;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link gov.pcc.pwc.domain.MrpResourceList}.
 */
@RestController
@RequestMapping("/api")
public class MrpResourceListResource {

    private final Logger log = LoggerFactory.getLogger(MrpResourceListResource.class);

    private static final String ENTITY_NAME = "mrpResourceList";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MrpResourceListService mrpResourceListService;

    private final MrpResourceListRepository mrpResourceListRepository;

    public MrpResourceListResource(MrpResourceListService mrpResourceListService, MrpResourceListRepository mrpResourceListRepository) {
        this.mrpResourceListService = mrpResourceListService;
        this.mrpResourceListRepository = mrpResourceListRepository;
    }
    @GetMapping("/service/MRP0104R/queryKPI")
    public Map<String, Object> queryKPI() {
        log.debug("Rest queryKPI");
        return mrpResourceListService.queryKPI();
    }

//    @PostMapping("/mrp/test")
//    public Map<String, Object> test(HttpServletRequest request) {
//        System.out.println("MrpResourceListResource");
//        System.out.println(mrpResourceListService.test(request));
//        return mrpResourceListService.test(request);
//    }

}
