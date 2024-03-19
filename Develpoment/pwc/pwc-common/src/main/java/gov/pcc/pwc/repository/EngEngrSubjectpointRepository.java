package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.EngEngrSubjectpoint;
import gov.pcc.pwc.repository.custom.EngEngrSubjectpointRepositoryCustom;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the EngEngrSubjectpoint entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EngEngrSubjectpointRepository extends JpaRepository<EngEngrSubjectpoint, String>, EngEngrSubjectpointRepositoryCustom {}
