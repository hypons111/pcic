package gov.pcc.pwc.repository.custom;

import gov.pcc.pwc.service.criteria.PwbRoleApproveCriteria;
import gov.pcc.pwc.service.criteria.PwbRoleModuleCriteria;
import gov.pcc.pwc.service.dto.PwbRoleApproveDTO;
import gov.pcc.pwc.service.dto.PwbRoleDTO;
import gov.pcc.pwc.service.dto.PwbRoleModuleDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PwbRoleModuleRepositoryCustom {
    List<String> findAllBusinessType();
    List<PwbRoleModuleDTO> findRoleId();
    Page<PwbRoleModuleDTO> findSearchList(PwbRoleModuleCriteria pwbRoleModuleCriteria);
    List<PwbRoleModuleDTO> findRoleIdByModule(PwbRoleModuleCriteria criteria);
    void deleteModule (PwbRoleModuleDTO pwbRoleModuleDTO);
    List<PwbRoleModuleDTO> findAllRoleId(PwbRoleModuleCriteria criteria);
}
