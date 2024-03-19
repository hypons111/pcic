package gov.pcc.pwc.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A AdmMenu.
 */
@Entity
@Table(name = "adm_menu")
public class AdmMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "open_menu", nullable = false)
    private Boolean openMenu;

    @Column(name = "parent_id")
    private Long parentId;

    @Size(max = 200)
    @Column(name = "path", length = 200)
    private String path;

    @Column(name = "directory")
    private Boolean directory;

    @Size(max = 100)
    @Column(name = "function_id", length = 100)
    private String functionId;

    @Size(max = 100)
    @Column(name = "icon", length = 100)
    private String icon;

    @Size(max = 100)
    @Column(name = "label", length = 100)
    private String label;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public AdmMenu id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getOpenMenu() { return this.openMenu; }

    public AdmMenu openMenu(Boolean openMenu) {
        this.setOpenMenu(openMenu);
        return this;
    }

    public void setOpenMenu(Boolean openMenu) { this.openMenu = openMenu; }

    public Long getParentId() {
        return this.parentId;
    }

    public AdmMenu parentId(Long parentId) {
        this.setParentId(parentId);
        return this;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getPath() {
        return this.path;
    }

    public AdmMenu path(String path) {
        this.setPath(path);
        return this;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Boolean getDirectory() {
        return this.directory;
    }

    public AdmMenu directory(Boolean directory) {
        this.setDirectory(directory);
        return this;
    }

    public void setDirectory(Boolean directory) {
        this.directory = directory;
    }

    public String getFunctionId() {
        return this.functionId;
    }

    public AdmMenu functionId(String functionId) {
        this.setFunctionId(functionId);
        return this;
    }

    public void setFunctionId(String functionId) {
        this.functionId = functionId;
    }

    public String getIcon() {
        return this.icon;
    }

    public AdmMenu icon(String icon) {
        this.setIcon(icon);
        return this;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getLabel() {
        return this.label;
    }

    public AdmMenu label(String label) {
        this.setLabel(label);
        return this;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AdmMenu)) {
            return false;
        }
        return id != null && id.equals(((AdmMenu) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AdmMenu{" +
            "id=" + getId() +
            ", openMenu=" + getOpenMenu() +
            ", parentId=" + getParentId() +
            ", path='" + getPath() + "'" +
            ", directory='" + getDirectory() + "'" +
            ", functionId='" + getFunctionId() + "'" +
            ", icon='" + getIcon() + "'" +
            ", label='" + getLabel() + "'" +
            "}";
    }
}
