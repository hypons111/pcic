package gov.pcc.pwc.repository.impl;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.repository.custom.EngCaseRepositoryCustom;
import gov.pcc.pwc.service.criteria.EngCaseTotalDTOCriteria;
import gov.pcc.pwc.service.dto.AdmSysCodeDTO;
import gov.pcc.pwc.service.dto.EngCaseTotalDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.List;

public class EngCaseRepositoryimpl implements EngCaseRepositoryCustom {

    @Autowired
    private SqlExecutor sqlExecutor;

//    public EngCaseRepositoryimpl(SqlExecutor sqlExecutor) {
//        this.sqlExecutor = sqlExecutor;
//    }

    private final Logger log = LoggerFactory.getLogger(EngCompRepositoryImpl.class);

//    @Override
//    public Page<EngCaseTotalDTO> findEngCaseTotal(EngCaseTotalDTOCriteria criteria) {
//        return null;
//    }


//    @Override
//    public List<AdmSysCodeDTO> findAdmSysCode() {
//        System.out.println("有進來嗎");
//        Query query = Query.builder()
//                .append("SELECT * FROM [PWC_DB].[dbo].[ADM_SYS_CODE]")
//                .append(" WHERE [DATA_TYPE] = 'PROJECT_TYPE'")
//                .append(" AND [MODULE_TYPE] = 'ENG'")
//                .build();
//        List<AdmSysCodeDTO> list = sqlExecutor.queryForList(query, AdmSysCodeDTO.class);
//        log.debug("EngCaseImp-getProjectTypes" + list);
//        return list;
//    }
}
