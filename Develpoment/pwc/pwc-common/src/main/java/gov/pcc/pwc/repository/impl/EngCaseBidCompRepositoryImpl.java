package gov.pcc.pwc.repository.impl;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.repository.custom.EngCaseBidCompRepositoryCustom;
import gov.pcc.pwc.service.criteria.EngCaseTotalDTOCriteria;
import gov.pcc.pwc.service.dto.EngCaseTotalDTO;
import gov.pcc.pwc.service.dto.EngCaseTotalDTO.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.xmlbeans.impl.common.NameUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;

public class EngCaseBidCompRepositoryImpl implements EngCaseBidCompRepositoryCustom {

    private final SqlExecutor sqlExecutor;

    public EngCaseBidCompRepositoryImpl(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    private final Logger log = LoggerFactory.getLogger(EngCaseBidCompRepositoryImpl.class);

    @Override
    public Page<EngCaseTotalDTO> EngCaseTotalDTOR1(EngCaseTotalDTOCriteria criteria) {
        Query queryTotalCount = Query.builder()
//                .append("SELECT COUNT(A.ENG_CASE_ID) FROM [dbo].[ENG_CASE] A")
                .append("SELECT COUNT (A.ENG_CASE_ID) FROM [dbo].[ENG_CASE] A")
                .append(" LEFT JOIN [dbo].[ENG_CASE_BID_COMP] B ON A.ENG_CASE_ID = B.ENG_CASE_ID")
                .append(" AND B.ITEM_NO IN (select top 1 ITEM_NO as BIDCOMP_ITEM_NO")
                .append(" from [dbo].[ENG_CASE_ENGR] order by ITEM_NO)")
                .append(" LEFT JOIN [dbo].[ENG_CASE_ENGR] C ON C.ENG_CASE_ID = A.ENG_CASE_ID")
                .append(" AND C.ITEM_NO IN (select top 1 ITEM_NO as ENGR_ITEM_NO")
                .append(" from [dbo].[ENG_CASE_ENGR] order by ITEM_NO)")
                .append(" LEFT JOIN [dbo].[ENG_CASE_ENGR_RECORD] D ON D.ENG_CASE_ID = C.ENG_CASE_ID")
                .append(" AND C.ITEM_NO IN (select top 1 ITEM_NO as RECORD_ITEM_NO")
                .append(" from [dbo].[ENG_CASE_ENGR_RECORD] order by ITEM_NO)")
                .append(" WHERE 1 = 1")
                .appendWhen(StringUtils.isNotBlank(criteria.getCaseType()),
                        " AND A.CASE_TYPE = :caseType", criteria.getCaseType())
                .appendWhen(StringUtils.isNotBlank(criteria.getRecordItemsList()),
                        " AND A.RECORD_ITEMS_LIST = :itemsList", criteria.getRecordItemsList())
                .appendWhen(StringUtils.isNotBlank(criteria.getProjectType()),
                        " AND A.PROJECT_TYPE = :projectType", criteria.getProjectType())
                .appendWhen(StringUtils.isNotBlank(criteria.getCaseNo()),
                        " AND A.CASE_NO LIKE :caseNo", "%" + criteria.getCaseNo() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getCaseName()),
                        " AND A.CASE_NAME LIKE :caseName", "%" + criteria.getCaseName() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getExecOrgName()),
                        " AND A.EXEC_ORG_NAME = :execOrgName", criteria.getExecOrgName())
                .appendWhen(StringUtils.isNotBlank(criteria.getExecOrgId()),
                        " AND A.EXEC_ORG_ID = :execOrgId", criteria.getExecOrgId())
                .appendWhen(criteria.getExecDateStart() != null,
                        " AND A.EXEC_DATE_START >= :dateStart", criteria.getTenderDateStart())
                .appendWhen(criteria.getExecDateEnd() != null,
                        " AND A.EXEC_DATE_END <= :dateEnd", criteria.getExecDateEnd())
                .appendWhen(criteria.getTenderDateStart() != null,
                        " AND A.TENDER_DATE >= :tDateStart", criteria.getTenderDateStart())
                .appendWhen(criteria.getTenderDateEnd() != null,
                        " AND A.TENDER_DATE <= :tDateEnd", criteria.getTenderDateEnd())
                .appendWhen(StringUtils.isNotBlank(criteria.getCompName()),
                        " AND B.COMP_NAME LIKE :compName", "%" + criteria.getCompName() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getCompIdno()),
                        " AND B.COMP_IDNO = :compIdno", criteria.getCompIdno())
                .appendWhen(StringUtils.isNotBlank(criteria.getEngrName()),
                        " AND C.ENGR_NAME LIKE :engrName", "%" + criteria.getEngrName() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getEngrId()),
                        " AND C.ENGR_ID = :engrId", criteria.getEngrId())
                .build();
        int totalCount = sqlExecutor.queryForList(queryTotalCount, Integer.class).get(0);

        Query query = Query.builder()
                .append("SELECT A.ENG_CASE_ID, A.CASE_NO,")
                .append("A.TENDER_NO, A.AWARD_NO, A.CASE_TYPE, A.CASE_NAME,")
                .append("A.ORG_ID, A.ORG_NAME, A.EXEC_ORG_ID, A.EXEC_ORG_NAME,")
                .append("A.CONTACT_PERSON, A.CONTACT_TEL, A.ENTRUST_ITEM, A.TENDER_DATE,")
                .append("A.TENDER_PRICE, A.EXEC_DATE_START, A.EXEC_DATE_END, A.PROJECT_TYPE,")
                .append("A.RECORD_RANGE_LIST, A.RECORD_RANGE_OTHER, A.RECORD_ITEMS_LIST,")
                .append("A.RECORD_ITEMS_OTHER, A.REMARK,")
                .append(" CASE WHEN B.ITEM_NO IS NULL THEN 1 ELSE B.ITEM_NO END AS COMP_ITEM ")
                .append(", B.COMP_ID, B.COMP_IDNO,")
                .append(" B.COMP_NAME, B.COMP_LICENSE_NO, C.ENGR_ID, C.ENGR_NAME, C.ENGR_NAME_ROME, ")
                .append(" CASE WHEN C.ITEM_NO IS NULL THEN 1 ELSE C.ITEM_NO END AS ENGR_ITEM")
                .append(",C.ENG_ENGR_CERTIFICATE_ID_LIST, C.DOC_NO, C.CLOSE_DATE, C.STATUS, D.EXPECT_DATE,")
                .append("D.REAL_TIME, D.RECORD_CONTENT, D.RECORD_COMMENT, D.SIGN_DATE, E.[SUBJECT],")
                .append("A.UPDATE_TIME, A.UPDATE_USER, A.CREATE_TIME, A.CREATE_USER,")
                .append("A.TRANSFER_FROM, A.TRANSFER_TIME FROM [dbo].[ENG_CASE] A")
                .append(" LEFT JOIN [dbo].[ENG_CASE_BID_COMP] B ON A.ENG_CASE_ID = B.ENG_CASE_ID")
//                .append(" AND B.ITEM_NO IN (select top 1 ITEM_NO as BIDCOMP_ITEM_NO ")
//                .append(" from [dbo].[ENG_CASE_ENGR] order by ITEM_NO)")
                .append(" LEFT JOIN [dbo].[ENG_CASE_ENGR] C ON C.ENG_CASE_ID = A.ENG_CASE_ID")
                .append(" AND C.COMP_ID = B.COMP_ID")
//                .append(" AND C.ITEM_NO IN (select top 1 ITEM_NO as ENGR_ITEM_NO")
//                .append(" from [dbo].[ENG_CASE_ENGR] order by ITEM_NO)")
                .append(" LEFT JOIN [dbo].[ENG_CASE_ENGR_RECORD] D ON D.ENG_CASE_ID = A.ENG_CASE_ID")
                .append(" AND C.ITEM_NO IN (select top 1 ITEM_NO as RECORD_ITEM_NO")
                .append(" from [dbo].[ENG_CASE_ENGR_RECORD] order by ITEM_NO)")
                .append("LEFT JOIN [dbo].[ENG_ENGR_CERTIFICATE] E ON C.ENG_ENGR_CERTIFICATE_ID_LIST")
                .append("LIKE E.ENG_ENGR_CERTIFICATE_ID")
                .append(" WHERE 1 = 1")
                .appendWhen(StringUtils.isNotBlank(criteria.getCaseType()),
                        " AND A.CASE_TYPE = :caseType", criteria.getCaseType())
                .appendWhen(StringUtils.isNotBlank(criteria.getRecordItemsList()),
                        " AND A.RECORD_ITEMS_LIST = :itemsList", criteria.getRecordItemsList())
                .appendWhen(StringUtils.isNotBlank(criteria.getProjectType()),
                        " AND A.PROJECT_TYPE = :projectType", criteria.getProjectType())
                .appendWhen(StringUtils.isNotBlank(criteria.getCaseNo()),
                        " AND A.CASE_NO LIKE :caseNo", "%" + criteria.getCaseNo() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getCaseName()),
                        " AND A.CASE_NAME LIKE :caseName", "%" + criteria.getCaseName() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getExecOrgName()),
                        " AND A.EXEC_ORG_NAME = :execOrgName", criteria.getExecOrgName())
                .appendWhen(StringUtils.isNotBlank(criteria.getExecOrgId()),
                        " AND A.EXEC_ORG_ID = :execOrgId", criteria.getExecOrgId())
                .appendWhen(criteria.getExecDateStart() != null,
                        " AND A.EXEC_DATE_START >= :dateStart", criteria.getTenderDateStart())
                .appendWhen(criteria.getExecDateEnd() != null,
                        " AND A.EXEC_DATE_END <= :dateEnd", criteria.getExecDateEnd())
                .appendWhen(criteria.getTenderDateStart() != null,
                        " AND A.TENDER_DATE >= :tDateStart", criteria.getTenderDateStart())
                .appendWhen(criteria.getTenderDateEnd() != null,
                        " AND A.TENDER_DATE <= :tDateEnd", criteria.getTenderDateEnd())
                .appendWhen(StringUtils.isNotBlank(criteria.getCompName()),
                        " AND B.COMP_NAME LIKE :compName", "%" + criteria.getCompName() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getCompIdno()),
                        " AND B.COMP_IDNO = :compIdno", criteria.getCompIdno())
                .appendWhen(StringUtils.isNotBlank(criteria.getEngrName()),
                        " AND C.ENGR_NAME LIKE :engrName", "%" + criteria.getEngrName() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getEngrId()),
                        " AND C.ENGR_ID = :engrId", criteria.getEngrId())
                .append("order by " + criteria.getOrderBy())
                .append(
                        "offset :currentPage * :perPage rows fetch next :perPage rows only",
                        criteria.getCurrentPage(),
                        criteria.getPerPage(),
                        criteria.getPerPage()
                )
                .build();
        List<EngCaseTotalDTO> tempList = sqlExecutor.queryForList(query, EngCaseTotalDTO.class);
        List<EngCaseTotalDTO> list = new ArrayList<>();  // 案件陣列
        list.add(tempList.get(0));
        list.get(0).getCompList().add(0, new Comp(tempList.get(0).getCompName(), tempList.get(0).getCompName())); // 設該案件第一個公司名稱
        list.get(0).getEngrList().add(0,  // 設該案件第一位技師姓名
                new Engr(tempList.get(0).getEngrId(), tempList.get(0).getEngrName(),
                        tempList.get(0).getSubject(), tempList.get(0).getRecordRangeList(),
                        tempList.get(0).getRecordItemsList(), tempList.get(0).getExecDateStart(),
                        tempList.get(0).getExecDateEnd(), tempList.get(0).getSignDate(),
                        tempList.get(0).getCloseDate(), tempList.get(0).getUpdateTime(),
                        tempList.get(0).getRecordItemsOther(), tempList.get(0).getRecordRangeOther()));
//        int listCount = 1;  // list的計數器
        for (int i = 1; i < tempList.size(); i++) {
            if (!tempList.get(i).getEngCaseId().equals(tempList.get(i - 1).getEngCaseId())) {
                list.add(tempList.get(i));  // pk不同才add進list
                // size-1 就是當前項
                list.get(list.size() - 1).getCompList().add(0, new Comp(tempList.get(i).getCompId(), tempList.get(i).getCompName())); // 設該案件第一個公司名稱
                list.get(list.size() - 1).getEngrList().add(0,  // 設該案件第一位技師姓名
                        new Engr(tempList.get(i).getEngrId(), tempList.get(i).getEngrName(),
                                tempList.get(i).getSubject(), tempList.get(i).getRecordRangeList(),
                                tempList.get(i).getRecordItemsList(), tempList.get(i).getExecDateStart(),
                                tempList.get(i).getExecDateEnd(), tempList.get(i).getSignDate(),
                                tempList.get(i).getCloseDate(), tempList.get(i).getUpdateTime(),
                                tempList.get(i).getRecordItemsOther(), tempList.get(i).getRecordRangeOther()));
            } else {
                List<Comp> tempComps = list.get(list.size() - 1).getCompList();  // 此案件的公司陣列
                List<Engr> tempEngrs = list.get(list.size() - 1).getEngrList(); // 此案件技師陣列
                if (!tempList.get(i).getCompName().equals(tempComps.get(tempComps.size()-1).getCompName())) {
                    // 公司陣列該項內名稱跟前一個名稱不一樣的話，就新增
                    list.get(list.size()-1).getCompList().add(tempComps.size()-1, new Comp(tempList.get(i).getCompId(), tempList.get(i).getCompName()));
                }   // 技師陣列該項內名稱跟前一個名稱不一樣的話，就新增
                list.get(list.size()-1).getEngrList().add(tempEngrs.size()-1,
                        new Engr(tempList.get(i).getEngrId(), tempList.get(i).getEngrName(),
                                tempList.get(i).getSubject(), tempList.get(i).getRecordRangeList(),
                                tempList.get(i).getRecordItemsList(), tempList.get(i).getExecDateStart(),
                                tempList.get(i).getExecDateEnd(), tempList.get(i).getSignDate(),
                                tempList.get(i).getCloseDate(), tempList.get(i).getUpdateTime(),
                                tempList.get(i).getRecordItemsOther(), tempList.get(i).getRecordRangeOther()));
            }
        }
        log.debug("EngCaseTotalDTOR1" + list);
        return new PageImpl<EngCaseTotalDTO>(list, criteria.toPageable(), totalCount);
    }
}
