package gov.pcc.pwc.repository.custom;

import gov.pcc.pwc.service.criteria.EngEngrLicenseCriteria;
import gov.pcc.pwc.service.dto.EngEngrLicenseDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EngEngrLicenseRepositoryCustom {
    List<EngEngrLicenseDTO> findByCriteriaImpl(EngEngrLicenseCriteria criteria);

    Page<EngEngrLicenseDTO> findByCriteriaImpl2(EngEngrLicenseCriteria criteria);
}
