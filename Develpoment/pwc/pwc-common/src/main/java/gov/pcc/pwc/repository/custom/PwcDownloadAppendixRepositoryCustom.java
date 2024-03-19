package gov.pcc.pwc.repository.custom;

import gov.pcc.pwc.domain.PwcDownloadAppendix;
import gov.pcc.pwc.service.dto.PwcDownloadAppendixDTO;
import gov.pcc.pwc.service.dto.PwcDownloadDTO;

import java.util.List;

public interface PwcDownloadAppendixRepositoryCustom {
    List<PwcDownloadAppendixDTO> findByLink(PwcDownloadDTO criteria);
    List<PwcDownloadAppendixDTO> findByAttachment(PwcDownloadDTO criteria);
    List<PwcDownloadAppendix> findByCriteriaAll(PwcDownloadDTO criteria);
    List<PwcDownloadAppendixDTO> findByInfoId(String id);
}
