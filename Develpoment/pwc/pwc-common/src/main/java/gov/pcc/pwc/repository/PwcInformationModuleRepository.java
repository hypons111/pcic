package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.PwcInformationModule;
import gov.pcc.pwc.repository.custom.PwcInformationModuleRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the PwcInformationModule entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PwcInformationModuleRepository extends JpaRepository<PwcInformationModule, Long>, PwcInformationModuleRepositoryCustom, JpaSpecificationExecutor<PwcInformationModule> {
}
