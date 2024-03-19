package gov.pcc.pwc.repository.impl;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.repository.custom.BidMdlbasRepositoryCustom;
import gov.pcc.pwc.service.criteria.BidMdlbasCriteria;
import gov.pcc.pwc.service.dto.BidMdlbasDTO;

import java.util.List;

public class BidMdlbasRepositoryImpl implements BidMdlbasRepositoryCustom {
    private final SqlExecutor sqlExecutor;

    public BidMdlbasRepositoryImpl(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    @Override
    public List<BidMdlbasDTO> findNotyBudgByCriteria(BidMdlbasCriteria criteria) {
        Query query = Query
                .builder()
                .append("SELECT * FROM BID_MDLBAS a JOIN BID_PROJECT b ON a.WKUT = b.WKUT WHERE 1 = 1")
                .append("AND  b.WKUT LIKE :wkut",criteria.getWkut().substring(1,5)+"%")
                .append("OR b.WKUT LIKE :wkut",criteria.getWkut().substring(1,3)+"00%")
                .append("AND a.DIS_NO LIKE 'b.IS_ADD92%'")
                .append("AND a.NOTY_BUDG > 0")
                .build();
        System.out.println("@@@@@@ Query : "+query);
        return  sqlExecutor.queryForList(query,BidMdlbasDTO.class);
    }
}
