package com.project.service.impl;

import com.project.constant.Constant;
import com.project.dto.ClassroomDTO;
import com.project.enums.MessageCodeEnum;
import com.project.model.entity.Classroom;
import com.project.model.entity.Employee;
import com.project.model.mapstruct.ClassroomMapstruct;
import com.project.repository.ClassroomRepository;
import com.project.repository.EmployeeRepository;
import com.project.service.ClassroomService;
import com.project.utils.CommonMethods;
import com.project.utils.ExceptionUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j2
public class ClassroomServiceImpl implements ClassroomService {

    @Autowired
    private ClassroomRepository classroomRepository;

    @Autowired
    @Lazy
    private EmployeeRepository employeeRepository;

    @Override
    @Transactional
    public ClassroomDTO add(ClassroomDTO dto) {
        Classroom classroom = ClassroomMapstruct.toEntity(dto);

        if (ObjectUtils.isEmpty(dto.getTeacherId())) {
            log.error("Create Classroom Error. Classroom must not have a Teacher");
            ExceptionUtil.throwCustomException(MessageCodeEnum.ROLE_IS_NULL);
        }

        Employee employee = employeeRepository.findById(dto.getTeacherId()).orElse(null);
        if (employee == null) {
            log.error("Create Classroom Error. Cannot find Teacher with ID: {}", dto.getTeacherId());
            ExceptionUtil.throwCustomException(MessageCodeEnum.DATA_NOT_FOUND);
        }

        if (!Constant.TEACHER_ROLE.equalsIgnoreCase(employee.getRole().getType())) {
            log.error("Create Classroom Error. Employee must be Teacher: {}", dto.getTeacherId());
            ExceptionUtil.throwCustomException(MessageCodeEnum.ROLE_NOT_ACCEPT);
        }
        classroom.setTeacher(employee);
        classroom.setCode(CommonMethods.randomCode(classroom.getGrade()));

        return ClassroomMapstruct.toDTO(classroomRepository.save(classroom));
    }
}
