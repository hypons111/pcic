package gov.pcc.pwc.repository.impl;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.repository.custom.BidPlaceRepositoryCustom;
import gov.pcc.pwc.service.criteria.BidPlaceCriteria;
import gov.pcc.pwc.service.dto.BidPlaceDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

public class BidPlaceRepositoryImpl implements BidPlaceRepositoryCustom {

    private final SqlExecutor sqlExecutor;

    public BidPlaceRepositoryImpl(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    @Override
    public List<BidPlaceDTO> findMapInfoByPlaceCode(String placeCode) {

        Query query = Query
                .builder()
                .append(" select")
                .append(" PLACE_CODE, PLACE_NAME, SHAPE, COORDS, MAPSRC, WID, HEI")
                .append(" FROM BID_PLACE ")
                .append(" WHERE 1=1 ")
                .append(" AND SHAPE IS NOT NULL ")
                .appendWhen("00".equals(placeCode), " AND PLACE_CODE LIKE :placeCode ", "%" + placeCode)
                .appendWhen(!"00".equals(placeCode), " AND PLACE_CODE LIKE :placeCode ", placeCode + "%")
                .append(" ORDER BY PLACE_CODE DESC ")
                .build();
        List<BidPlaceDTO> BidPlaceDTOs = sqlExecutor.queryForList(query, BidPlaceDTO.class);
        return BidPlaceDTOs;

    }

    @Override
    public Page<BidPlaceDTO> findProjectCountByCriteria(BidPlaceCriteria criteria) {
        String placeCode = criteria.getPlaceCode();

        if ("00".equals(placeCode)) {
            Query queryTotalCount = Query
                    .builder()
                    .append(" SELECT COUNT(*) FROM BID_PLACE WHERE 1=1 ")
                    .append(" AND PLACE_CODE LIKE :placeCode ", "%" + placeCode)
                    .append(" AND PLACE_CODE <> '9999'")
                    .build();
            int totalCount = sqlExecutor.queryForList(queryTotalCount, Integer.class).get(0) + 1;

            Query query = Query
                    .builder()
                    .append(" SELECT ")
                    .append(" A.PLACE_CODE, A.PLACE_NAME, ISNULL(C.PROJECT_COUNT,0) PROJECT_COUNT, ISNULL(C.CT_SUM,0) CT_SUM ")
                    .append(" FROM BID_PLACE A LEFT JOIN ( ")
                    .append(" SELECT ")
                    .append(" PC, COUNT(*) PROJECT_COUNT, SUM(CT_SUM) CT_SUM ")
                    .append(" FROM (SELECT ")
                    .append(" WKUT, PRJNO, SUBSTRING(PLACE_CODE,1,2) PC, CT_SUM ")
                    .append(" FROM BID_PROJECT ")
                    .append(" WHERE 1=1 ")
                    .append(" AND PLACE_CODE <> '9999'")
                    .append(" AND (SUBSTRING(TYPE_CODE, 1, 1) <> '9') ") // 工程類別第一碼不為9
                    .append(" AND (ISNULL(BDGT1, 0) >= 1000) ") // 發包預算>=100萬
                    .append(" AND (AOK_DATE IS NULL ") // 驗收合格日期為空值或驗收合格日期>=今年
                    .append(" OR CONVERT(VARCHAR, AOK_DATE, 112) >= YEAR(GETDATE()) + '0000') ")
                    .append(" ) B ")
                    .append(" GROUP BY PC) C ")
                    .append(" ON A.PLACE_CODE = C.PC + '00' ")
                    .append(" WHERE A.PLACE_CODE LIKE :placeCode1 ", "%" + placeCode)
                    .append(" UNION ")
                    .append(" SELECT ")
                    .append(" 'total' PLACE_CODE, '合計' PLACE_NAME, COUNT(*) PROJECT_COUNT, ISNULL(SUM(CT_SUM),0) CT_SUM ")
                    .append(" FROM BID_PROJECT ")
                    .append(" WHERE 1=1 ")
                    .append(" AND PLACE_CODE <> '9999'")
                    .append(" AND (SUBSTRING(TYPE_CODE, 1, 1) <> '9') ") // 工程類別第一碼不為9
                    .append(" AND (ISNULL(BDGT1, 0) >= 1000) ") // 發包預算>=100萬
                    .append(" AND (AOK_DATE IS NULL ") // 驗收合格日期為空值或驗收合格日期>=今年
                    .append(" OR CONVERT(VARCHAR, AOK_DATE, 112) >= YEAR(GETDATE()) + '0000') ")
                    .append(" order by " + criteria.getOrderBy())
                    .append(
                            " offset :currentPage * :perPage rows fetch next :perPage rows only",
                            criteria.getCurrentPage(),
                            criteria.getPerPage(),
                            criteria.getPerPage()
                    )
                    .build();
            List<BidPlaceDTO> bidPlaceDTOs = sqlExecutor.queryForList(query, BidPlaceDTO.class);
            Page<BidPlaceDTO> resultPage = new PageImpl<>(bidPlaceDTOs, criteria.toPageable(), totalCount);

            return resultPage;
        } else {
            Query queryTotalCount = Query
                    .builder()
                    .append(" SELECT COUNT(*) FROM BID_PLACE WHERE 1=1 ")
                    .append(" AND PLACE_CODE LIKE :placeCode1 ", placeCode + "%")
                    .append(" AND PLACE_CODE <> :placeCode2 ", placeCode + "00")
                    .build();
            int totalCount = sqlExecutor.queryForList(queryTotalCount, Integer.class).get(0) + 1;

            Query query = Query
                    .builder()
                    .append(" SELECT ")
                    .append(" A.PLACE_CODE, A.PLACE_NAME, ISNULL(C.PROJECT_COUNT,0) PROJECT_COUNT, ISNULL(C.CT_SUM,0) CT_SUM")
                    .append(" FROM BID_PLACE A LEFT JOIN ( ")
                    .append(" SELECT ")
                    .append(" PLACE_CODE, COUNT(*) PROJECT_COUNT, SUM(CT_SUM) CT_SUM ")
                    .append(" FROM (SELECT ")
                    .append(" WKUT, PRJNO, PLACE_CODE, CT_SUM ")
                    .append(" FROM BID_PROJECT ")
                    .append(" WHERE 1=1 ")
                    .append(" AND (SUBSTRING(TYPE_CODE, 1, 1) <> '9') ") // 工程類別第一碼不為9
                    .append(" AND (ISNULL(BDGT1, 0) >= 1000) ") // 發包預算>=100萬
                    .append(" AND (AOK_DATE IS NULL ") // 驗收合格日期為空值或驗收合格日期>=今年
                    .append(" OR CONVERT(VARCHAR, AOK_DATE, 112) >= YEAR(GETDATE()) + '0000') ")
                    .append(" ) B ")
                    .append(" GROUP BY PLACE_CODE) C ")
                    .append(" ON A.PLACE_CODE = C.PLACE_CODE ")
                    .append(" WHERE A.PLACE_CODE LIKE :placeCode1 ", placeCode + "%")
                    .append(" AND A.PLACE_CODE <> :placeCode2 ", placeCode + "00")
                    .append(" UNION ")
                    .append(" SELECT ")
                    .append(" 'total' PLACE_CODE, '合計' PLACE_NAME, COUNT(*) PROJECT_COUNT, ISNULL(SUM(CT_SUM),0) CT_SUM")
                    .append(" FROM BID_PROJECT ")
                    .append(" WHERE 1=1 ")
                    .append(" AND PLACE_CODE LIKE :placeCode3 ", placeCode + "%")
                    .append(" AND (SUBSTRING(TYPE_CODE, 1, 1) <> '9') ") // 工程類別第一碼不為9
                    .append(" AND (ISNULL(BDGT1, 0) >= 1000) ") // 發包預算>=100萬
                    .append(" AND (AOK_DATE IS NULL ") // 驗收合格日期為空值或驗收合格日期>=今年
                    .append(" OR CONVERT(VARCHAR, AOK_DATE, 112) >= YEAR(GETDATE()) + '0000') ")
                    .append(" order by " + criteria.getOrderBy())
                    .append(
                            " offset :currentPage * :perPage rows fetch next :perPage rows only",
                            criteria.getCurrentPage(),
                            criteria.getPerPage(),
                            criteria.getPerPage()
                    )
                    .build();
            List<BidPlaceDTO> bidPlaceDTOs = sqlExecutor.queryForList(query, BidPlaceDTO.class);
            Page<BidPlaceDTO> resultPage = new PageImpl<>(bidPlaceDTOs, criteria.toPageable(), totalCount);

            return resultPage;
        }

    }

}
