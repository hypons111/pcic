package gov.pcc.pwc.repository.custom;

import gov.pcc.pwc.domain.PwcInformationAppendix;
import gov.pcc.pwc.service.dto.PwcInformationAppendixDTO;
import gov.pcc.pwc.service.dto.PwcInformationDTO;

import java.util.List;

public interface PwcInformationAppendixRepositoryCustom {
    List<PwcInformationAppendixDTO> findByLink(PwcInformationDTO criteria);
    List<PwcInformationAppendixDTO> findByAttachment(PwcInformationDTO criteria);
    List<PwcInformationAppendix> findByCriteriaAll(PwcInformationDTO criteria);
    List<PwcInformationAppendixDTO> findByInfoId(String id);
}
