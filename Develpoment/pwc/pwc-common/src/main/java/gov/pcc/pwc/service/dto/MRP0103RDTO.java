package gov.pcc.pwc.service.dto;

import java.io.Serializable;
import java.time.Instant;

public class MRP0103RDTO implements Serializable {

    private Instant createDate;

    private String accountId;

    private String type;

    private String action;

    private String ip;

    private String searchConditions;

    private String userCnt;

    private String keywordCnt;

    private String numCnt;

    private String samNumCnt;

    private String dowNumCnt;

    public Instant getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getSearchConditions() {
        return searchConditions;
    }

    public void setSearchConditions(String searchConditions) {
        this.searchConditions = searchConditions;
    }

    public String getUserCnt() {
        return userCnt;
    }

    public void setUserCnt(String userCnt) {
        this.userCnt = userCnt;
    }

    public String getKeywordCnt() {
        return keywordCnt;
    }

    public void setKeywordCnt(String keywordCnt) {
        this.keywordCnt = keywordCnt;
    }

    public String getNumCnt() {
        return numCnt;
    }

    public void setNumCnt(String numCnt) {
        this.numCnt = numCnt;
    }

    public String getSamNumCnt() {
        return samNumCnt;
    }

    public void setSamNumCnt(String samNumCnt) {
        this.samNumCnt = samNumCnt;
    }

    public String getDowNumCnt() {
        return dowNumCnt;
    }

    public void setDowNumCnt(String dowNumCnt) {
        this.dowNumCnt = dowNumCnt;
    }

    @Override
    public String toString() {
        return "MRP0103RDTO{" +
                "createDate='" + createDate + '\'' +
                ", accountId='" + accountId + '\'' +
                ", type='" + type + '\'' +
                ", action='" + action + '\'' +
                ", ip='" + ip + '\'' +
                ", searchConditions='" + searchConditions + '\'' +
                ", userCnt='" + userCnt + '\'' +
                ", keywordCnt='" + keywordCnt + '\'' +
                ", numCnt='" + numCnt + '\'' +
                ", samNumCnt='" + samNumCnt + '\'' +
                ", dowNumCnt='" + dowNumCnt + '\'' +
                '}';
    }
}
