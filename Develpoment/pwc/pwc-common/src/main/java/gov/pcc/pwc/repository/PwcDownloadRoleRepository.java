package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.PwcDownloadRole;
import gov.pcc.pwc.repository.custom.PwcDownloadRoleRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the PwcDownloadRole entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PwcDownloadRoleRepository extends JpaRepository<PwcDownloadRole, Long>, PwcDownloadRoleRepositoryCustom, JpaSpecificationExecutor<PwcDownloadRole>{}
