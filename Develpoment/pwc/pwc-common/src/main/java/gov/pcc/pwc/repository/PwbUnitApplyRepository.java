package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.PwbUnitApply;
import gov.pcc.pwc.repository.custom.PwbUnitApplyRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the PwbUnitApply entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PwbUnitApplyRepository extends JpaRepository<PwbUnitApply, Long>, PwbUnitApplyRepositoryCustom, JpaSpecificationExecutor<PwbUnitApply> {}
