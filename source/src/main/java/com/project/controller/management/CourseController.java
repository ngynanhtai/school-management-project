package com.project.controller.management;

import com.project.dto.CourseDTO;
import com.project.dto.common.ResponseDTO;
import com.project.enums.MessageCodeEnum;
import com.project.service.CourseService;
import com.project.utils.ExceptionUtil;
import com.project.utils.ResponseUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/system")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/course")
    public ResponseEntity<ResponseDTO> createCourse(@RequestBody CourseDTO dto) {
        if (ObjectUtils.isEmpty(dto)) {
            ExceptionUtil.throwCustomException(MessageCodeEnum.VALIDATION_REQUEST_NULL);
        }

        return ResponseUtil.buildSuccess(courseService.add(dto));
    }

    @GetMapping("/course/{id}")
    public ResponseEntity<ResponseDTO> findById(@PathVariable("id") Long id) {
        return ResponseUtil.buildSuccess(courseService.findById(id));
    }

//    @PutMapping("/course/{id}/assign-time")
//    public ResponseEntity<ResponseDTO> assignTimeForCourse(@PathVariable("id") Long id,
//                                                           @RequestBody List<CourseTimeDTO> courseTimeDTOList) {
//        if (CollectionUtils.isEmpty(courseTimeDTOList)) {
//            ExceptionUtil.throwCustomException(MessageCodeEnum.VALIDATION_REQUEST_NULL);
//        }
//
//        return ResponseUtil.buildSuccess(courseService.assignTimeForCourse(id, courseTimeDTOList));
//    }
}
