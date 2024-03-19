package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.util.Objects;

public class BidCcindPrimaryKey implements Serializable {

    private String year;

    private String month;

    public BidCcindPrimaryKey() {
    }

    public BidCcindPrimaryKey(String year, String month) {
        this.year = year;
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BidCcindPrimaryKey)) return false;
        BidCcindPrimaryKey that = (BidCcindPrimaryKey) o;
        return year.equals(that.year) && month.equals(that.month);
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, month);
    }

    @Override
    public String toString() {
        return "BidCcindPrimaryKey{" +
                "year='" + year + '\'' +
                ", month='" + month + '\'' +
                '}';
    }
}
