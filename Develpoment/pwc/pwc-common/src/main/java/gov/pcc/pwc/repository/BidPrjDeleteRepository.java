package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.BidPrjDelete;
import gov.pcc.pwc.domain.BidProjectPrimaryKey;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the BidPrjDelete entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BidPrjDeleteRepository extends JpaRepository<BidPrjDelete, BidProjectPrimaryKey> {}
