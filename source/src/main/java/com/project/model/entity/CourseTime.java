package com.project.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

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

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "courseTime",
            orphanRemoval = true)
    private Set<Timetable> timetables;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Course course;
}
