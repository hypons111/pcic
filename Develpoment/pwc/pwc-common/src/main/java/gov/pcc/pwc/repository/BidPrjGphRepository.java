package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.BidPrjGph;
import gov.pcc.pwc.domain.BidPrjGphPrimaryKey;
import gov.pcc.pwc.repository.custom.BidPrjGphRepositoryCustom;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the BidPrjGph entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BidPrjGphRepository extends JpaRepository<BidPrjGph, BidPrjGphPrimaryKey>, BidPrjGphRepositoryCustom, JpaSpecificationExecutor<BidPrjGph> {}
