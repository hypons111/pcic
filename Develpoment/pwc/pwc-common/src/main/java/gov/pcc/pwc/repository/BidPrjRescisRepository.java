package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.BidPrjRescis;
import gov.pcc.pwc.domain.BidPrjRescisPrimaryKey;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the BidPrjRescis entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BidPrjRescisRepository extends JpaRepository<BidPrjRescis, BidPrjRescisPrimaryKey>, JpaSpecificationExecutor<BidPrjRescis> {}
