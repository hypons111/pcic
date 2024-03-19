package gov.pcc.pwc.repository.custom;

import gov.pcc.pwc.service.criteria.BidPrjPay2Criteria;
import gov.pcc.pwc.service.dto.BidPrjPay2DTO;
import org.springframework.data.domain.Page;

public interface BidPrjPay2RepositoryCustom {
    Page<BidPrjPay2DTO> findBidPrjPay2ByCriteria(BidPrjPay2Criteria criteria);
}
