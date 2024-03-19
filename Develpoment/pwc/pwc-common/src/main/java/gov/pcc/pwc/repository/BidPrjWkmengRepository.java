package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.BidPrjWkmeng;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the BidPrjWkmeng entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BidPrjWkmengRepository extends JpaRepository<BidPrjWkmeng, String> {}
