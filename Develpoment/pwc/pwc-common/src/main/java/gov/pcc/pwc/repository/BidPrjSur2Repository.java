package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.BidPrjSur2;
import gov.pcc.pwc.domain.BidPrjSur2PrimaryKey;
import gov.pcc.pwc.repository.custom.BidPrjSur2RepositoryCustom;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the BidPrjSur2 entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BidPrjSur2Repository extends JpaRepository<BidPrjSur2, BidPrjSur2PrimaryKey>, BidPrjSur2RepositoryCustom {}
