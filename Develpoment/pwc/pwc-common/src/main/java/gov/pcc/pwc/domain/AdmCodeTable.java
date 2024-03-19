package gov.pcc.pwc.domain;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * A AdmCodeTable.
 */
@Entity
@Table(name = "adm_code_table")
public class AdmCodeTable implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    /**
     * type: varchar(5)
     */
    @NotNull
    @Size(max = 5)
    @Schema(description = "type: varchar(5)", required = true)
    @Column(name = "lang", length = 5, nullable = false)
    private String lang;

    /**
     * type: varchar(100)
     */
    @NotNull
    @Size(max = 100)
    @Schema(description = "type: varchar(100)", required = true)
    @Column(name = "category", length = 100, nullable = false)
    private String category;

    /**
     * type: varchar(100)
     */
    @NotNull
    @Size(max = 100)
    @Schema(description = "type: varchar(100)", required = true)
    @Column(name = "code", length = 100, nullable = false)
    private String code;

    /**
     * type: nvarchar(2000)
     */
    @NotNull
    @Size(max = 2000)
    @Schema(description = "type: nvarchar(2000)", required = true)
    @Column(name = "text", length = 2000, nullable = false)
    private String text;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdmCodeTable that = (AdmCodeTable) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AdmCodeTable{" +
                "id=" + id +
                ", lang='" + lang + '\'' +
                ", category='" + category + '\'' +
                ", code='" + code + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
