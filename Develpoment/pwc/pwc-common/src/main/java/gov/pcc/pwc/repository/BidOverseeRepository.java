package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.BidOversee;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the BidOversee entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BidOverseeRepository extends JpaRepository<BidOversee, String>,JpaSpecificationExecutor<BidOversee> {}
