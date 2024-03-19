package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.BidKpifin;
import gov.pcc.pwc.domain.BidKpifinPrimaryKey;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the BidKpifin entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BidKpifinRepository extends JpaRepository<BidKpifin, BidKpifinPrimaryKey>,JpaSpecificationExecutor<BidKpifin> {}
