package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.AdmCompany;
import gov.pcc.pwc.service.dto.AdmCompanyDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link AdmCompany} and its DTO {@link AdmCompanyDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AdmCompanyMapper extends EntityMapper<AdmCompanyDTO, AdmCompany> {}
