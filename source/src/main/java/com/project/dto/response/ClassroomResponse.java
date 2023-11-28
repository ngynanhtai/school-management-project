package com.project.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClassroomResponse {
    private Long id;
    private String code;
    private String name;
    private String grade;
    private int total;
    private String type;
    private String year;
    private EmployeeResponse teacher;
    private List<ClassmateResponse> classmates;
    private List<BorrowEquipmentResponse> borrowEquipments;
    private List<ClassAllocationResponse> classAllocations;
    private List<ScoreResponse> scores;
}
