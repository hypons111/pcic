package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.BidPrjPrice;
import gov.pcc.pwc.domain.BidPrjPricePrimaryKey;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the BidPrjPrice entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BidPrjPriceRepository extends JpaRepository<BidPrjPrice, BidPrjPricePrimaryKey>,JpaSpecificationExecutor<BidPrjPrice> {}
