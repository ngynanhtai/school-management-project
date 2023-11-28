package com.project.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleResponse {
    private Long id;
    private String code;
    private String type;
    private String name;
    private List<StudentResponse> students;
    private List<EmployeeResponse> employees;
}
