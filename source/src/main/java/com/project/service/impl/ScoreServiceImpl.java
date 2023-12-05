package com.project.service.impl;

import com.project.dto.ScoreDTO;
import com.project.enums.MessageCodeEnum;
import com.project.enums.ScoreEnum;
import com.project.model.entity.Classroom;
import com.project.model.entity.Course;
import com.project.model.entity.Score;
import com.project.model.entity.Student;
import com.project.model.mapstruct.ScoreMapstruct;
import com.project.repository.ClassroomRepository;
import com.project.repository.CourseRepository;
import com.project.repository.ScoreRepository;
import com.project.repository.StudentRepository;
import com.project.service.ScoreService;
import com.project.utils.ExceptionUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j2
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    @Lazy
    private ClassroomRepository classroomRepository;

    @Autowired
    @Lazy
    private StudentRepository studentRepository;

    @Autowired
    @Lazy
    private CourseRepository courseRepository;

    @Override
    @Transactional
    public ScoreDTO assignScore(ScoreDTO dto) {
        Score score = ScoreMapstruct.toEntity(dto);
        Student student = studentRepository.findOneById(dto.getStudentId()).orElse(null);
        if (student == null) {
            log.error("Assign Course Error. Student not found with ID: {}", dto.getStudentId());
            ExceptionUtil.throwCustomException(MessageCodeEnum.DATA_NOT_FOUND.getCode(), "Student not found with ID: ".concat(dto.getStudentId().toString()));
        }

        Course course = courseRepository.findOneById(dto.getCourseId()).orElse(null);
        if (course == null) {
            log.error("Assign Course Error. Course not found with ID: {}", dto.getCourseId());
            ExceptionUtil.throwCustomException(MessageCodeEnum.DATA_NOT_FOUND.getCode(), "Course not found with ID: ".concat(dto.getCourseId().toString()));
        }

        if (!ScoreEnum.scoreEnumTypes().contains(dto.getType())) {
            ExceptionUtil.throwCustomException(MessageCodeEnum.ENUM_NOT_MATCH);
        }

        Classroom classroom = classroomRepository.findOneById(dto.getClassroomId()).orElse(null);
        if (classroom == null) {
            log.error("Assign Score Error. Classroom not found with ID: {}", dto.getClassroomId());
            ExceptionUtil.throwCustomException(MessageCodeEnum.DATA_NOT_FOUND.getCode(), "Classroom not found with ID: ".concat(dto.getClassroomId().toString()));
        }
//        score.setCreatedUserId(SecurityContextHolder.getContext().getAuthentication().getName());
        score.setClassroom(classroom);
        score.setStudent(student);
        score.setCourse(course);
        return ScoreMapstruct.toDTO(scoreRepository.save(score));
    }

    @Override
    @Transactional
    public void deleteScore(Long studentId, Long id) {
        Student student = studentRepository.findOneById(studentId).orElse(null);
        if (student == null) {
            log.error("Delete Score Error. Student not found with ID: {}", studentId);
            ExceptionUtil.throwCustomException(MessageCodeEnum.DATA_NOT_FOUND.getCode(), "Student not found with ID: ".concat(studentId.toString()));
        }

        Score score = scoreRepository.findById(id).orElse(null);
        if (score == null) {
            log.error("Delete Score Error. Score not found with ID: {}", studentId);
            ExceptionUtil.throwCustomException(MessageCodeEnum.DATA_NOT_FOUND.getCode(), "Score not found with ID: ".concat(studentId.toString()));
        }

        if (student.getScores().stream().anyMatch(item -> item.getId().equals(id))) {
            student.getScores().remove(score);
            return;
        }
        ExceptionUtil.throwCustomException(MessageCodeEnum.DATA_NOT_FOUND.getCode(), String.format("Score with ID: %d not belong to Student ID: %d", id, studentId));
    }
}
