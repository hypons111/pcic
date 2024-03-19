package gov.pcc.pwc.service.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

public class RecProjectOptionsDTO {

    private String typhoonNo;

    private String typhoonName;

    private Instant applyEndTime;

    private String projectCode;

    private String projectCodeName;

    private String applyOrgId;

    private String orgName;

    private String city;

    private String projectStatus;

    private String projectStatusName;

    private String constItemName;

    private String constModelName;

    private String constModelSource;

    private BigDecimal constModelPrice;

    private String constModelUnit;

    //==============================

    public String getTyphoonNo() {
        return typhoonNo;
    }

    public void setTyphoonNo(String typhoonNo) {
        this.typhoonNo = typhoonNo;
    }

    public String getTyphoonName() {
        return typhoonName;
    }

    public void setTyphoonName(String typhoonName) {
        this.typhoonName = typhoonName;
    }

    public Instant getApplyEndTime() {
        return applyEndTime;
    }

    public void setApplyEndTime(Instant applyEndTime) {
        this.applyEndTime = applyEndTime;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectCodeName() {
        return projectCodeName;
    }

    public void setProjectCodeName(String projectCodeName) {
        this.projectCodeName = projectCodeName;
    }

    public String getApplyOrgId() {
        return applyOrgId;
    }

    public void setApplyOrgId(String applyOrgId) {
        this.applyOrgId = applyOrgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus;
    }

    public String getProjectStatusName() {
        return projectStatusName;
    }

    public void setProjectStatusName(String projectStatusName) {
        this.projectStatusName = projectStatusName;
    }

    public String getConstItemName() {
        return constItemName;
    }

    public void setConstItemName(String constItemName) {
        this.constItemName = constItemName;
    }

    public String getConstModelName() {
        return constModelName;
    }

    public void setConstModelName(String constModelName) {
        this.constModelName = constModelName;
    }

    public String getConstModelSource() {
        return constModelSource;
    }

    public void setConstModelSource(String constModelSource) {
        this.constModelSource = constModelSource;
    }

    public BigDecimal getConstModelPrice() {
        return constModelPrice;
    }

    public void setConstModelPrice(BigDecimal constModelPrice) {
        this.constModelPrice = constModelPrice;
    }

    public String getConstModelUnit() {
        return constModelUnit;
    }

    public void setConstModelUnit(String constModelUnit) {
        this.constModelUnit = constModelUnit;
    }

    @Override
    public String toString() {
        return "RecProjectOptionsDTO{" +
//                "projectCode=" + getProjectCode() +
                "}";
    }
}
