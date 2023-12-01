package com.project.service;

import com.project.dto.EmployeeDTO;
import com.project.dto.RoleDTO;
import com.project.model.entity.Employee;

import java.util.List;

public interface RoleService {
    // DTO
    RoleDTO add(RoleDTO dto);
    List<RoleDTO> findAll();
    List<EmployeeDTO> findEmployeeByRoleId(Long id);
}
