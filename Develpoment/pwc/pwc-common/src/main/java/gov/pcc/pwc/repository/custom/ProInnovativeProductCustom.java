package gov.pcc.pwc.repository.custom;

import gov.pcc.pwc.service.dto.AdmCodeTableDTO;
import gov.pcc.pwc.service.dto.CommunicateProductQueryDTO;

import java.util.List;

public interface ProInnovativeProductCustom {
    List<CommunicateProductQueryDTO> findByCriteria(CommunicateProductQueryDTO criteria);
}
