package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.VwBidProject;
import gov.pcc.pwc.service.dto.VwBidProjectDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link VwBidProject} and its DTO {@link VwBidProjectDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface VwBidProjectMapper extends EntityMapper<VwBidProjectDTO, VwBidProject> {}
