package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.BidPrjFrwkr;
import gov.pcc.pwc.domain.BidPrjSegmset;
import gov.pcc.pwc.domain.BidPrjSegmsetPrimaryKey;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the BidPrjSegmset entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BidPrjSegmsetRepository extends JpaRepository<BidPrjSegmset, BidPrjSegmsetPrimaryKey>, JpaSpecificationExecutor<BidPrjSegmset> {}
