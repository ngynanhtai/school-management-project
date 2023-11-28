package com.project.dto.response;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClassAllocationResponse {
    private Long id;
    private ClassroomResponse classroom;
    private CourseResponse course;
    private Timestamp createdDate;
}
