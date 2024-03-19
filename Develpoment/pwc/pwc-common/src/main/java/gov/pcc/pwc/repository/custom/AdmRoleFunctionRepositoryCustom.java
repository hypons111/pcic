package gov.pcc.pwc.repository.custom;

import java.util.List;

import gov.pcc.pwc.service.dto.AdmFunctionDTO;

public interface AdmRoleFunctionRepositoryCustom {
    List<AdmFunctionDTO> findByRoleId(String roleId);
}
