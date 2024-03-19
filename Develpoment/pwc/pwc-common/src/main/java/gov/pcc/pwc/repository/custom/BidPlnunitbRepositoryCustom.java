package gov.pcc.pwc.repository.custom;

import gov.pcc.pwc.service.criteria.BidPlnunitbCriteria;
import gov.pcc.pwc.service.dto.BidPlnunitbDTO;

import java.util.List;

public interface BidPlnunitbRepositoryCustom {

    List<BidPlnunitbDTO> findAllByCriteria(BidPlnunitbCriteria criteria);
}
