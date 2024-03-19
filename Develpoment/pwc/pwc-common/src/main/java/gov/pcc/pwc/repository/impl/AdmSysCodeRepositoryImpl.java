package gov.pcc.pwc.repository.impl;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.repository.custom.AdmSysCodeRepositoryCustom;
import gov.pcc.pwc.repository.custom.AdmSysParameterRepositoryCustom;
import gov.pcc.pwc.service.criteria.AdmSysCodeCriteria;
import gov.pcc.pwc.service.criteria.AdmSysParameterCriteria;
import gov.pcc.pwc.service.dto.AdmSysCodeDTO;
import gov.pcc.pwc.service.dto.AdmSysParameterDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

public class AdmSysCodeRepositoryImpl implements AdmSysCodeRepositoryCustom {

    private final SqlExecutor sqlExecutor;

    public AdmSysCodeRepositoryImpl(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    @Override
    public Page<AdmSysCodeDTO> findAllByCriteria(AdmSysCodeCriteria criteria) {

        Query queryTotalCount = Query
                .builder()
                .append(" SELECT COUNT(*) FROM ADM_SYS_CODE WHERE 1=1 ")
                .appendWhen(StringUtils.isNotBlank(criteria.getModuleType()), " AND MODULE_TYPE = :moduleType ",criteria.getModuleType())
                .appendWhen(
                        StringUtils.isNotBlank(criteria.getDataType()),
                        " AND DATA_TYPE = :dataType ",
                        criteria.getDataType()
                )
                .appendWhen(StringUtils.isNotBlank(criteria.getDataKey()), " AND DATA_KEY LIKE :dataKey ", "%" + criteria.getDataKey() + "%")
                .build();
        int totalCount = sqlExecutor.queryForList(queryTotalCount, Integer.class).get(0);

        Query query = Query
                .builder()
                .append(" select row_number() over(order by " + criteria.getOrderBy() + ") as row_num,")
                .append(
                        " MODULE_TYPE, DATA_TYPE, SORT_NO, DATA_KEY, VALUE, CODE_DESCRIPT, EN_CODE_DESCRIPT, REMARK, " +
                                "IS_VALID, VALID_FROM_DATE, IS_MODIFY, ATTR1, ATTR2, " +
                                "CREATE_USER, CREATE_TIME, UPDATE_USER, UPDATE_TIME"
                )
                .append(" FROM ADM_SYS_CODE ")
                .append(" WHERE 1=1 ")
                .appendWhen(StringUtils.isNotBlank(criteria.getModuleType()), " AND MODULE_TYPE = :moduleType ", criteria.getModuleType())
                .appendWhen(
                        StringUtils.isNotBlank(criteria.getDataType()),
                        " AND DATA_TYPE = :dataType ",
                        criteria.getDataType()
                )
                .appendWhen(StringUtils.isNotBlank(criteria.getDataKey()), " AND DATA_KEY LIKE :dataKey ", "%" + criteria.getDataKey() + "%")
                .append("order by " + criteria.getOrderBy())
                .append(
                        "offset :currentPage * :perPage rows fetch next :perPage rows only",
                        criteria.getCurrentPage(),
                        criteria.getPerPage(),
                        criteria.getPerPage()
                )
                .build();
        List<AdmSysCodeDTO> AdmSysCodeDTOs = sqlExecutor.queryForList(query, AdmSysCodeDTO.class);
        Page<AdmSysCodeDTO> resultPage = new PageImpl<>(AdmSysCodeDTOs, criteria.toPageable(), totalCount);
        return resultPage;

    }

    @Override
    public AdmSysCodeDTO findByPk(String moduleType, String dataType, String dataKey) {
        Query query = Query
                .builder()
                .append(" select * from ADM_SYS_CODE ")
                .append( " WHERE MODULE_TYPE = :moduleType ", moduleType)
                .append( " AND DATA_TYPE = :dataType ", dataType)
                .append( " AND DATA_KEY = :dataKey ", dataKey)
                .build();
        List<AdmSysCodeDTO> AdmSysCodeDTOs = sqlExecutor.queryForList(query, AdmSysCodeDTO.class);
        AdmSysCodeDTO result = null;
        if (AdmSysCodeDTOs.size() > 0) {
            result = AdmSysCodeDTOs.get(0);
        }
        return result;
    }

    @Override
    public List<AdmSysCodeDTO> findAllDataTypeOpts() {
        Query query = Query
                .builder()
                .append(" select distinct DATA_TYPE FROM ADM_SYS_CODE order by DATA_TYPE asc")
                .build();
        return sqlExecutor.queryForList(query, AdmSysCodeDTO.class);
    }

    @Override
    public int update(AdmSysCodeDTO dto) {
        Query query = Query
                .builder()
                .append(" UPDATE ADM_SYS_CODE SET SORT_NO = :sortNo", dto.getSortNo())
                .append(" , VALUE = :value", dto.getValue())
                .appendWhen(StringUtils.isNotBlank(dto.getCodeDescript())," , CODE_DESCRIPT = :codeDescript", dto.getCodeDescript())
                .appendWhen(StringUtils.isNotBlank(dto.getEnCodeDescript())," , EN_CODE_DESCRIPT = :enCodeDescript", dto.getEnCodeDescript())
                .appendWhen(StringUtils.isNotBlank(dto.getRemark())," , REMARK = :remark", dto.getRemark())
                .append(" , IS_VALID = :isValid", dto.getIsValid())
                .appendWhen(dto.getValidFromDate() != null ," , VALID_FROM_DATE = GetDate()")
                .append(" , IS_MODIFY = :isModify", dto.getIsModify())
                .appendWhen(StringUtils.isNotBlank(dto.getAttr1())," , ATTR1 = :attr1", dto.getAttr1())
                .appendWhen(StringUtils.isNotBlank(dto.getAttr2())," , ATTR2 = :attr2", dto.getAttr2())
                .append(" , Update_User = :updateUser", dto.getUpdateUser())
                .append(" , Update_Time = GetDate()")
                .append(" WHERE MODULE_TYPE = :moduleType", dto.getModuleType())
                .append(" AND DATA_TYPE = :dataType", dto.getDataType())
                .append(" AND DATA_KEY = :dataKey", dto.getDataKey())
                .build();
        return sqlExecutor.update(query);
    }
}
