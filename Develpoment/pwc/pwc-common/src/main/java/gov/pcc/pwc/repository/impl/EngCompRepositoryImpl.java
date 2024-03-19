package gov.pcc.pwc.repository.impl;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.repository.custom.EngCompRepositoryCustom;
import gov.pcc.pwc.service.criteria.Eng0822R1DTOCriteria;
import gov.pcc.pwc.service.dto.Eng0822R1DTO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.*;

public class EngCompRepositoryImpl implements EngCompRepositoryCustom {

    private final SqlExecutor sqlExecutor;

    public EngCompRepositoryImpl(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    private final Logger log = LoggerFactory.getLogger(EngCompRepositoryImpl.class);

    // 列出全部顧問公會資訊
    @Override
    public Page<Eng0822R1DTO> findEng0822R1(Eng0822R1DTOCriteria criteria) {
        Query queryTotalCount = Query.builder()
                .append("SELECT COUNT(*) FROM ENG_COMP A")
                .append("LEFT JOIN ENG_COMP_CONTACT B ON A.ENG_COMP_ID = B.ENG_COMP_ID AND (B.ITEM_NO = 0 OR B.ITEM_NO is null)")
                .append("LEFT JOIN ENG_MEMBER C on A.ENGR_ID_LIST = C.ENG_MEMBER_ID")
                .append("LEFT JOIN ENG_CST_LICENSE D on A.ENG_COMP_ID = D.COMP_ID")
                .append("and D.ENG_CST_LICENSE_VERSION =")
                .append("(select max(ENG_CST_LICENSE_VERSION) from ENG_CST_LICENSE where COMP_ID = D.COMP_ID)")
                .append("WHERE A.TYPE = '6' AND A.IS_USE = 1")
                .build();
        int totalCount = sqlExecutor.queryForList(queryTotalCount, Integer.class).get(0);

        Query query = Query.builder()
                .append("SELECT distinct A.*, B.TEL1, B.TEL2, B.FAX, B.MAIL1,")
                .append("B.ADDRESS1_ZIP, B.ADDRESS1_CITY, B.ADDRESS1_TOWN, B.ADDRESS1_OTHER, C.IS_ENGR")
                .append("AS Master_IsEngr FROM ENG_COMP A")
                .append("LEFT JOIN ENG_COMP_CONTACT B ON A.ENG_COMP_ID = B.ENG_COMP_ID AND (B.ITEM_NO = 0 OR B.ITEM_NO is null)")
                .append("LEFT JOIN ENG_MEMBER C on A.ENGR_ID_LIST = C.ENG_MEMBER_ID")
                .append("LEFT JOIN ENG_CST_LICENSE D on A.ENG_COMP_ID = D.COMP_ID")
                .append("and D.ENG_CST_LICENSE_VERSION =")
                .append("(select max(ENG_CST_LICENSE_VERSION) from ENG_CST_LICENSE where COMP_ID = D.COMP_ID)")
                .append("WHERE A.TYPE = '6' AND A.IS_USE = 1")
                .append("order by " + criteria.getOrderBy() + ", A.TYPE desc")
                .append("offset :currentPage * :perPage rows fetch next :perPage rows only",
                        criteria.getCurrentPage(),
                        criteria.getPerPage(),
                        criteria.getPerPage())
                .build();
        List<Eng0822R1DTO> list = sqlExecutor.queryForList(query, Eng0822R1DTO.class);
        Page<Eng0822R1DTO> page = new PageImpl<>(list, criteria.toPageable(), totalCount);
        log.debug("findEng0822R1: " + list);
        return page;
    }

    // 模糊查詢區域主管機關
    @Override
    public Page<Eng0822R1DTO> findEng0703(Eng0822R1DTOCriteria criteria) {
        Query queryTotalCount = Query.builder()
                .append("SELECT COUNT(*)")
                .append("FROM [dbo].[ENG_COMP] A")
                .append(" LEFT JOIN [dbo].[ENG_COMP_CONTACT] B ON A.ENG_COMP_ID = B.ENG_COMP_ID")
                .append("WHERE A.TYPE = '4'")
                .append("AND B.ITEM_NO= 0")
                .appendWhen(StringUtils.isNotBlank(criteria.getBosName()), " AND A.CH_NAME LIKE :chName ", "%" + criteria.getBosName() + "%")
                .build();
        int totalCount = sqlExecutor.queryForList(queryTotalCount, Integer.class).get(0);

        Query query = Query.builder()
                .append("SELECT A.ENG_COMP_ID, A.[CH_NAME],")
                .append("B.[TEL1], B.[TEL2], B.[FAX],")
                .append("B.[ADDRESS1_ZIP], B.[ADDRESS1_CITY],B.[ADDRESS1_TOWN],B.[ADDRESS1_OTHER]")
                .append("FROM [dbo].[ENG_COMP] A")
                .append(" LEFT JOIN [dbo].[ENG_COMP_CONTACT] B ON A.ENG_COMP_ID = B.ENG_COMP_ID")
                .append("WHERE A.TYPE = '4'")
                .append("AND B.ITEM_NO= 0")
                .appendWhen(StringUtils.isNotBlank(criteria.getBosName()), " AND A.CH_NAME LIKE :chName ", "%" + criteria.getBosName() + "%")
                .append("order by " + criteria.getOrderBy())
                .append("offset :currentPage * :perPage rows fetch next :perPage rows only",
                        criteria.getCurrentPage(),
                        criteria.getPerPage(),
                        criteria.getPerPage()
                )
                .build();
        List<Eng0822R1DTO> list = sqlExecutor.queryForList(query, Eng0822R1DTO.class);
        log.debug("findEng0703r1" + list);
        return new PageImpl<Eng0822R1DTO>(list, criteria.toPageable(), totalCount);
    }

    // 檢視主管機關單筆資料
    @Override
    public Eng0822R1DTO viewEng0703(String id) {
        Query query = Query.builder()
                .append("SELECT A.*, B.TEL1, B.TEL2, B.FAX, B.MAIL1, B.ADDRESS1_CITY,")
                .append("B.ADDRESS1_TOWN, B.ADDRESS1_OTHER, B.ADDRESS1_ZIP")
                .append("FROM [dbo].[ENG_COMP] A")
                .append(" LEFT JOIN ENG_COMP_CONTACT B ON A.ENG_COMP_ID = B.ENG_COMP_ID")
                .append(" AND (B.ITEM_NO = 0 OR B.ITEM_NO is null)")
                .appendWhen(StringUtils.isNotBlank(id), "WHERE A.ENG_COMP_ID = :id", id)
                .build();
        List<Eng0822R1DTO> list = sqlExecutor.queryForList(query, Eng0822R1DTO.class);
        log.debug("viewEng0703" + list.get(0));
        return list.get(0);
    }

    // 編輯該主管機關資料
    @Override
    public Eng0822R1DTO update0703BySql(Eng0822R1DTO eng0822R1DTO) {
        Query query1 = Query.builder()
                .append(" UPDATE ENG_COMP")
                .append(" SET CH_NAME = :na", eng0822R1DTO.getChName())
                .append(" ,IS_USE = :isu", eng0822R1DTO.getIsUse())
                .append(" ,REMARK = :remark", eng0822R1DTO.getRemark())
                .append(" ,UPDATE_TIME = :uptime", new Date())
                .append(" ,UPDATE_USER = :upuser", eng0822R1DTO.getUpdateUser())
                .append(" WHERE ENG_COMP_ID = :id", eng0822R1DTO.getEngCompId())
                .build();
        sqlExecutor.update(query1);

        Query query2 = Query.builder()
                .append(" UPDATE ENG_COMP_CONTACT")
                .append(" SET TEL1 = :tel1", eng0822R1DTO.getTel1())
                .append(" ,TEL2 = :tel2", eng0822R1DTO.getTel2())
                .append(" ,FAX = :fax", eng0822R1DTO.getFax())
                .append(" ,ADDRESS1_ZIP = :addz", eng0822R1DTO.getAddress1Zip())
                .append(" ,ADDRESS1_CITY = :addc", eng0822R1DTO.getAddress1City())
                .append(" ,ADDRESS1_TOWN = :addt", eng0822R1DTO.getAddress1Town())
                .append(" ,ADDRESS1_OTHER = :addo", eng0822R1DTO.getAddress1Other())
                .append(" ,MAIL1 = :mail1", eng0822R1DTO.getMail1())
                .append(" ,UPDATE_TIME = :uptime", new Date())
                .append(" ,UPDATE_USER = :upuser", eng0822R1DTO.getUpdateUser())
                .append(" WHERE ENG_COMP_ID = :id", eng0822R1DTO.getEngCompId())
                .build();
        sqlExecutor.update(query2);
        log.debug("implUpdateEng0703" + eng0822R1DTO);
        return viewEng0703(eng0822R1DTO.getEngCompId());
    }


    // 新增主管機關
    @Override
    public Eng0822R1DTO createEng0703(Eng0822R1DTO dto) {
        // 因為是主管機關，類型寫死為4
        Query query1 = Query.builder()
                .append("INSERT INTO [ENG_COMP] ([ENG_COMP_ID], [CH_NAME],")
                .append(" [TYPE], [IS_USE], [REMARK], [UPDATE_TIME],")
                .append("[UPDATE_USER], [CREATE_TIME], [CREATE_USER])")
                .append("VALUES (")
                .append(":compId, :chName,", dto.getEngCompId(), dto.getChName())
                .append(":engType, :isUse,", "4", dto.getIsUse())
                .append(":remark,", dto.getRemark())
                .append(":upTime, :upUser,", new Date(), dto.getUpdateUser())
                .append(":crTime, :crUser)", new Date(), dto.getCreateUser())
                .build();
        sqlExecutor.insert(query1);

        Query query2 = Query.builder()
                .append("INSERT INTO [ENG_COMP_CONTACT] ([ENG_COMP_ID], [ITEM_NO],")
                .append(" [TEL1], [TEL2], [FAX], [MAIL1],")
                .append(" [ADDRESS1_ZIP], [ADDRESS1_CITY], [ADDRESS1_TOWN], [ADDRESS1_OTHER],")
                .append(" [UPDATE_TIME], [UPDATE_USER], [CREATE_TIME], [CREATE_USER])")
                .append("VALUES (")
                .append(":id, :itemNo,", dto.getEngCompId(), dto.getItemNo())
                .append(":tel1, :tel2,", dto.getTel1(), dto.getTel2())
                .append(":fax, :mail1,", dto.getFax(), dto.getMail1())
                .append(":add1City, :add1Town,", dto.getAddress1City(), dto.getAddress1Town())
                .append(":add1Other, :add1Zip,", dto.getAddress1Other(), dto.getAddress1Zip())
                .append(":upTime, :upUser,", new Date(), dto.getUpdateUser())
                .append(":crTime, :crUser)", new Date(), dto.getCreateUser())
                .build();
        sqlExecutor.insert(query2);
        log.debug("implCreateEng0703" + dto);
        return viewEng0703(dto.getEngCompId());
    }

    // 用userId檢視自己公會資料
    @Override
    public Eng0822R1DTO viewEng0506(String id) {
        Query query = Query.builder()
                .append("SELECT * FROM [dbo].[ENG_COMP] A")
                .append(" LEFT JOIN [dbo].[ENG_COMP_CONTACT] B")
                .append(" ON A.ENG_COMP_ID = B.ENG_COMP_ID")
                .append("WHERE A.USER_ID LIKE :id", "%" + id + "%")
                .build();
        List<Eng0822R1DTO> list = sqlExecutor.queryForList(query, Eng0822R1DTO.class);
        Eng0822R1DTO dto;
        if(list.get(0).getItemNo() == 1) {  // 若list[0]是秘書長資料
            dto = list.get(1);
            dto.setPersonName(list.get(0).getPersonName());
        } else {
            dto = list.get(0);
            dto.setPersonName(list.get(1).getPersonName());
        }
        log.debug("implViewEng0506" + dto);
        return dto;
    }

    // 編輯自己公會資料
    @Override
    public Eng0822R1DTO updateEng0506(Eng0822R1DTO eng0822R1DTO) {
        Query queryComp = Query.builder()
                .append(" UPDATE ENG_COMP")
                .append(" SET CH_NAME = :na", eng0822R1DTO.getChName())
                .append(" ,UPDATE_TIME = :uptime", new Date())
                .append(" ,UPDATE_USER = :upuser", eng0822R1DTO.getUpdateUser())
                .append(" WHERE ENG_COMP_ID = :id", eng0822R1DTO.getEngCompId())
                .build();
        sqlExecutor.update(queryComp);

        Query queryContact1 = Query.builder()
                .append(" UPDATE ENG_COMP_CONTACT")
                .append(" SET PERSON_NAME = :masterName", eng0822R1DTO.getMasterName())
                .append(" ,TEL1 = :tel1", eng0822R1DTO.getTel1())
                .append(" ,TEL2 = :tel2", eng0822R1DTO.getTel2())
                .append(" ,FAX = :fax", eng0822R1DTO.getFax())
                .append(" ,ADDRESS1_ZIP = :addz", eng0822R1DTO.getAddress1Zip())
                .append(" ,ADDRESS1_CITY = :addc", eng0822R1DTO.getAddress1City())
                .append(" ,ADDRESS1_TOWN = :addt", eng0822R1DTO.getAddress1Town())
                .append(" ,ADDRESS1_OTHER = :addo", eng0822R1DTO.getAddress1Other())
                .append(" ,MAIL1 = :mail1", eng0822R1DTO.getMail1())
                .append(" ,UPDATE_TIME = :uptime", new Date())
                .append(" ,UPDATE_USER = :upuser", eng0822R1DTO.getUpdateUser())
                .append(" WHERE ENG_COMP_ID = :id", eng0822R1DTO.getEngCompId())
                .append(" AND ITEM_NO = '0'")
                .build();
        sqlExecutor.update(queryContact1);

        Query queryContact2 = Query.builder()
                .append(" UPDATE ENG_COMP_CONTACT")
                .append(" SET PERSON_NAME = :personName", eng0822R1DTO.getPersonName())
                .append(" ,UPDATE_TIME = :uptime", new Date())
                .append(" ,UPDATE_USER = :upuser", eng0822R1DTO.getUpdateUser())
                .append(" WHERE ENG_COMP_ID = :id", eng0822R1DTO.getEngCompId())
                .append(" AND ITEM_NO = '1'")
                .build();
        sqlExecutor.update(queryContact2);
        log.debug("implUpdateEng0506" + eng0822R1DTO);
        return viewEng0506(eng0822R1DTO.getUserId());
    }

    @Override
    public Page<Eng0822R1DTO> findEng0502(Eng0822R1DTOCriteria criteria) {
        Query queryTotal = Query.builder()
                .append("SELECT COUNT(*) FROM [dbo].[ENG_COMP] A")
                .append(" LEFT JOIN [dbo].[ENG_COMP_CONTACT] B")
                .append(" ON A.ENG_COMP_ID = B.ENG_COMP_ID")
                .append(" WHERE B.ITEM_NO != 1")
                .append(" AND (A.TYPE = 2 OR A.TYPE = 5 OR A.TYPE = 6)")
                .appendWhen(StringUtils.isNotBlank(criteria.getType()),
                        " AND A.TYPE = :tp", criteria.getType())
                .appendWhen(StringUtils.isNotBlank(criteria.getSubject()),
                        " AND A.SUBJECT_LIST LIKE :subList", "%" + criteria.getSubject() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getChName()),
                        " AND A.CH_NAME LIKE :chNa", "%" + criteria.getChName() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getMasterName()),
                        " AND A.MASTER_NAME LIKE :masterNa", "%" + criteria.getMasterName() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getStatus()),
                        " AND A.IS_USE = :isUse", criteria.getStatus())
                .build();

        int totalCount = sqlExecutor.queryForList(queryTotal, Integer.class).get(0);

        Query query = Query.builder()
                .append("SELECT * FROM [dbo].[ENG_COMP] A")
                .append(" LEFT JOIN [dbo].[ENG_COMP_CONTACT] B")
                .append(" ON A.ENG_COMP_ID = B.ENG_COMP_ID")
                .append(" WHERE B.ITEM_NO != 1")
                .append(" AND (A.TYPE = 2 OR A.TYPE = 5 OR A.TYPE = 6)")
                .appendWhen(StringUtils.isNotBlank(criteria.getType()),
                        " AND A.TYPE = :tp", criteria.getType())
                .appendWhen(StringUtils.isNotBlank(criteria.getSubject()),
                        " AND A.SUBJECT_LIST LIKE :subList", "%" + criteria.getSubject() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getChName()),
                                 " AND A.CH_NAME LIKE :chNa", "%" + criteria.getChName() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getMasterName()),
                        " AND A.MASTER_NAME LIKE :masterNa", "%" + criteria.getMasterName() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getStatus()),
                        " AND A.IS_USE = :isUse", criteria.getStatus())
                .append("order by " + criteria.getOrderBy())
                .append(", A.CH_NAME ASC ")
                .append("offset :currentPage * :perPage rows fetch next :perPage rows only",
                        criteria.getCurrentPage(),
                        criteria.getPerPage(),
                        criteria.getPerPage()
                )
                .build();
        List<Eng0822R1DTO> list = sqlExecutor.queryForList(query, Eng0822R1DTO.class);
        log.debug("findEng0502r1" + list);
        return new PageImpl<Eng0822R1DTO>(list, criteria.toPageable(), totalCount);
    }

    // 用engCompId檢視公會資料
    @Override
    public Eng0822R1DTO viewEng0502(String engCompId) {
        Query query = Query.builder()
                .append("SELECT A.*, B.PERSON_NAME, B.TEL1,")
                .append("B.TEL2, B.TEL3, B.FAX, B.ADDRESS1_CITY,")
                .append("B.ADDRESS1_TOWN, B.ADDRESS1_OTHER,")
                .append(" B.MAIL1 FROM [dbo].[ENG_COMP] A")
                .append(" LEFT JOIN [dbo].[ENG_COMP_CONTACT] B")
                .append(" ON A.ENG_COMP_ID = B.ENG_COMP_ID")
                .append("WHERE A.ENG_COMP_ID = :id", engCompId)
                .build();
        List<Eng0822R1DTO> list = sqlExecutor.queryForList(query, Eng0822R1DTO.class);
        Eng0822R1DTO dto;
        if(list.size() >= 2) {
            if(list.get(0).getItemNo() == 1) {  // 若list[0]是秘書長資料
                dto = list.get(1);  // 董事長資料
                dto.setPersonName(list.get(0).getPersonName());  // 秘書長名字
                dto.setTel3(list.get(1).getTel3());  // 董事長手機
            } else {
                dto = list.get(0); // 董事長資料
                dto.setPersonName(list.get(1).getPersonName());  // 秘書長名字
                dto.setTel3(list.get(0).getTel3());  // 董事長手機
            }
        } else {
            dto = list.get(0);
        }
        log.debug("implViewEng0502" + dto);
        return dto;
    }

    @Override
    public Eng0822R1DTO updateEng0502(Eng0822R1DTO eng0822R1DTO) {
        Query queryComp = Query.builder()
                .append(" UPDATE ENG_COMP")
                .append(" SET CH_NAME = :na", eng0822R1DTO.getChName())
                .append(" ,[TYPE] = :engType", eng0822R1DTO.getType())
                .append(" ,IS_USE = :isu", eng0822R1DTO.getIsUse())
                .append(" ,UPDATE_TIME = :uptime", new Date())
                .append(" ,UPDATE_USER = :upuser", eng0822R1DTO.getUpdateUser())
                .append(" ,SUBJECT_LIST = :subList", eng0822R1DTO.getSubjectList())
                .append(" ,REMARK = :remark", eng0822R1DTO.getRemark())
                .append(" WHERE ENG_COMP_ID = :id", eng0822R1DTO.getEngCompId())
                .build();
        sqlExecutor.update(queryComp);

        Query queryContact1 = Query.builder()
                .append(" UPDATE ENG_COMP_CONTACT")
                .append(" SET PERSON_NAME = :masterName", eng0822R1DTO.getMasterName())
                .append(" ,TEL1 = :tel1", eng0822R1DTO.getTel1())
                .append(" ,TEL2 = :tel2", eng0822R1DTO.getTel2())
                .append(" ,TEL3 = :tel3", eng0822R1DTO.getTel3())
                .append(" ,FAX = :fax", eng0822R1DTO.getFax())
                .append(" ,ADDRESS1_ZIP = :addz", eng0822R1DTO.getAddress1Zip())
                .append(" ,ADDRESS1_CITY = :addc", eng0822R1DTO.getAddress1City())
                .append(" ,ADDRESS1_TOWN = :addt", eng0822R1DTO.getAddress1Town())
                .append(" ,ADDRESS1_OTHER = :addo", eng0822R1DTO.getAddress1Other())
                .append(" ,MAIL1 = :mail1", eng0822R1DTO.getMail1())
                .append(" ,UPDATE_TIME = :uptime", new Date())
                .append(" ,UPDATE_USER = :upuser", eng0822R1DTO.getUpdateUser())
                .append(" WHERE ENG_COMP_ID = :id", eng0822R1DTO.getEngCompId())
                .append(" AND ITEM_NO = '0'")
                .build();
        sqlExecutor.update(queryContact1);

        Query queryContact2 = Query.builder()
                .append(" UPDATE ENG_COMP_CONTACT")
                .append(" SET PERSON_NAME = :personName", eng0822R1DTO.getPersonName())
                .append(" ,UPDATE_TIME = :uptime", new Date())
                .append(" ,UPDATE_USER = :upuser", eng0822R1DTO.getUpdateUser())
                .append(" WHERE ENG_COMP_ID = :id", eng0822R1DTO.getEngCompId())
                .append(" AND ITEM_NO = '1'")
                .build();
        sqlExecutor.update(queryContact2);
        log.debug("implUpdateEng0502" + eng0822R1DTO);
        return viewEng0502(eng0822R1DTO.getEngCompId());
    }

    // 業管新增公會資料
    @Override
    public Eng0822R1DTO createEng0502(Eng0822R1DTO dto) {
        Query query1 = Query.builder()
                .append("INSERT INTO [ENG_COMP] ([ENG_COMP_ID], [CH_NAME],")
                .append(" [TYPE], [IS_USE], [MASTER_NAME], [REMARK], [SUBJECT_LIST],")
                .append(" [UPDATE_TIME], [UPDATE_USER], [CREATE_TIME], [CREATE_USER])")
                .append("VALUES (")
                .append(":compId, :chName,", dto.getEngCompId(), dto.getChName())
                .append(":engType, :isUse,", dto.getType(), dto.getIsUse())
                .append(":masterName, :remark,", dto.getMasterName(), dto.getRemark())
                .append(":subjectList, ", dto.getSubjectList())
                .append(":upTime, :upUser,", new Date(), dto.getUpdateUser())
                .append(":crTime, :crUser)", new Date(), dto.getCreateUser())
                .build();
        sqlExecutor.insert(query1);

        Query query2 = Query.builder()
                .append("INSERT INTO [ENG_COMP_CONTACT] ([ENG_COMP_ID], [ITEM_NO],")
                .append(" [TEL1], [TEL2], [TEL3], [FAX], [MAIL1],")
                .append(" [ADDRESS1_ZIP], [ADDRESS1_CITY], [ADDRESS1_TOWN], [ADDRESS1_OTHER],")
                .append(" [UPDATE_TIME], [UPDATE_USER], [CREATE_TIME], [CREATE_USER])")
                .append("VALUES (")
                .append(":id, :itemNo,", dto.getEngCompId(), 0)
                .append(":tel1, :tel2,", dto.getTel1(), dto.getTel2())
                .append(":tel3,", dto.getTel3())
                .append(":fax, :mail1,", dto.getFax(), dto.getMail1())
                .append(":add1Zip, :add1City,", dto.getAddress1Zip(), dto.getAddress1City())
                .append(":add1Town, :add1Other,", dto.getAddress1Town(), dto.getAddress1Other() )
                .append(":upTime, :upUser,", new Date(), dto.getUpdateUser())
                .append(":crTime, :crUser)", new Date(), dto.getCreateUser())
                .build();
        sqlExecutor.insert(query2);

        Query query3 = Query.builder()
                .append("INSERT INTO [ENG_COMP_CONTACT] ([ENG_COMP_ID], [ITEM_NO],")
                .append(" [TEL1], [TEL2], [FAX], [MAIL1],")
                .append(" [ADDRESS1_ZIP], [ADDRESS1_CITY], [ADDRESS1_TOWN], [ADDRESS1_OTHER],")
                .append(" [UPDATE_TIME], [UPDATE_USER], [CREATE_TIME], [CREATE_USER])")
                .append("VALUES (")
                .append(":id, :itemNo,", dto.getEngCompId(), 1)
                .append(":tel1, :tel2,", dto.getTel1(), dto.getTel2())
                .append(":fax, :mail1,", dto.getFax(), dto.getMail1())
                .append(":add1Zip, :add1City,", dto.getAddress1Zip(), dto.getAddress1City())
                .append(":add1Town, :add1Other,", dto.getAddress1Town(), dto.getAddress1Other())
                .append(":upTime, :upUser,", new Date(), dto.getUpdateUser())
                .append(":crTime, :crUser)", new Date(), dto.getCreateUser())
                .build();
        sqlExecutor.insert(query3);
        log.debug("implCreateEng0502" + dto);
        return viewEng0502(dto.getEngCompId());
    }

    @Override
    public Page<Eng0822R1DTO> findEng0821R1(Eng0822R1DTOCriteria criteria) {
        Query queryTotalCount = Query.builder()
                .append("SELECT COUNT(*) FROM ENG_COMP A")
                .append("LEFT JOIN ENG_COMP_CONTACT B ON A.ENG_COMP_ID = B.ENG_COMP_ID AND (B.ITEM_NO = 0 OR B.ITEM_NO is null)")
                .append("LEFT JOIN ENG_MEMBER C on A.ENGR_ID_LIST = C.ENG_MEMBER_ID")
                .append("LEFT JOIN ENG_CST_LICENSE D on A.ENG_COMP_ID = D.COMP_ID")
                .append("and D.ENG_CST_LICENSE_VERSION =")
                .append("(select max(ENG_CST_LICENSE_VERSION) from ENG_CST_LICENSE where COMP_ID = D.COMP_ID)")
                .append("WHERE A.TYPE in ('2','5') AND A.IS_USE = 1")
                .build();
        int totalCount = sqlExecutor.queryForList(queryTotalCount, Integer.class).get(0);

        Query query = Query.builder()
                .append("SELECT distinct A.*, B.TEL1, B.TEL2, B.FAX, B.MAIL1,")
                .append("B.ADDRESS1_ZIP, B.ADDRESS1_CITY, B.ADDRESS1_TOWN, B.ADDRESS1_OTHER, C.IS_ENGR")
                .append("AS Master_IsEngr FROM ENG_COMP A")
                .append("LEFT JOIN ENG_COMP_CONTACT B ON A.ENG_COMP_ID = B.ENG_COMP_ID AND (B.ITEM_NO = 0 OR B.ITEM_NO is null)")
                .append("LEFT JOIN ENG_MEMBER C on A.ENGR_ID_LIST = C.ENG_MEMBER_ID")
                .append("LEFT JOIN ENG_CST_LICENSE D on A.ENG_COMP_ID = D.COMP_ID")
                .append("and D.ENG_CST_LICENSE_VERSION =")
                .append("(select max(ENG_CST_LICENSE_VERSION) from ENG_CST_LICENSE where COMP_ID = D.COMP_ID)")
                .append("WHERE A.TYPE in ('2','5') AND A.IS_USE = 1")  // 找出全聯會，技師公會
                .append("order by " + criteria.getOrderBy() + ", A.TYPE desc")
                .append("offset :currentPage * :perPage rows fetch next :perPage rows only",
                        criteria.getCurrentPage(),
                        criteria.getPerPage(),
                        criteria.getPerPage())
                .build();
        List<Eng0822R1DTO> list = sqlExecutor.queryForList(query, Eng0822R1DTO.class);
        Page<Eng0822R1DTO> page = new PageImpl<>(list, criteria.toPageable(), totalCount);
        log.debug("findEng0821R1: " + list);
        return page;
    }
}
