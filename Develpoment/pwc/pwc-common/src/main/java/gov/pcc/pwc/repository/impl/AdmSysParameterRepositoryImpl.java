package gov.pcc.pwc.repository.impl;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.repository.custom.AdmAccountRepositoryCustom;
import gov.pcc.pwc.repository.custom.AdmSysParameterRepositoryCustom;
import gov.pcc.pwc.service.criteria.AdmAccountContactCriteria;
import gov.pcc.pwc.service.criteria.AdmSysParameterCriteria;
import gov.pcc.pwc.service.dto.AdmAccountContactDTO;
import gov.pcc.pwc.service.dto.AdmSysParameterDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

public class AdmSysParameterRepositoryImpl implements AdmSysParameterRepositoryCustom {

    private final SqlExecutor sqlExecutor;

    public AdmSysParameterRepositoryImpl(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    @Override
    public Page<AdmSysParameterDTO> findAllByCriteria(AdmSysParameterCriteria criteria) {

        Query queryTotalCount = Query
                .builder()
                .append(" SELECT COUNT(*) FROM ADM_SYS_PARAMETER WHERE 1=1 ")
                .appendWhen(StringUtils.isNotBlank(criteria.getSysName()), " AND SYS_NAME LIKE :sysName ","%" +  criteria.getSysName()+ "%")
                .appendWhen(
                        StringUtils.isNotBlank(criteria.getSysType()),
                        " AND SYS_TYPE LIKE :sysType ",
                        "%" + criteria.getSysType() + "%"
                )
                .appendWhen(StringUtils.isNotBlank(criteria.getStatus()), " AND STATUS = :status ", criteria.getStatus())
                .build();
        int totalCount = sqlExecutor.queryForList(queryTotalCount, Integer.class).get(0);

        Query query = Query
                .builder()
                .append(" select row_number() over(order by " + criteria.getOrderBy() + ") as row_num,")
                .append(
                        " SYS_NAME, SYS_TYPE, SYS_VALUE, DESCRIPT, STATUS, UPDATE_USER, UPDATE_TIME"
                )
                .append(" FROM ADM_SYS_PARAMETER ")
                .append(" WHERE 1=1 ")
                .appendWhen(StringUtils.isNotBlank(criteria.getSysName()), " AND SYS_NAME LIKE :sysName ", "%" +  criteria.getSysName()+ "%")
                .appendWhen(
                        StringUtils.isNotBlank(criteria.getSysType()),
                        " AND SYS_TYPE LIKE :sysType ",
                        "%" + criteria.getSysType() + "%"
                )
                .appendWhen(StringUtils.isNotBlank(criteria.getStatus()), " AND STATUS = :status ", criteria.getStatus())
                .append("order by " + criteria.getOrderBy())
                .append(
                        "offset :currentPage * :perPage rows fetch next :perPage rows only",
                        criteria.getCurrentPage(),
                        criteria.getPerPage(),
                        criteria.getPerPage()
                )
                .build();
        List<AdmSysParameterDTO> AdmSysParameterDTOs = sqlExecutor.queryForList(query, AdmSysParameterDTO.class);
        Page<AdmSysParameterDTO> resultPage = new PageImpl<>(AdmSysParameterDTOs, criteria.toPageable(), totalCount);
        return resultPage;

    }

    @Override
    public AdmSysParameterDTO findByPk(String sysName, String sysType) {
        Query query = Query
                .builder()
                .append(" select SYS_NAME, SYS_TYPE, SYS_VALUE, DESCRIPT, STATUS, UPDATE_USER, UPDATE_TIME")
                .append(" FROM ADM_SYS_PARAMETER ")
                .append( "WHERE SYS_NAME = :sysName ", sysName)
                .append( "AND SYS_TYPE = :sysType ", sysType)
                .build();
        List<AdmSysParameterDTO> AdmSysParameterDTOs = sqlExecutor.queryForList(query, AdmSysParameterDTO.class);
        AdmSysParameterDTO result = null;
        if (AdmSysParameterDTOs.size() > 0) {
            result = AdmSysParameterDTOs.get(0);
        }
        return result;
    }

    @Override
    public List<AdmSysParameterDTO> findAllSysTypeOpts() {
        Query query = Query
                .builder()
                .append(" select distinct SYS_TYPE FROM ADM_SYS_PARAMETER order by SYS_TYPE asc")
                .build();
        return sqlExecutor.queryForList(query, AdmSysParameterDTO.class);
    }

    @Override
    public int update(AdmSysParameterDTO dto) {
        Query query = Query
                .builder()
                .append(" UPDATE ADM_SYS_PARAMETER SET SYS_VALUE = :sysValue", dto.getSysValue())
                .append(" , DESCRIPT = :descript", dto.getDescript())
                .append(" , STATUS = :status", dto.getStatus())
                .append(" , Update_User = :updateUser", dto.getUpdateUser())
                .append(" , Update_Time = GetDate()")
                .append(" WHERE SYS_NAME = :sysName", dto.getSysName())
                .append(" AND SYS_TYPE = :sysType", dto.getSysType())
                .build();
        return sqlExecutor.update(query);
    }
}
