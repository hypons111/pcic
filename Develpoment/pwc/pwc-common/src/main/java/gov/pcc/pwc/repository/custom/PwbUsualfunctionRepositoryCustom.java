package gov.pcc.pwc.repository.custom;

import gov.pcc.pwc.domain.AdmFunction;
import gov.pcc.pwc.service.dto.PwcPengingViewDTO;

import java.util.List;

public interface PwbUsualfunctionRepositoryCustom {
    List<AdmFunction> getUsualfunctionFunctionName(String id);
    List<AdmFunction> getUsualfunctionFunctionNameDefault(String id);
    List<PwcPengingViewDTO> getPengingViewForUserid(String id);
}
