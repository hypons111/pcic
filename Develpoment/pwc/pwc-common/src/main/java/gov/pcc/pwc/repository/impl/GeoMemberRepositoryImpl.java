package gov.pcc.pwc.repository.impl;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.repository.custom.GeoMemberRepositoryCustom;
import gov.pcc.pwc.service.criteria.GeoMemberCriteria;
import gov.pcc.pwc.service.dto.GeoMemberDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;


public class GeoMemberRepositoryImpl implements GeoMemberRepositoryCustom {

    private final SqlExecutor sqlExecutor;

    public GeoMemberRepositoryImpl(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    @Override
    public Page<GeoMemberDTO> findAllByCriteriaImpl(GeoMemberCriteria criteria) {

        List workPlaceList = new ArrayList<String>();
        if(StringUtils.isNotBlank(criteria.getWorkPlace1())) {
            workPlaceList.add(criteria.getWorkPlace1());
        }
        if(StringUtils.isNotBlank(criteria.getWorkPlace2())) {
            workPlaceList.add(criteria.getWorkPlace2());
        }
        if(StringUtils.isNotBlank(criteria.getWorkPlace3())) {
            workPlaceList.add(criteria.getWorkPlace3());
        }
        String workPlace = StringUtils.join(workPlaceList, ',');



        Query query = Query
                .builder()
                .append("SELECT a.* FROM GEO_MEMBER a JOIN ( ")
                .append("SELECT GEO_MEMBER_ID, (apa.CITY + apa.AREA + gm.ADDR_OTHER) AS ADDR ")
                .append("FROM GEO_MEMBER gm LEFT JOIN ADM_POST_ADDR apa ON  (gm.ADDR_CITY + gm.ADDR_TOWN) = apa.AREA_CODE ")
                .append(") b ON a.GEO_MEMBER_ID = b.GEO_MEMBER_ID ")
                .append("WHERE 1 = 1 ")
//                .append("AND EXISTS (SELECT * FROM STRING_SPLIT(WORK_PLACE, ',')WHERE value IN (")
                .appendWhen(StringUtils.isNotBlank(workPlace), "AND EXISTS (SELECT * FROM STRING_SPLIT(WORK_PLACE, ',') WHERE value IN (" + workPlace + ")) ")
                .appendWhen(StringUtils.isNotBlank(criteria.getJobType()), "AND JOB_TYPE = :jobType ", criteria.getJobType())
                .appendWhen(StringUtils.isNotBlank(criteria.getSalaryRange()), "AND SALARY_RANGE = :salaryRange ", criteria.getSalaryRange())
                .appendWhen(StringUtils.isNotBlank(criteria.getKeyWord()), "AND (USER_NAME LIKE :keyWord ", "%" + criteria.getKeyWord() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getKeyWord()), "OR JOB_DEMAND LIKE :keyWord ", "%" + criteria.getKeyWord() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getKeyWord()), "OR REMARK LIKE :keyWord ", "%" + criteria.getKeyWord() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getKeyWord()), "OR ADDR LIKE :keyWord) ", "%" + criteria.getKeyWord() + "%")
                .append("ORDER BY " + criteria.getOrderBy())
                .append(
                        "OFFSET :currentPage * :perPage ROWS FETCH NEXT :perPage ROWS ONLY",
                        criteria.getCurrentPage(),
                        criteria.getPerPage(),
                        criteria.getPerPage()
                )
                .build();

        Query queryTotalRows = Query
                .builder()
                .append("SELECT COUNT(*) FROM GEO_MEMBER a JOIN ( ")
                .append("SELECT GEO_MEMBER_ID, (apa.CITY + apa.AREA + gm.ADDR_OTHER) AS ADDR ")
                .append("FROM GEO_MEMBER gm LEFT JOIN ADM_POST_ADDR apa ON  (gm.ADDR_CITY + gm.ADDR_TOWN) = apa.AREA_CODE ")
                .append(") b ON a.GEO_MEMBER_ID = b.GEO_MEMBER_ID ")
                .append("WHERE 1 = 1 ")
                .appendWhen(StringUtils.isNotBlank(workPlace), "AND EXISTS (SELECT * FROM STRING_SPLIT(WORK_PLACE, ',') WHERE value IN (" + workPlace + ")) ")
                .appendWhen(StringUtils.isNotBlank(criteria.getJobType()), " AND JOB_TYPE = :jobType ", criteria.getJobType())
                .appendWhen(StringUtils.isNotBlank(criteria.getSalaryRange()), "AND SALARY_RANGE = :salaryRange ", criteria.getSalaryRange())
                .appendWhen(StringUtils.isNotBlank(criteria.getKeyWord()), "AND (USER_NAME LIKE :keyWord ", "%" + criteria.getKeyWord() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getKeyWord()), "OR JOB_DEMAND LIKE :keyWord ", "%" + criteria.getKeyWord() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getKeyWord()), "OR REMARK LIKE :keyWord ", "%" + criteria.getKeyWord() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getKeyWord()), "OR ADDR LIKE :keyWord) ", "%" + criteria.getKeyWord() + "%")
                .build();

        return new PageImpl<>(
                sqlExecutor.queryForList(query, GeoMemberDTO.class),
                criteria.toPageable(),
                sqlExecutor.queryForList(queryTotalRows, Integer.class).get(0)
        );
    }
}
