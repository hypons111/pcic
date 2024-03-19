package gov.pcc.pwc.repository.custom;

import gov.pcc.pwc.service.criteria.BidPrjStcCriteria;
import gov.pcc.pwc.service.dto.BidPrjStcDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BidPrjStcRepositoryCustom {
   List<BidPrjStcDTO> findBidAae013DTOByCriteria(BidPrjStcCriteria criteria);
}
