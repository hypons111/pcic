package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.PwcDownloadAppendix;
import gov.pcc.pwc.repository.custom.PwcDownloadAppendixRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the PwcDownloadAppendix entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PwcDownloadAppendixRepository extends JpaRepository<PwcDownloadAppendix, Long>, PwcDownloadAppendixRepositoryCustom, JpaSpecificationExecutor<PwcDownloadAppendix> {}
