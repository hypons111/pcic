package gov.pcc.pwc.repository.custom;

import gov.pcc.pwc.service.criteria.BidMdlbasCriteria;
import gov.pcc.pwc.service.dto.BidMdlbasDTO;

import java.util.List;

public interface BidMdlbasRepositoryCustom {
    List<BidMdlbasDTO> findNotyBudgByCriteria(BidMdlbasCriteria criteria);
}
