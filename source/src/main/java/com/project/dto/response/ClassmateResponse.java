package com.project.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClassmateResponse {
    private Long id;
    private ClassroomResponse classroom;
    private StudentResponse student;
}
