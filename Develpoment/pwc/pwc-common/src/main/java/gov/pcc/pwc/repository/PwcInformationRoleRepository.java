package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.PwcInformationRole;
import gov.pcc.pwc.repository.custom.PwcInformationRoleRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the PwcInformationRole entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PwcInformationRoleRepository extends JpaRepository<PwcInformationRole, Long>, PwcInformationRoleRepositoryCustom, JpaSpecificationExecutor<PwcInformationRole> {
}