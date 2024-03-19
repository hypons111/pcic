package gov.pcc.pwc.repository.custom;

import gov.pcc.pwc.service.dto.BidProjectBaseDTO;
import gov.pcc.pwc.service.dto.BidProjectInfoDTO;
import gov.pcc.pwc.service.dto.BidUnit10DTO;

import java.util.List;
import java.util.Optional;

public interface BidProjectRepositoryCustom {
    Optional<BidProjectInfoDTO> findBidProjectInfoByWkutAndPrjno(String wkut, String prjno);
    List<BidUnit10DTO> findProjectUnit(String wkut, String prjno, String type);
    BidProjectBaseDTO findBidProjectBaseByWkutAndPrjno(String wkut, String prjno);
}
