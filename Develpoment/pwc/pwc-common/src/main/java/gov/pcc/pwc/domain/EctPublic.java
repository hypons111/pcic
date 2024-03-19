package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A EctPublic.
 */
@Entity
@Table(name = "ect_public")
public class EctPublic implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "ect_publish_id", nullable = false)
    private Long ectPublishId;

    @NotNull
    @Column(name = "certificate_no", nullable = false)
    private Long certificateNo;

    @NotNull
    @Column(name = "certificate_type", nullable = false)
    private Integer certificateType;

    @NotNull
    @Column(name = "apply_item", nullable = false)
    private Integer applyItem;

    @NotNull
    @Column(name = "certificate_state", nullable = false)
    private Integer certificateState;

    @NotNull
    @Size(max = 30)
    @Column(name = "certificate_word", length = 30, nullable = false)
    private String certificateWord;

    @NotNull
    @Size(max = 50)
    @Column(name = "certificate_text", length = 50, nullable = false)
    private String certificateText;

    @NotNull
    @Size(max = 10)
    @Column(name = "public_year_month", length = 10, nullable = false)
    private String publicYearMonth;

    @NotNull
    @Size(max = 50)
    @Column(name = "public_text", length = 50, nullable = false)
    private String publicText;

    @Size(max = 50)
    @Column(name = "name", length = 50)
    private String name;

    @Size(max = 50)
    @Column(name = "ch_name_rome", length = 50)
    private String chNameRome;

    @Size(max = 100)
    @Column(name = "en_name", length = 100)
    private String enName;

    @Size(max = 30)
    @Column(name = "idno", length = 30)
    private String idno;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "sex")
    private String sex;

    @Column(name = "is_die")
    private String isDie;

    @Size(max = 50)
    @Column(name = "qty_code", length = 50)
    private String qtyCode;

    @Size(max = 200)
    @Column(name = "qty_name", length = 200)
    private String qtyName;

    @Size(max = 100)
    @Column(name = "class", length = 100)
    private String classes;

    @Size(max = 100)
    @Column(name = "qtclass_name", length = 100)
    private String qtclassName;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "class_hour")
    private Integer classHour;

    @Size(max = 200)
    @Column(name = "per_photo_name", length = 200)
    private String perPhotoName;

    @Size(max = 60)
    @Column(name = "comp_name", length = 60)
    private String compName;

    @Size(max = 300)
    @Column(name = "comp_addr", length = 300)
    private String compAddr;

    @Size(max = 10)
    @Column(name = "comp_ban", length = 10)
    private String compBan;

    @Size(max = 60)
    @Column(name = "rp_name", length = 60)
    private String rpName;

    @Size(max = 100)
    @Column(name = "reason", length = 100)
    private String reason;

    @Size(max = 1000)
    @Column(name = "business_content", length = 1000)
    private String businessContent;

    @Size(max = 300)
    @Column(name = "classification_cname", length = 300)
    private String classificationCname;

    @Size(max = 300)
    @Column(name = "classification_ename", length = 300)
    private String classificationEname;

    @Size(max = 600)
    @Column(name = "exam_num", length = 600)
    private String examNum;

    @Size(max = 300)
    @Column(name = "exam_enum", length = 300)
    private String examEnum;

    @Size(max = 300)
    @Column(name = "pra_approach", length = 300)
    private String praApproach;

    @Size(max = 100)
    @Column(name = "pra_comp", length = 100)
    private String praComp;

    @Size(max = 300)
    @Column(name = "pra_comp_addr", length = 300)
    private String praCompAddr;

    @Size(max = 2000)
    @Column(name = "pra_class_business_scope", length = 2000)
    private String praClassBusinessScope;

    @Size(max = 100)
    @Column(name = "pra_class_cname", length = 100)
    private String praClassCname;

    @Size(max = 1000)
    @Column(name = "business_scope", length = 1000)
    private String businessScope;

    @Size(max = 10)
    @Column(name = "pra_start_date", length = 10)
    private String praStartDate;

    @Size(max = 10)
    @Column(name = "pra_expire_date", length = 10)
    private String praExpireDate;

    @Size(max = 200)
    @Column(name = "pfx_name", length = 200)
    private String pfxName;

    @Size(max = 50)
    @Column(name = "key_aname", length = 50)
    private String keyAname;

    @Size(max = 200)
    @Column(name = "hash_message", length = 200)
    private String hashMessage;

    @Size(max = 200)
    @Column(name = "file_name", length = 200)
    private String fileName;

    @Column(name = "file_create_time")
    private Instant fileCreateTime;

    @NotNull
    @Column(name = "is_valid", nullable = false)
    private String isValid;

    @Size(max = 50)
    @Column(name = "source_ip", length = 50)
    private String sourceIp;

    @NotNull
    @Size(max = 40)
    @Column(name = "create_user", length = 40, nullable = false)
    private String createUser;

    @NotNull
    @Column(name = "create_time", nullable = false)
    private Instant createTime;

    @Size(max = 40)
    @Column(name = "update_user", length = 40)
    private String updateUser;

    @Column(name = "update_time")
    private Instant updateTime;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getEctPublishId() {
        return this.ectPublishId;
    }

    public EctPublic ectPublishId(Long ectPublishId) {
        this.setEctPublishId(ectPublishId);
        return this;
    }

    public void setEctPublishId(Long ectPublishId) {
        this.ectPublishId = ectPublishId;
    }

    public Long getCertificateNo() {
        return this.certificateNo;
    }

    public EctPublic certificateNo(Long certificateNo) {
        this.setCertificateNo(certificateNo);
        return this;
    }

    public void setCertificateNo(Long certificateNo) {
        this.certificateNo = certificateNo;
    }

    public Integer getCertificateType() {
        return this.certificateType;
    }

    public EctPublic certificateType(Integer certificateType) {
        this.setCertificateType(certificateType);
        return this;
    }

    public void setCertificateType(Integer certificateType) {
        this.certificateType = certificateType;
    }

    public Integer getApplyItem() {
        return this.applyItem;
    }

    public EctPublic applyItem(Integer applyItem) {
        this.setApplyItem(applyItem);
        return this;
    }

    public void setApplyItem(Integer applyItem) {
        this.applyItem = applyItem;
    }

    public Integer getCertificateState() {
        return this.certificateState;
    }

    public EctPublic certificateState(Integer certificateState) {
        this.setCertificateState(certificateState);
        return this;
    }

    public void setCertificateState(Integer certificateState) {
        this.certificateState = certificateState;
    }

    public String getCertificateWord() {
        return this.certificateWord;
    }

    public EctPublic certificateWord(String certificateWord) {
        this.setCertificateWord(certificateWord);
        return this;
    }

    public void setCertificateWord(String certificateWord) { this.certificateWord = certificateWord; }

    public String getCertificateText() {
        return this.certificateText;
    }

    public EctPublic certificateText(String certificateText) {
        this.setCertificateText(certificateText);
        return this;
    }

    public void setCertificateText(String certificateText) {
        this.certificateText = certificateText;
    }

    public String getPublicYearMonth() {
        return this.publicYearMonth;
    }

    public EctPublic publicYearMonth(String publicYearMonth) {
        this.setPublicYearMonth(publicYearMonth);
        return this;
    }

    public void setPublicYearMonth(String publicYearMonth) {
        this.publicYearMonth = publicYearMonth;
    }

    public String getPublicText() {
        return this.publicText;
    }

    public EctPublic publicText(String publicText) {
        this.setPublicText(publicText);
        return this;
    }

    public void setPublicText(String publicText) {
        this.publicText = publicText;
    }

    public String getName() {
        return this.name;
    }

    public EctPublic name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChNameRome() {
        return this.chNameRome;
    }

    public EctPublic chNameRome(String chNameRome) {
        this.setChNameRome(chNameRome);
        return this;
    }

    public void setChNameRome(String chNameRome) {
        this.chNameRome = chNameRome;
    }

    public String getEnName() {
        return this.enName;
    }

    public EctPublic enName(String enName) {
        this.setEnName(enName);
        return this;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getIdno() {
        return this.idno;
    }

    public EctPublic idno(String idno) {
        this.setIdno(idno);
        return this;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    public EctPublic birthDate(LocalDate birthDate) {
        this.setBirthDate(birthDate);
        return this;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getSex() {
        return this.sex;
    }

    public EctPublic sex(String sex) {
        this.setSex(sex);
        return this;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIsDie() {
        return this.isDie;
    }

    public EctPublic isDie(String isDie) {
        this.setIsDie(isDie);
        return this;
    }

    public void setIsDie(String isDie) {
        this.isDie = isDie;
    }

    public String getQtyCode() {
        return this.qtyCode;
    }

    public EctPublic qtyCode(String qtyCode) {
        this.setQtyCode(qtyCode);
        return this;
    }

    public void setQtyCode(String qtyCode) {
        this.qtyCode = qtyCode;
    }

    public String getQtyName() {
        return this.qtyName;
    }

    public EctPublic qtyName(String qtyName) {
        this.setQtyName(qtyName);
        return this;
    }

    public void setQtyName(String qtyName) {
        this.qtyName = qtyName;
    }

    public String getClasses() {
        return this.classes;
    }

    public EctPublic classes(String classes) {
        this.setClasses(classes);
        return this;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public String getQtclassName() {
        return this.qtclassName;
    }

    public EctPublic qtclassName(String qtclassName) {
        this.setQtclassName(qtclassName);
        return this;
    }

    public void setQtclassName(String qtclassName) {
        this.qtclassName = qtclassName;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public EctPublic startDate(LocalDate startDate) {
        this.setStartDate(startDate);
        return this;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }

    public EctPublic endDate(LocalDate endDate) {
        this.setEndDate(endDate);
        return this;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Integer getClassHour() {
        return this.classHour;
    }

    public EctPublic classHour(Integer classHour) {
        this.setClassHour(classHour);
        return this;
    }

    public void setClassHour(Integer classHour) {
        this.classHour = classHour;
    }

    public String getPerPhotoName() {
        return this.perPhotoName;
    }

    public EctPublic perPhotoName(String perPhotoName) {
        this.setPerPhotoName(perPhotoName);
        return this;
    }

    public void setPerPhotoName(String perPhotoName) {
        this.perPhotoName = perPhotoName;
    }

    public String getCompName() {
        return this.compName;
    }

    public EctPublic compName(String compName) {
        this.setCompName(compName);
        return this;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public String getCompAddr() {
        return this.compAddr;
    }

    public EctPublic compAddr(String compAddr) {
        this.setCompAddr(compAddr);
        return this;
    }

    public void setCompAddr(String compAddr) {
        this.compAddr = compAddr;
    }

    public String getCompBan() {
        return this.compBan;
    }

    public EctPublic compBan(String compBan) {
        this.setCompBan(compBan);
        return this;
    }

    public void setCompBan(String compBan) {
        this.compBan = compBan;
    }

    public String getRpName() {
        return this.rpName;
    }

    public EctPublic rpName(String rpName) {
        this.setRpName(rpName);
        return this;
    }

    public void setRpName(String rpName) {
        this.rpName = rpName;
    }

    public String getReason() {
        return this.reason;
    }

    public EctPublic reason(String reason) {
        this.setReason(reason);
        return this;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getBusinessContent() {
        return this.businessContent;
    }

    public EctPublic businessContent(String businessContent) {
        this.setBusinessContent(businessContent);
        return this;
    }

    public void setBusinessContent(String businessContent) {
        this.businessContent = businessContent;
    }

    public String getClassificationCname() {
        return this.classificationCname;
    }

    public EctPublic classificationCname(String classificationCname) {
        this.setClassificationCname(classificationCname);
        return this;
    }

    public void setClassificationCname(String classificationCname) {
        this.classificationCname = classificationCname;
    }

    public String getClassificationEname() {
        return this.classificationEname;
    }

    public EctPublic classificationEname(String classificationEname) {
        this.setClassificationEname(classificationEname);
        return this;
    }

    public void setClassificationEname(String classificationEname) {
        this.classificationEname = classificationEname;
    }

    public String getExamNum() {
        return this.examNum;
    }

    public EctPublic examNum(String examNum) {
        this.setExamNum(examNum);
        return this;
    }

    public void setExamNum(String examNum) {
        this.examNum = examNum;
    }

    public String getExamEnum() {
        return this.examEnum;
    }

    public EctPublic examEnum(String examEnum) {
        this.setExamEnum(examEnum);
        return this;
    }

    public void setExamEnum(String examEnum) {
        this.examEnum = examEnum;
    }

    public String getPraApproach() {
        return this.praApproach;
    }

    public EctPublic praApproach(String praApproach) {
        this.setPraApproach(praApproach);
        return this;
    }

    public void setPraApproach(String praApproach) {
        this.praApproach = praApproach;
    }

    public String getPraComp() {
        return this.praComp;
    }

    public EctPublic praComp(String praComp) {
        this.setPraComp(praComp);
        return this;
    }

    public void setPraComp(String praComp) {
        this.praComp = praComp;
    }

    public String getPraCompAddr() {
        return this.praCompAddr;
    }

    public EctPublic praCompAddr(String praCompAddr) {
        this.setPraCompAddr(praCompAddr);
        return this;
    }

    public void setPraCompAddr(String praCompAddr) {
        this.praCompAddr = praCompAddr;
    }

    public String getPraClassBusinessScope() {
        return this.praClassBusinessScope;
    }

    public EctPublic praClassBusinessScope(String praClassBusinessScope) {
        this.setPraClassBusinessScope(praClassBusinessScope);
        return this;
    }

    public void setPraClassBusinessScope(String praClassBusinessScope) {
        this.praClassBusinessScope = praClassBusinessScope;
    }

    public String getPraClassCname() {
        return this.praClassCname;
    }

    public EctPublic praClassCname(String praClassCname) {
        this.setPraClassCname(praClassCname);
        return this;
    }

    public void setPraClassCname(String praClassCname) {
        this.praClassCname = praClassCname;
    }

    public String getBusinessScope() {
        return this.businessScope;
    }

    public EctPublic businessScope(String businessScope) {
        this.setBusinessScope(businessScope);
        return this;
    }

    public void setBusinessScope(String businessScope) {
        this.businessScope = businessScope;
    }

    public String getPraStartDate() {
        return this.praStartDate;
    }

    public EctPublic praStartDate(String praStartDate) {
        this.setPraStartDate(praStartDate);
        return this;
    }

    public void setPraStartDate(String praStartDate) {
        this.praStartDate = praStartDate;
    }

    public String getPraExpireDate() {
        return this.praExpireDate;
    }

    public EctPublic praExpireDate(String praExpireDate) {
        this.setPraExpireDate(praExpireDate);
        return this;
    }

    public void setPraExpireDate(String praExpireDate) {
        this.praExpireDate = praExpireDate;
    }

    public String getPfxName() {
        return this.pfxName;
    }

    public EctPublic pfxName(String pfxName) {
        this.setPfxName(pfxName);
        return this;
    }

    public void setPfxName(String pfxName) {
        this.pfxName = pfxName;
    }

    public String getKeyAname() {
        return this.keyAname;
    }

    public EctPublic keyAname(String keyAname) {
        this.setKeyAname(keyAname);
        return this;
    }

    public void setKeyAname(String keyAname) {
        this.keyAname = keyAname;
    }

    public String getHashMessage() {
        return this.hashMessage;
    }

    public EctPublic hashMessage(String hashMessage) {
        this.setHashMessage(hashMessage);
        return this;
    }

    public void setHashMessage(String hashMessage) {
        this.hashMessage = hashMessage;
    }

    public String getFileName() {
        return this.fileName;
    }

    public EctPublic fileName(String fileName) {
        this.setFileName(fileName);
        return this;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Instant getFileCreateTime() {
        return this.fileCreateTime;
    }

    public EctPublic fileCreateTime(Instant fileCreateTime) {
        this.setFileCreateTime(fileCreateTime);
        return this;
    }

    public void setFileCreateTime(Instant fileCreateTime) {
        this.fileCreateTime = fileCreateTime;
    }

    public String getIsValid() {
        return this.isValid;
    }

    public EctPublic isValid(String isValid) {
        this.setIsValid(isValid);
        return this;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }

    public String getSourceIp() {
        return this.sourceIp;
    }

    public EctPublic sourceIp(String sourceIp) {
        this.setSourceIp(sourceIp);
        return this;
    }

    public void setSourceIp(String sourceIp) {
        this.sourceIp = sourceIp;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public EctPublic createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getCreateTime() {
        return this.createTime;
    }

    public EctPublic createTime(Instant createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public EctPublic updateUser(String updateUser) {
        this.setUpdateUser(updateUser);
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getUpdateTime() {
        return this.updateTime;
    }

    public EctPublic updateTime(Instant updateTime) {
        this.setUpdateTime(updateTime);
        return this;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EctPublic)) {
            return false;
        }
        return ectPublishId != null && ectPublishId.equals(((EctPublic) o).ectPublishId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EctPublic{" +
            "ectPublishId=" + getEctPublishId() +
            ", certificateNo=" + getCertificateNo() +
            ", certificateType=" + getCertificateType() +
            ", applyItem=" + getApplyItem() +
            ", certificateState=" + getCertificateState() +
            ", certificateWord='" + getCertificateWord() + "'" +
            ", certificateText='" + getCertificateText() + "'" +
            ", publicYearMonth='" + getPublicYearMonth() + "'" +
            ", publicText='" + getPublicText() + "'" +
            ", name='" + getName() + "'" +
            ", chNameRome='" + getChNameRome() + "'" +
            ", enName='" + getEnName() + "'" +
            ", idno='" + getIdno() + "'" +
            ", birthDate='" + getBirthDate() + "'" +
            ", sex='" + getSex() + "'" +
            ", isDie='" + getIsDie() + "'" +
            ", qtyCode='" + getQtyCode() + "'" +
            ", qtyName='" + getQtyName() + "'" +
            ", classes='" + getClasses() + "'" +
            ", qtclassName='" + getQtclassName() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", classHour=" + getClassHour() +
            ", perPhotoName='" + getPerPhotoName() + "'" +
            ", compName='" + getCompName() + "'" +
            ", compAddr='" + getCompAddr() + "'" +
            ", compBan='" + getCompBan() + "'" +
            ", rpName='" + getRpName() + "'" +
            ", reason='" + getReason() + "'" +
            ", businessContent='" + getBusinessContent() + "'" +
            ", classificationCname='" + getClassificationCname() + "'" +
            ", classificationEname='" + getClassificationEname() + "'" +
            ", examNum='" + getExamNum() + "'" +
            ", examEnum='" + getExamEnum() + "'" +
            ", praApproach='" + getPraApproach() + "'" +
            ", praComp='" + getPraComp() + "'" +
            ", praCompAddr='" + getPraCompAddr() + "'" +
            ", praClassBusinessScope='" + getPraClassBusinessScope() + "'" +
            ", praClassCname='" + getPraClassCname() + "'" +
            ", businessScope='" + getBusinessScope() + "'" +
            ", praStartDate='" + getPraStartDate() + "'" +
            ", praExpireDate='" + getPraExpireDate() + "'" +
            ", pfxName='" + getPfxName() + "'" +
            ", keyAname='" + getKeyAname() + "'" +
            ", hashMessage='" + getHashMessage() + "'" +
            ", fileName='" + getFileName() + "'" +
            ", fileCreateTime='" + getFileCreateTime() + "'" +
            ", isValid='" + getIsValid() + "'" +
            ", sourceIp='" + getSourceIp() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            "}";
    }
}
