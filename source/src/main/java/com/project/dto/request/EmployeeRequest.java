package com.project.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.dto.response.*;
import com.project.utils.DateUtil;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeRequest {
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
    private String profession;
    private String maritalStatus;
    private Double salary;
    private Long roleId;
}