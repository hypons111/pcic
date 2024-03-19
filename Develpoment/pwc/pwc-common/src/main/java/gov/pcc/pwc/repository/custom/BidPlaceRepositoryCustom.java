package gov.pcc.pwc.repository.custom;

import gov.pcc.pwc.service.criteria.BidPlaceCriteria;
import gov.pcc.pwc.service.dto.BidPlaceDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BidPlaceRepositoryCustom {
    List<BidPlaceDTO> findMapInfoByPlaceCode(String placeCode);
    Page<BidPlaceDTO> findProjectCountByCriteria(BidPlaceCriteria criteria);
}
