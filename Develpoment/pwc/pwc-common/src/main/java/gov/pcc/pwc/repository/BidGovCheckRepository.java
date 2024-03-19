package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.BidGovCheck;
import gov.pcc.pwc.domain.BidGovCheckPrimaryKey;
import gov.pcc.pwc.repository.custom.BidGovCheckRepositoryCustom;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the BidGovCheck entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BidGovCheckRepository extends JpaRepository<BidGovCheck, BidGovCheckPrimaryKey>, BidGovCheckRepositoryCustom, JpaSpecificationExecutor<BidGovCheck> {}
