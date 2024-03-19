package gov.pcc.pwc.repository.impl;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.repository.custom.EngEngrLicenseRepositoryCustom;
import gov.pcc.pwc.service.criteria.EngEngrLicenseCriteria;
import gov.pcc.pwc.service.dto.EngEngrLicenseDTO;
import gov.pcc.pwc.service.dto.EngEngrSubjectpointDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

public class EngEngrLicenseRepositoryImpl implements EngEngrLicenseRepositoryCustom {

    private final SqlExecutor sqlExecutor;

    public EngEngrLicenseRepositoryImpl(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    @Override
    public List<EngEngrLicenseDTO> findByCriteriaImpl(EngEngrLicenseCriteria criteria){
        Query query = Query
                .builder()
                .append("SELECT ENGR_LICENSE_DOC_NO, COMP_CH_NAME, COMP_IDNO, APPLY_ITEM FROM ENG_ENGR_LICENSE")
                .append(" WHERE ENGR_ID = :engrId ", criteria.getEngrId())
                .append(" AND ENG_ENGR_LICENSE_VERSION = (select max(ENG_ENGR_LICENSE_VERSION) FROM ENG_ENGR_LICENSE")
                .append(" WHERE ENGR_ID = :engrId ", criteria.getEngrId())
                .append(" AND STATUS='5')")
                .build();
        return sqlExecutor.queryForList(query, EngEngrLicenseDTO.class);
    }

    public Page<EngEngrLicenseDTO> findByCriteriaImpl2(EngEngrLicenseCriteria criteria){
        Query query = Query
                .builder()
                .append("SELECT ENG_ENGR_LICENSE_NO, CH_NAME, SUBJECT_LIST, IDNO, RECEIVE_NO, APPLY_TYPE, RECIVE_DATE, PUBLIC_DATE, STATUS FROM ENG_ENGR_LICENSE")
                .append("WHERE 1=1 " )
                .appendWhen(StringUtils.isNotBlank(criteria.getEngrName()), " AND CH_NAME = :engrName ", criteria.getEngrName())
                .appendWhen(StringUtils.isNotBlank(criteria.getIdno()), " AND IDNO = :idno ", criteria.getIdno())
                .appendWhen(criteria.getApplyDateStart() != null, "AND RECIVE_DATE >= :applyDateStart ", criteria.getApplyDateStart())
                .appendWhen(criteria.getApplyDateEnd() != null, "AND RECIVE_DATE <= :applyDateEnd ", criteria.getApplyDateEnd())
                .appendWhen(criteria.getPublicDateStart() != null, "AND PUBLIC_DATE >= :publicDateStart ", criteria.getPublicDateStart())
                .appendWhen(criteria.getPublicDateEnd() != null, "AND PUBLIC_DATE <= :publicDateEnd ", criteria.getPublicDateEnd())
                .appendWhen(StringUtils.isNotBlank(criteria.getReviewStatus()), " AND STATUS = :reviewStatus ", criteria.getReviewStatus())
                .appendWhen(StringUtils.isNotBlank(criteria.getSubject()), " AND SUBJECT_LIST = :subject ", criteria.getSubject())
                .appendWhen(StringUtils.isNotBlank(criteria.getEngrLicenseDocNo()), " AND ENGR_LICENSE_DOC_NO = :engrLicenseDocNo ", criteria.getEngrLicenseDocNo())
                .appendWhen(StringUtils.isNotBlank(criteria.getCompName()), " AND COMP_CH_NAME = :compName ", criteria.getCompName())
                .appendWhen(StringUtils.isNotBlank(criteria.getReceiveNo()), " AND RECEIVE_NO = :receiveNo ", criteria.getReceiveNo())
                .appendWhen(StringUtils.isNotBlank(criteria.getEngEngrCertificateNo()), " AND ENG_ENGR_LICENSE_NO = :engEngrCertificateNo ", criteria.getEngEngrCertificateNo())
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
                .append("SELECT COUNT(*) FROM ENG_ENGR_LICENSE ")
                .append("WHERE 1 = 1 ")
                .appendWhen(StringUtils.isNotBlank(criteria.getEngrName()), " AND CH_NAME = :engrName ", criteria.getEngrName())
                .appendWhen(StringUtils.isNotBlank(criteria.getIdno()), " AND IDNO = :idno ", criteria.getIdno())
                .appendWhen(criteria.getApplyDateStart() != null, "AND RECIVE_DATE >= :applyDateStart ", criteria.getApplyDateStart())
                .appendWhen(criteria.getApplyDateEnd() != null, "AND RECIVE_DATE <= :applyDateEnd ", criteria.getApplyDateEnd())
                .appendWhen(criteria.getPublicDateStart() != null, "AND PUBLIC_DATE >= :publicDateStart ", criteria.getPublicDateStart())
                .appendWhen(criteria.getPublicDateEnd() != null, "AND PUBLIC_DATE <= :publicDateEnd ", criteria.getPublicDateEnd())
                .appendWhen(StringUtils.isNotBlank(criteria.getReviewStatus()), " AND STATUS = :reviewStatus ", criteria.getReviewStatus())
                .appendWhen(StringUtils.isNotBlank(criteria.getSubject()), " AND SUBJECT_LIST = :subject ", criteria.getSubject())
                .appendWhen(StringUtils.isNotBlank(criteria.getEngrLicenseDocNo()), " AND ENGR_LICENSE_DOC_NO = :engrLicenseDocNo ", criteria.getEngrLicenseDocNo())
                .appendWhen(StringUtils.isNotBlank(criteria.getCompName()), " AND COMP_CH_NAME = :compName ", criteria.getCompName())
                .appendWhen(StringUtils.isNotBlank(criteria.getReceiveNo()), " AND RECEIVE_NO = :receiveNo ", criteria.getReceiveNo())
                .appendWhen(StringUtils.isNotBlank(criteria.getEngEngrCertificateNo()), " AND ENG_ENGR_LICENSE_NO = :engEngrCertificateNo ", criteria.getEngEngrCertificateNo())
                .build();

        return new PageImpl<>(
                sqlExecutor.queryForList(query, EngEngrLicenseDTO.class),
                criteria.toPageable(),
                sqlExecutor.queryForList(queryTotalRows, Integer.class).get(0)
        );
    }
}
