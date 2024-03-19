package gov.pcc.pwc.repository.custom;

import gov.pcc.pwc.service.dto.EngEngrExamDTO;

public interface EngEngrExamRepositoryCustom {
    EngEngrExamDTO findByIdnoAndSubject(String idno, String subject);
}
