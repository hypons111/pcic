package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.BidPrjPe;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the BidPrjPe entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BidPrjPeRepository extends JpaRepository<BidPrjPe, String> {}
