package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.BidProjectPrimaryKey;
import gov.pcc.pwc.domain.VwBidProject;
import gov.pcc.pwc.repository.custom.VwBidProjectRepositoryCustom;
import gov.pcc.pwc.repository.impl.VwBidProjectRepositoryImpl;
import gov.pcc.pwc.service.dto.VwBidProjectWkutDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

/**
 * Spring Data SQL repository for the VwBidProject entity.
 */
@Repository
public interface VwBidProjectRepository extends JpaRepository<VwBidProject, BidProjectPrimaryKey>, JpaSpecificationExecutor<VwBidProject>, VwBidProjectRepositoryCustom {

    @Query("select new gov.pcc.pwc.service.dto.VwBidProjectWkutDTO(b.wkut, b.wkutName) from VwBidProject b where b.isAdd92 = :add92" +
            " and SUBSTRING(b.typeCode, 1, 1) != '9'" +
            " and (b.aokDate is null or b.aokDate >= :certainDate )")
    public Page<VwBidProjectWkutDTO> findWkutNameAndCodeByAdd92(@Param("add92") String add92, @Param("certainDate") LocalDate certainDate, Pageable pageable);
}
