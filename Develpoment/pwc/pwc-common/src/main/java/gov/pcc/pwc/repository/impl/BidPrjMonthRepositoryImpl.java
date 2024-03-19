package gov.pcc.pwc.repository.impl;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.domain.BidPrjMonth;
import gov.pcc.pwc.repository.custom.BidPrjMonthRepositoryCustom;
import gov.pcc.pwc.service.dto.BidPrjMonthDTO;
import gov.pcc.pwc.service.mapper.BidPrjMonthMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class BidPrjMonthRepositoryImpl implements BidPrjMonthRepositoryCustom {

    private final SqlExecutor sqlExecutor;
    private final BidPrjMonthMapper bidPrjMonthMapper;
    public BidPrjMonthRepositoryImpl(SqlExecutor sqlExecutor, BidPrjMonthMapper bidPrjMonthMapper) {
        this.sqlExecutor = sqlExecutor;
        this.bidPrjMonthMapper = bidPrjMonthMapper;
    }

//    private final Logger log = LoggerFactory.getLogger();
    @Override
    public Optional<BidPrjMonthDTO> findLatestPrjMnth(String wkut, String prjno) {
        Query query = Query
                .builder()
                .append("select top 1 * from BID_PRJ_MONTH bpm ")
                .append("WHERE bpm.WKUT = :wkut",wkut)
                .append("AND bpm.PRJNO = :prjno",prjno)
                .append("order by bpm.syr,bpm.month DESC" )
                .build();
        return sqlExecutor.queryForList(query, BidPrjMonth.class).stream().map(bidPrjMonthMapper::toDto).findFirst();
    }

    @Override
    public List<BidPrjMonthDTO> findAllBidPrjMonth(String wkut, String prjno) {
        Query query = Query
                .builder()
                .append("select * from BID_PRJ_MONTH bpm ")
                .append("WHERE bpm.WKUT = :wkut",wkut)
                .append("AND bpm.PRJNO = :prjno",prjno)
                .append("order by bpm.syr,bpm.month DESC" )
                .build();

        return sqlExecutor.queryForList(query, BidPrjMonthDTO.class);
    }
}
