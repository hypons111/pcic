package gov.pcc.pwc.repository.custom;

import gov.pcc.pwc.service.criteria.EngEngrSubjectpointCriteria;
import gov.pcc.pwc.service.dto.EngEngrSubjectpointDTO;
import org.springframework.data.domain.Page;

public interface EngEngrSubjectpointRepositoryCustom {
    Page<EngEngrSubjectpointDTO> findAllByCriteria(EngEngrSubjectpointCriteria criteria);
    EngEngrSubjectpointDTO findDTOById(String id);
}
