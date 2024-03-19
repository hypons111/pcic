package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.RecTyphoon;
import gov.pcc.pwc.service.dto.RecTyphoonDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link RecTyphoon} and its DTO {@link RecTyphoonDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RecTyphoonMapper extends EntityMapper<RecTyphoonDTO, RecTyphoon> {}
