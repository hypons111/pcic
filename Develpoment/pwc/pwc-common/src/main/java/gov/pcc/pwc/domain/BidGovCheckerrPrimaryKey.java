package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class BidGovCheckerrPrimaryKey implements Serializable {

    private String wkut;

    private String prjno;

    private LocalDate checkDate;

    private String errNum;

    public BidGovCheckerrPrimaryKey() {
    }

    public BidGovCheckerrPrimaryKey(String wkut, String prjno, LocalDate checkDate, String errNum) {
        this.wkut = wkut;
        this.prjno = prjno;
        this.checkDate = checkDate;
        this.errNum = errNum;
    }

    public String getWkut() {
        return wkut;
    }

    public void setWkut(String wkut) {
        this.wkut = wkut;
    }

    public String getPrjno() {
        return prjno;
    }

    public void setPrjno(String prjno) {
        this.prjno = prjno;
    }

    public LocalDate getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(LocalDate checkDate) {
        this.checkDate = checkDate;
    }

    public String getErrNum() {
        return errNum;
    }

    public void setErrNum(String errNum) {
        this.errNum = errNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BidGovCheckerrPrimaryKey)) return false;
        BidGovCheckerrPrimaryKey that = (BidGovCheckerrPrimaryKey) o;
        return wkut.equals(that.wkut) && prjno.equals(that.prjno) && checkDate.equals(that.checkDate) && errNum.equals(that.errNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wkut, prjno, checkDate, errNum);
    }

    @Override
    public String toString() {
        return "BidGovCheckPrimaryKey{" +
                "wkut='" + wkut + '\'' +
                ", prjno='" + prjno + '\'' +
                ", check_date='" + checkDate + '\'' +
                ", err_num='" + errNum + '\'' +
                '}';
    }
}
