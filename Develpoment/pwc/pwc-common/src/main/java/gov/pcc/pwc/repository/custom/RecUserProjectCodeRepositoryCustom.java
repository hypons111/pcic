package gov.pcc.pwc.repository.custom;

import gov.pcc.pwc.service.criteria.RecUserProjectCodeCriteria;
import gov.pcc.pwc.service.dto.*;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RecUserProjectCodeRepositoryCustom {
    List<RecOrgRoleDTO> findAllRecOrgByQuery();
    List<RecAdmRoleDTO> findAllRecRoleByQuery();
    Page<RecUserProjectCodeQueryDTO> findUserByCriteria(RecUserProjectCodeCriteria criteria);
    List<RecProjectCodesDTO> findAccessRecProjectCodesByQuery(String loginId, String roleId, String orgId);
    List<String> findSelectedProjectCodesByQuery(String userId);
    void insertUserProjectCodesByQuery (RecUserProjectCodeDTO recUserProjectCodeDTO);
}
