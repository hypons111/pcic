package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.BidPrjMonthPrimaryKey;
import gov.pcc.pwc.domain.BidPrjMonth;
import gov.pcc.pwc.repository.custom.BidPrjMonthRepositoryCustom;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the BidPrjMonth entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BidPrjMonthRepository extends JpaRepository<BidPrjMonth, BidPrjMonthPrimaryKey>, BidPrjMonthRepositoryCustom,JpaSpecificationExecutor<BidPrjMonth> {}
