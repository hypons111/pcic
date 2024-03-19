package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.PwbRoleModule;
import gov.pcc.pwc.service.dto.PwbRoleModuleDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PwbRoleModule} and its DTO {@link PwbRoleModuleDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PwbRoleModuleMapper extends EntityMapper<PwbRoleModuleDTO, PwbRoleModule> {}
