package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.EngComp;
import gov.pcc.pwc.repository.custom.EngCompRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the EngComp entity.
 */
@Repository
public interface EngCompRepository extends JpaRepository<EngComp, String>, EngCompRepositoryCustom, JpaSpecificationExecutor<EngComp> {
    public EngComp getByEngCompId(String engCompId);
}
