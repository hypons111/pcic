package gov.pcc.pwc.repository.custom;

import gov.pcc.pwc.service.dto.AdmUnitDTO;

import java.util.List;

public interface AdmUnitRepositoryCustom {
    List<AdmUnitDTO> findAllUnitOpts();
}
