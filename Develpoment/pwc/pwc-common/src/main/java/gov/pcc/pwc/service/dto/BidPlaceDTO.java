package gov.pcc.pwc.service.dto;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.BidPlace} entity.
 */
public class BidPlaceDTO implements Serializable {

    @NotNull
    @Size(max = 4)
    @Id
    @Column(name = "place_code", length = 4, nullable = false)
    private String placeCode;

    @Size(max = 20)
    @Column(name = "place_name", length = 20)
    private String placeName;

    @Size(max = 20)
    @Column(name = "shape", length = 20)
    private String shape;

    @Size(max = 1000)
    @Column(name = "coords", length = 1000)
    private String coords;

    @Size(max = 20)
    @Column(name = "mapsrc", length = 20)
    private String mapsrc;

    @Size(max = 10)
    @Column(name = "wid", length = 10)
    private String wid;

    @Size(max = 10)
    @Column(name = "hei", length = 10)
    private String hei;

    @Size(max = 20)
    @Column(name = "tm_2_x", length = 20)
    private String tm2x;

    @Size(max = 20)
    @Column(name = "tm_2_y", length = 20)
    private String tm2y;

    @Size(max = 20)
    @Column(name = "tm_2_xr", length = 20)
    private String tm2xr;

    @Size(max = 20)
    @Column(name = "tm_2_yr", length = 20)
    private String tm2yr;

    @Size(max = 4)
    @Column(name = "ord", length = 4)
    private String ord;

    @Column(name = "ucnt")
    private Integer ucnt;

    @Size(max = 1)
    @Column(name = "p_kind", length = 1)
    private String pKind;

    @NotNull
    @Column(name = "create_date", nullable = false)
    private Instant createDate;

    @NotNull
    @Size(max = 20)
    @Column(name = "create_user", length = 20, nullable = false)
    private String createUser;

    @Column(name = "update_date")
    private Instant updateDate;

    @Size(max = 20)
    @Column(name = "update_user", length = 20)
    private String updateUser;

    private Float projectCount;

    private Float ctSum;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getPlaceCode() {
        return this.placeCode;
    }
    
    public void setPlaceCode(String placeCode) {
        this.placeCode = placeCode;
    }

    public String getPlaceName() {
        return this.placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getShape() {
        return this.shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public String getCoords() {
        return this.coords;
    }

    public void setCoords(String coords) {
        this.coords = coords;
    }

    public String getMapsrc() {
        return this.mapsrc;
    }

    public void setMapsrc(String mapsrc) {
        this.mapsrc = mapsrc;
    }

    public String getWid() {
        return this.wid;
    }

    public void setWid(String wid) {
        this.wid = wid;
    }

    public String getHei() {
        return this.hei;
    }

    public void setHei(String hei) {
        this.hei = hei;
    }

    public String getTm2x() {
        return this.tm2x;
    }

    public void setTm2x(String tm2x) {
        this.tm2x = tm2x;
    }

    public String getTm2y() {
        return this.tm2y;
    }

    public void setTm2y(String tm2y) {
        this.tm2y = tm2y;
    }

    public String getTm2xr() {
        return this.tm2xr;
    }

    public void setTm2xr(String tm2xr) {
        this.tm2xr = tm2xr;
    }

    public String getTm2yr() {
        return this.tm2yr;
    }

    public void setTm2yr(String tm2yr) {
        this.tm2yr = tm2yr;
    }

    public String getOrd() {
        return this.ord;
    }

    public void setOrd(String ord) {
        this.ord = ord;
    }

    public Integer getUcnt() {
        return this.ucnt;
    }

    public void setUcnt(Integer ucnt) {
        this.ucnt = ucnt;
    }

    public String getpKind() {
        return this.pKind;
    }

    public void setpKind(String pKind) {
        this.pKind = pKind;
    }

    public Instant getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getUpdateDate() {
        return this.updateDate;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Float getProjectCount() {
        return this.projectCount;
    }

    public void setProjectCount(Float projectCount) {
        this.projectCount = projectCount;
    }

    public Float getCtSum() {
        return this.ctSum;
    }

    public void setCtSum(Float ctSum) {
        this.ctSum = ctSum;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BidPlaceDTO)) {
            return false;
        }

        BidPlaceDTO bidPlaceDTO = (BidPlaceDTO) o;
        if (this.placeCode == null) {
            return false;
        }
        return Objects.equals(this.placeCode, bidPlaceDTO.placeCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.placeCode);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidPlace{" +
                "placeCode=" + getPlaceCode() +
                ", placeName='" + getPlaceName() + "'" +
                ", shape='" + getShape() + "'" +
                ", coords='" + getCoords() + "'" +
                ", mapsrc='" + getMapsrc() + "'" +
                ", wid='" + getWid() + "'" +
                ", hei='" + getHei() + "'" +
                ", tm2x='" + getTm2x() + "'" +
                ", tm2y='" + getTm2y() + "'" +
                ", tm2xr='" + getTm2xr() + "'" +
                ", tm2yr='" + getTm2yr() + "'" +
                ", ord='" + getOrd() + "'" +
                ", ucnt=" + getUcnt() +
                ", pKind='" + getpKind() + "'" +
                ", createDate='" + getCreateDate() + "'" +
                ", createUser='" + getCreateUser() + "'" +
                ", updateDate='" + getUpdateDate() + "'" +
                ", updateUser='" + getUpdateUser() + "'" +
                "}";
    }
}
