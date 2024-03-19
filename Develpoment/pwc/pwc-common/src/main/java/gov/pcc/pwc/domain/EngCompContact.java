package gov.pcc.pwc.domain;

//import com.microsoft.sqlserver.jdbc.Geography;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;

/**
 * A EngCompContact by Tim.
 */
@Entity
@Table(name = "eng_comp_contact")
@IdClass(EngCompContactPK.class)
public class EngCompContact implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @Column(name = "eng_comp_id", length = 20)
    private String engCompId;

    @NotNull
    @Id
    @Column(name = "item_no", length = 4)
    private int itemNo;

    @Column(name = "person_name", length = 50)
    private String personName;

    @Column(name = "person_name_rome", length = 50)
    private String personNameRome;

    @Column(name = "title", length = 10)
    private String title;

    @Column(name = "desc", length = 200)
    private String desc;

    @Column(name = "tel1", length = 50)
    private String tel1;

    @Column(name = "tel2", length = 20)
    private String tel2;

    @Column(name = "tel3", length = 20)
    private String tel3;

    @Column(name = "fax", length = 20)
    private String fax;

    @Column(name = "mail1", length = 50)
    private String mail1;

    @Column(name = "mail2", length = 50)
    private String mail2;

    @Column(name = "address1_zip", length = 10)
    private String address1Zip;

    @Column(name = "address1_city", length = 5)
    private String address1City;

    @Column(name = "address1_town", length = 3)
    private String address1Town;

    @Column(name = "address1_other", length = 100)
    private String address1Other;

    @Column(name = "address2_zip", length = 10)
    private String address2Zip;

    @Column(name = "address2_city", length = 5)
    private String address2City;

    @Column(name = "address2_town", length = 3)
    private String address2Town;

    @Column(name = "address2_other", length = 100)
    private String address2Other;

    @Column(name = "address3_zip", length = 10)
    private String address3Zip;

    @Column(name = "address3_city", length = 5)
    private String address3City;

    @Column(name = "address3_town", length = 3)
    private String address3Town;

    @Column(name = "address3_other", length = 100)
    private String address3Other;

    @Column(name = "is_default")
    private boolean isDefault;

    @Column(name = "is_use")
    private boolean isUse;

    @Column(name = "remark", length = 80)
    private String remark;

    @Column(name = "longitude", length = 8)
    private float longitude;

    @Column(name = "latitude", length = 8)
    private float latitude;

//    @Column(name = "geographys")
//    private Geography geographys;

    @NotNull
    @Column(name = "update_time")
    private Instant updateTime;

    @NotNull
    @Column(name = "update_user", length = 20)
    private String updateUser;

    @NotNull
    @Column(name = "create_time")
    private Instant createTime;

    @NotNull
    @Column(name = "create_user", length = 20)
    private String createUser;

    @Column(name = "transfer_form", length = 100)
    private String transferForm;

    @Column(name = "transfer_time")
    private Instant transferTime;


    public String getEngCompId() {
        return engCompId;
    }

    public void setEngCompId(String engCompId) {
        this.engCompId = engCompId;
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

    public void setIsDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    public boolean getIsUse() {
        return isUse;
    }

    public void setUse(boolean isUse) {
        this.isUse = isUse;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

//    public Geography getGeographys() {
//        return geographys;
//    }
//
//    public void setGeographys(Geography geographys) {
//        this.geographys = geographys;
//    }

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

    public String getTransferForm() {
        return transferForm;
    }

    public void setTransferForm(String transferForm) {
        this.transferForm = transferForm;
    }

    public Instant getTransferTime() {
        return transferTime;
    }

    public void setTransferTime(Instant transferTime) {
        this.transferTime = transferTime;
    }

    @Override
    public String toString() {
        return "EngCompContact{" +
                "engCompId='" + engCompId + '\'' +
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
                ", isUse=" + isUse +
                ", remark='" + remark + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", updateTime=" + updateTime +
                ", updateUser='" + updateUser + '\'' +
                ", createTime=" + createTime +
                ", createUser='" + createUser + '\'' +
                ", transferForm='" + transferForm + '\'' +
                ", transferTime=" + transferTime +
                '}';
    }
}
