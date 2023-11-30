package com.project.dto;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MealDTO {
    private Long id;
    private String mainDish;
    private String sideDish;
    private String dessert;
    private String period;
    private Timestamp implementDate;
    private Timestamp createdDate;
    private EmployeeDTO createdBy;
    private EmployeeDTO assignedTo;
}
