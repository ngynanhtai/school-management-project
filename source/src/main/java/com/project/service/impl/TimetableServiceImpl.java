package com.project.service.impl;

import com.project.enums.DateEnum;
import com.project.model.entity.Course;
import com.project.model.entity.CourseTime;
import com.project.model.entity.Timetable;
import com.project.repository.TimetableRepository;
import com.project.service.TimetableService;
import com.project.utils.DateUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Set;

@Service
@Log4j2
public class TimetableServiceImpl implements TimetableService {
    @Autowired
    private TimetableRepository timetableRepository;


    @Override
    @Transactional
    public void generateTimetable(Course course) {
        log.info("Start generateTimetable for Course: {}", course.getCode());

        int count = 0;

        Set<CourseTime> courseTimes = course.getCourseTimes();
        LocalDate startDate = DateUtil.convertDatetoLocalDate(course.getStartDate());
        int cycle = course.getCycle();
        LocalDate endDate = startDate.plusDays(cycle);

        String classroomName = course.getClassroom().getName();
        Long teacherId = course.getTeacher().getId();

        for (CourseTime courseTime : courseTimes) {
            String weekDay = courseTime.getWeekDay();
            for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
                if (date.getDayOfWeek().getValue() == DateEnum.findByDateString(weekDay).getDateInt()) {
                    Timetable timetable = new Timetable();
                    timetable.setShift(courseTime.getShift());
                    timetable.setClassroomName(classroomName);
                    timetable.setTeacherId(teacherId);
                    timetable.setImplementDate(DateUtil.convertLocalDatetoDate(date));

                    timetableRepository.save(timetable);
                    count++;
                }
            }
        }

        log.info("End generateTimetable for Course: {}, TOTAL: {}", course.getCode(), count);
    }
}
