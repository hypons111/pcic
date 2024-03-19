package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.AdmFunctionClick;
import gov.pcc.pwc.repository.custom.AdmFunctionClickRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the AdmFunctionClick entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdmFunctionClickRepository extends JpaRepository<AdmFunctionClick, Long>, AdmFunctionClickRepositoryCustom, JpaSpecificationExecutor<AdmFunctionClick> {}
