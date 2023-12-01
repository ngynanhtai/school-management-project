package com.project.model.mapstruct;

import com.project.dto.CourseTimeDTO;
import com.project.enums.MessageCodeEnum;
import com.project.model.entity.CourseTime;
import com.project.utils.ExceptionUtil;
import org.apache.commons.lang3.ObjectUtils;

public class CourseTimeMapstruct {
    public static CourseTime toEntity(CourseTimeDTO object) {
        if (ObjectUtils.isEmpty(object)) {
            ExceptionUtil.throwCustomException(MessageCodeEnum.CONVERT_DATA_ERROR);
        }
        return CourseTime
                .builder()
                .id(object.getId())
                .weekDay(object.getWeekDay())
                .shift(object.getShift())
                .build();
    }

    public static CourseTimeDTO toDTO(CourseTime object) {
        if (ObjectUtils.isEmpty(object)) {
            ExceptionUtil.throwCustomException(MessageCodeEnum.CONVERT_DATA_ERROR);
        }
        return CourseTimeDTO
                .builder()
                .id(object.getId())
                .weekDay(object.getWeekDay())
                .shift(object.getShift())
                .build();
    }
}
