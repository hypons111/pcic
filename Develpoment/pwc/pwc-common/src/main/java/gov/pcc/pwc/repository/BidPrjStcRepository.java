package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.BidPrjStc;
import gov.pcc.pwc.domain.BidPrjStcPrimaryKey;
import gov.pcc.pwc.repository.custom.BidPrjStcRepositoryCustom;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the BidPrjStc entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BidPrjStcRepository extends JpaRepository<BidPrjStc, BidPrjStcPrimaryKey>,JpaSpecificationExecutor<BidPrjStc>,BidPrjStcRepositoryCustom {}
