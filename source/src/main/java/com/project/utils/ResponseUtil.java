package com.project.utils;

import java.util.Date;

import com.project.dto.common.ErrorDTO;
import com.project.dto.common.ResponseDTO;
import com.project.enums.ResponseStatusEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ResponseUtil {
	public static ResponseEntity<ResponseDTO> buildErrorBadRequest(Object data, Integer code, String message){
		log.error("Custom exception: {}", message);
		return new ResponseEntity<>(ResponseDTO.builder()
				.data(data)
				.status(ResponseStatusEnum.ERROR)
				.error(buildObjectError(code, message))
				.build(), HttpStatus.BAD_REQUEST);
	}

	public static ResponseEntity<ResponseDTO> buildErrorServerInternal(String message){
		log.error("Custom exception: {}", message);
		return new ResponseEntity<>(ResponseDTO.builder()
				.status(ResponseStatusEnum.ERROR)
				.error(buildObjectError(HttpStatus.INTERNAL_SERVER_ERROR.value(), message))
				.build(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public static ErrorDTO buildObjectError(Integer Code, String message) {
		return ErrorDTO.builder()
				.code(Code)
				.message(message)
				.timestamp(DateUtil.dateToString(new Date(), DateUtil.DATE_TIME_GMT))
				.build();
	}

	public static ResponseEntity<ResponseDTO> buildSuccess(Object data) {
		return ResponseEntity.ok(ResponseDTO.builder()
				.status(ResponseStatusEnum.SUCCESS)
				.data(data)
				.build());
	}
}
