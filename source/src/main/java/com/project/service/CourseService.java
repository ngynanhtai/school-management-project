package com.project.service;

import com.project.dto.CourseDTO;
import com.project.dto.CourseTimeDTO;

import java.util.List;

public interface CourseService {
    CourseDTO add(CourseDTO dto);
    CourseDTO findById(Long id);
    CourseDTO assignTimeForCourse(Long id, List<CourseTimeDTO> courseTimeDTOList);
}
