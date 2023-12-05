package com.project.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public enum ScoreEnum {
    FINAL("FINAL"),
    SEMI_FINAL("SEMI_FINAL"),
    X1("X1"),
    X2("X2")
    ;
    private final String type;

    public static List<String> scoreEnumTypes() {
        return Arrays.stream(ScoreEnum.values()).map(ScoreEnum::getType).collect(Collectors.toList());
    }
}
