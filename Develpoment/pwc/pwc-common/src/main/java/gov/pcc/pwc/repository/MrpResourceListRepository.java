package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.MrpResourceList;
import gov.pcc.pwc.repository.custom.MrpResourceListRepositoryCustom;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Spring Data SQL repository for the MrpResourceList entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MrpResourceListRepository extends JpaRepository<MrpResourceList, Long>, MrpResourceListRepositoryCustom, JpaSpecificationExecutor<MrpResourceList> {
    public Map<String, Object> queryKPI();
}
