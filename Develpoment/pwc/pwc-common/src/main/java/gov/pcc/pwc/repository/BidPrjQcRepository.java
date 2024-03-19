package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.BidPrjQc;
import gov.pcc.pwc.domain.BidPrjQcPrimaryKey;
import gov.pcc.pwc.repository.custom.BidPrjQcRepositoryCustom;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the BidPrjQc entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BidPrjQcRepository extends JpaRepository<BidPrjQc, BidPrjQcPrimaryKey>, BidPrjQcRepositoryCustom {}
