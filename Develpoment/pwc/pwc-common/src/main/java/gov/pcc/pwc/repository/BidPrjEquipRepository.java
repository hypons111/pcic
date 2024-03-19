package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.BidPrjEquip;
import gov.pcc.pwc.domain.BidPrjEquipPrimaryKey;
import gov.pcc.pwc.domain.BidPrjMonthPrimaryKey;
import gov.pcc.pwc.repository.custom.BidPrjEquipRepositoryCustom;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the BidPrjEquip entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BidPrjEquipRepository extends JpaRepository<BidPrjEquip, BidPrjEquipPrimaryKey>, BidPrjEquipRepositoryCustom {}
