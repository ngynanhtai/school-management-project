package com.project.dto;

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
    private Timestamp createdDate;
    private List<BorrowEquipmentDTO> borrowEquipments;
}
