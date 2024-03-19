package gov.pcc.pwc.repository.custom;

import gov.pcc.pwc.service.criteria.ProInnovativeProductProUserCriteria;
import gov.pcc.pwc.service.dto.*;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

//Repository(介面) 類似於DAO的接口 在這邊是為了客製化自己的Repository和SERVICE的設計雷同
public interface ProInnovativeProductRepositoryCustom {
    List<CommunicateProductQueryDTO> findByCriteria(CommunicateProductQueryDTO criteria);
    //使用ProInnovativeDTO為Controller接收與傳遞的物件
    List<ProInnovativeProductProUserDTO> findAllProInnovativeProducts(ProInnovativeProductProUserCriteria criteria);

    Page<ProInnovativeProductProUserDTO> findPageAllProInnovativeProducts(ProInnovativeProductProUserCriteria criteria);

    List<ProInnovativeProductProUserDTO> findAllBackendProInnovativeProducts(ProInnovativeProductProUserCriteria criteria);

    ProInnovativeProductSumSearchDetailDTO findproInnovativeProductAndDetail(ProInnovativeProductProUserCriteria criteria);

    Page<ProInnovativeProductProUserDTO> findPageBackendAllProInnovativeProducts(ProInnovativeProductProUserCriteria criteria);

    ProApplyCompanyDTO findApplyCompany(String userId);



}
