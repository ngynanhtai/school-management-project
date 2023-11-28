package com.project.dto.common;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import com.project.enums.ResponseStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private ResponseStatusEnum status;
	private Object data;
	private PagingDTO page;
	private ErrorDTO error;
}
