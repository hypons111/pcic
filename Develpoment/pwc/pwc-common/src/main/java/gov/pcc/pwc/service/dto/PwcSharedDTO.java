package gov.pcc.pwc.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * A DTO for shared
 */
public class PwcSharedDTO implements Serializable {

    /**
     * type: NVARCHAR(20)
     */
    @NotNull
    @Size(max = 20)
    @Schema(description = "type: NVARCHAR(20)")
    private String code;

    /**
     * type: NVARCHAR(50)
     */
    @Size(max = 50)
    @Schema(description = "type: NVARCHAR(50)")
    private String text;


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
        if (this == o) {
            return true;
        }
        if (!(o instanceof PwcSharedDTO)) {
            return false;
        }
        return false;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PwcSharedDTO{" +
            ", code='" + getCode() + "'" +
            ", text='" + getText() + "'" +
            "}";
    }
}
