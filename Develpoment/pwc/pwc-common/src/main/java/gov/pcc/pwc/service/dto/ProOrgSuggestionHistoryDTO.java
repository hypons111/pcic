package gov.pcc.pwc.service.dto;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;


/*
 *  DTO For proOrgSuggestionHistory (pro0104r1)
 * */
public class ProOrgSuggestionHistoryDTO implements Serializable {

//    表格流水號
    private int id;

//    跳轉明細用
    private String proInnovativeProductId;

    //    創新產品名稱(JOIN 到 PRO_INNOVATIVE_PRODUCT(PRO_INNOVATIVE_PRODUCT_ID))
    @Size(max = 50)
    private String innovateName;

    //    廠商名稱(JOIN 到PRO_INNOVATIVE_PRODUCT(PRO_INNOVATIVE_PRODUCT_ID) 到ADM_ACCOUNT(USER_ID) 再到 ADM_COMPANY(COMP_IDNO))
    @Size(max = 60)
    private String companyName;

    //  廠商名稱 後端資料
    private String name;

    //    交流機關名稱(JOIN 到 PRO_INNOVATIVE_PRODUCT(PRO_INNOVATIVE_PRODUCT_ID))
    @Size(max = 1000)
    private String communicateOrganName;

    // 交流機關名稱 後端資料
    private String orgName;

    //    交流或通知不交流時間 起(搜尋條件)
    private Instant communicateDateStart;

    //    交流或通知不交流時間 迄(搜尋條件)
    private Instant communicateDateEnd;

    //    交流或通知不交流時間(table show)
    private Instant time;

    //    廠商登錄日期 起(搜尋條件)
    private Instant createTimeStart;

    //    廠商登錄日期 迄(搜尋條件)
    private Instant createTimeEnd;

    //    廠商登錄日期 (table show)
    private Instant createTime;

    //    交流狀態(條件判斷用，沒有欄位)
    @Size(max = 1)
    private String communicateStatus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCommunicateOrganName() {
        return communicateOrganName;
    }

    public void setCommunicateOrganName(String communicateOrganName) {
        this.communicateOrganName = communicateOrganName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Instant getCommunicateDateStart() {
        return communicateDateStart;
    }

    public void setCommunicateDateStart(Instant communicateDateStart) {
        this.communicateDateStart = communicateDateStart;
    }

    public Instant getCommunicateDateEnd() {
        return communicateDateEnd;
    }

    public void setCommunicateDateEnd(Instant communicateDateEnd) {
        this.communicateDateEnd = communicateDateEnd;
    }

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public Instant getCreateTimeStart() {
        return createTimeStart;
    }

    public void setCreateTimeStart(Instant createTimeStart) {
        this.createTimeStart = createTimeStart;
    }

    public Instant getCreateTimeEnd() {
        return createTimeEnd;
    }

    public void setCreateTimeEnd(Instant createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public String getCommunicateStatus() {
        return communicateStatus;
    }

    public void setCommunicateStatus(String communicateStatus) {
        this.communicateStatus = communicateStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.innovateName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProOrgSuggestionHistoryDTO)) {
            return false;
        }

        ProOrgSuggestionHistoryDTO proOrgSuggetionHistoryDTO = (ProOrgSuggestionHistoryDTO) o;
        if (this.innovateName == null) {
            return false;
        }

        return Objects.equals(this.innovateName, proOrgSuggetionHistoryDTO.innovateName);
    }

//    @Override
//    public String toString() {
//        return "ProOrgSuggestionHistoryDTO{" +
//                "id ='" + getId() + "'" +
//                "innovateName ='" + getInnovateName() + "'" +
//                ", companyName ='" + getCompanyName() + "'" +
//                ", communicateOrganName ='" + getCommunicateOrganName() + "'" +
//                ", communicateDateStart ='" + getCommunicateDateStart() + "'" +
//                ", communicateDateEnd ='" + getCommunicateDateEnd() + "'" +
//                ", time ='" + getTime() + "'" +
//                ", createTimeStart ='" + getCreateTimeStart() + "'" +
//                ", createTimeEnd ='" + getCreateTimeEnd() + "'" +
//                ", createTime ='" + getCreateTime() + "'" +
//                ", communicateStatus ='" + getCommunicateStatus() + "'"
//                + "}";
//    }
}
