package gov.pcc.pwc.repository.impl;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.repository.custom.AdmAccountRepositoryCustom;
import gov.pcc.pwc.service.criteria.AdmAccountContactCriteria;
import gov.pcc.pwc.service.criteria.UserManagementCriteria;
import gov.pcc.pwc.service.dto.AdmAccountContactDTO;
import gov.pcc.pwc.service.dto.ComStatusSearchDTO;
import gov.pcc.pwc.service.dto.UserManagementDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

public class AdmAccountRepositoryImpl implements AdmAccountRepositoryCustom {

    private final SqlExecutor sqlExecutor;

    public AdmAccountRepositoryImpl(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    @Override
    public List<AdmAccountContactDTO> findAllByCriteria(AdmAccountContactDTO criteria) {
        Query query = Query
            .builder()
            .append(
                " SELECT a.User_Id, c.Ch_Name, c.Ch_Name_Rome, c.Title, a.Status, a.Audit_Status, a.User_Type , a.Pcc_Org_Id, a.Comp_Idno , a.No , a.User_Pad_Hash , a.Pad_Update_Time, a.Update_User, a.Update_Time, CONVERT(datetime, c.birthday) as birthday, c.Idno, c.Sex ,c.Work_Tel ,c.Mobile_Tel ,c.Email " +
                        " , c.Home_Addr_Zip, c.Home_Addr_City, c.Home_Addr_Town, c.Home_Addr_Other, c.Work_Addr_Zip, c.Work_Addr_City, c.Work_Addr_Town, c.Work_Addr_Other, r.Role_Id_List "
            )
            .append(" FROM ADM_ACCOUNT a JOIN ADM_CONTACT c ON a.User_Id = c.User_Id ")
            .append(
                " JOIN (SELECT DISTINCT User_Id, Role_Id_List= ( SELECT cast(Role_Id AS VARCHAR) + ',' from Adm_Account_Role where User_Id = ar.User_ID FOR XML PATH('')) from Adm_Account_Role ar) r ON a.User_Id = r.User_Id"
            )
            .append(" WHERE 1=1 ")
            .appendWhen(StringUtils.isNotBlank(criteria.getUserId()), " AND a.USER_ID = :userId ", criteria.getUserId())
            .appendWhen(
                StringUtils.isNotBlank(criteria.getChName()),
                " AND c.CH_NAME LIKE :chName ",
                "%" + criteria.getChName() + "%"
            )
            .appendWhen(
                StringUtils.isNotBlank(criteria.getChNameRome()),
                    " AND c.CH_NAME_ROME LIKE :chNameRome ",
                    "%" + criteria.getChNameRome() + "%"
            )
            .appendWhen(StringUtils.isNotBlank(criteria.getUserType()), " AND a.USER_TYPE = :userType ", criteria.getUserType())
            .appendWhen(StringUtils.isNotBlank(criteria.getPccOrgId()), " AND a.PCC_ORG_ID = :pccOrgId ", criteria.getPccOrgId())
            .appendWhen(StringUtils.isNotBlank(criteria.getCompIdno()), " AND a.COMP_IDNO = :compIdno ", criteria.getCompIdno())
            .appendWhen(StringUtils.isNotBlank(criteria.getStatus()), " AND a.STATUS = :status ", criteria.getStatus())
            .appendWhen(StringUtils.isNotBlank(criteria.getAuditStatus()), " AND a.AUDIT_STATUS = :auditStatus ", criteria.getAuditStatus())
            .build();

        List<AdmAccountContactDTO> admAccountContractDTOs = sqlExecutor.queryForList(query, AdmAccountContactDTO.class);

        // TODO 時間轉換...這邊硬+8小時
        if (admAccountContractDTOs.size() > 0) {
            admAccountContractDTOs.forEach(admAccountContractDTO -> {
                admAccountContractDTO.setUpdateTime(admAccountContractDTO.getUpdateTime().plusSeconds(28800));
            });
        }
        return admAccountContractDTOs;
    }

    @Override
    public Page<AdmAccountContactDTO> findAllByCriteria(AdmAccountContactCriteria criteria) {
        Query queryTotalCount = Query
            .builder()
            .append(" SELECT COUNT(*) FROM ADM_ACCOUNT a JOIN ADM_CONTACT c ON a.User_Id = c.User_Id WHERE 1=1 ")
            .appendWhen(StringUtils.isNotBlank(criteria.getUserId()), " AND a.USER_ID LIKE :userId ","%" +  criteria.getUserId()+ "%")
            .appendWhen(StringUtils.isNotBlank(criteria.getChName()), " AND c.CH_NAME LIKE :chName ", "%" + criteria.getChName() + "%")
            .appendWhen(StringUtils.isNotBlank(criteria.getChNameRome()), " AND c.CH_NAME_ROME LIKE :chNameRome ", "%" + criteria.getChNameRome() + "%")
            .appendWhen(StringUtils.isNotBlank(criteria.getUserType()), " AND a.USER_TYPE = :userType ", criteria.getUserType())
            .appendWhen(StringUtils.isNotBlank(criteria.getPccOrgId()), " AND a.PCC_ORG_ID = :pccOrgId ", criteria.getPccOrgId())
            .appendWhen(StringUtils.isNotBlank(criteria.getUnitId()), " AND a.USER_ID LIKE :unitId ", "3.60." + criteria.getUnitId() + "%")
            .appendWhen(StringUtils.isNotBlank(criteria.getCompIdno()), " AND a.COMP_IDNO = :compIdno ", criteria.getCompIdno())
            .appendWhen(StringUtils.isNotBlank(criteria.getStatus()), " AND a.STATUS = :status ", criteria.getStatus())
            .appendWhen(StringUtils.isNotBlank(criteria.getAuditStatus()), " AND a.AUDIT_STATUS = :auditStatus ", criteria.getAuditStatus())
            .build();
        int totalCount = sqlExecutor.queryForList(queryTotalCount, Integer.class).get(0);

        Query query = Query
            .builder()
            .append(" select row_number() over(order by " + criteria.getOrderBy() + ") as row_num,")
                .append(
                        " a.User_Id, c.Ch_Name, c.Ch_Name_Rome, c.Title, a.Status, a.Audit_Status, a.User_Type , a.Pcc_Org_Id, a.Comp_Idno , a.No , a.User_Pad_Hash , a.Pad_Update_Time, a.Update_User, a.Update_Time, CONVERT(datetime, c.birthday) as birthday, c.Idno, c.Sex ,c.Work_Tel ,c.Mobile_Tel ,c.Email " +
                                " , c.Home_Addr_Zip, c.Home_Addr_City, c.Home_Addr_Town, c.Home_Addr_Other, c.Work_Addr_Zip, c.Work_Addr_City, c.Work_Addr_Town, c.Work_Addr_Other, r.Role_Id_List "
                )
            .append(" FROM ADM_ACCOUNT a JOIN ADM_CONTACT c ON a.User_Id = c.User_Id ")
            .append(
                " JOIN (SELECT DISTINCT User_Id, Role_Id_List= ( SELECT cast(Role_Id AS VARCHAR) + ',' from Adm_Account_Role where User_Id = ar.User_ID FOR XML PATH('')) from Adm_Account_Role ar) r ON a.User_Id = r.User_Id")
            .append(" WHERE 1=1 ")
            .appendWhen(StringUtils.isNotBlank(criteria.getUserId()), " AND a.USER_ID LIKE :userId ", "%" +  criteria.getUserId()+ "%")
            .appendWhen(StringUtils.isNotBlank(criteria.getChName()), " AND c.CH_NAME LIKE :chName ", "%" + criteria.getChName() + "%")
            .appendWhen(StringUtils.isNotBlank(criteria.getChNameRome()), " AND c.CH_NAME_ROME LIKE :chNameRome ", "%" + criteria.getChNameRome() + "%")
            .appendWhen(StringUtils.isNotBlank(criteria.getUserType()), " AND a.USER_TYPE = :userType ", criteria.getUserType())
            .appendWhen(StringUtils.isNotBlank(criteria.getPccOrgId()), " AND a.PCC_ORG_ID = :pccOrgId ", criteria.getPccOrgId())
            .appendWhen(StringUtils.isNotBlank(criteria.getUnitId()), " AND a.USER_ID LIKE :unitId ", "3.60." + criteria.getUnitId() + "%")
            .appendWhen(StringUtils.isNotBlank(criteria.getCompIdno()), " AND a.COMP_IDNO = :compIdno ", criteria.getCompIdno())
            .appendWhen(StringUtils.isNotBlank(criteria.getStatus()), " AND a.STATUS = :status ", criteria.getStatus())
            .appendWhen(StringUtils.isNotBlank(criteria.getAuditStatus()), " AND a.AUDIT_STATUS = :auditStatus ", criteria.getAuditStatus())
            .append("order by " + criteria.getOrderBy())
            .append(
                "offset :currentPage * :perPage rows fetch next :perPage rows only",
                criteria.getCurrentPage(),
                criteria.getPerPage(),
                criteria.getPerPage()
            )
            .build();
        List<AdmAccountContactDTO> admAccountContractDTOs = sqlExecutor.queryForList(query, AdmAccountContactDTO.class);
        // TODO 時間轉換...這邊硬+8小時
        if (admAccountContractDTOs.size() > 0) {
            admAccountContractDTOs.forEach(admAccountContractDTO -> {
                admAccountContractDTO.setUpdateTime(admAccountContractDTO.getUpdateTime().plusSeconds(28800));
            });
        }
        Page<AdmAccountContactDTO> resultPage = new PageImpl<>(admAccountContractDTOs, criteria.toPageable(), totalCount);
        return resultPage;
    }

    @Override
    public Long getBiggestNo() {
        Query query = Query
                .builder()
                .append(" select max(a.no) from adm_account a ")
                .build();
        List<Long> result = sqlExecutor.queryForList(query, Long.class);
        if (result.isEmpty()) {
            return 0L;
        } else {
            return result.get(0) + 1L;
        }
    }

    @Override
    public AdmAccountContactDTO findByUserId(AdmAccountContactDTO criteria) {
        Query query = Query
                .builder()
                .append(" SELECT * FROM ADM_ACCOUNT WHERE 1=1 ")
                .append(" AND User_Id = :Id ", criteria.getUserId())
                .build();
        List<AdmAccountContactDTO> admAccountContactDTOs = sqlExecutor.queryForList(query, AdmAccountContactDTO.class);
        AdmAccountContactDTO result = null;
        if (admAccountContactDTOs.size() > 0) {
            result = admAccountContactDTOs.get(0);
        }
        return result;
    }

    @Override
    public List<AdmAccountContactDTO> findUserIdByCriteria(AdmAccountContactDTO criteria) {
        Query query = Query
                .builder()
                .append(" SELECT USER_ID, CH_NAME, EMAIL, UPDATE_USER, UPDATE_TIME FROM ADM_CONTACT WHERE 1=1 ")
                .append(" AND User_Id = :Id ", criteria.getUserId())
                .build();
        return sqlExecutor.queryForList(query, AdmAccountContactDTO.class);
    }

    @Override
    public List<String> findAccountUserId(AdmAccountContactDTO criteria) {
        Query query = Query
                .builder()
                .append(" SELECT USER_ID FROM ADM_ACCOUNT WHERE 1=1 ")
                .appendWhen(StringUtils.isNotBlank(criteria.getCompIdno()), " AND COMP_IDNO = :compIdno ", criteria.getCompIdno())
                .appendWhen(StringUtils.isNotBlank(criteria.getPccOrgId()), " AND PCC_ORG_ID = :pccOrgId ", criteria.getPccOrgId())
                .append(" ORDER BY len(USER_ID), USER_ID ")
                .build();
        List<String> result = sqlExecutor.queryForList(query, String.class);
        return result;
    }

    @Override
    public Page<UserManagementDTO> findAccountInfo(UserManagementCriteria criteria) {
        Query queryTotalCount = Query
                .builder()
                .append(" SELECT COUNT(*) FROM ")
                .append(" (SELECT USER_ID, CH_NAME, STATUS, ")
                .append(" (SELECT CAST (ar.Role_Name AS NVARCHAR) + ',' FROM ")
                .append(" (SELECT aa.USER_ID AS USER_ID, ac.CH_NAME AS CH_NAME, aa.STATUS AS STATUS, ar.Role_Name AS Role_Name ")
                .append(" FROM ADM_ACCOUNT aa ")
                .append(" JOIN ADM_CONTACT ac ON aa.USER_ID = ac.USER_ID ")
                .append(" LEFT JOIN ADM_ACCOUNT_ROLE aar ON aa.USER_ID = aar.User_Id ")
                .append(" LEFT JOIN ADM_ROLE ar ON aar.Role_Id = ar.Role_Id ")
                .append(" WHERE 1 = 1")
                .appendWhen(StringUtils.isNotBlank(criteria.getPccOrgId()), " AND aa.PCC_ORG_ID = :pccOrgId", criteria.getPccOrgId())
                .appendWhen(StringUtils.isNotBlank(criteria.getCompIndo()), " AND aa.COMP_IDNO= :compIdno", criteria.getCompIndo())
                .append(" ) ar ")
                .append(" WHERE ar.USER_ID=arn.USER_ID AND ar.CH_NAME=arn.CH_NAME  AND ar.STATUS=arn.STATUS ")
                .append(" FOR XML PATH('')) AS Role_Name ")
                .append(" FROM (SELECT aa.USER_ID AS USER_ID, ac.CH_NAME AS CH_NAME, aa.STATUS AS STATUS, ar.Role_Name AS Role_Name ")
                .append(" FROM ADM_ACCOUNT aa ")
                .append(" JOIN ADM_CONTACT ac ON aa.USER_ID = ac.USER_ID ")
                .append(" LEFT JOIN ADM_ACCOUNT_ROLE aar ON aa.USER_ID = aar.User_Id ")
                .append(" LEFT JOIN ADM_ROLE ar ON aar.Role_Id = ar.Role_Id ")
                .append(" WHERE 1 = 1")
                .appendWhen(StringUtils.isNotBlank(criteria.getPccOrgId()), " AND aa.PCC_ORG_ID = :pccOrgId", criteria.getPccOrgId())
                .appendWhen(StringUtils.isNotBlank(criteria.getCompIndo()), " AND aa.COMP_IDNO= :compIdno", criteria.getCompIndo())
                .append(" ) as arn ")
                .append(" WHERE 1 = 1")
                .appendWhen(StringUtils.isNotBlank(criteria.getUserId()), " AND USER_ID = :userId ", criteria.getUserId())
                .appendWhen(StringUtils.isNotBlank(criteria.getChName()), " AND CH_NAME LIKE :chName ","%" + criteria.getChName() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getRoleId()), " AND ROLE_NAME LIKE :roleName ", "%" + criteria.getRoleId() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getStatus()), " AND STATUS = :status ", criteria.getStatus())
                .append(" GROUP BY USER_ID, CH_NAME, STATUS) AS USER_ROLE_NAME ")
                .build();

        int totalCount = sqlExecutor.queryForList(queryTotalCount, Integer.class).get(0);

        Query query = Query
                .builder()
                .append(" SELECT USER_ID, CH_NAME, STATUS, ")
                .append(" (SELECT CAST (ar.Role_Name AS NVARCHAR) + ',' FROM ")
                .append(" (SELECT aa.USER_ID AS USER_ID, ac.CH_NAME AS CH_NAME, aa.STATUS AS STATUS, ar.Role_Name AS Role_Name ")
                .append(" FROM ADM_ACCOUNT aa ")
                .append(" JOIN ADM_CONTACT ac ON aa.USER_ID = ac.USER_ID ")
                .append(" LEFT JOIN ADM_ACCOUNT_ROLE aar ON aa.USER_ID = aar.User_Id ")
                .append(" LEFT JOIN ADM_ROLE ar ON aar.Role_Id = ar.Role_Id ")
                .append(" WHERE 1 = 1")
                .appendWhen(StringUtils.isNotBlank(criteria.getPccOrgId()), " AND aa.PCC_ORG_ID = :pccOrgId", criteria.getPccOrgId())
                .appendWhen(StringUtils.isNotBlank(criteria.getCompIndo()), " AND aa.COMP_IDNO= :compIdno", criteria.getCompIndo())
                .append(" ) AS ar ")
                .append(" WHERE ar.USER_ID = arn.USER_ID AND ar.CH_NAME=arn.CH_NAME  AND ar.STATUS=arn.STATUS ")
                .append(" FOR XML PATH('')) AS Role_Name ")
                .append(" FROM (SELECT aa.USER_ID AS USER_ID, ac.CH_NAME AS CH_NAME, aa.STATUS AS STATUS, ar.Role_Name AS Role_Name ")
                .append(" FROM ADM_ACCOUNT aa ")
                .append(" JOIN ADM_CONTACT ac ON aa.USER_ID = ac.USER_ID ")
                .append(" LEFT JOIN ADM_ACCOUNT_ROLE aar ON aa.USER_ID = aar.User_Id ")
                .append(" LEFT JOIN ADM_ROLE ar ON aar.Role_Id = ar.Role_Id ")
                .append(" WHERE 1 = 1")
                .appendWhen(StringUtils.isNotBlank(criteria.getPccOrgId()), " AND aa.PCC_ORG_ID = :pccOrgId", criteria.getPccOrgId())
                .appendWhen(StringUtils.isNotBlank(criteria.getCompIndo()), " AND aa.COMP_IDNO= :compIdno", criteria.getCompIndo())
                .append(" ) AS arn ")
                .append(" WHERE 1 = 1")
                .appendWhen(StringUtils.isNotBlank(criteria.getUserId()), " AND USER_ID = :userId ", criteria.getUserId())
                .appendWhen(StringUtils.isNotBlank(criteria.getChName()), " AND CH_NAME LIKE :chName ","%" + criteria.getChName() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getRoleId()), " AND ROLE_NAME LIKE :roleName ", "%" + criteria.getRoleId() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getStatus()), " AND STATUS = :status ", criteria.getStatus())
                .append(" GROUP BY USER_ID, CH_NAME, STATUS ")
                .append(" order by " + criteria.getOrderBy())
                .append(
                        "offset :currentPage * :perPage rows fetch next :perPage rows only",
                        criteria.getCurrentPage(),
                        criteria.getPerPage(),
                        criteria.getPerPage()
                )
                .build();

        List<UserManagementDTO> UserManagementDTOs = sqlExecutor.queryForList(query, UserManagementDTO.class);
        Page<UserManagementDTO> resultPage = new PageImpl<>(UserManagementDTOs, criteria.toPageable(), totalCount);
        return resultPage;
    }

}
