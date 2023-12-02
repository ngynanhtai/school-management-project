package com.project.enums;

import com.project.utils.ExceptionUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Calendar;

@Getter
@AllArgsConstructor
public enum DateEnum {
    MONDAY(Calendar.MONDAY, "MONDAY"),
    TUESDAY(Calendar.TUESDAY, "TUESDAY"),
    WEDNESDAY(Calendar.WEDNESDAY, "WEDNESDAY"),
    THURSDAY(Calendar.THURSDAY, "THURSDAY"),
    FRIDAY(Calendar.FRIDAY, "FRIDAY"),
    SATURDAY(Calendar.SATURDAY, "SATURDAY"),
    SUNDAY(Calendar.SUNDAY, "SUNDAY")
    ;

    private final int dateInt;
    private final String dateString;

    public static DateEnum findByDateString(String dateString) {
        DateEnum result = null;
        for (DateEnum dateEnum : DateEnum.values()) {
            if (dateEnum.dateString.equalsIgnoreCase(dateString)) {
                result = dateEnum;
                break;
            }
        }
        if (ObjectUtils.isEmpty(result)) {
            ExceptionUtil.throwCustomException(MessageCodeEnum.ENUM_NOT_MATCH);
        }
        return result;
    }
}
