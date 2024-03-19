package gov.pcc.pwc.repository.custom;

import gov.pcc.pwc.service.criteria.EngCaseTotalDTOCriteria;
import gov.pcc.pwc.service.dto.EngCaseTotalDTO;
import org.springframework.data.domain.Page;

public interface EngCaseBidCompRepositoryCustom {

    // 查詢簽證案件資料
    Page<EngCaseTotalDTO> EngCaseTotalDTOR1(EngCaseTotalDTOCriteria criteria);
}
