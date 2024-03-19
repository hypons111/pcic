package gov.pcc.pwc.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 *  A DTO for the Create AND Modify when Bulletin Download And Information
 */
public class BulletinDownAndInfoDTO implements Serializable {

    /**
     * type:bigint
     */
    @Schema(description = "type:bigint", required = true)
    private Long id;

    /**
     * type:nvarchar(100)
     */

    @Size(max = 100)
    @Schema(description = "type:nvarchar(100)", required = true)
    private String subject;

    /**
     * type:nvarchar(1000)
     */

    @Size(max = 1000)
    @Schema(description = "type:nvarchar(1000)", required = true)
    private String text;

    /**
     * type:nvarchar(100)
     */
    @Size(max = 100)
    @Schema(description = "type:nvarchar(100)")
    private String kind;

    @Schema(description = "type:varchar(2)")
    private String location;

    /**
     * type:date
     */
    @Schema(description = "type:date")
    private LocalDate releaseDate;

    /**
     * type:date
     */
    @Schema(description = "type:date")
    private LocalDate downDate;

    /**
     * type:nvarchar(20)
     */
    @Size(max = 20)
    @Schema(description = "type:nvarchar(20)")
    private String releaseUnit;

    /**
     * type:nvarchar(20)
     */
    @Size(max = 20)
    @Schema(description = "type:nvarchar(20)")
    private String contactPerson;

    /**
     * type:nvarchar(50)
     */
    @Size(max = 50)
    @Schema(description = "type:nvarchar(50)")
    private String contactInformationTel;

    /**
     * type:nvarchar(50)
     */
    @Size(max = 50)
    @Schema(description = "type:nvarchar(50)")
    private String contactInformationEmail;

    @Pattern(regexp = "[YN]")
    private String enable;

    /**
     * type:datetime
     */
    @Schema(description = "type:datetime")
    private Instant updateTime;

    /**
     * type:nvarchar(20)
     */
    @Size(max = 20)
    @Schema(description = "type:nvarchar(20)")
    private String updateUser;

    /**
     * type:datetime
     */
    @Schema(description = "type:datetime")
    private Instant createTime;

    /**
     * type:nvarchar(20)
     */
    @Size(max = 20)
    @Schema(description = "type:nvarchar(20)")
    private String createUser;

    /**
     * type:varchar
     */
    @Schema(description = "type:varchar")
    private String type;

    /**
     * 置頂
     * type:varchar
     */
    @Pattern(regexp = "[YN]")
    @Schema(description = "type:varchar")
    private String isTop;

    /**
     * 權限
     * type:List<Map<String, String>>
     */
    private List<PwcSharedDTO> jurisdictionRoles;

    /**
     * 模組
     * type:List<Map<String, String>>
     */
    private List<PwcSharedDTO> moduleRoles;

    /**
     * 連結text
     * type:List<String>
     */
    private List<String> inputUrlText;

    /**
     * 連結Adder
     * type:List<String>
     */
    private List<String> inputUrlAdder;

    /**
     * 檔案text
     * type:List<String>
     */
    private List<String> inputFileText;

    /**
     * 檔案
     * type:List<MultipartFile>
     */
    private List<MultipartFile> inputFile;

    /**
     * 檔案
     * type:List<String>
     */
    private List<String> locationListDisplay;

    /**
     * 前端處理狀態
     * type:varchar
     */
    @Schema(description = "type:varchar")
    private String status;

    /**
     * 連結組成 List<PwcInformationAppendixDTO>
     * type:List<PwcInformationAppendixDTO>
     */
    private List<PwcInformationAppendixDTO> upLoadUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long Id) {
        this.id = Id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public LocalDate getDownDate() {
        return downDate;
    }

    public void setDownDate(LocalDate downDate) {
        this.downDate = downDate;
    }

    public String getReleaseUnit() {
        return releaseUnit;
    }

    public void setReleaseUnit(String releaseUnit) {
        this.releaseUnit = releaseUnit;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactInformationTel() {
        return contactInformationTel;
    }

    public void setContactInformationTel(String contactInformationTel) {
        this.contactInformationTel = contactInformationTel;
    }

    public String getContactInformationEmail() {
        return contactInformationEmail;
    }

    public void setContactInformationEmail(String contactInformationEmail) {
        this.contactInformationEmail = contactInformationEmail;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    public Instant getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIsTop() {
        return isTop;
    }

    public void setIsTop(String isTop) {
        this.isTop = isTop;
    }

    public List<PwcSharedDTO> getJurisdictionRoles() {
        return jurisdictionRoles;
    }

    public void setJurisdictionRoles(List<PwcSharedDTO> jurisdictionRoles) {
        this.jurisdictionRoles = jurisdictionRoles;
    }

    public List<PwcSharedDTO> getModuleRoles() {
        return moduleRoles;
    }

    public void setModuleRoles(List<PwcSharedDTO> moduleRoles) {
        this.moduleRoles = moduleRoles;
    }

    public List<String> getInputUrlText() {
        return inputUrlText;
    }

    public void setInputUrlText(List<String> inputUrlText) {
        this.inputUrlText = inputUrlText;
    }

    public List<String> getInputUrlAdder() {
        return inputUrlAdder;
    }

    public void setInputUrlAdder(List<String> inputUrlAdder) {
        this.inputUrlAdder = inputUrlAdder;
    }

    public List<String> getInputFileText() {
        return inputFileText;
    }

    public void setInputFileText(List<String> inputFileText) {
        this.inputFileText = inputFileText;
    }

    public List<MultipartFile> getInputFile() {
        return inputFile;
    }

    public void setInputFile(List<MultipartFile> inputFile) {
        this.inputFile = inputFile;
    }

    public List<String> getLocationListDisplay() {
        return locationListDisplay;
    }

    public void setLocationListDisplay(List<String> locationListDisplay) {
        this.locationListDisplay = locationListDisplay;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<PwcInformationAppendixDTO> getUpLoadUrl() {
        return upLoadUrl;
    }

    public void setUpLoadUrl(List<PwcInformationAppendixDTO> upLoadUrl) {
        this.upLoadUrl = upLoadUrl;
    }

//    public List<PwcInformationAppendixDTO> getUpLoadFile() {
//        return upLoadFile;
//    }
//
//    public void setUpLoadFile(List<PwcInformationAppendixDTO> upLoadFile) {
//        this.upLoadFile = upLoadFile;
//    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BulletinDownAndInfoDTO {" +
                "id=" + getId() +
                ", subject='" + getSubject() + "'" +
                ", text='" + getText() + "'" +
                ", kind='" + getKind() + "'" +
                ", location='" + getLocation() + "'" +
                ", releaseDate='" + getReleaseDate() + "'" +
                ", downDate='" + getDownDate() + "'" +
                ", releaseUnit='" + getReleaseUnit() + "'" +
                ", contactPerson='" + getContactPerson() + "'" +
                ", contactInformationTel='" + getContactInformationTel() + "'" +
                ", contactInformationEmail='" + getContactInformationEmail() + "'" +
                ", enable='" + getEnable() + "'" +
                ", updateTime='" + getUpdateTime() + "'" +
                ", updateUser='" + getUpdateUser() + "'" +
                ", createTime='" + getCreateTime() + "'" +
                ", createUser='" + getCreateUser() + "'" +
                ", type='" + getType() + "'" +
                ", isTop='" + getIsTop() + "'" +
                ", jurisdictionRoles='" + getJurisdictionRoles() + "'" +
                ", moduleRoles='" + getModuleRoles() + "'" +
                ", inputUrlText='" + getInputUrlText() + "'" +
                ", inputUrlAdder='" + getInputUrlAdder() + "'" +
                ", inputFileText='" + getInputFileText() + "'" +
                ", inputFile='" + getInputFile() + "'" +
                ", locationListDisplay='" + getLocationListDisplay() + "'" +
                ", status='" + getStatus() + "'" +
                ", upLoadUrl='" + getUpLoadUrl() + "'" +
//                ", upLoadFile='" + getUpLoadFile() + "'" +
                "}";
    }
}
