package gov.pcc.pwc.repository.custom;

import gov.pcc.pwc.service.criteria.GeoQuestionCriteria;
import gov.pcc.pwc.service.dto.AdmSysCodeDTO;
import gov.pcc.pwc.service.dto.GeoMemberDTO;
import gov.pcc.pwc.service.dto.GeoQuestionDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface GeoQuestionRepositoryCustom {

    Page<GeoQuestionDTO> findAllByCriteriaImpl(GeoQuestionCriteria criteria);

    int insert(GeoQuestionDTO dto);

    List<AdmSysCodeDTO> findAdmSysCodeByCriteriaImpl(GeoQuestionCriteria criteria);

    List<GeoMemberDTO> findGeoMemberIdByCriteriaImpl(GeoQuestionCriteria criteria);

    void updateAdmSysCodeByCriteriaImpl(GeoQuestionCriteria criteria);
}
