package gov.pcc.pwc.repository.custom;




import gov.pcc.pwc.service.criteria.GeoMemberCriteria;
import gov.pcc.pwc.service.dto.GeoMemberDTO;
import org.springframework.data.domain.Page;

public interface GeoMemberRepositoryCustom {
    Page<GeoMemberDTO> findAllByCriteriaImpl(GeoMemberCriteria criteria);
}
