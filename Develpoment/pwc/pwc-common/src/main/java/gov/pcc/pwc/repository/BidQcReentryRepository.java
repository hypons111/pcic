package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.BidQcReentry;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the BidQcReentry entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BidQcReentryRepository extends JpaRepository<BidQcReentry, String> {}
