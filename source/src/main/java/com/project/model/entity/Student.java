package com.project.model.entity;

import com.project.utils.DateUtil;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "student")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "password")
    private String password;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "national_id")
    private String nationalId;

    @Column(name = "dob")
    @DateTimeFormat(pattern = DateUtil.DATE_HYPHEN)
    private Date dob;

    @Column(name = "email")
    private String email;

    @Column(name = "gender")
    private String gender;

    @Column(name = "address")
    private String address;

    @Column(name = "ward")
    private String ward;

    @Column(name = "district")
    private String district;

    @Column(name = "province")
    private String province;

    @Column(name = "first_parent_name")
    private String firstParentName;

    @Column(name = "first_parent_phone")
    private String firstParentPhone;

    @Column(name = "first_parent_relation")
    private String firstParentRelation;

    @Column(name = "second_parent_name")
    private String secondParentName;

    @Column(name = "second_parent_phone")
    private String secondParentPhone;

    @Column(name = "second_parent_relation")
    private String secondParentRelation;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;

//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "student")
//    private Set<ClassroomStudent> classroomStudents;
//
//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "payer")
//    private Set<Invoice> payerInvoices;
//
//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "student")
//    private Set<Score> scores;
}
