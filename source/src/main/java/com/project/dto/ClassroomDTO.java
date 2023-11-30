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
    private EmployeeDTO teacher;
    private List<ClassroomDTO> classmates;
    private List<BorrowEquipmentDTO> borrowEquipments;
    private List<ClassAllocationDTO> classAllocations;
    private List<ScoreDTO> scores;
}
