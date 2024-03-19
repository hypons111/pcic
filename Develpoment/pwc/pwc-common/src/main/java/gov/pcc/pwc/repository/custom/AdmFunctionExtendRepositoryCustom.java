package gov.pcc.pwc.repository.custom;

import java.util.List;

import gov.pcc.pwc.service.dto.AdmFunctionExtendDTO;

public interface AdmFunctionExtendRepositoryCustom {
    List<AdmFunctionExtendDTO> findAllByCriteria(AdmFunctionExtendDTO criteria);
    List<AdmFunctionExtendDTO> findAllFunctionExtendOpts();
    void deleteByFunctionExtendId(String functionExtendId);
    int update(AdmFunctionExtendDTO admFunctionExtendDTO);
}
