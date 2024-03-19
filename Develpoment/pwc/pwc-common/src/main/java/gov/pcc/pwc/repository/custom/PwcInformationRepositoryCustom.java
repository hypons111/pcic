package gov.pcc.pwc.repository.custom;

import gov.pcc.pwc.service.dto.PwcInformationDTO;

import java.util.List;

public interface PwcInformationRepositoryCustom {
    List<PwcInformationDTO> findByInformation(PwcInformationDTO criteria);
    int deleteByID(PwcInformationDTO criteria);
    List<PwcInformationDTO> findInfoByCriteria(PwcInformationDTO criteria);
    Long findTypeByUserId(PwcInformationDTO criteria);
}
