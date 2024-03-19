package gov.pcc.pwc.repository.impl;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.repository.custom.PwcInformationRepositoryCustom;
import gov.pcc.pwc.service.dto.PwcInformationDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class PwcInformationRepositoryImpl implements PwcInformationRepositoryCustom {

    private final SqlExecutor sqlExecutor;

    public PwcInformationRepositoryImpl(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    @Override
    public List<PwcInformationDTO> findByInformation(PwcInformationDTO criteria) {
        Query query = Query
                .builder()
                .append(" SELECT ID, SUBJECT, TEXT, " )
                .append(" (SELECT CODE_DESCRIPT FROM ADM_SYS_CODE WHERE MODULE_TYPE = 'PWC' AND DATA_TYPE = 'KIND' AND DATA_KEY = KIND COLLATE Chinese_Taiwan_Stroke_CI_AS) AS KIND, " ) // $$$
                .append(" LOCATION, RELEASE_DATE, DOWN_DATE, RELEASE_UNIT, CONTACT_PERSON, CONTACT_INFORMATION_TEL, CONTACT_INFORMATION_EMAIL, ENABLE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, IS_TOP, TYPE " )
                .append(" FROM PWC_INFORMATION ")
                .append(" WHERE 1=1 ")
                .append(" AND ENABLE = 'Y' ")
                .appendWhen(StringUtils.isNotBlank(criteria.getLocation()), " AND LOCATION IN (" + criteria.getLocation() + ")")
                .appendWhen(StringUtils.isNotBlank(criteria.getKind()), " AND Kind = :kind ", criteria.getKind())
                .appendWhen(StringUtils.isNotBlank(criteria.getSubject()), " AND Subject LIKE :Subject ", "%" + criteria.getSubject() + "%")
                .appendWhen(StringUtils.isNotBlank(String.valueOf(criteria.getReleaseDate())), " AND Release_Date BETWEEN :releaseDate ",  criteria.getReleaseDate())
                .appendWhen(StringUtils.isNotBlank(String.valueOf(criteria.getDownDate())), " AND :downDate ", criteria.getDownDate())
                .append(" ORDER BY IS_TOP DESC, Release_Date DESC ")
                .build();
        return sqlExecutor.queryForList(query, PwcInformationDTO.class);
    }

    @Override
    public int deleteByID(PwcInformationDTO criteria) {
        Query query = Query
                .builder()
                .append(" DELETE FROM PWC_INFORMATION WHERE 1=1 ")
                .appendWhen(StringUtils.isNotBlank(criteria.getId().toString()), " AND Id = "+ criteria.getId() )
                .build();
        return sqlExecutor.delete(query);
    }

    @Override
    public List<PwcInformationDTO> findInfoByCriteria(PwcInformationDTO criteria) {
        Query query = Query
                .builder()
                .append(" SELECT DISTINCT pinf.ID, pinf.SUBJECT, pinf.TEXT, " )
                .append(" (SELECT CODE_DESCRIPT FROM ADM_SYS_CODE WHERE MODULE_TYPE = 'PWC' AND DATA_TYPE = 'KIND' AND DATA_KEY = KIND) AS KIND, " )
                .append(" LOCATION, RELEASE_DATE, DOWN_DATE, RELEASE_UNIT, CONTACT_PERSON, CONTACT_INFORMATION_TEL, CONTACT_INFORMATION_EMAIL, ENABLE, " )
                .append(" pinf.UPDATE_USER, pinf.UPDATE_TIME, pinf.CREATE_USER, pinf.CREATE_TIME, IS_TOP, pinf.TYPE  ")
                .append(" FROM ADM_ACCOUNT aa ")
                .append(" JOIN ADM_ACCOUNT_ROLE aar on aar.USER_ID = aa.USER_ID ")
                .append(" JOIN PWB_ROLE_MODULE prm on prm.ROLE_ID = aar.ROLE_Id ")
                .append(" JOIN PWC_INFORMATION_ROLE pir on pir.[ROLE] = aar.ROLE_Id ")
                .append(" JOIN PWC_INFORMATION pinf on pinf.ID = pir.PRO_INFORMATION_ID ")
                .append(" JOIN PWC_INFORMATION_MODULE pim on pim.PRO_INFORMATION_ID = pinf.ID ")
                .append(" WHERE 1=1 ")
                .append(" AND pinf.ENABLE = 'Y' ")
                .append(" AND pinf.LOCATION IN ('A','B') ")
                .append(" AND aa.USER_ID = :userId" , criteria.getUserId())
                .appendWhen(StringUtils.isNotBlank(criteria.getKind()), " AND Kind = :kind ", criteria.getKind())
                .appendWhen(StringUtils.isNotBlank(criteria.getSubject()), " AND Subject LIKE :subject ", "%" + criteria.getSubject() + "%")
                .appendWhen(StringUtils.isNotBlank(String.valueOf(criteria.getReleaseDate())), " AND Release_Date BETWEEN :releaseDate ",  criteria.getReleaseDate())
                .appendWhen(StringUtils.isNotBlank(String.valueOf(criteria.getDownDate())), " AND :downDate ", criteria.getDownDate())
                .append(" ORDER BY IS_TOP DESC, RELEASE_DATE DESC ")
                .build();
        return sqlExecutor.queryForList(query, PwcInformationDTO.class);
    }

    @Override
    public Long findTypeByUserId(PwcInformationDTO criteria) {
        Query query = Query
                .builder()
                .append(" SELECT COUNT(*) FROM ADM_ACCOUNT aa  " )
                .append(" JOIN ADM_ACCOUNT_ROLE aar ON aa.USER_ID = aar.User_Id " )
                .append(" JOIN PWB_JURISDICTION pj ON aar.Role_Id = pj.ROLE_ID " )
                .append(" WHERE 1=1 ")
                .append(" AND aar.USER_ID = :userId" , criteria.getUserId())
                .append(" AND TYPE = '00' ")
                .build();

        List<Long> result = sqlExecutor.queryForList(query, Long.class);
        if (result.isEmpty()) {
            return null;
        } else {
            return result.get(0);
        }
    }

}
