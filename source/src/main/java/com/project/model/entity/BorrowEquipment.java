package com.project.model.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "borrow_equipment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BorrowEquipment {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "borrowing_date")
    private Timestamp borrowingDate;

    @Column(name = "returned_quantity")
    private int returnedQuantity = 0;

    @Column(name = "is_returned")
    private boolean isReturned = false;

    @Column(name = "borrower_id")
    private Long borrowerId;

    @Column(name = "class_id")
    private Long classroomId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "class_equipment_id", referencedColumnName = "id")
    private ClassEquipment classEquipment;
}
