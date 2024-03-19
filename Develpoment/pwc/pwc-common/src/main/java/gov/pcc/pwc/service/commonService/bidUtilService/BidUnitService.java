package gov.pcc.pwc.service.commonService.bidUtilService;

import gov.pcc.pwc.service.AdmAgentService;
import gov.pcc.pwc.service.BidUnit10Service;
import gov.pcc.pwc.service.dto.AdmAgentDTO;
import gov.pcc.pwc.service.dto.BidUnit10DTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BidUnitService {

    private final AdmAgentService admAgentService;

    private final BidUnit10Service bidUnit10Service;

    public BidUnitService(AdmAgentService admAgentService, BidUnit10Service bidUnit10Service) {
        this.admAgentService = admAgentService;
        this.bidUnit10Service = bidUnit10Service;
    }

    public List<AdmAgentDTO> findBidExamineUnit(String wkutCode){
        return admAgentService.findBidExamineUnit(wkutCode);
    }

    public List<BidUnit10DTO> findBidManageUnit(String wkutCode){
        return bidUnit10Service.findBidManageUnit(wkutCode);
    }
}
