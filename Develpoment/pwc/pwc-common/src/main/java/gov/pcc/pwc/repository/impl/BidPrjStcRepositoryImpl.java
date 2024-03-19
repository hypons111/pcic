package gov.pcc.pwc.repository.impl;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.repository.custom.BidPrjStcRepositoryCustom;
import gov.pcc.pwc.service.criteria.BidPrjStcCriteria;
import gov.pcc.pwc.service.dto.BidPrjStcDTO;
import gov.pcc.pwc.service.dto.BidUnit10DTO;

import java.util.List;

public class BidPrjStcRepositoryImpl implements BidPrjStcRepositoryCustom {
    private final SqlExecutor sqlExecutor;

    public BidPrjStcRepositoryImpl(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    @Override
    public List<BidPrjStcDTO> findBidAae013DTOByCriteria(BidPrjStcCriteria criteria) {
        Query query = Query
                .builder()
                .append("Select * FROM BID_PRJ_STC a JOIN BID_PROJECT b ON a.WKUT = b.WKUT AND a.PRJNO = b.PRJNO WHERE 1 = 1")
                .append("AND a.WKUT = :wkut",criteria.getWkut())
                .append("AND a.PRJNO = :prjno",criteria.getPrjno())
                .append("AND a.CHECK_DATE = :checkDate",criteria.getCheckDate())
                .build();
        return sqlExecutor.queryForList(query, BidPrjStcDTO.class);
    }
}
