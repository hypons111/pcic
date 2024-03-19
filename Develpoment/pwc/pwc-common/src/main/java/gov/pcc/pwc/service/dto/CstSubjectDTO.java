package gov.pcc.pwc.service.dto;

import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.CstSubject} entity.
 */
public class CstSubjectDTO implements Serializable {

    /**
     * type:numeric(4,0)
     */
    @NotNull
    @Schema(description = "type:numeric(4,0)", required = true)
    private Long id;

    /**
     * type: nvarchar(50)
     */
    @Size(max = 50)
    @Schema(description = "type: nvarchar(50)")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CstSubjectDTO)) {
            return false;
        }

        CstSubjectDTO cstSubjectDTO = (CstSubjectDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, cstSubjectDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CstSubjectDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
