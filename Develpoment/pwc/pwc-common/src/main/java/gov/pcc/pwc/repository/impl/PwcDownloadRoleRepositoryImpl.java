package gov.pcc.pwc.repository.impl;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.domain.PwcDownloadRole;
import gov.pcc.pwc.repository.custom.PwcDownloadRoleRepositoryCustom;

import java.util.List;

public class PwcDownloadRoleRepositoryImpl implements PwcDownloadRoleRepositoryCustom {

    private final SqlExecutor sqlExecutor;

    public PwcDownloadRoleRepositoryImpl(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    @Override
    public List<PwcDownloadRole> findByInfoId(String id) {
        Query query = Query
                .builder()
                .append(
                        "SELECT * FROM PWC_DOWNLOAD_ROLE WHERE 1=1  " +
                                "AND PRO_DOWNLOAD_ID = :Id" , id )
                .build();
        return sqlExecutor.queryForList(query, PwcDownloadRole.class);
    }
}
