package gov.pcc.pwc.repository.custom;

import gov.pcc.pwc.service.dto.PwbRoleApplyDTO;

import java.util.List;

public interface PwbRoleApplyRepositoryCustom {
    List<PwbRoleApplyDTO> findRoleApply(PwbRoleApplyDTO criteria);

}
