package com.project.service.impl;

import com.project.dto.TimetableDTO;
import com.project.enums.DateEnum;
import com.project.enums.MessageCodeEnum;
import com.project.model.entity.Course;
import com.project.model.entity.CourseTime;
import com.project.model.entity.Employee;
import com.project.model.entity.Timetable;
import com.project.model.mapstruct.TimetableMapstruct;
import com.project.repository.TimetableRepository;
import com.project.service.TimetableService;
import com.project.utils.DateUtil;
import com.project.utils.ExceptionUtil;
import com.project.utils.ListUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        Employee teacher = course.getTeacher();
        Long teacherId = teacher.getId();
        String teacherName = teacher.getFullName();

        List<Timetable> timetables = timetableRepository.findByTeacherId(teacherId).orElse(ListUtil.emptyList());
        for (CourseTime courseTime : courseTimes) {
            String weekDay = courseTime.getWeekDay();
            String shift = courseTime.getShift();

            if (!ObjectUtils.isEmpty(timetables)) {
                Timetable timetable = timetables.stream()
                        .filter(item -> item.getShift().equalsIgnoreCase(shift) && item.getWeekDay().equalsIgnoreCase(weekDay))
                        .findFirst().orElse(null);
                if (timetable != null) {
                    continue;
                }
            }

            for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
                if (date.getDayOfWeek().getValue() == DateEnum.findByDateString(weekDay).getDateInt()) {
                    Timetable timetable = new Timetable();
                    timetable.setShift(shift);
                    timetable.setWeekDay(weekDay);
                    timetable.setClassroomName(classroomName);
                    timetable.setTeacherId(teacherId);
                    timetable.setTeacherName(teacherName);
                    timetable.setCourseTime(courseTime);
                    timetable.setImplementDate(DateUtil.convertLocalDatetoDate(date));

                    timetableRepository.save(timetable);
                    count++;
                }
            }
        }
        log.info("End generateTimetable for Course: {}, TOTAL: {}", course.getCode(), count);
    }

    @Override
    public List<TimetableDTO> findTeacherTimetable(Long teacherId) {
        List<Timetable> timetables = timetableRepository.findByTeacherId(teacherId).orElse(ListUtil.emptyList());
        if (CollectionUtils.isEmpty(timetables)) {
            log.info("Timetable not found with TeacherID: {}", teacherId);
            ExceptionUtil.throwCustomException(MessageCodeEnum.DATA_NOT_FOUND.getCode(), "Timetable not found with TeacherID: ".concat(teacherId.toString()));
        }
        return timetables.stream().map(TimetableMapstruct::toDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public int deleteTimetableByCourseTimeId(Long courseTimeId) {
        return timetableRepository.deleteTimetableByCourseTimeId(courseTimeId);
    }
}
