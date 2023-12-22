package com.project.security;

import com.project.dto.common.UserDTO;
import com.project.enums.MessageCodeEnum;
import com.project.model.entity.Employee;
import com.project.model.entity.Student;
import com.project.repository.EmployeeRepository;
import com.project.repository.StudentRepository;
import com.project.utils.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    @Lazy
    private StudentRepository studentRepository;

    @Autowired
    @Lazy
    private EmployeeRepository employeeRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student student = studentRepository.findOneByEmail(username).orElse(null);
        if (student != null) {
            UserDTO user = UserDTO
                    .builder()
                    .id(student.getId())
                    .userCode(student.getCode())
                    .fullName(student.getFullName())
                    .password(student.getPassword())
                    .email(student.getEmail())
                    .roleType(student.getRole().getType())
                    .build();
            return CustomUserDetails.build(user);
        }

        Employee employee = employeeRepository.findOneByEmail(username).orElse(null);
        if (employee != null) {
            UserDTO user = UserDTO
                    .builder()
                    .id(employee.getId())
                    .userCode(employee.getCode())
                    .fullName(employee.getFullName())
                    .password(employee.getPassword())
                    .email(employee.getEmail())
                    .roleType(employee.getRole().getType())
                    .build();
            return CustomUserDetails.build(user);
        }
        ExceptionUtil.throwCustomException(MessageCodeEnum.DATA_NOT_FOUND.getCode(), "User not found.");
        return null;
    }

    public static void main(String[] args) {
        String pass = "$2a$10$Q5WLWY1JIqo4Q4QxFRr/COdESVw37Y9uRSlhxRR4J6EL5SGBxfqX6";
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.matches("123456", pass));
    }
}
