package gov.pcc.pwc.repository.impl;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.repository.custom.PwcDownloadRepositoryCustom;
import gov.pcc.pwc.service.dto.PwcDownloadDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class PwcDownloadRepositoryImpl implements PwcDownloadRepositoryCustom {

    private final SqlExecutor sqlExecutor;

    public PwcDownloadRepositoryImpl(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    @Override
    public List<PwcDownloadDTO> findByDownload(PwcDownloadDTO criteria) {
        Query query = Query
                .builder()
                .append(" SELECT ID, SUBJECT, TEXT, " )
                .append(" (SELECT CODE_DESCRIPT FROM ADM_SYS_CODE WHERE MODULE_TYPE = 'PWC' AND DATA_TYPE = 'KIND' AND DATA_KEY = KIND COLLATE Chinese_Taiwan_Stroke_CI_AS) AS KIND, " ) // $$$
                .append(" LOCATION, RELEASE_DATE, DOWN_DATE, RELEASE_UNIT, CONTACT_PERSON, CONTACT_INFORMATION_TEL, CONTACT_INFORMATION_EMAIL, ENABLE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, TYPE " )
                .append(" FROM PWC_DOWNLOAD ")
                .append(" WHERE 1=1 ")
                .append(" AND ENABLE = 'Y' ")
                .appendWhen(StringUtils.isNotBlank(criteria.getLocation()), " AND LOCATION IN (" + criteria.getLocation() + ")")
                .appendWhen(StringUtils.isNotBlank(criteria.getKind()), " AND Kind = :kind ", criteria.getKind())
                .appendWhen(StringUtils.isNotBlank(criteria.getSubject()), " AND Subject LIKE :Subject ", "%" + criteria.getSubject() + "%")
                .appendWhen(StringUtils.isNotBlank(String.valueOf(criteria.getReleaseDate())), " AND Release_Date BETWEEN :releaseDate ",  criteria.getReleaseDate())
                .appendWhen(StringUtils.isNotBlank(String.valueOf(criteria.getDownDate())), " AND :downDate ", criteria.getDownDate())
                .append(" ORDER BY Release_Date DESC ")
                .build();
        return sqlExecutor.queryForList(query, PwcDownloadDTO.class);
    }

    @Override
    public int deleteByID(PwcDownloadDTO criteria) {
        Query query = Query
                .builder()
                .append(" DELETE FROM PWC_DOWNLOAD WHERE 1=1 ")
                .appendWhen(StringUtils.isNotBlank(criteria.getId().toString()), " AND Id = "+ criteria.getId() )
                .build();

        return sqlExecutor.delete(query);
    }

    @Override
    public List<PwcDownloadDTO> findDownByCriteria(PwcDownloadDTO criteria) {
        Query query = Query
                .builder()
                .append(" SELECT DISTINCT pd.ID, pd.SUBJECT, pd.TEXT, " )
                .append(" (SELECT CODE_DESCRIPT FROM ADM_SYS_CODE WHERE MODULE_TYPE = 'PWC' AND DATA_TYPE = 'KIND' AND DATA_KEY = KIND) AS KIND, " )
                .append(" LOCATION, RELEASE_DATE, DOWN_DATE, RELEASE_UNIT, CONTACT_PERSON, CONTACT_INFORMATION_TEL, CONTACT_INFORMATION_EMAIL, ENABLE, " )
                .append(" pd.UPDATE_USER, pd.UPDATE_TIME, pd.CREATE_USER, pd.CREATE_TIME, pd.TYPE  ")
                .append(" FROM ADM_ACCOUNT aa ")
                .append(" JOIN ADM_ACCOUNT_ROLE aar on aar.USER_ID = aa.USER_ID ")
                .append(" JOIN PWB_ROLE_MODULE prm on prm.ROLE_ID = aar.ROLE_Id ")
                .append(" JOIN PWC_DOWNLOAD_ROLE pdr on pdr.[ROLE] = aar.ROLE_Id ")
                .append(" JOIN PWC_DOWNLOAD pd on pd.ID = pdr.PRO_DOWNLOAD_ID ")
                .append(" JOIN PWC_DOWNLOAD_MODULE pim on pim.PRO_DOWNLOAD_ID = pd.ID ")
                .append(" WHERE 1=1 ")
                .append(" AND pd.ENABLE = 'Y' ")
                .append(" AND pd.LOCATION IN ('A','B') ")
                .append(" AND aa.USER_ID = :userId" , criteria.getUserId())
                .appendWhen(StringUtils.isNotBlank(criteria.getKind()), " AND Kind = :kind ", criteria.getKind())
                .appendWhen(StringUtils.isNotBlank(criteria.getSubject()), " AND Subject LIKE :subject ", "%" + criteria.getSubject() + "%")
                .appendWhen(StringUtils.isNotBlank(String.valueOf(criteria.getReleaseDate())), " AND Release_Date BETWEEN :releaseDate ",  criteria.getReleaseDate())
                .appendWhen(StringUtils.isNotBlank(String.valueOf(criteria.getDownDate())), " AND :downDate ", criteria.getDownDate())
                .append(" ORDER BY RELEASE_DATE DESC ")
                .build();
        return sqlExecutor.queryForList(query, PwcDownloadDTO.class);
    }
}
