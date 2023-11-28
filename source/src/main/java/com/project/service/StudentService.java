package com.project.service;

import com.project.dto.request.StudentRequest;
import com.project.dto.response.StudentResponse;

import java.util.List;

public interface StudentService {
    List<StudentResponse> findAll();
    StudentResponse add(StudentRequest request);
}
