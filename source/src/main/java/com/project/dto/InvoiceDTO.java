package com.project.dto;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvoiceDTO {
    private Long id;
    private String source;
    private String note;
    private Double moneyAmount;
    private StudentDTO payer;
    private EmployeeDTO collector;
    private Timestamp createdDate;
}
