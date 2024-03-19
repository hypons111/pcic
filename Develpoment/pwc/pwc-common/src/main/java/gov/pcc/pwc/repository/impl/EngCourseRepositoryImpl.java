package gov.pcc.pwc.repository.impl;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.repository.custom.EngCourseRepositoryCustom;
import gov.pcc.pwc.service.criteria.EngCourseCriteria;
import gov.pcc.pwc.service.dto.EngCourseDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

public class EngCourseRepositoryImpl implements EngCourseRepositoryCustom {
    private final SqlExecutor sqlExecutor;

    public EngCourseRepositoryImpl(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    @Override
    public Page<EngCourseDTO> findAllByCriteria(EngCourseCriteria criteria) {
        Query query = Query
                .builder()
                .append("SELECT DISTINCT a.*, c.TYPE as COMP_TYPE, c.CH_NAME AS COMP_NAME ")
                .append("FROM ENG_COURSE a ")
                .append("LEFT JOIN ENG_COURSE_DETAIL b ON a.ENG_COURSE_ID = b.ENG_COURSE_ID ")
                .append("INNER JOIN ENG_COMP c ON a.COMP_ID = c.ENG_COMP_ID ")
                .append("WHERE 1 = 1 ")
                .appendWhen(StringUtils.isNotBlank(criteria.getCourseName()), "AND (a.COURSE_NAME LIKE :courseName OR b.COURSE_NAME LIKE :courseName) ", "%" + criteria.getCourseName() + "%", "%" + criteria.getCourseName() + "%")
                .appendWhen(criteria.getDateStart() != null, "AND a.DATE_START >= :dateStart ", criteria.getDateStart())
                .appendWhen(criteria.getDateEnd() != null, "AND a.DATE_END <= :dateEnd ", criteria.getDateEnd())
                .appendWhen(StringUtils.isNotBlank(criteria.getAdminCompName()), "AND (a.COURSE_NAME LIKE :adminCompName) ", "%" + criteria.getAdminCompName() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getDocNo()), "AND a.DOC_NO = :docNo ", criteria.getDocNo())
                .appendWhen(StringUtils.isNotBlank(criteria.getStatus()), "AND a.STATUS = :status ", criteria.getStatus())
                .appendWhen(criteria.getReviewDateStart() != null, "AND a.REVIEW_TIME >= :reviewDateStart ", criteria.getReviewDateStart())
                .appendWhen(criteria.getReviewDateEnd() != null, "AND a.REVIEW_TIME <= :reviewDateEnd ", criteria.getReviewDateEnd())
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
                .append("SELECT COUNT(DISTINCT a.ENG_COURSE_ID) ")
                .append("FROM ENG_COURSE a ")
                .append("LEFT JOIN ENG_COURSE_DETAIL b ON a.ENG_COURSE_ID = b.ENG_COURSE_ID ")
                .append("INNER JOIN ENG_COMP c ON a.COMP_ID = c.ENG_COMP_ID ")
                .append("WHERE 1 = 1 ")
                .appendWhen(StringUtils.isNotBlank(criteria.getCourseName()), "AND (a.COURSE_NAME LIKE :courseName OR b.COURSE_NAME LIKE :courseName) ", "%" + criteria.getCourseName() + "%", "%" + criteria.getCourseName() + "%")
                .appendWhen(criteria.getDateStart() != null, "AND a.DATE_START >= :dateStart ", criteria.getDateStart())
                .appendWhen(criteria.getDateEnd() != null, "AND a.DATE_END <= :dateEnd ", criteria.getDateEnd())
                .appendWhen(StringUtils.isNotBlank(criteria.getAdminCompName()), "AND (a.COURSE_NAME LIKE :adminCompName) ", "%" + criteria.getAdminCompName() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getDocNo()), "AND a.DOC_NO = :docNo ", criteria.getDocNo())
                .appendWhen(StringUtils.isNotBlank(criteria.getStatus()), "AND a.STATUS = :status ", criteria.getStatus())
                .appendWhen(criteria.getReviewDateStart() != null, "AND a.REVIEW_TIME >= :reviewDateStart ", criteria.getReviewDateStart())
                .appendWhen(criteria.getReviewDateEnd() != null, "AND a.REVIEW_TIME <= :reviewDateEnd ", criteria.getReviewDateEnd())
                .build();

        return new PageImpl<>(
                sqlExecutor.queryForList(query, EngCourseDTO.class),
                criteria.toPageable(),
                sqlExecutor.queryForList(queryTotalRows, Integer.class).get(0)
        );
    }

    @Override
    public EngCourseDTO findDTOById(String engCourseId) {
        Query query = Query
                .builder()
                .append("SELECT a.*, b.TYPE as COMP_TYPE, b.CH_NAME AS COMP_NAME ")
                .append("FROM ENG_COURSE a ")
                .append("INNER JOIN ENG_COMP b ON a.COMP_ID = b.ENG_COMP_ID ")
                .append("WHERE ENG_COURSE_ID = :engCourseId", engCourseId)
                .build();
        List<EngCourseDTO> list = sqlExecutor.queryForList(query, EngCourseDTO.class);
        return list.size() > 0 ? list.get(0) : null;
    }


}
