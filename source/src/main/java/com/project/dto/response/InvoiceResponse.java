package com.project.dto.response;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvoiceResponse {
    private Long id;
    private String source;
    private String note;
    private Double moneyAmount;
    private StudentResponse payer;
    private EmployeeResponse collector;
    private Timestamp createdDate;
}
