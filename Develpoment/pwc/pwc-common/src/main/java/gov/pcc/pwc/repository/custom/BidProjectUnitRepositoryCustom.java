package gov.pcc.pwc.repository.custom;

import gov.pcc.pwc.service.dto.BidProjectUnitDTO;

import java.util.List;

public interface BidProjectUnitRepositoryCustom {
    List<BidProjectUnitDTO> findBidCcutNames(String wkut, String prjno);
    List<BidProjectUnitDTO> findBidCcntNames(String wkut, String prjno);
    int findMaxSeq(String wkut, String prjno, String type);
}
