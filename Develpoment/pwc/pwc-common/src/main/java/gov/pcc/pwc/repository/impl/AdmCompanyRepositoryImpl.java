package gov.pcc.pwc.repository.impl;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.repository.custom.AdmCompanyRepositoryCustom;
import gov.pcc.pwc.service.dto.AdmCompanyApplyDTO;
import gov.pcc.pwc.service.dto.AdmCompanyDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class AdmCompanyRepositoryImpl implements AdmCompanyRepositoryCustom {

    private final SqlExecutor sqlExecutor;

    public AdmCompanyRepositoryImpl(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    @Override
    public AdmCompanyApplyDTO findByUserId(String userId) {
        Query query = Query
                .builder()
                .append(" SELECT acc.STATUS, con.CH_NAME, con.WORK_TEL FROM ADM_ACCOUNT acc ")
                .append(" JOIN ADM_CONTACT con ON acc.USER_ID = con.USER_ID")
                .append(" WHERE 1=1 ")
                .append(" AND acc.USER_ID = :userId ", userId)
                .build();
        List<AdmCompanyApplyDTO> admCompanyApplyDTOs = sqlExecutor.queryForList(query, AdmCompanyApplyDTO.class);
        AdmCompanyApplyDTO result = null;
        if (admCompanyApplyDTOs.size() > 0) {
            result = admCompanyApplyDTOs.get(0);
        }

        return result;
    }

    @Override
    public Page<AdmCompanyDTO> findByAdmCompanyAll(String keywordName, Pageable pageable) {
        Query query = Query
                .builder()
                .append(" SELECT COMP_IDNO, NAME FROM ADM_COMPANY ac WHERE 1=1 ")
                .appendWhen(StringUtils.isNotBlank(keywordName), " AND NAME LIKE :name ", "%" + keywordName + "%" )
                .build();
        List<AdmCompanyDTO> admAgentDTOs = sqlExecutor.queryForList(query, AdmCompanyDTO.class);
        Page<AdmCompanyDTO> resultPage = new PageImpl<>(admAgentDTOs, pageable, admAgentDTOs.size());
        return resultPage;
    }
}
