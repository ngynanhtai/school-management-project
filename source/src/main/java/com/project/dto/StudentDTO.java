package com.project.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.utils.DateUtil;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDTO {
    private Long id;
    private String code;
    private String phoneNumber;
    //    private String password;
    private String fullName;
    private String nationalId;
    @JsonFormat(pattern = DateUtil.DATE_HYPHEN)
    private Date dob;
    private String email;
    private String gender;
    private String address;
    private String ward;
    private String district;
    private String province;
    private String firstParentName;
    private String firstParentPhone;
    private String firstParentRelation;
    private String secondParentName;
    private String secondParentPhone;
    private String secondParentRelation;
    private RoleDTO role;
    private List<ClassmateDTO> classmates;
    private List<InvoiceDTO> payerInvoices;
    private List<ScoreDTO> scores;
}
