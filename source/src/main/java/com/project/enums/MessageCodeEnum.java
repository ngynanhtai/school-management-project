package com.project.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MessageCodeEnum { 
	
	/** ============================================== VALIDATION MESSAGE =========================================== */
	VALIDATION_REQUEST_NULL(1700, "validation.request.null"),
	VALIDATION_TOKEN_NULL(1701, "validation.token.null"),
    CONVERT_DATA_ERROR(1702, "convert.data.error"),
    CONNECTION_TIMEOUT(1720,"connection.timeout"),
    DATA_NOT_FOUND(1710, "data.not.found"),


    ROLE_IS_NULL(1730, "role.is.null"),
    ROLE_NOT_ACCEPT(1731, "role.not.accept")
    ;

    private final Integer code;
    private final String message;
}
