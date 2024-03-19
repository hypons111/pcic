package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.service.EngCaseBidCompService;
import gov.pcc.pwc.service.criteria.EngCaseTotalDTOCriteria;
import gov.pcc.pwc.service.dto.EngCaseTotalDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class EngCaseBidCompResource {

    private final Logger log = LoggerFactory.getLogger(EngCaseBidCompResource.class);

    private static final String ENTITY_NAME = "engCaseBidComp";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EngCaseBidCompService engCaseBidCompService;

    public EngCaseBidCompResource(EngCaseBidCompService engCaseBidCompService) {
        this.engCaseBidCompService = engCaseBidCompService;
    }

    @PostMapping("/eng-eng0301r1")
    public Page<EngCaseTotalDTO> findEng0301(
        @Valid @RequestBody EngCaseTotalDTOCriteria criteria) throws InterruptedException {
        log.debug("Rest eng0301" + criteria);
        return engCaseBidCompService.findEng0301R1(criteria);
    }

}
