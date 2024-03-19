package gov.pcc.pwc.repository.custom;

import gov.pcc.pwc.service.criteria.BidGovCheckCriteria;
import gov.pcc.pwc.service.dto.BidGovCheckDTO;
import org.springframework.data.domain.Page;

public interface BidGovCheckRepositoryCustom {
    Page<BidGovCheckDTO> findAllByCriteria(BidGovCheckCriteria criteria);
}
