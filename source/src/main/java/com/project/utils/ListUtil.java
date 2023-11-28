package com.project.utils;

import java.util.ArrayList;
import java.util.List;

public class ListUtil {

	@SuppressWarnings("unchecked")
	public static <T> List<T> emptyList() {
		return (List<T>) new ArrayList<>(0);
	}

}
