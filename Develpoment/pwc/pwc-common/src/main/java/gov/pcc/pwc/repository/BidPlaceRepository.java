package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.BidPlace;
import gov.pcc.pwc.repository.custom.BidPlaceRepositoryCustom;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the BidPlace entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BidPlaceRepository extends JpaRepository<BidPlace, String>, BidPlaceRepositoryCustom {}
