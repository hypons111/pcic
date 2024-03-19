package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.EngCase;
import gov.pcc.pwc.service.dto.EngCaseDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link EngCase} and its DTO {@link EngCaseDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EngCaseMapper extends EntityMapper<EngCaseDTO, EngCase> {}
