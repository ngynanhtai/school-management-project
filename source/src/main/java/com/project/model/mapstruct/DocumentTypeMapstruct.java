package com.project.model.mapstruct;

import com.project.dto.DocumentTypeDTO;
import com.project.enums.MessageCodeEnum;
import com.project.model.entity.DocumentType;
import com.project.utils.ExceptionUtil;
import org.apache.commons.lang3.ObjectUtils;

public class DocumentTypeMapstruct {
    public static DocumentType toEntity(DocumentTypeDTO object) {
        if (ObjectUtils.isEmpty(object)) {
            ExceptionUtil.throwCustomException(MessageCodeEnum.CONVERT_DATA_ERROR);
        }
        return DocumentType
                .builder()
                .id(object.getId())
                .code(object.getCode())
                .name(object.getName())
                .basicName(object.getBasicName())
                .mediaType(object.getMediaType())
                .fileExtension(object.getFileExtension())
                .isMultiple(object.isMultiple())
                .build();
    }

    public static DocumentTypeDTO toDTO(DocumentType object) {
        if (ObjectUtils.isEmpty(object)) {
            ExceptionUtil.throwCustomException(MessageCodeEnum.CONVERT_DATA_ERROR);
        }
        return DocumentTypeDTO
                .builder()
                .id(object.getId())
                .code(object.getCode())
                .name(object.getName())
                .basicName(object.getBasicName())
                .mediaType(object.getMediaType())
                .fileExtension(object.getFileExtension())
                .isMultiple(object.isMultiple())
                .build();
    }
}
