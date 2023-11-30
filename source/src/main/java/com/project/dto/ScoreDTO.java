package com.project.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScoreDTO {
    private Long id;
    private Double score;
    private String note;
    private SubjectDTO subject;
    private ClassroomDTO classroom;
    private StudentDTO student;
    private EmployeeDTO createdBy;
}
