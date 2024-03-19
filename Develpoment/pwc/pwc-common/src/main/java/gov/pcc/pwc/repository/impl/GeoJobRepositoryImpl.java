package gov.pcc.pwc.repository.impl;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.repository.custom.GeoJobRepositoryCustom;
import gov.pcc.pwc.service.criteria.GeoJobCriteria;
import gov.pcc.pwc.service.criteria.RecProjectQueryCriteria;
import gov.pcc.pwc.service.dto.Geo0607DTO;
import gov.pcc.pwc.service.dto.GeoJobDTO;

import gov.pcc.pwc.service.odsExporter.OdsExportService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

import static gov.pcc.pwc.repository.impl.RecProjectRepositoryImpl.parseNull;


public class GeoJobRepositoryImpl implements GeoJobRepositoryCustom {

    private final SqlExecutor sqlExecutor;

   OdsExportService odsExportService;

    public GeoJobRepositoryImpl(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    @Override
    public Page<GeoJobDTO> findAllByCriteria(GeoJobCriteria criteria) {
        Query query = Query
                .builder()
                .append("SELECT j.*, c.COMPANY_NAME, c.EMAIL FROM GEO_JOB j JOIN GEO_COMPANY c ")
                .append("ON j.CREATE_USER = c.GEO_COMPANY_ID ")
                .append("WHERE 1 = 1 ")
                .appendWhen(StringUtils.isNotBlank(criteria.getPublishStatus()), "AND PUBLISH_STATUS = :publishStatus ", criteria.getPublishStatus())
                .appendWhen(StringUtils.isNotBlank(criteria.getJobKeyWord()), "AND (EXISTS(SELECT * FROM STRING_SPLIT(WORK_PLACE_LIST, ',')WHERE value IN (SELECT DISTINCT CITY_CODE FROM ADM_POST_ADDR WHERE CITY LIKE :jobKeyWord", "%" + criteria.getJobKeyWord() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getJobKeyWord()), ")) OR WORK_PLACE_DESC LIKE :jobKeyWord ", "%" + criteria.getJobKeyWord() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getJobKeyWord()), "OR OTHER_COMMENT LIKE :jobKeyWord ", "%" + criteria.getJobKeyWord() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getJobKeyWord()), "OR JOB_TITLE LIKE :jobKeyWord ", "%" + criteria.getJobKeyWord() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getJobKeyWord()), "OR j.CONTACT_PERSON LIKE :jobKeyWord ", "%" + criteria.getJobKeyWord() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getJobKeyWord()), "OR JOB_TYPE IN ( SELECT DATA_KEY FROM ADM_SYS_CODE WHERE VALUE LIKE :jobKeyWord ", "%" + criteria.getJobKeyWord() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getJobKeyWord()), "))")
                .append(" ORDER BY PUBLISH_DATE_START DESC ")
                .append(
                        "OFFSET :currentPage * :perPage ROWS FETCH NEXT :perPage ROWS ONLY",
                        criteria.getCurrentPage(),
                        criteria.getPerPage(),
                        criteria.getPerPage()
                )
                .build();

        Query queryTotalRows = Query
                .builder()
                .append("SELECT COUNT(*) FROM GEO_JOB j JOIN GEO_COMPANY c ")
                .append("ON j.CREATE_USER = c.GEO_COMPANY_ID ")
                .append("WHERE 1 = 1 ")
                .appendWhen(StringUtils.isNotBlank(criteria.getPublishStatus()), "AND PUBLISH_STATUS = :publishStatus ", criteria.getPublishStatus())
                .appendWhen(StringUtils.isNotBlank(criteria.getJobKeyWord()), "AND (EXISTS(SELECT * FROM STRING_SPLIT(WORK_PLACE_LIST, ',')WHERE value IN (SELECT DISTINCT CITY_CODE FROM ADM_POST_ADDR WHERE CITY LIKE :jobKeyWord", "%" + criteria.getJobKeyWord() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getJobKeyWord()), ")) OR WORK_PLACE_DESC LIKE :jobKeyWord ", "%" + criteria.getJobKeyWord() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getJobKeyWord()), "OR OTHER_COMMENT LIKE :jobKeyWord ", "%" + criteria.getJobKeyWord() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getJobKeyWord()), "OR JOB_TITLE LIKE :jobKeyWord ", "%" + criteria.getJobKeyWord() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getJobKeyWord()), "OR j.CONTACT_PERSON LIKE :jobKeyWord ", "%" + criteria.getJobKeyWord() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getJobKeyWord()), "OR JOB_TYPE IN ( SELECT DATA_KEY FROM ADM_SYS_CODE WHERE VALUE LIKE :jobKeyWord ", "%" + criteria.getJobKeyWord() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getJobKeyWord()), "))")
                .build();

        return new PageImpl<>(
                sqlExecutor.queryForList(query, GeoJobDTO.class),
                criteria.toPageable(),
                sqlExecutor.queryForList(queryTotalRows, Integer.class).get(0)
        );
    }

    @Override
    public Page<GeoJobDTO> findByWorkPlace(GeoJobCriteria criteria) {
        Query query = Query
                .builder()
                .append("SELECT j.*, c.COMPANY_NAME FROM GEO_JOB j JOIN GEO_COMPANY c ")
                .append("ON j.CREATE_USER = c.GEO_COMPANY_ID ")
                .append("WHERE 1 = 1 ")
                .appendWhen(StringUtils.isNotBlank(criteria.getWorkPlaceList()), "AND EXISTS(SELECT * FROM STRING_SPLIT(j.WORK_PLACE_LIST, ',')WHERE value IN (" + criteria.getWorkPlaceList() + ")) ")
                .append("AND j.IS_APPROVE = 1 ")
                .append("AND j.PUBLISH_DATE_START <= GETDATE()")
                .append("AND j.PUBLISH_DATE_END >= GETDATE()")
                .append(" ORDER BY ID")
                .append(
                        "OFFSET :currentPage * :perPage ROWS FETCH NEXT :perPage ROWS ONLY",
                        criteria.getCurrentPage(),
                        criteria.getPerPage(),
                        criteria.getPerPage()
                )
                .build();

        Query queryTotalRows = Query
                .builder()
                .append("SELECT COUNT(*) FROM GEO_JOB j JOIN GEO_COMPANY c ")
                .append("ON j.CREATE_USER = c.GEO_COMPANY_ID ")
                .append("WHERE 1 = 1 ")
                .appendWhen(StringUtils.isNotBlank(criteria.getWorkPlaceList()), "AND EXISTS(SELECT * FROM STRING_SPLIT(j.WORK_PLACE_LIST, ',')WHERE value IN (" + criteria.getWorkPlaceList() + ")) ")
                .append("AND j.IS_APPROVE = 1 ")
                .append("AND j.PUBLISH_DATE_START <= GETDATE()")
                .append("AND j.PUBLISH_DATE_END >= GETDATE()")
                .build();

        return new PageImpl<>(
                sqlExecutor.queryForList(query, GeoJobDTO.class),
                criteria.toPageable(),
                sqlExecutor.queryForList(queryTotalRows, Integer.class).get(0)
        );
    }

    //    @Override
//    public Page<GeoJobDTO> findPublicJobs(GeoJobCriteria criteria) {
//        Query query = Query
//                .builder()
//                .append("SELECT j.*, c.COMPANY_NAME FROM GEO_JOB j JOIN GEO_COMPANY c ")
//                .append("ON j.CREATE_USER = c.GEO_COMPANY_ID ")
//                .append("WHERE 1 = 1 ")
//                .appendWhen(StringUtils.isNotBlank(criteria.getWorkPlaceList()), "AND EXISTS(SELECT * FROM STRING_SPLIT(j.WORK_PLACE_LIST, ',')WHERE value IN (" + criteria.getWorkPlaceList() + ")) ")
//                .append("AND j.IS_APPROVE = 1 ")
//                .append("AND j.IS_PUBLIC = 0 ")
//                .append("AND j.PUBLISH_DATE_START <= GETDATE()")
//                .append("AND j.PUBLISH_DATE_END >= GETDATE()")
//                .append(" ORDER BY ID")
//                .append(
//                        "OFFSET :currentPage * :perPage ROWS FETCH NEXT :perPage ROWS ONLY",
//                        criteria.getCurrentPage(),
//                        criteria.getPerPage(),
//                        criteria.getPerPage()
//                )
//                .build();
//
//        Query queryTotalRows = Query
//                .builder()
//                .append("SELECT COUNT(*) FROM GEO_JOB j JOIN GEO_COMPANY c ")
//                .append("ON j.CREATE_USER = c.GEO_COMPANY_ID ")
//                .append("WHERE 1 = 1 ")
//                .appendWhen(StringUtils.isNotBlank(criteria.getWorkPlaceList()), "AND EXISTS(SELECT * FROM STRING_SPLIT(j.WORK_PLACE_LIST, ',')WHERE value IN (" + criteria.getWorkPlaceList() + ")) ")
//                .append("AND j.IS_APPROVE = 1 ")
//                .append("AND j.IS_PUBLIC = 0 ")
//                .append("AND j.PUBLISH_DATE_START <= GETDATE()")
//                .append("AND j.PUBLISH_DATE_END >= GETDATE()")
//                .build();
//
//        return new PageImpl<>(
//                sqlExecutor.queryForList(query, GeoJobDTO.class),
//                criteria.toPageable(),
//                sqlExecutor.queryForList(queryTotalRows, Integer.class).get(0)
//        );
//    }
    @Override
    public Page<Geo0607DTO> findJobCounts(GeoJobCriteria criteria) {
        Query query = Query
                .builder()
                .append("SELECT  c.COMPANY_NAME as companyName, sum(j.PERSON_COUNT) as personCount, count(j.GEO_JOB_ID) as offerCount FROM GEO_JOB j JOIN GEO_COMPANY c ")
                .append("ON j.CREATE_USER = c.GEO_COMPANY_ID ")
                .append("WHERE 1 = 1 ")
                .append("AND j.PUBLISH_STATUS = 1 ")
                .append("AND j.IS_APPROVE = 0 ")
                .appendWhen(StringUtils.isNotBlank(criteria.getJobKind()), "AND j.JOB_KIND = :jobKind ", criteria.getJobKind())
                .appendWhen(criteria.getPublishDate1() != null, "AND j.PUBLISH_DATE_END >= :publishDate1 ", criteria.getPublishDate1())
                .appendWhen(criteria.getPublishDate2() == null, "AND j.PUBLISH_DATE_END < :publishDate1Plus ", criteria.getPublishDate1().plusMonths(1))
                .appendWhen(null !=criteria.getPublishDate2(), "AND j.PUBLISH_DATE_END < :publishDate2 ",criteria.getPublishDate2() == null ? null : criteria.getPublishDate2().plusMonths(1))
                                .append("GROUP BY c.COMPANY_NAME ")
                                .append("ORDER BY c.COMPANY_NAME")
                                .append(
                                        "OFFSET :currentPage * :perPage ROWS FETCH NEXT :perPage ROWS ONLY",
                                        criteria.getCurrentPage(),
                                        criteria.getPerPage(),
                                        criteria.getPerPage()
                                )
                                .build();

        Query queryTotalRows = Query
                .builder()
                .append("SELECT COUNT(*) FROM ( ")
                .append("SELECT c.COMPANY_NAME as companyName, sum(j.PERSON_COUNT) as personCount, count(j.GEO_JOB_ID) as offerCount FROM GEO_JOB j JOIN GEO_COMPANY c ")
                .append("ON j.CREATE_USER = c.GEO_COMPANY_ID ")
                .append("WHERE 1 = 1 ")
                .append("AND j.PUBLISH_STATUS = 1 ")
                .append("AND j.IS_APPROVE = 0 ")
                .appendWhen(StringUtils.isNotBlank(criteria.getJobKind()), "AND j.JOB_KIND = :jobKind ", criteria.getJobKind())
                .appendWhen(criteria.getPublishDate1() != null, "AND j.PUBLISH_DATE_END >= :publishDate1 ", criteria.getPublishDate1())
                .appendWhen(criteria.getPublishDate2() == null, "AND j.PUBLISH_DATE_END < :publishDate1Plus ", criteria.getPublishDate1().plusMonths(1))
                .appendWhen(criteria.getPublishDate2() != null, "AND j.PUBLISH_DATE_END < :publishDate2 ", criteria.getPublishDate2()== null ? null : criteria.getPublishDate2().plusMonths(1))
                                .append("GROUP BY c.COMPANY_NAME")
                                .append(") a")
                                .build();

        return new PageImpl<>(
                sqlExecutor.queryForList(query, Geo0607DTO.class),
                criteria.toPageable(),
                sqlExecutor.queryForList(queryTotalRows, Integer.class).get(0)
        );
    }

    @Override
    public List<Geo0607DTO> exportJobCounts(LocalDate firstMonth, String jobKind) {

        LocalDate lastMonth = firstMonth.plusMonths(1);

        Query query = Query
                .builder()
                .append("SELECT  c.COMPANY_NAME as companyName, sum(j.PERSON_COUNT) as personCount, count(j.GEO_JOB_ID) as offerCount, (select value from ADM_SYS_CODE where data_type='JOB_KIND' and data_key=j.JOB_KIND) as jobKind  FROM GEO_JOB j JOIN GEO_COMPANY c ")
                .append("ON j.CREATE_USER = c.GEO_COMPANY_ID ")
                .append("WHERE 1 = 1 ")
                .append("AND j.PUBLISH_STATUS = 1 ")
                .append("AND j.IS_APPROVE = 0 ")
                .appendWhen(StringUtils.isNotBlank(jobKind) ,"AND j.JOB_KIND = :jobKind ", jobKind)
                .append("AND j.PUBLISH_DATE_END >= :firstMonth ", firstMonth)
                .append("AND j.PUBLISH_DATE_END < :lastMonth ", lastMonth)
                .append("GROUP BY j.JOB_KIND, c.COMPANY_NAME ")
                .build();
        return sqlExecutor.queryForList(query, Geo0607DTO.class);

    }

}
