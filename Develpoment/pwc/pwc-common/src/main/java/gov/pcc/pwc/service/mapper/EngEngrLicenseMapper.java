package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.EngEngrLicense;
import gov.pcc.pwc.service.dto.EngEngrLicenseDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link EngEngrLicense} and its DTO {@link EngEngrLicenseDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EngEngrLicenseMapper extends EntityMapper<EngEngrLicenseDTO, EngEngrLicense> {}
