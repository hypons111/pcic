package gov.pcc.pwc.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.EngCourse} entity.
 */
@Data
public class EngCourseDTO implements Serializable {

    @NotNull
    @Size(max = 20)
    private String engCourseId;

    private LocalDate applyDate;

    @Size(max = 200)
    private String compId;

    @Size(max = 200)
    private String adminCompName;

    @Size(max = 600)
    private String courseName;

    private LocalDate dateStart;

    private LocalDate dateEnd;

    private LocalDate uploadDeadline;

    @Size(max = 100)
    private String place;

    @Size(max = 20)
    private String contactPerson;

    @Size(max = 200)
    private String contactTel;

    @Size(max = 50)
    private String contactEmail;

    @NotNull
    @Size(max = 3)
    private String status;

    @Size(max = 20)
    private String reviewStaff;

    private Instant reviewTime;

    @Size(max = 2000)
    private String reviewDesc;

    @Size(max = 500)
    private String webUrl;

    @Size(max = 20)
    private String docNo;

    @Size(max = 1)
    private String isNeedCheck;

    @Size(max = 1)
    private String isMedia;

    @Size(max = 1200)
    private String remark;

    @NotNull
    private Instant updateTime;

    @NotNull
    @Size(max = 20)
    private String updateUser;

    @NotNull
    private Instant createTime;

    @NotNull
    @Size(max = 20)
    private String createUser;

    @Size(max = 100)
    private String transferFrom;

    private Instant transferTime;

    private String compType;
    private String compName;
}
