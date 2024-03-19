package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.BidPrjDsptch;
import gov.pcc.pwc.domain.BidPrjDsptchPrimaryKey;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the BidPrjDsptch entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BidPrjDsptchRepository extends JpaRepository<BidPrjDsptch, BidPrjDsptchPrimaryKey>,JpaSpecificationExecutor<BidPrjDsptch> {}
