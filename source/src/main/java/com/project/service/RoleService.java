package com.project.service;

import com.project.dto.request.RoleRequest;
import com.project.dto.RoleDTO;
import com.project.model.entity.Role;

import java.util.List;

public interface RoleService {
    // DTO
    RoleDTO add(RoleRequest request);
    List<RoleDTO> findAll();
    RoleDTO detail(Long id);

    // ENTITY
    Role findEntityById(Long id);
    Role findEntityByType(String type);
}
