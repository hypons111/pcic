package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.AdmAgent;
import gov.pcc.pwc.repository.custom.AdmAgentRepositoryCustom;
import gov.pcc.pwc.service.dto.BidUnit10NameAndCodeDTO;
import gov.pcc.pwc.service.dto.ProAdmAgentOrgNameDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the AdmAgent entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdmAgentRepository extends JpaRepository<AdmAgent, String>, AdmAgentRepositoryCustom, JpaSpecificationExecutor<AdmAgent> {

// ITREE SELECT查詢
    @Query("select new gov.pcc.pwc.service.dto.ProAdmAgentOrgNameDTO(b.orgId, b.orgName) from AdmAgent b where b.orgName like CONCAT('%',:keyword,'%')")
    public Page<ProAdmAgentOrgNameDTO> findOrgIdAndOrgNameByKeyWord(@Param("keyword")String keyword, Pageable pageable);


}




