package com.project.service;

import com.project.dto.request.ClassroomRequest;
import com.project.dto.ClassroomDTO;

public interface ClassroomService {
    ClassroomDTO add(ClassroomRequest request);
}
