package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.BidProjectUnit;
import gov.pcc.pwc.domain.BidProjectUnitPrimaryKey;
import gov.pcc.pwc.repository.custom.BidProjectUnitRepositoryCustom;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data SQL repository for the BidProjectUnit entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BidProjectUnitRepository
  extends
    JpaRepository<BidProjectUnit, BidProjectUnitPrimaryKey>,
    BidProjectUnitRepositoryCustom,
    JpaSpecificationExecutor<BidProjectUnit> {
    List<BidProjectUnit> findByWkutAndPrjnoAndType(String wkut, String name, String type);
}
