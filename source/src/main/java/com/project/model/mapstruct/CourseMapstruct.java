package com.project.model.mapstruct;

import com.project.dto.CourseDTO;
import com.project.enums.MessageCodeEnum;
import com.project.model.entity.Course;
import com.project.model.entity.Subject;
import com.project.utils.ExceptionUtil;
import org.apache.commons.lang3.ObjectUtils;

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
                .build();
    }

    public static CourseDTO toDTO(Course object) {
        if (ObjectUtils.isEmpty(object)) {
            ExceptionUtil.throwCustomException(MessageCodeEnum.CONVERT_DATA_ERROR);
        }

        Subject subject = object.getSubject();
        return CourseDTO
                .builder()
                .id(object.getId())
                .name(object.getName())
                .code(object.getCode())
                .cycle(object.getCycle())
                .fee(object.getFee())
                .createdDate(object.getCreatedDate())
                .subjectId(subject.getId())
                .subjectName(subject.getName())
                .subjectCode(subject.getCode())
                .build();
    }
}
