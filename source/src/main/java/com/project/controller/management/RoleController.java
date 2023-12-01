package com.project.controller.management;

import com.project.dto.RoleDTO;
import com.project.dto.common.ResponseDTO;
import com.project.enums.MessageCodeEnum;
import com.project.service.RoleService;
import com.project.utils.ExceptionUtil;
import com.project.utils.ResponseUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/system")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/role")
    public ResponseEntity<ResponseDTO> createRole(@RequestBody RoleDTO dto) {
        if (ObjectUtils.isEmpty(dto)) {
            ExceptionUtil.throwCustomException(MessageCodeEnum.VALIDATION_REQUEST_NULL);
        }

        return ResponseUtil.buildSuccess(roleService.add(dto));
    }

    @GetMapping("/role")
    public ResponseEntity<ResponseDTO> findAllRoles() {
        return ResponseUtil.buildSuccess(roleService.findAll());
    }

    @GetMapping("/role/{id}/users")
    public ResponseEntity<ResponseDTO> findEmployeeByRoleId(@PathVariable("id") Long id) {
        return ResponseUtil.buildSuccess(roleService.findEmployeeByRoleId(id));
    }
}
