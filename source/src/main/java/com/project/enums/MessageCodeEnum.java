package com.project.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MessageCodeEnum { 
	
	/** ============================================== VALIDATION MESSAGE =========================================== */
    INTERNAL_SERVER_ERROR(5000, "internal.server.error"),
    VALIDATION_REQUEST_NULL(5001, "validation.request.null"),
	VALIDATION_TOKEN_NULL(5002, "validation.token.null"),
    CONVERT_DATA_ERROR(5003, "convert.data.error"),
    CONNECTION_TIMEOUT(5004,"connection.timeout"),
    DATA_NOT_FOUND(5005, "data.not.found"),
    ENUM_NOT_MATCH(5006, "enum.not.found"),
    DELETE_ERROR(5007, "delete.error"),
    ENTITY_DELETED(5008,"entity.deleted"),
    DUPLICATE_DATA(5009, "duplicate.data"),
    STORAGE_INIT_ERROR(5010, "storage.init.error"),
    UPLOAD_FILE_ERROR(5011, "upload.file.error"),
    DOC_TYPE_NOT_MULTIPLE(5012,"doc.type.not.multiple")
    ;

    private final Integer code;
    private final String message;
}
