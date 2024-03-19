package gov.pcc.pwc.service.dto;

import com.google.common.base.Objects;

public class RecSelectOptionsChildDTO {

    private String text;

    private String value;

    private String parent;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecSelectOptionsChildDTO that = (RecSelectOptionsChildDTO) o;
        return Objects.equal(text, that.text) && Objects.equal(value, that.value) && Objects.equal(parent, that.value);
    }

    @Override
    public String toString() {
        return "RecSelectOptionsChildDTO{" +
                "text='" + getText() + '\'' +
                ", value='" + getValue() + '\'' +
                ", parent='" + getParent() + '\'' +
                '}';
    }
}
