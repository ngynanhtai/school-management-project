package com.project.dto.response;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MealResponse {
    private Long id;
    private String mainDish;
    private String sideDish;
    private String dessert;
    private String period;
    private Timestamp implementDate;
    private Timestamp createdDate;
    private EmployeeResponse createdBy;
    private EmployeeResponse assignedTo;
}
