package gov.pcc.pwc.repository.impl;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.repository.custom.BidGovCheckRepositoryCustom;
import gov.pcc.pwc.service.criteria.BidGovCheckCriteria;
import gov.pcc.pwc.service.dto.BidGovCheckDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

public class BidGovCheckRepositoryImpl implements BidGovCheckRepositoryCustom {

    private final SqlExecutor sqlExecutor;

    public BidGovCheckRepositoryImpl(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    @Override
    public Page<BidGovCheckDTO> findAllByCriteria(BidGovCheckCriteria criteria) {

        Query queryTotalCount = Query
                .builder()
                .append(" SELECT COUNT(*) FROM BID_GOV_CHECK WHERE 1=1 ")
                .appendWhen(StringUtils.isNotBlank(criteria.getWkut()), " AND WKUT = :wkut ",criteria.getWkut())
                .appendWhen(StringUtils.isNotBlank(criteria.getPrjno()), " AND PRJNO = :prjno ", criteria.getPrjno())
                .build();
        int totalCount = sqlExecutor.queryForList(queryTotalCount, Integer.class).get(0);

        Query query = Query
                .builder()
                .append(" select row_number() over(order by " + criteria.getOrderBy() + ") as row_num,")
                .append(" WKUT, PRJNO, CHECK_DATE, CHECK_UNIT,")
                .append(" DETEL3, ROVE_CHK0, ROVE_CHK1, ROVE_CHK7, ROVE_CHK2, ROVE_CHK3, ROVE_CHK32, ROVE_CHK33, ROVE_CHK34, ROVE_CHK35, ROVE_CHK36, ROVE_CHK4, ROVE_CHK42, ROVE_CHK43, ROVE_CHK45, ROVE_CHK44,")
                .append(" ROVE_CHK1A, ROVE_CHK7A, ROVE_CHK2A, ROVE_CHK3A, ROVE_CHK32A, ROVE_CHK33A, ROVE_CHK34A, ROVE_CHK35A, ROVE_CHK36A, ROVE_CHK4A, ROVE_CHK42A, ROVE_CHK43A, ROVE_CHK45A, ROVE_CHK44A,")
                .append(" DETEL4")
                .append(" FROM BID_GOV_CHECK ")
                .append(" WHERE 1=1 ")
                .appendWhen(StringUtils.isNotBlank(criteria.getWkut()), " AND WKUT = :wkut ", criteria.getWkut())
                .appendWhen(StringUtils.isNotBlank(criteria.getPrjno()), " AND PRJNO = :prjno ", criteria.getPrjno())
                // 360開頭之查核主辦單位 且 紀錄已奉核
                .append(" AND ((SUBSTRING(CHECK_UNIT,1,3)='360' AND CHKFLAG = 'Y') OR SUBSTRING(CHECK_UNIT,1,3) <> '360' ) ")
                .append("order by " + criteria.getOrderBy())
                .append(
                        "offset :currentPage * :perPage rows fetch next :perPage rows only",
                        criteria.getCurrentPage(),
                        criteria.getPerPage(),
                        criteria.getPerPage()
                )
                .build();
        List<BidGovCheckDTO> BidGovCheckDTOs = sqlExecutor.queryForList(query, BidGovCheckDTO.class);
        Page<BidGovCheckDTO> resultPage = new PageImpl<>(BidGovCheckDTOs, criteria.toPageable(), totalCount);
        return resultPage;
    }
}
