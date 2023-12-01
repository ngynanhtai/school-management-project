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
    private Long id;
    private String code;
    private String name;
    private int cycle;
    private Double fee;
    private Timestamp createdDate;
    private Long subjectId;
    private String subjectCode;
    private String subjectName;
    private List<CourseTimeDTO> courseTimes;
}
