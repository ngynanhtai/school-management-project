package com.project.controller.management;

import com.project.dto.common.ResponseDTO;
import com.project.service.TimetableService;
import com.project.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/system")
public class TimetableController {
    @Autowired
    private TimetableService timetableService;

    @GetMapping("/timetable/teacher/{id}")
    public ResponseEntity<ResponseDTO> findTeacherTimetable(@PathVariable("id") Long id) {
        return ResponseUtil.buildSuccess(timetableService.findTeacherTimetable(id));
    }
}
