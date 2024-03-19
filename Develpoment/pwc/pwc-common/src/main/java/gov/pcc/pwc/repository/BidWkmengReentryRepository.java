package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.BidWkmengReentry;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the BidWkmengReentry entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BidWkmengReentryRepository extends JpaRepository<BidWkmengReentry, String> {}
