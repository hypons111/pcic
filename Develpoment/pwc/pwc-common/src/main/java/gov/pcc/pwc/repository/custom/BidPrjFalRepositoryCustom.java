package gov.pcc.pwc.repository.custom;

import gov.pcc.pwc.service.dto.BidPrjFalDTO;
import gov.pcc.pwc.service.dto.BidPrjMonthDTO;

import java.util.List;
import java.util.Optional;

public interface BidPrjFalRepositoryCustom {

    List<BidPrjFalDTO> findPrjMonthAllFal(String wkut, String prjno,String year,String month);


}
