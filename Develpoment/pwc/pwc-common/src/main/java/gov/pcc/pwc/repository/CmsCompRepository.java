package gov.pcc.pwc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import gov.pcc.pwc.domain.CmsComp;
import gov.pcc.pwc.repository.custom.CmsCompRepositoryCustom;

/**
 * Spring Data SQL repository for the CmsComp entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CmsCompRepository extends JpaRepository<CmsComp, String>, CmsCompRepositoryCustom, JpaSpecificationExecutor<CmsComp> {}
