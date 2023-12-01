package com.project.model.mapstruct;

import com.project.dto.ClassroomDTO;
import com.project.enums.MessageCodeEnum;
import com.project.model.entity.Classroom;
import com.project.utils.ExceptionUtil;
import org.apache.commons.lang3.ObjectUtils;

public class ClassroomMapstruct {
    public static Classroom toEntity(ClassroomDTO object) {
        if (ObjectUtils.isEmpty(object)) {
            ExceptionUtil.throwCustomException(MessageCodeEnum.CONVERT_DATA_ERROR);
        }
        return Classroom
                .builder()
                .id(object.getId())
                .code(object.getCode())
                .type(object.getType())
                .name(object.getName())
                .grade(object.getGrade())
                .total(object.getTotal())
                .type(object.getType())
                .year(object.getYear())
                .build();
    }

    public static ClassroomDTO toDTO(Classroom object) {
        if (ObjectUtils.isEmpty(object)) {
            ExceptionUtil.throwCustomException(MessageCodeEnum.CONVERT_DATA_ERROR);
        }
        return ClassroomDTO
                .builder()
                .id(object.getId())
                .code(object.getCode())
                .type(object.getType())
                .name(object.getName())
                .grade(object.getGrade())
                .total(object.getTotal())
                .type(object.getType())
                .year(object.getYear())
                .homeTeacherId(object.getId())
                .build();
    }
}
