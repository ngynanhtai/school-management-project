package com.project.utils;

import com.project.enums.SortEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


public class PageUtil {
	
	public static Pageable buildPageable(int page, int size, String fieldSort, String sort) {
		page--;
		if(page < 0) {
			page = 0;
			size = Integer.MAX_VALUE;
		}
		if(StringUtils.isBlank(fieldSort)) {
			return PageRequest.of(page, size);
		}
		if(SortEnum.ASC.getCode().equals(sort)) {
			return PageRequest.of(page, size, Sort.by(fieldSort).ascending());
		}
		return PageRequest.of(page, size, Sort.by(fieldSort).descending());
	}
}
