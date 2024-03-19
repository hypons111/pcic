package gov.pcc.pwc.repository.custom;

import gov.pcc.pwc.domain.PwcInformationModule;

import java.util.List;

public interface PwcInformationModuleRepositoryCustom {
    List<PwcInformationModule> findByInfoId(String id);
}
