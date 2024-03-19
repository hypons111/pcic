package gov.pcc.pwc.repository.custom;

import gov.pcc.pwc.service.dto.AdmRoleDTO;
import java.util.List;

public interface PwbUnitRoleRepositoryCustom {
    List<AdmRoleDTO> findAllRoleOpts(String unitId);
    void deleteUnitRole(String userId);
}
