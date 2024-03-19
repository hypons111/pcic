package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.RecArea;
import gov.pcc.pwc.service.dto.RecAreaDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link RecArea} and its DTO {@link RecAreaDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RecAreaMapper extends EntityMapper<RecAreaDTO, RecArea> {}
