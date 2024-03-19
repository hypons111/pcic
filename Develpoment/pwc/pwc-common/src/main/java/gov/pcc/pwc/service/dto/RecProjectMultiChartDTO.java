package gov.pcc.pwc.service.dto;

import java.util.List;

public class RecProjectMultiChartDTO {

    private String name;

    private String year;

    private List<String> data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RecProjectMultiChartDTO{" +
                "name='" + getName() + "'" +
                ", year='" + getYear() + "'" +
                ", data=" + getData() +
                '}';
    }
}
