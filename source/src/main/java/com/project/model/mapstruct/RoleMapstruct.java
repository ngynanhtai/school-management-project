package com.project.model.mapstruct;

import com.project.dto.request.RoleRequest;
import com.project.dto.RoleDTO;
import com.project.enums.MessageCodeEnum;
import com.project.model.entity.Role;
import com.project.utils.ExceptionUtil;
import org.apache.commons.lang3.ObjectUtils;

public class RoleMapstruct {
    public static Role toEntity(RoleRequest object) {
        if (ObjectUtils.isEmpty(object)) {
            ExceptionUtil.throwCustomException(MessageCodeEnum.CONVERT_DATA_ERROR);
        }
        return Role
                .builder()
                .id(object.getId())
                .code(object.getCode())
                .type(object.getType())
                .name(object.getName())
                .build();
    }

    public static RoleDTO toDTO(Role object) {
        if (ObjectUtils.isEmpty(object)) {
            ExceptionUtil.throwCustomException(MessageCodeEnum.CONVERT_DATA_ERROR);
        }
        return RoleDTO
                .builder()
                .id(object.getId())
                .code(object.getCode())
                .type(object.getType())
                .name(object.getName())
                .build();
    }
}
