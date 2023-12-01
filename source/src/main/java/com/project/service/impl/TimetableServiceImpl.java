package com.project.service.impl;

import com.project.dto.CourseDTO;
import com.project.model.entity.Course;
import com.project.model.entity.CourseTime;
import com.project.model.entity.Timetable;
import com.project.model.mapstruct.CourseMapstruct;
import com.project.repository.TimetableRepository;
import com.project.service.TimetableService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Log4j2
public class TimetableServiceImpl implements TimetableService {
    @Autowired
    private TimetableRepository timetableRepository;


    @Override
    @Transactional
    public void generateTimetable(CourseDTO dto) {
        Course course = CourseMapstruct.toEntity(dto);

        Set<CourseTime> courseTimes = course.getCourseTimes();
        for (CourseTime courseTime : courseTimes) {
            Timetable timetable = new Timetable();
            timetable.setShift(courseTime.getShift());
            timetable.setClassroomName(course.getClassroomCourses().);
        }
    }
}
