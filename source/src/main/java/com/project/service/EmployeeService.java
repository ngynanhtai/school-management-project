package com.project.service;

import com.project.dto.EmployeeDTO;
import com.project.model.entity.Employee;

import java.util.List;

public interface EmployeeService {
    // DTO
    List<EmployeeDTO> findAll();
    List<EmployeeDTO> findEmployeesByRoleId(Long roleId);
    EmployeeDTO add(EmployeeDTO dto);

    // ENTITY
    Employee findEntityById(Long id);
}
