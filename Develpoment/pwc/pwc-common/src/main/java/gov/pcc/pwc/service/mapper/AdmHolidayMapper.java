package gov.pcc.pwc.service.mapper;

import gov.pcc.pwc.domain.AdmHoliday;
import gov.pcc.pwc.service.dto.AdmHolidayDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link gov.pcc.pwc.domain.AdmHoliday} and its DTO {@link gov.pcc.pwc.service.dto.AdmHolidayDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AdmHolidayMapper extends EntityMapper<AdmHolidayDTO, AdmHoliday> {}
