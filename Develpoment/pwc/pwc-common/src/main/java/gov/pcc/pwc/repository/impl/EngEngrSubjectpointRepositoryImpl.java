package gov.pcc.pwc.repository.impl;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.repository.custom.EngEngrSubjectpointRepositoryCustom;
import gov.pcc.pwc.service.criteria.EngEngrSubjectpointCriteria;
import gov.pcc.pwc.service.dto.EngEngrSubjectpointDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

public class EngEngrSubjectpointRepositoryImpl implements EngEngrSubjectpointRepositoryCustom {
    private final SqlExecutor sqlExecutor;

    public EngEngrSubjectpointRepositoryImpl(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    @Override
    public Page<EngEngrSubjectpointDTO> findAllByCriteria(EngEngrSubjectpointCriteria criteria) {
        Query query = Query
                .builder()
                .append("SELECT ROW_NUMBER() OVER(ORDER BY " + criteria.getOrderBy() + ") AS ROW_NUM, ")
                .append("a.*, b.CH_NAME, b.EN_NAME, b.IDNO, c.CH_NAME AS CONTRACTOR_NAME ")
                .append("FROM ENG_ENGR_SUBJECTPOINT a ")
                .append("INNER JOIN ENG_MEMBER b ON a.ENGR_ID = b.ENG_MEMBER_ID ")
                .append("LEFT JOIN ADM_CONTACT c ON a.CONTRACTOR_ID = c.USER_ID ")
                .append("WHERE 1 = 1 ")
                .appendWhen(criteria.getApplyDateStart() != null, "AND a.APPLY_DATE >= :applyDateStart ", criteria.getApplyDateStart())
                .appendWhen(criteria.getApplyDateEnd() != null, "AND a.APPLY_DATE <= :applyDateEnd ", criteria.getApplyDateEnd())
                .appendWhen(StringUtils.isNotBlank(criteria.getContent()), "AND (a.THEME LIKE :content OR a.CONTENT LIKE :content) ", "%" + criteria.getContent() + "%", "%" + criteria.getContent() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getEngrName()), "AND (b.CH_NAME LIKE :engrName OR b.EN_NAME LIKE :engrName) ", "%" + criteria.getEngrName() + "%", "%" + criteria.getEngrName() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getIdno()), "AND b.IDNO = :idno ", criteria.getIdno())
                .appendWhen(StringUtils.isNotBlank(criteria.getSubject()), "AND a.SUBJECT = :subject ", criteria.getSubject())
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
                .append("SELECT COUNT(*) FROM ENG_ENGR_SUBJECTPOINT a ")
                .append("INNER JOIN ENG_MEMBER b ON a.ENGR_ID = b.ENG_MEMBER_ID ")
                .append("LEFT JOIN ADM_CONTACT c ON a.CONTRACTOR_ID = c.USER_ID ")
                .append("WHERE 1 = 1 ")
                .appendWhen(criteria.getApplyDateStart() != null, "AND a.APPLY_DATE >= :applyDateStart ", criteria.getApplyDateStart())
                .appendWhen(criteria.getApplyDateEnd() != null, "AND a.APPLY_DATE <= :applyDateEnd ", criteria.getApplyDateEnd())
                .appendWhen(StringUtils.isNotBlank(criteria.getContent()), "AND (a.THEME LIKE :content OR a.CONTENT LIKE :content) ", "%" + criteria.getContent() + "%", "%" + criteria.getContent() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getEngrName()), "AND (b.CH_NAME LIKE :engrName OR b.EN_NAME LIKE :engrName) ", "%" + criteria.getEngrName() + "%", "%" + criteria.getEngrName() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getIdno()), "AND b.IDNO = :idno ", criteria.getIdno())
                .appendWhen(StringUtils.isNotBlank(criteria.getSubject()), "AND a.SUBJECT = :subject ", criteria.getSubject())
                .build();

        return new PageImpl<>(
                sqlExecutor.queryForList(query, EngEngrSubjectpointDTO.class),
                criteria.toPageable(),
                sqlExecutor.queryForList(queryTotalRows, Integer.class).get(0)
        );
    }

    @Override
    public EngEngrSubjectpointDTO findDTOById(String id) {
        Query query = Query
                .builder()
                .append("SELECT a.*, b.CH_NAME, b.EN_NAME, b.IDNO, c.CH_NAME AS CONTRACTOR_NAME ")
                .append("FROM ENG_ENGR_SUBJECTPOINT a ")
                .append("INNER JOIN ENG_MEMBER b ON a.ENGR_ID = b.ENG_MEMBER_ID ")
                .append("LEFT JOIN ADM_CONTACT c ON a.CONTRACTOR_ID = c.USER_ID ")
                .append("WHERE a.ENG_ENGR_SUBJECTPOINT_ID = :id", id)
                .build();
        List<EngEngrSubjectpointDTO> list = sqlExecutor.queryForList(query, EngEngrSubjectpointDTO.class);
        return list.size() > 0 ? list.get(0) : null;
    }
}
