package gov.pcc.pwc.repository.custom;

import gov.pcc.pwc.service.criteria.BidUnit10Criteria;
import gov.pcc.pwc.service.dto.BidUnit10DTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BidUnit10RepositoryCustom {
    List<BidUnit10DTO> findBidPrjResponsName(String code);

    List<BidUnit10DTO> findBidManageUnit(String wkutCode);

    Page<BidUnit10DTO> findAllByCriteria(BidUnit10Criteria criteria);
}
