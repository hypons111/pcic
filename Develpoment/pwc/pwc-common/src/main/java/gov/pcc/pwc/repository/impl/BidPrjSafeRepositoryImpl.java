package gov.pcc.pwc.repository.impl;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.repository.custom.BidPrjSafeRepositoryCustom;
import gov.pcc.pwc.service.criteria.BidPrjSafeCriteria;
import gov.pcc.pwc.service.dto.BidPrjSafeDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

public class BidPrjSafeRepositoryImpl implements BidPrjSafeRepositoryCustom {

    private final SqlExecutor sqlExecutor;

    public BidPrjSafeRepositoryImpl(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    @Override
    public Page<BidPrjSafeDTO> findAllBidSafeKey(BidPrjSafeCriteria criteria) {
        Query queryCount = Query
                .builder()
                .append("Select COUNT(*) FROM BID_PRJ_SAFE WHERE 1=1")
                .append("AND WKUT = :wkut",criteria.getWkut())
                .append("AND  PRJNO = :prjno",criteria.getPrjno())
                .appendWhen(criteria.getSdate() != null,"AND SDATE = :sdate",criteria.getSdate())
                .build();
      int totalCount = sqlExecutor.queryForList(queryCount, Integer.class).get(0);

        Query query = Query
                .builder()
                .append("SELECT  ROW_NUMBER() OVER (ORDER BY "+ criteria.getOrderBy()+ ") AS row_num,* FROM BID_PRJ_SAFE WHERE 1=1")
                .append("AND WKUT = :wkut",criteria.getWkut())
                .append("AND PRJNO = :prjno",criteria.getPrjno())
                .appendWhen(criteria.getSdate() != null,"AND SDATE = :sdate",criteria.getSdate())
                .append("order by " + criteria.getOrderBy())
                .append("offset :currentPage * :perPage rows fetch next :perPage rows only",
                        criteria.getCurrentPage(), criteria.getPerPage(), criteria.getPerPage())
                .build();
        List<BidPrjSafeDTO> bidPrjSafeDTOs = sqlExecutor.queryForList(query, BidPrjSafeDTO.class);
        Page<BidPrjSafeDTO> resultPage = new PageImpl<>(bidPrjSafeDTOs,criteria.toPageable(),totalCount);
        return resultPage;
    }
}
