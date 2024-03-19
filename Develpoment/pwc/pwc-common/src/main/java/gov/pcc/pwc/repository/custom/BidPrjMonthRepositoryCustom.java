package gov.pcc.pwc.repository.custom;

import gov.pcc.pwc.service.dto.BidPrjMonthDTO;

import java.util.List;
import java.util.Optional;

public interface BidPrjMonthRepositoryCustom {
    Optional<BidPrjMonthDTO> findLatestPrjMnth(String wkut, String prjno);

    List<BidPrjMonthDTO> findAllBidPrjMonth(String wkut, String prjno);


}
