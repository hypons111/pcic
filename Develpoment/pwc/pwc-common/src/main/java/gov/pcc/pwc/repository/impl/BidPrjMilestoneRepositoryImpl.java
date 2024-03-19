package gov.pcc.pwc.repository.impl;

import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import gov.pcc.pwc.repository.custom.BidPrjMilestoneRepositoryCustom;
import gov.pcc.pwc.service.criteria.BidPrjMilestoneCriteria;
import gov.pcc.pwc.service.dto.BidPrjMilestoneDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.sql.Timestamp;
import java.util.*;

public class BidPrjMilestoneRepositoryImpl implements BidPrjMilestoneRepositoryCustom {
    private final SqlExecutor sqlExecutor;

    public BidPrjMilestoneRepositoryImpl(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    @Override
    public Page<BidPrjMilestoneDTO> findAllByCriteria(BidPrjMilestoneCriteria criteria) {
        Query queryCount = Query
                .builder()
                .append("SELECT COUNT(*) FROM BID_PRJ_MILESTONE where 1=1")
                .append("AND WKUT = :wkut",criteria.getWkut())
                .append("AND PRJNO = :prjno",criteria.getPrjno())
                .build();
        int totalCount = sqlExecutor.queryForList(queryCount, Integer.class).get(0);

        Query query = Query
                .builder()
                .append("SELECT ROW_NUMBER() OVER (ORDER BY "+ criteria.getOrderBy()+ ") AS row_num, * FROM BID_PRJ_MILESTONE where 1=1")
                .append("AND WKUT = :wkut",criteria.getWkut())
                .append("AND PRJNO = :prjno",criteria.getPrjno())
                .append("order by " + criteria.getOrderBy())
                .append("offset :currentPage * :perPage rows fetch next :perPage rows only",
                        criteria.getCurrentPage(), criteria.getPerPage(), criteria.getPerPage())
                .build();
        List<BidPrjMilestoneDTO> bidPrjMilestoneDTOS = sqlExecutor.queryForList(query, BidPrjMilestoneDTO.class);
        Page<BidPrjMilestoneDTO> resultPage = new PageImpl<>(bidPrjMilestoneDTOS,criteria.toPageable(),totalCount);

        return resultPage;

    }

    @Override
    public void insertBidPrjMilestone(BidPrjMilestoneDTO bidPrjMilestoneDTO) {
        System.out.println(" query : "+bidPrjMilestoneDTO);

        Query query =Query
                .builder()
                .append("INSERT INTO BID_PRJ_MILESTONE (WKUT,PRJNO,APPLY_DATE,MILESTONE_NO,MILESTONE_NAME,MILESTONE_TYPE,MILESTONE_STATUS,ESTIMATE_DATE" +
                        ",ACTUAL_DATE,REMARK,CREATE_DATE,CREATE_USER,UPDATE_DATE,UPDATE_USER) ")
                .append("VALUES(:wkut,:prjno,:applydate,:milestoneNo,:milestoneName,:milestoneType," +
                        ":milestoneStatus,:estimateDate,:actualDate,:remark,:createDate,:createUser,:updateDate,:updateUser)",
                        bidPrjMilestoneDTO.getWkut(),bidPrjMilestoneDTO.getPrjno(),bidPrjMilestoneDTO.getApplyDate(),bidPrjMilestoneDTO.getMilestoneNo(),bidPrjMilestoneDTO.getMilestoneName(),
                        bidPrjMilestoneDTO.getMilestoneType(),bidPrjMilestoneDTO.getMilestoneStatus(),bidPrjMilestoneDTO.getEstimateDate(),bidPrjMilestoneDTO.getActualDate(),
                        bidPrjMilestoneDTO.getRemark(), Timestamp.from(bidPrjMilestoneDTO.getCreateDate()) ,bidPrjMilestoneDTO.getCreateUser(),Timestamp.from(bidPrjMilestoneDTO.getUpdateDate()),bidPrjMilestoneDTO.getUpdateUser())
                .build();
        System.out.println("&&&&& query : "+query);
        sqlExecutor.insert(query);
    }
}
