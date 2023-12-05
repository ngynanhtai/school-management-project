package com.project.service.impl;

import com.project.constant.Constant;
import com.project.dto.ClassroomDTO;
import com.project.dto.StudentDTO;
import com.project.enums.MessageCodeEnum;
import com.project.model.entity.ClassroomStudent;
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

        if (ObjectUtils.isEmpty(dto.getHomeTeacherId())) {
            log.error("Create Classroom Error. Classroom must not have a Teacher");
            ExceptionUtil.throwCustomException(HttpStatus.SC_BAD_REQUEST, "Create Classroom Error. Classroom must not have a Teacher");
        }

        Employee employee = employeeRepository.findOneById(dto.getHomeTeacherId()).orElse(null);
        if (employee == null) {
            log.error("Create Classroom Error. Teacher not found with ID: {}", dto.getHomeTeacherId());
            ExceptionUtil.throwCustomException(MessageCodeEnum.DATA_NOT_FOUND.getCode(), "Teacher not found with ID: ".concat(dto.getHomeTeacherId().toString()));
        }

        if (!Constant.TEACHER_ROLE.equalsIgnoreCase(employee.getRole().getType())) {
            log.error("Create Classroom Error. Employee must be Teacher: {}", dto.getHomeTeacherId());
            ExceptionUtil.throwCustomException(HttpStatus.SC_BAD_REQUEST, "Create Classroom Error. Employee must be Teacher: ".concat(dto.getHomeTeacherId().toString()));
        }
        classroom.setHomeTeacher(employee);
        classroom.setCode(CommonMethods.randomCode(classroom.getGrade()));

        return ClassroomMapstruct.toDTO(classroomRepository.save(classroom));
    }

    @Override
    @Transactional
    public List<StudentDTO> assignStudents(Long classroomId, List<Long> studentIds) {
        Classroom classroom = classroomRepository.findOneById(classroomId).orElse(null);
        if (classroom == null) {
            log.info("Assign Students to Classroom Error. Classroom not found with ID: {}", classroomId);
            ExceptionUtil.throwCustomException(MessageCodeEnum.DATA_NOT_FOUND.getCode(), "Classroom not found with ID: ".concat(classroomId.toString()));
        }

        List<Student> students = studentRepository.findStudentsByIds(studentIds).orElse(ListUtil.emptyList());
        if (students.isEmpty()) {
            log.info("Assign Students to Classroom Error. Cannot find any Student with IDs: {}", studentIds);
            ExceptionUtil.throwCustomException(MessageCodeEnum.DATA_NOT_FOUND.getCode(), "Cannot find any Student with IDs: ".concat(studentIds.toString()));
        }

        Set<ClassroomStudent> classroomStudents = classroom.getClassroomStudents();
        for (Student student : students) {
            if (classroomStudents.stream().anyMatch(item -> item.getStudent() == student)) {
                continue;
            }
            ClassroomStudent classroomStudent = new ClassroomStudent();
            classroomStudent.setClassroom(classroom);
            classroomStudent.setStudent(student);

            classroomStudents.add(classroomStudent);
        }

        classroom.setClassroomStudents(classroomStudents);

        return students.stream().map(StudentMapstruct::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<StudentDTO> findStudentsByClassroomId(Long id) {
        Classroom classroom = classroomRepository.findOneById(id).orElse(null);
        if (classroom == null) {
            log.info("Find Students By Classroom ID Error. Classroom not found with ID: {}", id);
            ExceptionUtil.throwCustomException(MessageCodeEnum.DATA_NOT_FOUND.getCode(), "Classroom not found with ID: ".concat(id.toString()));
        }

        Set<ClassroomStudent> classroomStudents = classroom.getClassroomStudents();
        List<Student> students = classroomStudents.stream().map(ClassroomStudent::getStudent).collect(Collectors.toList());

        return students.stream().map(StudentMapstruct::toDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteClassroom(Long id) {
        Classroom classroom = classroomRepository.findById(id).orElse(null);
        if (classroom == null) {
            log.info("Delete Classroom Error. Classroom not found with ID: {}", id);
            ExceptionUtil.throwCustomException(MessageCodeEnum.DATA_NOT_FOUND.getCode(), "Classroom not found with ID: ".concat(id.toString()));
        }
        classroom.setDeleted(true);
    }
}
