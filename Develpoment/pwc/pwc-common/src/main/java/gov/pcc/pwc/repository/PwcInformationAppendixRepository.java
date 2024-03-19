package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.PwcInformationAppendix;
import gov.pcc.pwc.repository.custom.PwcInformationAppendixRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the PwcInformationAppendix entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PwcInformationAppendixRepository extends JpaRepository<PwcInformationAppendix, Long>, PwcInformationAppendixRepositoryCustom, JpaSpecificationExecutor<PwcInformationAppendix> {}
