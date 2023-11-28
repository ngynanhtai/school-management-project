package com.project.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScoreResponse {
    private Long id;
    private Double score;
    private String note;
    private SubjectResponse subject;
    private ClassroomResponse classroom;
    private StudentResponse student;
    private EmployeeResponse createdBy;
}
