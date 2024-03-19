package gov.pcc.pwc.repository.custom;

import gov.pcc.pwc.service.criteria.AdmAccountContactCriteria;
import gov.pcc.pwc.service.criteria.UserManagementCriteria;
import gov.pcc.pwc.service.dto.AdmAccountContactDTO;
import gov.pcc.pwc.service.dto.UserManagementDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AdmAccountRepositoryCustom {
    List<AdmAccountContactDTO> findAllByCriteria(AdmAccountContactDTO criteria);
    Page<AdmAccountContactDTO> findAllByCriteria(AdmAccountContactCriteria criteria);
    Long getBiggestNo();
    AdmAccountContactDTO findByUserId(AdmAccountContactDTO criteria);
    List<AdmAccountContactDTO> findUserIdByCriteria(AdmAccountContactDTO criteria);
    List<String> findAccountUserId(AdmAccountContactDTO criteria);
    Page<UserManagementDTO> findAccountInfo(UserManagementCriteria criteria);
}
