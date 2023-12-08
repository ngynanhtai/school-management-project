package com.project.model.entity;

import com.project.utils.DateUtil;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
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

    @Column(name = "code", unique = true)
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "cycle")
    private int cycle; //days

    @Column(name = "fee")
    private Double fee;

    @Column(name = "deleted")
    private boolean deleted = false;

    @Column(name = "active_status")
    private boolean activeStatus = false;

    @Column(name = "start_date")
    @DateTimeFormat(pattern = DateUtil.DATE_HYPHEN)
    private Date startDate;

    @Column(name = "created_date", updatable = false)
    @CreationTimestamp
    private Timestamp createdDate;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "course",
            orphanRemoval = true)
    private Set<CourseTime> courseTimes;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "course",
            orphanRemoval = true)
    private Set<Score> scores;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    private Employee teacher;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "classroom_id", referencedColumnName = "id")
    private Classroom classroom;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    private Subject subject;
}
