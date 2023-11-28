package com.project.dto.common;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PagingDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private int totalPages;
	private int pageNumber;
	private int pageSize;
	private int totalElements;
}
