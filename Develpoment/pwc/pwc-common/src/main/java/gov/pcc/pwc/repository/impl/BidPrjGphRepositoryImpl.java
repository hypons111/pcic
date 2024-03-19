package gov.pcc.pwc.repository.impl;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.repository.custom.BidPrjGphRepositoryCustom;
import gov.pcc.pwc.service.criteria.BidPrjGphCriteria;

public class BidPrjGphRepositoryImpl implements BidPrjGphRepositoryCustom {

    private final SqlExecutor sqlExecutor;

    public BidPrjGphRepositoryImpl(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    @Override
    public int findMaxSeq(BidPrjGphCriteria criteria) {
        Query query = Query
                .builder()
                .append(" select MAX(SEQ) from BID_PRJ_GPH ")
                .append( " WHERE WKUT = :wkut ", criteria.getWkut())
                .append( " AND PRJNO = :prjno ", criteria.getPrjno())
                .append( " AND PHRASE = :phrase ", "0")
                .build();
        Integer iSeq = sqlExecutor.queryForList(query, Integer.class).get(0);
        if (iSeq == null) {
            iSeq = 0;
        }
        return iSeq;
    }
}
