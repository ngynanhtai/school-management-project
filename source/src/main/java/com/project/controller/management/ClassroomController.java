package com.project.controller.management;

import com.project.dto.ClassroomDTO;
import com.project.dto.common.ResponseDTO;
import com.project.enums.MessageCodeEnum;
import com.project.service.ClassroomService;
import com.project.utils.ExceptionUtil;
import com.project.utils.ResponseUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system")
public class ClassroomController {

    @Autowired
    private ClassroomService classroomService;

    @PostMapping("/classroom")
    public ResponseEntity<ResponseDTO> createClassroom(@RequestBody ClassroomDTO dto) {
        if (ObjectUtils.isEmpty(dto)) {
            ExceptionUtil.throwCustomException(MessageCodeEnum.VALIDATION_REQUEST_NULL);
        }

        return ResponseUtil.buildSuccess(classroomService.add(dto));
    }

    @PutMapping("/classroom/{id}/assign-students")
    public ResponseEntity<ResponseDTO> assignStudents(@PathVariable("id") Long classroomId,
                                                      @RequestBody List<Long> studentIds) {
        if (CollectionUtils.isEmpty(studentIds)) {
            ExceptionUtil.throwCustomException(MessageCodeEnum.VALIDATION_REQUEST_NULL);
        }

        return ResponseUtil.buildSuccess(classroomService.assignStudents(classroomId, studentIds));
    }

    @GetMapping("/classroom/{id}/students")
    public ResponseEntity<ResponseDTO> findStudentsByClassroomId(@PathVariable("id") Long id) {
        return ResponseUtil.buildSuccess(classroomService.findStudentsByClassroomId(id));
    }
}
