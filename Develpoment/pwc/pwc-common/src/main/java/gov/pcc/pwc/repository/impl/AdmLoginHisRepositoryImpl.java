package gov.pcc.pwc.repository.impl;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.repository.custom.AdmLoginHisRepositoryCustom;
import gov.pcc.pwc.service.criteria.AdmLoginHisCriteria;
import gov.pcc.pwc.service.dto.AdmLoginHisDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.time.Instant;
import java.util.List;

public class AdmLoginHisRepositoryImpl implements AdmLoginHisRepositoryCustom {

    private final SqlExecutor sqlExecutor;

    public AdmLoginHisRepositoryImpl(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    @Override
    public Page<AdmLoginHisDTO> findAllByCriteria(AdmLoginHisCriteria criteria) {
        Query queryTotalCount = Query
                .builder()
                .append(" SELECT COUNT(*) FROM ADM_LOGIN_HIS alh JOIN ADM_CONTACT ac ON alh.create_user_sys = ac.user_id WHERE 1=1 ")
                .appendWhen(StringUtils.isNotBlank(criteria.getCreateUserSys()), " AND alh.CREATE_USER_SYS LIKE :createUserSys ","%" + criteria.getCreateUserSys() + "%")
                .appendWhen(
                        StringUtils.isNotBlank(criteria.getChName()),
                        " AND ac.CH_NAME LIKE :chName ",
                        "%" + criteria.getChName() + "%" )
                .appendWhen(
                        StringUtils.isNotBlank(criteria.getChNameRome()),
                        " AND ac.CH_NAME_ROME LIKE :chNameRome ",
                        "%" + criteria.getChNameRome() + "%" )
                .appendWhen(StringUtils.isNotBlank(criteria.getSysCategory()), " AND alh.SYS_CATEGORY = :sysCategory ", criteria.getSysCategory())
                .appendWhen(StringUtils.isNotBlank(criteria.getResult()), " AND alh.RESULT = :result ", criteria.getResult())
                .appendWhen( Instant.MIN != criteria.getDateStart()," AND alh.CREATE_TIME >= :dateStart ", criteria.getDateStart().toString())
                .appendWhen(Instant.MAX != criteria.getDateEnd()," AND alh.CREATE_TIME <= :dateEnd ", criteria.getDateEnd().toString())
                .build();
        int totalCount = sqlExecutor.queryForList(queryTotalCount, Integer.class).get(0);

        Query query = Query
                .builder()
                .append(" select row_number() over(order by " + criteria.getOrderBy() + ") as row_num, *")
                .append(" FROM ADM_LOGIN_HIS alh JOIN ADM_CONTACT ac ON alh.create_user_sys = ac.user_id ")
                .append(" WHERE 1=1 ")
                .appendWhen(StringUtils.isNotBlank(criteria.getCreateUserSys()), " AND alh.CREATE_USER_SYS LIKE :createUserSys ","%" + criteria.getCreateUserSys() + "%")
                .appendWhen(
                        StringUtils.isNotBlank(criteria.getChName()),
                        " AND ac.CH_NAME LIKE :chName ",
                        "%" + criteria.getChName() + "%" )
                .appendWhen(
                        StringUtils.isNotBlank(criteria.getChNameRome()),
                        " AND ac.CH_NAME_ROME LIKE :chNameRome ",
                        "%" + criteria.getChNameRome() + "%" )
                .appendWhen(StringUtils.isNotBlank(criteria.getSysCategory()), " AND alh.SYS_CATEGORY = :sysCategory ", criteria.getSysCategory())
                .appendWhen(StringUtils.isNotBlank(criteria.getResult()), " AND alh.RESULT = :result ", criteria.getResult())
                .appendWhen( Instant.MIN != criteria.getDateStart()," AND alh.CREATE_TIME >= :dateStart ", criteria.getDateStart().toString())
                .appendWhen(Instant.MAX != criteria.getDateEnd()," AND alh.CREATE_TIME <= :dateEnd ", criteria.getDateEnd().toString())
                .append("order by " + criteria.getOrderBy())
                .append(
                        "offset :currentPage * :perPage rows fetch next :perPage rows only",
                        criteria.getCurrentPage(),
                        criteria.getPerPage(),
                        criteria.getPerPage()
                )
                .build();

        List<AdmLoginHisDTO> admLoginHisDTOs = sqlExecutor.queryForList(query, AdmLoginHisDTO.class);
        // TODO 時間轉換...這邊硬+8小時
        if (admLoginHisDTOs.size() > 0) {
            admLoginHisDTOs.forEach(admLoginHisDTO -> {
                admLoginHisDTO.setCreateTime(admLoginHisDTO.getCreateTime().plusSeconds(28800));
            });
        }
        Page<AdmLoginHisDTO> resultPage = new PageImpl<>(admLoginHisDTOs, criteria.toPageable(), totalCount);
        return resultPage;
    }

    @Override
    public AdmLoginHisDTO findByPk(long id) {
        Query query = Query
                .builder()
                .append(" select * FROM ADM_LOGIN_HIS alh JOIN ADM_CONTACT ac ON alh.create_user_sys = ac.user_id")
                .append(" WHERE 1=1 ")
                .append( " AND alh.ID = :id ", id)
                .build();
        List<AdmLoginHisDTO> admLoginHisDTOs = sqlExecutor.queryForList(query, AdmLoginHisDTO.class);
        if (admLoginHisDTOs.size() > 0) {
            admLoginHisDTOs.get(0).setCreateTime(admLoginHisDTOs.get(0).getCreateTime().plusSeconds(28800));
            return admLoginHisDTOs.get(0);
        }
        return null;
    }
}
