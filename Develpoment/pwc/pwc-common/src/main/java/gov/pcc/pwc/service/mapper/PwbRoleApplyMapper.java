package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.PwbRoleApply;
import gov.pcc.pwc.service.dto.PwbRoleApplyDTO;
import gov.pcc.pwc.service.dto.PwbRoleDTO;
import org.mapstruct.*;

import java.util.List;

/**
 * Mapper for the entity {@link PwbRoleApply} and its DTO {@link PwbRoleApplyDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PwbRoleApplyMapper extends EntityMapper<PwbRoleApplyDTO, PwbRoleApply> {
    PwbRoleApply toEntity(PwbRoleDTO pwbRoleDTO);
}
