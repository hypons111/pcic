package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.BidProject;
import gov.pcc.pwc.service.dto.BidProjectDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link BidProject} and its DTO {@link BidProjectDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BidProjectMapper extends EntityMapper<BidProjectDTO, BidProject> {}
