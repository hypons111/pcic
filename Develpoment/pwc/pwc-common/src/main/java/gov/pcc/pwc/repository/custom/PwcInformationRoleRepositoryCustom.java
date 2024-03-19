package gov.pcc.pwc.repository.custom;

import gov.pcc.pwc.domain.PwcInformationRole;

import java.util.List;

public interface PwcInformationRoleRepositoryCustom {
    List<PwcInformationRole> findByInfoId(String id);
}
