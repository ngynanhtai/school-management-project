package com.project.dto;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExpenseDTO {
    private Long id;
    private String source;
    private String note;
    private Double moneyAmount;
    private EmployeeDTO createdBy;
    private Timestamp createdDate;
}
