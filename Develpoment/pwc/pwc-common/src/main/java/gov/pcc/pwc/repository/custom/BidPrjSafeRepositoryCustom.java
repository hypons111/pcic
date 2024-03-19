package gov.pcc.pwc.repository.custom;

import gov.pcc.pwc.service.criteria.BidPrjSafeCriteria;
import gov.pcc.pwc.service.dto.BidPrjSafeDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BidPrjSafeRepositoryCustom {
    Page<BidPrjSafeDTO> findAllBidSafeKey(BidPrjSafeCriteria criteria);
}
