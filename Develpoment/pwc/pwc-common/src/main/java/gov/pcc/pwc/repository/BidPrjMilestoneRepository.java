package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.BidPrjMilestone;
import gov.pcc.pwc.domain.BidPrjMilestonePrimaryKey;
import gov.pcc.pwc.domain.BidPrjStc;
import gov.pcc.pwc.repository.custom.BidPrjMilestoneRepositoryCustom;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the BidPrjMilestone entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BidPrjMilestoneRepository extends JpaRepository<BidPrjMilestone, BidPrjMilestonePrimaryKey>, BidPrjMilestoneRepositoryCustom,JpaSpecificationExecutor<BidPrjMilestone> {}
