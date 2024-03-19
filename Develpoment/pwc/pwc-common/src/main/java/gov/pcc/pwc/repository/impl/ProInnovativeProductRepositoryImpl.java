package gov.pcc.pwc.repository.impl;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.domain.AdmFile;
import gov.pcc.pwc.repository.AdmFileRepository;
import gov.pcc.pwc.repository.custom.ProInnovativeProductCustom;
import gov.pcc.pwc.repository.custom.ProInnovativeProductRepositoryCustom;
import gov.pcc.pwc.service.criteria.ProInnovativeProductProUserCriteria;
import gov.pcc.pwc.service.dto.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

public class ProInnovativeProductRepositoryImpl implements ProInnovativeProductRepositoryCustom, ProInnovativeProductCustom {

    //應該是本專案提供的查詢元件

    private final SqlExecutor sqlExecutor;

    private final AdmFileRepository admFileRepository;

    public ProInnovativeProductRepositoryImpl(SqlExecutor sqlExecutor, AdmFileRepository admFileRepository) {
        this.sqlExecutor = sqlExecutor;
        this.admFileRepository = admFileRepository;
    }

    //利用建構者模式的 Query.Builder queryBuilder去組成指令，並且利用ENUM搭配SWITCH去組成程式邏輯的條件判斷
    //前台List查詢
    @Override
    public List<ProInnovativeProductProUserDTO> findAllProInnovativeProducts(ProInnovativeProductProUserCriteria criteria) {
        //StringUtils.isNotBlank(去掉空格)

        Query.Builder queryBuilder = Query
                .builder()
                .append("SELECT '"+Optional.ofNullable(criteria.getCommunicateType()).map(comm->comm.toString()).orElse("")+"' as communicate_type,pip.PRO_INNOVATIVE_PRODUCT_ID, pip.INNOVATE_NAME, pip.INNOVATE_TYPE, pip.USER_ID, pip.CREATE_TIME, pip.ACCEPTED_ORGANIZATION, pip.STATUS as status_innovative_product, pos.STATUS  as status_org_suggestion, pos.TIME,pos.WRITE_CONDITION, ac.NAME as company_name " +
                        "FROM pro_innovative_product pip left join PRO_ORG_SUGGESTION pos on pip.PRO_INNOVATIVE_PRODUCT_ID=pos.PRO_INNOVATIVE_PRODUCT_ID left join ADM_ACCOUNT aa on pip.USER_ID = aa.USER_ID left join ADM_COMPANY ac on aa.COMP_IDNO = ac.COMP_IDNO left join ADM_COMPANY ac on aa.COMP_IDNO = ac.COMP_IDNO WHERE 1=1")
                .appendWhen(StringUtils.isNotBlank(criteria.getInnovateName()), "AND pip.innovate_name LIKE :innovate_name", "%" + criteria.getInnovateName() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getInnovateType()), "AND pip.innovate_type = :innovate_type", criteria.getInnovateType())
                .appendWhen(StringUtils.isNotBlank(criteria.getCompanyName()), "AND ac.name LIKE :name", "%" + criteria.getCompanyName() + "%");

        //SA提供的交流狀態判斷方法
    if(Optional.ofNullable(criteria.getCommunicateType()).isPresent()) {
    switch (criteria.getCommunicateType()) {
        case ALLPRODUCT:
            break;
        case COMMUNICATING:
            queryBuilder.appendWhen(true, "AND pip.status = :status_innovative_product", 1)
                        .appendWhen(true, "AND pos.status = :status_org_suggestion", null)
                        .appendWhen(true, "OR pos.status = :status_org_suggestion", 0)
                        .appendWhen(true, "AND pos.write_condition = :write_condition", 0);
            break;
        case COMMUNICATED:
            queryBuilder.appendWhen(true, "AND pip.status = :status_innovative_product", 1)
                        .appendWhen(true, "AND pos.status = :status_org_suggestion", 0)
                        .appendWhen(true, "AND pos.write_condition = :write_condition", 1);
            break;
        case NOCOMMUNICATE:
            queryBuilder.appendWhen(true, "AND pip.status = :status_innovative_product", 1)
                        .appendWhen(true, "AND pos.status = :status_org_suggestion", 1);
            break;
    }
}
        Query query = queryBuilder.append("ORDER BY CREATE_TIME DESC")
                .build();


        return sqlExecutor.queryForList(query, ProInnovativeProductProUserDTO.class);
        //sqlExecutor內含rowmapper方法可轉為DTO
    }
    //為了不要重複上方程式碼，則將上方的資料作包裝
    //登入前PAGE查詢
    @Override
    public Page<ProInnovativeProductProUserDTO> findPageAllProInnovativeProducts(ProInnovativeProductProUserCriteria criteria) {
        //StringUtils.isNotBlank(去掉空格)
        //--------------------------------------------------------------這區是查總筆數的資料-----------------------------------------------------------------
        int totalCount =  findPageCountAllProInnovativeProducts(criteria);
       //--------------------------------------------------------------這區是查該分頁比數的資料 假設有100筆資料 這頁可以查出20,40,60...筆資料--------------------------------------------------------
        List<ProInnovativeProductProUserDTO> proInnovativeProductProUserDTOs = findPageListAllProInnovativeProducts(criteria);
        //--------------------------------------------------------------這區是把總比數包起來的-----------------------------------------------------------------
        Page<ProInnovativeProductProUserDTO> resultPage = new PageImpl<>(proInnovativeProductProUserDTOs, criteria.toPageable(), totalCount);

        return resultPage;
    }
    //--------------------------------------------------------------這區是查總筆數的資料-----------------------------------------------------------------
    private Integer findPageCountAllProInnovativeProducts(ProInnovativeProductProUserCriteria criteria){
        Query.Builder queryBuilderTotalCount = Query
                .builder()
                .append("SELECT COUNT(*) FROM pro_innovative_product pip left join PRO_ORG_SUGGESTION pos on pip.PRO_INNOVATIVE_PRODUCT_ID=pos.PRO_INNOVATIVE_PRODUCT_ID " +
                        "left join ADM_ACCOUNT aa on pip.USER_ID = aa.USER_ID " +
                        "left join ADM_COMPANY ac on aa.COMP_IDNO = ac.COMP_IDNO  WHERE 1=1");

        queryBuilderTotalCount = findPageWhereProInnovativeProducts(queryBuilderTotalCount, criteria);
        Query queryTotal = queryBuilderTotalCount.build();

        int totalCount =  sqlExecutor.queryForList(queryTotal, Integer.class).get(0);
        return totalCount;
    }

    //--------------------------------------------------------------這區是查該分頁比數的資料 假設有100筆資料 這頁可以查出20,40,60...筆資料--------------------------------------------------------
    private List<ProInnovativeProductProUserDTO> findPageListAllProInnovativeProducts(ProInnovativeProductProUserCriteria criteria){
        Query.Builder queryBuilder = Query
                .builder()
                .append(" SELECT '"+Optional.ofNullable(criteria.getCommunicateType()).map(comm->comm.toString()).orElse("")+"' as communicate_type," +
                        " pip.PRO_INNOVATIVE_PRODUCT_ID, pip.INNOVATE_NAME, pip.INNOVATE_TYPE, pip.USER_ID, pip.CREATE_TIME, pip.ACCEPTED_ORGANIZATION,pip.STATUS, pip.STATUS as status_innovative_product, pos.STATUS  as status_org_suggestion," +
                        " pip.COMMUNICATE_DATE, pip.COMMUNICATE_DOC_NUMBER,pip.INNOVATE_FUNCTION,pip.INNOVATE_QUALITY,pip.INNOVATE_PROVE,pip.PATENT,pip.PATENT_START_DATE,pip.PATENT_END_DATE,pip.CONSTRUCT_COST,pip.MAINTAIN_COST,pip.INNOVATE_CASE_NAME," +
                        "pip.INNOVATE_CASE_VENDOR,pip.INNOVATE_CASE_SCALE, pos.TIME,pos.WRITE_CONDITION, ac.NAME as company_name, pip.ACCEPTED_ORGANIZATION ")
                .append( "FROM pro_innovative_product pip left join PRO_ORG_SUGGESTION pos on pip.PRO_INNOVATIVE_PRODUCT_ID=pos.PRO_INNOVATIVE_PRODUCT_ID left join ADM_ACCOUNT aa on pip.USER_ID = aa.USER_ID left join ADM_COMPANY ac on aa.COMP_IDNO = ac.COMP_IDNO" +
                        "  left join ADM_AGENT aag on pos.ORG_ID=aag.ORG_ID  WHERE 1=1");

        queryBuilder = findPageWhereProInnovativeProducts(queryBuilder,criteria);
        Query query = queryBuilder.append("ORDER BY CREATE_TIME DESC")
                .append(
                        "offset :currentPage * :perPage rows fetch next :perPage rows only",
                        criteria.getCurrentPage(),
                        criteria.getPerPage(),
                        criteria.getPerPage()
                )
                .build();
        List<ProInnovativeProductProUserDTO> proInnovativeProductProUserDTOs = sqlExecutor.queryForList(query, ProInnovativeProductProUserDTO.class);

        return proInnovativeProductProUserDTOs;
    }

    private Query.Builder findPageWhereProInnovativeProducts(Query.Builder queryBuilder,ProInnovativeProductProUserCriteria criteria){
        Query.Builder queryBuilderWhere = queryBuilder
                .appendWhen(StringUtils.isNotBlank(criteria.getInnovateName()), "AND pip.innovate_name LIKE :innovate_name", "%" + criteria.getInnovateName() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getInnovateType()), "AND pip.innovate_type = :innovate_type", criteria.getInnovateType())
                .appendWhen(StringUtils.isNotBlank(criteria.getCompanyName()), "AND ac.name LIKE :company_name", "%" + criteria.getCompanyName() + "%");
//        "AND pip.status = :status_innovative_product"

        if (Optional.ofNullable(criteria.getCommunicateType()).isPresent()) {
            switch (criteria.getCommunicateType()) {
                case ALLPRODUCT:
                    break;
                case COMMUNICATING:
                    queryBuilderWhere.appendWhen(true, "AND pip.status = :status_innovative_product", 1)
                            .appendWhen(true, "AND pos.status = :status_org_suggestion", null)
                            .appendWhen(true, "OR pos.status = :status_org_suggestion", 0)
                            .appendWhen(true, "AND pos.write_condition = :write_condition", 0);
                    break;
                case COMMUNICATED:
                    queryBuilderWhere.appendWhen(true, "AND pip.status = :status_innovative_product", 1)
                            .appendWhen(true, "AND pos.status = :status_org_suggestion", 0)
                            .appendWhen(true, "AND pos.write_condition = :write_condition", 1);
                    break;
                case NOCOMMUNICATE:
                    queryBuilderWhere.appendWhen(true, "AND pip.status = :status_innovative_product", 1)
                            .appendWhen(true, "AND pos.status = :status_org_suggestion", 1);
                    break;
            }
        }
        return queryBuilderWhere;
    }
    //後台+PAGE LIST查詢
    @Override
    public List<ProInnovativeProductProUserDTO> findAllBackendProInnovativeProducts(ProInnovativeProductProUserCriteria criteria) {

        Query.Builder queryBuilder = Query
                .builder()
                .append(
                        " SELECT '"+Optional.ofNullable(criteria.getCommunicateType()).map(comm->comm.toString()).orElse("")+"' as communicate_type," +
                                " pip.PRO_INNOVATIVE_PRODUCT_ID, pip.INNOVATE_NAME, pip.INNOVATE_TYPE, pip.USER_ID, pip.CREATE_TIME,pip.STATUS, pip.STATUS as status_innovative_product, pos.STATUS  as status_org_suggestion," +
                                " pip.COMMUNICATE_DATE, pip.COMMUNICATE_DOC_NUMBER,pip.INNOVATE_FUNCTION,pip.INNOVATE_QUALITY,pip.INNOVATE_PROVE,pip.PATENT,pip.PATENT_START_DATE,pip.PATENT_END_DATE,pip.CONSTRUCT_COST,pip.MAINTAIN_COST,pip.INNOVATE_CASE_NAME," +
                                "pip.INNOVATE_CASE_VENDOR,pip.INNOVATE_CASE_SCALE, pos.TIME,pos.WRITE_CONDITION, ac.NAME as company_name, pip.ACCEPTED_ORGANIZATION "
                )
                .append( "FROM pro_innovative_product pip left join PRO_ORG_SUGGESTION pos on pip.PRO_INNOVATIVE_PRODUCT_ID=pos.PRO_INNOVATIVE_PRODUCT_ID left join ADM_ACCOUNT aa on pip.USER_ID = aa.USER_ID left join ADM_COMPANY ac on aa.COMP_IDNO = ac.COMP_IDNO" +
                        "  left join ADM_AGENT aag on pos.ORG_ID=aag.ORG_ID  WHERE 1=1")
                .appendWhen(StringUtils.isNotBlank(criteria.getInnovateName()), "AND pip.innovate_name LIKE :innovate_name", "%" + criteria.getInnovateName() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getCompanyName()), "AND ac.name LIKE :name", "%" + criteria.getCompanyName() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getOrgName()), "AND aag.org_name LIKE :org_name", "%" + criteria.getOrgName() + "%")
                .appendWhen(StringUtils.isNotBlank(criteria.getStatusInnovativeProduct()), "AND pip.status = :status_innovative_product", criteria.getStatusInnovativeProduct());

        if(Optional.ofNullable(criteria.getCommunicateType()).isPresent()) {
            switch (criteria.getCommunicateType()) {
                case ALLPRODUCT:
                    break;
                case COMMUNICATING:
                    queryBuilder.appendWhen(true, "AND pip.status = :status_innovative_product", 1)
                            .appendWhen(true, "AND pos.status = :status_org_suggestion", null)
                            .appendWhen(true, "OR pos.status = :status_org_suggestion", 0)
                            .appendWhen(true, "AND pos.write_condition = :write_condition", 0);
                    break;
                case COMMUNICATED:
                    queryBuilder.appendWhen(true, "AND pip.status = :status_innovative_product", 1)
                            .appendWhen(true, "AND pos.status = :status_org_suggestion", 0)
                            .appendWhen(true, "AND pos.write_condition = :write_condition", 1);
                    break;
                case NOCOMMUNICATE:
                    queryBuilder.appendWhen(true, "AND pip.status = :status_innovative_product", 1)
                            .appendWhen(true, "AND pos.status = :status_org_suggestion", 1);
                    break;
            }
        }
        //處理PAGE
        Query query = queryBuilder.append("ORDER BY CREATE_TIME DESC")
                .append(
                        "offset :currentPage * :perPage rows fetch next :perPage rows only",
                        criteria.getCurrentPage(),
                        criteria.getPerPage(),
                        criteria.getPerPage()
                )
                .build();

        List<ProInnovativeProductProUserDTO> result = sqlExecutor.queryForList(query, ProInnovativeProductProUserDTO.class);

        return result;

//        return sqlExecutor.queryForList(query, ProInnovativeProductProUserDTO.class);

    }
    //直接把上方的方法拿來組成後台查詢方法
    //後台PAGE查詢
    public Page<ProInnovativeProductProUserDTO> findPageBackendAllProInnovativeProducts(ProInnovativeProductProUserCriteria criteria){

        //StringUtils.isNotBlank(去掉空格)
        //--------------------------------------------------------------這區是查總筆數的資料-----------------------------------------------------------------
        int totalCount =  findPageCountAllProInnovativeProducts(criteria);
        //--------------------------------------------------------------這區是查該分頁比數的資料 假設有100筆資料 這頁可以查出20,40,60...筆資料--------------------------------------------------------
        List<ProInnovativeProductProUserDTO> proInnovativeProductProUserDTOs = findAllBackendProInnovativeProducts(criteria);
        //--------------------------------------------------------------這區是把總比數包起來的-----------------------------------------------------------------
        Page<ProInnovativeProductProUserDTO> resultPage = new PageImpl<>(proInnovativeProductProUserDTOs, criteria.toPageable(), totalCount);

        return resultPage;

    }



    //查明細資料
    @Override
    public ProInnovativeProductSumSearchDetailDTO findproInnovativeProductAndDetail(ProInnovativeProductProUserCriteria criteria) {

        ProInnovativeProductMainDTO proInnovativeProductMainDTO = findByInnovativeProductMain(criteria);
        List<ProEnginListDTO> proEnginListDTOList = findByProEnginList(criteria);
        List<ProRecommendListDTO> proRecommendListDTOList = findByProRecommendList(criteria);
        List<ProCommunicatedDTO> proCommunicatedDTOList = findByCommunicated(criteria);
        List<ProNoCommunicateDTO> proNoCommunicateDTOList = findByNoCommunicate(criteria);

        ProInnovativeProductSumSearchDetailDTO proInnovativeProductSumSearchDetailDTO = new ProInnovativeProductSumSearchDetailDTO(proInnovativeProductMainDTO,proEnginListDTOList,proRecommendListDTOList,proCommunicatedDTOList,proNoCommunicateDTOList);
        return proInnovativeProductSumSearchDetailDTO;
    }

    //查單一的資料
    private ProInnovativeProductMainDTO findByInnovativeProductMain(ProInnovativeProductProUserCriteria criteria){

        Query query = Query
                .builder()
                .append("SELECT pip.PRO_INNOVATIVE_PRODUCT_ID, pip.CREATE_TIME, pip.INNOVATE_NAME, pip.ACCEPTED_ORGANIZATION, pip.COMMUNICATE_DATE, pip.COMMUNICATE_DOC_NUMBER, pip.INNOVATE_TYPE, pip.INNOVATE_FUNCTION, pip.INNOVATE_QUALITY " +
                        ",pip.INNOVATE_PROVE, pip.PATENT, pip.PATENT_START_DATE, pip.PATENT_END_DATE, pip.CONSTRUCT_COST, pip.MAINTAIN_COST, pip.INNOVATE_CASE_NAME, pip.INNOVATE_CASE_VENDOR, pip.INNOVATE_CASE_SCALE, pip.STATUS as statusIP, pos.TIME, pos.TYPE, pos.ADDR, pos.OUTLINE, pos.MEMO, pos.OTHER, pos.COMM_REASON, ac.NAME, ac.COMP_IDNO, ac.ADDRESS_CITY" +
                        ", ac.ADDRESS_TOWN, ac.ADDRESS_VILLAGE, ac.ADDRESS_LIN, ac.ADDRESS_ROAD, aco.CH_NAME, aco.TITLE, aco.WORK_TEL ,pip.STATUS as status_innovative_product ,pos.STATUS  as status_org_suggestion ,pos.WRITE_CONDITION " +
                        "FROM PRO_INNOVATIVE_PRODUCT pip left outer join ADM_ACCOUNT aa on pip.USER_ID = aa.USER_ID left outer join ADM_COMPANY ac on aa.COMP_IDNO = ac.COMP_IDNO left join ADM_CONTACT aco on pip.USER_ID =aco.USER_ID left join PRO_ORG_SUGGESTION pos on pip.PRO_INNOVATIVE_PRODUCT_ID =pos.PRO_INNOVATIVE_PRODUCT_ID left join ADM_AGENT aag on pos.ORG_ID=aag.ORG_ID WHERE 1=1")
                .appendWhen(StringUtils.isNotBlank(criteria.getProInnovativeProductId()), "AND pip.PRO_INNOVATIVE_PRODUCT_ID = :proInnovativeProId", criteria.getProInnovativeProductId())
                .build();

        List<ProInnovativeProductMainDTO> proInnovativeProductMainDTOs = sqlExecutor.queryForList(query, ProInnovativeProductMainDTO.class);
        //判斷空或是取第一筆資料
        ProInnovativeProductMainDTO proInnovativeProductMainDTO= CollectionUtils.isEmpty(proInnovativeProductMainDTOs) ? null : proInnovativeProductMainDTOs.get(0);
        //sqlExecutor內含rowmapper方法可轉為DTO

        //使用admFileRepository查檔案資料
//        Optional<AdmFile> optAdmFile = admFileRepository.findByModuleTypeAndBusinessTypeAndSourceIdOrderBySourceId("PRO", "PRO_INNOVATIVE_PRODUCT", proInnovativeProductMainDTO.getProInnovativeProductId() + "_dmFile_");
//        if(optAdmFile.isPresent()){
//            proInnovativeProductMainDTO.setProAdmFile(optAdmFile.get());
//        }

        return proInnovativeProductMainDTO;
    }

    //查詢工程類別資料(資料為一對多)
    private List<ProEnginListDTO> findByProEnginList(ProInnovativeProductProUserCriteria criteria){
        Query query = Query
                .builder()
                .append("SELECT pel.ENGIN_TYPE FROM PRO_ENGIN_LIST pel  WHERE 1=1")
                .appendWhen(StringUtils.isNotBlank(criteria.getProInnovativeProductId()), "AND pel.PRO_INNOVATIVE_PRODUCT_ID = :proInnovativeProId", criteria.getProInnovativeProductId())
                .build();

        return sqlExecutor.queryForList(query, ProEnginListDTO.class);
    }

    //查詢推薦單位資料(資料為一對多)
    private List<ProRecommendListDTO> findByProRecommendList(ProInnovativeProductProUserCriteria criteria){
        Query query = Query
                .builder()
                .append("SELECT prl.RECOMMEND_TYPE, prl.RECOMMEND_NAME,prl.RECOMMEND_TEL FROM PRO_RECOMMEND_LIST prl   WHERE 1=1")
                .appendWhen(StringUtils.isNotBlank(criteria.getProInnovativeProductId()), "AND prl.PRO_INNOVATIVE_PRODUCT_ID = :proInnovativeProId", criteria.getProInnovativeProductId())
                .build();


//        List<ProRecommendListDTO> proRecommendListDTOList = sqlExecutor.queryForList(query, ProRecommendListDTO.class);
//        //使用admFileRepository查檔案資料 因為推薦單位內有分五個單位  所以把五個單位取名並且作對應
//        for (ProRecommendListDTO proRecommendListDTO:proRecommendListDTOList){
//            switch (proRecommendListDTO.getRecommendType()){
//                case "0":
//                    AdmFile rcollege = admFileRepository.findByModuleTypeAndBusinessTypeAndSourceIdOrderBySourceId("PRO", "PRO_INNOVATIVE_PRODUCT", criteria.getProInnovativeProductId() + "_r_college").orElse(null);
//                    proRecommendListDTO.setProAdmFile(rcollege);
//                    break;
//                case "1":
//                    AdmFile rresearch = admFileRepository.findByModuleTypeAndBusinessTypeAndSourceIdOrderBySourceId("PRO", "PRO_INNOVATIVE_PRODUCT", criteria.getProInnovativeProductId() + "_r_guild").orElse(null);
//                    proRecommendListDTO.setProAdmFile(rresearch);
//                    break;
//                case "2":
//                    AdmFile racademy = admFileRepository.findByModuleTypeAndBusinessTypeAndSourceIdOrderBySourceId("PRO", "PRO_INNOVATIVE_PRODUCT", criteria.getProInnovativeProductId() + "_r_association").orElse(null);
//                    proRecommendListDTO.setProAdmFile(racademy);
//                    break;
//                case "3":
//                    AdmFile rguild = admFileRepository.findByModuleTypeAndBusinessTypeAndSourceIdOrderBySourceId("PRO", "PRO_INNOVATIVE_PRODUCT", criteria.getProInnovativeProductId() + "_r_research").orElse(null);
//                    proRecommendListDTO.setProAdmFile(rguild);
//                    break;
//                case "4":
//                    AdmFile rassociation = admFileRepository.findByModuleTypeAndBusinessTypeAndSourceIdOrderBySourceId("PRO", "PRO_INNOVATIVE_PRODUCT", criteria.getProInnovativeProductId() + "_r_academy").orElse(null);
//                    proRecommendListDTO.setProAdmFile(rassociation);
//                    break;
//            }
//        }
        return sqlExecutor.queryForList(query, ProRecommendListDTO.class);
//        return proRecommendListDTOList;
    }

    //查詢已交流時一對多的資料(回復更新日期和參與人員)
    private List<ProCommunicatedDTO> findByCommunicated(ProInnovativeProductProUserCriteria criteria){
        Query query = Query
                .builder()
                .append("SELECT pos.UPDATE_TIME as update_time_pos ,popl.PERSON_NAME FROM PRO_INNOVATIVE_PRODUCT pip join PRO_ORG_SUGGESTION pos on pip.PRO_INNOVATIVE_PRODUCT_ID =pos.PRO_INNOVATIVE_PRODUCT_ID" +
                        " left join PRO_OS_PERSON_LIST popl on pos.PRO_ORG_SUGGESTION_ID =popl.PRO_ORG_SUGGESTION_ID    WHERE 1=1")
                .appendWhen(StringUtils.isNotBlank(criteria.getProInnovativeProductId()), "AND pip.PRO_INNOVATIVE_PRODUCT_ID = :proInnovativeProId", criteria.getProInnovativeProductId())
                .build();

        return sqlExecutor.queryForList(query, ProCommunicatedDTO.class);
    }

    //查詢不交流時一對多的資料(該資料的checkbox)
    private List<ProNoCommunicateDTO> findByNoCommunicate(ProInnovativeProductProUserCriteria criteria){
        Query query = Query
                .builder()
                .append("SELECT pncl.NO_COMMUNICATE_TYPE FROM PRO_INNOVATIVE_PRODUCT pip join PRO_ORG_SUGGESTION pos on pip.PRO_INNOVATIVE_PRODUCT_ID =pos.PRO_INNOVATIVE_PRODUCT_ID " +
                        " left join PRO_NO_COMMUNICATE_LIST pncl on pos.PRO_ORG_SUGGESTION_ID =pncl.PRO_ORG_SUGGESTION_ID WHERE 1=1")
                .appendWhen(StringUtils.isNotBlank(criteria.getProInnovativeProductId()), "AND pip.PRO_INNOVATIVE_PRODUCT_ID = :proInnovativeProId", criteria.getProInnovativeProductId())
                .build();

        return sqlExecutor.queryForList(query, ProNoCommunicateDTO.class);
    }


    public List<CommunicateProductQueryDTO> findByCriteria(CommunicateProductQueryDTO criteria) {
        Query query = Query
                .builder()
                .append(" SELECT  pro.PRO_INNOVATIVE_PRODUCT_ID , pro.INNOVATE_NAME, pos.WRITE_CONDITION , com.Name, pos.STATUS ")
                .append("FROM  PRO_INNOVATIVE_PRODUCT pro JOIN PRO_ORG_SUGGESTION pos ON pro.PRO_INNOVATIVE_PRODUCT_ID  = pos.PRO_INNOVATIVE_PRODUCT_ID")
                .append("JOIN ADM_ACCOUNT acc ON pro.USER_ID = acc.USER_ID" )
                .append("JOIN ADM_COMPANY com on acc.COMP_IDNO = com.COMP_IDNO" )
                .append("WHERE 1=1 ")
                .append(" AND pro.PRO_INNOVATIVE_PRODUCT_ID = :proInnovativeProductId ", criteria.getProInnovativeProductId())
                .build();

               List<CommunicateProductQueryDTO> CommunicateProductQueryDTOs = sqlExecutor.queryForList(query, CommunicateProductQueryDTO.class);
               return CommunicateProductQueryDTOs;
    }

    @Override
    public ProApplyCompanyDTO findApplyCompany(String userId) {

        Query query = Query
                .builder()
                .append("SELECT ac.NAME , ac.COMP_IDNO ,ac.RP_NAME  ,ac.ADDRESS_CITY ,ac.ADDRESS_TOWN ,ac.ADDRESS_VILLAGE ,ac.ADDRESS_LIN ,ac.ADDRESS_ROAD ,aa.USER_ID ,act.TITLE ,act.WORK_TEL")
                .append("FROM ADM_ACCOUNT aa left join ADM_COMPANY ac on aa.COMP_IDNO = ac.COMP_IDNO left join ADM_CONTACT act on act.USER_ID = aa.USER_ID WHERE 1=1 ")
                .appendWhen(StringUtils.isNotBlank(userId),   "AND aa.USER_ID  = :userId", userId)
                .build();

       List<ProApplyCompanyDTO> proApplyCompanyDTOs = sqlExecutor.queryForList(query, ProApplyCompanyDTO.class);
        ProApplyCompanyDTO result = null;
        if(proApplyCompanyDTOs.size() > 0) {
            result = proApplyCompanyDTOs.get(0);
        }
        return result;

    }

}
