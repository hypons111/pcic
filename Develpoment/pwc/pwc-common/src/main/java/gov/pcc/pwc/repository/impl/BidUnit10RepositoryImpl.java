package gov.pcc.pwc.repository.impl;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.repository.custom.BidUnit10RepositoryCustom;
import gov.pcc.pwc.service.criteria.BidUnit10Criteria;
import gov.pcc.pwc.service.dto.BidUnit10DTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

public class BidUnit10RepositoryImpl implements BidUnit10RepositoryCustom {
    private final SqlExecutor sqlExecutor;

    public BidUnit10RepositoryImpl(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    @Override
    public List<BidUnit10DTO> findBidPrjResponsName(String code) {
        Query query = Query
                .builder()
                .append("SELECT NAME FROM BID_UNIT10 WHERE 1=1")
                .append("AND CODE = :code",code)
                .build();
        return sqlExecutor.queryForList(query, BidUnit10DTO.class);
    }

    public List<BidUnit10DTO> findBidManageUnit(String wkutCode) {
        Query query = Query
                .builder()
                .append("SELECT CODE, NAME " )
                .append("FROM BID_UNIT10 aa WHERE 1=1 ")
                .append("AND TYPE = '0' ")
                .append("AND (CODE like :wkut1 ",wkutCode.substring(0,3)+"000000_")
                .append("OR CODE like :wkut3 ",wkutCode.substring(0,3)+"00__00_")
                .append("OR CODE like :wkut5 ",wkutCode.substring(0,5)+"0000_")
                .append("OR CODE like :wkut7 ",wkutCode.substring(0,7)+"00_")
                .append("OR CODE like :wkut9) ",wkutCode.substring(0,9)+"_")
                .append("ORDER BY CODE; ")
                .build();
        return sqlExecutor.queryForList(query, BidUnit10DTO.class);
    }

    @Override
    public Page<BidUnit10DTO> findAllByCriteria(BidUnit10Criteria criteria) {
        Query queryTotalCount = Query
                .builder()
                .append(" SELECT COUNT(*) FROM BID_UNIT10 bu ")
                .append(" WHERE 1=1 ")
                .appendWhen(StringUtils.isNotBlank(criteria.getCode()), " AND bu.CODE LIKE :code ", criteria.getCode() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getName()), " AND bu.NAME LIKE :name ", "%" + criteria.getName() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getType()), " AND bu.TYPE = :type ", criteria.getType())
                .build();
        int totalCount = sqlExecutor.queryForList(queryTotalCount, Integer.class).get(0);

        Query query = Query
                .builder()
                .append(" select row_number() over(order by " + criteria.getOrderBy() + ") as row_num, * ")
                .append(" FROM BID_UNIT10 bu ")
                .append(" WHERE 1=1 ")
                .appendWhen(StringUtils.isNotBlank(criteria.getCode()), " AND bu.CODE LIKE :code ", criteria.getCode() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getName()), " AND bu.NAME LIKE :name ", "%" + criteria.getName() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getType()), " AND bu.TYPE = :type ", criteria.getType())
                .append("order by " + criteria.getOrderBy())
                .append(
                        "offset :currentPage * :perPage rows fetch next :perPage rows only",
                        criteria.getCurrentPage(),
                        criteria.getPerPage(),
                        criteria.getPerPage()
                )
                .build();

        List<BidUnit10DTO> bidUnit10DTOs = sqlExecutor.queryForList(query, BidUnit10DTO.class);
        Page<BidUnit10DTO> resultPage = new PageImpl<>(bidUnit10DTOs, criteria.toPageable(), totalCount);
        return resultPage;
    }
}
