package com.project.service;

import com.project.dto.EmployeeDTO;
import com.project.dto.request.EmployeeRequest;
import com.project.model.entity.Employee;

import java.util.List;

public interface EmployeeService {
    // DTO
    List<EmployeeDTO> findAll();
    List<EmployeeDTO> findEmployeesByRoleId(Long roleId);
    EmployeeDTO add(EmployeeRequest request);

    // ENTITY
    Employee findEntityById(Long id);
}
