package gov.pcc.pwc.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EngCourseDtlPK implements Serializable {
    private String engCourseId;
    private Long engCourseDetailId;
}
