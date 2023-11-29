package com.project.service.impl;

import com.project.constant.Constant;
import com.project.dto.request.RoleRequest;
import com.project.dto.response.EmployeeResponse;
import com.project.dto.response.RoleResponse;
import com.project.dto.response.StudentResponse;
import com.project.model.entity.Role;
import com.project.model.mapstruct.RoleMapstruct;
import com.project.repository.RoleRepository;
import com.project.service.EmployeeService;
import com.project.service.RoleService;
import com.project.service.StudentService;
import com.project.utils.ListUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    @Lazy
    private StudentService studentService;

    @Autowired
    @Lazy
    private EmployeeService employeeService;

    @Override
    @Transactional
    public RoleResponse add(RoleRequest request) {
        return RoleMapstruct.toDTO(roleRepository.save(RoleMapstruct.toEntity(request)));
    }

    @Override
    public List<RoleResponse> findAll() {
        List<Role> roles = roleRepository.findAll();
        if(CollectionUtils.isEmpty(roles))
            return ListUtil.emptyList();
        return roles.stream().map(RoleMapstruct::toDTO).collect(Collectors.toList());
    }

    @Override
    public RoleResponse detail(Long id) {
        Role role = roleRepository.findById(id).orElse(null);
        if (role == null) {
            return null;
        }

        RoleResponse dto = RoleMapstruct.toDTO(role);
        if (Constant.STUDENT_ROLE.equalsIgnoreCase(dto.getCode())) {
            List<StudentResponse> students = studentService.findAll();
            dto.setStudents(students);
        } else {
            List<EmployeeResponse> employees = employeeService.findEmployeesByRoleId(dto.getId());
            dto.setEmployees(employees);
        }
        return dto;
    }

    @Override
    public Role findEntityById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    @Override
    public Role findEntityByType(String type) {
        return roleRepository.findByType(type);
    }
}
