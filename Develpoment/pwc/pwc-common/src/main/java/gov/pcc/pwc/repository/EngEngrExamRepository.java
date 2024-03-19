package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.EngEngrExam;
import gov.pcc.pwc.repository.custom.EngEngrExamRepositoryCustom;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the EngEngrExam entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EngEngrExamRepository extends JpaRepository<EngEngrExam, String>, EngEngrExamRepositoryCustom, JpaSpecificationExecutor<EngEngrExam> {}
