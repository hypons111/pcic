package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.TecPage;
import gov.pcc.pwc.service.dto.TecPageDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link TecPage} and its DTO {@link TecPageDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TecPageMapper extends EntityMapper<TecPageDTO, TecPage> {}
