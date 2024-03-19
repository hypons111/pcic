package gov.pcc.pwc.repository.custom;

import gov.pcc.pwc.service.criteria.AdmHolidayCriteria;
import gov.pcc.pwc.service.dto.AdmHolidayDTO;
import org.springframework.data.domain.Page;

public interface AdmHolidayRepositoryCustom {
    Page<AdmHolidayDTO> findAllByCriteria(AdmHolidayCriteria criteria);
}
