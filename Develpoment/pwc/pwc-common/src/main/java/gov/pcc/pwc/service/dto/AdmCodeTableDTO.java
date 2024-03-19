package gov.pcc.pwc.service.dto;

import javax.validation.constraints.Size;
import java.util.Objects;

public class AdmCodeTableDTO {

    private Long id;

    @Size(max = 5)
    private String lang;

    @Size(max = 100)
    private String category;

    @Size(max = 100)
    private String code;

    @Size(max = 2000)
    private String text;

    private int rowNum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdmCodeTableDTO that = (AdmCodeTableDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "AdmCodeTableDTO{" +
                "id=" + id +
                ", lang='" + lang + '\'' +
                ", category='" + category + '\'' +
                ", code='" + code + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
