package gov.pcc.pwc.repository.custom;

import gov.pcc.pwc.service.criteria.AdmCodeTableCriteria;
import gov.pcc.pwc.service.dto.AdmCodeTableDTO;
import org.springframework.data.domain.Page;

public interface AdmCodeTableRepositoryCustom {
    Page<AdmCodeTableDTO> findAllByCriteria(AdmCodeTableCriteria criteria);
    AdmCodeTableDTO findByPk(Long id);
    int update(AdmCodeTableDTO dto);
}
