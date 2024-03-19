package gov.pcc.pwc.repository.custom;

import gov.pcc.pwc.service.criteria.PwbRoleApproveCriteria;
import gov.pcc.pwc.service.dto.PwbRoleDTO;
import org.springframework.data.domain.Page;
import gov.pcc.pwc.service.dto.PwbRoleApproveDTO;

import java.util.List;

public interface PwbRoleApproveRepositoryCustom {
    List<String> findByUserId(String userId);
    Page<PwbRoleDTO> findByCriteria(PwbRoleApproveCriteria criteria);
    List<PwbRoleApproveDTO> findUserId(String moduleType);
    List<PwbRoleApproveDTO> findBusinessUserId(String moduleType, String businessType);
    List<String> findByUserIdForAgency(String userId);
    Page<PwbRoleDTO> findApproveList(PwbRoleApproveCriteria criteria);
    List<String> findApproveModuleType();
    List<PwbRoleDTO> findApproveUserId();
}
