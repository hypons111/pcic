package gov.pcc.pwc.repository.custom;

import gov.pcc.pwc.service.criteria.AdmFunctionClickCriteria;
import gov.pcc.pwc.service.criteria.AdmFunctionUsedRecordCriteria;
import gov.pcc.pwc.service.dto.AdmFunctionClickDTO;
import gov.pcc.pwc.service.dto.AdmFunctionUsedRecordDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AdmFunctionClickRepositoryCustom {
    Page<AdmFunctionClickDTO> findAllByCriteria(AdmFunctionClickCriteria criteria);
    Page<AdmFunctionClickDTO> findAllStatisticsByCriteria(AdmFunctionClickCriteria criteria);
    List<AdmFunctionClickDTO> findAllStatisticsInfoByCriteria(AdmFunctionClickCriteria criteria);
}
