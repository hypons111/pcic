package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.AdmContact;
import gov.pcc.pwc.service.dto.AdmContactDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link AdmContact} and its DTO {@link AdmContactDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AdmContactMapper extends EntityMapper<AdmContactDTO, AdmContact> {}
