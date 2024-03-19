package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.BidUnit10;
import gov.pcc.pwc.domain.CmsComp;
import gov.pcc.pwc.repository.custom.BidUnit10RepositoryCustom;
import gov.pcc.pwc.repository.custom.VwBidProjectRepositoryCustom;
import gov.pcc.pwc.service.dto.BidUnit10NameAndCodeDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the BidUnit10 entity.
 */
@Repository
public interface BidUnit10Repository
  extends JpaRepository<BidUnit10, String>, BidUnit10RepositoryCustom ,JpaSpecificationExecutor<BidUnit10> {
  @Query(
    "select new gov.pcc.pwc.service.dto.BidUnit10NameAndCodeDTO(b.code, b.name) from BidUnit10 b where b.name like CONCAT('%',:keyword,'%')"
  )
  public Page<BidUnit10NameAndCodeDTO> findNameAndCodeByKeyWord(
    @Param("keyword") String keyword,
    Pageable pageable
  );

  @Query(
    "select new gov.pcc.pwc.service.dto.BidUnit10NameAndCodeDTO(b.code, b.name) from BidUnit10 b order by b.code desc"
  )
  public Page<BidUnit10NameAndCodeDTO> findAllWithNameAndCode(
    Pageable pageable
  );

  Optional<BidUnit10> findByCode(String code);
}
