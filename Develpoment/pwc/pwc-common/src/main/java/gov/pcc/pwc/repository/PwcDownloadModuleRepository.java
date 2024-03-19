package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.PwcDownloadModule;
import gov.pcc.pwc.repository.custom.PwcDownloadModuleRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the PwcDownloadModule entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PwcDownloadModuleRepository extends JpaRepository<PwcDownloadModule, Long>, PwcDownloadModuleRepositoryCustom, JpaSpecificationExecutor<PwcDownloadModule> {}
