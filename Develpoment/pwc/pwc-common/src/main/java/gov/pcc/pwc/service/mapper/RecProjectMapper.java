package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.RecProject;
import gov.pcc.pwc.service.dto.RecProjectDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link RecProject} and its DTO {@link RecProjectDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RecProjectMapper extends EntityMapper<RecProjectDTO, RecProject> {}
