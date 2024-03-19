package gov.pcc.pwc.repository.custom;

import gov.pcc.pwc.service.dto.AdmAccountContactDTO;
import gov.pcc.pwc.service.dto.AdmAccountRoleDTO;

import java.util.List;

public interface AdmAccountRoleRepositoryCustom {
    void saveAdmAccountRoleList(AdmAccountContactDTO admAccountContractDTO);
    List<String> getRoleId(String moduleType);
    void deleteAccountRole(String userId);
    List<String> findAccountRoleId(AdmAccountContactDTO criteria);
    List<String> getUnitRoleId(String moduleType, String businessType, String roleType);
}
