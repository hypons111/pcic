package gov.pcc.pwc.repository.custom;

import gov.pcc.pwc.domain.PwcDownloadModule;

import java.util.List;

public interface PwcDownloadModuleRepositoryCustom {
    List<PwcDownloadModule> findByInfoId(String id);
}
