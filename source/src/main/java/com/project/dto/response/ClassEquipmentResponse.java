package com.project.dto.response;

import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClassEquipmentResponse {
    private Long id;
    private String name;
    private int total;
    private int remain;
    private Timestamp createdDate;
    private List<BorrowEquipmentResponse> borrowEquipments;
}
