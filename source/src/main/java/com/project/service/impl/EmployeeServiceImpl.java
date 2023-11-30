package com.project.service.impl;

import com.project.constant.Constant;
import com.project.dto.request.EmployeeRequest;
import com.project.dto.EmployeeDTO;
import com.project.dto.RoleDTO;
import com.project.enums.MessageCodeEnum;
import com.project.model.entity.Employee;
import com.project.model.entity.Role;
import com.project.model.mapstruct.EmployeeMapstruct;
import com.project.model.mapstruct.RoleMapstruct;
import com.project.repository.EmployeeRepository;
import com.project.service.EmployeeService;
import com.project.service.RoleService;
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
    private RoleService roleService;

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
    public EmployeeDTO add(EmployeeRequest request) {
        Employee employee = EmployeeMapstruct.toEntity(request);

        if (ObjectUtils.isEmpty(request.getRoleId())) {
            log.error("Create Employee Error. Employee must not have a Role");
            ExceptionUtil.throwCustomException(MessageCodeEnum.ROLE_IS_NULL);
        }

        Role role = roleService.findEntityById(request.getRoleId());
        if (role == null) {
            log.error("Create Employee Error. Cannot find role with ID: {}", request.getRoleId());
            ExceptionUtil.throwCustomException(MessageCodeEnum.DATA_NOT_FOUND);
        }

        if (Constant.STUDENT_ROLE.equalsIgnoreCase(role.getType())) {
            log.error("Create Employee Error. Employee must not be Student: {}", request.getRoleId());
            ExceptionUtil.throwCustomException(MessageCodeEnum.ROLE_NOT_ACCEPT);
        }
        employee.setRole(role);
        employee.setCode(CommonMethods.randomCode(role.getType()));

        RoleDTO roleDTO = RoleMapstruct.toDTO(role);
        EmployeeDTO result = EmployeeMapstruct.toDTO(employeeRepository.save(employee));
        result.setRole(roleDTO);
        return result;
    }

    @Override
    public Employee findEntityById(Long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee == null) {
            ExceptionUtil.throwCustomException(MessageCodeEnum.DATA_NOT_FOUND);
        }
        return employee;
    }
}
