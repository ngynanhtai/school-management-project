package com.project.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.utils.DateUtil;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClassEquipmentDTO {
    private Long id;
    private String name;
    private int total;
    private int remain;
    @JsonFormat(pattern = DateUtil.DATE_HYPHEN)
    private Timestamp createdDate;
    private List<BorrowEquipmentDTO> borrowEquipments;
}
