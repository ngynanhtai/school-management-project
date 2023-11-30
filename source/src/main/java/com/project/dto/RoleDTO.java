package com.project.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleDTO {
    private Long id;
    private String code;
    private String type;
    private String name;
    private List<StudentDTO> students;
    private List<EmployeeDTO> employees;
}
