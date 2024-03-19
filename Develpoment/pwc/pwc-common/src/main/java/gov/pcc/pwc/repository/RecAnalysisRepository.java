package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.RecAnalysis;
import gov.pcc.pwc.repository.custom.RecAnalysisRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the RecProject entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RecAnalysisRepository extends JpaRepository<RecAnalysis, String>, RecAnalysisRepositoryCustom, JpaSpecificationExecutor<RecAnalysis> {}
