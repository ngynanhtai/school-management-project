package com.project.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "classroom")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Classroom {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", unique = true)
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "grade")
    private String grade;

    //Constant: MAIN, SUB
    @Column(name = "type")
    private String type;

    @Column(name = "year")
    private String year;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "home_teacher_id", referencedColumnName = "id")
    private Employee homeTeacher;

    @Column(name = "deleted")
    private boolean deleted = false;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "classroom")
    private Set<ClassroomStudent> classroomStudents;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "classroom")
    private Set<Course> courses;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "classroom")
    private Set<Score> scores;
}
