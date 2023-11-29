package com.project.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import com.project.constant.Constant;

import java.text.Normalizer;
import java.util.Date;
import java.util.Random;
import java.util.regex.Pattern;

@Slf4j
public class CommonMethods {

    private static final String PATTERN_REPLACE = "\\p{InCombiningDiacriticalMarks}+";
    
    public static String removeCodeFromString(String aString) {
        if (StringUtils.isEmpty(aString)) {
            return aString;
        }

        if (!aString.contains("-") || aString.equals("-")) {
            return aString;
        }
        return aString.substring(aString.indexOf(Constant.HYPHEN) + 1);
    }

    public static String removeAccent(String s) {
        if (StringUtils.isBlank(s)) {
            return Constant.EMPTY;
        }
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile(PATTERN_REPLACE);
        temp = pattern.matcher(temp).replaceAll(Constant.EMPTY);
        temp = temp.replaceAll("Đ", "D");
        temp = temp.replaceAll("đ", "d");
        return temp.trim();
    }

    public static String randomToken() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 16;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }

    public static String randomCode(String type) {
        return type
                .concat(Constant.HYPHEN)
                .concat(RandomStringUtils.random(4, true, true).toLowerCase())
                .concat(Constant.HYPHEN)
                .concat(String.valueOf(new Date().getTime() / 1000000));
    }
}
