package gov.pcc.pwc.repository.custom;

import java.util.List;

import gov.pcc.pwc.service.dto.CmsCompDTO;

public interface CmsCompRepositoryCustom {
    List<CmsCompDTO> findAllByCriteria(CmsCompDTO criteria);
}
