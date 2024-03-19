package gov.pcc.pwc.repository.custom;



import gov.pcc.pwc.service.criteria.GeoJobCriteria;
import gov.pcc.pwc.service.dto.Geo0607DTO;
import gov.pcc.pwc.service.dto.GeoJobDTO;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface GeoJobRepositoryCustom {
    Page<GeoJobDTO> findAllByCriteria(GeoJobCriteria criteria);
    Page<GeoJobDTO> findByWorkPlace(GeoJobCriteria criteria);
    Page<Geo0607DTO> findJobCounts(GeoJobCriteria criteria);
    List<Geo0607DTO> exportJobCounts(LocalDate firstMonth, String jobKind);
}
