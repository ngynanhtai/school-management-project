package com.project.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClassroomDTO {
    private Long id;
    private String code;
    private String name;
    private String grade;
    private int total;
    private String type;
    private String year;
    private Long homeTeacherId;
}
