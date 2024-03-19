package gov.pcc.pwc.repository.custom;

import gov.pcc.pwc.service.criteria.BidPrjGphCriteria;

public interface BidPrjGphRepositoryCustom {
    int findMaxSeq(BidPrjGphCriteria criteria);
}
