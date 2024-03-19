package gov.pcc.pwc.repository.custom;

import gov.pcc.pwc.service.criteria.VwBidProjectCriteria;
import gov.pcc.pwc.service.dto.VwBidProjectDTO;
import org.springframework.data.domain.Page;

public interface VwBidProjectRepositoryCustom {
    Page<VwBidProjectDTO> findProjectByDesingOrSupervisionUnitName(VwBidProjectCriteria criteria);
}
