package com.project.service;

import com.project.dto.RoleDTO;
import com.project.model.entity.Role;

import java.util.List;

public interface RoleService {
    // DTO
    RoleDTO add(RoleDTO dto);
    List<RoleDTO> findAll();
    RoleDTO detail(Long id);

    // ENTITY
    Role findEntityById(Long id);
    Role findEntityByType(String type);
}
