package com.project.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseTimeDTO {
    private Long id;
    private String shift;
    private String weekDay;
}
