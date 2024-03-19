package gov.pcc.pwc.repository.custom;

import gov.pcc.pwc.service.criteria.AdmSysCodeCriteria;
import gov.pcc.pwc.service.dto.AdmSysCodeDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AdmSysCodeRepositoryCustom {
    Page<AdmSysCodeDTO> findAllByCriteria(AdmSysCodeCriteria criteria);
    AdmSysCodeDTO findByPk(String moduleType, String dataType, String dataKey);
    List<AdmSysCodeDTO> findAllDataTypeOpts();
    int update(AdmSysCodeDTO dto);
}
