package com.project.dto.response;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExpenseResponse {
    private Long id;
    private String source;
    private String note;
    private Double moneyAmount;
    private EmployeeResponse createdBy;
    private Timestamp createdDate;
}
