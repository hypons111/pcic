package gov.pcc.pwc.repository.custom;


import gov.pcc.pwc.service.criteria.BidPrjEnviCriteria;
import gov.pcc.pwc.service.dto.BidCcutDTO;
import gov.pcc.pwc.service.dto.BidPrjEnviDTO;

import java.util.List;


public interface BidPrjEnviRepositoryCustom {
    List<BidPrjEnviDTO> findByPk(String wkut, String prjno);
    List<BidCcutDTO> findctUnitByPk(BidPrjEnviCriteria criteria);
//    Page<BidPrjEnviDTO> findAll(BidPrjEnviCriteria criteria);


}
