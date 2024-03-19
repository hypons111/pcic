package gov.pcc.pwc.service.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.AdmMenu} entity.
 */
public class AdmMenuDTO implements Serializable {

    @NotNull
    private Long id;

    private Long parentId;

    @Size(max = 200)
    private String path;

    public static class Meta {
        private Boolean directory;

        @Size(max = 100)
        private String functionId;

        @Size(max = 100)
        private String icon;

        @Size(max = 100)
        private String label;

        public Boolean getDirectory() {
            return directory;
        }

        public void setDirectory(Boolean directory) {
            this.directory = directory;
        }

        public String getFunctionId() {
            return functionId;
        }

        public void setFunctionId(String functionId) {
            this.functionId = functionId;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Meta meta = (Meta) o;
            return com.google.common.base.Objects.equal(directory, meta.directory) && com.google.common.base.Objects.equal(functionId, meta.functionId) && com.google.common.base.Objects.equal(icon, meta.icon) && com.google.common.base.Objects.equal(label, meta.label);
        }

        @Override
        public int hashCode() {
            return com.google.common.base.Objects.hashCode(directory, functionId, icon, label);
        }

        @Override
        public String toString() {
            return com.google.common.base.MoreObjects.toStringHelper(this)
                    .add("directory", directory)
                    .add("functionId", functionId)
                    .add("icon", icon)
                    .add("label", label)
                    .toString();
        }
    }

    private final Meta meta = new Meta();

    private List<AdmMenuDTO> children = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Meta getMeta() {
        return meta;
    }

    public List<AdmMenuDTO> getChildren() {
        return children;
    }

    public void setChildren(List<AdmMenuDTO> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return com.google.common.base.MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("parentId", parentId)
                .add("path", path)
                .add("meta", meta)
                .add("children", children)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AdmMenuDTO)) {
            return false;
        }

        AdmMenuDTO admMenuDTO = (AdmMenuDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, admMenuDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

}
