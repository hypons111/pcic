package gov.pcc.pwc.repository.custom;

import gov.pcc.pwc.service.dto.EngMemberDTO;

public interface EngMemberRepositoryCustom {
    EngMemberDTO findByTypeAndIdno(String type, String idno);
}
