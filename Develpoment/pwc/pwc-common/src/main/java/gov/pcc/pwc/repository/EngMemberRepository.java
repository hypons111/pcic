package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.CmsComp;
import gov.pcc.pwc.domain.EngMember;
import gov.pcc.pwc.repository.custom.EngMemberRepositoryCustom;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the EngMember entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EngMemberRepository extends JpaRepository<EngMember, String>, EngMemberRepositoryCustom, JpaSpecificationExecutor<EngMember> {}
