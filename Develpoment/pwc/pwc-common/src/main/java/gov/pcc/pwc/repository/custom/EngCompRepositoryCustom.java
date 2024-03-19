package gov.pcc.pwc.repository.custom;

import gov.pcc.pwc.service.criteria.Eng0822R1DTOCriteria;
import gov.pcc.pwc.service.dto.Eng0822R1DTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface EngCompRepositoryCustom {

    // 搜尋顧問公司聯絡資訊
    Page<Eng0822R1DTO> findEng0822R1(Eng0822R1DTOCriteria criteria);

    // 查詢主管機關
    Page<Eng0822R1DTO> findEng0703(Eng0822R1DTOCriteria criteria);

    // 檢視該主管機關資料
    Eng0822R1DTO viewEng0703(String id);

    // 編輯該主管機關資料
    Eng0822R1DTO update0703BySql(Eng0822R1DTO eng0822R1DTO);

    // 新增主管機關資料
    Eng0822R1DTO createEng0703(Eng0822R1DTO eng0822R1DTO);

    // 用userId取得自己公會資料
    Eng0822R1DTO viewEng0506(String id);

    // 編輯自己的公會資料
    Eng0822R1DTO updateEng0506(Eng0822R1DTO eng0822R1DTO);

    // 條件查詢公會資料
    Page<Eng0822R1DTO> findEng0502(Eng0822R1DTOCriteria criteria);

    // 檢視該公會資料
    public Eng0822R1DTO viewEng0502(String engCompId);

    // 業管編輯公會資料
    Eng0822R1DTO updateEng0502(Eng0822R1DTO eng0822R1DTO);

    // 業管新增公會資料
    Eng0822R1DTO createEng0502(Eng0822R1DTO eng0822R1DTO);

    // 技師公會通訊錄
    Page<Eng0822R1DTO> findEng0821R1(Eng0822R1DTOCriteria criteria);
}
