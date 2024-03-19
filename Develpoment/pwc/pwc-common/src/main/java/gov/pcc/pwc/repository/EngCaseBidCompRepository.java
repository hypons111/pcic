package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.EngCaseBidComp;
import gov.pcc.pwc.repository.custom.EngCaseBidCompRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface EngCaseBidCompRepository  extends JpaRepository<EngCaseBidComp, String>, EngCaseBidCompRepositoryCustom, JpaSpecificationExecutor<EngCaseBidComp> {
}
