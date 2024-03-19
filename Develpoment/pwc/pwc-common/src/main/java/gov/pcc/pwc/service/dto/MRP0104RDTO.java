package gov.pcc.pwc.service.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;

public class MRP0104RDTO implements Serializable {

    private Instant maxDate;

    private String a1;

    private String a2;

    private String base1;

    private String a5;

    private String a6;

    private String cnt;

    private String searchSuccess;

    private String keywordName;

    public Instant getMaxDate() {
        return maxDate;
    }

    public void setMaxDate(Instant maxDate) {
        this.maxDate = maxDate;
    }

    public String getA1() {
        return a1;
    }

    public void setA1(String a1) {
        this.a1= a1;
    }

    public String getA2() {
        return a2;
    }

    public void setA2(String a2) {
        this.a2 = a2;
    }

    public String getBase1() {
        return base1;
    }

    public void setBase1(String base1) {
        this.base1 = base1;
    }

    public String getA5() {
        return a5;
    }

    public void setA5(String a5) {
        this.a5 = a5;
    }

    public String getA6() {
        return a6;
    }

    public void setA6(String a6) {
        this.a6 = a6;
    }

    public String getCnt() {
        return cnt;
    }

    public void setCnt(String cnt) {
        this.cnt = cnt;
    }

    public String getSearchSuccess() {
        return searchSuccess;
    }

    public void setSearchSuccess(String searchSuccess) {
        this.searchSuccess = searchSuccess;
    }

    public String getKeywordName() {
        return keywordName;
    }

    public void setKeywordName(String keywordName) {
        this.keywordName = keywordName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MRP0104RDTO)) {
            return false;
        }

        MRP0104RDTO mrp0104rdto = (MRP0104RDTO) o;
        if (this.a1 == null) {
            return false;
        }
        return Objects.equals(this.a1, mrp0104rdto.a1);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.a1);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MRP0104RDTO{" +
            "maxDate=" + getMaxDate() +
            ", a1='" + getA1() + "'" +
            ", a2=" + getA2() +
            ", base1=" + getBase1() +
            ", a5=" + getA5() +
            ", a6='" + getA6() + "'" +
            ", cnt='" + getCnt() + "'" +
            ", searchSuccess='" + getSearchSuccess() + "'" +
            ", keywordName='" + getKeywordName() + "'" +
            "}";
    }
}
