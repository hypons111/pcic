package gov.pcc.pwc.domain;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EngCourseDtlTeacherPK implements Serializable {
    private String engCourseId;
    private Long engCourseDetailId;
    private Long engCourseDetailTeacherId;
}
