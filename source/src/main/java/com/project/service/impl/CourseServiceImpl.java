package com.project.service.impl;

import com.project.dto.CourseDTO;
import com.project.dto.CourseTimeDTO;
import com.project.enums.MessageCodeEnum;
import com.project.model.entity.*;
import com.project.model.mapstruct.CourseMapstruct;
import com.project.model.mapstruct.CourseTimeMapstruct;
import com.project.repository.*;
import com.project.service.CourseService;
import com.project.service.TimetableService;
import com.project.utils.CommonMethods;
import com.project.utils.ExceptionUtil;
import com.project.utils.ListUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Log4j2
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    @Lazy
    private SubjectRepository subjectRepository;

    @Autowired
    @Lazy
    private EmployeeRepository employeeRepository;

    @Autowired
    @Lazy
    private ClassroomRepository classroomRepository;

    @Autowired
    @Lazy
    private CourseTimeRepository courseTimeRepository;

    @Autowired
    @Lazy
    private TimetableService timetableService;

    @Override
    @Transactional
    public CourseDTO add(CourseDTO dto) {
        Course course = CourseMapstruct.toEntity(dto);

        if (ObjectUtils.isEmpty(dto.getSubjectId())) {
            log.error("Create Course Error. Course must belong to one Subject");
            ExceptionUtil.throwCustomException(HttpStatus.SC_BAD_REQUEST, "Create Course Error. Course must belong to one Subject");
        }

        Subject subject = subjectRepository.findById(dto.getSubjectId()).orElse(null);
        if (subject == null) {
            log.error("Create Course Error. Subject not found with ID: {}", dto.getSubjectId());
            ExceptionUtil.throwCustomException(MessageCodeEnum.DATA_NOT_FOUND.getCode(), "Subject not found with ID: ".concat(dto.getSubjectId().toString()));
        }

        Employee teacher = employeeRepository.findById(dto.getTeacherId()).orElse(null);
        if (teacher == null) {
            log.error("Create Course Error. Teacher not found with ID: {}", dto.getTeacherId());
            ExceptionUtil.throwCustomException(MessageCodeEnum.DATA_NOT_FOUND.getCode(), "Teacher not found with ID: ".concat(dto.getTeacherId().toString()));
        }

        Classroom classroom = classroomRepository.findById(dto.getClassroomId()).orElse(null);
        if (classroom == null) {
            log.error("Create Course Error. Classroom not found with ID: {}", dto.getClassroomId());
            ExceptionUtil.throwCustomException(MessageCodeEnum.DATA_NOT_FOUND.getCode(), "Classroom not found with ID: ".concat(dto.getClassroomId().toString()));
        }

        if (classroom.getCourses().stream().anyMatch(item -> item.getSubject().equals(subject))) {
            log.error("Create Course Error. Classroom {} already had Course for Subject {}", classroom.getName(), subject.getName());
            ExceptionUtil.throwCustomException(MessageCodeEnum.CLASSROOM_COURSE_DUPLICATE.getCode(), "Classroom ".concat(classroom.getName()).concat(" already had Course for Subject ").concat(subject.getName()));
        }

        course.setSubject(subject);
        course.setTeacher(teacher);
        course.setClassroom(classroom);
        course.setCode(CommonMethods.randomCode(subject.getCode()));

        course = courseRepository.save(course);

        List<CourseTime> courseTimes = dto.getCourseTimes().stream().map(CourseTimeMapstruct::toEntity).collect(Collectors.toList());
        for (CourseTime courseTime : courseTimes) {
            courseTime.setCourse(course);
        }
        courseTimeRepository.saveAll(courseTimes);

        course.setCourseTimes(new HashSet<>(courseTimes));

        if (!ObjectUtils.isEmpty(course)) {
            timetableService.generateTimetable(course);
        }

        return CourseMapstruct.toDTO(course);
    }

    @Override
    public CourseDTO findById(Long id) {
        Course course = courseRepository.findById(id).orElse(null);
        if (course == null) {
            ExceptionUtil.throwCustomException(MessageCodeEnum.DATA_NOT_FOUND.getCode(), "Course not found with ID: ".concat(id.toString()));
        }
        CourseDTO result = CourseMapstruct.toDTO(course);
        Set<CourseTime> courseTimes = course.getCourseTimes();
        result.setCourseTimes(!CollectionUtils.isEmpty(courseTimes) ? courseTimes.stream().map(CourseTimeMapstruct::toDTO).collect(Collectors.toList()) : ListUtil.emptyList());
        return result;
    }

    @Override
    @Transactional
    public CourseDTO assignTimeForCourse(Long id, List<CourseTimeDTO> courseTimeDTOList) {
        Course course = courseRepository.findById(id).orElse(null);
        if (course == null) {
            log.error("Assign Time for Course Error. Course not found with ID: {}", id);
            ExceptionUtil.throwCustomException(MessageCodeEnum.DATA_NOT_FOUND.getCode(), "Course not found with ID: ".concat(id.toString()));
        }

        Set<CourseTime> courseTimes = course.getCourseTimes();
        int count = 0;
        for (CourseTimeDTO dto : courseTimeDTOList) {
            if (courseTimes.stream().anyMatch(item ->
                    item.getWeekDay().equalsIgnoreCase(dto.getWeekDay())
                            && item.getShift().equalsIgnoreCase(dto.getShift()))) {
                continue;
            }

            CourseTime courseTime = CourseTimeMapstruct.toEntity(dto);
            courseTime.setCourse(course);
            courseTimes.add(courseTime);
            count++;
        }
        course.setCourseTimes(courseTimes);

        if (count > 0) {
            timetableService.generateTimetable(course);
        }

        CourseDTO result = CourseMapstruct.toDTO(course);
        result.setCourseTimes(courseTimes.stream().map(CourseTimeMapstruct::toDTO).collect(Collectors.toList()));
        return result;
    }

    @Override
    @Transactional
    public void deleteCourseTimeForCourse(Long id, List<Long> courseTimesIds) {
        Course course = courseRepository.findById(id).orElse(null);
        if (course == null) {
            log.error("Delete Course Time for Course Error. Course not found with ID: {}", id);
            ExceptionUtil.throwCustomException(MessageCodeEnum.DATA_NOT_FOUND, "Course not found with ID: ".concat(id.toString()));
        }

        List<Long> entityIds = course.getCourseTimes().stream().map(CourseTime::getId).collect(Collectors.toList());
        for (Long courseTimeId : courseTimesIds) {
            if (!entityIds.contains(courseTimeId)) {
                ExceptionUtil.throwCustomException(MessageCodeEnum.DELETE_ERROR.getCode(), "Please provide List CourseTime that belongs to Course");
            }
            int count = timetableService.deleteTimetableByCourseTimeId(courseTimeId);
            if (count == 0) {
                ExceptionUtil.throwCustomException(MessageCodeEnum.DELETE_ERROR.getCode(), "Delete Timetable of CourseTime Error");
            }
            courseTimeRepository.removeById(courseTimeId);
        }
    }
}
