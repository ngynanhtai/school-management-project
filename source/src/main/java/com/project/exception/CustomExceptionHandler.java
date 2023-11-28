package com.project.exception;

import com.project.dto.common.ResponseDTO;
import com.project.utils.ResponseUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class CustomExceptionHandler {

	@ExceptionHandler(value = CustomException.class)
	public final ResponseEntity<ResponseDTO> exception(CustomException ex) {
		log.error("Custom exception: {}", ex.getMessage(), ex);
		return ResponseUtil.buildErrorBadRequest(ex.getData() ,ex.getCode(), ex.getMessage());
	}

	@ExceptionHandler(value = Exception.class)
	public final ResponseEntity<ResponseDTO> runtimeException(Exception ex) {
		log.error("Internal exception: {}", ex.getMessage(), ex);
		return ResponseUtil.buildErrorServerInternal(ex.getMessage());
	}
}
