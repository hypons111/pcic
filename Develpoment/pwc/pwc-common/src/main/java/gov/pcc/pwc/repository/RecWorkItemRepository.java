package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.RecWorkItem;
import gov.pcc.pwc.repository.custom.RecWorkItemRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the RecWorkItem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RecWorkItemRepository extends JpaRepository<RecWorkItem, String>, RecWorkItemRepositoryCustom, JpaSpecificationExecutor<RecWorkItem> {}
