package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.GeoCompany;
import gov.pcc.pwc.service.dto.GeoCompanyDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link GeoCompany} and its DTO {@link GeoCompanyDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface GeoCompanyMapper extends EntityMapper<GeoCompanyDTO, GeoCompany> {}
