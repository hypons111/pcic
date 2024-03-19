package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.RecUserProjectCode;
import gov.pcc.pwc.service.dto.RecUserProjectCodeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link RecUserProjectCode} and its DTO {@link RecUserProjectCodeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RecUserProjectCodeMapper extends EntityMapper<RecUserProjectCodeDTO, RecUserProjectCode> {}
