package gov.pcc.pwc.repository.custom;

import java.util.List;

import gov.pcc.pwc.service.dto.AdmRoleDTO;

public interface AdmRoleRepositoryCustom {
    List<AdmRoleDTO> findAllByCriteria(AdmRoleDTO criteria);
    //    Page<AdmAccountContactDTO> findAllByCriteria(AdmAccountContactCriteria criteria);
    List<AdmRoleDTO> findAllRoleOpts();
    AdmRoleDTO findByRoleId(String roleId);
}
