package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.BidPrjScequip;
import gov.pcc.pwc.domain.BidPrjScequipPrimaryKey;
import gov.pcc.pwc.domain.BidPrjSubco;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data SQL repository for the BidPrjScequip entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BidPrjScequipRepository extends JpaRepository<BidPrjScequip, BidPrjScequipPrimaryKey>,JpaSpecificationExecutor<BidPrjScequip> {



    List<BidPrjScequip> findByWkutAndPrjnoAndSubCcut(String wkut, String prjno, String subCcut);

    List<BidPrjScequip> deleteByWkutAndPrjnoAndSubCcut(String wkut, String prjno, String subCcut);



}
