package com.project.model.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "meal")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Meal {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "main_dish")
    private String mainDish;

    @Column(name = "side_dish")
    private String sideDish;

    @Column(name = "dessert")
    private String dessert;

    //Constant: BREAKFAST, LUNCH, SNACK
    @Column(name = "period")
    private String period;

    @Column(name = "implement_date")
    private Timestamp implementDate;

    @Column(name = "created_date", updatable = false)
    @CreationTimestamp
    private Timestamp createdDate;

    @Column(name = "created_user_id")
    private Long createdUserId;

    @Column(name = "assigned_to_id")
    private Long assignedToId;
}
