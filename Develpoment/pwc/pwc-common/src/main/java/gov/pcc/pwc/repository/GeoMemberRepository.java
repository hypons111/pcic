package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.GeoMember;
import gov.pcc.pwc.repository.custom.GeoMemberRepositoryCustom;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data SQL repository for the GeoMember entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GeoMemberRepository extends JpaRepository<GeoMember, String>,JpaSpecificationExecutor<GeoMember>, GeoMemberRepositoryCustom {

    public Optional<GeoMember> findByUserId(String userid);
}
