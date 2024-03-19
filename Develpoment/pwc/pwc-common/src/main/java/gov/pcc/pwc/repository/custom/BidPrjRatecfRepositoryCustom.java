package gov.pcc.pwc.repository.custom;


import gov.pcc.pwc.service.criteria.BidPrjRatecfCriteria;
import gov.pcc.pwc.service.dto.AdmAgentDTO;
import gov.pcc.pwc.service.dto.BidPrjRatecfDTO;
import org.springframework.data.domain.Page;

import java.util.List;


public interface BidPrjRatecfRepositoryCustom {
    BidPrjRatecfDTO findByPk(String wkut, String prjno);
    Page<BidPrjRatecfDTO> findName(BidPrjRatecfCriteria criteria);
    List<AdmAgentDTO> findOrgId(String userId);

}
