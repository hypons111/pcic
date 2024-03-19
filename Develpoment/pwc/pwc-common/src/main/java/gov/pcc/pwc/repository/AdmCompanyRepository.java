package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.AdmCompany;
import gov.pcc.pwc.repository.custom.AdmCompanyRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the AdmCompany entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdmCompanyRepository extends JpaRepository<AdmCompany, String>, AdmCompanyRepositoryCustom {}
