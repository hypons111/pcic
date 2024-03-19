package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.BidPrjStopwk;
import gov.pcc.pwc.domain.BidPrjStopwkPrimaryKey;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the BidPrjStopwk entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BidPrjStopwkRepository extends JpaRepository<BidPrjStopwk, BidPrjStopwkPrimaryKey>,JpaSpecificationExecutor<BidPrjStopwk> {}
