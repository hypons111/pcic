package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.BidMdlbas;
import gov.pcc.pwc.domain.BidMdlbasPrimaryKey;
import gov.pcc.pwc.repository.custom.BidMdlbasRepositoryCustom;
import gov.pcc.pwc.repository.custom.BidPrjStcRepositoryCustom;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the BidMdlbas entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BidMdlbasRepository extends JpaRepository<BidMdlbas, BidMdlbasPrimaryKey>, BidMdlbasRepositoryCustom {}
