package com.project.service.impl;

import com.project.dto.common.UserDTO;
import com.project.enums.MessageCodeEnum;
import com.project.model.entity.Employee;
import com.project.model.entity.Student;
import com.project.repository.EmployeeRepository;
import com.project.repository.StudentRepository;
import com.project.service.AuthService;
import com.project.utils.ExceptionUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class AuthServiceImpl implements AuthService {

    @Autowired
    @Lazy
    private StudentRepository studentRepository;

    @Autowired
    @Lazy
    private EmployeeRepository employeeRepository;

    @Override
    public UserDTO findUserByEmail(String email) {
        Student student = studentRepository.findOneByEmail(email).orElse(null);
        if (student != null) {
            return UserDTO
                    .builder()
                    .id(student.getId())
                    .userCode(student.getCode())
                    .password(student.getPassword())
                    .email(student.getEmail())
                    .build();
        }

        Employee employee = employeeRepository.findOneByEmail(email).orElse(null);
        if (employee != null) {
            return UserDTO
                    .builder()
                    .id(employee.getId())
                    .userCode(employee.getCode())
                    .password(employee.getPassword())
                    .email(employee.getEmail())
                    .build();
        }
        ExceptionUtil.throwCustomException(MessageCodeEnum.DATA_NOT_FOUND.getCode(), "User not found.");
        return null;
    }
}
