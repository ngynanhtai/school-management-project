package com.project.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SortEnum {

	ASC("ASC"), DES("DES");
	
	private String code;
}
