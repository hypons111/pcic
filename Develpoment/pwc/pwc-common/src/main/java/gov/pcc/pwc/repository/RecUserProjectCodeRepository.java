package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.RecUserProjectCode;
import gov.pcc.pwc.repository.custom.RecUserProjectCodeRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the RecUserProjectCode entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RecUserProjectCodeRepository extends JpaRepository<RecUserProjectCode, String>,JpaSpecificationExecutor<RecUserProjectCode>, RecUserProjectCodeRepositoryCustom {}
