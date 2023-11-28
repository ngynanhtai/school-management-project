package com.project.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "weekdays")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Weekdays {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "monday")
    private boolean monday = false;

    @Column(name = "tuesday")
    private boolean tuesday = false;

    @Column(name = "wednesday")
    private boolean wednesday = false;

    @Column(name = "thursday")
    private boolean thursday = false;

    @Column(name = "friday")
    private boolean friday = false;

    @Column(name = "saturday")
    private boolean saturday = false;

    @Column(name = "sunday")
    private boolean sunday = false;

    @Column(name = "active_status")
    private boolean activeStatus = false;

    @OneToOne(mappedBy = "weekdays")
    private Course course;
}
