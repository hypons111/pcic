package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.GeoQuestion;
import gov.pcc.pwc.service.dto.GeoQuestionDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link GeoQuestion} and its DTO {@link GeoQuestionDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface GeoQuestionMapper extends EntityMapper<GeoQuestionDTO, GeoQuestion> {}
