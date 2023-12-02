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
    ENUM_NOT_MATCH(5006, "enum.not.match"),
    ;

    private final Integer code;
    private final String message;
}
