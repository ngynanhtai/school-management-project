package com.project.service;

import com.project.dto.ClassroomDTO;
import com.project.dto.StudentDTO;

import java.util.List;

public interface ClassroomService {
    ClassroomDTO add(ClassroomDTO dto);
    List<StudentDTO> assignStudents(Long classroomId, List<Long> studentIds);
    List<StudentDTO> findStudentsByClassroomId(Long classroomId);
}
