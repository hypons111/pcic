package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A MrpSysCode.
 */
@Entity
@Table(name = "mrp_sys_code")
public class MrpSysCode implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "sort_no", nullable = false)
    private Long sortNo;

    @NotNull
    @Size(max = 50)
    @Column(name = "data_type", length = 50, nullable = false)
    private String dataType;

    @NotNull
    @Size(max = 50)
    @Column(name = "data_key", length = 50, nullable = false)
    private String dataKey;

    @NotNull
    @Size(max = 20)
    @Column(name = "data_key_type", length = 20, nullable = false)
    private String dataKeyType;

    @NotNull
    @Size(max = 100)
    @Column(name = "data_value", length = 100, nullable = false)
    private String dataValue;

    @Size(max = 500)
    @Column(name = "code_descript", length = 500)
    private String codeDescript;

    @Size(max = 1000)
    @Column(name = "en_code_descript", length = 1000)
    private String enCodeDescript;

    @Size(max = 100)
    @Column(name = "remark", length = 100)
    private String remark;

    @NotNull
    @Column(name = "is_valid", nullable = false)
    private Integer isValid;

    @Column(name = "valid_from_date")
    private Instant validFromDate;

    @Column(name = "is_modify")
    private Integer isModify;

    @Size(max = 100)
    @Column(name = "attr_1", length = 100)
    private String attr1;

    @Size(max = 100)
    @Column(name = "attr_2", length = 100)
    private String attr2;

    @Size(max = 100)
    @Column(name = "attr_3", length = 100)
    private String attr3;

    @Size(max = 100)
    @Column(name = "attr_4", length = 100)
    private String attr4;

    @Size(max = 100)
    @Column(name = "attr_5", length = 100)
    private String attr5;

    @Size(max = 20)
    @Column(name = "update_id", length = 20)
    private String updateId;

    @Column(name = "update_date")
    private Instant updateDate;

    @NotNull
    @Size(max = 20)
    @Column(name = "create_id", length = 20, nullable = false)
    private String createId;

    @Column(name = "create_date")
    private Instant createDate;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getSortNo() {
        return this.sortNo;
    }

    public MrpSysCode sortNo(Long sortNo) {
        this.setSortNo(sortNo);
        return this;
    }

    public void setSortNo(Long sortNo) {
        this.sortNo = sortNo;
    }

    public String getDataType() {
        return this.dataType;
    }

    public MrpSysCode dataType(String dataType) {
        this.setDataType(dataType);
        return this;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getDataKey() {
        return this.dataKey;
    }

    public MrpSysCode dataKey(String dataKey) {
        this.setDataKey(dataKey);
        return this;
    }

    public void setDataKey(String dataKey) {
        this.dataKey = dataKey;
    }

    public String getDataKeyType() {
        return this.dataKeyType;
    }

    public MrpSysCode dataKeyType(String dataKeyType) {
        this.setDataKeyType(dataKeyType);
        return this;
    }

    public void setDataKeyType(String dataKeyType) {
        this.dataKeyType = dataKeyType;
    }

    public String getDataValue() {
        return this.dataValue;
    }

    public MrpSysCode dataValue(String dataValue) {
        this.setDataValue(dataValue);
        return this;
    }

    public void setDataValue(String dataValue) {
        this.dataValue = dataValue;
    }

    public String getCodeDescript() {
        return this.codeDescript;
    }

    public MrpSysCode codeDescript(String codeDescript) {
        this.setCodeDescript(codeDescript);
        return this;
    }

    public void setCodeDescript(String codeDescript) {
        this.codeDescript = codeDescript;
    }

    public String getEnCodeDescript() {
        return this.enCodeDescript;
    }

    public MrpSysCode enCodeDescript(String enCodeDescript) {
        this.setEnCodeDescript(enCodeDescript);
        return this;
    }

    public void setEnCodeDescript(String enCodeDescript) {
        this.enCodeDescript = enCodeDescript;
    }

    public String getRemark() {
        return this.remark;
    }

    public MrpSysCode remark(String remark) {
        this.setRemark(remark);
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getIsValid() {
        return this.isValid;
    }

    public MrpSysCode isValid(Integer isValid) {
        this.setIsValid(isValid);
        return this;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public Instant getValidFromDate() {
        return this.validFromDate;
    }

    public MrpSysCode validFromDate(Instant validFromDate) {
        this.setValidFromDate(validFromDate);
        return this;
    }

    public void setValidFromDate(Instant validFromDate) {
        this.validFromDate = validFromDate;
    }

    public Integer getIsModify() {
        return this.isModify;
    }

    public MrpSysCode isModify(Integer isModify) {
        this.setIsModify(isModify);
        return this;
    }

    public void setIsModify(Integer isModify) {
        this.isModify = isModify;
    }

    public String getAttr1() {
        return this.attr1;
    }

    public MrpSysCode attr1(String attr1) {
        this.setAttr1(attr1);
        return this;
    }

    public void setAttr1(String attr1) {
        this.attr1 = attr1;
    }

    public String getAttr2() {
        return this.attr2;
    }

    public MrpSysCode attr2(String attr2) {
        this.setAttr2(attr2);
        return this;
    }

    public void setAttr2(String attr2) {
        this.attr2 = attr2;
    }

    public String getAttr3() {
        return this.attr3;
    }

    public MrpSysCode attr3(String attr3) {
        this.setAttr3(attr3);
        return this;
    }

    public void setAttr3(String attr3) {
        this.attr3 = attr3;
    }

    public String getAttr4() {
        return this.attr4;
    }

    public MrpSysCode attr4(String attr4) {
        this.setAttr4(attr4);
        return this;
    }

    public void setAttr4(String attr4) {
        this.attr4 = attr4;
    }

    public String getAttr5() {
        return this.attr5;
    }

    public MrpSysCode attr5(String attr5) {
        this.setAttr5(attr5);
        return this;
    }

    public void setAttr5(String attr5) {
        this.attr5 = attr5;
    }

    public String getUpdateId() {
        return this.updateId;
    }

    public MrpSysCode updateId(String updateId) {
        this.setUpdateId(updateId);
        return this;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }

    public Instant getUpdateDate() {
        return this.updateDate;
    }

    public MrpSysCode updateDate(Instant updateDate) {
        this.setUpdateDate(updateDate);
        return this;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public String getCreateId() {
        return this.createId;
    }

    public MrpSysCode createId(String createId) {
        this.setCreateId(createId);
        return this;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }

    public Instant getCreateDate() {
        return this.createDate;
    }

    public MrpSysCode createDate(Instant createDate) {
        this.setCreateDate(createDate);
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MrpSysCode)) {
            return false;
        }
        return sortNo != null && sortNo.equals(((MrpSysCode) o).sortNo);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MrpSysCode{" +
            "sortNo=" + getSortNo() +
            ", dataType='" + getDataType() + "'" +
            ", dataKey='" + getDataKey() + "'" +
            ", dataKeyType='" + getDataKeyType() + "'" +
            ", dataValue='" + getDataValue() + "'" +
            ", codeDescript='" + getCodeDescript() + "'" +
            ", enCodeDescript='" + getEnCodeDescript() + "'" +
            ", remark='" + getRemark() + "'" +
            ", isValid=" + getIsValid() +
            ", validFromDate='" + getValidFromDate() + "'" +
            ", isModify=" + getIsModify() +
            ", attr1='" + getAttr1() + "'" +
            ", attr2='" + getAttr2() + "'" +
            ", attr3='" + getAttr3() + "'" +
            ", attr4='" + getAttr4() + "'" +
            ", attr5='" + getAttr5() + "'" +
            ", updateId='" + getUpdateId() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            ", createId='" + getCreateId() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            "}";
    }
}
