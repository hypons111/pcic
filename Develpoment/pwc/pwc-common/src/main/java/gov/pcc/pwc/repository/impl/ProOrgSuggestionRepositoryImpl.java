package gov.pcc.pwc.repository.impl;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.repository.custom.ProOrgSuggestionRepositoryCustom;
import gov.pcc.pwc.service.criteria.ProOrgSuggestionHistoryCriteria;
import gov.pcc.pwc.service.dto.ProOrgSuggestionHistoryDTO;
import gov.pcc.pwc.service.criteria.ProOrgSuggestionCriteria;
import gov.pcc.pwc.service.dto.AdmFunctionClickDTO;
import gov.pcc.pwc.service.dto.ComStatusSearchDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import java.util.List;
import java.util.Optional;

public class ProOrgSuggestionRepositoryImpl implements ProOrgSuggestionRepositoryCustom {

    private final SqlExecutor sqlExecutor;

    public ProOrgSuggestionRepositoryImpl(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    @Override
    public List<ProOrgSuggestionHistoryDTO> findAllByCriteria(ProOrgSuggestionHistoryDTO criteria) {
        Query.Builder queryBuilder = Query
                .builder()
                .append(" SELECT pip.INNOVATE_NAME,ac.NAME,ag.ORG_NAME,pos.TIME,pip.CREATE_TIME" +
                        " FROM PRO_ORG_SUGGESTION pos " +
                        "JOIN PRO_INNOVATIVE_PRODUCT pip ON pos.PRO_INNOVATIVE_PRODUCT_ID = pip.PRO_INNOVATIVE_PRODUCT_ID " +
                        "JOIN ADM_ACCOUNT aa ON aa.USER_ID = pip.USER_ID " +
                        "JOIN ADM_COMPANY ac ON aa.COMP_IDNO = ac.COMP_IDNO  " +
                        "JOIN ADM_AGENT ag ON pos.ORG_ID = ag.ORG_ID WHERE 1=1 ")
                .appendWhen(StringUtils.isNotBlank(criteria.getInnovateName()), " AND pip.INNOVATE_NAME LIKE :innovateName", "%" + criteria.getInnovateName() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getCompanyName()), " AND ac.NAME LIKE :companyName)", "%" + criteria.getCompanyName() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getCommunicateOrganName()), " AND ag.ORG_NAME LIKE :communicateOrganName ", "%" + criteria.getCommunicateOrganName() + "%");
        // NULLPOINTEREXCEPTION
//                .appendWhen(Instant.MIN != criteria.getCommunicateDateStart(), " AND pos.TIME >= :communicateDateStart ", criteria.getCommunicateDateStart())
//                 valueof().toString 會拋出 "null" 導致isNotBlank() 誤判true
//                .appendWhen(StringUtils.isNotBlank(String.valueOf(criteria.getCommunicateDateStart())), " AND pos.TIME >= :communicateDateStart ", criteria.getCommunicateDateStart())

//      Optional.ofNullable處理 NULL
        if (Optional.ofNullable(criteria.getCommunicateDateStart()).isPresent()) {
            queryBuilder.append(" AND pos.TIME >= :communicateDateStart ", criteria.getCommunicateDateStart().toString());
        }
        if (Optional.ofNullable(criteria.getCommunicateDateEnd()).isPresent()) {
            queryBuilder.append(" AND pos.TIME <= :getCommunicateDateEnd ", criteria.getCommunicateDateEnd().toString());
        }
        if (Optional.ofNullable(criteria.getCreateTimeStart()).isPresent()) {
            queryBuilder.append(" AND pip.CREATE_TIME >= :getCreateTimeStart ", criteria.getCreateTimeStart().toString());
        }
        if (Optional.ofNullable(criteria.getCreateTimeEnd()).isPresent()) {
            queryBuilder.append(" AND pip.CREATE_TIME <= :getCreateTimeEnd ", criteria.getCreateTimeEnd().toString());
        }


        //                交流狀態是用pro_org 及 pro_inno 中的status判斷
        if (Optional.ofNullable(criteria.getCommunicateStatus()).isPresent()) {
            System.out.println(criteria.getCommunicateStatus());
            switch (criteria.getCommunicateStatus()) {
                // 全部
                case "0":
                    break;
                //交流中
                case "1":
                    queryBuilder.appendWhen(true, "AND pip.status = :pipStatus", 1)
                            .appendWhen(true, "AND (pos.status is NULL OR pos.status = :posStatus", 0)
                            .appendWhen(true, "AND pos.WRITE_CONDITION = :writeCondition)", 0);
                    break;
                //已交流
                case "2":
                    queryBuilder.appendWhen(true, "AND pip.status = :pipStatus", 1)
                            .appendWhen(true, "AND pos.status = :posStatus", 0)
                            .appendWhen(true, "AND pos.WRITE_CONDITION = :writeCondition", 1);
                    break;
                //未交流
                case "3":
                    queryBuilder.appendWhen(true, "AND pip.status = :pipStatus", 1)
                            .appendWhen(true, "AND pos.status = :posStatus", 1);
                    break;
            }
        }
        Query query = queryBuilder.build();

        // 手動時間轉換...這邊硬+8小時，因JSON預設時區轉換差
        List<ProOrgSuggestionHistoryDTO> result = sqlExecutor.queryForList(query, ProOrgSuggestionHistoryDTO.class);


        if(result.size() >0){
            result.forEach(ProOrgSuggestionHistoryDTO ->{
                ProOrgSuggestionHistoryDTO.setTime(ProOrgSuggestionHistoryDTO.getTime().plusSeconds(28800));
                ProOrgSuggestionHistoryDTO.setCreateTime(ProOrgSuggestionHistoryDTO.getCreateTime().plusSeconds(28800));
            });
        }

        // 流水號
        int countid = 1;
        for(int i=0; i <result.size();i++){
            result.get(i).setId(countid);
            countid++;
        }

        return result;
    }

    @Override
    public Page<ProOrgSuggestionHistoryDTO> findAllByCriteria(ProOrgSuggestionHistoryCriteria criteria) {
        Query.Builder queryTotalCount = Query
                .builder()
                .append(" SELECT COUNT(*)" +
                        " FROM PRO_ORG_SUGGESTION pos " +
                        "JOIN PRO_INNOVATIVE_PRODUCT pip ON pos.PRO_INNOVATIVE_PRODUCT_ID = pip.PRO_INNOVATIVE_PRODUCT_ID " +
                        "JOIN ADM_ACCOUNT aa ON aa.USER_ID = pip.USER_ID " +
                        "JOIN ADM_COMPANY ac ON aa.COMP_IDNO = ac.COMP_IDNO  " +
                        "JOIN ADM_AGENT ag ON pos.ORG_ID = ag.ORG_ID WHERE 1=1 ")
                .appendWhen(StringUtils.isNotBlank(criteria.getInnovateName()), " AND pip.INNOVATE_NAME LIKE :innovateName", "%" + criteria.getInnovateName() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getCompanyName()), " AND ac.NAME LIKE :companyName", "%" + criteria.getCompanyName() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getCommunicateOrganName()), " AND ag.ORG_NAME LIKE :communicateOrganName ", "%" + criteria.getCommunicateOrganName() + "%");

        if ((Optional.ofNullable(criteria.getCommunicateDateStart())).equals(Optional.ofNullable(criteria.getCommunicateDateEnd()))) {
            if (Optional.ofNullable(criteria.getCommunicateDateStart()).isPresent()) {
                queryTotalCount.append(" AND pos.TIME BETWEEN :communicateDateStart AND DATEADD(DAY,1,:communicateDateStart) ", criteria.getCommunicateDateStart().plusSeconds(28800).toString().substring(0, 10), criteria.getCommunicateDateStart().plusSeconds(28800).toString().substring(0, 10));
            }
        } else {

            if (Optional.ofNullable(criteria.getCommunicateDateStart()).isPresent()) {
                queryTotalCount.append(" AND pos.TIME >= :communicateDateStart ", criteria.getCommunicateDateStart().plusSeconds(28800).toString().substring(0, 10));
            }
            if (Optional.ofNullable(criteria.getCommunicateDateEnd()).isPresent()) {
                queryTotalCount.append(" AND pos.TIME <= :communicateDateEnd ", criteria.getCommunicateDateEnd().plusSeconds(28800).toString().substring(0, 10));
            }
        }

        if ((Optional.ofNullable(criteria.getCreateTimeStart())).equals(Optional.ofNullable(criteria.getCreateTimeEnd()))) {
            if (Optional.ofNullable(criteria.getCreateTimeStart()).isPresent()) {
                queryTotalCount.append(" AND pip.CREATE_TIME BETWEEN :createTimeStart AND DATEADD(DAY,1,:createTimeStart)", criteria.getCreateTimeStart().plusSeconds(28800).toString().substring(0, 10), criteria.getCreateTimeStart().plusSeconds(28800).toString().substring(0, 10));
            }
        } else {

            if (Optional.ofNullable(criteria.getCreateTimeStart()).isPresent()) {
                queryTotalCount.append(" AND pip.CREATE_TIME >= :createTimeStart ", criteria.getCreateTimeStart().plusSeconds(28800).toString().substring(0, 10));
            }
            if (Optional.ofNullable(criteria.getCreateTimeEnd()).isPresent()) {
                queryTotalCount.append(" AND pip.CREATE_TIME <= :createTimeEnd ", criteria.getCreateTimeEnd().plusSeconds(28800).toString().substring(0, 10));
            }
        }


        //                交流狀態是用pro_org 及 pro_inno 中的status判斷
        if (Optional.ofNullable(criteria.getCommunicateStatus()).isPresent()) {
            switch (criteria.getCommunicateStatus()) {
                // 全部
                case "0":
                    break;
                //交流中
                case "1":
                    queryTotalCount.appendWhen(true, "AND pip.status = :pipStatus", 1)
                            .appendWhen(true, "AND (pos.status is NULL OR pos.status = :posStatus", 0)
                            .appendWhen(true, "AND pos.WRITE_CONDITION = :writeCondition)", 0);
                    break;
                //已交流
                case "2":
                    queryTotalCount.appendWhen(true, "AND pip.status = :pipStatus", 1)
                            .appendWhen(true, "AND pos.status = :posStatus", 0)
                            .appendWhen(true, "AND pos.WRITE_CONDITION = :writeCondition", 1);
                    break;
                //未交流
                case "3":
                    queryTotalCount.appendWhen(true, "AND pip.status = :pipStatus", 1)
                            .appendWhen(true, "AND pos.status = :posStatus", 1);
                    break;
            }
        }
        Query totalQuery = queryTotalCount.build();
        int totalCount = sqlExecutor.queryForList(totalQuery, Integer.class).get(0);

        Query.Builder queryBuilder = Query
                .builder()
                .append(" SELECT pos.PRO_INNOVATIVE_PRODUCT_ID,pip.INNOVATE_NAME,ac.NAME,ag.ORG_NAME,pos.TIME,pip.CREATE_TIME" +
                        " FROM PRO_ORG_SUGGESTION pos " +
                        "JOIN PRO_INNOVATIVE_PRODUCT pip ON pos.PRO_INNOVATIVE_PRODUCT_ID = pip.PRO_INNOVATIVE_PRODUCT_ID " +
                        "JOIN ADM_ACCOUNT aa ON aa.USER_ID = pip.USER_ID " +
                        "JOIN ADM_COMPANY ac ON aa.COMP_IDNO = ac.COMP_IDNO  " +
                        "JOIN ADM_AGENT ag ON pos.ORG_ID = ag.ORG_ID WHERE 1=1 ")
                .appendWhen(StringUtils.isNotBlank(criteria.getInnovateName()), " AND pip.INNOVATE_NAME LIKE :innovateName", "%" + criteria.getInnovateName() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getCompanyName()), " AND ac.NAME LIKE :companyName", "%" + criteria.getCompanyName() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getCommunicateOrganName()), " AND ag.ORG_NAME LIKE :communicateOrganName ", "%" + criteria.getCommunicateOrganName() + "%");

        if ((Optional.ofNullable(criteria.getCommunicateDateStart())).equals(Optional.ofNullable(criteria.getCommunicateDateEnd()))) {
            if (Optional.ofNullable(criteria.getCommunicateDateStart()).isPresent()) {
                queryBuilder.append(" AND pos.TIME BETWEEN :communicateDateStart AND DATEADD(DAY,1,:communicateDateStart)", criteria.getCommunicateDateStart().plusSeconds(28800).toString().substring(0, 10), criteria.getCommunicateDateStart().plusSeconds(28800).toString().substring(0, 10));
            }
        } else {
            if (Optional.ofNullable(criteria.getCommunicateDateStart()).isPresent()) {
                queryBuilder.append(" AND pos.TIME >= :communicateDateStart ", criteria.getCommunicateDateStart().plusSeconds(28800).toString().substring(0, 10));
            }
            if (Optional.ofNullable(criteria.getCommunicateDateEnd()).isPresent()) {
                queryBuilder.append(" AND pos.TIME <= :communicateDateEnd ", criteria.getCommunicateDateEnd().plusSeconds(28800).toString().substring(0, 10));
            }
        }

        if ((Optional.ofNullable(criteria.getCreateTimeStart())).equals(Optional.ofNullable(criteria.getCreateTimeEnd()))) {
            if (Optional.ofNullable(criteria.getCreateTimeStart()).isPresent()) {
                queryBuilder.append(" AND pip.CREATE_TIME BETWEEN :createTimeStart AND DATEADD(DAY,1,:createTimeStart)", criteria.getCreateTimeStart().plusSeconds(28800).toString().substring(0, 10), criteria.getCreateTimeStart().plusSeconds(28800).toString().substring(0, 10));
            }
        } else {

            if (Optional.ofNullable(criteria.getCreateTimeStart()).isPresent()) {
                queryBuilder.append(" AND pip.CREATE_TIME >= :createTimeStart ", criteria.getCreateTimeStart().plusSeconds(28800).toString().substring(0, 10));
            }
            if (Optional.ofNullable(criteria.getCreateTimeEnd()).isPresent()) {
                queryBuilder.append(" AND pip.CREATE_TIME <= :createTimeEnd ", criteria.getCreateTimeEnd().plusSeconds(28800).toString().substring(0, 10));
            }
        }


        //                交流狀態是用pro_org 及 pro_inno 中的status判斷
        if (Optional.ofNullable(criteria.getCommunicateStatus()).isPresent()) {
            switch (criteria.getCommunicateStatus()) {
                // 全部
                case "0":
                    break;
                //交流中
                case "1":
                    queryBuilder.appendWhen(true, "AND pip.status = :pipStatus", 1)
                            .appendWhen(true, "AND (pos.status is NULL OR pos.status = :posStatus", 0)
                            .appendWhen(true, "AND pos.WRITE_CONDITION = :writeCondition)", 0);
                    break;
                //已交流
                case "2":
                    queryBuilder.appendWhen(true, "AND pip.status = :pipStatus", 1)
                            .appendWhen(true, "AND pos.status = :posStatus", 0)
                            .appendWhen(true, "AND pos.WRITE_CONDITION = :writeCondition", 1);
                    break;
                //未交流
                case "3":
                    queryBuilder.appendWhen(true, "AND pip.status = :pipStatus", 1)
                            .appendWhen(true, "AND pos.status = :posStatus", 1);
                    break;
            }
        }
        queryBuilder.append("order by " + criteria.getOrderBy());
        queryBuilder.append("offset :currentPage * :perPage rows fetch next :perPage rows only",
                criteria.getCurrentPage(),
                criteria.getPerPage(),
                criteria.getPerPage());

        Query query = queryBuilder.build();
        List<ProOrgSuggestionHistoryDTO> proOrgSuggestionHistoryDTOs = sqlExecutor.queryForList(query, ProOrgSuggestionHistoryDTO.class);
        // 手動時間轉換...這邊硬+8小時，因JSON預設時區轉換差
        if (proOrgSuggestionHistoryDTOs.size() > 0) {
            proOrgSuggestionHistoryDTOs.forEach(ProOrgSuggestionHistoryDTO -> {
                ProOrgSuggestionHistoryDTO.setTime(ProOrgSuggestionHistoryDTO.getTime().plusSeconds(28800));
                ProOrgSuggestionHistoryDTO.setCreateTime(ProOrgSuggestionHistoryDTO.getCreateTime().plusSeconds(28800));
            });
        }

        int countId =1;
        for(int i=0;i<proOrgSuggestionHistoryDTOs.size();i++){
            proOrgSuggestionHistoryDTOs.get(i).setId(countId);
            countId++;
        }

        Page<ProOrgSuggestionHistoryDTO> resultPage = new PageImpl<>(proOrgSuggestionHistoryDTOs, criteria.toPageable(), totalCount);

        return resultPage;
    }

    @Override
    public List<ComStatusSearchDTO> findAllByCriteria(ProOrgSuggestionCriteria criteria) {
        Query query = Query
                .builder()
                .append(" SELECT org.PRO_INNOVATIVE_PRODUCT_ID, inn.INNOVATE_NAME, com.NAME, agt.ORG_NAME, org.STATUS, org.WRITE_CONDITION")
                .append(" FROM PRO_ORG_SUGGESTION org")
                .append(" JOIN PRO_INNOVATIVE_PRODUCT inn ON org.PRO_INNOVATIVE_PRODUCT_ID = inn.PRO_INNOVATIVE_PRODUCT_ID")
                .append(" JOIN ADM_ACCOUNT acc ON inn.USER_ID = acc.USER_ID")
                .append(" JOIN ADM_AGENT agt ON org.ORG_ID = agt.ORG_ID ")
                .append(" JOIN ADM_COMPANY com ON acc.COMP_IDNO = com.COMP_IDNO")
                .append(" WHERE 1=1 ")
                .appendWhen(StringUtils.isNotBlank(criteria.getInnovateName()), " AND inn.INNOVATE_NAME LIKE :innovateName ","%" + criteria.getInnovateName() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getName()), " AND com.NAME LIKE :name ","%" + criteria.getName() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getOrgName()), " AND agt.ORG_NAME LIKE :orgName ","%" + criteria.getOrgName() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getStatus()), " AND org.STATUS = :status ", criteria.getStatus())
                .appendWhen(StringUtils.isNotBlank(criteria.getWriteCondition()), " AND org.WRITE_CONDITION = :writeCondition ", criteria.getWriteCondition())
                .build();

        List<ComStatusSearchDTO> ComStatusSearchDTOs = sqlExecutor.queryForList(query, ComStatusSearchDTO.class);
        return ComStatusSearchDTOs;
    }

    @Override
    public Page<ComStatusSearchDTO> findPageByCriteria(ProOrgSuggestionCriteria criteria) {
        Query queryTotalCount = Query
                .builder()
                .append(" SELECT COUNT(*) FROM PRO_ORG_SUGGESTION org")
                .append(" JOIN PRO_INNOVATIVE_PRODUCT inn ON org.PRO_INNOVATIVE_PRODUCT_ID = inn.PRO_INNOVATIVE_PRODUCT_ID")
                .append(" JOIN ADM_ACCOUNT acc ON inn.USER_ID = acc.USER_ID")
                .append(" JOIN ADM_AGENT agt ON org.ORG_ID = agt.ORG_ID ")
                .append(" JOIN ADM_COMPANY com ON acc.COMP_IDNO = com.COMP_IDNO")
                .append(" WHERE 1=1 ")
                .appendWhen(StringUtils.isNotBlank(criteria.getInnovateName()), " AND inn.INNOVATE_NAME LIKE :innovateName ","%" + criteria.getInnovateName() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getName()), " AND com.NAME LIKE :name ","%" + criteria.getName() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getOrgName()), " AND agt.ORG_NAME LIKE :orgName ","%" + criteria.getOrgName() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getStatus()), " AND org.STATUS = :status ", criteria.getStatus())
                .appendWhen(StringUtils.isNotBlank(criteria.getWriteCondition()), " AND org.WRITE_CONDITION = :writeCondition ", criteria.getWriteCondition())
                .build();

        int totalCount = sqlExecutor.queryForList(queryTotalCount, Integer.class).get(0);

        Query query = Query
                .builder()
                .append(" SELECT org.PRO_INNOVATIVE_PRODUCT_ID, inn.INNOVATE_NAME, com.NAME, agt.ORG_NAME, org.STATUS, org.WRITE_CONDITION")
                .append(" FROM PRO_ORG_SUGGESTION org")
                .append(" JOIN PRO_INNOVATIVE_PRODUCT inn ON org.PRO_INNOVATIVE_PRODUCT_ID = inn.PRO_INNOVATIVE_PRODUCT_ID")
                .append(" JOIN ADM_ACCOUNT acc ON inn.USER_ID = acc.USER_ID")
                .append(" JOIN ADM_AGENT agt ON org.ORG_ID = agt.ORG_ID ")
                .append(" JOIN ADM_COMPANY com ON acc.COMP_IDNO = com.COMP_IDNO")
                .append(" WHERE 1=1 ")
                .appendWhen(StringUtils.isNotBlank(criteria.getInnovateName()), " AND inn.INNOVATE_NAME LIKE :innovateName ","%" + criteria.getInnovateName() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getName()), " AND com.NAME LIKE :name ","%" + criteria.getName() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getOrgName()), " AND agt.ORG_NAME LIKE :orgName ","%" + criteria.getOrgName() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getStatus()), " AND org.STATUS = :status ", criteria.getStatus())
                .appendWhen(StringUtils.isNotBlank(criteria.getWriteCondition()), " AND org.WRITE_CONDITION = :writeCondition ", criteria.getWriteCondition())
                .append("order by " + criteria.getOrderBy())
                .append(
                        "offset :currentPage * :perPage rows fetch next :perPage rows only",
                        criteria.getCurrentPage(),
                        criteria.getPerPage(),
                        criteria.getPerPage()
                )
                .build();

        List<ComStatusSearchDTO> ComStatusSearchDTOs = sqlExecutor.queryForList(query, ComStatusSearchDTO.class);
        Page<ComStatusSearchDTO> resultPage = new PageImpl<>(ComStatusSearchDTOs, criteria.toPageable(), totalCount);
        return resultPage;
    }
}
