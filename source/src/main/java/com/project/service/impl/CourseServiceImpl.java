package com.project.service.impl;

import com.project.dto.CourseDTO;
import com.project.dto.CourseTimeDTO;
import com.project.enums.MessageCodeEnum;
import com.project.model.entity.Course;
import com.project.model.entity.CourseTime;
import com.project.model.entity.Subject;
import com.project.model.mapstruct.CourseMapstruct;
import com.project.model.mapstruct.CourseTimeMapstruct;
import com.project.repository.CourseRepository;
import com.project.repository.SubjectRepository;
import com.project.service.CourseService;
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
            ExceptionUtil.throwCustomException(MessageCodeEnum.DATA_NOT_FOUND);
        }

        course.setSubject(subject);
        course.setCode(CommonMethods.randomCode(subject.getCode()));

        return CourseMapstruct.toDTO(courseRepository.save(course));
    }

    @Override
    public CourseDTO findById(Long id) {
        Course course = courseRepository.findById(id).orElse(null);
        if (course == null) {
            ExceptionUtil.throwCustomException(MessageCodeEnum.DATA_NOT_FOUND);
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
            log.error("Assign Time for Course Error. Cannot find Course with ID: {}", id);
            ExceptionUtil.throwCustomException(MessageCodeEnum.DATA_NOT_FOUND);
        }

        Set<CourseTime> courseTimes = course.getCourseTimes();
        for (CourseTimeDTO dto : courseTimeDTOList) {
            if (courseTimes.stream().anyMatch(item ->
                    item.getWeekDay().equalsIgnoreCase(dto.getWeekDay())
                            && item.getShift().equalsIgnoreCase(dto.getShift()))) {
                continue;
            }

            CourseTime courseTime = CourseTimeMapstruct.toEntity(dto);
            courseTime.setCourse(course);
            courseTimes.add(courseTime);
        }
        course.setCourseTimes(courseTimes);
        CourseDTO result = CourseMapstruct.toDTO(course);
        result.setCourseTimes(courseTimes.stream().map(CourseTimeMapstruct::toDTO).collect(Collectors.toList()));
        return result;
    }
}
