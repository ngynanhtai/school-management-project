package com.project.model.mapstruct;

import com.project.dto.request.EmployeeRequest;
import com.project.dto.response.EmployeeResponse;
import com.project.enums.MessageCodeEnum;
import com.project.model.entity.Employee;
import com.project.utils.ExceptionUtil;
import org.apache.commons.lang3.ObjectUtils;

public class EmployeeMapstruct {
    public static Employee toEntity(EmployeeRequest object) {
        if (ObjectUtils.isEmpty(object)) {
            ExceptionUtil.throwCustomException(MessageCodeEnum.CONVERT_DATA_ERROR);
        }
        return Employee
                .builder()
                .id(object.getId())
                .code(object.getCode())
                .phoneNumber(object.getPhoneNumber())
                .fullName(object.getFullName())
                .nationalId(object.getNationalId())
                .dob(object.getDob())
                .email(object.getEmail())
                .gender(object.getGender())
                .address(object.getAddress())
                .ward(object.getWard())
                .district(object.getDistrict())
                .province(object.getProvince())
                .profession(object.getProfession())
                .maritalStatus(object.getMaritalStatus())
                .salary(object.getSalary())
                .build();
    }

    public static EmployeeResponse toDTO(Employee object) {
        if (ObjectUtils.isEmpty(object)) {
            ExceptionUtil.throwCustomException(MessageCodeEnum.CONVERT_DATA_ERROR);
        }
        return EmployeeResponse
                .builder()
                .id(object.getId())
                .code(object.getCode())
                .phoneNumber(object.getPhoneNumber())
                .fullName(object.getFullName())
                .nationalId(object.getNationalId())
                .dob(object.getDob())
                .email(object.getEmail())
                .gender(object.getGender())
                .address(object.getAddress())
                .ward(object.getWard())
                .district(object.getDistrict())
                .province(object.getProvince())
                .profession(object.getProfession())
                .maritalStatus(object.getMaritalStatus())
                .salary(object.getSalary())
                .build();
    }
}
