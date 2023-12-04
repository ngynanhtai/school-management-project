package com.project.service.impl;

import com.project.dto.EmployeeDTO;
import com.project.dto.RoleDTO;
import com.project.enums.MessageCodeEnum;
import com.project.model.entity.Employee;
import com.project.model.entity.Role;
import com.project.model.mapstruct.EmployeeMapstruct;
import com.project.model.mapstruct.RoleMapstruct;
import com.project.repository.EmployeeRepository;
import com.project.repository.RoleRepository;
import com.project.repository.StudentRepository;
import com.project.service.RoleService;
import com.project.utils.ExceptionUtil;
import com.project.utils.ListUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Log4j2
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    @Lazy
    private StudentRepository studentRepository;

    @Autowired
    @Lazy
    private EmployeeRepository employeeRepository;

    @Override
    @Transactional
    public RoleDTO add(RoleDTO dto) {
        return RoleMapstruct.toDTO(roleRepository.save(RoleMapstruct.toEntity(dto)));
    }

    @Override
    public List<RoleDTO> findAll() {
        List<Role> roles = roleRepository.findAll();
        if(CollectionUtils.isEmpty(roles))
            return ListUtil.emptyList();
        return roles.stream().map(RoleMapstruct::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDTO> findEmployeeByRoleId(Long id) {
        Role role = roleRepository.findById(id).orElse(null);
        if (role == null) {
            log.error("Find Employee By RoleID Error. Role not found with ID: {}", id);
            ExceptionUtil.throwCustomException(MessageCodeEnum.DATA_NOT_FOUND.getCode(), "Role not found with ID: ".concat(id.toString()));
        }

        Set<Employee> employees = role.getEmployees();
        return employees.stream().map(EmployeeMapstruct::toDTO).collect(Collectors.toList());
    }
}
