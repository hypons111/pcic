package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.RecProject;
import gov.pcc.pwc.repository.custom.RecProjectRepositoryCustom;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the RecProject entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RecProjectRepository extends JpaRepository<RecProject, String>, JpaSpecificationExecutor<RecProject>, RecProjectRepositoryCustom {}
