package gov.pcc.pwc.repository.impl;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.repository.custom.RecUserProjectCodeRepositoryCustom;
import gov.pcc.pwc.service.criteria.RecUserProjectCodeCriteria;
import gov.pcc.pwc.service.dto.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class RecUserProjectCodeRepositoryImpl implements RecUserProjectCodeRepositoryCustom {

    private final SqlExecutor sqlExecutor;

    public RecUserProjectCodeRepositoryImpl(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    @Override
    public List<RecOrgRoleDTO> findAllRecOrgByQuery() {
        Query query = Query
                .builder()
                .append("SELECT ORG_ID,ORG_NAME FROM REC_ORG_ROLE")
                .append("ORDER BY ORG_ID")
                .build();
        return sqlExecutor.queryForList(query, RecOrgRoleDTO.class);
    }

    @Override
    public List<RecAdmRoleDTO> findAllRecRoleByQuery() {
        Query query = Query
                .builder()
                .append("SELECT ROLE_ID,ROLE_NAME FROM ADM_ROLE")
                .append("WHERE BUSINESS_CATEGORY='REC'")
                .append("ORDER BY ROLE_ID")
                .build();
        return sqlExecutor.queryForList(query, RecAdmRoleDTO.class);
    }

    @Override
    public Page<RecUserProjectCodeQueryDTO> findUserByCriteria(RecUserProjectCodeCriteria criteria) {

        //先查登入者的權限
        Query queryLoginPermission = Query
                .builder()
                .append("SELECT MIN(Role_Id) FROM ADM_ACCOUNT_ROLE WHERE 1=1")
                .appendWhen(StringUtils.isNotBlank(criteria.getLoginId()),"AND USER_ID = :loginId",criteria.getLoginId().trim())
                .build();
        String loginPermission = sqlExecutor.queryForList(queryLoginPermission,String.class).get(0);

        //再查登入者的單位
        Query queryLoginOrg = Query
                .builder()
                .append("SELECT r1.ORG_ID  FROM ADM_ACCOUNT a ")
                .append("JOIN REC_ORG_ROLE r1 on r1.ORG_ID = a.PCC_ORG_ID WHERE 1=1")
                .appendWhen(StringUtils.isNotBlank(criteria.getLoginId()),"AND a.USER_ID = :loginId" ,criteria.getLoginId().trim())
                .build();
        String loginOrg = sqlExecutor.queryForList(queryLoginOrg,String.class).get(0);

        //查每個帳號的最小ROLE_ID(最大權限)的總數
        Query queryTotalCount = Query
                .builder()
                .append("SELECT count(*) FROM")
                .append("( SELECT a.USER_ID, MIN(ar.ROLE_ID) AS ROLE_ID")
                .append("FROM ADM_ACCOUNT a ")
                .append("JOIN ADM_CONTACT c on c.USER_ID = a.USER_ID ")
                .append("JOIN ADM_ACCOUNT_ROLE r on r.USER_ID = a.USER_ID ")
                .append("JOIN ADM_ROLE ar on ar.ROLE_ID = r.ROLE_ID and ar.BUSINESS_CATEGORY = 'REC' ")
                .append("LEFT JOIN REC_ORG_ROLE r1 on r1.ORG_ID = a.PCC_ORG_ID WHERE 1=1")
                .appendWhen(StringUtils.isNotBlank(criteria.getUserId()),"AND a.USER_ID = :userId", criteria.getUserId().trim())
                .appendWhen(StringUtils.isNotBlank(criteria.getChName()),"AND c.CH_NAME LIKE :chName","%" + criteria.getChName().trim() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getOrgName()),"AND r1.ORG_ID = :orgName",criteria.getOrgName())
                .appendWhen(StringUtils.isNotBlank(criteria.getRoleName()),"AND ar.ROLE_ID = :roleName",criteria.getRoleName())
                .appendWhen(StringUtils.isNotBlank(criteria.getStatus()),"AND a.STATUS = :status",criteria.getStatus())
                //如果是模組管理員可管四種權限
                .appendWhen(loginPermission.equals("REC_00"),"AND ar.Role_Id IN ('REC_00','REC_10','REC_20','REC_40')")
                //如果是中央管理員除了管理轄下還可以管同單位及其子單位
                .appendWhen(loginPermission.equals("REC_20"),"AND  ar.Role_Id IN ('REC_30','REC_31') AND ( r1.ORG_ID= :loginOrg OR r1.PARENT_ORG_ID = :loginOrg )", loginOrg ,loginOrg)
                //如果是地方管理員除了管理轄下還可以管同單位及其子單位
                .appendWhen(loginPermission.equals("REC_40"),"AND ar.Role_Id IN ('REC_50','REC_51','REC_60') AND ( r1.ORG_ID= :loginOrg OR r1.PARENT_ORG_ID = :loginOrg )", loginOrg ,loginOrg )
                .append("GROUP BY a.USER_ID ) AS role_id_min")
                .build();
        int totalCount = sqlExecutor.queryForList(queryTotalCount,Integer.class).get(0);

        //實際查帳號
        Query query = Query
                .builder()
                .append("SELECT ROW_NUMBER() OVER(ORDER BY a.USER_ID) as row_num, ")
                .append("a.USER_ID, c.CH_NAME ,r1.ORG_ID ,r1.ORG_NAME, c.WORK_TEL,c.EMAIL,ar.Role_Id , ar.ROLE_NAME, a.STATUS")
                .append("FROM ADM_ACCOUNT a")
                .append("JOIN ADM_CONTACT c on c.USER_ID = a.USER_ID")
                .append("JOIN ( SELECT a.USER_ID, MIN(ar.ROLE_ID) AS ROLE_ID")
                .append("FROM ADM_ACCOUNT a ")
                .append("JOIN ADM_CONTACT c on c.USER_ID = a.USER_ID ")
                .append("JOIN ADM_ACCOUNT_ROLE r on r.USER_ID = a.USER_ID ")
                .append("JOIN ADM_ROLE ar on ar.ROLE_ID = r.ROLE_ID and ar.BUSINESS_CATEGORY = 'REC' ")
                .append("LEFT JOIN REC_ORG_ROLE r1 on r1.ORG_ID = a.PCC_ORG_ID WHERE 1=1")
                .appendWhen(StringUtils.isNotBlank(criteria.getUserId()),"AND a.USER_ID = :userId", criteria.getUserId().trim())
                .appendWhen(StringUtils.isNotBlank(criteria.getChName()),"AND c.CH_NAME LIKE :chName","%" + criteria.getChName().trim() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getOrgName()),"AND r1.ORG_ID = :orgName",criteria.getOrgName())
                .appendWhen(StringUtils.isNotBlank(criteria.getRoleName()),"AND ar.ROLE_ID = :roleName",criteria.getRoleName())
                .appendWhen(StringUtils.isNotBlank(criteria.getStatus()),"AND a.STATUS = :status",criteria.getStatus())
                //如果是模組管理員可管四種權限
                .appendWhen(loginPermission.equals("REC_00"),"AND ar.Role_Id IN ('REC_00','REC_10','REC_20','REC_40')")
                //如果是中央管理員除了管理轄下還可以管同單位及其子單位
                .appendWhen(loginPermission.equals("REC_20"),"AND  ar.Role_Id IN ('REC_30','REC_31') AND ( r1.ORG_ID= :loginOrg OR r1.PARENT_ORG_ID = :loginOrg )", loginOrg ,loginOrg)
                //如果是地方管理員除了管理轄下還可以管同單位及其子單位
                .appendWhen(loginPermission.equals("REC_40"),"AND ar.Role_Id IN ('REC_50','REC_51','REC_60') AND ( r1.ORG_ID= :loginOrg OR r1.PARENT_ORG_ID = :loginOrg )", loginOrg ,loginOrg )
                .append("GROUP BY a.USER_ID ) role_id_min on role_id_min.USER_ID  = a.USER_ID")
                .append("JOIN ADM_ROLE ar on ar.ROLE_ID = role_id_min.ROLE_ID ")
                .append("LEFT JOIN REC_ORG_ROLE r1 on r1.ORG_ID = a.PCC_ORG_ID")
                .append("ORDER BY " + criteria.getOrderBy())
                .append("OFFSET :currentPage * :perPage rows fetch next :perPage ROWS ONLY",criteria.getCurrentPage(),criteria.getPerPage(),criteria.getPerPage())
                .build();
        List<RecUserProjectCodeQueryDTO> recUserProjectCodeQueryDTOS = sqlExecutor.queryForList(query, RecUserProjectCodeQueryDTO.class);
        Page<RecUserProjectCodeQueryDTO> resultPage = new PageImpl<>(recUserProjectCodeQueryDTOS,criteria.toPageable(),totalCount);
        return resultPage;
    }

    @Override
    public List<RecProjectCodesDTO> findAccessRecProjectCodesByQuery(String loginId, String roleId, String orgId) {
        //先找出全部
        Query query = Query
                .builder()
                .append("SELECT DATA_KEY, VALUE  FROM ADM_SYS_CODE ")
                .append("WHERE MODULE_TYPE ='REC' AND DATA_TYPE ='REC_PROJECT_CODE' AND IS_VALID =1 ORDER BY SORT_NO")
                .build();
        List<RecProjectCodesDTO> allProjectCodesList = sqlExecutor.queryForList(query,RecProjectCodesDTO.class);
        //判斷傳入的roleId是不是REC_00
        if(roleId.equals("REC_00")){
            for(int i =0; i<allProjectCodesList.size();i++){
                allProjectCodesList.get(i).setDisabled(false);
            }
        }
        //判斷傳入的roleId是不是REC_20
        else if(roleId.equals("REC_20")){
            Query queryRec20 = Query
                    .builder()
                    .append("SELECT PROJECT_CODE FROM REC_ORG_PROJECT_CODE WHERE ORG_ID =:orgId",orgId)
                    .build();
            List<String> rec20ProjectCode = sqlExecutor.queryForList(queryRec20,String.class);
            if(rec20ProjectCode.size() > 0) {
                for (int i = 0; i < allProjectCodesList.size(); i++) {
                    for (int j = 0; j < rec20ProjectCode.size(); j++) {
                        allProjectCodesList.get(i).setDisabled(true);
                        //該單位有的工程類別才可以選
                        if(allProjectCodesList.get(i).getDataKey().equals(rec20ProjectCode.get(j))){
                            allProjectCodesList.get(i).setDisabled(false);
                            break;
                        }
                    }
                }
            }
        }
        //傳入的roleId不是REC_20時
        else {
            Query findLoginIdQuery = Query
                .builder()
                .append("SELECT PROJECT_CODE FROM REC_USER_PROJECT_CODE WHERE USER_ID =:loginId",loginId)
                .build();
            List<String> loginIdProjectCodes = sqlExecutor.queryForList(findLoginIdQuery,String.class);
            if(loginIdProjectCodes.size() > 0) {
                for (int i = 0; i < allProjectCodesList.size(); i++){
                    for(int j=0; j<loginIdProjectCodes.size(); j++){
                        allProjectCodesList.get(i).setDisabled(true);
                        //只有登入者自己擁有的工程類別可以選
                        if(allProjectCodesList.get(i).getDataKey().equals(loginIdProjectCodes.get(j))){
                            allProjectCodesList.get(i).setDisabled(false);
                            break;
                        }
                    }
                }
            }
        }
        return  allProjectCodesList;
    }

    @Override
    public List<String> findSelectedProjectCodesByQuery(String userId) {
        Query query = Query
                .builder()
                .append("SELECT PROJECT_CODE FROM REC_USER_PROJECT_CODE WHERE USER_ID =:userId",userId)
                .build();
        return sqlExecutor.queryForList(query,String.class);
    }

    @Override
    public void insertUserProjectCodesByQuery(RecUserProjectCodeDTO recUserProjectCodeDTO) {
        if(recUserProjectCodeDTO.getUserId() != null){
            Map<String,String> deleteMap = new HashMap();
            deleteMap.put("userId",recUserProjectCodeDTO.getUserId());
            sqlExecutor.delete("DELETE FROM REC_USER_PROJECT_CODE WHERE USER_ID=:userId",deleteMap);
        }
        List<String> projectCode = recUserProjectCodeDTO.getProjectCodes();
        List<Map<String, ?>> paramList = new LinkedList<>();
        for(int i = 0 ; i<projectCode.size(); i++){
            Map<String, String> paramMap = new HashMap<>();
            paramMap.put("userId",recUserProjectCodeDTO.getUserId());
            paramMap.put("projectCode",projectCode.get(i));
            paramMap.put("createTime",recUserProjectCodeDTO.getCreateTime().toString());
            paramMap.put("createUser",recUserProjectCodeDTO.getCreateUser());
            paramMap.put("updateTime",recUserProjectCodeDTO.getUpdateTime().toString());
            paramMap.put("updateUser",recUserProjectCodeDTO.getUpdateUser());
            paramList.add(paramMap);
        }
        String insertCol ="INSERT INTO REC_USER_PROJECT_CODE (USER_ID,PROJECT_CODE,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) VALUES(:userId,:projectCode,:createUser,:createTime,:updateUser,:updateTime)";
        sqlExecutor.insert(insertCol, paramList);
    }
}
