package gov.pcc.pwc.repository.custom;

import gov.pcc.pwc.service.dto.BidPrjEquipDTO;

import java.util.List;

public interface BidPrjEquipRepositoryCustom {

    List<BidPrjEquipDTO> findPrjMonthAllEquip(String wkut, String prjno, String year, String month);


}
