package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.*;
import gov.pcc.pwc.service.dto.GeoMemberDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link GeoMember} and its DTO {@link GeoMemberDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface GeoMemberMapper extends EntityMapper<GeoMemberDTO, GeoMember> {}
