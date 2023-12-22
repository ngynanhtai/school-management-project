package com.project.constant;

public class Constant {

	// -----------------LOCALE-----------------//
	public static final String I18N_MESSAGES = "i18n/messages";
	public static final String UTF8 = "UTF-8";
	public static final String EN = "en";
	public static final String VI = "vi";

	// ---------------AUTH-----------------------//

	public static final String AUTHORIZATION = "Authorization";
	public static final String BEARER = "Bearer ";
	public static final String CLIENT_ID = "client_id";
	public static final String CLIENT_SECRET = "client_secret";
	public static final String GRANT_TYPE = "grant_type";
	public static final String CONTENT_TYPE = "Content-Type";

	// ---------------MEDIA_TYPE-----------------------//
	public static final String JPEG_MEDIA_TYPE = "data:image/jpeg;base64";
	public static final String JPG_MEDIA_TYPE = "data:image/jpg;base64";
	public static final String PNG_MEDIA_TYPE = "data:image/png;base64";
	public static final String MP4_MEDIA_TYPE = "data:video/mp4;base64";

	// ---------------PUNCTUATION-----------------------//
	public static final String EMPTY = "";
	public static final String HYPHEN = "-";
	public static final String UNDERSCORE = "_";
	public static final String WHITE_SPACE = " ";
	public static final String PERCENT = "%";
	public static final String COMMA = ",";
	public static final String SLASH = "/";
	public static final String COMMA_SPACE = ", ";
	public static final String BREAK_LINE = "\n";

	// ---------------REGEX VALIDATION-----------------------//
	public static final String NAME_REGEX = "^[a-zA-Z0-9\\s_()]+$";
	public static final String NAME_UTF8_SPECIAL_REGEX = "^[a-zA-Z0-9\\s\\p{L}+$.-]+$";
	public static final String USER_NAME_REGEX = "^[a-zA-Z0-9.-]+$";
	public static final String PHONE_REGEX = "^0[0-9]{9}$";
	public static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
	public static final String NUMERIC_REGEX = "[0-9]+";

	// ---------------ROLE-----------------------//
	public static final String STUDENT_ROLE = "STUDENT";
	public static final String TEACHER_ROLE = "TEACHER";
	public static final String EMPLOYEE_ROLE = "EMPLOYEE";

	// ---------------Storage-----------------------//
	public static final String PERSONAL_PATH = "PERSONAL";
	public static final String SCHOOL_PATH = "SCHOOL";

	// ---------------URI-----------------------//
	public static final String LOGIN_URI = "/login";
}
