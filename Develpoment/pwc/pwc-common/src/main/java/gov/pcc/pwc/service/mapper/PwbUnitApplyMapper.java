package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.PwbUnitApply;
import gov.pcc.pwc.service.dto.PwbUnitApplyDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PwbUnitApply} and its DTO {@link PwbUnitApplyDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PwbUnitApplyMapper extends EntityMapper<PwbUnitApplyDTO, PwbUnitApply> {}
