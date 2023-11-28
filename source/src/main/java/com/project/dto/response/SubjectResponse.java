package com.project.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubjectResponse {
    private Long id;
    private String code;
    private String name;
    private String type;
    private int totalOpen;
    private List<CourseResponse> courses;
    private List<ScoreResponse> scores;
}
