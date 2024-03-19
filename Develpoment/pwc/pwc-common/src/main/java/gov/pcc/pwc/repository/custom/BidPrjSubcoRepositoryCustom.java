package gov.pcc.pwc.repository.custom;

import gov.pcc.pwc.domain.BidPrjSubcoPrimaryKey;
import gov.pcc.pwc.domain.BidProjectPrimaryKey;
import gov.pcc.pwc.service.criteria.BidProjectUnitCriteria;
import gov.pcc.pwc.service.dto.BidPrjSubcoDTO;
import gov.pcc.pwc.service.dto.BidProjectDTO;
import java.util.List;

public interface BidPrjSubcoRepositoryCustom {

    List<BidPrjSubcoDTO> findBidPrjSubco(BidPrjSubcoPrimaryKey bidPrjSubcoPrimaryKey);
    List<BidPrjSubcoDTO> gatAllMet(BidProjectPrimaryKey bidProjectPrimaryKey);

    List<BidProjectDTO> findBidProject(BidProjectDTO bidPrjSubcoDTO);

    List<BidProjectUnitCriteria> findBidCcntNames(String wkut, String prjno);

    List<BidPrjSubcoDTO> insertBidPrjSubco(BidPrjSubcoDTO bidPrjSubcoDTO);

    List<BidPrjSubcoDTO> findBidProjectAndProjectUnit(BidPrjSubcoPrimaryKey bidPrjSubcoPrimaryKey);

    public int findBidPrjSubcoCount(BidPrjSubcoPrimaryKey bidPrjSubcoPrimaryKey);

    List<BidProjectDTO> gatAllProject(BidPrjSubcoPrimaryKey bidPrjSubcoPrimaryKey);

}
