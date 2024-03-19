package gov.pcc.pwc.repository.custom;


import gov.pcc.pwc.service.criteria.BidPrjSur2Criteria;
import gov.pcc.pwc.service.dto.BidPrjSur2DTO;
import org.springframework.data.domain.Page;

public interface BidPrjSur2RepositoryCustom {
    Page<BidPrjSur2DTO> findByCriteria(BidPrjSur2Criteria bidPrjSur2Criteria);
}
