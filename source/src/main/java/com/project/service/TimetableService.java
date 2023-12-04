package com.project.service;

import com.project.dto.TimetableDTO;
import com.project.model.entity.Course;

import java.util.List;

public interface TimetableService {
    void generateTimetable(Course course);
    List<TimetableDTO> findTeacherTimetable(Long teacherId);
    int deleteTimetableByCourseTimeId(Long courseTimeId);
}
