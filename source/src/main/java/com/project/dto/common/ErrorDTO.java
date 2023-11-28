package com.project.dto.common;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.project.utils.DateUtil;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer code;
	private String message;
	private String timestamp;

	public ErrorDTO(String message) {
		this.message = message;
		this.timestamp = DateUtil.dateToString(new Date(), DateUtil.DATE_TIME_GMT);
	}

}
