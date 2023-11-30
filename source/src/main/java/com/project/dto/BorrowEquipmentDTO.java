package com.project.dto;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BorrowEquipmentDTO {
    private Long id;
    private Timestamp borrowingDate;
    private int returnedQuantity;
    private boolean isReturned;
    private EmployeeDTO borrower;
    private ClassroomDTO classroom;
    private ClassEquipmentDTO classEquipment;
}
