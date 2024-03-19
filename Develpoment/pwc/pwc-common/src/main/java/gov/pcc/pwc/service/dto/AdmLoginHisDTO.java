package gov.pcc.pwc.service.dto;

import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.Objects;

public class AdmLoginHisDTO {

    private Long id;

    private String userId;

    private String chName;

    private String chNameRome;

    @Size(max = 10)
    private String sysCategory;

    private String jwtToken;

    @Size(max = 1)
    private String result;

    @Size(max = 100)
    private String descript;

    @Size(max = 100)
    private String sourceIp;

    @Size(max = 100)
    private String browser;

    private Instant createTime;

    @Size(max = 20)
    private String createUserSys;

    private int rowNum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getChName() {
        return chName;
    }

    public void setChName(String chName) {
        this.chName = chName;
    }

    public String getChNameRome() {
        return chNameRome;
    }

    public void setChNameRome(String chNameRome) {
        this.chNameRome = chNameRome;
    }

    public String getSysCategory() {
        return sysCategory;
    }

    public void setSysCategory(String sysCategory) {
        this.sysCategory = sysCategory;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public String getSourceIp() {
        return sourceIp;
    }

    public void setSourceIp(String sourceIp) {
        this.sourceIp = sourceIp;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public String getCreateUserSys() {
        return createUserSys;
    }

    public void setCreateUserSys(String createUserSys) {
        this.createUserSys = createUserSys;
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdmLoginHisDTO that = (AdmLoginHisDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "AdmLoginHisDTO{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", chName='" + chName + '\'' +
                ", chNameRome='" + chNameRome + '\'' +
                ", sysCategory='" + sysCategory + '\'' +
                ", jwtToken='" + jwtToken + '\'' +
                ", result='" + result + '\'' +
                ", descript='" + descript + '\'' +
                ", sourceIp='" + sourceIp + '\'' +
                ", browser='" + browser + '\'' +
                ", createTime=" + createTime +
                ", createUserSys='" + createUserSys + '\'' +
                ", rowNum=" + rowNum +
                '}';
    }
}
