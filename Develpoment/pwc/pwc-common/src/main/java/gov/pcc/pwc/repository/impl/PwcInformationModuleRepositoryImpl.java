package gov.pcc.pwc.repository.impl;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.domain.PwcInformationModule;
import gov.pcc.pwc.repository.custom.PwcInformationModuleRepositoryCustom;

import java.util.List;

public class PwcInformationModuleRepositoryImpl implements PwcInformationModuleRepositoryCustom {

    private final SqlExecutor sqlExecutor;

    public PwcInformationModuleRepositoryImpl(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    @Override
    public List<PwcInformationModule> findByInfoId(String id) {
        Query query = Query
                .builder()
                .append(
                        "SELECT * FROM PWC_INFORMATION_MODULE WHERE 1=1  " +
                                "AND PRO_INFORMATION_ID = :Id" , id )
                .build();
        return sqlExecutor.queryForList(query, PwcInformationModule.class);
    }
}
