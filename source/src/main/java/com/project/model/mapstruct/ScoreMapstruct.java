package com.project.model.mapstruct;

import com.project.dto.ScoreDTO;
import com.project.enums.MessageCodeEnum;
import com.project.model.entity.*;
import com.project.utils.ExceptionUtil;
import org.apache.commons.lang3.ObjectUtils;

public class ScoreMapstruct {
    public static Score toEntity(ScoreDTO object) {
        if (ObjectUtils.isEmpty(object)) {
            ExceptionUtil.throwCustomException(MessageCodeEnum.CONVERT_DATA_ERROR);
        }
        return Score
                .builder()
                .id(object.getId())
                .score(object.getScore())
                .note(object.getNote())
                .type(object.getType())
                .build();
    }

    public static ScoreDTO toDTO(Score object) {
        if (ObjectUtils.isEmpty(object)) {
            ExceptionUtil.throwCustomException(MessageCodeEnum.CONVERT_DATA_ERROR);
        }
        Course course = object.getCourse();
        Classroom classroom = object.getClassroom();
        Student student = object.getStudent();
        return ScoreDTO
                .builder()
                .id(object.getId())
                .score(object.getScore())
                .note(object.getNote())
                .type(object.getType())
                .courseId(course.getId())
                .courseName(course.getName())
                .classroomId(classroom.getId())
                .classroomName(classroom.getName())
                .studentId(student.getId())
                .studentName(student.getFullName())
                .createdUserId(object.getCreatedUserId())
                .build();
    }
}
