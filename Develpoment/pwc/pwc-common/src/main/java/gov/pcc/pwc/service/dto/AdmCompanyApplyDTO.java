package gov.pcc.pwc.service.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

public class AdmCompanyApplyDTO {

    private String agencyCardType;

    @Size(max = 20)
    private String updateUser;

    private Instant updateTime;

    /* ADM_COMPANY */
    @Size(max = 10)
    private String compIdno;

    @Size(max = 60)
    private String rpName;

    @Size(max = 60)
    private String name;

    @Size(max = 10)
    private String addressZip;

    @Size(max = 10)
    private String addressCity;

    @Size(max = 10)
    private String addressTown;

    @Size(max = 10)
    private String addressRoad;

    private BigDecimal amount;

    private Instant conDate;


    /* ADM_CONTACT */
    @Size(max = 50)
    private String chName;

    @Size(max = 10)
    private String idno;

    @Size(max = 1)
    private String sex;

    private Instant birthday;

    @Size(max = 10)
    private String workAddrZip;

    @Size(max = 10)
    private String workAddrCity;

    @Size(max = 10)
    private String workAddrTown;

    @Size(max = 100)
    private String workAddrOther;

    @Size(max = 20)
    private String workTel;

    @Size(max = 15)
    private String mobileTel;

    @Size(max = 100)
    private String email;

    /* ADM_COMPANY */
    @Size(max = 20)
    private String userId;

    @Size(max = 20)
    private String status;

    public String getAgencyCardType() {return agencyCardType;}
    public void setAgencyCardType(String agencyCardType) {this.agencyCardType = agencyCardType;}

    public String getUpdateUser() {return updateUser;}
    public void setUpdateUser(String updateUser) {this.updateUser = updateUser;}

    public Instant getUpdateTime() {return updateTime;}
    public void setUpdateTime(Instant updateTime) {this.updateTime = updateTime;}

    public String getCompIdno() {return compIdno;}
    public void setCompIdno(String compIdno) {this.compIdno = compIdno;}

    public String getRpName() {return rpName;}
    public void setRpName(String rpName) {this.rpName = rpName;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getAddressZip() {return addressZip;}
    public void setAddressZip(String addressZip) {this.addressZip = addressZip;}

    public String getAddressCity() {return addressCity;}
    public void setAddressCity(String addressCity) {this.addressCity = addressCity;}

    public String getAddressTown() {return addressTown;}
    public void setAddressTown(String addressTown) {this.addressTown = addressTown;}

    public String getAddressRoad() {return addressRoad;}
    public void setAddressRoad(String addressRoad) {this.addressRoad = addressRoad;}

    public BigDecimal getAmount() {return amount;}
    public void setAmount(BigDecimal amount) {this.amount = amount;}

    public Instant getConDate() {return conDate;}
    public void setConDate(Instant conDate) {this.conDate = conDate;}

    public String getChName() {return chName;}
    public void setChName(String chName) {this.chName = chName;}

    public String getIdno() {return idno;}
    public void setIdno(String idno) {this.idno = idno;}

    public String getSex() {return sex;}
    public void setSex(String sex) {this.sex = sex;}

    public Instant getBirthday() {return birthday;}
    public void setBirthday(Instant birthday) {this.birthday = birthday;}

    public String getWorkAddrZip() {return workAddrZip;}
    public void setWorkAddrZip(String workAddrZip) {this.workAddrZip = workAddrZip;}

    public String getWorkAddrCity() {return workAddrCity;}
    public void setWorkAddrCity(String workAddrCity) {this.workAddrCity = workAddrCity;}

    public String getWorkAddrTown() {return workAddrTown;}
    public void setWorkAddrTown(String workAddrTown) {this.workAddrTown = workAddrTown;}

    public String getWorkAddrOther() {return workAddrOther;}
    public void setWorkAddrOther(String workAddrOther) {this.workAddrOther = workAddrOther;}

    public String getWorkTel() {return workTel;}
    public void setWorkTel(String workTel) {this.workTel = workTel;}

    public String getMobileTel() {return mobileTel;}
    public void setMobileTel(String mobileTel) {this.mobileTel = mobileTel;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public String getUserId() {return userId;}
    public void setUserId(String userId) {this.userId = userId;}

    public String getStatus() {return status;}
    public void setStatus(String status) {this.status = status;}

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AdmCompanyApplyDTO)) {
            return false;
        }

        AdmCompanyApplyDTO admCompanyApplyDTO = (AdmCompanyApplyDTO) o;
        if (this.compIdno == null) {
            return false;
        }
        return Objects.equals(this.compIdno, admCompanyApplyDTO.compIdno);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.compIdno);
    }

    @Override
    public String toString() {
        return "AdmCompanyApplyDTO{" +
                "compIdno='" + compIdno + '\'' +
                ", agencyCardType='" + agencyCardType + '\'' +
                ", updateUser='" + updateUser + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", rpName='" + rpName + '\'' +
                ", name='" + name + '\'' +
                ", addressZip='" + addressZip + '\'' +
                ", addressCity='" + addressCity + '\'' +
                ", addressTown=" + addressTown +
                ", addressRoad='" + addressRoad + '\'' +
                ", amount='" + amount + '\'' +
                ", conDate='" + conDate + '\'' +
                ", chName='" + chName + '\'' +
                ", idno=" + idno +
                ", sex=" + sex +
                ", birthday='" + birthday + '\'' +
                ", workAddrZip='" + workAddrZip + '\'' +
                ", workAddrCity=" + workAddrCity +
                ", workAddrTown='" + workAddrTown + '\'' +
                ", workAddrOther='" + workAddrOther + '\'' +
                ", workTel='" + workTel + '\'' +
                ", mobileTel='" + mobileTel + '\'' +
                ", email='" + email + '\'' +
                ", userId='" + userId + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
