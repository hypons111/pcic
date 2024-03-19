package gov.pcc.pwc.repository.impl;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.repository.custom.BidPrjPay2RepositoryCustom;
import gov.pcc.pwc.service.criteria.BidPrjPay2Criteria;
import gov.pcc.pwc.service.dto.BidPrjPay2DTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

public class BidPrjPay2RepositoryImpl implements BidPrjPay2RepositoryCustom {

    private final SqlExecutor sqlExecutor;

    public BidPrjPay2RepositoryImpl(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    @Override
    public Page<BidPrjPay2DTO> findBidPrjPay2ByCriteria(BidPrjPay2Criteria criteria) {
        Query queryCount = Query
                .builder()
                .append("Select COUNT(*) FROM BID_PRJ_PAY2 WHERE 1=1")
                .append("AND WKUT = :wkut",criteria.getWkut())
                .append("AND  PRJNO = :prjno",criteria.getPrjno())
                .build();
        int totalCount = sqlExecutor.queryForList(queryCount, Integer.class).get(0);

        Query query = Query
                .builder()
                .append("SELECT  ROW_NUMBER() OVER (ORDER BY "+ criteria.getOrderBy()+ ") AS row_num,* FROM BID_PRJ_PAY2 WHERE 1=1")
                .append("AND WKUT = :wkut",criteria.getWkut())
                .append("AND PRJNO = :prjno",criteria.getPrjno())
                .append("order by " + criteria.getOrderBy())
                .append("offset :currentPage * :perPage rows fetch next :perPage rows only",
                        criteria.getCurrentPage(), criteria.getPerPage(), criteria.getPerPage())
                .build();
        List<BidPrjPay2DTO> bidPrjPay2DTOS = sqlExecutor.queryForList(query, BidPrjPay2DTO.class);
        Page<BidPrjPay2DTO> resultPage = new PageImpl<>(bidPrjPay2DTOS,criteria.toPageable(),totalCount);
        return resultPage;
    }
}
