package com.project.service;

import com.project.dto.request.EmployeeRequest;
import com.project.dto.response.EmployeeResponse;
import com.project.model.entity.Employee;

import java.util.List;

public interface EmployeeService {
    // DTO
    List<EmployeeResponse> findAll();
    List<EmployeeResponse> findEmployeesByRoleId(Long roleId);
    EmployeeResponse add(EmployeeRequest request);

    // ENTITY
    Employee findEntityById(Long id);
}
