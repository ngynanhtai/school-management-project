package com.project.service;

import com.project.dto.request.StudentRequest;
import com.project.dto.StudentDTO;

import java.util.List;

public interface StudentService {
    List<StudentDTO> findAll();
    StudentDTO add(StudentRequest request);
}
