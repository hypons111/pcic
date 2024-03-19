package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.service.commonService.bidUtilService.BidUnitService;
import gov.pcc.pwc.service.dto.AdmAgentDTO;
import gov.pcc.pwc.service.dto.BidUnit10DTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BidUtilResource {

    private final Logger log = LoggerFactory.getLogger(BidUtilResource.class);

    private final BidUnitService bidUnitService;

    public BidUtilResource(BidUnitService bidUnitService) {
        this.bidUnitService = bidUnitService;
    }

    /**
     *
     * @param wkut 執行單位 對應到 adm Agent 的org id
     * @return 該執行單位的 監察單位的 list(name 和 code)
     */
    @GetMapping("/bid-utils/findBidExamineUnit/{wkut}")
    public List<AdmAgentDTO> findBidExamineUnit(@PathVariable(value = "wkut", required = true) final String wkut){
        log.debug("REST request to find examine unit : {}", wkut);
        return bidUnitService.findBidExamineUnit(wkut);
    }

    /**
     *
     * @param wkut 執行單位 對應到 bid Unit10 的code
     * @return 該執行單位的 主辦機關及主管機關的 list(name 和 code)
     */
    @GetMapping("/bid-utils/findBidManageUnit/{wkut}")
    public List<BidUnit10DTO> findManageUnit(@PathVariable(value = "wkut", required = true) final String wkut){
        log.debug("REST request to find examine unit : {}", wkut);
        return bidUnitService.findBidManageUnit(wkut);
    }

}
