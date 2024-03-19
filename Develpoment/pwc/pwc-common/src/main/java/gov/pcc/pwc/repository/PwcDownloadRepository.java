package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.PwcDownload;
import gov.pcc.pwc.repository.custom.PwcDownloadRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the PwcDownload entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PwcDownloadRepository extends JpaRepository<PwcDownload, Long>, PwcDownloadRepositoryCustom, JpaSpecificationExecutor<PwcDownload> {}
