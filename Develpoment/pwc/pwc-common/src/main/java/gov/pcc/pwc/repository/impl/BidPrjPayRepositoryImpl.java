package gov.pcc.pwc.repository.impl;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.repository.custom.BidPrjPayRepositoryCustom;
import gov.pcc.pwc.service.criteria.BidPrjPayCriteria;
import org.apache.commons.lang3.StringUtils;

public class BidPrjPayRepositoryImpl implements BidPrjPayRepositoryCustom {

    private final SqlExecutor sqlExecutor;

    public BidPrjPayRepositoryImpl(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    @Override
    public int findMaxMonNo(BidPrjPayCriteria criteria) {
        Query query = Query
                .builder()
                .append(" select MAX(MON_NO) from BID_PRJ_PAY ")
                .append( " WHERE WKUT = :wkut ", criteria.getWkut())
                .append( " AND PRJNO = :prjno ", criteria.getPrjno())
                .append( " AND ISNUMERIC(MON_NO) = '1' ")
                .build();
        String sMaxMonNo = sqlExecutor.queryForList(query, String.class).get(0);
        int iMaxMonNo = 0;
        if (StringUtils.isNotBlank(sMaxMonNo)) {
            iMaxMonNo = Integer.parseInt(sMaxMonNo);
        }
        return iMaxMonNo;
    }
}
