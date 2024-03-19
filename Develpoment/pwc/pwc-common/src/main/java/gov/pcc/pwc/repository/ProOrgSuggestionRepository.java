package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.ProOrgSuggestion;
import gov.pcc.pwc.repository.custom.ProOrgSuggestionRepositoryCustom;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ProOrgSuggestion entity.
 */
@SuppressWarnings("unused")
@Repository

public interface ProOrgSuggestionRepository extends JpaRepository<ProOrgSuggestion, Long>, ProOrgSuggestionRepositoryCustom {}

