package com.project.dto.response;

import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseResponse {
    private String code;
    private String name;
    private String fromTime;
    private String toTime;
    private String period;
    private int cycle;
    private Double fee;
    private Timestamp createdDate;
    private List<ClassAllocationResponse> classAllocations;
    private WeekdaysResponse weekdays;
    private SubjectResponse subject;
}
