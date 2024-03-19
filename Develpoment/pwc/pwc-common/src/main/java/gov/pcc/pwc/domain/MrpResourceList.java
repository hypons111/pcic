package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A MrpResourceList.
 */
@Entity
@Table(name = "mrp_resource_list")
public class MrpResourceList implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Column(name = "filesource_id", nullable = false)
    private Long filesourceId;

    @NotNull
    @Column(name = "tender_info_file_id", nullable = false)
    private String tenderInfoFileId;

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id", nullable = false)
    private Long mrlId;

    @Size(max = 50)
    @Column(name = "item_kind", length = 50)
    private String itemKind;

    @Size(max = 1000)
    @Column(name = "description_tw", length = 1000)
    private String descriptionTw;

    @Size(max = 1000)
    @Column(name = "description_en", length = 1000)
    private String descriptionEn;

    @Size(max = 50)
    @Column(name = "unit_tw", length = 50)
    private String unitTw;

    @Size(max = 50)
    @Column(name = "unit_en", length = 50)
    private String unitEn;

    @Column(name = "quantity")
    private Double quantity;

    @Column(name = "price")
    private Double price;

    @Column(name = "amount")
    private Double amount;

    @Size(max = 1000)
    @Column(name = "remark", length = 1000)
    private String remark;

    @Size(max = 50)
    @Column(name = "refitem_code", length = 50)
    private String refitemCode;

    @Size(max = 50)
    @Column(name = "item_key", length = 50)
    private String itemKey;

    @Size(max = 50)
    @Column(name = "parent_key", length = 50)
    private String parentKey;

    @Size(max = 50)
    @Column(name = "itemno", length = 50)
    private String itemno;

    @Column(name = "percentt")
    private Double percentt;

    @Column(name = "labour_ratio")
    private Double labourRatio;

    @Column(name = "equipment_ratio")
    private Double equipmentRatio;

    @Column(name = "material_ratio")
    private Double materialRatio;

    @Column(name = "miscellanea_ratio")
    private Double miscellaneaRatio;

    @Size(max = 50)
    @Column(name = "main_code", length = 50)
    private String mainCode;

    @NotNull
    @Column(name = "is_analysis", nullable = false)
    private Integer isAnalysis;

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

    public Long getFilesourceId() {
        return this.filesourceId;
    }

    public MrpResourceList filesourceId(Long filesourceId) {
        this.setFilesourceId(filesourceId);
        return this;
    }

    public void setFilesourceId(Long filesourceId) {
        this.filesourceId = filesourceId;
    }

    public String getTenderInfoFileId() {
        return this.tenderInfoFileId;
    }

    public MrpResourceList tenderInfoFileId(String tenderInfoFileId) {
        this.setTenderInfoFileId(tenderInfoFileId);
        return this;
    }

    public void setTenderInfoFileId(String tenderInfoFileId) {
        this.tenderInfoFileId = tenderInfoFileId;
    }

    public Long getMrlId() {
        return this.mrlId;
    }

    public MrpResourceList mrlId(Long mrlId) {
        this.setMrlId(mrlId);
        return this;
    }

    public void setMrlId(Long mrlId) {
        this.mrlId = mrlId;
    }

    public String getItemKind() {
        return this.itemKind;
    }

    public MrpResourceList itemKind(String itemKind) {
        this.setItemKind(itemKind);
        return this;
    }

    public void setItemKind(String itemKind) {
        this.itemKind = itemKind;
    }

    public String getDescriptionTw() {
        return this.descriptionTw;
    }

    public MrpResourceList descriptionTw(String descriptionTw) {
        this.setDescriptionTw(descriptionTw);
        return this;
    }

    public void setDescriptionTw(String descriptionTw) {
        this.descriptionTw = descriptionTw;
    }

    public String getDescriptionEn() {
        return this.descriptionEn;
    }

    public MrpResourceList descriptionEn(String descriptionEn) {
        this.setDescriptionEn(descriptionEn);
        return this;
    }

    public void setDescriptionEn(String descriptionEn) {
        this.descriptionEn = descriptionEn;
    }

    public String getUnitTw() {
        return this.unitTw;
    }

    public MrpResourceList unitTw(String unitTw) {
        this.setUnitTw(unitTw);
        return this;
    }

    public void setUnitTw(String unitTw) {
        this.unitTw = unitTw;
    }

    public String getUnitEn() {
        return this.unitEn;
    }

    public MrpResourceList unitEn(String unitEn) {
        this.setUnitEn(unitEn);
        return this;
    }

    public void setUnitEn(String unitEn) {
        this.unitEn = unitEn;
    }

    public Double getQuantity() {
        return this.quantity;
    }

    public MrpResourceList quantity(Double quantity) {
        this.setQuantity(quantity);
        return this;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return this.price;
    }

    public MrpResourceList price(Double price) {
        this.setPrice(price);
        return this;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getAmount() {
        return this.amount;
    }

    public MrpResourceList amount(Double amount) {
        this.setAmount(amount);
        return this;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getRemark() {
        return this.remark;
    }

    public MrpResourceList remark(String remark) {
        this.setRemark(remark);
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRefitemCode() {
        return this.refitemCode;
    }

    public MrpResourceList refitemCode(String refitemCode) {
        this.setRefitemCode(refitemCode);
        return this;
    }

    public void setRefitemCode(String refitemCode) {
        this.refitemCode = refitemCode;
    }

    public String getItemKey() {
        return this.itemKey;
    }

    public MrpResourceList itemKey(String itemKey) {
        this.setItemKey(itemKey);
        return this;
    }

    public void setItemKey(String itemKey) {
        this.itemKey = itemKey;
    }

    public String getParentKey() {
        return this.parentKey;
    }

    public MrpResourceList parentKey(String parentKey) {
        this.setParentKey(parentKey);
        return this;
    }

    public void setParentKey(String parentKey) {
        this.parentKey = parentKey;
    }

    public String getItemno() {
        return this.itemno;
    }

    public MrpResourceList itemno(String itemno) {
        this.setItemno(itemno);
        return this;
    }

    public void setItemno(String itemno) {
        this.itemno = itemno;
    }

    public Double getPercentt() {
        return this.percentt;
    }

    public MrpResourceList percentt(Double percentt) {
        this.setPercentt(percentt);
        return this;
    }

    public void setPercentt(Double percentt) {
        this.percentt = percentt;
    }

    public Double getLabourRatio() {
        return this.labourRatio;
    }

    public MrpResourceList labourRatio(Double labourRatio) {
        this.setLabourRatio(labourRatio);
        return this;
    }

    public void setLabourRatio(Double labourRatio) {
        this.labourRatio = labourRatio;
    }

    public Double getEquipmentRatio() {
        return this.equipmentRatio;
    }

    public MrpResourceList equipmentRatio(Double equipmentRatio) {
        this.setEquipmentRatio(equipmentRatio);
        return this;
    }

    public void setEquipmentRatio(Double equipmentRatio) {
        this.equipmentRatio = equipmentRatio;
    }

    public Double getMaterialRatio() {
        return this.materialRatio;
    }

    public MrpResourceList materialRatio(Double materialRatio) {
        this.setMaterialRatio(materialRatio);
        return this;
    }

    public void setMaterialRatio(Double materialRatio) {
        this.materialRatio = materialRatio;
    }

    public Double getMiscellaneaRatio() {
        return this.miscellaneaRatio;
    }

    public MrpResourceList miscellaneaRatio(Double miscellaneaRatio) {
        this.setMiscellaneaRatio(miscellaneaRatio);
        return this;
    }

    public void setMiscellaneaRatio(Double miscellaneaRatio) {
        this.miscellaneaRatio = miscellaneaRatio;
    }

    public String getMainCode() {
        return this.mainCode;
    }

    public MrpResourceList mainCode(String mainCode) {
        this.setMainCode(mainCode);
        return this;
    }

    public void setMainCode(String mainCode) {
        this.mainCode = mainCode;
    }

    public Integer getIsAnalysis() {
        return this.isAnalysis;
    }

    public MrpResourceList isAnalysis(Integer isAnalysis) {
        this.setIsAnalysis(isAnalysis);
        return this;
    }

    public void setIsAnalysis(Integer isAnalysis) {
        this.isAnalysis = isAnalysis;
    }

    public String getUpdateId() {
        return this.updateId;
    }

    public MrpResourceList updateId(String updateId) {
        this.setUpdateId(updateId);
        return this;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }

    public Instant getUpdateDate() {
        return this.updateDate;
    }

    public MrpResourceList updateDate(Instant updateDate) {
        this.setUpdateDate(updateDate);
        return this;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public String getCreateId() {
        return this.createId;
    }

    public MrpResourceList createId(String createId) {
        this.setCreateId(createId);
        return this;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }

    public Instant getCreateDate() {
        return this.createDate;
    }

    public MrpResourceList createDate(Instant createDate) {
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
        if (!(o instanceof MrpResourceList)) {
            return false;
        }
        return mrlId != null && mrlId.equals(((MrpResourceList) o).mrlId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MrpResourceList{" +
            "mrlId=" + getMrlId() +
            ", filesourceId=" + getFilesourceId() +
            ", tenderInfoFileId='" + getTenderInfoFileId() + "'" +
            ", itemKind='" + getItemKind() + "'" +
            ", descriptionTw='" + getDescriptionTw() + "'" +
            ", descriptionEn='" + getDescriptionEn() + "'" +
            ", unitTw='" + getUnitTw() + "'" +
            ", unitEn='" + getUnitEn() + "'" +
            ", quantity=" + getQuantity() +
            ", price=" + getPrice() +
            ", amount=" + getAmount() +
            ", remark='" + getRemark() + "'" +
            ", refitemCode='" + getRefitemCode() + "'" +
            ", itemKey='" + getItemKey() + "'" +
            ", parentKey='" + getParentKey() + "'" +
            ", itemno='" + getItemno() + "'" +
            ", percentt=" + getPercentt() +
            ", labourRatio=" + getLabourRatio() +
            ", equipmentRatio=" + getEquipmentRatio() +
            ", materialRatio=" + getMaterialRatio() +
            ", miscellaneaRatio=" + getMiscellaneaRatio() +
            ", mainCode='" + getMainCode() + "'" +
            ", isAnalysis=" + getIsAnalysis() +
            ", updateId='" + getUpdateId() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            ", createId='" + getCreateId() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            "}";
    }
}
