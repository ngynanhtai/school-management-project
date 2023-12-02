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
public class MealDTO {
    private Long id;
    private String mainDish;
    private String sideDish;
    private String dessert;
    private String period;
    @JsonFormat(pattern = DateUtil.DATE_HYPHEN)
    private Timestamp implementDate;
    @JsonFormat(pattern = DateUtil.DATE_HYPHEN)
    private Timestamp createdDate;
    private EmployeeDTO createdBy;
    private EmployeeDTO assignedTo;
}
