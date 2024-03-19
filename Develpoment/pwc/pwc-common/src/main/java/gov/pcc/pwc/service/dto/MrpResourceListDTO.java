package gov.pcc.pwc.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.MrpResourceList} entity.
 */
public class MrpResourceListDTO implements Serializable {

    @NotNull
    private Long filesourceId;

    @NotNull
    private String tenderInfoFileId;

    @NotNull
    private Long mrlId;

    @Size(max = 50)
    private String itemKind;

    @Size(max = 1000)
    private String descriptionTw;

    @Size(max = 1000)
    private String descriptionEn;

    @Size(max = 50)
    private String unitTw;

    @Size(max = 50)
    private String unitEn;

    private Double quantity;

    private Double price;

    private Double amount;

    @Size(max = 1000)
    private String remark;

    @Size(max = 50)
    private String refitemCode;

    @Size(max = 50)
    private String itemKey;

    @Size(max = 50)
    private String parentKey;

    @Size(max = 50)
    private String itemno;

    private Double percentt;

    private Double labourRatio;

    private Double equipmentRatio;

    private Double materialRatio;

    private Double miscellaneaRatio;

    @Size(max = 50)
    private String mainCode;

    @NotNull
    private Integer isAnalysis;

    @Size(max = 20)
    private String updateId;

    private Instant updateDate;

    @NotNull
    @Size(max = 20)
    private String createId;

    private Instant createDate;

    public Long getFilesourceId() {
        return filesourceId;
    }

    public void setFilesourceId(Long filesourceId) {
        this.filesourceId = filesourceId;
    }

    public String getTenderInfoFileId() {
        return tenderInfoFileId;
    }

    public void setTenderInfoFileId(String tenderInfoFileId) {
        this.tenderInfoFileId = tenderInfoFileId;
    }

    public Long getMrlId() {
        return mrlId;
    }

    public void setMrlId(Long mrlId) {
        this.mrlId = mrlId;
    }

    public String getItemKind() {
        return itemKind;
    }

    public void setItemKind(String itemKind) {
        this.itemKind = itemKind;
    }

    public String getDescriptionTw() {
        return descriptionTw;
    }

    public void setDescriptionTw(String descriptionTw) {
        this.descriptionTw = descriptionTw;
    }

    public String getDescriptionEn() {
        return descriptionEn;
    }

    public void setDescriptionEn(String descriptionEn) {
        this.descriptionEn = descriptionEn;
    }

    public String getUnitTw() {
        return unitTw;
    }

    public void setUnitTw(String unitTw) {
        this.unitTw = unitTw;
    }

    public String getUnitEn() {
        return unitEn;
    }

    public void setUnitEn(String unitEn) {
        this.unitEn = unitEn;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRefitemCode() {
        return refitemCode;
    }

    public void setRefitemCode(String refitemCode) {
        this.refitemCode = refitemCode;
    }

    public String getItemKey() {
        return itemKey;
    }

    public void setItemKey(String itemKey) {
        this.itemKey = itemKey;
    }

    public String getParentKey() {
        return parentKey;
    }

    public void setParentKey(String parentKey) {
        this.parentKey = parentKey;
    }

    public String getItemno() {
        return itemno;
    }

    public void setItemno(String itemno) {
        this.itemno = itemno;
    }

    public Double getPercentt() {
        return percentt;
    }

    public void setPercentt(Double percentt) {
        this.percentt = percentt;
    }

    public Double getLabourRatio() {
        return labourRatio;
    }

    public void setLabourRatio(Double labourRatio) {
        this.labourRatio = labourRatio;
    }

    public Double getEquipmentRatio() {
        return equipmentRatio;
    }

    public void setEquipmentRatio(Double equipmentRatio) {
        this.equipmentRatio = equipmentRatio;
    }

    public Double getMaterialRatio() {
        return materialRatio;
    }

    public void setMaterialRatio(Double materialRatio) {
        this.materialRatio = materialRatio;
    }

    public Double getMiscellaneaRatio() {
        return miscellaneaRatio;
    }

    public void setMiscellaneaRatio(Double miscellaneaRatio) {
        this.miscellaneaRatio = miscellaneaRatio;
    }

    public String getMainCode() {
        return mainCode;
    }

    public void setMainCode(String mainCode) {
        this.mainCode = mainCode;
    }

    public Integer getIsAnalysis() {
        return isAnalysis;
    }

    public void setIsAnalysis(Integer isAnalysis) {
        this.isAnalysis = isAnalysis;
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }

    public Instant getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MrpResourceListDTO)) {
            return false;
        }

        MrpResourceListDTO mrpResourceListDTO = (MrpResourceListDTO) o;
        if (this.mrlId == null) {
            return false;
        }
        return Objects.equals(this.mrlId, mrpResourceListDTO.mrlId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.mrlId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MrpResourceListDTO{" +
            "filesourceId=" + getFilesourceId() +
            ", tenderInfoFileId='" + getTenderInfoFileId() + "'" +
            ", mrlId=" + getMrlId() +
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
