package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.AdmSysCode;
import gov.pcc.pwc.service.dto.AdmSysCodeDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link AdmSysCode} and its DTO {@link AdmSysCodeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AdmSysCodeMapper extends EntityMapper<AdmSysCodeDTO, AdmSysCode> {}
