package gov.pcc.pwc.service.dto;

public class RecProjectChartDTO {

    private String item;

    private Integer cnt;

    private Double amt;

    private String typhoonYear;

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public Double getAmt() {
        return amt;
    }

    public void setAmt(Double amt) {
        this.amt = amt;
    }

    public String getTyphoonYear() {
        return typhoonYear;
    }

    public void setTyphoonYear(String typhoonYear) {
        this.typhoonYear = typhoonYear;
    }

    @Override
    public String toString() {
        return "RecProjectChartDTO{" +
                "item='" + getItem() + "'" +
                ", cnt='" + getCnt() + "'" +
                ", amt='" + getAmt() + "'" +
                ", typhoonYear='" + getTyphoonYear() + "'" +
                "}";
    }
}
