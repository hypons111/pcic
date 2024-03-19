package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.BidPrjGdfk;
import gov.pcc.pwc.domain.BidPrjGdfkPrimaryKey;
import gov.pcc.pwc.domain.BidPrjWksorc;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the BidPrjGdfk entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BidPrjGdfkRepository extends JpaRepository<BidPrjGdfk, BidPrjGdfkPrimaryKey>, JpaSpecificationExecutor<BidPrjGdfk> {}
