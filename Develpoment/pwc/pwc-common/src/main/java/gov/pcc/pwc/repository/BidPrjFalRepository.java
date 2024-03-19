package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.BidPrjFal;
import gov.pcc.pwc.domain.BidPrjFalPrimaryKey;
import gov.pcc.pwc.domain.BidPrjMonthPrimaryKey;
import gov.pcc.pwc.repository.custom.BidPrjFalRepositoryCustom;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the BidPrjFal entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BidPrjFalRepository extends JpaRepository<BidPrjFal, BidPrjFalPrimaryKey>, BidPrjFalRepositoryCustom {}
