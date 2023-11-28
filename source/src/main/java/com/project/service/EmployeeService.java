package com.project.service;

import com.project.dto.request.EmployeeRequest;
import com.project.dto.response.EmployeeResponse;

import java.util.List;

public interface EmployeeService {
    List<EmployeeResponse> findAll();
    List<EmployeeResponse> findEmployeesByRoleId(Long roleId);
    EmployeeResponse add(EmployeeRequest request);
}
