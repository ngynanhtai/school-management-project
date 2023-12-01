package com.project.model.mapstruct;

import com.project.enums.MessageCodeEnum;
import com.project.dto.StudentDTO;
import com.project.model.entity.Student;
import com.project.utils.ExceptionUtil;
import org.apache.commons.lang3.ObjectUtils;

public class StudentMapstruct {
    public static Student toEntity(StudentDTO object) {
        if (ObjectUtils.isEmpty(object)) {
            ExceptionUtil.throwCustomException(MessageCodeEnum.CONVERT_DATA_ERROR);
        }
        return Student
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
                .firstParentName(object.getFirstParentName())
                .firstParentPhone(object.getFirstParentPhone())
                .firstParentRelation(object.getFirstParentRelation())
                .secondParentName(object.getSecondParentName())
                .secondParentPhone(object.getSecondParentPhone())
                .secondParentRelation(object.getSecondParentRelation())
                .build();
    }
    
    public static StudentDTO toDTO(Student object) {
        if (ObjectUtils.isEmpty(object)) {
            ExceptionUtil.throwCustomException(MessageCodeEnum.CONVERT_DATA_ERROR);
        }
        return StudentDTO
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
                .firstParentName(object.getFirstParentName())
                .firstParentPhone(object.getFirstParentPhone())
                .firstParentRelation(object.getFirstParentRelation())
                .secondParentName(object.getSecondParentName())
                .secondParentPhone(object.getSecondParentPhone())
                .secondParentRelation(object.getSecondParentRelation())
                .roleId(object.getId())
                .build();
    }
}
