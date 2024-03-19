package gov.pcc.pwc.repository.impl;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.repository.custom.EngReviewLogRepositoryCustom;
import gov.pcc.pwc.service.dto.EngReviewLogDTO;
import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class EngReviewLogRepositoryImpl implements EngReviewLogRepositoryCustom {

    private final SqlExecutor sqlExecutor;

    public EngReviewLogRepositoryImpl(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    @Override
    public int insert(EngReviewLogDTO dto) {
        Query query = Query
                .builder()
                .append("INSERT INTO [ENG_REVIEW_LOG] ([ENG_REVIEW_LOG_ID],[EXTERNAL_ID],[EXTERNAL_VERSION],[REVIEW_CASE_TYPE],[ITEM_NO],")
                .append("[REVIEW_STATUS],[REVIEW_STAFF],[REVIEW_TIME],[REVIEW_DESC],[UPDATE_TIME],[UPDATE_USER],[CREATE_TIME],[CREATE_USER])")
                .append("VALUES (")
                .append(":engReviewLogId, :externalId, :externalVersion,", dto.getEngReviewLogId(), dto.getExternalId(), dto.getExternalVersion())
                .append(":reviewCaseType, :itemNo, :reviewStatus,", dto.getReviewCaseType(), dto.getItemNo(), dto.getReviewStatus())
                .append(":reviewStaff, :reviewTime, :reviewDesc,", dto.getReviewStaff(), Timestamp.from(dto.getReviewTime()).toLocalDateTime(), dto.getReviewDesc())
//                .append(":reviewStaff, :reviewTime, :reviewDesc)", dto.getReviewStaff(), LocalDateTime.ofInstant(dto.getReviewTime(), ZoneId.systemDefault()), dto.getReviewDesc())
                .append(":updateTime, :updateUser,", Timestamp.from(dto.getUpdateTime()).toLocalDateTime(), dto.getUpdateUser())
                .append(":createTime, :createUser)", Timestamp.from(dto.getCreateTime()).toLocalDateTime(), dto.getCreateUser())
                .build();
        return sqlExecutor.insert(query);
    }
}
