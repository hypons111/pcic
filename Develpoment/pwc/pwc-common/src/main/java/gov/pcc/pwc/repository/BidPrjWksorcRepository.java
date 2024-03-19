package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.BidPrjSegmset;
import gov.pcc.pwc.domain.BidPrjWksorc;
import gov.pcc.pwc.domain.BidPrjWksorcPrimaryKey;
import gov.pcc.pwc.domain.BidUnit10;
import gov.pcc.pwc.service.criteria.BidPrjEnviCriteria;
import gov.pcc.pwc.service.criteria.BidPrjWksorcCriteria;
import gov.pcc.pwc.service.dto.BidCcutDTO;
import gov.pcc.pwc.service.dto.BidPrjWksorcDTO;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data SQL repository for the BidPrjWksorc entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BidPrjWksorcRepository extends JpaRepository<BidPrjWksorc, BidPrjWksorcPrimaryKey>, JpaSpecificationExecutor<BidPrjWksorc> {
//    List<BidPrjWksorcDTO> findByWkutAndPrjno(BidPrjWksorcCriteria criteria);
List<BidPrjWksorc> findByWkutAndPrjno(String wkut, String prjno);
}
