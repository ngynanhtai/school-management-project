package com.project.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "class_mate")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Classmate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "class_id", referencedColumnName = "id")
    private Classroom classroom;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;
}
