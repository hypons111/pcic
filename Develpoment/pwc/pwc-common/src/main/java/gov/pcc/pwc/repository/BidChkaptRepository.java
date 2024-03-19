package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.BidChkapt;
import gov.pcc.pwc.domain.BidChkaptPrimaryKey;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the BidChkapt entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BidChkaptRepository extends JpaRepository<BidChkapt, BidChkaptPrimaryKey>, JpaSpecificationExecutor<BidChkapt> {}
