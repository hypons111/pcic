package gov.pcc.pwc.repository.custom;

import gov.pcc.pwc.domain.BidPrjQc;
import gov.pcc.pwc.service.dto.BidPrjQcDTO;

import java.util.List;

public interface BidPrjQcRepositoryCustom {
    List<BidPrjQcDTO> findBidPrjQc(BidPrjQcDTO bidPrjQcDTO);
}
