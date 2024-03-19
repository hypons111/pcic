package gov.pcc.pwc.service.criteria;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import gov.pcc.pwc.db.Criteria;
import gov.pcc.pwc.service.dto.ProOrgSuggestionHistoryDTO;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

public class ProOrgSuggestionHistoryCriteria extends Criteria<ProOrgSuggestionHistoryDTO> {


    //    表格流水號
    private int id;

    //    跳轉明細用
    private String proInnovativeProductId;

    //    創新產品名稱
    private String innovateName;

    //    廠商名稱
    private String companyName;

    //  廠商名稱 後端資料
    private String name;

    //    交流機關名稱
    private String communicateOrganName;

    // 交流機關名稱 後端資料
    private String orgName;

    //    交流或通知不交流時間 起
    private Instant communicateDateStart;

    //    交流或通知不交流時間 迄
    private Instant communicateDateEnd;

    //    交流或通知不交流時間(table show)
    private Instant time;

    //    廠商登錄日期 起
    private Instant createTimeStart;

    //    廠商登錄日期 迄
    private Instant createTimeEnd;

    //    廠商登錄日期 (table show)
    private Instant createTime;

    //    交流狀態
    private String communicateStatus;

    public String getProInnovativeProductId() {
        return proInnovativeProductId;
    }

    public void setProInnovativeProductId(String proInnovativeProductId) {
        this.proInnovativeProductId = proInnovativeProductId;
    }

    public String getInnovateName() {
        return innovateName;
    }

    public void setInnovateName(String innovateName) {
        this.innovateName = innovateName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCommunicateOrganName() {
        return communicateOrganName;
    }

    public void setCommunicateOrganName(String communicateOrganName) {
        this.communicateOrganName = communicateOrganName;
    }

    public Instant getCommunicateDateStart() {
        return communicateDateStart;
    }

    public void setCommunicateDateStart(Instant communicateDateStart) {
        this.communicateDateStart = communicateDateStart;
    }

    public Instant getCreateTimeStart() {
        return createTimeStart;
    }

    public void setCreateTimeStart(Instant createTimeStart) {
        this.createTimeStart = createTimeStart;
    }

    public String getCommunicateStatus() {
        return communicateStatus;
    }

    public void setCommunicateStatus(String communicateStatus) {
        this.communicateStatus = communicateStatus;
    }

    public Instant getCommunicateDateEnd() {
        return communicateDateEnd;
    }

    public void setCommunicateDateEnd(Instant communicateDateEnd) {
        this.communicateDateEnd = communicateDateEnd;
    }

    public Instant getCreateTimeEnd() {
        return createTimeEnd;
    }

    public void setCreateTimeEnd(Instant createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProOrgSuggestionHistoryCriteria that = (ProOrgSuggestionHistoryCriteria) o;
        return (
                Objects.equal(innovateName, that.innovateName) &&
                        Objects.equal(proInnovativeProductId, that.proInnovativeProductId) &&
                        Objects.equal(id, that.id) &&
                        Objects.equal(companyName, that.companyName) &&
                        Objects.equal(name, that.name) &&
                        Objects.equal(communicateOrganName, that.communicateOrganName) &&
                        Objects.equal(orgName, that.orgName) &&
                        Objects.equal(communicateDateStart, that.communicateDateStart) &&
                        Objects.equal(communicateDateEnd, that.communicateDateEnd) &&
                        Objects.equal(time, that.time) &&
                        Objects.equal(createTimeStart, that.createTimeStart) &&
                        Objects.equal(createTimeEnd, that.createTimeEnd) &&
                        Objects.equal(createTime, that.createTime) &&
                        Objects.equal(communicateStatus, that.communicateStatus)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id,proInnovativeProductId, innovateName, companyName, name, orgName, communicateOrganName, communicateDateStart, communicateDateEnd, time, createTimeStart, createTimeEnd, createTime, communicateStatus);
    }

    @Override
    public String toString() {
        return MoreObjects
                .toStringHelper(this)
                .add("id", id)
                .add("proInnovativeProductId", proInnovativeProductId)
                .add("innovateName", innovateName)
                .add("name", name)
                .add("companyName", companyName)
                .add("orgName", orgName)
                .add("communicateOrganName", communicateOrganName)
                .add("communicateDateStart", communicateDateStart)
                .add("communicateDateEnd", communicateDateEnd)
                .add("time", time)
                .add("createTimeStart", createTimeStart)
                .add("createTimeEnd", createTimeEnd)
                .add("createTime", createTime)
                .add("communicateStatus", communicateStatus)
                .toString();
    }

    @Override
    public String getOrderBy() {
        String sortBy;
        if (this.getSortBy()[0].equals("createTime")) {
            sortBy = "pip.CREATE_TIME";
        } else if (this.getSortBy()[0].equals("name")) {
            sortBy = "ac.NAME";
        } else if (this.getSortBy()[0].equals("time")) {
            sortBy = "pos.TIME";
        } else if (this.getSortBy()[0].equals("orgName")) {
            sortBy = "ag.ORG_NAME";
        } else if (this.getSortBy()[0].equals("innovateName")) {
            sortBy = "pip.INNOVATE_NAME";
        } else if (this.getSortBy()[0].equals("id")) {
            sortBy = "id";
        }else {
            sortBy = "id";
        }

        return sortBy + " " + this.getSortDirection().name();
    }

    @Override
    public Predicate toPredicate(Root<ProOrgSuggestionHistoryDTO> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Set<Predicate> predicates = new HashSet<>();

        if (StringUtils.isNotBlank(innovateName)) {
            predicates.add(criteriaBuilder.like(root.get("innovateName"), WILD_CARD + innovateName + WILD_CARD));
        }
        if (StringUtils.isNotBlank(companyName)) {
            predicates.add(criteriaBuilder.like(root.get("companyName"), WILD_CARD + companyName + WILD_CARD));
        }
        if (StringUtils.isNotBlank(name)) {
            predicates.add(criteriaBuilder.like(root.get("name"), WILD_CARD + name + WILD_CARD));
        }
        if (StringUtils.isNotBlank(orgName)) {
            predicates.add(criteriaBuilder.equal(root.get("orgName"), orgName));
        }
        if (communicateDateStart != null) {
            predicates.add(criteriaBuilder.equal(root.get("communicateDateStart"), communicateDateStart));
        }
        if (communicateDateStart != null) {
            predicates.add(criteriaBuilder.equal(root.get("communicateDateEnd"), communicateDateEnd));
        }
        if (time != null) {
            predicates.add(criteriaBuilder.equal(root.get("time"), time));
        }
        if (createTimeStart != null) {
            predicates.add(criteriaBuilder.equal(root.get("createTimeStart"), createTimeStart));
        }
        if (createTimeStart != null) {
            predicates.add(criteriaBuilder.equal(root.get("createTimeEnd"), createTimeEnd));
        }
        if (createTime != null) {
            predicates.add(criteriaBuilder.equal(root.get("createTime"), createTime));
        }
        if (StringUtils.isNotBlank(communicateStatus)) {
            predicates.add(criteriaBuilder.equal(root.get("communicateStatus"), communicateStatus));
        }
        return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
    }
}
