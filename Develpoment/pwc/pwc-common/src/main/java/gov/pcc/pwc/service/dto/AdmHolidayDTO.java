package gov.pcc.pwc.service.dto;

import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.Objects;

public class AdmHolidayDTO {

    private String id;

    private Instant holidayDate;

    @Size(max = 10)
    private String holidayName;

    @Size(max = 20)
    private String updateUser;

    private Instant updateTime;

    private int rowNum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Instant getHolidayDate() {
        return holidayDate;
    }

    public void setHolidayDate(Instant holidayDate) {
        this.holidayDate = holidayDate;
    }

    public String getHolidayName() {
        return holidayName;
    }

    public void setHolidayName(String holidayName) {
        this.holidayName = holidayName;
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

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdmHolidayDTO that = (AdmHolidayDTO) o;
        return rowNum == that.rowNum && Objects.equals(id, that.id) && Objects.equals(holidayDate, that.holidayDate) && Objects.equals(holidayName, that.holidayName) && Objects.equals(updateUser, that.updateUser) && Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, holidayDate, holidayName, updateUser, updateTime, rowNum);
    }

    @Override
    public String toString() {
        return "AdmHolidayDTO{" +
                "id='" + id + '\'' +
                ", holidayDate=" + holidayDate +
                ", holidayName='" + holidayName + '\'' +
                ", updateUser='" + updateUser + '\'' +
                ", updateTime=" + updateTime +
                ", rowNum=" + rowNum +
                '}';
    }
}
