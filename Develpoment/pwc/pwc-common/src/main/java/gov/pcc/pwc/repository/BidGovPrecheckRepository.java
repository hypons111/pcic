package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.BidGovPrecheck;
import gov.pcc.pwc.domain.BidGovPrecheckPrimaryKey;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the BidGovPrecheck entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BidGovPrecheckRepository extends JpaRepository<BidGovPrecheck, BidGovPrecheckPrimaryKey>,JpaSpecificationExecutor<BidGovPrecheck>  {}
