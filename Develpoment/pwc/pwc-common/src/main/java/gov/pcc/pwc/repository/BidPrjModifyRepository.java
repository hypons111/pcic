package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.BidPrjModify;
import gov.pcc.pwc.domain.BidPrjModifyPrimaryKey;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the BidPrjModify entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BidPrjModifyRepository extends JpaRepository<BidPrjModify, BidPrjModifyPrimaryKey>,JpaSpecificationExecutor<BidPrjModify>  {}
