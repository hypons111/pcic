package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.AdmLoginHis;
import gov.pcc.pwc.repository.custom.AdmLoginHisRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the AdmLoginHis entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdmLoginHisRepository extends JpaRepository<AdmLoginHis, Long>, AdmLoginHisRepositoryCustom, JpaSpecificationExecutor<AdmLoginHis> {}
