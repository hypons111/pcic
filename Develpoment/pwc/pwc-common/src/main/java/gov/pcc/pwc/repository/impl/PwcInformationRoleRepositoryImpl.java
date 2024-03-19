package gov.pcc.pwc.repository.impl;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.domain.PwcInformationRole;
import gov.pcc.pwc.repository.custom.PwcInformationRoleRepositoryCustom;

import java.util.List;

public class PwcInformationRoleRepositoryImpl implements PwcInformationRoleRepositoryCustom {

    private final SqlExecutor sqlExecutor;

    public PwcInformationRoleRepositoryImpl(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    @Override
    public List<PwcInformationRole> findByInfoId(String id) {
        Query query = Query
                .builder()
                .append(
                        "SELECT * FROM PWC_INFORMATION_ROLE WHERE 1=1  " +
                                "AND PRO_INFORMATION_ID = :Id" , id )
                .build();
        return sqlExecutor.queryForList(query, PwcInformationRole.class);
    }
}
