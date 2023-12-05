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
    private String type;
    private Long courseId;
    private String courseName;
    private Long classroomId;
    private String classroomName;
    private Long studentId;
    private String studentName;
    private Long createdUserId;
}
