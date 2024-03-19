package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.RecArea;
import gov.pcc.pwc.repository.custom.RecAreaRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the RecProject entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RecAreaRepository extends JpaRepository<RecArea, String>, RecAreaRepositoryCustom, JpaSpecificationExecutor<RecArea> {}
