package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.*;
import gov.pcc.pwc.service.dto.EngReviewLogDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link EngReviewLog} and its DTO {@link EngReviewLogDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EngReviewLogMapper extends EntityMapper<EngReviewLogDTO, EngReviewLog> {}
