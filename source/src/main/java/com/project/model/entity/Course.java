package com.project.model.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "course")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Course {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "from_time")
    private String fromTime; //9:00, 10:00, 10:30 (Constant)

    @Column(name = "to_time")
    private String toTime;

    @Column(name = "period")
    private String period;

    @Column(name = "cycle")
    private int cycle; //days

    @Column(name = "fee")
    private Double fee;

    @Column(name = "created_date", updatable = false)
    @CreationTimestamp
    private Timestamp createdDate;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "course")
    private Set<ClassAllocation> classAllocations;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "weekdays_id", referencedColumnName = "id")
    private Weekdays weekdays;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    private Subject subject;
}
