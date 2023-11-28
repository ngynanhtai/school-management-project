package com.project.service.impl;

import com.project.dto.request.EmployeeRequest;
import com.project.dto.response.EmployeeResponse;
import com.project.enums.MessageCodeEnum;
import com.project.model.entity.Employee;
import com.project.model.entity.Role;
import com.project.model.mapstruct.EmployeeMapstruct;
import com.project.repository.EmployeeRepository;
import com.project.service.EmployeeService;
import com.project.service.RoleService;
import com.project.utils.CommonMethods;
import com.project.utils.ExceptionUtil;
import com.project.utils.ListUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
    private RoleService roleService;

    @Override
    public List<EmployeeResponse> findAll() {
        List<Employee> employees = employeeRepository.findAll();
        if (CollectionUtils.isEmpty(employees)) {
            return ListUtil.emptyList();
        }
        return employees.stream().map(EmployeeMapstruct::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<EmployeeResponse> findEmployeesByRoleId(Long roleId) {
        List<Employee> employees = employeeRepository.findEmployeesByRoleId(roleId).orElse(null);
        if (employees == null) {
            return ListUtil.emptyList();
        }
        return employees.stream().map(EmployeeMapstruct::toDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public EmployeeResponse add(EmployeeRequest request) {
        Employee employee = EmployeeMapstruct.toEntity(request);

        if (!ObjectUtils.isEmpty(request.getRoleId())) {
            Role role = roleService.findEntityById(request.getRoleId());
            if (role == null) {
                log.error("Create Employee Error. Cannot find role with ID: {}", request.getRoleId());
                ExceptionUtil.throwCustomException(MessageCodeEnum.DATA_NOT_FOUND);
            }
            employee.setRole(role);
            employee.setCode(CommonMethods.randomCode(role.getType()));
        }
        return EmployeeMapstruct.toDTO(employeeRepository.save(employee));
    }
}
