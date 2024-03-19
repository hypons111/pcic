package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.BidPrjPay;
import gov.pcc.pwc.domain.BidPrjPayPrimaryKey;
import gov.pcc.pwc.repository.custom.BidPrjPayRepositoryCustom;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the BidPrjPay entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BidPrjPayRepository extends JpaRepository<BidPrjPay, BidPrjPayPrimaryKey>, BidPrjPayRepositoryCustom, JpaSpecificationExecutor<BidPrjPay> {}
