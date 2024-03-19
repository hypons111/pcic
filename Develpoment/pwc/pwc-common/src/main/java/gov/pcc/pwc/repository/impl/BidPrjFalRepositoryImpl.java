package gov.pcc.pwc.repository.impl;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.repository.custom.BidPrjFalRepositoryCustom;
import gov.pcc.pwc.service.dto.BidPrjFalDTO;
import gov.pcc.pwc.service.dto.BidPrjMonthDTO;
import java.util.List;

public class BidPrjFalRepositoryImpl implements BidPrjFalRepositoryCustom {

    private final SqlExecutor sqlExecutor;
    public BidPrjFalRepositoryImpl(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    @Override
    public List<BidPrjFalDTO> findPrjMonthAllFal(String wkut, String prjno, String year, String month) {
                Query query = Query
                .builder()
                .append("select * from BID_PRJ_FAL bpl ")
                .append("WHERE bpl.WKUT = :wkut",wkut)
                .append("AND bpl.PRJNO = :prjno",prjno)
                .append("AND bpl.YEAR = :year",year)
                .append("AND bpl.MONTH = :month",month)
                .append("order by bpl.MFT DESC" )
                .build();

        return sqlExecutor.queryForList(query, BidPrjFalDTO.class);
    }
}
