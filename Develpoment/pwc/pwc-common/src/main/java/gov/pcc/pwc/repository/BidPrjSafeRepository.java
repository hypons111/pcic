package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.BidPrjSafe;
import gov.pcc.pwc.domain.BidPrjSafePrimaryKey;
import gov.pcc.pwc.repository.custom.BidPrjSafeRepositoryCustom;
import gov.pcc.pwc.service.criteria.BidPrjSafeCriteria;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the BidPrjSafe entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BidPrjSafeRepository extends JpaRepository<BidPrjSafe, BidPrjSafePrimaryKey>, BidPrjSafeRepositoryCustom,JpaSpecificationExecutor<BidPrjSafe> {}
