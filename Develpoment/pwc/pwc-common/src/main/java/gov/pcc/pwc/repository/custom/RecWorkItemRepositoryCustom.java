package gov.pcc.pwc.repository.custom;

import gov.pcc.pwc.service.criteria.RecWorkItemCriteria;
import gov.pcc.pwc.service.dto.RecWorkItemDTO;
import gov.pcc.pwc.service.genSerialNumber.errors.TypeVariableNotMatchException;
import org.springframework.data.domain.Page;

public interface RecWorkItemRepositoryCustom {
    Page<RecWorkItemDTO> findAllByCriteria(RecWorkItemCriteria criteria);
    RecWorkItemDTO packPkAndLogColumn (RecWorkItemDTO recWorkItemDTO) throws TypeVariableNotMatchException;
}
