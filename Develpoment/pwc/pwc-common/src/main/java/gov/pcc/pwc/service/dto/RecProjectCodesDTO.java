package gov.pcc.pwc.service.dto;

import java.util.Objects;

public class RecProjectCodesDTO {

    private String dataKey;

    private String value;

    private Boolean disabled;

    public String getDataKey() {
        return dataKey;
    }

    public void setDataKey(String dataKey) {
        this.dataKey = dataKey;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RecProjectCodesDTO)){
            return false;
        }
        RecProjectCodesDTO recProjectCodesDTO = (RecProjectCodesDTO) o;
        return Objects.equals(this.dataKey, recProjectCodesDTO.dataKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataKey, value, disabled);
    }

    @Override
    public String toString() {
        return "RecProjectCodesDTO{" +
                "dataKey='" + getDataKey() + "'" +
                ", value='" + getValue() + "'" +
                ", disabled=" + getDisabled() +"'"+
                '}';
    }
}
