package com.project.dto.request;

import com.project.dto.response.EmployeeResponse;
import com.project.dto.response.StudentResponse;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleRequest {
    private Long id;
    private String code;
    private String type;
    private String name;
}
