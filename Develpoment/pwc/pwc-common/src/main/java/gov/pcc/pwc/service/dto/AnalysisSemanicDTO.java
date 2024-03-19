package gov.pcc.pwc.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class AnalysisSemanicDTO implements Serializable {

    private Long anaId;

    private String name;

    private String code;

    private String unit;

    private Double price;

    private Integer status;

    private Integer kind;

    private LocalDate awardBudgetDate;

    private String descriptionTw;

    private String unitTw;

    private String itemKey;

    public Long getAnaId() {
        return anaId;
    }

    public void setAnaId(Long anaId) {
        this.anaId = anaId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getKind() {
        return kind;
    }

    public void setKind(Integer kind) {
        this.kind = kind;
    }

    public LocalDate getAwardBudgetDate() {
        return awardBudgetDate;
    }

    public void setAwardBudgetDate(LocalDate awardBudgetDate) {
        this.awardBudgetDate = awardBudgetDate;
    }

    public String getDescriptionTw() {
        return descriptionTw;
    }

    public void setDescriptionTw(String descriptionTw) {
        this.descriptionTw = descriptionTw;
    }

    public String getUnitTw() {
        return unitTw;
    }

    public void setUnitTw(String unitTw) {
        this.unitTw = unitTw;
    }

    public String getItemKey() {
        return itemKey;
    }

    public void setItemKey(String itemKey) {
        this.itemKey = itemKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AnalysisSemanicDTO)) {
            return false;
        }

        AnalysisSemanicDTO mrpSynonymDTO = (AnalysisSemanicDTO) o;
        if (this.anaId == null) {
            return false;
        }
        return Objects.equals(this.anaId, mrpSynonymDTO.anaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.anaId);
    }

    @Override
    public String toString() {
        return "AnalysisSemanicDTO{" +
                "anaId=" + anaId +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", unit='" + unit + '\'' +
                ", price=" + price +
                ", status=" + status +
                ", kind=" + kind +
                ", awardBudgetDate=" + awardBudgetDate +
                ", descriptionTw='" + descriptionTw + '\'' +
                ", unitTw='" + unitTw + '\'' +
                ", itemKey='" + itemKey + '\'' +
                '}';
    }
}
