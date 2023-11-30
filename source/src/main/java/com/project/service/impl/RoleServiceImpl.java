package com.project.service.impl;

import com.project.constant.Constant;
import com.project.dto.EmployeeDTO;
import com.project.dto.RoleDTO;
import com.project.dto.StudentDTO;
import com.project.model.entity.Employee;
import com.project.model.entity.Role;
import com.project.model.entity.Student;
import com.project.model.mapstruct.EmployeeMapstruct;
import com.project.model.mapstruct.RoleMapstruct;
import com.project.model.mapstruct.StudentMapstruct;
import com.project.repository.EmployeeRepository;
import com.project.repository.RoleRepository;
import com.project.repository.StudentRepository;
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
    public RoleDTO detail(Long id) {
        Role role = roleRepository.findById(id).orElse(null);
        if (role == null) {
            return null;
        }

        RoleDTO dto = RoleMapstruct.toDTO(role);
        if (Constant.STUDENT_ROLE.equalsIgnoreCase(dto.getCode())) {
            List<Student> students = studentRepository.findAll();
            dto.setStudents(students.stream().map(StudentMapstruct::toDTO).collect(Collectors.toList()));
        } else {
            List<Employee> employees = employeeRepository.findEmployeesByRoleId(dto.getId()).orElse(ListUtil.emptyList());
            dto.setEmployees(employees.stream().map(EmployeeMapstruct::toDTO).collect(Collectors.toList()));
        }
        return dto;
    }
}
