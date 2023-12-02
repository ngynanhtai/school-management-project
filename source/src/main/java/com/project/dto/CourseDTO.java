package com.project.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.utils.DateUtil;
import lombok.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseDTO {
    private Long id;
    private String code;
    private String name;
    private int cycle;
    private Double fee;
    private Timestamp createdDate;
    @JsonFormat(pattern = DateUtil.DATE_HYPHEN)
    private Date startDate;
    private Long subjectId;
    private String subjectCode;
    private String subjectName;
    private Long teacherId;
    private String teacherCode;
    private String teacherName;
    private Long classroomId;
    private String classroomCode;
    private String classroomName;
    private List<CourseTimeDTO> courseTimes;
}
