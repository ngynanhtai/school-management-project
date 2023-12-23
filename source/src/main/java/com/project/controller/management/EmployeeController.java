package com.project.controller.management;

import com.project.dto.EmployeeDTO;
import com.project.dto.common.ResponseDTO;
import com.project.enums.MessageCodeEnum;
import com.project.service.EmployeeService;
import com.project.utils.ExceptionUtil;
import com.project.utils.ResponseUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/system")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/employee")
    public ResponseEntity<ResponseDTO> createEmployee(@RequestBody EmployeeDTO dto) {
        if (ObjectUtils.isEmpty(dto)) {
            ExceptionUtil.throwCustomException(MessageCodeEnum.VALIDATION_REQUEST_NULL);
        }

        return ResponseUtil.buildSuccess(employeeService.add(dto));
    }

    @GetMapping("/employee")
    public ResponseEntity<ResponseDTO> getEmployeesPagination(@RequestParam(value = "query", required = false) String query,
                                                              @RequestParam(value = "page") Integer page,
                                                              @RequestParam(value = "limit") Integer limit) {
        return ResponseUtil.buildSuccess(employeeService.findEmployeePagination(query, page, limit));
    }
}
