package gov.pcc.pwc.service.dto;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EngCaseTotalDTO implements Serializable {

    @NotNull
    @Size(max = 20)
    private String engCaseId;

    @Size(max = 100)
    private String caseNo;

    @Size(max = 20)
    private String tenderNo;

    @Size(max = 20)
    private String awardNo;

    @Size(max = 1)
    private String caseType;

//    @NotNull
    @Size(max = 300)
    private String caseName;

    @Size(max = 100)
    private String orgId;

    @Size(max = 50)
    private String orgName;

    @Size(max = 100)
    private String execOrgId;

    @Size(max = 50)
    private String execOrgName;

    @Size(max = 200)
    private String contactPerson;

    @Size(max = 50)
    private String contactTel;

    @Size(max = 600)
    private String entrustItem;

    private LocalDate tenderDate;

    // 自己加的，為了查詢
    private LocalDate tenderDateStart;

    // 自己家的，為了查詢
    private LocalDate tenderDateEnd;

    private BigDecimal tenderPrice;

//    @NotNull
    private LocalDate execDateStart;

//    @NotNull
    private LocalDate execDateEnd;

//    @NotNull
    @Size(max = 20)
    private String projectType;

//    @NotNull
    @Size(max = 100)
    private String recordRangeList;

    @Size(max = 100)
    private String recordRangeOther;

//    @NotNull
    @Size(max = 50)
    private String recordItemsList;

    @Size(max = 100)
    private String recordItemsOther;

    private String remark;

    // BidComp
//    @NotNull
    private int compItem;

    private int engrItem;

    @Size(max = 20)
    private String compId;

    @Size(max = 20)
    private String compIdno;

    @Size(max = 200)
    private String compName;

    @Size(max = 50)
    private String compLicenseNo;

    // Engr
    @Size(max = 20)
    private String engrId;

    @Size(max = 20)
    private String engrName;

    @Size(max = 50)
    private String engrNameRome;

    @Size(max = 300)
    private String engEngrCertificateIdList;

    @Size(max = 30)
    private String docNo;

    private LocalDate closeDate;

    @Size(max = 1)
    private String status;

    // record
    private LocalDate expectDate;

    private Instant realTime;

    private String recordContent;

    private String recordComment;

    private Instant signDate;

//    @NotNull
    private int noSignItem;

    private String subject;


//    @NotNull
    private Instant updateTime;

//    @NotNull
    @Size(max = 20)
    private String updateUser;

//    @NotNull
    private Instant createTime;

//    @NotNull
    @Size(max = 20)
    private String createUser;

    @Size(max = 100)
    private String transferFrom;

    private Instant transferTime;

    private List<Comp> compList = new ArrayList<>();

    private List<Engr> engrList = new ArrayList<>();

    public static class Comp {

        private String compId;
        private String compName;

        public Comp() {}

        public Comp(String compId, String compName) {
            this.compId = compId;
            this.compName = compName;
        }

        public String getCompId() {
            return compId;
        }

        public void setCompId(String compId) {
            this.compId = compId;
        }

        public String getCompName() {
            return compName;
        }

        public void setCompName(String compName) {
            this.compName = compName;
        }
    }

    public static class Engr{

        private String engrId;
        private String engrName;
        private String subject;
        private String recordRangeList;
        private String recordItemsList;
        private LocalDate execDateStart;
        private LocalDate execDateEnd;
        private Instant signDate;
        private LocalDate closeDate;
        private Instant updateTime;

        private String recordItemsOther;

        private String recordRangeOther;

        public Engr() {
        }

        public Engr(String engrId, String engrName, String subject,
                    String recordRangeList, String recordItemsList, LocalDate execDateStart,
                    LocalDate execDateEnd, Instant signDate, LocalDate closeDate,
                    Instant updateTime, String recordItemsOther, String recordRangeOther) {
            this.engrId = engrId;
            this.engrName = engrName;
            this.subject = subject;
            this.recordRangeList = recordRangeList;
            this.recordItemsList = recordItemsList;
            this.execDateStart = execDateStart;
            this.execDateEnd = execDateEnd;
            this.signDate = signDate;
            this.closeDate = closeDate;
            this.updateTime = updateTime;
            this.recordItemsOther = recordItemsOther;
            this.recordRangeOther = recordRangeOther;
        }

        public String getEngrId() {
            return engrId;
        }

        public void setEngrId(String engrId) {
            this.engrId = engrId;
        }

        public String getEngrName() {
            return engrName;
        }

        public void setEngrName(String engrName) {
            this.engrName = engrName;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getRecordRangeList() {
            return recordRangeList;
        }

        public void setRecordRangeList(String recordRangeList) {
            this.recordRangeList = recordRangeList;
        }

        public String getRecordItemsList() {
            return recordItemsList;
        }

        public void setRecordItemsList(String recordItemsList) {
            this.recordItemsList = recordItemsList;
        }

        public LocalDate getExecDateStart() {
            return execDateStart;
        }

        public void setExecDateStart(LocalDate execDateStart) {
            this.execDateStart = execDateStart;
        }

        public LocalDate getExecDateEnd() {
            return execDateEnd;
        }

        public void setExecDateEnd(LocalDate execDateEnd) {
            this.execDateEnd = execDateEnd;
        }

        public Instant getSignDate() {
            return signDate;
        }

        public void setSignDate(Instant signDate) {
            this.signDate = signDate;
        }

        public LocalDate getCloseDate() {
            return closeDate;
        }

        public void setCloseDate(LocalDate closeDate) {
            this.closeDate = closeDate;
        }

        public Instant getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Instant updateTime) {
            this.updateTime = updateTime;
        }

        public String getRecordItemsOther() {
            return recordItemsOther;
        }

        public void setRecordItemsOther(String recordItemsOther) {
            this.recordItemsOther = recordItemsOther;
        }

        public String getRecordRangeOther() {
            return recordRangeOther;
        }

        public void setRecordRangeOther(String recordRangeOther) {
            this.recordRangeOther = recordRangeOther;
        }
    }

    public String getEngCaseId() {
        return engCaseId;
    }

    public void setEngCaseId(String engCaseId) {
        this.engCaseId = engCaseId;
    }

    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }

    public String getTenderNo() {
        return tenderNo;
    }

    public void setTenderNo(String tenderNo) {
        this.tenderNo = tenderNo;
    }

    public String getAwardNo() {
        return awardNo;
    }

    public void setAwardNo(String awardNo) {
        this.awardNo = awardNo;
    }

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getExecOrgId() {
        return execOrgId;
    }

    public void setExecOrgId(String execOrgId) {
        this.execOrgId = execOrgId;
    }

    public String getExecOrgName() {
        return execOrgName;
    }

    public void setExecOrgName(String execOrgName) {
        this.execOrgName = execOrgName;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    public String getEntrustItem() {
        return entrustItem;
    }

    public void setEntrustItem(String entrustItem) {
        this.entrustItem = entrustItem;
    }

    public LocalDate getTenderDate() {
        return tenderDate;
    }

    public void setTenderDate(LocalDate tenderDate) {
        this.tenderDate = tenderDate;
    }

    public LocalDate getTenderDateStart() {
        return tenderDateStart;
    }

    public void setTenderDateStart(LocalDate tenderDateStart) {
        this.tenderDateStart = tenderDateStart;
    }

    public LocalDate getTenderDateEnd() {
        return tenderDateEnd;
    }

    public void setTenderDateEnd(LocalDate tenderDateEnd) {
        this.tenderDateEnd = tenderDateEnd;
    }

    public BigDecimal getTenderPrice() {
        return tenderPrice;
    }

    public void setTenderPrice(BigDecimal tenderPrice) {
        this.tenderPrice = tenderPrice;
    }

    public LocalDate getExecDateStart() {
        return execDateStart;
    }

    public void setExecDateStart(LocalDate execDateStart) {
        this.execDateStart = execDateStart;
    }

    public LocalDate getExecDateEnd() {
        return execDateEnd;
    }

    public void setExecDateEnd(LocalDate execDateEnd) {
        this.execDateEnd = execDateEnd;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getRecordRangeList() {
        return recordRangeList;
    }

    public void setRecordRangeList(String recordRangeList) {
        this.recordRangeList = recordRangeList;
    }

    public String getRecordRangeOther() {
        return recordRangeOther;
    }

    public void setRecordRangeOther(String recordRangeOther) {
        this.recordRangeOther = recordRangeOther;
    }

    public String getRecordItemsList() {
        return recordItemsList;
    }

    public void setRecordItemsList(String recordItemsList) {
        this.recordItemsList = recordItemsList;
    }

    public String getRecordItemsOther() {
        return recordItemsOther;
    }

    public void setRecordItemsOther(String recordItemsOther) {
        this.recordItemsOther = recordItemsOther;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getCompItem() {
        return compItem;
    }

    public void setCompItem(int compItem) {
        this.compItem = compItem;
    }

    public int getEngrItem() {
        return engrItem;
    }

    public void setEngrItem(int engrItem) {
        this.engrItem = engrItem;
    }

    public String getCompId() {
        return compId;
    }

    public void setCompId(String compId) {
        this.compId = compId;
    }

    public String getCompIdno() {
        return compIdno;
    }

    public void setCompIdno(String compIdno) {
        this.compIdno = compIdno;
    }

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public String getCompLicenseNo() {
        return compLicenseNo;
    }

    public void setCompLicenseNo(String compLicenseNo) {
        this.compLicenseNo = compLicenseNo;
    }

    public String getEngrId() {
        return engrId;
    }

    public void setEngrId(String engrId) {
        this.engrId = engrId;
    }

    public String getEngrName() {
        return engrName;
    }

    public void setEngrName(String engrName) {
        this.engrName = engrName;
    }

    public String getEngrNameRome() {
        return engrNameRome;
    }

    public void setEngrNameRome(String engrNameRome) {
        this.engrNameRome = engrNameRome;
    }

    public String getEngEngrCertificateIdList() {
        return engEngrCertificateIdList;
    }

    public void setEngEngrCertificateIdList(String engEngrCertificateIdList) {
        this.engEngrCertificateIdList = engEngrCertificateIdList;
    }

    public String getDocNo() {
        return docNo;
    }

    public void setDocNo(String docNo) {
        this.docNo = docNo;
    }

    public LocalDate getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(LocalDate closeDate) {
        this.closeDate = closeDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getExpectDate() {
        return expectDate;
    }

    public void setExpectDate(LocalDate expectDate) {
        this.expectDate = expectDate;
    }

    public Instant getRealTime() {
        return realTime;
    }

    public void setRealTime(Instant realTime) {
        this.realTime = realTime;
    }

    public String getRecordContent() {
        return recordContent;
    }

    public void setRecordContent(String recordContent) {
        this.recordContent = recordContent;
    }

    public String getRecordComment() {
        return recordComment;
    }

    public void setRecordComment(String recordComment) {
        this.recordComment = recordComment;
    }

    public Instant getSignDate() {
        return signDate;
    }

    public void setSignDate(Instant signDate) {
        this.signDate = signDate;
    }

    public int getNoSignItem() {
        return noSignItem;
    }

    public void setNoSignItem(int noSignItem) {
        this.noSignItem = noSignItem;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Instant getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getTransferFrom() {
        return transferFrom;
    }

    public void setTransferFrom(String transferFrom) {
        this.transferFrom = transferFrom;
    }

    public Instant getTransferTime() {
        return transferTime;
    }

    public void setTransferTime(Instant transferTime) {
        this.transferTime = transferTime;
    }

    public List<Comp> getCompList() {
        return compList;
    }

    public void setCompList(List<Comp> compList) {
        this.compList = compList;
    }

    public List<Engr> getEngrList() {
        return engrList;
    }

    public void setEngrList(List<Engr> engrList) {
        this.engrList = engrList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EngCaseTotalDTO that = (EngCaseTotalDTO) o;
        return compItem == that.compItem && engrItem == that.engrItem && noSignItem == that.noSignItem && Objects.equals(engCaseId, that.engCaseId) && Objects.equals(caseNo, that.caseNo) && Objects.equals(tenderNo, that.tenderNo) && Objects.equals(awardNo, that.awardNo) && Objects.equals(caseType, that.caseType) && Objects.equals(caseName, that.caseName) && Objects.equals(orgId, that.orgId) && Objects.equals(orgName, that.orgName) && Objects.equals(execOrgId, that.execOrgId) && Objects.equals(execOrgName, that.execOrgName) && Objects.equals(contactPerson, that.contactPerson) && Objects.equals(contactTel, that.contactTel) && Objects.equals(entrustItem, that.entrustItem) && Objects.equals(tenderDate, that.tenderDate) && Objects.equals(tenderDateStart, that.tenderDateStart) && Objects.equals(tenderDateEnd, that.tenderDateEnd) && Objects.equals(tenderPrice, that.tenderPrice) && Objects.equals(execDateStart, that.execDateStart) && Objects.equals(execDateEnd, that.execDateEnd) && Objects.equals(projectType, that.projectType) && Objects.equals(recordRangeList, that.recordRangeList) && Objects.equals(recordRangeOther, that.recordRangeOther) && Objects.equals(recordItemsList, that.recordItemsList) && Objects.equals(recordItemsOther, that.recordItemsOther) && Objects.equals(remark, that.remark) && Objects.equals(compId, that.compId) && Objects.equals(compIdno, that.compIdno) && Objects.equals(compName, that.compName) && Objects.equals(compLicenseNo, that.compLicenseNo) && Objects.equals(engrId, that.engrId) && Objects.equals(engrName, that.engrName) && Objects.equals(engrNameRome, that.engrNameRome) && Objects.equals(engEngrCertificateIdList, that.engEngrCertificateIdList) && Objects.equals(docNo, that.docNo) && Objects.equals(closeDate, that.closeDate) && Objects.equals(status, that.status) && Objects.equals(expectDate, that.expectDate) && Objects.equals(realTime, that.realTime) && Objects.equals(recordContent, that.recordContent) && Objects.equals(recordComment, that.recordComment) && Objects.equals(signDate, that.signDate) && Objects.equals(subject, that.subject) && Objects.equals(updateTime, that.updateTime) && Objects.equals(updateUser, that.updateUser) && Objects.equals(createTime, that.createTime) && Objects.equals(createUser, that.createUser) && Objects.equals(transferFrom, that.transferFrom) && Objects.equals(transferTime, that.transferTime) && Objects.equals(compList, that.compList) && Objects.equals(engrList, that.engrList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(engCaseId, caseNo, tenderNo, awardNo, caseType, caseName, orgId, orgName, execOrgId, execOrgName, contactPerson, contactTel, entrustItem, tenderDate, tenderDateStart, tenderDateEnd, tenderPrice, execDateStart, execDateEnd, projectType, recordRangeList, recordRangeOther, recordItemsList, recordItemsOther, remark, compItem, engrItem, compId, compIdno, compName, compLicenseNo, engrId, engrName, engrNameRome, engEngrCertificateIdList, docNo, closeDate, status, expectDate, realTime, recordContent, recordComment, signDate, noSignItem, subject, updateTime, updateUser, createTime, createUser, transferFrom, transferTime, compList, engrList);
    }

    @Override
    public String toString() {
        return "EngCaseTotalDTO{" +
                "engCaseId='" + engCaseId + '\'' +
                ", caseNo='" + caseNo + '\'' +
                ", tenderNo='" + tenderNo + '\'' +
                ", awardNo='" + awardNo + '\'' +
                ", caseType='" + caseType + '\'' +
                ", caseName='" + caseName + '\'' +
                ", orgId='" + orgId + '\'' +
                ", orgName='" + orgName + '\'' +
                ", execOrgId='" + execOrgId + '\'' +
                ", execOrgName='" + execOrgName + '\'' +
                ", contactPerson='" + contactPerson + '\'' +
                ", contactTel='" + contactTel + '\'' +
                ", entrustItem='" + entrustItem + '\'' +
                ", tenderDate=" + tenderDate +
                ", tenderDateStart=" + tenderDateStart +
                ", tenderDateEnd=" + tenderDateEnd +
                ", tenderPrice=" + tenderPrice +
                ", execDateStart=" + execDateStart +
                ", execDateEnd=" + execDateEnd +
                ", projectType='" + projectType + '\'' +
                ", recordRangeList='" + recordRangeList + '\'' +
                ", recordRangeOther='" + recordRangeOther + '\'' +
                ", recordItemsList='" + recordItemsList + '\'' +
                ", recordItemsOther='" + recordItemsOther + '\'' +
                ", remark='" + remark + '\'' +
                ", compItem=" + compItem +
                ", engrItem=" + engrItem +
                ", compId='" + compId + '\'' +
                ", compIdno='" + compIdno + '\'' +
                ", compName='" + compName + '\'' +
                ", compLicenseNo='" + compLicenseNo + '\'' +
                ", engrId='" + engrId + '\'' +
                ", engrName='" + engrName + '\'' +
                ", engrNameRome='" + engrNameRome + '\'' +
                ", engEngrCertificateIdList='" + engEngrCertificateIdList + '\'' +
                ", docNo='" + docNo + '\'' +
                ", closeDate=" + closeDate +
                ", status='" + status + '\'' +
                ", expectDate=" + expectDate +
                ", realTime=" + realTime +
                ", recordContent='" + recordContent + '\'' +
                ", recordComment='" + recordComment + '\'' +
                ", signDate=" + signDate +
                ", noSignItem=" + noSignItem +
                ", subject='" + subject + '\'' +
                ", updateTime=" + updateTime +
                ", updateUser='" + updateUser + '\'' +
                ", createTime=" + createTime +
                ", createUser='" + createUser + '\'' +
                ", transferFrom='" + transferFrom + '\'' +
                ", transferTime=" + transferTime +
                ", compList=" + compList +
                ", engrList=" + engrList +
                '}';
    }
}
