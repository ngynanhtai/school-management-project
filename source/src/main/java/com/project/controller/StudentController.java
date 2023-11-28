package com.project.controller;

import com.project.dto.common.ResponseDTO;
import com.project.dto.request.StudentRequest;
import com.project.enums.MessageCodeEnum;
import com.project.service.StudentService;
import com.project.utils.ExceptionUtil;
import com.project.utils.ResponseUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/student")
    public ResponseEntity<ResponseDTO> createStudent(@RequestBody StudentRequest request) {
        if (ObjectUtils.isEmpty(request)) {
            ExceptionUtil.throwCustomException(MessageCodeEnum.VALIDATION_REQUEST_NULL);
        }

        return ResponseUtil.buildSuccess(studentService.add(request));
    }
}
