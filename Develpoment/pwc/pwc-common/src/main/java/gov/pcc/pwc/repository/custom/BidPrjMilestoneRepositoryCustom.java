package gov.pcc.pwc.repository.custom;

import gov.pcc.pwc.service.criteria.BidPrjMilestoneCriteria;
import gov.pcc.pwc.service.dto.BidPrjMilestoneDTO;
import org.springframework.data.domain.Page;


public interface BidPrjMilestoneRepositoryCustom {
    Page<BidPrjMilestoneDTO> findAllByCriteria(BidPrjMilestoneCriteria criteria);
    void insertBidPrjMilestone(BidPrjMilestoneDTO bidPrjMilestoneDTO);
}
