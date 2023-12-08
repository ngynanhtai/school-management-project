package com.project.model.mapstruct;

import com.project.dto.DocumentDTO;
import com.project.enums.MessageCodeEnum;
import com.project.model.entity.Document;
import com.project.model.entity.DocumentType;
import com.project.utils.ExceptionUtil;
import org.apache.commons.lang3.ObjectUtils;

public class DocumentMapstruct {
    public static DocumentDTO toDTO(Document object) {
        if (ObjectUtils.isEmpty(object)) {
            ExceptionUtil.throwCustomException(MessageCodeEnum.CONVERT_DATA_ERROR);
        }
        DocumentType documentType = object.getDocumentType();
        return DocumentDTO
                .builder()
                .id(object.getId())
                .path(object.getPath())
                .type(object.getType())
                .userCode(object.getUserCode())
                .classCode(object.getClassCode())
                .mediaType(documentType.getMediaType())
                .updatedDate(object.getUpdatedDate())
                .createdDate(object.getCreatedDate())
                .build();
    }
}
