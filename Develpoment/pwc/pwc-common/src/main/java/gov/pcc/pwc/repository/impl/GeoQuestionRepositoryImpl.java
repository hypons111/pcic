package gov.pcc.pwc.repository.impl;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.repository.custom.GeoQuestionRepositoryCustom;
import gov.pcc.pwc.service.criteria.GeoQuestionCriteria;
import gov.pcc.pwc.service.dto.AdmSysCodeDTO;
import gov.pcc.pwc.service.dto.GeoMemberDTO;
import gov.pcc.pwc.service.dto.GeoQuestionDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.sql.Timestamp;
import java.util.List;

public class GeoQuestionRepositoryImpl implements GeoQuestionRepositoryCustom {

    private final SqlExecutor sqlExecutor;

    public GeoQuestionRepositoryImpl(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    @Override
        public Page<GeoQuestionDTO> findAllByCriteriaImpl(GeoQuestionCriteria criteria) {
        Query query = Query
            .builder()
                .append(" SELECT gq.* FROM (GEO_QUESTION gq LEFT JOIN GEO_MEMBER gm ON gq.CREATE_USER = gm.GEO_MEMBER_ID)")
                .append(" LEFT JOIN GEO_COMPANY gc ON gq.CREATE_USER = gc.GEO_COMPANY_ID  WHERE 1=1 AND IS_DEL = 'N' ")
                .appendWhen(StringUtils.isNotBlank(criteria.getQuestionType()), " AND gq.ROLE_ID = :questionType ", criteria.getQuestionType())
                .appendWhen(StringUtils.isNotBlank(criteria.getStatus()), " AND gq.STATUS = :status ", criteria.getStatus())
                .appendWhen(StringUtils.isNotBlank(criteria.getQuestionPerson()), " AND (gm.USER_NAME LIKE :questionPerson OR gc.COMPANY_NAME LIKE :questionPerson )","%" + criteria.getQuestionPerson() + "%","%" + criteria.getQuestionPerson() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getQuestion()), " AND gm.QUESTION LIKE :question ","%" + criteria.getQuestion() + "%")
                .appendWhen(criteria.getCreateTimeStart() != null, " AND CONVERT(date, gq.CREATE_TIME) >= :createTimeStart ", criteria.getCreateTimeStart())
                .appendWhen(criteria.getCreateTimeEnd() != null, " AND CONVERT(date, gq.CREATE_TIME) <= :createTimeEnd ", criteria.getCreateTimeEnd())
//                .appendWhen(criteria.getCreateTimeStart() != null, " AND gq.CREATE_TIME >= :createTimeStart ", Timestamp.from(criteria.getCreateTimeStart()).toLocalDateTime())
//                .appendWhen(criteria.getCreateTimeEnd() != null, " AND gq.CREATE_TIME <= :createTimeEnd ", Timestamp.from(criteria.getCreateTimeEnd()).toLocalDateTime())
//                .appendWhen(Timestamp.from(criteria.getCreateTimeStart()).toLocalDateTime() != null, " AND gq.CREATE_TIME >= :createTimeStart ", Timestamp.from(criteria.getCreateTimeStart()).toLocalDateTime())
//                .appendWhen(Timestamp.from(criteria.getCreateTimeStart()).toLocalDateTime() != null, " AND gq.CREATE_TIME <= :createTimeEnd ", Timestamp.from(criteria.getCreateTimeEnd()).toLocalDateTime())
                .append(" ORDER BY gq.CREATE_TIME DESC ")
                .append(
                        "OFFSET :currentPage * :perPage ROWS FETCH NEXT :perPage ROWS ONLY",
                        criteria.getCurrentPage(),
                        criteria.getPerPage(),
                        criteria.getPerPage()
                )
                .build();

        Query queryTotalRows = Query
                .builder()
                .append(" SELECT COUNT(*) FROM (GEO_QUESTION gq LEFT JOIN GEO_MEMBER gm ON gq.CREATE_USER = gm.GEO_MEMBER_ID)")
                .append(" LEFT JOIN GEO_COMPANY gc ON gq.CREATE_USER = gc.GEO_COMPANY_ID  WHERE 1=1 AND IS_DEL = 'N' ")
                .appendWhen(StringUtils.isNotBlank(criteria.getQuestionType()), " AND gq.ROLE_ID = :questionType ", criteria.getQuestionType())
                .appendWhen(StringUtils.isNotBlank(criteria.getStatus()), " AND gq.STATUS = :status ", criteria.getStatus())
                .appendWhen(StringUtils.isNotBlank(criteria.getQuestionPerson()), " AND (gm.USER_NAME LIKE :questionPerson OR gc.COMPANY_NAME LIKE :questionPerson )","%" + criteria.getQuestionPerson() + "%","%" + criteria.getQuestionPerson() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getQuestion()), " AND gm.QUESTION LIKE :question ","%" + criteria.getQuestion() + "%")
                .appendWhen(criteria.getCreateTimeStart() != null, " AND CONVERT(date, gq.CREATE_TIME) >= :createTimeStart ", criteria.getCreateTimeStart())
                .appendWhen(criteria.getCreateTimeEnd() != null, " AND CONVERT(date, gq.CREATE_TIME) <= :createTimeEnd ", criteria.getCreateTimeEnd())
//                .appendWhen(criteria.getCreateTimeStart() != null, " AND gq.CREATE_TIME >= :createTimeStart ", Timestamp.from(criteria.getCreateTimeStart()).toLocalDateTime())
//                .appendWhen(criteria.getCreateTimeEnd() != null, " AND gq.CREATE_TIME <= :createTimeEnd ", Timestamp.from(criteria.getCreateTimeEnd()).toLocalDateTime())
//                .appendWhen(Timestamp.from(criteria.getCreateTimeStart()).toLocalDateTime() != null, " AND gq.CREATE_TIME >= :createTimeStart ", Timestamp.from(criteria.getCreateTimeStart()).toLocalDateTime())
//                .appendWhen(Timestamp.from(criteria.getCreateTimeStart()).toLocalDateTime() != null, " AND gq.CREATE_TIME <= :createTimeEnd ", Timestamp.from(criteria.getCreateTimeEnd()).toLocalDateTime())
                .build();

        return new PageImpl<>(
                sqlExecutor.queryForList(query, GeoQuestionDTO.class),
                criteria.toPageable(),
                sqlExecutor.queryForList(queryTotalRows, Integer.class).get(0)
        );
    }

    @Override
    public int insert(GeoQuestionDTO dto) {
        Query query = Query
                .builder()
                .append("INSERT INTO GEO_QUESTION (GEO_QUESTION_ID,ROLE_ID,QUESTION,CREATE_USER) VALUES (")
//                .append(" :geoQuestionId", dto.getGeoQuestionId())
//                .append("SELECT GEO_MEMBER_ID FROM GEO_MEMBER WHERE IDNO = (SELECT IDNO FROM ADM_CONTACT WHERE USER_ID = 'user')", dto.getGeoQuestionId())
//                .append(", :roleId", dto.getRoleId())
                .append("1000,2")
                .appendWhen(StringUtils.isNotBlank(dto.getQuestion()),", :question", dto.getQuestion())
//                .append(" :createUser", dto.getCreateUser())
                .append(",1000")
                .append(")")
                .build();
        return sqlExecutor.insert(query);
    }

    @Override
    public List<AdmSysCodeDTO> findAdmSysCodeByCriteriaImpl(GeoQuestionCriteria criteria){
        Query query = Query
                .builder()
                .append("SELECT VALUE FROM ADM_SYS_CODE WHERE MODULE_TYPE = 'GEO' ")
                .append("AND DATA_TYPE = 'JOB_REMARK'")
                .append("AND DATA_KEY = :role ", criteria.getRole())
                .build();
        return sqlExecutor.queryForList(query, AdmSysCodeDTO.class);
    }
    @Override
    public void updateAdmSysCodeByCriteriaImpl(GeoQuestionCriteria criteria) {

        Query query1 = Query
                .builder()
                .append(" UPDATE ADM_SYS_CODE SET")
                .appendWhen(StringUtils.isNotBlank(criteria.getMemberRemark())," VALUE = :memberRemark", criteria.getMemberRemark())
                .append(" WHERE MODULE_TYPE = 'GEO'")
                .append(" AND DATA_TYPE = 'JOB_REMARK'")
                .append(" AND DATA_KEY = '01'")
                .build();
        Query query2 = Query
                .builder()
                .append(" UPDATE ADM_SYS_CODE SET ")
                .appendWhen(StringUtils.isNotBlank(criteria.getMemberRemark())," VALUE = :companyRemark", criteria.getCompanyRemark())
                .append(" WHERE MODULE_TYPE = 'GEO'")
                .append(" AND DATA_TYPE = 'JOB_REMARK'")
                .append(" AND DATA_KEY = '02'")
                .build();
         sqlExecutor.update(query1);
         sqlExecutor.update(query2);
    }


    @Override
    public List<GeoMemberDTO> findGeoMemberIdByCriteriaImpl(GeoQuestionCriteria criteria){
        Query query = Query
                .builder()
                .append("SELECT GEO_MEMBER_ID,IDNO FROM GEO_MEMBER")
                .append(" WHERE IDNO = (SELECT IDNO FROM ADM_CONTACT")
                .append(" WHERE USER_ID = :userId)", criteria.getUserId())
                .build();
        return sqlExecutor.queryForList(query, GeoMemberDTO.class);
    }

}
