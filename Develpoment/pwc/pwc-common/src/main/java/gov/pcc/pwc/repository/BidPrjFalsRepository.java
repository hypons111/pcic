package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.BidPrjFals;
import gov.pcc.pwc.domain.BidPrjFalsPrimaryKey;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the BidPrjFals entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BidPrjFalsRepository extends JpaRepository<BidPrjFals, BidPrjFalsPrimaryKey> ,JpaSpecificationExecutor<BidPrjFals>{}
