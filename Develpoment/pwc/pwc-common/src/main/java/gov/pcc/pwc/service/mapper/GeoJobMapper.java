package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.GeoJob;
import gov.pcc.pwc.service.dto.GeoJobDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link GeoJob} and its DTO {@link GeoJobDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface GeoJobMapper extends EntityMapper<GeoJobDTO, GeoJob> {}
