package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A BidPlace.
 */
@Entity
@Table(name = "bid_place")
public class BidPlace implements Serializable {

    private static final long serialVersionUID = 1L;

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
    @Column(name = "tm2x", length = 20)
    private String tm2x;

    @Size(max = 20)
    @Column(name = "tm2y", length = 20)
    private String tm2y;

    @Size(max = 20)
    @Column(name = "tm2xr", length = 20)
    private String tm2xr;

    @Size(max = 20)
    @Column(name = "tm2yr", length = 20)
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

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getPlaceCode() {
        return this.placeCode;
    }

    public BidPlace placeCode(String placeCode) {
        this.setPlaceCode(placeCode);
        return this;
    }

    public void setPlaceCode(String placeCode) {
        this.placeCode = placeCode;
    }

    public String getPlaceName() {
        return this.placeName;
    }

    public BidPlace placeName(String placeName) {
        this.setPlaceName(placeName);
        return this;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getShape() {
        return this.shape;
    }

    public BidPlace shape(String shape) {
        this.setShape(shape);
        return this;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public String getCoords() {
        return this.coords;
    }

    public BidPlace coords(String coords) {
        this.setCoords(coords);
        return this;
    }

    public void setCoords(String coords) {
        this.coords = coords;
    }

    public String getMapsrc() {
        return this.mapsrc;
    }

    public BidPlace mapsrc(String mapsrc) {
        this.setMapsrc(mapsrc);
        return this;
    }

    public void setMapsrc(String mapsrc) {
        this.mapsrc = mapsrc;
    }

    public String getWid() {
        return this.wid;
    }

    public BidPlace wid(String wid) {
        this.setWid(wid);
        return this;
    }

    public void setWid(String wid) {
        this.wid = wid;
    }

    public String getHei() {
        return this.hei;
    }

    public BidPlace hei(String hei) {
        this.setHei(hei);
        return this;
    }

    public void setHei(String hei) {
        this.hei = hei;
    }

    public String getTm2x() {
        return this.tm2x;
    }

    public BidPlace tm2x(String tm2x) {
        this.setTm2x(tm2x);
        return this;
    }

    public void setTm2x(String tm2x) {
        this.tm2x = tm2x;
    }

    public String getTm2y() {
        return this.tm2y;
    }

    public BidPlace tm2y(String tm2y) {
        this.setTm2y(tm2y);
        return this;
    }

    public void setTm2y(String tm2y) {
        this.tm2y = tm2y;
    }

    public String getTm2xr() {
        return this.tm2xr;
    }

    public BidPlace tm2xr(String tm2xr) {
        this.setTm2xr(tm2xr);
        return this;
    }

    public void setTm2xr(String tm2xr) {
        this.tm2xr = tm2xr;
    }

    public String getTm2yr() {
        return this.tm2yr;
    }

    public BidPlace tm2yr(String tm2yr) {
        this.setTm2yr(tm2yr);
        return this;
    }

    public void setTm2yr(String tm2yr) {
        this.tm2yr = tm2yr;
    }

    public String getOrd() {
        return this.ord;
    }

    public BidPlace ord(String ord) {
        this.setOrd(ord);
        return this;
    }

    public void setOrd(String ord) {
        this.ord = ord;
    }

    public Integer getUcnt() {
        return this.ucnt;
    }

    public BidPlace ucnt(Integer ucnt) {
        this.setUcnt(ucnt);
        return this;
    }

    public void setUcnt(Integer ucnt) {
        this.ucnt = ucnt;
    }

    public String getpKind() {
        return this.pKind;
    }

    public BidPlace pKind(String pKind) {
        this.setpKind(pKind);
        return this;
    }

    public void setpKind(String pKind) {
        this.pKind = pKind;
    }

    public Instant getCreateDate() {
        return this.createDate;
    }

    public BidPlace createDate(Instant createDate) {
        this.setCreateDate(createDate);
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public BidPlace createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getUpdateDate() {
        return this.updateDate;
    }

    public BidPlace updateDate(Instant updateDate) {
        this.setUpdateDate(updateDate);
        return this;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public BidPlace updateUser(String updateUser) {
        this.setUpdateUser(updateUser);
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BidPlace)) {
            return false;
        }
        return placeCode != null && placeCode.equals(((BidPlace) o).placeCode);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
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
