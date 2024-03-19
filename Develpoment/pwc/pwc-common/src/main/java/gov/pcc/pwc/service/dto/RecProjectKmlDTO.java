package gov.pcc.pwc.service.dto;

public class RecProjectKmlDTO {

    private String projectName;

    private String originalDesign;

    private String gpsX;

    private String gpsY;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getOriginalDesign() {
        return originalDesign;
    }

    public void setOriginalDesign(String originalDesign) {
        this.originalDesign = originalDesign;
    }

    public String getGpsX() {
        return gpsX;
    }

    public void setGpsX(String gpsX) {
        this.gpsX = gpsX;
    }

    public String getGpsY() {
        return gpsY;
    }

    public void setGpsY(String gpsY) {
        this.gpsY = gpsY;
    }

    @Override
    public String toString() {
        return "RecProjectKmlDTO{" +
                "projectName='" + getProjectName() + "'" +
                ", originalDesign='" + getOriginalDesign() + "'" +
                ", gpsX='" + getGpsX() + "'" +
                ", gpsY='" + getGpsY() + "'" +
                '}';
    }
}
