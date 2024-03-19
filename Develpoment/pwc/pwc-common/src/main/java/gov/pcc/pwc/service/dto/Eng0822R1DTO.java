package gov.pcc.pwc.service.dto;

import com.microsoft.sqlserver.jdbc.Geography;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO
 */
    public class Eng0822R1DTO implements Serializable {

    @NotNull
    @Size(max = 50)
    private String engCompId;

    @NotNull
    @Size(max = 1)
    private String type;

    @Size(max = 20)
    private String pccOrgId;

    @Size(max = 50)
    private String chName;

    @Size(max = 100)
    private String enName;

    @Size(max = 20)
    private String compIdno;

    @Size(max = 20)
    private String engCstLicenseId;

    @Size(max = 20)
    private String licenseNo;

    private LocalDate establishDate;

    private BigDecimal capital;

    @Size(max = 100)
    private String subjectList;

    @Size(max = 50)
    private String masterName;

    @Size(max = 50)
    private String masterNameRome;

    // 先用布林
    private boolean isMasterEngr;

    @Size(max = 500)
    private String engrIdList;

    private boolean isUse;

    @Size(max = 80)
    private String remark;


    private Instant updateTime;

    @Size(max = 20)
    private String updateUser;


    private Instant createTime;

    @Size(max = 20)
    private String createUser;

    @Size(max = 100)
    private String transferFrom;


    private Instant transferTime;

    private int itemNo;

    @Size(max = 50)
    private String personName;

    @Size(max = 50)
    private String personNameRome;

    @Size(max = 10)
    private String title;

    @Size(max = 200)
    private String desc;

    @Size(max = 50)
    private String tel1;

    @Size(max = 20)
    private String tel2;

    @Size(max = 20)
    private String tel3;

    @Size(max = 20)
    private String fax;

    @Size(max = 50)
    private String mail1;

    @Size(max = 50)
    private String mail2;

    @Size(max = 10)
    private String address1Zip;

    @Size(max = 5)
    private String address1City;

    @Size(max = 3)
    private String address1Town;

    @Size(max = 100)
    private String address1Other;

    @Size(max = 10)
    private String address2Zip;

    @Size(max = 5)
    private String address2City;

    @Size(max = 3)
    private String address2Town;

    @Size(max = 100)
    private String address2Other;

    @Size(max = 10)
    private String address3Zip;

    @Size(max = 5)
    private String address3City;

    @Size(max = 3)
    private String address3Town;

    @Size(max = 100)
    private String address3Other;


    private boolean isDefault;


    private Float longitude;


    private Float latitude;


//    private Geography geographys;

    @Size(max = 20)
    private String engMemberId;

    @Size(max = 20)
    private String no;

    @Size(max = 20)
    private String idno;


    private LocalDate birthDate;


    private boolean sex;

    @Size(max = 10)
    private String permanentAddrZip;

    @Size(max = 5)
    private String permanentAddrCity;

    @Size(max = 3)
    private String permanentAddrTown;

    @Size(max = 100)
    private String permanentAddrOther;

    @Size(max = 10)
    private String contactAddrZip;

    @Size(max = 5)
    private String contactAddrCity;

    @Size(max = 100)
    private String contactAddrTown;

    @Size(max = 50)
    private String email1;

    @Size(max = 50)
    private String email2;


    private boolean isGovMember;


    private boolean isEngr;

    @Size(max = 1)
    private String identityType;

    @Size(max = 300)
    private String addrForeign;

    @Size(max = 20)
    private String userId;

    @Size(max = 100)
    private String jobTypeList;

    @Size(max = 20)
    private String licenseId;

    @Size(max = 20)
    private String compId;

    @Size(max = 20)
    private String execStatus;


    private BigDecimal engCstLicenseVersion;

    @Size(max = 20)
    private String engCstLicenseNo;

    @Size(max = 20)
    private String cstLicenseDocNo;


    private LocalDate receiveDate;

    @Size(max = 20)
    private String masterId;

    @Size(max = 100)
    private String masterNameEng;

    @Size(max = 20)
    private String masterIdno;


    private LocalDate masterBirthdate;


    private boolean masterSex;

    @Size(max = 20)
    private String masterMobileTel;

    @Size(max = 10)
    private String masterPermanentAddrZip;

    @Size(max = 5)
    private String masterPermanentAddrCity;

    @Size(max = 3)
    private String masterPermanentAddrTown;

    @Size(max = 100)
    private String masterPermanentAddrOther;

    @Size(max = 10)
    private String masterContactAddrZip;

    @Size(max = 5)
    private String masterContactAddrCity;

    @Size(max = 3)
    private String masterContactAddrTown;

    @Size(max = 100)
    private String masterContactAddrOther;

    @Size(max = 50)
    private String masterEmail;

    @Size(max = 1)
    private String masterIdentityType;

    @Size(max = 100)
    private String compName;

    @Size(max = 20)
    private String compTel1;

    @Size(max = 20)
    private String compTel2;

    @Size(max = 20)
    private String compFax;

    @Size(max = 10)
    private String compAddrZip;

    @Size(max = 5)
    private String compAddrCity;

    @Size(max = 3)
    private String compAddrTown;

    @Size(max = 100)
    private String compAddrOther;


    @Size(max = 20)
    private String applyItem;


    private boolean applyType;

    @Size(max = 20)
    private String receiveNo;

    @Size(max = 20)
    private String contactId;


    private LocalDate publicDate;

    @Size(max = 20)
    private String areaMasterOrg;

    @Size(max = 100)
    private String saleAreaList;

    @Size(max = 3)
    private String agreeLow1;

    @Size(max = 20)
    private String agreeLow2;

    @Size(max = 1)
    private String receiveType;

    @Size(max = 10)
    private String receiveAddrZip;

    @Size(max = 5)
    private String receiveAddrCity;

    @Size(max = 3)
    private String receiveAddrTown;

    @Size(max = 100)
    private String receiveAddrOther;

    @Size(max = 1)
    private String status;

    @Size(max = 20)
    private String reviewStaff;


    private Instant reviewTime;

    @Size(max = 2000)
    private String reviewDesc;

    @Size(max = 100)
    private String changeItems;

    @Size(max = 200)
    private String changeItemsDesc;


    private Instant transferDate;


    private boolean masterIsEngr;


    public String getEngCompId() {
        return engCompId;
    }

    public void setEngCompId(String engCompId) {
        this.engCompId = engCompId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPccOrgId() {
        return pccOrgId;
    }

    public void setPccOrgId(String pccOrgId) {
        this.pccOrgId = pccOrgId;
    }

    public String getChName() {
        return chName;
    }

    public void setChName(String chName) {
        this.chName = chName;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getCompIdno() {
        return compIdno;
    }

    public void setCompIdno(String compIdno) {
        this.compIdno = compIdno;
    }

    public String getEngCstLicenseId() {
        return engCstLicenseId;
    }

    public void setEngCstLicenseId(String engCstLicenseId) {
        this.engCstLicenseId = engCstLicenseId;
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    public LocalDate getEstablishDate() {
        return establishDate;
    }

    public void setEstablishDate(LocalDate establishDate) {
        this.establishDate = establishDate;
    }

    public BigDecimal getCapital() {
        return capital;
    }

    public void setCapital(BigDecimal capital) {
        this.capital = capital;
    }

    public String getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(String subjectList) {
        this.subjectList = subjectList;
    }

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public String getMasterNameRome() {
        return masterNameRome;
    }

    public void setMasterNameRome(String masterNameRome) {
        this.masterNameRome = masterNameRome;
    }

    public boolean getIsMasterEngr() {
        return isMasterEngr;
    }

    public void setIsMasterEngr(boolean masterEngr) {
        isMasterEngr = masterEngr;
    }

    public String getEngrIdList() {
        return engrIdList;
    }

    public void setEngrIdList(String engrIdList) {
        this.engrIdList = engrIdList;
    }

    public boolean getIsUse() {
        return isUse;
    }

    public void setIsUse(boolean use) {
        isUse = use;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public int getItemNo() {
        return itemNo;
    }

    public void setItemNo(int itemNo) {
        this.itemNo = itemNo;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonNameRome() {
        return personNameRome;
    }

    public void setPersonNameRome(String personNameRome) {
        this.personNameRome = personNameRome;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTel1() {
        return tel1;
    }

    public void setTel1(String tel1) {
        this.tel1 = tel1;
    }

    public String getTel2() {
        return tel2;
    }

    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }

    public String getTel3() {
        return tel3;
    }

    public void setTel3(String tel3) {
        this.tel3 = tel3;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getMail1() {
        return mail1;
    }

    public void setMail1(String mail1) {
        this.mail1 = mail1;
    }

    public String getMail2() {
        return mail2;
    }

    public void setMail2(String mail2) {
        this.mail2 = mail2;
    }

    public String getAddress1Zip() {
        return address1Zip;
    }

    public void setAddress1Zip(String address1Zip) {
        this.address1Zip = address1Zip;
    }

    public String getAddress2Zip() {
        return address2Zip;
    }

    public void setAddress2Zip(String address2Zip) {
        this.address2Zip = address2Zip;
    }

    public String getAddress3Zip() {
        return address3Zip;
    }

    public void setAddress3Zip(String address3Zip) {
        this.address3Zip = address3Zip;
    }

    public String getAddress1City() {
        return address1City;
    }

    public void setAddress1City(String address1City) {
        this.address1City = address1City;
    }

    public String getAddress1Town() {
        return address1Town;
    }

    public void setAddress1Town(String address1Town) {
        this.address1Town = address1Town;
    }

    public String getAddress1Other() {
        return address1Other;
    }

    public void setAddress1Other(String address1Other) {
        this.address1Other = address1Other;
    }

    public String getAddress2City() {
        return address2City;
    }

    public void setAddress2City(String address2City) {
        this.address2City = address2City;
    }

    public String getAddress2Town() {
        return address2Town;
    }

    public void setAddress2Town(String address2Town) {
        this.address2Town = address2Town;
    }

    public String getAddress2Other() {
        return address2Other;
    }

    public void setAddress2Other(String address2Other) {
        this.address2Other = address2Other;
    }

    public String getAddress3City() {
        return address3City;
    }

    public void setAddress3City(String address3City) {
        this.address3City = address3City;
    }

    public String getAddress3Town() {
        return address3Town;
    }

    public void setAddress3Town(String address3Town) {
        this.address3Town = address3Town;
    }

    public String getAddress3Other() {
        return address3Other;
    }

    public void setAddress3Other(String address3Other) {
        this.address3Other = address3Other;
    }

    public boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

//    public Geography getGeographys() {
//        return geographys;
//    }

//    public void setGeographys(Geography geographys) {
//        this.geographys = geographys;
//    }

    public String getEngMemberId() {
        return engMemberId;
    }

    public void setEngMemberId(String engMemberId) {
        this.engMemberId = engMemberId;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public boolean getSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getPermanentAddrZip() {
        return permanentAddrZip;
    }

    public void setPermanentAddrZip(String permanentAddrZip) {
        this.permanentAddrZip = permanentAddrZip;
    }

    public String getPermanentAddrCity() {
        return permanentAddrCity;
    }

    public void setPermanentAddrCity(String permanentAddrCity) {
        this.permanentAddrCity = permanentAddrCity;
    }

    public String getPermanentAddrTown() {
        return permanentAddrTown;
    }

    public void setPermanentAddrTown(String permanentAddrTown) {
        this.permanentAddrTown = permanentAddrTown;
    }

    public String getPermanentAddrOther() {
        return permanentAddrOther;
    }

    public void setPermanentAddrOther(String permanentAddrOther) {
        this.permanentAddrOther = permanentAddrOther;
    }

    public String getContactAddrZip() {
        return contactAddrZip;
    }

    public void setContactAddrZip(String contactAddrZip) {
        this.contactAddrZip = contactAddrZip;
    }

    public String getContactAddrCity() {
        return contactAddrCity;
    }

    public void setContactAddrCity(String contactAddrCity) {
        this.contactAddrCity = contactAddrCity;
    }

    public String getContactAddrTown() {
        return contactAddrTown;
    }

    public void setContactAddrTown(String contactAddrTown) {
        this.contactAddrTown = contactAddrTown;
    }

    public String getEmail1() {
        return email1;
    }

    public void setEmail1(String email1) {
        this.email1 = email1;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public boolean getIsGovMember() {
        return isGovMember;
    }

    public void setIsGovMember(boolean govMember) {
        isGovMember = govMember;
    }

    public boolean getIsEngr() {
        return isEngr;
    }

    public void setIsEngr(boolean engr) {
        isEngr = engr;
    }

    public String getIdentityType() {
        return identityType;
    }

    public void setIdentityType(String identityType) {
        this.identityType = identityType;
    }

    public String getAddrForeign() {
        return addrForeign;
    }

    public void setAddrForeign(String addrForeign) {
        this.addrForeign = addrForeign;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getJobTypeList() {
        return jobTypeList;
    }

    public void setJobTypeList(String jobTypeList) {
        this.jobTypeList = jobTypeList;
    }

    public String getLicenseId() {
        return licenseId;
    }

    public void setLicenseId(String licenseId) {
        this.licenseId = licenseId;
    }

    public String getCompId() {
        return compId;
    }

    public void setCompId(String compId) {
        this.compId = compId;
    }

    public String getExecStatus() {
        return execStatus;
    }

    public void setExecStatus(String execStatus) {
        this.execStatus = execStatus;
    }

    public BigDecimal getEngCstLicenseVersion() {
        return engCstLicenseVersion;
    }

    public void setEngCstLicenseVersion(BigDecimal engCstLicenseVersion) {
        this.engCstLicenseVersion = engCstLicenseVersion;
    }

    public String getEngCstLicenseNo() {
        return engCstLicenseNo;
    }

    public void setEngCstLicenseNo(String engCstLicenseNo) {
        this.engCstLicenseNo = engCstLicenseNo;
    }

    public String getCstLicenseDocNo() {
        return cstLicenseDocNo;
    }

    public void setCstLicenseDocNo(String cstLicenseDocNo) {
        this.cstLicenseDocNo = cstLicenseDocNo;
    }

    public LocalDate getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(LocalDate receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getMasterId() {
        return masterId;
    }

    public void setMasterId(String masterId) {
        this.masterId = masterId;
    }

    public String getMasterNameEng() {
        return masterNameEng;
    }

    public void setMasterNameEng(String masterNameEng) {
        this.masterNameEng = masterNameEng;
    }

    public String getMasterIdno() {
        return masterIdno;
    }

    public void setMasterIdno(String masterIdno) {
        this.masterIdno = masterIdno;
    }

    public LocalDate getMasterBirthdate() {
        return masterBirthdate;
    }

    public void setMasterBirthdate(LocalDate masterBirthdate) {
        this.masterBirthdate = masterBirthdate;
    }

    public boolean getMasterSex() {
        return masterSex;
    }

    public void setMasterSex(boolean masterSex) {
        this.masterSex = masterSex;
    }

    public String getMasterMobileTel() {
        return masterMobileTel;
    }

    public void setMasterMobileTel(String masterMobileTel) {
        this.masterMobileTel = masterMobileTel;
    }

    public String getMasterPermanentAddrZip() {
        return masterPermanentAddrZip;
    }

    public void setMasterPermanentAddrZip(String masterPermanentAddrZip) {
        this.masterPermanentAddrZip = masterPermanentAddrZip;
    }

    public String getMasterPermanentAddrCity() {
        return masterPermanentAddrCity;
    }

    public void setMasterPermanentAddrCity(String masterPermanentAddrCity) {
        this.masterPermanentAddrCity = masterPermanentAddrCity;
    }

    public String getMasterPermanentAddrTown() {
        return masterPermanentAddrTown;
    }

    public void setMasterPermanentAddrTown(String masterPermanentAddrTown) {
        this.masterPermanentAddrTown = masterPermanentAddrTown;
    }

    public String getMasterPermanentAddrOther() {
        return masterPermanentAddrOther;
    }

    public void setMasterPermanentAddrOther(String masterPermanentAddrOther) {
        this.masterPermanentAddrOther = masterPermanentAddrOther;
    }

    public String getMasterContactAddrZip() {
        return masterContactAddrZip;
    }

    public void setMasterContactAddrZip(String masterContactAddrZip) {
        this.masterContactAddrZip = masterContactAddrZip;
    }

    public String getMasterContactAddrCity() {
        return masterContactAddrCity;
    }

    public void setMasterContactAddrCity(String masterContactAddrCity) {
        this.masterContactAddrCity = masterContactAddrCity;
    }

    public String getMasterContactAddrTown() {
        return masterContactAddrTown;
    }

    public void setMasterContactAddrTown(String masterContactAddrTown) {
        this.masterContactAddrTown = masterContactAddrTown;
    }

    public String getMasterContactAddrOther() {
        return masterContactAddrOther;
    }

    public void setMasterContactAddrOther(String masterContactAddrOther) {
        this.masterContactAddrOther = masterContactAddrOther;
    }

    public String getMasterEmail() {
        return masterEmail;
    }

    public void setMasterEmail(String masterEmail) {
        this.masterEmail = masterEmail;
    }

    public String getMasterIdentityType() {
        return masterIdentityType;
    }

    public void setMasterIdentityType(String masterIdentityType) {
        this.masterIdentityType = masterIdentityType;
    }

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public String getCompTel1() {
        return compTel1;
    }

    public void setCompTel1(String compTel1) {
        this.compTel1 = compTel1;
    }

    public String getCompTel2() {
        return compTel2;
    }

    public void setCompTel2(String compTel2) {
        this.compTel2 = compTel2;
    }

    public String getCompFax() {
        return compFax;
    }

    public void setCompFax(String compFax) {
        this.compFax = compFax;
    }

    public String getCompAddrZip() {
        return compAddrZip;
    }

    public void setCompAddrZip(String compAddrZip) {
        this.compAddrZip = compAddrZip;
    }

    public String getCompAddrCity() {
        return compAddrCity;
    }

    public void setCompAddrCity(String compAddrCity) {
        this.compAddrCity = compAddrCity;
    }

    public String getCompAddrTown() {
        return compAddrTown;
    }

    public void setCompAddrTown(String compAddrTown) {
        this.compAddrTown = compAddrTown;
    }

    public String getCompAddrOther() {
        return compAddrOther;
    }

    public void setCompAddrOther(String compAddrOther) {
        this.compAddrOther = compAddrOther;
    }

    public String getApplyItem() {
        return applyItem;
    }

    public void setApplyItem(String applyItem) {
        this.applyItem = applyItem;
    }

    public boolean getApplyType() {
        return applyType;
    }

    public void setApplyType(boolean applyType) {
        this.applyType = applyType;
    }

    public String getReceiveNo() {
        return receiveNo;
    }

    public void setReceiveNo(String receiveNo) {
        this.receiveNo = receiveNo;
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public LocalDate getPublicDate() {
        return publicDate;
    }

    public void setPublicDate(LocalDate publicDate) {
        this.publicDate = publicDate;
    }

    public String getAreaMasterOrg() {
        return areaMasterOrg;
    }

    public void setAreaMasterOrg(String areaMasterOrg) {
        this.areaMasterOrg = areaMasterOrg;
    }

    public String getSaleAreaList() {
        return saleAreaList;
    }

    public void setSaleAreaList(String saleAreaList) {
        this.saleAreaList = saleAreaList;
    }

    public String getAgreeLow1() {
        return agreeLow1;
    }

    public void setAgreeLow1(String agreeLow1) {
        this.agreeLow1 = agreeLow1;
    }

    public String getAgreeLow2() {
        return agreeLow2;
    }

    public void setAgreeLow2(String agreeLow2) {
        this.agreeLow2 = agreeLow2;
    }

    public String getReceiveType() {
        return receiveType;
    }

    public void setReceiveType(String receiveType) {
        this.receiveType = receiveType;
    }

    public String getReceiveAddrZip() {
        return receiveAddrZip;
    }

    public void setReceiveAddrZip(String receiveAddrZip) {
        this.receiveAddrZip = receiveAddrZip;
    }

    public String getReceiveAddrCity() {
        return receiveAddrCity;
    }

    public void setReceiveAddrCity(String receiveAddrCity) {
        this.receiveAddrCity = receiveAddrCity;
    }

    public String getReceiveAddrTown() {
        return receiveAddrTown;
    }

    public void setReceiveAddrTown(String receiveAddrTown) {
        this.receiveAddrTown = receiveAddrTown;
    }

    public String getReceiveAddrOther() {
        return receiveAddrOther;
    }

    public void setReceiveAddrOther(String receiveAddrOther) {
        this.receiveAddrOther = receiveAddrOther;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReviewStaff() {
        return reviewStaff;
    }

    public void setReviewStaff(String reviewStaff) {
        this.reviewStaff = reviewStaff;
    }

    public Instant getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(Instant reviewTime) {
        this.reviewTime = reviewTime;
    }

    public String getReviewDesc() {
        return reviewDesc;
    }

    public void setReviewDesc(String reviewDesc) {
        this.reviewDesc = reviewDesc;
    }

    public String getChangeItems() {
        return changeItems;
    }

    public void setChangeItems(String changeItems) {
        this.changeItems = changeItems;
    }

    public String getChangeItemsDesc() {
        return changeItemsDesc;
    }

    public void setChangeItemsDesc(String changeItemsDesc) {
        this.changeItemsDesc = changeItemsDesc;
    }

    public Instant getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(Instant transferDate) {
        this.transferDate = transferDate;
    }

    public boolean getMasterIsEngr() {
        return masterIsEngr;
    }

    public void setMasterIsEngr(boolean masterIsEngr) {
        this.masterIsEngr = masterIsEngr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Eng0822R1DTO)) {
            return false;
        }
        Eng0822R1DTO eNG0822R1DTO = (Eng0822R1DTO) o;
        if (this.engCompId == null) {
            return false;
        }
        return Objects.equals(this.engCompId, eNG0822R1DTO.engCompId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.engCompId);
    }

    @Override
    public String toString() {
        return "Eng0822R1DTO{" +
                "engCompId='" + engCompId + '\'' +
                ", type='" + type + '\'' +
                ", pccOrgId='" + pccOrgId + '\'' +
                ", chName='" + chName + '\'' +
                ", enName='" + enName + '\'' +
                ", compIdno='" + compIdno + '\'' +
                ", engCstLicenseId='" + engCstLicenseId + '\'' +
                ", licenseNo='" + licenseNo + '\'' +
                ", establishDate=" + establishDate +
                ", capital=" + capital +
                ", subjectList='" + subjectList + '\'' +
                ", masterName='" + masterName + '\'' +
                ", masterNameRome='" + masterNameRome + '\'' +
                ", isMasterEngr=" + isMasterEngr +
                ", engrIdList='" + engrIdList + '\'' +
                ", isUse=" + isUse +
                ", remark='" + remark + '\'' +
                ", updateTime=" + updateTime +
                ", updateUser='" + updateUser + '\'' +
                ", createTime=" + createTime +
                ", createUser='" + createUser + '\'' +
                ", transferFrom='" + transferFrom + '\'' +
                ", transferTime=" + transferTime +
                ", itemNo=" + itemNo +
                ", personName='" + personName + '\'' +
                ", personNameRome='" + personNameRome + '\'' +
                ", title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", tel1='" + tel1 + '\'' +
                ", tel2='" + tel2 + '\'' +
                ", tel3='" + tel3 + '\'' +
                ", fax='" + fax + '\'' +
                ", mail1='" + mail1 + '\'' +
                ", mail2='" + mail2 + '\'' +
                ", address1Zip='" + address1Zip + '\'' +
                ", address1City='" + address1City + '\'' +
                ", address1Town='" + address1Town + '\'' +
                ", address1Other='" + address1Other + '\'' +
                ", address2Zip='" + address2Zip + '\'' +
                ", address2City='" + address2City + '\'' +
                ", address2Town='" + address2Town + '\'' +
                ", address2Other='" + address2Other + '\'' +
                ", address3Zip='" + address3Zip + '\'' +
                ", address3City='" + address3City + '\'' +
                ", address3Town='" + address3Town + '\'' +
                ", address3Other='" + address3Other + '\'' +
                ", isDefault=" + isDefault +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
//                ", geographys=" + geographys +
                ", engMemberId='" + engMemberId + '\'' +
                ", no='" + no + '\'' +
                ", idno='" + idno + '\'' +
                ", birthDate=" + birthDate +
                ", sex=" + sex +
                ", permanentAddrZip='" + permanentAddrZip + '\'' +
                ", permanentAddrCity='" + permanentAddrCity + '\'' +
                ", permanentAddrTown='" + permanentAddrTown + '\'' +
                ", permanentAddrOther='" + permanentAddrOther + '\'' +
                ", contactAddrZip='" + contactAddrZip + '\'' +
                ", contactAddrCity='" + contactAddrCity + '\'' +
                ", contactAddrTown='" + contactAddrTown + '\'' +
                ", email1='" + email1 + '\'' +
                ", email2='" + email2 + '\'' +
                ", isGovMember=" + isGovMember +
                ", isEngr=" + isEngr +
                ", identityType='" + identityType + '\'' +
                ", addrForeign='" + addrForeign + '\'' +
                ", userId='" + userId + '\'' +
                ", jobTypeList='" + jobTypeList + '\'' +
                ", licenseId='" + licenseId + '\'' +
                ", compId='" + compId + '\'' +
                ", execStatus='" + execStatus + '\'' +
                ", engCstLicenseVersion=" + engCstLicenseVersion +
                ", engCstLicenseNo='" + engCstLicenseNo + '\'' +
                ", cstLicenseDocNo='" + cstLicenseDocNo + '\'' +
                ", receiveDate=" + receiveDate +
                ", masterId='" + masterId + '\'' +
                ", masterNameEng='" + masterNameEng + '\'' +
                ", masterIdno='" + masterIdno + '\'' +
                ", masterBirthdate=" + masterBirthdate +
                ", masterSex=" + masterSex +
                ", masterMobileTel='" + masterMobileTel + '\'' +
                ", masterPermanentAddrZip='" + masterPermanentAddrZip + '\'' +
                ", masterPermanentAddrCity='" + masterPermanentAddrCity + '\'' +
                ", masterPermanentAddrTown='" + masterPermanentAddrTown + '\'' +
                ", masterPermanentAddrOther='" + masterPermanentAddrOther + '\'' +
                ", masterContactAddrZip='" + masterContactAddrZip + '\'' +
                ", masterContactAddrCity='" + masterContactAddrCity + '\'' +
                ", masterContactAddrTown='" + masterContactAddrTown + '\'' +
                ", masterContactAddrOther='" + masterContactAddrOther + '\'' +
                ", masterEmail='" + masterEmail + '\'' +
                ", masterIdentityType='" + masterIdentityType + '\'' +
                ", compName='" + compName + '\'' +
                ", compTel1='" + compTel1 + '\'' +
                ", compTel2='" + compTel2 + '\'' +
                ", compFax='" + compFax + '\'' +
                ", compAddrZip='" + compAddrZip + '\'' +
                ", compAddrCity='" + compAddrCity + '\'' +
                ", compAddrTown='" + compAddrTown + '\'' +
                ", compAddrOther='" + compAddrOther + '\'' +
                ", applyItem='" + applyItem + '\'' +
                ", applyType=" + applyType +
                ", receiveNo='" + receiveNo + '\'' +
                ", contactId='" + contactId + '\'' +
                ", publicDate=" + publicDate +
                ", areaMasterOrg='" + areaMasterOrg + '\'' +
                ", saleAreaList='" + saleAreaList + '\'' +
                ", agreeLow1='" + agreeLow1 + '\'' +
                ", agreeLow2='" + agreeLow2 + '\'' +
                ", receiveType='" + receiveType + '\'' +
                ", receiveAddrZip='" + receiveAddrZip + '\'' +
                ", receiveAddrCity='" + receiveAddrCity + '\'' +
                ", receiveAddrTown='" + receiveAddrTown + '\'' +
                ", receiveAddrOther='" + receiveAddrOther + '\'' +
                ", status='" + status + '\'' +
                ", reviewStaff='" + reviewStaff + '\'' +
                ", reviewTime=" + reviewTime +
                ", reviewDesc='" + reviewDesc + '\'' +
                ", changeItems='" + changeItems + '\'' +
                ", changeItemsDesc='" + changeItemsDesc + '\'' +
                ", transferDate=" + transferDate +
                ", masterIsEngr=" + masterIsEngr +
                '}';
    }
}
