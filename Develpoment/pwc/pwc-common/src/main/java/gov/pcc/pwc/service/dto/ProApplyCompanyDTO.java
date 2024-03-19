package gov.pcc.pwc.service.dto;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

public class ProApplyCompanyDTO implements Serializable {

    @Size(max = 60)
    private String name;

    @Size(max = 10)
    private String compIdno;

    @Size(max = 60)
    private String rpName;

    @Size(max = 10)
    private String addressCity;

    @Size(max = 10)
    private String addressTown;

    @Size(max = 10)
    private String addressVillage;

    @Size(max = 10)
    private String addressLin;

    @Size(max = 10)
    private String addressRoad;

    @Size(max = 60)
    private String title;

    @Size(max = 20)
    private String workTel;

    @Size(max = 20)
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompIdno() {
        return compIdno;
    }

    public void setCompIdno(String compIdno) {
        this.compIdno = compIdno;
    }

    public String getRpName() {
        return rpName;
    }

    public void setRpName(String rpName) {
        this.rpName = rpName;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public String getAddressTown() {
        return addressTown;
    }

    public void setAddressTown(String addressTown) {
        this.addressTown = addressTown;
    }

    public String getAddressVillage() {
        return addressVillage;
    }

    public void setAddressVillage(String addressVillage) {
        this.addressVillage = addressVillage;
    }

    public String getAddressLin() {
        return addressLin;
    }

    public void setAddressLin(String addressLin) {
        this.addressLin = addressLin;
    }

    public String getAddressRoad() {
        return addressRoad;
    }

    public void setAddressRoad(String addressRoad) {
        this.addressRoad = addressRoad;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWorkTel() {
        return workTel;
    }

    public void setWorkTel(String workTel) {
        this.workTel = workTel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProApplyCompanyDTO that = (ProApplyCompanyDTO) o;
        return Objects.equals(name, that.name) && Objects.equals(compIdno, that.compIdno) && Objects.equals(rpName, that.rpName) && Objects.equals(addressCity, that.addressCity) && Objects.equals(addressTown, that.addressTown) && Objects.equals(addressVillage, that.addressVillage) && Objects.equals(addressLin, that.addressLin) && Objects.equals(addressRoad, that.addressRoad) && Objects.equals(title, that.title) && Objects.equals(workTel, that.workTel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, compIdno, rpName, addressCity, addressTown, addressVillage, addressLin, addressRoad, title, workTel);
    }

    @Override
    public String toString() {
        return "ProApplyCompanyDTO{" +
                "name='" + name + '\'' +
                ", compIdno='" + compIdno + '\'' +
                ", rpName='" + rpName + '\'' +
                ", addressCity='" + addressCity + '\'' +
                ", addressTown='" + addressTown + '\'' +
                ", addressVillage='" + addressVillage + '\'' +
                ", addressLin='" + addressLin + '\'' +
                ", addressRoad='" + addressRoad + '\'' +
                ", title='" + title + '\'' +
                ", workTel='" + workTel + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
