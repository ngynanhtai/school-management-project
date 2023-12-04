package com.project.model.mapstruct;

import com.project.dto.SubjectDTO;
import com.project.dto.TimetableDTO;
import com.project.enums.MessageCodeEnum;
import com.project.model.entity.Course;
import com.project.model.entity.Subject;
import com.project.model.entity.Timetable;
import com.project.utils.ExceptionUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.util.CollectionUtils;

import java.util.Set;

public class TimetableMapstruct {
    public static TimetableDTO toDTO(Timetable object) {
        if (ObjectUtils.isEmpty(object)) {
            ExceptionUtil.throwCustomException(MessageCodeEnum.CONVERT_DATA_ERROR);
        }
        return TimetableDTO
                .builder()
                .id(object.getId())
                .teacherId(object.getTeacherId())
                .teacherName(object.getTeacherName())
                .classroomName(object.getClassroomName())
                .shift(object.getShift())
                .weekDay(object.getWeekDay())
                .courseTimeId(object.getCourseTimeId())
                .implementDate(object.getImplementDate())
                .createdDate(object.getCreatedDate())
                .build();
    }
}
