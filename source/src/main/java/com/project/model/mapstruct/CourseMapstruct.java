package com.project.model.mapstruct;

import com.project.dto.CourseDTO;
import com.project.dto.CourseTimeDTO;
import com.project.enums.MessageCodeEnum;
import com.project.model.entity.*;
import com.project.utils.ExceptionUtil;
import com.project.utils.ListUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.util.CollectionUtils;

import java.util.Set;
import java.util.stream.Collectors;

public class CourseMapstruct {
    public static Course toEntity(CourseDTO object) {
        if (ObjectUtils.isEmpty(object)) {
            ExceptionUtil.throwCustomException(MessageCodeEnum.CONVERT_DATA_ERROR);
        }
        return Course
                .builder()
                .id(object.getId())
                .name(object.getName())
                .code(object.getCode())
                .cycle(object.getCycle())
                .fee(object.getFee())
                .startDate(object.getStartDate())
                .build();
    }

    public static CourseDTO toDTO(Course object) {
        if (ObjectUtils.isEmpty(object)) {
            ExceptionUtil.throwCustomException(MessageCodeEnum.CONVERT_DATA_ERROR);
        }

        Subject subject = object.getSubject();
        Employee teacher = object.getTeacher();
        Classroom classroom = object.getClassroom();
        Set<CourseTime> courseTimes = object.getCourseTimes();
        return CourseDTO
                .builder()
                .id(object.getId())
                .name(object.getName())
                .code(object.getCode())
                .cycle(object.getCycle())
                .fee(object.getFee())
                .createdDate(object.getCreatedDate())
                .startDate(object.getStartDate())
                .subjectId(subject.getId())
                .subjectName(subject.getName())
                .subjectCode(subject.getCode())
                .teacherId(teacher.getId())
                .teacherName(teacher.getFullName())
                .teacherCode(teacher.getCode())
                .classroomId(classroom.getId())
                .classroomName(classroom.getName())
                .classroomCode(classroom.getCode())
                .courseTimes(!CollectionUtils.isEmpty(courseTimes) ? courseTimes.stream().map(CourseTimeMapstruct::toDTO).collect(Collectors.toList()) : ListUtil.emptyList())
                .build();
    }
}
