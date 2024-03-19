package gov.pcc.pwc.repository.custom;

import gov.pcc.pwc.service.criteria.BidPrjPayCriteria;

public interface BidPrjPayRepositoryCustom {
    int findMaxMonNo(BidPrjPayCriteria criteria);
}
