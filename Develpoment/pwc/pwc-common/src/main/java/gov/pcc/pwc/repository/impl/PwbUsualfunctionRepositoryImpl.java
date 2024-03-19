package gov.pcc.pwc.repository.impl;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.domain.AdmFunction;
import gov.pcc.pwc.repository.custom.PwbUsualfunctionRepositoryCustom;
import gov.pcc.pwc.service.dto.PwcPengingViewDTO;

import java.util.List;

public class PwbUsualfunctionRepositoryImpl implements PwbUsualfunctionRepositoryCustom {

    private final SqlExecutor sqlExecutor;

    public PwbUsualfunctionRepositoryImpl(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    @Override
    public List<AdmFunction> getUsualfunctionFunctionName(String id) {
        Query query = Query
                .builder()
                .append(" SELECT af.Function_Id, af.Function_Name ")
                .append(" FROM PWB_USUALFUNCTION pu ")
                .append(" JOIN ADM_FUNCTION af ON pu.Function_Id = af.Function_Id ")
                .append(" WHERE 1=1 ")
                .append(" AND pu.USER_ID = :userId ", id)
                .build();
        return sqlExecutor.queryForList(query, AdmFunction.class);
    }

    @Override
    public List<AdmFunction> getUsualfunctionFunctionNameDefault(String id) {
        Query query = Query
                .builder()
                .append(" SELECT DISTINCT af.Function_Id, af.Function_Name ")
                .append(" FROM ADM_ACCOUNT aa ")
                .append(" JOIN ADM_ACCOUNT_ROLE aar ON aa.USER_ID = aar.User_Id ")
                .append(" JOIN ADM_ROLE_FUNCTION arf ON aar.Role_Id = arf.Role_Id ")
                .append(" JOIN ADM_FUNCTION af ON arf.Function_Id = af.Function_Id ")
                .append(" WHERE 1=1 ")
                .append(" AND aa.USER_ID = :userId ", id)
                .build();
        return sqlExecutor.queryForList(query, AdmFunction.class);
    }

    @Override
    public List<PwcPengingViewDTO> getPengingViewForUserid(String id) {

        Query query = Query
                .builder()
                .append(" SELECT CONCAT(aa.PCC_ORG_ID, aa.COMP_IDNO) AS UNIT_ID, CONCAT(aa2.ORG_NAME, ac2.NAME) AS UNIT_NAME, ")
                .append(" ppv.USER_ID, ac.CH_NAME, ppv.BUSINESS_TYPE, ppv.CASE_NUM ")
                .append(" FROM PWC_PENGING_VIEW ppv " )
                .append(" JOIN ADM_ACCOUNT aa ON aa.USER_ID = ppv.USER_ID ")
                .append(" JOIN ADM_CONTACT ac ON ac.USER_ID = aa.USER_ID ")
                .append(" LEFT JOIN ADM_AGENT aa2 ON aa2.PCC_ORG_ID = aa.PCC_ORG_ID ")
                .append(" LEFT JOIN ADM_COMPANY ac2 ON ac2.COMP_IDNO = aa.COMP_IDNO ")
                .append(" WHERE 1=1 ")
                .append(" AND ppv.USER_ID = :userId ", id)
                .build();

        List<PwcPengingViewDTO> pwcPengingViewDTOs = sqlExecutor.queryForList(query, PwcPengingViewDTO.class);
        return pwcPengingViewDTOs;
    }
}
