package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.PwcInformation;
import gov.pcc.pwc.repository.custom.PwcInformationRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the PwcInformation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PwcInformationRepository extends JpaRepository<PwcInformation, Long>, PwcInformationRepositoryCustom, JpaSpecificationExecutor<PwcInformation> {}
