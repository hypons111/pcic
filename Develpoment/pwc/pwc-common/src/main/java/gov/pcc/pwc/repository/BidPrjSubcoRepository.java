package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.BidPrjModify;
import gov.pcc.pwc.domain.BidPrjSubco;
import gov.pcc.pwc.domain.BidPrjSubcoPrimaryKey;

import gov.pcc.pwc.domain.BidProjectUnit;
import gov.pcc.pwc.repository.custom.BidPrjSubcoRepositoryCustom;
import gov.pcc.pwc.service.dto.BidPrjSubcoDTO;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data SQL repository for the BidPrjSubco entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BidPrjSubcoRepository extends JpaRepository<BidPrjSubco, BidPrjSubcoPrimaryKey>, BidPrjSubcoRepositoryCustom,JpaSpecificationExecutor<BidPrjSubco> {

    List<BidPrjSubco> findByWkutAndPrjnoAndSubcoNo(String wkut,String prjno, String subcoNo);

}

