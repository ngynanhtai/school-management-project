package com.project.model.mapstruct;

import com.project.dto.SubjectDTO;
import com.project.enums.MessageCodeEnum;
import com.project.model.entity.Course;
import com.project.model.entity.Subject;
import com.project.utils.ExceptionUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.util.CollectionUtils;

import java.util.Set;

public class SubjectMapstruct {
    public static Subject toEntity(SubjectDTO object) {
        if (ObjectUtils.isEmpty(object)) {
            ExceptionUtil.throwCustomException(MessageCodeEnum.CONVERT_DATA_ERROR);
        }
        return Subject
                .builder()
                .id(object.getId())
                .code(object.getCode())
                .name(object.getName())
                .type(object.getType())
                .build();
    }

    public static SubjectDTO toDTO(Subject object) {
        if (ObjectUtils.isEmpty(object)) {
            ExceptionUtil.throwCustomException(MessageCodeEnum.CONVERT_DATA_ERROR);
        }

        Set<Course> courses = object.getCourses();
        return SubjectDTO
                .builder()
                .id(object.getId())
                .code(object.getCode())
                .name(object.getName())
                .type(object.getType())
                .totalOpen(!CollectionUtils.isEmpty(courses) ? object.getCourses().stream().filter(Course::isActiveStatus).count() : 0)
                .build();
    }
}
