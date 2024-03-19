package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.TecContent;
import gov.pcc.pwc.service.dto.TecContentDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link TecContent} and its DTO {@link TecContentDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TecContentMapper extends EntityMapper<TecContentDTO, TecContent> {}
