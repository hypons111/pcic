package gov.pcc.pwc.repository.custom;

import gov.pcc.pwc.service.criteria.RecTyphoonCriteria;
import gov.pcc.pwc.service.dto.RecTyphoonDTO;
import gov.pcc.pwc.service.dto.RecTyphoonQueryDTO;
import gov.pcc.pwc.service.genSerialNumber.errors.TypeVariableNotMatchException;
import org.springframework.data.domain.Page;

public interface RecTyphoonRepositoryCustom {
    Page<RecTyphoonQueryDTO> findAllByCriteria(RecTyphoonCriteria criteria);
    RecTyphoonDTO packPkAndLogColumn (RecTyphoonDTO recTyphoonDTO) throws TypeVariableNotMatchException;
}
