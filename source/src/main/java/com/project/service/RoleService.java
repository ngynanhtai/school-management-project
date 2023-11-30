package com.project.service;

import com.project.dto.RoleDTO;

import java.util.List;

public interface RoleService {
    // DTO
    RoleDTO add(RoleDTO dto);
    List<RoleDTO> findAll();
    RoleDTO detail(Long id);
}
