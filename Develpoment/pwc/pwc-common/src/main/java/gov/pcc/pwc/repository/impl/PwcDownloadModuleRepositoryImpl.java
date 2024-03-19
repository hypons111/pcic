package gov.pcc.pwc.repository.impl;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.domain.PwcDownloadModule;
import gov.pcc.pwc.repository.custom.PwcDownloadModuleRepositoryCustom;

import java.util.List;

public class PwcDownloadModuleRepositoryImpl implements PwcDownloadModuleRepositoryCustom {

    private final SqlExecutor sqlExecutor;

    public PwcDownloadModuleRepositoryImpl(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    @Override
    public List<PwcDownloadModule> findByInfoId(String id) {
                Query query = Query
                .builder()
                .append(
                        "SELECT * FROM PWC_DOWNLOAD_MODULE WHERE 1=1  " +
                                "AND PRO_DOWNLOAD_ID = :Id" , id )
                .build();
        return sqlExecutor.queryForList(query, PwcDownloadModule.class);
    }


}
