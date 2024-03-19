package gov.pcc.pwc.repository.custom;

import gov.pcc.pwc.service.dto.TecPageContentDTO;
import gov.pcc.pwc.service.dto.TecInfoContentDTO;

import java.util.List;

public interface TecPageRepositoryCustom {
    List<TecPageContentDTO> findTopQuery();
    TecInfoContentDTO findTecInfoQuery();
    TecInfoContentDTO findLawRefQuery();
}
