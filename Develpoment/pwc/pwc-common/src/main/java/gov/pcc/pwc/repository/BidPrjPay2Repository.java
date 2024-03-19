package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.BidPrjPay2;
import gov.pcc.pwc.domain.BidPrjPay2PrimaryKey;
import gov.pcc.pwc.repository.custom.BidPrjPay2RepositoryCustom;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the BidPrjPay2 entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BidPrjPay2Repository extends JpaRepository<BidPrjPay2, BidPrjPay2PrimaryKey>, BidPrjPay2RepositoryCustom {}
