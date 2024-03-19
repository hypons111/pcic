package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.BidCheckerr;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the BidCheckerr entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BidCheckerrRepository extends JpaRepository<BidCheckerr, String> {}
