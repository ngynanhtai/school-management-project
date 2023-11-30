package com.project.service.impl;

import com.project.constant.Constant;
import com.project.dto.ClassroomDTO;
import com.project.dto.StudentDTO;
import com.project.enums.MessageCodeEnum;
import com.project.model.entity.Classmate;
import com.project.model.entity.Classroom;
import com.project.model.entity.Employee;
import com.project.model.entity.Student;
import com.project.model.mapstruct.ClassroomMapstruct;
import com.project.model.mapstruct.StudentMapstruct;
import com.project.repository.ClassroomRepository;
import com.project.repository.EmployeeRepository;
import com.project.repository.StudentRepository;
import com.project.service.ClassroomService;
import com.project.utils.CommonMethods;
import com.project.utils.ExceptionUtil;
import com.project.utils.ListUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Log4j2
public class ClassroomServiceImpl implements ClassroomService {

    @Autowired
    private ClassroomRepository classroomRepository;

    @Autowired
    @Lazy
    private EmployeeRepository employeeRepository;

    @Autowired
    @Lazy
    private StudentRepository studentRepository;

    @Override
    @Transactional
    public ClassroomDTO add(ClassroomDTO dto) {
        Classroom classroom = ClassroomMapstruct.toEntity(dto);

        if (ObjectUtils.isEmpty(dto.getTeacherId())) {
            log.error("Create Classroom Error. Classroom must not have a Teacher");
            ExceptionUtil.throwCustomException(HttpStatus.SC_BAD_REQUEST, "Create Classroom Error. Classroom must not have a Teacher");
        }

        Employee employee = employeeRepository.findById(dto.getTeacherId()).orElse(null);
        if (employee == null) {
            log.error("Create Classroom Error. Cannot find Teacher with ID: {}", dto.getTeacherId());
            ExceptionUtil.throwCustomException(MessageCodeEnum.DATA_NOT_FOUND);
        }

        if (!Constant.TEACHER_ROLE.equalsIgnoreCase(employee.getRole().getType())) {
            log.error("Create Classroom Error. Employee must be Teacher: {}", dto.getTeacherId());
            ExceptionUtil.throwCustomException(HttpStatus.SC_BAD_REQUEST, "Create Classroom Error. Employee must be Teacher: ".concat(dto.getTeacherId().toString()));
        }
        classroom.setTeacher(employee);
        classroom.setCode(CommonMethods.randomCode(classroom.getGrade()));

        return ClassroomMapstruct.toDTO(classroomRepository.save(classroom));
    }

    @Override
    @Transactional
    public List<StudentDTO> assignStudents(Long classroomId, List<Long> studentIds) {
        Classroom classroom = classroomRepository.findById(classroomId).orElse(null);
        if (classroom == null) {
            log.info("Assign Students to Classroom Error. Cannot find Classroom with ID: {}", classroomId);
            ExceptionUtil.throwCustomException(MessageCodeEnum.DATA_NOT_FOUND);
        }

        List<Student> students = studentRepository.findStudentsByIds(studentIds).orElse(ListUtil.emptyList());
        if (students.isEmpty()) {
            log.info("Assign Students to Classroom Error. Cannot find any Student with IDs: {}", studentIds);
            ExceptionUtil.throwCustomException(HttpStatus.SC_BAD_REQUEST, "Assign Students to Classroom Error. Cannot find any Student with IDs: ".concat(studentIds.toString()));
        }

        Set<Classmate> classmates = new HashSet<>();
        for (Student student : students) {
            Classmate classmate = new Classmate();
            classmate.setClassroom(classroom);
            classmate.setStudent(student);

            classmates.add(classmate);
        }

        classroom.setClassmates(classmates);

        return students.stream().map(StudentMapstruct::toDTO).collect(Collectors.toList());
    }
}
