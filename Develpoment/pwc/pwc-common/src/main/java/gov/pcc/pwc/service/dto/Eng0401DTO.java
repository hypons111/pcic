package gov.pcc.pwc.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Eng0401DTO implements Serializable {
    private EngCourseDTO engCourseDTO;
    private List<EngCourseDtlDTO> engCourseDtlDTOList;
    private List<EngCourseDtlTeacherDTO> engCourseDtlTeacherDTOList;
    private List<EngTeacherDTO> engTeacherDTOList;
}
