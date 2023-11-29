package com.project.service;

import com.project.dto.request.ClassroomRequest;
import com.project.dto.response.ClassroomResponse;

public interface ClassroomService {
    ClassroomResponse add(ClassroomRequest request);
}
