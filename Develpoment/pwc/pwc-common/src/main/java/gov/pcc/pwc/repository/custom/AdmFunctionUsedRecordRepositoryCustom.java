package gov.pcc.pwc.repository.custom;

import gov.pcc.pwc.service.criteria.AdmFunctionUsedRecordCriteria;
import gov.pcc.pwc.service.dto.AdmFunctionUsedRecordDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AdmFunctionUsedRecordRepositoryCustom {
    Page<AdmFunctionUsedRecordDTO> findAllByCriteria(AdmFunctionUsedRecordCriteria criteria);
    Page<AdmFunctionUsedRecordDTO> findAllStatisticsByCriteria(AdmFunctionUsedRecordCriteria criteria);
    List<AdmFunctionUsedRecordDTO> findAllStatisticsInfoByCriteria(AdmFunctionUsedRecordCriteria criteria);
    AdmFunctionUsedRecordDTO findByPk(long id);
}
