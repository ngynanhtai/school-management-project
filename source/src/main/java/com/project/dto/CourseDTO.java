package com.project.dto;

import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseDTO {
    private String code;
    private String name;
    private String fromTime;
    private String toTime;
    private String period;
    private int cycle;
    private Double fee;
    private Timestamp createdDate;
    private List<ClassroomCourseDTO> classroomCourses;
    private SubjectDTO subject;
}
