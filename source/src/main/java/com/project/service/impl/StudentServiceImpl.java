package com.project.service.impl;

import com.project.constant.Constant;
import com.project.dto.StudentDTO;
import com.project.enums.MessageCodeEnum;
import com.project.model.entity.Role;
import com.project.model.entity.Student;
import com.project.model.mapstruct.StudentMapstruct;
import com.project.repository.StudentRepository;
import com.project.service.RoleService;
import com.project.service.StudentService;
import com.project.utils.CommonMethods;
import com.project.utils.ExceptionUtil;
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
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    @Lazy
    private RoleService roleService;

    @Override
    public List<StudentDTO> findAll() {
        List<Student> students = studentRepository.findAll();
        if(CollectionUtils.isEmpty(students))
            return ListUtil.emptyList();
        return students.stream().map(StudentMapstruct::toDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public StudentDTO add(StudentDTO dto) {
        Student student = StudentMapstruct.toEntity(dto);

        Role role = roleService.findEntityByType(Constant.STUDENT_ROLE);
        if (role == null) {
            log.error("Create Student Error. Cannot find role with ID: {}", dto.getRoleId());
            ExceptionUtil.throwCustomException(MessageCodeEnum.DATA_NOT_FOUND);
        }
        student.setRole(role);
        student.setCode(CommonMethods.randomCode(role.getType()));

        return StudentMapstruct.toDTO(studentRepository.save(student));
    }
}
