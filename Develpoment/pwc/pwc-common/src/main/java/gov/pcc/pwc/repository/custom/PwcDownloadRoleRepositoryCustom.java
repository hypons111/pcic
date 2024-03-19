package gov.pcc.pwc.repository.custom;

import gov.pcc.pwc.domain.PwcDownloadRole;

import java.util.List;

public interface PwcDownloadRoleRepositoryCustom {
    List<PwcDownloadRole> findByInfoId(String id);
}
