package com.project.model.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "expense")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "source")
    private String source;

    @Column(name = "note")
    private String note;

    @Column(name = "money_amount")
    private Double moneyAmount;

    @Column(name = "created_user_id")
    private Long createdUserId;

    @Column(name = "created_date", updatable = false)
    @CreationTimestamp
    private Timestamp createdDate;
}
