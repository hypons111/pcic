package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.AdmCodeTable;
import gov.pcc.pwc.domain.AdmRole;
import gov.pcc.pwc.repository.custom.AdmCodeTableRepositoryCustom;
import gov.pcc.pwc.repository.custom.AdmRoleRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data SQL repository for the AdmCodeTable entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdmCodeTableRepository extends JpaRepository<AdmCodeTable, Long>, AdmCodeTableRepositoryCustom, JpaSpecificationExecutor<AdmCodeTable> {
   AdmCodeTable findByCategoryAndCode(String category, String code);
}
