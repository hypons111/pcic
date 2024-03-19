package gov.pcc.pwc.repository.custom;

import gov.pcc.pwc.service.dto.PwcDownloadDTO;

import java.util.List;

public interface PwcDownloadRepositoryCustom {
    List<PwcDownloadDTO> findByDownload(PwcDownloadDTO criteria);
    int deleteByID(PwcDownloadDTO criteria);
    List<PwcDownloadDTO> findDownByCriteria(PwcDownloadDTO criteria);
}
