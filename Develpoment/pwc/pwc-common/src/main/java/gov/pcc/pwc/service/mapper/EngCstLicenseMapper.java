package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.EngCstLicense;
import gov.pcc.pwc.service.dto.EngCstLicenseDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link EngCstLicense} and its DTO {@link EngCstLicenseDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EngCstLicenseMapper extends EntityMapper<EngCstLicenseDTO, EngCstLicense> {}
