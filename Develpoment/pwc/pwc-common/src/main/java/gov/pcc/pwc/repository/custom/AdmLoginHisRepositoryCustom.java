package gov.pcc.pwc.repository.custom;

import gov.pcc.pwc.service.criteria.AdmLoginHisCriteria;
import gov.pcc.pwc.service.dto.AdmLoginHisDTO;
import org.springframework.data.domain.Page;

public interface AdmLoginHisRepositoryCustom {
    Page<AdmLoginHisDTO> findAllByCriteria(AdmLoginHisCriteria criteria);
    AdmLoginHisDTO findByPk(long id);
}
