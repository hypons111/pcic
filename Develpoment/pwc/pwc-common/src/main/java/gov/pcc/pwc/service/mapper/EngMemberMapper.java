package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.*;
import gov.pcc.pwc.service.dto.EngMemberDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link EngMember} and its DTO {@link EngMemberDTO}.
 */
@Mapper(componentModel = "spring")
public interface EngMemberMapper extends EntityMapper<EngMemberDTO, EngMember> {}
