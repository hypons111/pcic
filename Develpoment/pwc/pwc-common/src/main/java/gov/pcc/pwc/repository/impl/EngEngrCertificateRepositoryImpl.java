package gov.pcc.pwc.repository.impl;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.repository.custom.EngEngrCertificateRepositoryCustom;

public class EngEngrCertificateRepositoryImpl implements EngEngrCertificateRepositoryCustom {

    private final SqlExecutor sqlExecutor;

    public EngEngrCertificateRepositoryImpl(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    @Override
    public int clearDocNoByEngrCertificateDocNo(String engrCertificateDocNo) {
        Query query = Query
                .builder()
                .append("UPDATE [PWC_DB].[dbo].[ENG_ENGR_CERTIFICATE]")
                .append("SET [ENGR_CERTIFICATE_DOC_NO] = NULL")
                .append("WHERE [ENGR_CERTIFICATE_DOC_NO] = :engrCertificateDocNo", engrCertificateDocNo)
                .build();
        return sqlExecutor.update(query);
    }
}
