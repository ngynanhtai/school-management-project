package com.project.utils;

import com.project.configuration.Translator;
import com.project.enums.MessageCodeEnum;
import com.project.exception.CustomException;

public class ExceptionUtil {
	public static void throwCustomException(MessageCodeEnum messageCode, Object... objects) {
		throw new CustomException(null, messageCode.getCode(), Translator.tolocale(messageCode.getMessage(), objects));
	}

	public static void throwCustomException(Object data, MessageCodeEnum messageCode, Object... objects) {
		throw new CustomException(data, messageCode.getCode(), Translator.tolocale(messageCode.getMessage(), objects));
	}

	public static void throwCustomException(int code, String message) {
		throw new CustomException(null, code, message);
	}

	public static void throwCustomException(Object data, int code, String message) {
		throw new CustomException(data, code, message);
	}
}
