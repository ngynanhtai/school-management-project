package com.project.service;

import com.project.dto.request.RoleRequest;
import com.project.dto.response.RoleResponse;
import com.project.model.entity.Role;

import java.util.List;

public interface RoleService {
    RoleResponse add(RoleRequest request);
    List<RoleResponse> findAll();
    RoleResponse detail(Long id);
    Role findEntityById(Long id);
    Role findEntityByType(String type);
}
