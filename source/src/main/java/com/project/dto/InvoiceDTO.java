package com.project.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.utils.DateUtil;
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
    @JsonFormat(pattern = DateUtil.DATE_HYPHEN)
    private Timestamp createdDate;
}
