package com.project.dto.response;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BorrowEquipmentResponse {
    private Long id;
    private Timestamp borrowingDate;
    private int returnedQuantity;
    private boolean isReturned;
    private EmployeeResponse borrower;
    private ClassroomResponse classroom;
    private ClassEquipmentResponse classEquipment;
}
