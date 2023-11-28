package com.project.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EmailEnum {
    Register("Tạo tài khoản", "create-template"),;

    private String subject;
    private String template;
}
