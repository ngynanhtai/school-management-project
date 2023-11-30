package com.project.service.impl;

import com.project.constant.Constant;
import com.project.dto.EmployeeDTO;
import com.project.dto.RoleDTO;
import com.project.enums.MessageCodeEnum;
import com.project.model.entity.Employee;
import com.project.model.entity.Role;
import com.project.model.mapstruct.EmployeeMapstruct;
import com.project.model.mapstruct.RoleMapstruct;
import com.project.repository.EmployeeRepository;
import com.project.repository.RoleRepository;
import com.project.service.EmployeeService;
import com.project.utils.CommonMethods;
import com.project.utils.ExceptionUtil;
import com.project.utils.ListUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    @Lazy
    private RoleRepository roleRepository;

    @Override
    public List<EmployeeDTO> findAll() {
        List<Employee> employees = employeeRepository.findAll();
        if (CollectionUtils.isEmpty(employees)) {
            return ListUtil.emptyList();
        }
        return employees.stream().map(EmployeeMapstruct::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDTO> findEmployeesByRoleId(Long roleId) {
        List<Employee> employees = employeeRepository.findEmployeesByRoleId(roleId).orElse(null);
        if (employees == null) {
            return ListUtil.emptyList();
        }
        return employees.stream().map(EmployeeMapstruct::toDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public EmployeeDTO add(EmployeeDTO dto) {
        Employee employee = EmployeeMapstruct.toEntity(dto);

        if (ObjectUtils.isEmpty(dto.getRoleId())) {
            log.error("Create Employee Error. Employee must not have a Role");
            ExceptionUtil.throwCustomException(MessageCodeEnum.ROLE_IS_NULL);
        }

        Role role = roleRepository.findById(dto.getRoleId()).orElse(null);
        if (role == null) {
            log.error("Create Employee Error. Cannot find role with ID: {}", dto.getRoleId());
            ExceptionUtil.throwCustomException(MessageCodeEnum.DATA_NOT_FOUND);
        }

        if (Constant.STUDENT_ROLE.equalsIgnoreCase(role.getType())) {
            log.error("Create Employee Error. Employee must not be Student: {}", dto.getRoleId());
            ExceptionUtil.throwCustomException(MessageCodeEnum.ROLE_NOT_ACCEPT);
        }
        employee.setRole(role);
        employee.setCode(CommonMethods.randomCode(role.getType()));

        RoleDTO roleDTO = RoleMapstruct.toDTO(role);
        return EmployeeMapstruct.toDTO(employeeRepository.save(employee));
    }
}
