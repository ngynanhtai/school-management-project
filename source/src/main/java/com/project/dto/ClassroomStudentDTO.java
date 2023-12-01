package com.project.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClassroomStudentDTO {
    private Long id;
    private ClassroomDTO classroom;
    private StudentDTO student;
}
