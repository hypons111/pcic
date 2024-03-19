package gov.pcc.pwc.repository.impl;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.repository.custom.BidPrjSur2RepositoryCustom;
import gov.pcc.pwc.service.criteria.BidPrjSur2Criteria;
import gov.pcc.pwc.service.dto.BidPrjSur2DTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

public class BidPrjSur2RepositoryImpl implements BidPrjSur2RepositoryCustom {

    private final SqlExecutor sqlExecutor;

    public BidPrjSur2RepositoryImpl(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    @Override
    public Page<BidPrjSur2DTO> findByCriteria(BidPrjSur2Criteria bidPrjSur2Criteria) {
        Query queryCount = Query
                .builder()
                .append("Select COUNT(*) FROM BID_PRJ_SUR2 a ")
                .append("JOIN BID_MDLBAS b ON a.DIS_NO = b.DIS_NO WHERE 1=1")
                .append("AND a.WKUT = :wkut",bidPrjSur2Criteria.getWkut())
                .append("AND  a.PRJNO = :prjno",bidPrjSur2Criteria.getPrjno())
                .build();
        int totalCount = sqlExecutor.queryForList(queryCount, Integer.class).get(0);

        Query query = Query
                .builder()
                .append("SELECT  ")
                .append("a.WKUT, a.PRJNO,a.DIS_NO,a.AMNTA,a.AMNTB,a.AMNT,a.CREATE_DATE,b.DIS_NAME, b.VERI3_BUDG ")
                .append("FROM BID_PRJ_SUR2 a JOIN BID_MDLBAS b ON a.DIS_NO = b.DIS_NO ")
                .append(" WHERE 1=1")
                .append("AND a.WKUT = :wkut",bidPrjSur2Criteria.getWkut())
                .append("AND a.PRJNO = :prjno",bidPrjSur2Criteria.getPrjno())
                .append("order by " + bidPrjSur2Criteria.getOrderBy())
                .append("offset :currentPage * :perPage rows fetch next :perPage rows only",
                        bidPrjSur2Criteria.getCurrentPage(), bidPrjSur2Criteria.getPerPage(), bidPrjSur2Criteria.getPerPage())
                .build();
        List<BidPrjSur2DTO> bidPrjSur2DTOs = sqlExecutor.queryForList(query,BidPrjSur2DTO.class);
        Page<BidPrjSur2DTO> resultPage = new PageImpl<>(bidPrjSur2DTOs,bidPrjSur2Criteria.toPageable(),totalCount);
        return resultPage;
    }
}
