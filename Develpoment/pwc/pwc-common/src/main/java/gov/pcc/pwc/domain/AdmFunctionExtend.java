package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * A AdmFunctionExtend.
 */
@Entity
@Table(name = "Adm_Function_Extend")
public class AdmFunctionExtend implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Function_Extend_Id", length = 20, nullable = false)
    private String functionExtendId;

    @Size(max = 20)
    @Column(name = "Function_Extend_Name", length = 20, nullable = false)
    private String functionExtendName;

    @Size(max = 500)
    @Column(name = "Function_Extend_Descript", length = 500, nullable = false)
    private String functionExtendDescript;

    @Column(name = "Update_User", length = 20)
    private String updateUser;

    @Column(name = "Update_Time")
    private Instant updateTime;

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFunctionExtendId() {
        return functionExtendId;
    }

    public void setFunctionExtendId(String functionExtendId) {
        this.functionExtendId = functionExtendId;
    }

    public String getFunctionExtendName() {
        return functionExtendName;
    }

    public void setFunctionExtendName(String functionExtendName) {
        this.functionExtendName = functionExtendName;
    }

    public String getFunctionExtendDescript() {
        return functionExtendDescript;
    }

    public void setFunctionExtendDescript(String functionExtendDescript) {
        this.functionExtendDescript = functionExtendDescript;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AdmFunctionExtend)) {
            return false;
        }
        return id != null && id.equals(((AdmFunctionExtend) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "AdmFunctionExtend{" +
                "id=" + id +
                ", functionExtendId='" + functionExtendId + '\'' +
                ", functionExtendName='" + functionExtendName + '\'' +
                ", functionExtendDescript='" + functionExtendDescript + '\'' +
                ", updateUser='" + updateUser + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}
