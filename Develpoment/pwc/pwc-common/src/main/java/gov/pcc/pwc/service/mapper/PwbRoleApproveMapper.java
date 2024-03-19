package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.PwbRoleApprove;
import gov.pcc.pwc.service.dto.PwbRoleApproveDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PwbRoleApprove} and its DTO {@link PwbRoleApproveDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PwbRoleApproveMapper extends EntityMapper<PwbRoleApproveDTO, PwbRoleApprove> {}
