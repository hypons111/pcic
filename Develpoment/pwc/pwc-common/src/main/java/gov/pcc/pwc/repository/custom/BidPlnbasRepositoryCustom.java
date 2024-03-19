package gov.pcc.pwc.repository.custom;

import gov.pcc.pwc.service.criteria.BidPlnbasCriteria;
import gov.pcc.pwc.service.dto.BidPlnbasDTO;
import org.springframework.data.domain.Page;

public interface BidPlnbasRepositoryCustom {

    Page<BidPlnbasDTO> findAllByCriteria(BidPlnbasCriteria criteria);
}
