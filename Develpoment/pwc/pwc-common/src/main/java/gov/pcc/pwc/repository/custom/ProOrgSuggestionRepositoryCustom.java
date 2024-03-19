package gov.pcc.pwc.repository.custom;


import gov.pcc.pwc.service.criteria.ProOrgSuggestionHistoryCriteria;
import gov.pcc.pwc.service.dto.ProOrgSuggestionHistoryDTO;
import gov.pcc.pwc.service.criteria.ProOrgSuggestionCriteria;
import gov.pcc.pwc.service.dto.ComStatusSearchDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProOrgSuggestionRepositoryCustom {

    List<ProOrgSuggestionHistoryDTO> findAllByCriteria(ProOrgSuggestionHistoryDTO criteria);
    Page<ProOrgSuggestionHistoryDTO> findAllByCriteria(ProOrgSuggestionHistoryCriteria criteria);
    List<ComStatusSearchDTO> findAllByCriteria(ProOrgSuggestionCriteria criteria);
    Page<ComStatusSearchDTO> findPageByCriteria(ProOrgSuggestionCriteria criteria);

}
