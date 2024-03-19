package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.TecPage;
import gov.pcc.pwc.repository.custom.TecPageRepositoryCustom;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TecPage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TecPageRepository extends JpaRepository<TecPage, Long>, TecPageRepositoryCustom, JpaSpecificationExecutor<TecPage> {}
