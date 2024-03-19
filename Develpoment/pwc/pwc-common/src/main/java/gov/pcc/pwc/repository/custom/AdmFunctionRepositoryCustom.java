package gov.pcc.pwc.repository.custom;

import java.util.List;

import gov.pcc.pwc.service.dto.AdmFunctionDTO;

public interface AdmFunctionRepositoryCustom {
    List<AdmFunctionDTO> findAllByCriteria(AdmFunctionDTO criteria);
    List<AdmFunctionDTO> findAllFunctionOpts();
    void deleteByFunctionId(String functionId);
    int update(AdmFunctionDTO admFunctionDTO);
}
