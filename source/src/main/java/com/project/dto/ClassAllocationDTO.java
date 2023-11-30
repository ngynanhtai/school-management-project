package com.project.dto;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClassAllocationDTO {
    private Long id;
    private ClassroomDTO classroom;
    private CourseDTO course;
    private Timestamp createdDate;
}
