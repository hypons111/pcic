package gov.pcc.pwc.repository.custom;

import gov.pcc.pwc.service.criteria.RecAreaCriteria;
import gov.pcc.pwc.service.dto.RecAreaDTO;
import gov.pcc.pwc.service.genSerialNumber.errors.TypeVariableNotMatchException;
import org.springframework.data.domain.Page;

public interface RecAreaRepositoryCustom {
    Page<RecAreaDTO> findAllByCriteria(RecAreaCriteria criteria);
    RecAreaDTO packPkAndLogColumn (RecAreaDTO recAreaDTO) throws TypeVariableNotMatchException;
}
