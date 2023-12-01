package com.project.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "course_time")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseTime {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "shift")
    private String shift;

    @Column(name = "week_day")
    private String weekDay;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Course course;
}
