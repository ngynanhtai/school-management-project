package com.project.model.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "class_equipment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClassEquipment {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "total")
    private int total;

    @Column(name = "remain")
    private int remain;

    @Column(name = "created_date", updatable = false)
    @CreationTimestamp
    private Timestamp createdDate;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "classEquipment",
            orphanRemoval = true)
    private Set<BorrowEquipment> borrowEquipments;
}
