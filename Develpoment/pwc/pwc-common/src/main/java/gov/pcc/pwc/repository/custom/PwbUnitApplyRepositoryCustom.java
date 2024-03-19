package gov.pcc.pwc.repository.custom;

import gov.pcc.pwc.service.criteria.PwbRoleApproveForUnitCriteria;
import gov.pcc.pwc.service.criteria.PwbUnitApplyCriteria;
import gov.pcc.pwc.service.dto.PwbUnitApplyDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PwbUnitApplyRepositoryCustom {
    Page<PwbUnitApplyDTO> findUnitApply(PwbUnitApplyDTO criteria);
    Page<PwbUnitApplyDTO> findByApproveCriteria(PwbRoleApproveForUnitCriteria criteria);
    Page<PwbUnitApplyDTO> findApplyList(PwbUnitApplyCriteria criteria);
    List<String> getUnitApplyRoleId(String moduleType, String businessType);
    void cancelUnitRole(String unitId);
}
