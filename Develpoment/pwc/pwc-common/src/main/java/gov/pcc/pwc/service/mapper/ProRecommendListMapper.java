package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.ProRecommendList;
import gov.pcc.pwc.service.dto.ProRecommendListDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProRecommendList} and its DTO {@link ProRecommendListDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProRecommendListMapper extends EntityMapper<ProRecommendListDTO, ProRecommendList> {}
