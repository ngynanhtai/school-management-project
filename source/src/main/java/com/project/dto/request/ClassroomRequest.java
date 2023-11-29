package com.project.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClassroomRequest {
    private Long id;
    private String code;
    private String name;
    private String grade;
    private int total;
    private String type;
    private String year;
    private Long teacherId;
}
